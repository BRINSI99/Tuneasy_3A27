/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Plat;
import Service.PlatService;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class PlatClientController implements Initializable {

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
    private Button btn_gest_res;
    @FXML
    private Button btn_gest_plat;
    @FXML
    private ListView<Plat> eventList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationRClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Mes Reservations");
        stage.show();
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
    public ObservableList<Plat> loadE(int id) throws SQLException {
        // int aux;
         //aux = Integer.parseInt(id_restoo.getText());
        PlatService ser = new PlatService();
        ResultSet rs = ser.getPlatById(id);
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Plat(
                  
                    rs.getInt("id_plat"),
                    rs.getInt("id_resto"),
                    rs.getString("nom"),
                    rs.getString("composition"),
                    rs.getFloat("prix"),
                    rs.getString("type"),
                    rs.getString("photo")
                    
                    
            )
                    
            );
                    
            
            
             
        }
        eventList.setItems(e);
        return e;
    }    
    public void showComs( int id) throws SQLException {
        
        try {
                
                eventList.setItems(loadE(id));
            eventList.setCellFactory(platsListView -> new PlatCellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        
        
        loadE(id);
    }
    
}
