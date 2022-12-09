package source_code.instructor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import source_code.general.student;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class search_cont implements Initializable {
    @FXML
    private TextField general;
    @FXML
    TableView<student> student;
    @FXML
    TextField id;
    @FXML
    TextField f_name;
    @FXML
    TextField l_name;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    TextField addition;
    @FXML
    TextField level;

    void spec_c() throws SQLException {
        String sql = "select * from student where";
        if(!id.getText().isEmpty()){
            sql += " STU_REG_NUM = '" + id.getText().trim() + "'";
        }
        if (!f_name.getText().isEmpty()) {
            if (id.getText().isEmpty()) {
                sql += " FIRST_NAME = '" + f_name.getText().trim() + "'";

            } else {
                sql += " AND FIRST_NAME = '" + f_name.getText().trim() + "'";

            }
        }
        if (!l_name.getText().isEmpty()) {
            if (id.getText().isEmpty() && !f_name.getText().isEmpty()) {
                sql += " LAST_NAME = '" + l_name.getText().trim() + "'";

            } else {
                sql += " AND LAST_NAME = '" + l_name.getText().trim() + "'";

            }
        }
        if (!email.getText().isEmpty()) {
            if (id.getText().isEmpty() && f_name.getText().isEmpty() && l_name.getText().isEmpty()) {
                sql += " STU_EMAIL = '" + email.getText().trim() + "'";

            } else {
                sql += " AND STU_EMAIL = '" + email.getText().trim() + "'";

            }
        }
        if (!phone.getText().isEmpty()) {
            if (id.getText().isEmpty() && f_name.getText().isEmpty() && l_name.getText().isEmpty() && email.getText().isEmpty()) {
                sql += " PHONE = '" + phone.getText().trim() + "'";

            } else {
                sql += " AND PHONE = '" + phone.getText().trim() + "'";

            }
        }
        if(! addition.getText().isEmpty()){
            if (id.getText().isEmpty() && f_name.getText().isEmpty() && l_name.getText().isEmpty() && email.getText().isEmpty() && phone.getText().isEmpty()) {
                sql += " UNIVERSITY_EMAIL = '" + addition.getText().trim() + "'";

            } else {
                sql += " AND UNIVERSITY_EMAIL = '" + addition.getText().trim() + "'";

            }
        }
        if(!level.getText().isEmpty()){
            if (id.getText().isEmpty() && f_name.getText().isEmpty() && l_name.getText().isEmpty() && email.getText().isEmpty() && phone.getText().isEmpty() && addition.getText().isEmpty()) {
                sql += " AC_LEVEL = '" + level.getText().trim() + "'";

            } else {
                sql += " AND AC_LEVEL = '" + level.getText().trim() + "'";

            }
        }
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<student> res = new ArrayList<>();
        while (rs.next()) {
            res.add(new student(rs.getString(6), rs.getString(5), rs.getString(2) + " " + rs.getString(3), rs.getString(1), rs.getString(7), rs.getString(4)));
        }
        if (res.isEmpty()) {
            student.setItems(null);
            return;
        }
        ObservableList<student> lst = FXCollections.observableArrayList(res);
        student.setItems(lst);
    }
@FXML
void general_c() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql = "select * from student where STU_REG_NUM LIKE '%" + general.getText().trim() + "%' OR FIRST_NAME LIKE '%" + general.getText().trim() + "%' OR LAST_NAME LIKE '%" + general.getText().trim() + "%' OR STUDENT.UNI_EMAIL LIKE '%" + general.getText().trim() + "%' OR STUDENT.PHONE LIKE '%" + general.getText().trim() + "%' OR AC_LEVEL LIKE '%" + general.getText().trim() + "%' OR STU_EMAIL LIKE '%" + general.getText().trim() + "%' ORDER BY STU_REG_NUM ASC ";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    ArrayList<student> res = new ArrayList<>();
    while (rs.next()) {
        res.add(new student(rs.getString(6), rs.getString(5), rs.getString(2) + " " + rs.getString(3), rs.getString(1), rs.getString(7), rs.getString(4)));
    }
    if (res.isEmpty()) {
        student.setItems(null);
        return;
    }
    ObservableList<student> lst = FXCollections.observableArrayList(res);
    student.setItems(lst);
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

