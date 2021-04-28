/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Plat;
import Service.PlatService;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class PlatController implements Initializable {

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
    private TableView<Plat> eventTable;
    @FXML
    private TableColumn<Plat, String> nom;
    @FXML
    private TableColumn<Plat, String> composition;
    @FXML
    private TableColumn<Plat, Float> prix;
    @FXML
    private TableColumn<Plat, String> type;
    @FXML
    private TableColumn<Plat, String> photo;
    @FXML
    private TableColumn<Plat, Integer> id_plat;
    @FXML
    private TableColumn<Plat, Integer> id_resto;
    @FXML
    private TableColumn<Plat, String> nomResto;
    @FXML
    private TextField tfid_restoP;
    @FXML
    private TextField tfnomP;
    @FXML
    private TextField tfcompositionP;
    @FXML
    private TextField tfprixP;
    @FXML
    private TextField tftypeP;
    @FXML
    private ImageView img;
    @FXML
    private Button image;
    @FXML
    private Button btnAjouterPlat;
    File file;
    String Auxi;
    
    @FXML
    private Button btn_gest_res;
    @FXML
    private Button btn_gest_resto;
    @FXML
    private ComboBox<String> restoCombo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        try {

           
            id_plat.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
            id_resto.setCellValueFactory(new PropertyValueFactory<>("id_resto"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            composition.setCellValueFactory(new PropertyValueFactory<>("composition"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
           nomResto.setCellValueFactory(new PropertyValueFactory<>("nomR"));
            
                  PlatService ser = new PlatService();
        ResultSet rs = ser.getnomR();
        while (rs.next()) { 
            restoCombo.getItems().addAll(rs.getString(1)); 
       }
            
            
        }catch (NullPointerException e){
            System.out.println("problem de connection = "+e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PlatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            try {
                eventTable.setItems(loadE());
               // combo.setItems(updateCombo());
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }    
    
    public ObservableList<Plat> loadE() throws SQLException {
         
        PlatService ser = new PlatService();
        ResultSet rs = ser.getPlat2();
        
        ObservableList e = FXCollections.observableArrayList();
        
            while(rs.next()) {
            e.add(new Plat(
                  
                    rs.getInt("id_plat"),
                    rs.getInt("id_resto"),
                    rs.getString("nom"),
                    rs.getString("composition"),
                    rs.getFloat("prix"),
                    rs.getString("type"),
                    rs.getString("photo"),
                    rs.getString(8)
                    
                    
            )
                    
            );
                    
            
            
             
        }
        eventTable.setItems(e);
        return e;
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    private boolean validateFields(){
    if(/*tfid_restoP.getText().isEmpty()*/restoCombo.getSelectionModel().isEmpty() | tfnomP.getText().isEmpty() | tfcompositionP.getText().isEmpty() | tfprixP.getText().isEmpty() |tftypeP.getText().isEmpty()){
    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Tous les champs sont obligatoires");
        alert.showAndWait();
    
    
    return false;
    }
    else return true;
    }
    @FXML
    private void onEdit(MouseEvent event) {
        // check the table's selected item and get selected item
    Plat resto = new Plat();
    if (eventTable.getSelectionModel().getSelectedItem() != null) {
        resto = eventTable.getSelectionModel().getSelectedItem();
        tfid_restoP.setText(String.valueOf(resto.getId_resto()));
        tfnomP.setText(resto.getNom());
        tfcompositionP.setText(resto.getComposition());
        tfprixP.setText(String.valueOf(resto.getPrix()));
        tftypeP.setText(resto.getType());
       
         File filex = new File(resto.getPhoto());
          Image image = new Image("file:/C:/wamp64/www/tuneasy/public/upload/img/" +filex.toString());
          img.setImage(image);
        
          restoCombo.getSelectionModel().select(resto.getNomR());
    
    
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
    private void AjouterR(ActionEvent event) throws SQLException {
        if (validateFields()){
       
        Plat r = new Plat();
        r.setNom(tfnomP.getText());
        r.setComposition(tfcompositionP.getText());
        r.setPrix(Float.parseFloat(tfprixP.getText()));
        r.setType(tftypeP.getText());
        //r.setId_resto(Integer.parseInt(tfid_restoP.getText()));
        r.setPhoto(Auxi);
          
        int x = 0;
        PlatService ser = new PlatService();
        ResultSet rs = ser.getidR(restoCombo.getValue());
         while (rs.next()) { 
            x=rs.getInt(1); 
       }
          r.setId_resto(x);

                  
          new PlatService().ajouter(r);
          tfnomP.setText("");
          tfcompositionP.setText("");
          tfprixP.setText("");
          tftypeP.setText("");
          tfid_restoP.setText("");
          
          
          loadE();
        
        }  
    }

    @FXML
    private void ModifierR(ActionEvent event) throws SQLException {
       
        if (validateFields()){
        Plat resto = eventTable.getSelectionModel().getSelectedItem();
       
         Plat r = new Plat();
          r.setId_plat(resto.getId_plat());
          //r.setId_resto(Integer.parseInt(tfid_restoP.getText()));
          r.setNom(tfnomP.getText());
          r.setComposition(tfcompositionP.getText());         
          r.setPrix(Float.parseFloat(tfprixP.getText()));
          r.setType(tftypeP.getText());
          
          //r.setPhoto(tfphotoR.getText());
         /* File filex = new File(resto.getPhoto());
          Image image = new Image(filex.toString());
          img.setImage(image);*/
          
          r.setPhoto(Auxi);
         
          
         int x = 0;
        PlatService ser2 = new PlatService();
        ResultSet rs = ser2.getidR(restoCombo.getValue());
         while (rs.next()) { 
            x=rs.getInt(1); 
       }
          r.setId_resto(x);
          
          
          PlatService ser = new PlatService();
          
                  
            try {
                ser.update(r);
            } catch (SQLException ex) {
                Logger.getLogger(RestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          tfid_restoP.setText("");
          tfnomP.setText("");
          tfcompositionP.setText("");
          tfprixP.setText("");
         tftypeP.setText("");
          
          
          
       
     loadE();
        }
       }

    @FXML
    private void SupprimerR(ActionEvent event) throws SQLException {
      Plat p = eventTable.getSelectionModel().getSelectedItem();
        
        PlatService ser = new PlatService();
        
     try{   
      ser.delete(p);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "Suppression effectué!!!!!!!!!");
             
            loadE(); 
        
         
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
    
    
}
