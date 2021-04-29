/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tuneasy.entities.Product;
import tuneasy.services.CategoryService;
import tuneasy.services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField subtitleTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button submitButton;

    String name, description, subtitle, image;
    int category;
    double prix;

    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    int idProduct = productService.idProduct;
    boolean selected = productService.selected;
    @FXML
    private ComboBox<String> categoryComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (selected) {
            Product product = productService.findById(idProduct);
            nameTextField.setText(product.getName());
            descriptionTextField.setText(product.getDescription());
            subtitleTextField.setText(product.getSubtitle());
            imageTextField.setText(product.getImage());
            //  categoryTextField.setText("" + product.getIdCategory());
            prixTextField.setText("" + product.getPrix());
        } //else {
            categoryComboBox.setItems(categoryService.showAllCategory());
   //     }
        submitButton.setOnAction((ActionEvent event) -> {
            name = nameTextField.getText();
            description = descriptionTextField.getText();
            subtitle = subtitleTextField.getText();
            image = imageTextField.getText();
            category = categoryService.findByName(categoryComboBox.getValue());
            prix = Double.parseDouble(prixTextField.getText());
            Product product = new Product(name, image, subtitle, description, prix, category);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (!selected) {
                productService.addProduct(product);

                alert.setTitle("Product added");
                alert.setHeaderText(null);
                alert.setContentText("Ypur product has been successfully added!");

            } else {
                productService.updateProduct(idProduct, product);
                productService.selected = false;
                alert.setTitle("Product updated");
                alert.setHeaderText(null);
                alert.setContentText("Ypur product has been successfully updated!");
            }
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

}
