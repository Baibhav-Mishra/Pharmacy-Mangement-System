package com.example.pharmacy_mangement_system;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeController {

    @FXML
    void onAddButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event,"Dashboard");
    }

    @FXML
    void onViewButtonClick(ActionEvent event) {

    }

//    public void onButton1Click(ActionEvent actionEvent) {
//    }
}
