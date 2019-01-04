package com.krishna.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.beans.Request;
import com.krishna.beans.Response;
import com.krishna.logger.LoggerUtility;
import com.krishna.mongoconfig.MongoConfig;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController

public class Controller {
	@Autowired
	private Response response;
	@Autowired
	MongoConfig mongoClient;

	@Value("${mongodb.databasename:#{null}}")
	private String DATABASE;
	@Value("${mongodb.collectionname:#{null}}")
	private String COLLECTION_NAME;
	@Autowired
	LoggerUtility loggerUtility;

	@ApiOperation(value = "Insert Operation", response = Response.class, tags = {
			"Spring_Boot_Web_Security_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> insert(@RequestBody Request request) throws Exception {
//		logger.getLogger().info("started insert method execution");
		loggerUtility.getLogger().info("started insert method execution");
		String id = request.getId();
		String name = request.getName();
		String dep = request.getDep();

		MongoCollection<Document> coll = getMongoCollection();
//		logger.getLogger().info("set the requested values to document object");
		Document doc = new Document();
		doc.put("_id", id);
		doc.put("name", name);
		doc.put("dep", dep);

		try {
//			logger.getLogger().info("inserting the data.....");
			coll.insertOne(doc);
		} catch (Exception ex) {
//			logger.getLogger().info("Build  error response");

			response.setId(id);
			response.setName(name);
			response.setDep(dep);
			response.setDesc(ex.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
//		logger.getLogger().info("Getting the data from the mongodb with specific key");
		Document query = new Document("_id", id);
		FindIterable<Document> cursor = coll.find(query);
		MongoCursor<Document> mongoCursor = cursor.iterator();

		Document query1 = new Document();
		try {
			while (mongoCursor.hasNext()) {
				query1 = mongoCursor.next();
				response.setId(query1.get("_id").toString());
				response.setName(query1.get("name").toString());
				response.setDep(query1.get("dep").toString());

			}
		} finally {
			mongoCursor.close();
		}
//		logger.getLogger().info("Build success response");

		response.setDesc("successfully inserted");

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "Insert Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@GetMapping(value = "/retrive/{id}")
	public ResponseEntity<Response> retrive(@PathVariable String id) throws Exception {
//		logger.getLogger().info("started retrive method execution");

		MongoCollection<Document> coll = getMongoCollection();
//		logger.getLogger().info("Getting the data from the mongodb with specific key");
		Document query = new Document("_id", id);
		FindIterable<Document> cursor = coll.find(query);
		MongoCursor<Document> mongoCursor = cursor.iterator();

		List<Document> listOfdoc = new ArrayList<Document>();

		Document query1 = new Document();
		try {
			while (mongoCursor.hasNext()) {
				query1 = mongoCursor.next();
				response.setId(query1.get("_id").toString());
				response.setName(query1.get("name").toString());
				response.setDep(query1.get("dep").toString());

			}
		} finally {
			mongoCursor.close();
		}
//		logger.getLogger().info("Build success response");
		response.setDesc("Retrive the data successfully");

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "Insert Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable String id) throws Exception {
		MongoCollection<Document> coll = getMongoCollection();
//		logger.getLogger().info("Retrive the data with specific id");
		Document query = new Document("_id", id);
		FindIterable<Document> cursor = coll.find(query);
		MongoCursor<Document> mongoCursor = cursor.iterator();

		if (mongoCursor.hasNext() == false) {
//			logger.getLogger().info("Build error response");
			response.setId(id);
			response.setDesc("Specific id is not there");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			Document query1 = new Document();
			try {
				while (mongoCursor.hasNext()) {
					query1 = mongoCursor.next();
					coll.deleteOne(query1);
				}
			} finally {
				mongoCursor.close();
			}
//			logger.getLogger().info("Build success response");
			response.setId(id);
			response.setDesc("Delete the data with specific id successfully");

			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Insert Operation", response = Response.class, tags = {
			"Spring_Boot_Rest_API_Mongodb_Crud_Operations" })
	@ApiResponse(code = 200, message = "OK", response = Response.class)
	@GetMapping(value = "/showAll")
	public ResponseEntity<Response> show() throws Exception {
//		logger.getLogger().info("started showAll method execution");
		MongoCollection<Document> coll = getMongoCollection();
//		logger.getLogger().info("Getting all data from collection object");
		FindIterable<Document> fi = coll.find();
		MongoCursor<Document> cursor = fi.iterator();
		List<Document> doc1 = new ArrayList<Document>();
		Document docu = new Document();
		try {
			while (cursor.hasNext()) {
				docu = cursor.next();
				doc1.add(docu);
			}
		} finally {
			cursor.close();
		}
//		logger.getLogger().info("Build success response");
		response.setData(doc1);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	private MongoCollection<Document> getMongoCollection() throws Exception {
//		logger.getLogger().info("Getting mongo collection");
		MongoDatabase db = mongoClient.mongo().getDatabase(DATABASE);
		MongoCollection<Document> coll = db.getCollection(COLLECTION_NAME);
		return coll;
	}

}
