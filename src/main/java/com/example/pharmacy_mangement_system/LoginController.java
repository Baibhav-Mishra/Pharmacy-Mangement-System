package com.example.pharmacy_mangement_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    public Label invalidLabel;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException {
        if(mongodb.verifyCred(userNameField.getText(), passwordField.getText()))
            SceneSwitch.switchToScene(event, "Home");
        invalidLabel.setVisible(true);
    }
    @FXML
    void onSignUpClicked(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Signup");
    }

}
