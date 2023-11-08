package com.example.pharmacy_mangement_system;

import com.itextpdf.kernel.colors.Lab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class    DashBoardController implements Initializable {

    public Label totalLabel;
    @FXML
    private TableColumn<Medicine, String> ExpiryColumn1;

    @FXML
    private TableColumn<Medicine, String> ExpiryColumn2;

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
    private TableColumn<Medicine, String> TypeColumn2;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Medicine, String> ManufactureColumn1;

    @FXML
    private TableColumn<Medicine, String> ManufactureColumn2;


    @FXML
    private TableView<Medicine> table1;



    @FXML
    private TableColumn<Medicine, Integer> IDColumn2;

    @FXML
    private TableColumn<Medicine, String> NameColumn2;

    @FXML
    private TableColumn<Medicine, Double> PriceColumn2;

//    @FXML
//    private TableColumn<Medicine, Double> AmountColumn2;

    @FXML
    private TableColumn<Medicine, Double> QuantityColumn;

    @FXML
    private TableColumn<Medicine, Double> QuantityColumn2;

//    @FXML
//    private TableColumn<Medicine, String> SerialNoColumn2;

    @FXML
    private TableView<Medicine> billing_table;

    @FXML
    Medicine valueTable2, referenceTable1, referenceTable2;
    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

    @FXML
    private TextField QuantityLabel;

    @FXML
    void onEnterSearchField(ActionEvent event) {
        int row_count = 0;

        for(Medicine i: list1)
        {
            if(Objects.equals(searchField.getText(), i.getName()) || Objects.equals(searchField.getText(), String.valueOf(i.get_id())))
            {
                table1.getSelectionModel().select(row_count);
                break;
            }

            row_count++;
        }

//        System.out.println(searchField.getText());
    }
    @FXML
    Alert a = new Alert(Alert.AlertType.NONE);


    double total=0;
    @FXML
    void onAddBillButtonClick(ActionEvent event) {
        int quantity = Integer.parseInt(QuantityLabel.getText());
        //To check if quantity is less than the available stock
        if(quantity > referenceTable1.getCurrentStock())
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Quantity should be less than total stock");
            a.show();
            return;

        }
        // show the dialog

        valueTable2.setCurrentStock(quantity);
//        valueTable2.setPrice(referenceTable1.getPrice() * valueTable2.getCurrentStock());
        total += referenceTable1.getPrice() * valueTable2.getCurrentStock();
        referenceTable1.setCurrentStock(referenceTable1.currentStock-quantity);
        list2.add(valueTable2);
        billing_table.setItems(list2);
        table1.refresh();
        totalLabel.setText("Total: ₹"+total);
    }
    public void undoButtonClick(ActionEvent actionEvent) {
        System.out.println("fuck me");
        referenceTable1.setCurrentStock(valueTable2.getCurrentStock()+ referenceTable1.getCurrentStock());
        total -= valueTable2.getCurrentStock()*valueTable2.getPrice();
        list2.remove(referenceTable2);

        table1.refresh();
        billing_table.refresh();
    }
    public void onGenerateButtonClick(ActionEvent actionEvent) throws FileNotFoundException {
        if(total == 0) return;
        GenerateBill.generateBill(list2,total);
        mongodb.updateCollection(list1);

    }

    ObservableList<Medicine> list2 = FXCollections.observableArrayList(
//            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004"),
//            new Medicine(1021,"Baibhav", 5, 20, "Mishra", "Tablet", "20/10/2004")


    );
    ObservableList<Medicine> list1 = FXCollections.observableArrayList();


//    public static <T> Callback<TableColumn<T, Void>, TableCell<T, Void>> indexCellFactory() {
//        return t -> new TableCell<T, Void>() {
//
//            @Override
//            public void updateIndex(int i) {
//                super.updateIndex(i);
//                setText(isEmpty() ? "" : Integer.toString(i));
//            }
//
//        };
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list1 = mongodb.fetchData();
        table1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                valueTable2 = newValue.copy();
                referenceTable1 = newValue;

            }
        });

        billing_table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                valueTable2 = newValue.copy();
                referenceTable2 = newValue;

            }
        });

        IDColumn1.setCellValueFactory(new PropertyValueFactory<>("_id"));
        ExpiryColumn1.setCellValueFactory(new PropertyValueFactory<>("expiry"));
        NameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));
        TypeColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
        ManufactureColumn1.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("currentStock"));

        table1.setItems(list1);

        IDColumn2.setCellValueFactory(new PropertyValueFactory<>("_id"));
        NameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        QuantityColumn2.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        ManufactureColumn2.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        ExpiryColumn2.setCellValueFactory(new PropertyValueFactory<>("expiry"));
        TypeColumn2.setCellValueFactory(new PropertyValueFactory<>("type"));







//        SerialNoColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Medicine, String> medicineStringCellDataFeatures) {
//                return new ReadOnlyObjectWrapper<>(billing_table.getItems().indexOf(medicineStringCellDataFeatures.getValue()) + "");
//            }});
//        Seria.setCellValueFactory(indexCellFactory());






    }



}