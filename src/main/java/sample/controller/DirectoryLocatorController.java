package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import sample.poi.extraction.ReadDirectoryFiles;

import java.io.File;

public class DirectoryLocatorController {
    @FXML
    public AnchorPane anchorPaneId;
    @FXML
    public ToggleGroup radioButtonToggleGroup;
    @FXML
    public RadioButton invoiceFXId;
    @FXML
    public RadioButton KBSFXId;
    @FXML
    public RadioButton CSDFXId;


    public String handleDirectoryContentType() {
        RadioButton button = (RadioButton) radioButtonToggleGroup.getSelectedToggle();
        if(button.getText().contains("Kitchen")){
            return "Kitchen";
        }else if (button.getText().contains("Client")){
            return "Client";
        }else {
            return "Invoice";
        }
    }


    public void handleLocateDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select production log directory");


        File file = chooser.showDialog(anchorPaneId.getScene().getWindow());
        String dirLoc;

        if(file != null){
            dirLoc = file.getAbsolutePath();
            ReadDirectoryFiles readDirectoryFiles = new ReadDirectoryFiles();
            readDirectoryFiles.readDirectoryFiles(dirLoc+"\\", handleDirectoryContentType());

        }else {
            System.out.println("Error choosing directory");
        }
    }





}
