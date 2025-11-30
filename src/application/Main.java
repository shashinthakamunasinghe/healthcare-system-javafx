package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	Image icon = new Image("icon.png");
        	primaryStage.getIcons().add(icon);
            Parent root = FXMLLoader.load(getClass().getResource("/views/main_menu.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
            primaryStage.setTitle("Doctor Channeling Center");       
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}