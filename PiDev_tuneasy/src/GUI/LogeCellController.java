/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Logement;
import Entities.ReservationL;

import Service.ReservationLService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LogeCellController extends ListCell<Logement> {

    @FXML
    private GridPane gridpane;
    @FXML
    private Label titre;
    @FXML
    private ImageView img;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Button btnPlat;
    @FXML
    private Label adresse;
    private FXMLLoader mLLoader;
    @FXML
    private DatePicker date_check_out;
    @FXML
    private DatePicker date_check_in;
    @FXML
    private Label n_salles_de_bains;
    @FXML
    private Label n_chambres;
    @FXML
    private Label ville;
    @FXML
    private Label n_lits;

    /**
     * Initializes the controller class.
     */
  
    @Override
    protected void updateItem(Logement restaurants, boolean empty)  {
        
         java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd");

String currentTime = sdf.format(dt);

        super.updateItem(restaurants, empty);
        
        

        if(empty || restaurants == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("LogeCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

     
            titre.setText(restaurants.getTitre());
          
            type.setText(restaurants.getType_logement());
            description.setText(restaurants.getDescription());
           
            adresse.setText(restaurants.getAdresse());
            ville.setText(restaurants.getVille());
            n_chambres.setText(String.valueOf(restaurants.getN_chambres()));
             n_lits.setText(String.valueOf(restaurants.getN_lits()));
              n_salles_de_bains.setText(String.valueOf(restaurants.getN_salles_de_bains()));
            
         //   img.setImage(new Image( restaurants.getFile_name()));
          
             date_check_in.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
             date_check_out.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
           
           
          btnPlat.setOnAction(e->{
             
               ReservationL r = new ReservationL();
               r.setId_client(1); 
           
               r.setLogement_id(restaurants.getId());
        
              r.setPrix_total(11.00f);
           
              r.setDate_check_in(Date.valueOf(eventDate_check_in()));
             r.setDate_check_out(Date.valueOf(eventDate_check_out()));
              
       
           
        
         new ReservationLService().ajouter(r);
         
          
          JOptionPane.showMessageDialog(null, "Reservation effectué!");
          date_check_in.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
        date_check_out.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
       
                
            
            }); 

            setText(null);
            setGraphic(gridpane);
            
            
            
      
        }
        
    }
    
    
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pressPlat(ActionEvent event) {
        
    }
    
     public String eventDate_check_in(){
        LocalDate date1 =  date_check_in.getValue();
       
        int a =  date1.getDayOfMonth();
        
       int b = date1.getYear();
       int c = date1.getMonthValue();
        
             
      
           
        return b+"-"+c+"-"+a ;
    
    }
       public String eventDate_check_out(){
        LocalDate date1 =  date_check_out.getValue();
       
        int a =  date1.getDayOfMonth();
        
       int b = date1.getYear();
       int c = date1.getMonthValue();
        
             
      
           
        return b+"-"+c+"-"+a ;
    
    }
    
}
