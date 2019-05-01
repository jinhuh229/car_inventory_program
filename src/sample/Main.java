package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_inventory_screen.fxml"));

        Scene scene = new Scene(root);


        primaryStage.setTitle("Car Inventory Program     |     Jin Huh");
        primaryStage.setScene(scene);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
