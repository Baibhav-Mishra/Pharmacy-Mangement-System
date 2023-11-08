package com.example.pharmacy_mangement_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField newUsername;

    private void errorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void onCreateButtonPress(ActionEvent event) throws IOException {
        String username = newUsername.getText();
        String password = newPassword.getText();
        if (PasswordValidator.isPasswordValid(password)) {
            if(mongodb.addCred(newUsername.getText(), newPassword.getText()))
            {
                SceneSwitch.switchToScene(event, "Login");
            }
            else
            {
                errorDialog("Username already exists", "Please enter a new username");
            }

        } else {
            errorDialog("Invalid Password", "Please enter a valid password");
        }

    }

    @FXML
    void onLoginClicked(ActionEvent event) throws IOException {
        SceneSwitch.switchToScene(event, "Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
