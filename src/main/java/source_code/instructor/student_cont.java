package source_code.instructor;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import source_code.general.Lab;
import source_code.general.grade;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class student_cont implements Initializable {

    @FXML
    public TextField First;

    @FXML
    public TextField Level;

    @FXML
    public  TextField last;

    @FXML
    public  TextField number;
    @FXML
    public  TextField password;
    @FXML
    public  TextField personal;

    @FXML
    public   TextField phone;

    @FXML
    public TextField uni;

    @FXML
    public Label user;

@FXML
void close(){
    Stage stage = (Stage) uni.getScene().getWindow();
    stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personal.setStyle("-fx-background-color: WHITE ");
        First.setStyle("-fx-background-color: WHITE ");
        last.setStyle("-fx-background-color: WHITE ");
        Level.setStyle("-fx-background-color: WHITE ");
        number.setStyle("-fx-background-color: WHITE ");
         phone.setStyle("-fx-background-color: WHITE ");
        uni.setStyle("-fx-background-color: WHITE ");
        personal.setEditable(false);
        First.setEditable(false);
        last.setEditable(false);
        Level.setEditable(false);
        number.setEditable(false);
         phone.setEditable(false);
        uni.setEditable(false);

    }
}
