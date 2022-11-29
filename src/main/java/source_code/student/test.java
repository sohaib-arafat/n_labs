package source_code.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import source_code.general.login_cont;

import java.io.IOException;
import java.net.URL;
import java.time.zone.ZoneRulesProvider;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class test implements Initializable {

    @FXML
    private Button pop;
    @FXML
    Stage primaryStage;
    @FXML
    private TextField testo;
    @FXML
    TableView tableView;

    String s;
    @FXML
    public void handle(ActionEvent event) throws IOException {
        /*final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(testo.getText()));
      FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_general/login.fxml"));
        Parent root=l.load( );
        login_cont k= l.getController();
        Scene dialogScene = new Scene(root);
         dialog.setScene(dialogScene);
s="hehe";
k.u1.setText(s);
        dialog.show();*/




    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     }
}
