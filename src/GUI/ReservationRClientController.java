/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationR;
import Service.ReservationRService;
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
 * @author BabyViper
 */
public class ReservationRClientController implements Initializable {

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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ListView<ReservationR> eventList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 try {
                
                eventList.setItems(loadE());
                eventList.setCellFactory(reservationsCellController -> new ReservationRCellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }    }  
    
     public ObservableList<ReservationR> loadE() throws SQLException {
         
        ReservationRService ser = new ReservationRService();
        ResultSet rs = ser.getReservationR5(1);
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new ReservationR(
                  
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(5),
                    rs.getDate(6),
                    rs.getString(4)
            )
                    
            );
                    
            
            
             
        }
        eventList.setItems(e);
        return e;
    } 

    @FXML
    private void handleClicks(ActionEvent event) {
    }
@FXML
    private void pressButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btnOrders.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("RestaurantClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Restaurants");
        stage.show();
        
    }
    @FXML
    private void Reload(ActionEvent event) throws SQLException {
        loadE();
    }

    
}
