package source_code.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import source_code.general.grade;
import source_code.general.student;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class grades implements Initializable {

    @FXML
    private TableColumn<grade, String> grade1;
    String id;

    @FXML
    private TableView<grade> grades;

    @FXML
    private TableColumn<grade, String> exp;

    @FXML
    private TableColumn<grade, String>lab;

    @FXML
    private TableColumn<grade, String>submession;

    @FXML
    void show(MouseEvent event) throws SQLException {
        ArrayList<grade> grades2 = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        String sql="SELECT SUB_ID,GRADE FROM SUB_STU WHERE STU_ID='"+id+"'";
        Statement stnt=con.createStatement();
        ResultSet rs=stnt.executeQuery(sql);
        while (rs.next()){
             String sql2="SELECT lab,EXP_NUM from submession WHERE sub_id='"+rs.getString(1)+"'";
                Statement stnt2=con.createStatement();
                ResultSet rs2=stnt2.executeQuery(sql2);
                rs2.next();
                String sql3="SELECT NAME FROM LAB WHERE LAB_NUM='"+rs2.getString(1)+"'";
                Statement stnt3=con.createStatement();
                ResultSet rs3=stnt3.executeQuery(sql3);
                rs3.next();
                grades2.add(new grade(rs3.getString(1),rs.getString(2),"1",rs.getString(1),rs2.getString(2)));

        }

        grades.setItems(FXCollections.observableArrayList(grades2));



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grade1.setCellValueFactory(new PropertyValueFactory<grade, String>("grade"));
        submession.setCellValueFactory(new PropertyValueFactory<grade, String>("submession"));
        exp.setCellValueFactory(new PropertyValueFactory<grade, String>("exp"));
        lab.setCellValueFactory(new PropertyValueFactory<grade, String>("lab"));

    }
}
