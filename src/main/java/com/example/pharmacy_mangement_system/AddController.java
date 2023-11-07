package com.example.pharmacy_mangement_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    private DatePicker addExpiry;

    @FXML
    private TextField addID;

    @FXML
    private TextField addManufacturer;

    @FXML
    private TextField addName;

    @FXML
    private TextField addPrice;

    @FXML
    private TextField addQuantity;

    @FXML
    private ComboBox<?> addType;

    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

    @FXML
    void onAddButtonPress(ActionEvent event) {
        String id = addID.getText();
        String name = addName.getText();
        String manufacturer = addManufacturer.getText();
        String price = addPrice.getText();
        String expiry = addExpiry.getValue().toString();
        String type = addType.getValue().toString();
        String quantity = addQuantity.getText();

        if (id.isEmpty() || name.isEmpty() || manufacturer.isEmpty() || price.isEmpty() || expiry.isEmpty() || type.isEmpty() || quantity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            mongodb.addtoCollection(Integer.parseInt(id), type, name, Double.parseDouble(price), expiry, manufacturer, Integer.parseInt(quantity));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
