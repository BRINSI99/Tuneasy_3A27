/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Restaurant;
import java.io.File;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class RestoCellController extends ListCell<Restaurant> {

    @FXML
    private Label nom;
    @FXML
    private ImageView img;
   
    @FXML
    private Label type;
    /*@FXML
    private TextFlow description1;
    @FXML
    private TextArea description1;*/
    
    @FXML
    private Label description;
    @FXML
    private Label adresse;
    @FXML
    private Label num_tel;
    @FXML
    private Label email;
    @FXML
    private Label note;
        private FXMLLoader mLLoader;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button btnPlat;


  @Override
    protected void updateItem(Restaurant restaurants, boolean empty)  {
        super.updateItem(restaurants, empty);
        
        

        if(empty || restaurants == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("RestoCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            note.setText(String.valueOf(restaurants.getNote()));
                        num_tel.setText(String.valueOf(restaurants.getNum_tel()));

            nom.setText(restaurants.getNom());
            //ville.setText(restaurants.getVille());
            type.setText(restaurants.getType());
            description.setText(restaurants.getDescription());
           /* Text text = new Text(restaurants.getDescription());
            description1.getChildren().add(text);*/
           //description1.setText(restaurants.getDescription());
            adresse.setText(restaurants.getAdresse());
            email.setText(restaurants.getEmail());
          
          /*File filex = new File(restaurants.getPhoto());
          Image image = new Image(filex.toString());
          img.setImage(image);*/
            img.setImage(new Image("file:/C:/wamp64/www/tuneasy/public/upload/img/" + restaurants.getPhoto()));
           
            
           btnPlat.setOnAction(e->{
                Stage stage = new Stage();
                
                
                Parent root;
                try {
                    FXMLLoader loader =new FXMLLoader(getClass().getResource("PlatClient.fxml"));
                    root = (Parent) loader.load();
                    Scene scene =new Scene(root);
                    
                    PlatClientController coms=loader.getController();
                    coms.showComs(restaurants.getId_resto());
        
        
                    stage.setScene(scene);
                    stage.setTitle("Plats");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(RestoCellController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RestoCellController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            });

            setText(null);
            setGraphic(gridpane);
            
            
            
      
        }
        

    }

    @FXML
    private void pressPlat(ActionEvent event) {
    }
    
}
