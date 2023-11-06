package com.example.pharmacy_mangement_system;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AlertController {

    @FXML
    void onBackButtonPress(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Home");
    }

}
