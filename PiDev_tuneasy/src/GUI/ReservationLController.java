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
import java.util.Properties;
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
 * @author ASUS
 */
public class ReservationLController implements Initializable {

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
    private TableView<ReservationL> eventTable;
    private TableColumn<ReservationL, Integer> id_reservation;
    @FXML
    private TableColumn<ReservationL, Integer> id_client;
    @FXML
    private TableColumn<ReservationL, Integer> logement_id;
    @FXML
    private TableColumn<ReservationL, Integer> date_check_out;
    @FXML
    private TableColumn<ReservationL, Float> prix_total;
    @FXML
    private Button btnStat;
    @FXML
    private TableColumn<ReservationL, Integer> date_check_in;
    @FXML
    private TableColumn<ReservationL, Integer> id;
    @FXML
    private Button btn_mes_res;
    @FXML
    private Button btn_gest_loge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            logement_id.setCellValueFactory(new PropertyValueFactory<>("logement_id"));
            prix_total.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
             date_check_in.setCellValueFactory(new PropertyValueFactory<>("date_check_in"));
           date_check_out.setCellValueFactory(new PropertyValueFactory<>("date_check_out"));
            
            
            
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
    
    public ObservableList<ReservationL> loadE() throws SQLException {
         
        ReservationLService ser = new ReservationLService();
        ResultSet rs = ser.getReservationL();
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new ReservationL(
                  
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3), 
                    rs.getDate(4),  
                    rs.getDate(5),
                    rs.getFloat(6)
                    
            )
                    
            );
                    
            
            
             
        }
        eventTable.setItems(e);
        return e;
    } 

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
      
        
           Stage stage = new Stage();
        stage = (Stage) btn_mes_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationLClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des plats");
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
        stage.setTitle("Gestion des Logements");
        stage.show();
    }

    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
         ReservationL p = eventTable.getSelectionModel().getSelectedItem();
        
       ReservationLService ser = new ReservationLService();
        
     try{   
      ser.delete(p); 
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             
            loadE(); 
        
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
     

   @FXML
    private void sendEmail(ActionEvent event) throws Exception {
        
        ReservationL r = eventTable.getSelectionModel().getSelectedItem();
        int idc = r.getId_client();
        int idr = r.getId();
        int idp = r.getLogement_id();
        int qt=r.getId();
        String email = "";
        String nom = "";
        String nomp = "";
        
        ReservationLService ser = new ReservationLService();
        ResultSet rs =ser.getUserR(idc);
        ResultSet rs2 =ser.getlg(idp);
        
        
        while (rs.next())
       {
         email = rs.getString(1);
         nom = rs.getString(2);
      
        }
        while (rs2.next())
       {
         
         nomp = rs2.getString(1);
      
        }
        
         
        sendMail(email,nom,nomp,qt);
        
       
   
        loadE();
       
    }

  
        private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String nom,String nomp ,int qt) throws AddressException, MessagingException
    {
     
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation ");
            String htmlCode = "<h1><h3>Reservation d'un logement</h3>\n" +
                               "\n" +
                               "<h4>Salut "+nom+"</h4>\n" +
                               "\n" +
                               "<p>Vous avez ajoute une nouvelle  reservation du logement  "+nomp+" identifiée par  "+qt+" </p>\n" +
                                "\n" +
                                "<h4>Merci Davoir utiliser tuneasy</h4>";
            message.setContent(htmlCode, "text/html");
            
            return message;
      
   
    }
    @FXML
    private void gotoStat(ActionEvent event) throws IOException {
        
       
        Stage stage = new Stage();
                
                
                Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("StatistiqueLogement.fxml"));
                    
                        root = (Parent) loader.load();
                        Scene scene =new Scene(root);
                    
                    
        
        
                    stage.setScene(scene);
                    stage.setTitle("Statistiques des logements");
                    stage.show();
                    
    }
    
}
