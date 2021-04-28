/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Restaurant;
import Service.RestaurantService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BabyViper
 */
public class RestaurantController implements Initializable {
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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Restaurant> eventTable;
    @FXML
    private TableColumn<Restaurant, String> nom;
    @FXML
    private TableColumn<Restaurant, String> adresse;
    @FXML
    private TableColumn<Restaurant, String> ville;
    @FXML
    private TableColumn<Restaurant, String> description;
    @FXML
    private TableColumn<Restaurant, String> Type;
    @FXML
    private TableColumn<Restaurant, String> photo;
    @FXML
    private TableColumn<Restaurant, Integer> num_tel;
    @FXML
    private TableColumn<Restaurant, String> email;
    @FXML
    private TableColumn<Restaurant, Integer> note;
    @FXML
    private TableColumn<Restaurant, Integer> id_resto;
    @FXML
    private TextField tfnomR;
    @FXML
    private TextField tfadresseR;
    @FXML
    private TextField tfvilleR;
    @FXML
    private TextField tfdescriptionR;
    @FXML
    private TextField tftypeR;
    @FXML
    private TextField tfnumtelR;
    @FXML
    private TextField tfemailR;
    @FXML
    private TextField tfnoteR;
    @FXML
    private ImageView img;
    @FXML
    private Button image;
    @FXML
    private Button btnAjouterResto;
    @FXML
    private Button btn_gest_plat;
    @FXML
    private Button btn_gest_res;
    @FXML
    private Button btn_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        
         try {

            id_resto.setCellValueFactory(new PropertyValueFactory<>("id_resto"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
            num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            note.setCellValueFactory(new PropertyValueFactory<>("note"));
            
            
            
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
   
    private boolean validateFields(){
    if(tfnomR.getText().isEmpty() | tfadresseR.getText().isEmpty() |
          tfvilleR.getText().isEmpty() |tfemailR.getText().isEmpty() | tfnoteR.getText().isEmpty()| tfnumtelR.getText().isEmpty()| tfdescriptionR.getText().isEmpty() 
            |tftypeR.getText().isEmpty()){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Tous les champs sont obligatoires");
    alert.showAndWait();
    }
    
    return false;
    }
    
    
    private boolean validateEmail(){
        Pattern p = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher m = p.matcher(tfemailR.getText());
       // System.out.println(tfemailR.getText() +" : "+ m.matches());
        if(m.matches()){
        return true;
        }
        else{ 

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Email non valide");
        alert.showAndWait();

     return false;
        }
    }
    
     private boolean validateNote(){
        Pattern p = Pattern.compile("[0-5]+");
        Matcher m = p.matcher(tfnoteR.getText());
       // System.out.println(tfemailR.getText() +" : "+ m.matches());
        if(m.matches()){
        return true;
        }
        else{ 

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Note doit etre entre 0 et 5 ");
        alert.showAndWait();

     return false;
        }
    }
      private boolean validateNum(){
        Pattern p = Pattern.compile("\\d{8}");
        Matcher m = p.matcher(tfnumtelR.getText());
       // System.out.println(tfemailR.getText() +" : "+ m.matches());
        if(m.matches()){
        return true;
        }
        else{ 

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Verifier le numero de telephone ");
        alert.showAndWait();

     return false;
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
        eventTable.setItems(e);
        return e;
    }
    @FXML
    private void onEdit(MouseEvent event) {
        // check the table's selected item and get selected item
    Restaurant resto = new Restaurant();
    if (eventTable.getSelectionModel().getSelectedItem() != null) {
        resto = eventTable.getSelectionModel().getSelectedItem();
        tfnomR.setText(resto.getNom());
        tfadresseR.setText(resto.getAdresse());
        tfvilleR.setText(resto.getVille());
        tfdescriptionR.setText(resto.getDescription());
        tftypeR.setText(resto.getType());
        //tfphotoR.setText(resto.getPhoto());
        
         File filex = new File(resto.getPhoto());
          Image image = new Image("file:/C:/wamp64/www/tuneasy/public/upload/img/" +filex.toString());
          img.setImage(image);
        tfnumtelR.setText(String.valueOf(resto.getNum_tel()));
        tfemailR.setText(resto.getEmail());
        tfnoteR.setText(String.valueOf(resto.getNote()));
    
    
     }
    }
    private Path to;
    private Path from;
    private File selectedFile;
    @FXML
    private void imageAdd(ActionEvent event) throws IOException {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Auxi = timeStamp+ file.getName();
        
        if (file != null) {
            from = Paths.get(file.toURI());
            
            to = Paths.get("C:/wamp64/www/tuneasy/public/upload/img/" +Auxi);
            // Files.copy(from.toFile(), to.toFile()); //gives a 'cannot resolve method error 
            Files.copy(from, to);
        }
        
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }

    @FXML
    private void ModifierR(ActionEvent event) throws SQLException {
       if (validateFields() | (validateEmail() && validateNote() && validateNum())){
        Restaurant resto = eventTable.getSelectionModel().getSelectedItem();
      
         Restaurant r = new Restaurant();
          r.setId_resto(resto.getId_resto());
          r.setNom(tfnomR.getText());
          r.setAdresse(tfadresseR.getText());         
          r.setVille(tfvilleR.getText());
          r.setDescription(tfdescriptionR.getText());
          r.setType(tftypeR.getText());
          //r.setPhoto(tfphotoR.getText());
         /* File filex = new File(resto.getPhoto());
          Image image = new Image(filex.toString());
          img.setImage(image);*/
         
          r.setPhoto(Auxi);
          
          r.setNum_tel(Integer.parseInt(tfnumtelR.getText()));          
          r.setEmail(tfemailR.getText());
          r.setNote(Integer.parseInt(tfnoteR.getText()));
          
          RestaurantService ser = new RestaurantService();
          
                  
            try {
                ser.update(r);
            } catch (SQLException ex) {
                Logger.getLogger(RestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          tfnomR.setText("");
          tfadresseR.setText("");
          tfvilleR.setText("");
          tfdescriptionR.setText("");
          tftypeR.setText("");
          
          tfvilleR.setText("");
          tfnumtelR.setText("");
          tfemailR.setText("");
          tfnoteR.setText("");
          
     
     loadE();
       }
    }
    @FXML
    private void AjouterR(ActionEvent event) throws SQLException {
        if (validateFields() | (validateEmail() && validateNote() && validateNum())){
        Restaurant r = new Restaurant();
        r.setNom(tfnomR.getText());
        
          r.setAdresse(tfadresseR.getText());
          
          r.setVille(tfvilleR.getText());
          r.setDescription(tfdescriptionR.getText());
          r.setType(tftypeR.getText());
          r.setPhoto(Auxi);
          r.setNum_tel(Integer.parseInt(tfnumtelR.getText()));
          
          r.setEmail(tfemailR.getText());
          r.setNote(Integer.parseInt(tfnoteR.getText()));
          new RestaurantService().ajouter(r);
          tfnomR.setText("");
          tfadresseR.setText("");
          tfvilleR.setText("");
          tfdescriptionR.setText("");
          tftypeR.setText("");
          
          tfnumtelR.setText("");
          tfemailR.setText("");
          tfnoteR.setText("");
          loadE();
        }
    }

    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
        Restaurant r = eventTable.getSelectionModel().getSelectedItem();
        
        RestaurantService ser = new RestaurantService();
        
     try{   
      ser.delete(r);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             
            loadE(); 
    }
    
    @FXML
    public void pressButton(ActionEvent event) throws Exception {               
    Stage stage = new Stage();
        stage = (Stage) btn_gest_plat.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("Plat.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des Plat");
        stage.show();
}
    @FXML
    public void press(ActionEvent event) throws Exception {               
    Stage stage = new Stage();
        stage = (Stage) btn_gest_res.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("ReservationR.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Gestion des reservations");
        stage.show();
}

    @FXML
    private void Clientbtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage = (Stage) btn_client.getScene().getWindow();
        stage.close();


        Parent root =FXMLLoader.load(getClass().getResource("RestaurantClient.fxml"));
        Scene scene =new Scene(root);


        stage.setScene(scene);
        stage.setTitle("Restaurants");
        stage.show();
    }
}
