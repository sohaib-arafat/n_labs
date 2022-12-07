package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class users_reg_cont {

    @FXML
    private Button add_stu;

    @FXML
    private TextField addition;

    @FXML
    private TextField email;

    @FXML
    private TextField f_name;

    @FXML
    private TextField general;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<?, ?> ins_email;

    @FXML
    private TableColumn<?, ?> ins_id;

    @FXML
    private TableColumn<?, ?> ins_name;

    @FXML
    private TableColumn<?, ?> ins_office;

    @FXML
    private TableColumn<?, ?> ins_phone;

    @FXML
    private TableView<?> instructor;

    @FXML
    private TextField l_name;

    @FXML
    private TableColumn<?, ?> lab_levelsuper_mail;

    @FXML
    private TextField level;

    @FXML
    private TableColumn<?, ?> level_col;

    @FXML
    private TextField pgone;

    @FXML
    private ComboBox<?> reg_sel;

    @FXML
    private Button spec;

    @FXML
    private TableColumn<?, ?> stu_mail;

    @FXML
    private TableColumn<?, ?> stu_name;

    @FXML
    private TableColumn<?, ?> stu_phone;

    @FXML
    private TableColumn<?, ?> stu_regc;

    @FXML
    private TableColumn<?, ?> stu_uni;

    @FXML
    private TableView<?> student;

    @FXML
    private TextField studentlevel;

    @FXML
    private TextField studentmail;

    @FXML
    private TextField studentname;

    @FXML
    private TextField studentnumber;

    @FXML
    private TextField studentreg;

    @FXML
    private TableColumn<?, ?> super_id;

    @FXML
    private TableColumn<?, ?> super_name;

    @FXML
    private TableColumn<?, ?> super_phone;

    @FXML
    private TableColumn<?, ?> super_special;

    @FXML
    private TableView<?> superv;

    @FXML
    private ComboBox<?> type;

    @FXML
    void reg_fac(ActionEvent event) {

    }

    @FXML
    void reg_stu(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String[]names=studentname.getText().split(" ");
        String uni="s"+studentreg.getText().trim()+"@stu.najah.edu";
            String sql="INSERT INTO N_LABS.STUDENT (STU_REG_NUM, FIRST_NAME, LAST_NAME, AC_LEVEL, STU_EMAIL, UNI_EMAIL, PHONE) VALUES ('"+studentreg.getText().trim()+"', '"+names[0]+"', '"+names[1]+"', '"+studentlevel.getText().trim()+"', '"+studentmail.getText().trim()+"', '"+uni+"', '"+studentnumber.getText().trim()+"')";
        Statement sr = con.createStatement();
try {
            sr.executeUpdate(sql);
        }catch (Exception E){
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setTitle("Invalid info");
            b.setContentText("Either student is registered to this lab or the section is full");
            b.show();
            studentreg.clear();
            studentname.clear();
            studentmail.clear();
            studentlevel.clear();
            studentnumber.clear();
            return;
        }
        con.commit();
        con.close();
        studentreg.clear();
        studentname.clear();
        studentmail.clear();
        studentlevel.clear();
        studentnumber.clear();
    }

}
