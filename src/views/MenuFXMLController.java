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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tuneasy.services.UserService;
import tuneasy.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private Button productsNavigationButton;
    @FXML
    private Button orderNavigationButton;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Button signoutButton;
    @FXML
    private AnchorPane rootPane;

    UserService userService = new UserService();
    @FXML
    private Button categoryNavigationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fullNameLabel.setText(userService.findUserById(LoginSession.idLoggedUser));
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProductsFXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        productsNavigationButton.setOnAction((t) -> {
            navigate("Products");
        });
        orderNavigationButton.setOnAction((t) -> {
            navigate("Orders");
        });
        categoryNavigationButton.setOnAction((t)->{
            navigate("Categories");
        });
        signoutButton.setOnAction((t) -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("TunEasy");
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
            stage.close();
            LoginSession.idLoggedUser = 0;
            LoginSession.isLogged = false;
        });
    }

    public void navigate(String panel) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(panel + "FXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
