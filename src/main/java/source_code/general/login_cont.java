package source_code.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class login_cont implements Initializable {

    @FXML
    private Button b1;

    @FXML
    private PasswordField p1;

    @FXML
     public TextField u1;

public  static String s1;
    @FXML
    void login(ActionEvent event) {

    }
public void get (ActionEvent e){
        s1=  u1.getText();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
