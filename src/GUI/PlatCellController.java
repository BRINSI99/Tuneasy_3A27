/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Plat;
import Entities.ReservationR;
import Service.ReservationRService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class PlatCellController extends ListCell<Plat> {

    @FXML
    private GridPane gridpane;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label composition;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    private FXMLLoader mLLoader;
    @FXML
    private TextField qte;
    @FXML
    private DatePicker date;
    @FXML
    private Button reserverbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    protected void updateItem(Plat plats, boolean empty)  {
        java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd");

String currentTime = sdf.format(dt);
        
        
       
      
        super.updateItem(plats, empty);

        if(empty || plats == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("PlatCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            prix.setText(String.valueOf(plats.getPrix()));
            nom.setText(plats.getNom());
            composition.setText(plats.getComposition());
            type.setText(plats.getType());
            img.setImage(new Image("file:/C:/wamp64/www/tuneasy/public/upload/img/"+plats.getPhoto()));
                          date.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));

           
            reserverbtn.setOnAction(e->{
              
                
               ReservationR r = new ReservationR();
               r.setId_client(1);
               r.setId_plat(plats.getId_plat());
              r.setQuantity(Integer.parseInt(qte.getText()));
              r.setDate_reservation(Date.valueOf(eventDate()));
               r.setEtat(false);
          
        
          new ReservationRService().ajouter(r);
          
          JOptionPane.showMessageDialog(null, "Reservation effectué!");
          date.setValue(LocalDate.parse(currentTime, DateTimeFormatter.ISO_DATE));
          
          qte.setText("");
            
            });

           

            setText(null);
            setGraphic(gridpane);
            
            
            
      
        }
        

    }
    public String eventDate(){
        LocalDate date1 =  date.getValue();
       
        int a =  date1.getDayOfMonth();
        
       int b = date1.getYear();
       int c = date1.getMonthValue();
        
             
      
           
        return b+"-"+c+"-"+a ;
    
    }
}
