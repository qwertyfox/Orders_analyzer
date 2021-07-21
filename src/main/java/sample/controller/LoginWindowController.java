package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindowController {
    @FXML
    public PasswordField passwordFieldId;
    @FXML
    public TextField userNameId;
    @FXML
    public Button loginButton;
    @FXML
    public Label labelId;
    @FXML
    public Label warningLabelId;
    @FXML
    public AnchorPane loginWindowId;

    public void login(ActionEvent actionEvent) throws Exception {

        if(userNameId.getText().equals("qwerty") && passwordFieldId.getText().equals("1234")){
            loginWindowId.getScene().getWindow().hide();
            System.out.println("Login successful");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/MainUI.fxml"));
            primaryStage.setTitle("Document Analyser");
            primaryStage.setScene(new Scene(root,334,400));
            primaryStage.show();
        }else {
            warningLabelId.setVisible(true);
            warningLabelId.setText("Login Failed!");
            warningLabelId.setTextFill(Color.RED);
        }
    }
}
