package source_code.head;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import source_code.general.student;
import source_code.general.time;

import java.net.URL;
import java.util.ResourceBundle;

public class section_cont implements Initializable {

    @FXML
    private Button add;

    @FXML
    public TextField cap;

    @FXML
    private Button del;

    @FXML
    public TextField inst;

    @FXML
    public TextField num;

    @FXML
    private Button save;

    @FXML
    private TextField student_num;

    @FXML
    private TableView<student> students;

    @FXML
    private Label stus;

    @FXML
    private TableView<time> times;

    @FXML
    private Button update;
    @FXML
    private TableColumn<student, String> reg_num;
    @FXML
    private TableColumn<student, String> stu_email;
    @FXML
    private TableColumn<student, String> uni_email;
    @FXML
    private TableColumn<student, String> name;
    @FXML
    private TableColumn<student, String> level;
    @FXML
    private TableColumn<student, String> phone;

@FXML
Label stus_num;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cap.setStyle("-fx-background-color: WHITE ");
        inst.setStyle("-fx-background-color: WHITE ");
        num.setStyle("-fx-background-color: WHITE ");



    }
}