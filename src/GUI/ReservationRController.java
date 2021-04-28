/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReservationR;
import Service.ReservationRService;
import java.io.IOException;
import java.net.Authenticator;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class ReservationRController implements Initializable {

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
    private ImageView img;
    @FXML
    private TableView<ReservationR> eventTable;
    @FXML
    private TableColumn<ReservationR, Integer> id_res_plat;
    @FXML
    private TableColumn<ReservationR, Integer> id_client;
    @FXML
    private TableColumn<ReservationR, Integer> id_plat;
    @FXML
    private TableColumn<ReservationR, Integer> quantity;
    @FXML
    private TableColumn<ReservationR, Integer> date_reservation;
    @FXML
    private Button btn_gest_plat;
    @FXML
    private Button btn_gest_resto;
    @FXML
    private TableColumn<ReservationR, Boolean> etat;
    @FXML
    private Button btnStat;
    @FXML
    private TableColumn<ReservationR, String> nomPlat;
    @FXML
    private TableColumn<ReservationR, String> emailC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            id_res_plat.setCellValueFactory(new PropertyValueFactory<>("id_res_plat"));
            id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            id_plat.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
             date_reservation.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            nomPlat.setCellValueFactory(new PropertyValueFactory<>("NomPlat"));
            emailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
            
        }catch (NullPointerException e){
            System.out.println("problem de connection = "+e.getMessage());
        }

        finally{
            try {
                eventTable.setItems(loadE());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
public ObservableList<ReservationR> loadE() throws SQLException {
         
        ReservationRService ser = new ReservationRService();
        ResultSet rs = ser.getReservationR7();
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new ReservationR(
                  
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getDate(5),
                    rs.getBoolean(6),
                    rs.getString(7),
                    rs.getString(8)
            )
                    
            );
                    
            
            
             
        }
        eventTable.setItems(e);
        return e;
    } 
    
    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
         ReservationR p = eventTable.getSelectionModel().getSelectedItem();
        
       ReservationRService ser = new ReservationRService();
        
     try{   
      ser.delete(p);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             
            loadE(); 
        
    }
    
    @FXML
    public void press(ActionEvent event) throws Exception {               
    Stage stage = new Stage();
        stage = (Stage) btn_gest_plat.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Plat.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des plats");
        stage.show();
}
    @FXML
    public void pressButton(ActionEvent event) throws Exception {               
    Stage stage = new Stage();
        stage = (Stage) btn_gest_resto.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des Restaurants");
        stage.show();
}
    
        public static void sendMail(String recepient,String nom,String nomp,int qt) throws Exception {
         System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
//Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "tuneasy.restaurants@gmail.com";
        //Your gmail password
        String password = "Tun.rest.3006";
       
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
                
               });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient,nom,nomp,qt);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
        
        private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String nom,String nomp ,int qt) throws AddressException, MessagingException
    {
    
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation Email ");
            String htmlCode = "<h1><h3>Reservation</h3>\n" +
                               "\n" +
                               "<h4>Salut "+nom+"</h4>\n" +
                               "\n" +
                               "<p>Votre reservation de "+qt+" : "+nomp+" a ete comfirmee</p>\n" +
                                "\n" +
                                "<h4>Merci Davoir utiliser tuneasy</h4>";
            message.setContent(htmlCode, "text/html");
            
            return message;
      
   
    }

    @FXML
    private void sendEmail(ActionEvent event) throws Exception {
        
        ReservationR r = eventTable.getSelectionModel().getSelectedItem();
        int idc = r.getId_client();
        int idr = r.getId_res_plat();
        int idp = r.getId_plat();
        int qt=r.getQuantity();
        String email = "";
        String nom = "";
        String nomp = "";
        
        ReservationRService ser = new ReservationRService();
        ResultSet rs =ser.getUserR(idc);
        ResultSet rs2 =ser.getPlt(idp);
        
        
        while (rs.next())
       {
         email = rs.getString(1);
         nom = rs.getString(2);
      
        }
        while (rs2.next())
       {
         
         nomp = rs2.getString(1);
      
        }
        
         //System.out.println(email+"///"+nom+"///"+nomp);
        sendMail(email,nom,nomp,qt);
        
       
        ser.updateEtat(r);
        loadE();
       
    }

    @FXML
    private void gotoStat(ActionEvent event) throws IOException {
        /*Stage stage = new Stage();
        stage = (Stage) btnStat.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Statestique.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Stat");
        stage.show();*/
        Stage stage = new Stage();
                
                
                Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Statestique.fxml"));
                    
                        root = (Parent) loader.load();
                        Scene scene =new Scene(root);
                    
                    
        
        
                    stage.setScene(scene);
                    stage.setTitle("Stat");
                    stage.show();
                    
    }
        
        

}
