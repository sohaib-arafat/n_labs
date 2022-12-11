package source_code.instructor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import source_code.general.student;
import source_code.general.submession;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class grades_cont implements Initializable {

    String inst;
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
    private ComboBox<String> specifier;

    @FXML
    private TextField student;

    @FXML
    private TableColumn<submession, Date> sub_date;

    @FXML
    private TableColumn<submession, String> sub_exp;

    @FXML
    private TableColumn<submession, String> sub_grade;

    @FXML
    private TableColumn<submession, String>  sub_id;

    @FXML
    private TableColumn<submession, String>  sub_lab;

    @FXML
    private TableColumn<submession, String>  sub_section;

    @FXML
    private TableView<submession> submessions1;
void all() throws SQLException {
    ArrayList<submession> submessions = new ArrayList<>();
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="SELECT SECTION.SEC_NUM,SECTION.LAB_NUM FROM SECTION WHERE SECTION.INS_NUM='"+inst+"'";
    Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()){

        String sql2="SELECT SUB_ID,GRADE,EXP_NUM,SUB_DATE FROM SUBMESSION WHERE LAB='"+rs.getString(2)+"' AND SECTION='"+rs.getString(1)+"'";
        Statement stmt2 = con.createStatement();
        java.sql.ResultSet rs2 = stmt2.executeQuery(sql2);

       while (rs2.next()){
            submessions.add(new submession(rs.getString(2),rs.getString(1),rs2.getString(1),rs2.getString(4),rs2.getString(3),rs2.getString(2)));
        }

    }
    con.close();
    ObservableList<submession> lst1 = FXCollections.observableArrayList(submessions);
    submessions1.setItems(lst1);


}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sub_grade.setCellValueFactory(new PropertyValueFactory<submession, String>("grade"));
        sub_exp.setCellValueFactory(new PropertyValueFactory<submession, String>("exp"));
        sub_date.setCellValueFactory(new PropertyValueFactory<submession, Date>("date"));
        sub_id.setCellValueFactory(new PropertyValueFactory<submession, String>("id"));
        sub_lab.setCellValueFactory(new PropertyValueFactory<submession, String>("lab"));
        sub_section.setCellValueFactory(new PropertyValueFactory<submession, String>("section"));

    }
}
