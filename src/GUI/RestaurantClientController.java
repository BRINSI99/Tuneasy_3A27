/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Restaurant;
import Service.RestaurantService;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class RestaurantClientController implements Initializable {

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
    private ListView<Restaurant> eventList;
    @FXML
    private Button btn_admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                
                eventList.setItems(loadE());
                eventList.setCellFactory(restaurantsCellController -> new RestoCellController());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }    
    
    public ObservableList<Restaurant> loadE() throws SQLException {
         
        RestaurantService ser = new RestaurantService();
        ResultSet rs = ser.getRestaurant();
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Restaurant(
                  
                    rs.getInt("id_resto"),
                    rs.getString("nom"),
                    rs.getString("adresse"),
                    rs.getString("ville"),
                    rs.getString("description"),
                    rs.getString("type"),
                    rs.getString("photo"),
                    rs.getInt("num_tel"),
                    rs.getString("email"),
                    rs.getInt("note")
            ));
                    
            
           
            
             
        }
        eventList.setItems(e);
        return e;
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
    private void adminpress(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_admin.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des restaurants");
        stage.show();
    
    }

  
    
}
