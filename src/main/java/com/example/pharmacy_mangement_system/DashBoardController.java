package com.example.pharmacy_mangement_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private TableColumn<Medicine, String> ExpiryColumn1;

    @FXML
    private TableColumn<Medicine, Integer> IDColumn1;

    @FXML
    private TableColumn<Medicine, String> NameColumn1;

    @FXML
    private TableColumn<Medicine, Double> PriceColumn1;

    @FXML
    private TableColumn<Medicine, Integer> SerialNoColumn1;

    @FXML
    private TableColumn<Medicine, String> TypeColumn1;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Medicine, String> ManufactureColumn1;


    @FXML
    private TableView<Medicine> table1;

    @FXML
    void onEnterSearchField(ActionEvent event) {
        System.out.println(searchField.getText());
    }

    ObservableList<Medicine> list = FXCollections.observableArrayList(


            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004"),
            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004")


    );


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("id"));
        ExpiryColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiry"));
        NameColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("cost"));
        TypeColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        ManufactureColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("manufacturer"));
        SerialNoColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("serialno"));
        table1.setItems(list);


    }
}