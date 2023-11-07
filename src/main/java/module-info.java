module com.example.pharmacy_mangement_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.sql;
    requires mysql.connector.java;
    requires com.fasterxml.jackson.databind;
    requires itextpdf;
    requires layout;
    requires kernel;
    opens com.example.pharmacy_mangement_system to javafx.fxml;
    exports com.example.pharmacy_mangement_system;
}