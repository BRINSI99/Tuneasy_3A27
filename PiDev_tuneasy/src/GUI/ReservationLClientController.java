/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationL;

import Service.ReservationLService;

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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationLClientController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ListView<ReservationL> eventList;
    @FXML
    private Button btn_gest_res;
    @FXML
    private Button btn_gest_loge;
    @FXML
    private Button btnLogements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                
                eventList.setItems(loadE());
                eventList.setCellFactory(ReservationLCellController -> new ReservationLCellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
    }

 public ObservableList<ReservationL> loadE() throws SQLException {
         
        ReservationLService ser = new ReservationLService();
        ResultSet rs = ser.getReservationL();
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new ReservationL(
                  
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getFloat(3),
                    rs.getDate(4), 
                    rs.getDate(5)
                    
            )
                    
            );
                    
            
            
             
        }
        eventList.setItems(e);
        return e;
    }     

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
           Stage stage = new Stage();
        stage = (Stage) btnLogements.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("LogementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Logements");
        stage.show();
    }

    @FXML
    private void pressButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_loge.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Logement.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
        
    }

     @FXML
    private void Reload(ActionEvent event) throws SQLException {
        loadE();
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationL.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
        
    }
    
}
