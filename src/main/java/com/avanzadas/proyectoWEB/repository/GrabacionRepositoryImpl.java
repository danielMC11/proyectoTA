package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.adaptadores.implementacion.MongoAdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.FabricaBaseDeDatos;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.Persistencia;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;


import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import java.io.ByteArrayOutputStream;



import static com.mongodb.client.model.Filters.eq;

@Repository
public class GrabacionRepositoryImpl implements GrabacionRepository{
	private AdaptadorBaseDatos adaptadorBaseDatos;

	public GrabacionRepositoryImpl(){
		adaptadorBaseDatos = FabricaBaseDeDatos.obtenerInstancia().obtenerAdaptador(Persistencia.MONGODB);
	}

	@Override
	public void guardarFrame(int userId, byte[] imagenData) {
		try (MongoClient conn = (MongoClient) adaptadorBaseDatos.obtenerConexion()) {

			MongoDatabase database = conn.getDatabase(adaptadorBaseDatos.getDbname());
			MongoCollection<Document> framesCollection = database.getCollection("frames");

			// Crear el documento con el campo de imagen en formato binario
			Document frameDoc = new Document("userId", userId)
				.append("imagenData", new Binary(imagenData));

			// Insertar el documento en la colección
			framesCollection.insertOne(frameDoc);

		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public List<byte[]> obtenerFramesTemporales(int userId){
		List<byte[]> frames = new ArrayList<>();

		try (MongoClient conn = (MongoClient) adaptadorBaseDatos.obtenerConexion()) {
			MongoDatabase database = conn.getDatabase(adaptadorBaseDatos.getDbname());
			MongoCollection<Document> framesCollection = database.getCollection("frames");

			// Consulta los documentos de la colección frames con el userId especificado
			List<Document> documents = framesCollection.find(new Document("userId", userId)).into(new ArrayList<>());

			// Procesa cada documento
			for (Document doc : documents) {
				// Obtiene el campo imagenData como Binary y extrae los datos en byte[]
				Object imagenDataObj = doc.get("imagenData");
				if (imagenDataObj instanceof Binary) {
					Binary imagenBinary = (Binary) imagenDataObj; // Hacer el casting a Binary
					frames.add(imagenBinary.getData());
				} else {
					// Manejar el caso cuando imagenData no es un Binary
					System.out.println("El campo 'imagenData' no es de tipo Binary en el documento: " + doc);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return frames;
	}


	@Override
	public Path crearVideoTemporalConFrames(List<byte[]> frames, int frameRate) {

		SeekableByteChannel out = null;
		Path tempFile = null;
		try {
			tempFile = Files.createTempFile("video_temp_usuario", ".mp4");

			out = NIOUtils.writableFileChannel(tempFile.toString());
			AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(frameRate, 1));
			for (byte[] frame : frames) {
				ByteArrayInputStream bais = new ByteArrayInputStream(frame);
				BufferedImage image = ImageIO.read(bais);
				encoder.encodeImage(image);
			}
			encoder.finish();
			NIOUtils.closeQuietly(out);
		}catch (Exception e){
			e.printStackTrace();
		}
		return tempFile;

	}

	@Override
	public void guardarVideo(Path rutaVideo) {
		try(
			MongoClient conn = (MongoClient) adaptadorBaseDatos.obtenerConexion();
		) {

			MongoDatabase database =  conn.getDatabase(adaptadorBaseDatos.getDbname());
			GridFSBucket gridFSBucket = GridFSBuckets.create(database, "videos");

			File videoFile = new File(rutaVideo.toString());
			try (FileInputStream streamToUploadFrom = new FileInputStream(videoFile)) {
				GridFSUploadOptions options = new GridFSUploadOptions()
					.chunkSizeBytes(1024 * 1024) // Tamaño de los chunks
					.metadata(new org.bson.Document("type", "video"));

				gridFSBucket.uploadFromStream("", streamToUploadFrom, options);
			}

			if (Files.exists(rutaVideo))
					Files.delete(rutaVideo);


		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void eliminarFramesTemporales() {
		try(
			MongoClient conn = (MongoClient) adaptadorBaseDatos.obtenerConexion();
		) {
			MongoDatabase database =  conn.getDatabase(adaptadorBaseDatos.getDbname());
			MongoCollection<Document> coleccion = database.getCollection("frames");
			coleccion.drop();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ByteArrayInputStream buscarPorId(String id) {
		ByteArrayInputStream inputStream = null;
		try (
			MongoClient conn = (MongoClient) adaptadorBaseDatos.obtenerConexion();
		) {
			MongoDatabase database =  conn.getDatabase(adaptadorBaseDatos.getDbname());
			GridFSBucket gridFSBucket = GridFSBuckets.create(database, "videos");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			GridFSFile gridFSFile = gridFSBucket.find(eq("_id", new ObjectId(id))).first();
			gridFSBucket.downloadToStream(gridFSFile.getObjectId(), outputStream);
			inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	@Override
	public List<String> buscarTodos() {
		List<String> videosId = new ArrayList<>();
		try(
			MongoClient conn =  (MongoClient) adaptadorBaseDatos.obtenerConexion();
		) {
			MongoDatabase database =  conn.getDatabase(adaptadorBaseDatos.getDbname());
			GridFSBucket gridFSBucket = GridFSBuckets.create(database, "videos");

			MongoCursor<GridFSFile> filesCursor = gridFSBucket.find().iterator();

			while (filesCursor.hasNext()) {
				GridFSFile gridFSFile = filesCursor.next();
				videosId.add(gridFSFile.getObjectId().toHexString());
			}
			filesCursor.close();

		}catch (Exception e){
			e.printStackTrace();
		}
		return videosId;
	}
}
