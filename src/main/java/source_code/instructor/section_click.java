package source_code.instructor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import source_code.general.time;

public class section_click {

    @FXML
    private TableColumn<time, String> day;

    @FXML
    private TableColumn<time, String> end;

    @FXML
    private GridPane grid;

    @FXML
    public Label sec_num;

    @FXML
    private TableColumn<time, String> start;

    @FXML
    private TableView<time> times;
    void cards(){

    }

}
