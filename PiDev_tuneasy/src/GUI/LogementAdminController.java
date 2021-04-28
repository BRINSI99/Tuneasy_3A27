/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Logement;
import Entities.ReservationL;
import Service.LogementService;
import Service.ReservationLService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
//import javax.swing.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LogementAdminController implements Initializable {

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
    private TableView<Logement> eventTable;
    @FXML
    private TableColumn<Logement, String> titre;
    @FXML
    private TableColumn<Logement, String> description;
    @FXML
    private TableColumn<Logement, String> adresse;
    @FXML
    private TableColumn<Logement, String> ville;
    @FXML
    private TableColumn<Logement, String> file_name;
    @FXML
    private TableColumn<Logement, Integer> n_chambres;
    @FXML
    private TableColumn<Logement, Integer> n_lits; 
    @FXML
    private TableColumn<Logement, Integer> n_salles_de_bains;
    @FXML
    private TableColumn<Logement, Float> prix_nuit;
    @FXML
    private TableColumn<Logement, Integer> animaux_acceptes;
    @FXML
    private TableColumn<Logement, Integer> valide;
    @FXML
    private TableColumn<Logement, Integer> fumeur;
    @FXML
    private TableColumn<Logement, Integer> id;
    @FXML
    private Button btnStat;
    @FXML
    private TableColumn<Logement, String> type;
    @FXML
    private TableColumn<Logement, Integer> id_client;
    @FXML
    private Button btn_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            file_name.setCellValueFactory(new PropertyValueFactory<>("file_name"));
            n_chambres.setCellValueFactory(new PropertyValueFactory<>("n_chambres"));
            n_lits.setCellValueFactory(new PropertyValueFactory<>("n_lits"));
            n_salles_de_bains.setCellValueFactory(new PropertyValueFactory<>("n_salles_de_bains"));
            prix_nuit.setCellValueFactory(new PropertyValueFactory<>("prix_nuit"));
            animaux_acceptes.setCellValueFactory(new PropertyValueFactory<>("animaux_acceptes"));
            valide.setCellValueFactory(new PropertyValueFactory<>("valide"));
            fumeur.setCellValueFactory(new PropertyValueFactory<>("fumeur"));
            id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
          
            
            
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
     public ObservableList<Logement> loadE() throws SQLException {
         
        LogementService ser = new LogementService();
        ResultSet rs = ser.getLogement();
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Logement(
                    
                    
                   
                    rs.getInt("id"),
                    rs.getString("file_name"),
                    rs.getString("titre"), 
                    rs.getString("description"),
                    rs.getString("type_logement"),
                    rs.getString("adresse"),
                    rs.getString("ville"),
                    
                    rs.getInt("n_chambres"),
                    rs.getInt("n_lits"),
                    rs.getInt("n_salles_de_bains"),
                    rs.getFloat("prix_nuit"),
                    rs.getInt("animaux_acceptes"),
                    rs.getInt("valide"),
                    rs.getInt("fumeur"),
                     rs.getInt("id_client")
                    
                 
            ));
                    
            
           
            
             
        }
        eventTable.setItems(e);
        return e;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }


    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
         Logement l = eventTable.getSelectionModel().getSelectedItem();
        
        LogementService ser = new LogementService();
        
     try{   
      ser.delete(l); 
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
        
     Logement r = eventTable.getSelectionModel().getSelectedItem();
        int idc = r.getId_client();
        int idr = r.getId();
        int idp = r.getId();
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
        
         //System.out.println(email+"///"+nom+"///"+nomp);
        sendMail(email,nom,nomp,qt);
        
       
       ser.updateValide(r);
        loadE();
       
    }

  
       private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String nom,String nomp ,int qt) throws AddressException, MessagingException
    {
     
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation Email ");
            String htmlCode = "<h1><h3>Ajout d'un logement</h3>\n" +
                               "\n" +
                               "<h4>Salut "+nom+"</h4>\n" +
                               "\n" +
                               "<p>Votre demande d'ajout  du logement  "+nomp+" identifiée par  "+qt+" a ete comfirmee</p>\n" +
                                "\n" + 
                                "<h4>Merci Davoir utiliser tuneasy</h4>";
            message.setContent(htmlCode, "text/html");
            
            return message;
      
   
    }

    @FXML
    private void gotoStat(ActionEvent event) {
        try {
             LogementService ser = new LogementService();
      
            String file_name = "C:\\Users\\ASUS\\Desktop\\logement.pdf";
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph p = new Paragraph("  ");
            Image img = Image.getInstance("C:\\Users\\ASUS\\Desktop\\PiDev_tuneasy\\src\\images\\logo.png");
            

            img.scaleAbsolute(100, 100);
            img.setAbsolutePosition(450, 630);
            document.add(img);
//            
            document.add(p);
            document.add(p);
            document.add(p);
            
            document.add(p);
            document.add(p);
            document.add(p);

           
            PreparedStatement psF = null;
            ResultSet rsF = null;

            PreparedStatement psP = null;
            ResultSet rsP = null;

            PreparedStatement psC = null;
            ResultSet rsC = null;

            

            
            rsF = ser.getLogement();
            int i = 1;
            int j = 1;
            int k = 1;
           
            while (rsF.next()) {
                Paragraph p2 = new Paragraph("Logement n°" + i);
                document.add(p2);

                Paragraph para = new Paragraph("Le Titre : " + rsF.getString("titre") + " \nLa Description   :" + rsF.getString("description")+ " \n  ________________________________ \n   ");
                document.add(para);
                int idForum = rsF.getInt("id"); 
                document.add(new Paragraph("  "));

               
                j = 1;

                i++;
               
            }

           

//          
            document.close();
            System.out.println("PDF created");
            
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            System.err.println(e);
        }
        
    } 

    @FXML
    private void Clientbtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_client.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("LogementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Logements");
        stage.show();
    }
    
}
