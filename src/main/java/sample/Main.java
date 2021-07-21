package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL loginWindowURL = new File("src/main/resources/LoginWindow.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(loginWindowURL);
        primaryStage.setTitle("TSG Document Analysis");
        primaryStage.setScene(new Scene(root, 267, 242));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
