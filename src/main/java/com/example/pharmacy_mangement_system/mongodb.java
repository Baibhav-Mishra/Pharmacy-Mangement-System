package com.example.pharmacy_mangement_system;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class mongodb {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@cluster0.qkqmwrq.mongodb.net/?retryWrites=true&w=majority";
        MongoClient client =  MongoClients.create(connectionString);
        MongoDatabase db = client.getDatabase("sample1");

        MongoCollection col = db.getCollection("data");
//
        Document sampleDoc = new Document("_id", "1").append("name", "John Smith");
//
        col.insertOne(sampleDoc);
    }
}
