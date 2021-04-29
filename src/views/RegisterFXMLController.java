/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tuneasy.entities.User;
import tuneasy.services.UserService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField rePasswordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    private TextField roleTextField;

    UserService userService = new UserService();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox<String> roleComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roleComboBox.getItems().addAll("admin", "client");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                navigate();
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkNotNull(firstNameTextField.getText()) && checkNotNull(lastNameTextField.getText()) && checkNotNull(imageTextField.getText()) &&  checkEmail(emailTextField.getText()) && checkPassword(passwordTextField.getText(), rePasswordTextField.getText())) {
                    User user = new User(emailTextField.getText(), passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), imageTextField.getText(), roleComboBox.getValue());
                    userService.signup(user);
                    navigate();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid mail");
                alert.setHeaderText(null);
                alert.setContentText("Ooops, invalid  Inputs!");
                alert.showAndWait();
                }
            }
        });
    }

    public boolean checkNotNull(String name) {
        if (name.length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String password, String rePassword) {
        if (password.length() > 8) {
            if (password.equals(rePassword)) {
                return true;
            } else {
                System.out.println("Password doesn't match");
                return false;
            }
        } else {
            System.out.println("Password is too short");
            return false;
        }
    }

    public boolean checkEmail(String email) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email);
        if (controler.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void navigate() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(RegisterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
