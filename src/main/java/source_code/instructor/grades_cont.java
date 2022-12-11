package source_code.instructor;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class grades_cont {

    @FXML
    private TextField general;

    @FXML
    private TextField grade;

    @FXML
    private TextField id;

    @FXML
    private TextField lab;

    @FXML
    private TextField section;

    @FXML
    private ComboBox<?> specifier;

    @FXML
    private TextField student;

    @FXML
    private TableColumn<?, ?> sub_date;

    @FXML
    private TableColumn<?, ?> sub_exp;

    @FXML
    private TableColumn<?, ?> sub_grade;

    @FXML
    private TableColumn<?, ?> sub_id;

    @FXML
    private TableColumn<?, ?> sub_lab;

    @FXML
    private TableColumn<?, ?> sub_section;

    @FXML
    private TableView<?> submessions;

}
