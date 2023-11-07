package com.example.pharmacy_mangement_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
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

//    @FXML
//    private TableColumn<Medicine, Integer> SerialNoColumn1;

    @FXML
    private TableColumn<Medicine, String> TypeColumn1;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Medicine, String> ManufactureColumn1;


    @FXML
    private TableView<Medicine> table1;



    @FXML
    private TableColumn<Medicine, Integer> IDColumn2;

    @FXML
    private TableColumn<Medicine, String> NameColumn2;

    @FXML
    private TableColumn<Medicine, Double> PriceColumn2;

    @FXML
    private TableColumn<Medicine, Double> AmountColumn2;

    @FXML
    private TableColumn<Medicine, Double> QuantityColumn;

    @FXML
    private TableColumn<Medicine, Double> QuantityColumn2;

    @FXML
    private TableColumn<Medicine, String> SerialNoColumn2;

    @FXML
    private TableView<Medicine> billing_table;

    @FXML
    Medicine ValueTable2, ValueTable1;
    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

    @FXML
    private TextField QuantityLabel;

    @FXML
    void onEnterSearchField(ActionEvent event) {
        System.out.println(searchField.getText());
    }
    @FXML
    void onAddBillButtonClick(ActionEvent event) {
        ValueTable2.setCurrentStock(Integer.parseInt(QuantityLabel.getText()));
        ValueTable2.setPrice(ValueTable1.getPrice() * ValueTable2.getCurrentStock());
        ValueTable1.setCurrentStock(ValueTable1.currentStock-Integer.parseInt(QuantityLabel.getText()));
        list2.add(ValueTable2);
        billing_table.setItems(list2);
        table1.refresh();
    }

    ObservableList<Medicine> list2 = FXCollections.observableArrayList(
//            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004"),
//            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004")


    );

    public static <T> Callback<TableColumn<T, Void>, TableCell<T, Void>> indexCellFactory() {
        return t -> new TableCell<T, Void>() {

            @Override
            public void updateIndex(int i) {
                super.updateIndex(i);
                setText(isEmpty() ? "" : Integer.toString(i));
            }

        };
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                ValueTable2 = newValue.copy();
                ValueTable1 = newValue;

            }
        });

        IDColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("_id"));
        ExpiryColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiry"));
        NameColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
        TypeColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        ManufactureColumn1.setCellValueFactory(new PropertyValueFactory<Medicine, String>("manufacturer"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("currentStock"));
        table1.setItems(mongodb.fetchData());

        IDColumn2.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("_id"));
        NameColumn2.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        PriceColumn2.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
        QuantityColumn2.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("currentStock"));
        TableColumn<Medicine, Void> indexColumn = new TableColumn<>("Row index");




//        SerialNoColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Medicine, String> medicineStringCellDataFeatures) {
//                return new ReadOnlyObjectWrapper<>(billing_table.getItems().indexOf(medicineStringCellDataFeatures.getValue()) + "");
//            }});
//        Seria.setCellValueFactory(indexCellFactory());






    }

}