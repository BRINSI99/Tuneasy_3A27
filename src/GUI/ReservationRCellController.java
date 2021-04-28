/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationR;
import Entities.Restaurant;
import Service.ReservationRService;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class ReservationRCellController extends ListCell<ReservationR> {

    @FXML
    private GridPane gridpane;
    @FXML
    private Label id_plat;
    @FXML
    private Label id_client;
    @FXML
    private TextField qte;
    @FXML
    private DatePicker date;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_mod;
private FXMLLoader mLLoader;
    

@Override
    protected void updateItem(ReservationR r, boolean empty)  {
        super.updateItem(r, empty);
        
        

        if(empty || r == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ReservationRCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            id_plat.setText(r.getNomPlat());
            //id_client.setText(String.valueOf(r.getId_client()));
            qte.setText(String.valueOf(r.getQuantity()));
            java.sql.Date deadlineDatePrompt = r.getDate_reservation();
            date.setValue(deadlineDatePrompt.toLocalDate());

            
           
            
           btn_supp.setOnAction(e->{
               
        
        ReservationRService ser = new ReservationRService();
        
     try{   
      ser.delete(r);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             

            
            });
           btn_mod.setOnAction(e->{
               
               ReservationR x = new ReservationR();
          x.setId_client(r.getId_client());
          x.setId_plat(r.getId_client());
          x.setId_res_plat(r.getId_res_plat());
       
          x.setQuantity(Integer.parseInt(qte.getText()));
          x.setDate_reservation(Date.valueOf(eventDate()));
          
          ReservationRService ser = new ReservationRService();
          
                  
            try {
                ser.update(x);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationRCellController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
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
