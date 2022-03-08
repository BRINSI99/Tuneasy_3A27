/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Logement;

import Service.LogementService;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LogementClientController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btn_admin;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btn_gest_res;
    @FXML
    private ListView<Logement> eventList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
                
                eventList.setItems(loadE());
                eventList.setCellFactory(LogeCellController -> new LogeCellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
     
     
     
    }    
    
     public ObservableList<Logement> loadE() throws SQLException {
         
        LogementService ser = new LogementService();
        ResultSet rs = ser.getLogement();
      
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Logement(
                  
                    rs.getInt("id"),
                    rs.getString("file_name"),
                    rs.getString("titre"),
                    rs.getString("description"),
                    rs.getString("type_logement"),
                    rs.getString("adresse"),
                    rs.getString("ville"),
                    
                    rs.getInt("n_chambres"),
                    rs.getInt("n_lits"),
                    rs.getInt("n_salles_de_bains"),
                    rs.getFloat("prix_nuit"),
                    rs.getInt("animaux_acceptes"),
                    rs.getInt("valide"),
                    rs.getInt("fumeur")
            ));
                   
            
          
            
             
        }
        eventList.setItems(e);
        return e;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void adminpress(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btn_admin.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("LogementAdmin.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des logements admin");
        stage.show();
    
        
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationLClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Mes Reservations");
        stage.show();
    }
    
}
