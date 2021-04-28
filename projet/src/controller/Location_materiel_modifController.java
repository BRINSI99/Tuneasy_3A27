/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.*;
import services.*;
import controller.*;
import java.io.IOException;
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
public class Location_materiel_modifController implements Initializable {

    Location_materielController lmc = new Location_materielController();
    location_materiel lm = new location_materiel();
    
    @FXML
    private TextField tf_location_materiel_total;
    @FXML
    private Button btn_modifier_total;
    @FXML
    private Label label_location_materiel_ajouter;
    @FXML
    private Label get_materiel_id;
    @FXML
    private Button btn_delete_location;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        get_materiel_id.setVisible(false);
    }    
    public void setlabel_location_materiel_ajouter(String label_location_materiel_ajouter) {
        this.label_location_materiel_ajouter.setText(label_location_materiel_ajouter);
    }

    public void settf_location_materiel_total(String tf_location_materiel_total) {
        this.tf_location_materiel_total.setText(tf_location_materiel_total);
    }

    public void setMaterielId(String get_materiel_id) {
        this.get_materiel_id.setText(get_materiel_id);
    }

    private void clearAll() {
        tf_location_materiel_total.setText("");
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        if (tf_location_materiel_total.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir tous les champs");

        } else if (!tf_location_materiel_total.getText().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir un total valide");
        } else {
            location_materielCRUD lmc = new location_materielCRUD();
            location_materiel lm = new location_materiel();
            float total = Float.valueOf(tf_location_materiel_total.getText());
            int id = Integer.valueOf(get_materiel_id.getText());
            lm.setTotal_location(total);
            lm.setMateriel_id(Integer.valueOf(get_materiel_id.getText()));

            lmc.update(id, total);
            clearAll();
            // initTable();

            Image img = new Image("/img/ok.png");
            Notifications notifAdd = Notifications.create()
                    .title("modifie avec succes")
                    .text("modifie avec sucees")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notifAdd.show();
            returnn();
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException{
        lmc.lmc.delete((int) lmc.lmc.getIdLocMat(Integer.valueOf(get_materiel_id.getText())));
        System.out.println(lmc.lmc.getIdLocMat(Integer.valueOf(get_materiel_id.getText())));
        clearAll();
        returnn();
    }
    private void returnn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/materiel.fxml"));
            Parent root = loader.load();

            tf_location_materiel_total.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
