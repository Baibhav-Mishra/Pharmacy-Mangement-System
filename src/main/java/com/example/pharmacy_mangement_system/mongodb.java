package com.example.pharmacy_mangement_system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class mongodb {

    public static ObservableList<Medicine> fetchData() {
        ObjectMapper mapper = new ObjectMapper();
        ObservableList<Medicine> list = FXCollections.observableArrayList();

        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Medicines");

//            Document doc = collection.find(eq("title", "Back to the Future")).first();
//            Document doc1 = new Document("_id", 2).append("type", "Tablet").append("name", "Crocin").append("price", 10).append("expiry", "20/10/2004").append("manufacturer","GSV").append("currentStock", 60);
//            InsertOneResult result = collection.insertOne(doc1);


            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    mapper.reader();
                    Medicine m1 = mapper.readValue(cursor.next().toJson(), Medicine.class);
                    list.add(m1);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
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
        catch(MongoTimeoutException exc){
            System.out.println("Timeout");
        }

    }
    public static void updateCollection(ObservableList<Medicine> list)
    {
        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Medicines");
            for(Medicine m: list)
            {
                collection.updateOne(Filters.eq("_id", m._id), Updates.set("currentStock", m.getCurrentStock()));
            }
        }
        catch(MongoTimeoutException exc){
            System.out.println("Timeout");
        }
    }

    public static boolean verifyCred(String username, String password) {
        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Users");

            try (MongoCursor<Document> cursor = collection.find(and(eq("username", username), eq("password", password))).iterator()) {
                if (cursor.hasNext()) {return true;}
            }
            catch (MongoTimeoutException exc) {System.out.println("Exception");}
        }
        return false;
    }
    public static boolean addCred(String username, String password)
    {
        String uri = "mongodb+srv://admin:admin@cluster0.ez7ctd3.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("PharmacyDB");
            MongoCollection<Document> collection = database.getCollection("Users");
            Bson filter = eq("username", username);
            // iterate code goes here
            try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
                if (!cursor.hasNext()) {
                    Document doc1 = new Document("username", username).append("password", password);
                    collection.insertOne(doc1);
                    return true;
                }
            }
        }
        catch(MongoTimeoutException exc){
            System.out.println("Timeout");
        }
        return false;

    }


}