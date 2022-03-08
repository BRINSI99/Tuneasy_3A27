/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Logement;


import Service.LogementService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
//import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.stage.FileChooser;

/*mport java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.nio.file.StandardCopyOption;*/






//import java.io.File;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LogementController implements Initializable {
    File file;

    String Auxi;
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
    private Button btn_client;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Logement> eventTable;
    @FXML
    private TableColumn<Logement, String> titre;
    @FXML
    private TableColumn<Logement, String> description;
    @FXML
    private TableColumn<Logement, String> type;
    @FXML
    private TableColumn<Logement, String> adresse;
    @FXML
    private TableColumn<Logement, String> ville;
    @FXML
    private TableColumn<Logement, Integer> n_chambres;
    @FXML
    private TableColumn<Logement, Integer> n_lits;
    @FXML
    private TableColumn<Logement, Integer> n_salles_de_bains;
    @FXML
    private TableColumn<Logement, Integer> id_resto;
    @FXML
    private TableColumn<Logement, Float> prix_nuit;
    @FXML
    private TableColumn<Logement, Integer> animaux_acceptes;
    @FXML
    private TableColumn<Logement, Integer> valide;
    @FXML
    private TableColumn<Logement, Integer> fumeur;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfn_chambres;
    @FXML
    private TextField tfn_lits;
    @FXML
    private TextField tfn_salles_de_bains;
    @FXML
    private TextField tfprix_nuit;
    @FXML
    private ImageView img;
    @FXML
    private Button image;
    @FXML
    private Button btnAjouterResto;
    @FXML
    private Button btn_gest_res;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tfanimaux_acceptes;
    @FXML
    private TextField tffumeur;
    @FXML
    private TableColumn<Logement, String> file_name;
    @FXML
    private TableColumn<Logement, Integer> id;
    public static final String ACCOUNT_SID = "ACdc7ba4f48ffd28d5268527f966a34b50";
    public static final String AUTH_TOKEN = "555c07c750bdd6cae7f00857577e876a";
    @FXML
    private Button btn_mes_res;
    private Object Files;

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
                    rs.getInt("fumeur")
                    
                 
            ));
                    
            
           
            
             
        }
        eventTable.setItems(e);
        return e;
    }
    @FXML
    private void Clientbtn(ActionEvent event) throws IOException  {
        
         Stage stage = new Stage();
        stage = (Stage) btn_client.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("LogementClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Logements");
        stage.show();
    }

    @FXML
    private void onEdit(MouseEvent event) {
          // check the table's selected item and get selected item
    Logement l = new Logement();
    if (eventTable.getSelectionModel().getSelectedItem() != null) {
        l = eventTable.getSelectionModel().getSelectedItem();
        tftitre.setText(l.getTitre());
        tfdescription.setText(l.getDescription());
        tftype.setText(l.getType_logement());
        tfadresse.setText(l.getAdresse());
        tfville.setText(l.getVille());
         //File filex = new File(l.getFile_name());
        //  Image image = new Image(filex.toString());
        //  img.setImage(image);
        File filex = new File(l.getFile_name());
          Image image = new Image("file:/C:/wamp64/www/tuneasy/public/upload/img/" +filex.toString());
          img.setImage(image);
        tfn_chambres.setText(String.valueOf(l.getN_chambres()));
        tfn_lits.setText(String.valueOf(l.getN_lits()));
        tfn_salles_de_bains.setText(String.valueOf(l.getN_salles_de_bains()));
        tfprix_nuit.setText(String.valueOf(l.getPrix_nuit()));
        tfanimaux_acceptes.setText(String.valueOf(l.getAnimaux_acceptes()));
        tffumeur.setText(String.valueOf(l.getFumeur()));
        
        //tfphotoR.setText(resto.getPhoto());
        
        
       
        
        
    
    
     }
    }
     private Path to;
    private Path from;
    private File selectedFile;
 
    @FXML
   
   private void imageAdd(ActionEvent event) throws IOException  {
        FileChooser fileChooserr = new FileChooser(); 
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
      
            
           
    }
  
    
    @FXML
    private void ModifierR(ActionEvent event) throws SQLException{
        Logement loge = eventTable.getSelectionModel().getSelectedItem();
      
         Logement l = new Logement();
        l.setId(loge.getId());
        l.setTitre(tftitre.getText());
        l.setDescription(tfdescription.getText());
        l.setType_logement(tftype.getText());
        l.setAdresse(tfadresse.getText());
        l.setVille(tfville.getText());
        File filex = new File(loge.getFile_name());
         Image image = new Image(filex.toString());
          img.setImage(image);
         l.setFile_name(file.toURI().toString());
          
        l.setN_chambres(Integer.parseInt(tfn_chambres.getText()));
        l.setN_lits(Integer.parseInt(tfn_lits.getText()));
        l.setN_salles_de_bains(Integer.parseInt(tfn_salles_de_bains.getText()));
        l.setPrix_nuit(Float.parseFloat(tfprix_nuit.getText()));
        l.setAnimaux_acceptes(Integer.parseInt(tfanimaux_acceptes.getText()));
        l.setFumeur(Integer.parseInt(tffumeur.getText()));
        l.setFile_name(file.toURI().toString());
          //r.setPhoto(tfphotoR.getText());
         
          
          LogementService ser = new LogementService();
          
                  
            try {
                ser.update(l);
            } catch (SQLException ex) {
                Logger.getLogger(LogementController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          tftitre.setText("");
          tfdescription.setText("");
          tftype.setText("");
          tfadresse.setText("");
          tfville.setText("");
          tfn_chambres.setText("");
          tfn_lits.setText("");
          tfn_salles_de_bains.setText("");
          tfprix_nuit.setText("");
          tfanimaux_acceptes.setText("");
          tffumeur.setText("");
          
     
     loadE();
    }

    @FXML
    private void AjouterR(ActionEvent event) throws SQLException {
        
        Logement l = new Logement();
        l.setTitre(tftitre.getText());
        l.setDescription(tfdescription.getText());
        l.setType_logement(tftype.getText());
        l.setAdresse(tfadresse.getText());
        l.setVille(tfville.getText());
        l.setN_chambres(Integer.parseInt(tfn_chambres.getText()));
        l.setN_lits(Integer.parseInt(tfn_lits.getText()));
        l.setN_salles_de_bains(Integer.parseInt(tfn_salles_de_bains.getText()));
        l.setPrix_nuit(Float.parseFloat(tfprix_nuit.getText()));
        l.setAnimaux_acceptes(Integer.parseInt(tfanimaux_acceptes.getText()));
        l.setFumeur(Integer.parseInt(tffumeur.getText()));
        l.setFile_name(file.toURI().toString());
        // l.setFile_name(Auxi);
        l.setId_client(1); 
        
        
          
          new LogementService().ajouter(l);
          tftitre.setText("");
          tfdescription.setText("");
          tftype.setText("");
          tfadresse.setText("");
          tfville.setText("");
          tfn_chambres.setText("");
          tfn_lits.setText("");
          tfn_salles_de_bains.setText("");
          tfprix_nuit.setText("");
          tfanimaux_acceptes.setText("");
          tffumeur.setText("");
          
           Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21626553014"), new PhoneNumber("+14158910369"), "un nouveau logement a ete ajoute  ").create();
        System.out.println(message.getSid());
          
          
          loadE();  
           
         JOptionPane.showMessageDialog(null, "Sms sent !!!!!!!!!");
        
    }

    @FXML
    private void SupprimerR(ActionEvent event)  throws SQLException {
         Logement l = eventTable.getSelectionModel().getSelectedItem();
        
        LogementService ser = new LogementService();
        
     try{   
      ser.delete(l);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             
            loadE(); 
    }
    
    private boolean validatePrix(){
        Pattern p = Pattern.compile(">0.00f");
        Matcher m = p.matcher(tfprix_nuit.getText());
       // System.out.println(tfemailR.getText() +" : "+ m.matches());
        if(m.matches()){
        return true;
        }
        else{ 

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Prix  doit etre >0 ");
        alert.showAndWait();

     return false;
        }
    }

    @FXML
    private void press(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationL.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
    }

    @FXML
    private void pressButton(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        stage = (Stage) btn_mes_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationLClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
    }
    
     private boolean validateFields(){
    if(tftitre.getText().isEmpty() | tfadresse.getText().isEmpty() |
          tfville.getText().isEmpty() |tftype.getText().isEmpty() | tfn_chambres.getText().isEmpty()| tfn_lits.getText().isEmpty()|  tfn_salles_de_bains.getText().isEmpty()| tfprix_nuit.getText().isEmpty()|animaux_acceptes.getText().isEmpty()||tffumeur.getText().isEmpty() 
            ){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Tous les champs sont obligatoires");
    alert.showAndWait();
    }
    
    return false;
    }
    
}
