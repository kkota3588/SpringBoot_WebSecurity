package com.krishna.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.beans.Request;
import com.krishna.beans.Response;
import com.krishna.mongoconfig.MongoConfig;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@EnableAutoConfiguration
@RestController
@Component
public class Controller {
	@Autowired
	private Response response;
	@Autowired
	MongoConfig mongoClient;

	@Value("${mongodb.databasename:#{null}}")
	private String DATABASE;
	@Value("${mongodb.collectionname:#{null}}")
	private String COLLECTION_NAME;

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> insert(@RequestBody Request request) throws Exception {
		String id = request.getId();
		String name = request.getName();
		String dep = request.getDep();

		MongoCollection<Document> coll = getMongoCollection();

		Document doc = new Document();
		doc.put("_id", id);
		doc.put("name", name);
		doc.put("dep", dep);
		try {
			coll.insertOne(doc);
		} catch (Exception ex) {
			response.setId(id);
			response.setName(name);
			response.setDep(dep);
			response.setDesc(ex.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		Document query = new Document("_id", id);
		FindIterable<Document> cursor = coll.find(query);
		MongoCursor<Document> mongoCursor = cursor.iterator();

		List<Document> listOfdoc = new ArrayList<Document>();

		Document query1 = new Document();
		try {
			while (mongoCursor.hasNext()) {
				query1 = mongoCursor.next();
				listOfdoc.add(query1);
			}
		} finally {
			mongoCursor.close();
		}
		response.setData(listOfdoc);
		response.setDesc("successfully inserted");

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/showAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> show(@RequestBody String value) throws Exception {
		MongoCollection<Document> coll = getMongoCollection();
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
		response.setData(doc1);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	private MongoCollection<Document> getMongoCollection() throws Exception {
		MongoDatabase db = mongoClient.mongo().getDatabase(DATABASE);
		MongoCollection<Document> coll = db.getCollection(COLLECTION_NAME);
		return coll;
	}

}
