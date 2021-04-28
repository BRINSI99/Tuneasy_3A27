/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.location_materiel;
import java.io.IOException;
import services.location_materielCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Location_materielController implements Initializable {

    location_materiel lm = new location_materiel();
    location_materielCRUD lmc = new location_materielCRUD();
    
    @FXML
    private TextField tf_location_total_location;
    @FXML
    private Button btn_ajouter_des_locations;
    @FXML
    private Label label_location_materiel_ajouter;
    @FXML
    private Label get_materiel_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       get_materiel_id.setVisible(false);
    }    

    public void setMaterielId(String get_materiel_id) {
        this.get_materiel_id.setText(get_materiel_id);
    }
    
    public void setlabel_location_materiel_ajouter(String label_location_materiel_ajouter) {
        this.label_location_materiel_ajouter.setText(label_location_materiel_ajouter);
    }

    private void clearAll() {
        tf_location_total_location.setText("");
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (tf_location_total_location.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir tous les champs.");

        } else if (!tf_location_total_location.getText().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un total valide");
        } else {
            location_materielCRUD lmc = new location_materielCRUD();
            location_materiel lm = new location_materiel();
            float total_location = Float.valueOf(tf_location_total_location.getText());
            lm.setTotal_location(total_location);
            lm.setMateriel_id(Integer.valueOf(get_materiel_id.getText()));

            lmc.addLocation_Materiel(lm);
            clearAll();
            // initTable();

            Image img = new Image("/img/ok.png");
            Notifications notifAdd = Notifications.create()
                    .title("ajoute avec succes")
                    .text("enregistre avec sucees")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notifAdd.show();
            returnn();
        }
    }
    
    private void returnn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/materiel.fxml"));
            Parent root = loader.load();

            tf_location_total_location.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
