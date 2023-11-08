package com.example.pharmacy_mangement_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AlertController implements Initializable {

// Low stock Table
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

// Near expiry Table
    @FXML
    private TableColumn<Medicine, String> alertExpiry1;

    @FXML
    private TableColumn<Medicine, Integer> alertID1;

    @FXML
    private TableColumn<Medicine, String> alertManufacturer1;

    @FXML
    private TableColumn<Medicine, String> alertName1;

    @FXML
    private TableColumn<Medicine, Double> alertStock1;

    @FXML
    private TableColumn<Medicine, String> alertType1;

    @FXML
    private TableView<Medicine> nearExpiryTable;

    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

    ObservableList<Medicine> lowStockObservableList() {
        ObservableList<Medicine> list = FXCollections.observableArrayList();
        for (Medicine med: mongodb.fetchData()) {
            if (med.getCurrentStock() < 10) {
                list.add(med);
            }
        }
        return list;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    ObservableList<Medicine> nearExpiryObservableList() {
        ObservableList<Medicine> list = FXCollections.observableArrayList();
        Calendar expiry = toCalendar(Calendar.getInstance().getTime());
        expiry.add(Calendar.MONTH, 1);
        for (Medicine med: mongodb.fetchData()) {
            String dateString = med.getExpiry();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null; Calendar cal = null;
            try {
                date = format.parse(dateString);
                cal = toCalendar(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert cal != null;
            if (cal.before(expiry)) {
                list.add(med);
            }
        }
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertID.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("_id"));
        alertType.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        alertExpiry.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiry"));
        alertName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        alertManufacturer.setCellValueFactory(new PropertyValueFactory<Medicine, String>("manufacturer"));
        alertStock.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("currentStock"));
        lowStockTable.setItems(lowStockObservableList());

        alertID1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("_id"));
        alertType1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        alertExpiry1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiry"));
        alertName1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        alertManufacturer1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("manufacturer"));
        alertStock1.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("currentStock"));
//        nearExpiryObservableList();
        nearExpiryTable.setItems(nearExpiryObservableList());
    }
}