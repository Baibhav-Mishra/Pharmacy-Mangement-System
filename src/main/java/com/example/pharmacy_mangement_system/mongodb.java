package com.example.pharmacy_mangement_system;

import static com.mongodb.client.model.Filters.eq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.result.InsertOneResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
public class mongodb {

    public static ObservableList<Medicine> fetchData() {
        ObjectMapper mapper = new ObjectMapper();
        ObservableList<Medicine> list = FXCollections.observableArrayList();

        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Medicines");
//
//            Document doc = collection.find(eq("title", "Back to the Future")).first();
//            Document doc1 = new Document("_id", 2).append("type", "Tablet").append("name", "Crocin").append("price", 10).append("expiry", "20/10/2004").append("manufacturer","GSV").append("currentStock", 60);
//            InsertOneResult result = collection.insertOne(doc1);


            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
//                    System.out.println();
                    Medicine m1 = mapper.readValue(cursor.next().toJson(), Medicine.class);
                    list.add(m1);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(list.size());
        }
        catch(MongoTimeoutException exc){
            System.out.println("h");
        }
        return list;
    }
    public static void addtoCollection(int id, String type, String name, double price, String expiry, String manufacturer, int currentStock) {
        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Medicines");
            Document doc1 = new Document("_id", id).append("type", type).append("name", name).append("price", price).append("expiry", expiry).append("manufacturer", manufacturer).append("currentStock", currentStock);
            InsertOneResult result = collection.insertOne(doc1);
        }
    }
}