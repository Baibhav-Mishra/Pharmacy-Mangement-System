package com.example.pharmacy_mangement_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable {

    @FXML
    private TableColumn<Medicine, String> alertExpiry;

    @FXML
    private TableColumn<Medicine, Integer> alertID;

    @FXML
    private TableColumn<Medicine, String> alertManufacturer;

    @FXML
    private TableColumn<Medicine, String> alertName;

    @FXML
    private TableColumn<Medicine, Double> alertStock;

    @FXML
    private TableColumn<Medicine, String> alertType;

    @FXML
    private TableView<Medicine> lowStockTable;

    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertID.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("_id"));
        alertType.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        alertExpiry.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiry"));
        alertName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        alertManufacturer.setCellValueFactory(new PropertyValueFactory<Medicine, String>("manufacturer"));
        alertStock.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("currentStock"));
        lowStockTable.setItems(mongodb.fetchData());
    }
}