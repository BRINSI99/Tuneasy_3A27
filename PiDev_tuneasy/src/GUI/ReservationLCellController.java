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
import java.sql.Date; 
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationLCellController extends ListCell<ReservationL> {

    @FXML
    private GridPane gridpane;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_mod;
    private TextField qte;
    private Label id_plat;
    
    private FXMLLoader mLLoader;
    @FXML
    private DatePicker date_check_out;
    @FXML
    private Label id;
    @FXML
    private Label logement_id;
    @FXML
    private DatePicker date_check_in;

    /**
     * Initializes the controller class.
     */
   @Override
    protected void updateItem(ReservationL r, boolean empty)  {
        
         java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd");

String currentTime = sdf.format(dt);
        super.updateItem(r, empty);
        
        

        if(empty || r == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ReservationLCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            id.setText(String.valueOf(r.getId()));
            logement_id.setText(String.valueOf(r.getLogement_id()));
          
         java.sql.Date deadlineDatePrompt = r.getDate_check_in();
          date_check_in.setValue(deadlineDatePrompt.toLocalDate());
             java.sql.Date deadlineDatePromptt = r.getDate_check_out();
            date_check_out.setValue(deadlineDatePromptt.toLocalDate());
          
          
      
            
           
            
           btn_supp.setOnAction(e->{
               
        
        ReservationLService ser = new ReservationLService();
        
     try{   
      ser.delete(r);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             

            
            });
           btn_mod.setOnAction(e->{
               
               ReservationL x = new ReservationL();
          x.setId(r.getId());
         x.setLogement_id(r.getLogement_id());
         x.setPrix_total(15.00f);
       
        //  x.setQuantity(Integer.parseInt(qte.getText()));
          x.setDate_check_in(Date.valueOf(eventDate_check_in()));
            x.setDate_check_out(Date.valueOf(eventDate_check_out()));
          
          ReservationLService ser = new ReservationLService();
          
          
                  
            try {
                ser.update(x);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationLCellController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
//          qte.setText("");
         
          
       JOptionPane.showMessageDialog(null, "Modification effectué!!!!!!!!!");
     
    
     
             });

            setText(null);
            setGraphic(gridpane);
            
            
            
      
        }
        

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
