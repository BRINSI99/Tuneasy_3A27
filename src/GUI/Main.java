package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        Scene scene =new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

 
        
        
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}