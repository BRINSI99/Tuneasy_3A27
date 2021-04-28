/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.materiel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import services.materielCRUD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tools.MyConnection;
import services.*;




/**
 * FXML Controller class
 *
 * @author asus
 */
public class BackmaterielController implements Initializable {
    
    materiel m = new materiel();
    materielCRUD mc = new materielCRUD();
    location_materielCRUD lmc = new location_materielCRUD();


    @FXML
    private TableColumn<materiel, String> col_nom_materiel;
    @FXML
    private TableColumn<materiel, String> col_description_materiel;
    ObservableList<materiel> oblistmateriel = FXCollections.observableArrayList();
    private Connection con;
    @FXML
    private Button PDF_materiel;
    @FXML
    private TableView<materiel> tableMateriel;
    @FXML
    private TableColumn<materiel, Float> col_prix_materiel;
    @FXML
    private TableColumn<materiel, String> col_photo_materiel;
    @FXML
    private TableColumn<materiel, String> col_location;
    @FXML
    private Button EXCEL_materiel;
    
    public BackmaterielController() 
    {
    con = MyConnection.getInstance().getCnx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    

    @FXML
    private void handleAction(MouseEvent event) throws SQLException {
        materiel m = tableMateriel.getSelectionModel().getSelectedItem();

    }
    
    private void initTable() {
        try {
            oblistmateriel = (ObservableList<materiel>) mc.readAllmateriel();
            col_nom_materiel.setCellValueFactory(new PropertyValueFactory<>("nom_materiel"));
            col_description_materiel.setCellValueFactory(new PropertyValueFactory<>("description_materiel"));
            col_prix_materiel.setCellValueFactory(new PropertyValueFactory<>("prix_materiel"));
            col_photo_materiel.setCellValueFactory(new PropertyValueFactory<>("photo_materiel"));
            col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
            
            tableMateriel.setItems(oblistmateriel);

        } catch (SQLException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PDF(ActionEvent event) {
        try {
            materielCRUD es = new materielCRUD();
            materiel panier = new materiel();

            String file_name = "C:\\Users\\asus\\Documents\\NetBeansProjects\\projet\\Materiel.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph p = new Paragraph("  ");
            

            
//            
            document.add(p);
            document.add(p);
            document.add(p);
            
            document.add(p);
            document.add(p);
            document.add(p);

            Connection connexion = MyConnection.getInstance().getCnx();
            PreparedStatement psF = null;
            ResultSet rsF = null;

            PreparedStatement psP = null;
            ResultSet rsP = null;

            PreparedStatement psC = null;
            ResultSet rsC = null;

            String queryF = "select * from materiel";

            psF = (PreparedStatement) connexion.prepareStatement(queryF);
            rsF = psF.executeQuery();
            int i = 1;
            int j = 1;
            int k = 1;
            materielCRUD ser = new materielCRUD();
            while (rsF.next()) {
                Paragraph p2 = new Paragraph("Materiel n°" + i);
                document.add(p2);

                Paragraph para = new Paragraph("Materiel nom : " + rsF.getString("nom_materiel") + " \nDescription   :" + rsF.getString("description_materiel") + " \nPrix   :" + rsF.getFloat("prix_materiel") +" " + "TND" );
                document.add(para);
                int idForum = rsF.getInt("id");
                document.add(new Paragraph("  "));
                j = 1;

                i++;
            }

            Paragraph parrr = new Paragraph("________________________________");
            parrr.setAlignment(Element.ALIGN_CENTER);
            document.add(parrr);

//          
            document.close();
            System.out.println("PDF cree avec succes");
            
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void EXCEL(ActionEvent event) throws IOException, SQLException {/*
              
        materielCRUD es = new materielCRUD();
        materiel panier = new materiel();
        
               File file = new File("exemple.xls");
               WritableWorkbook workbook = Workbook.createWorkbook(file);
               WritableSheet sheet = workbook.createSheet("Materiel", 0);
               
               Connection connexion = MyConnection.getInstance().getCnx();
               
               String queryF = "select * from materiel";
               
               PreparedStatement psF = null;
            ResultSet rsF = null;
            
                psF = (PreparedStatement) connexion.prepareStatement(queryF);
            rsF = psF.executeQuery();
            int i = 1;
            int j = 1;
            int k = 1;
            materielCRUD ser = new materielCRUD();
            while (rsF.next()) {
                Label p2 = new Label("Materiel n°" + i);
                sheet.addCell(p2);

                Paragraph para = new Paragraph("Materiel nom : " + rsF.getString("nom_materiel") + " \nDescription   :" + rsF.getString("description_materiel") + " \nPrix   :" + rsF.getFloat("prix_materiel") +" " + "TND" );
                sheet.addCell(para);
                int idForum = rsF.getInt("id");
                
                j = 1;

                i++;
            }
               
            
   */ }
    
}
