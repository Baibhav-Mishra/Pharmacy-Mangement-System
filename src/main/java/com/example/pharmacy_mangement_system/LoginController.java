package com.example.pharmacy_mangement_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException {
        System.out.println(userNameField.getText());

        if(CredentialValidator.validate(userNameField.getText(), passwordField.getText()))
            SceneSwitch.switchToScene(event, "Home");

    }
    @FXML
    void onSignUpClicked(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Signup");
    }

}
