package source_code.instructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class exp_cont implements Initializable {

    @FXML
    private Label exp_name;

    @FXML
    private TextFlow notes;

    @FXML
    private TextFlow object;

    @FXML
    private TextFlow procs;

    @FXML
    private TextFlow tools;
    String number;
    String lab;

void setall(){

}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "Sed euismod tellus id nulla vestibulum, eu vulputate libero ornare.\n" +
                "Mauris sed augue vitae erat aliquam pharetra vel quis eros.\n" +
                "Donec lobortis metus ac varius pellentesque.\n" +
                "Sed nec velit finibus, accumsan lorem id, venenatis lorem.");
        text.setStyle("-fx-font: 15 arial;");
        procs.getChildren().add(text);
    }
}
