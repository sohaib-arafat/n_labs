package source_code.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import source_code.general.expiremnt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class exp_cont implements Initializable {

    @FXML
    private HBox exp;

    @FXML
    private Label exp_num;

    @FXML
    private Label lab_name;

    @FXML
    private Button main_b;

    public void set_data(expiremnt ex) {
        exp_num.setText(ex.getExp_num());
        lab_name.setText(ex.getExp_lab());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
