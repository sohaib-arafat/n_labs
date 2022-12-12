package source_code.instructor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import source_code.general.section;
import source_code.general.student;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class search_lab_cont implements Initializable {

    @FXML
    private HBox cardly;

    @FXML
    private TextField general;

    @FXML
    private TextField inst;
    @FXML
public String inst1;
    @FXML
    private TableColumn<source_code.general.section, String> instr;

    @FXML
    private TableColumn<source_code.general.section, String> lab;

    @FXML
    private TextField lab_num;

    @FXML
    private TableColumn<source_code.general.section, String> lab_numb;

    @FXML
    private TextField level;

    @FXML
    private TextField room;

    @FXML
    private TextField section;

    @FXML
    private TableColumn<source_code.general.section, String> section_num;

    @FXML
    private TableView<section> sections;

    @FXML
    private TextField superv;

    @FXML
    private TableColumn<source_code.general.section, String> superv_s;
    @FXML
    void gen_c() throws SQLException {
        String sql = "select distinct SECTION.SEC_NUM,SECTION.LAB_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.last_NAME,LAB.NAME ,SUPERVISOR.LAST_NAME,SUPERVISOR.FIRST_NAME from section,LAB,INSTRUCTOR,SUPERVISOR where (";
        sql += "  LAB.NAME LIKE '%" + general.getText().trim() + "%'";
        sql += " OR LAB.AC_LEVEL LIKE '%" + general.getText().trim() + "%'";
        sql += " OR LAB.ROOM LIKE '%" + general.getText().trim() + "%'";
        sql += " OR SUPERVISOR.FIRST_NAME LIKE '%" + general.getText().trim() + "%'";
        sql += " OR SUPERVISOR.last_NAME LIKE '%" + general.getText().trim() + "%'";
        sql += " OR INSTRUCTOR.FIRST_NAME LIKE '%" + general.getText().trim() + "%'";
        sql += " OR INSTRUCTOR.last_NAME LIKE '%" + general.getText().trim() + "%'";
        sql+= " OR SECTION.SEC_NUM LIKE '%" + general.getText().trim() + "%'";
        sql+= " OR SECTION.LAB_NUM LIKE '%" + general.getText().trim() + "%'";
        sql+= " OR SECTION.INS_NUM LIKE '%" + general.getText().trim() + "%'";
        sql+= " OR LAB.SUPERVISOR LIKE '%" + general.getText().trim() + "%'";
        sql+= " OR LAB.LAB_NUM LIKE '%" + general.getText().trim() + "%') and LAB.LAB_NUM=SECTION.LAB_NUM AND INSTRUCTOR.F_ID=SECTION.INS_NUM AND SUPERVISOR.F_ID=LAB.SUPERVISOR";
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        sections.getItems().clear();
        while (rs.next()) {
            sections.getItems().add(new section(rs.getString(1),rs.getString(3)+" "+rs.getString(4),rs.getString(2),rs.getString(5),rs.getString(6)+" "+rs.getString(7)));
        }

        rs.close();
        stmt.close();
        con.close();




    }
    @FXML
    void spec_c() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "select SECTION.SEC_NUM,SECTION.LAB_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.last_NAME,LAB.NAME ,SUPERVISOR.LAST_NAME,SUPERVISOR.FIRST_NAME from section,LAB,INSTRUCTOR,SUPERVISOR where LAB.LAB_NUM=SECTION.LAB_NUM AND INSTRUCTOR.F_ID=SECTION.INS_NUM AND SUPERVISOR.F_ID=LAB.SUPERVISOR";
        if(!section.getText().isEmpty()){
            sql += " AND SECTION.SEC_NUM = '" + section.getText().trim() + "'";
        }
        if(!lab_num.getText().isEmpty()){
            sql += " AND LAB.NAME like '%" + lab_num.getText().trim() + "%'";
        }
        if(!inst.getText().isEmpty()){
            if(inst.getText().trim().contains(" ")){
                String[] name = inst.getText().split(" ");
                sql += " AND INSTRUCTOR.FIRST_NAME like '%" + name[0].trim() + "%' AND INSTRUCTOR.last_NAME like '%" + name[1].trim() + "%'";
            }
            else{
                sql += " AND (INSTRUCTOR.FIRST_NAME like '%" + inst.getText().trim() + "%' OR INSTRUCTOR.last_NAME like '%" + inst.getText().trim() + "%')";
            }


        }
        if(!level.getText().isEmpty()){
            sql += " AND LAB.AC_LEVEL = '" + level.getText().trim() + "'";
        }
        if(!room.getText().isEmpty()){
            sql += " AND LAB.ROOM = '" + room.getText().trim() + "'";
        }
        if(!superv.getText().isEmpty()){
            if(superv.getText().trim().contains(" ")){
                String[] name = superv.getText().split(" ");
                sql += " AND supervisor.FIRST_NAME like '%" + name[0].trim() + "%' AND supervisor.last_NAME like '%" + name[1].trim() + "%'";
            }
            else{
                sql += " AND (supervisor.FIRST_NAME like '%" + superv.getText().trim() + "%' OR supervisor.last_NAME like '%" + superv.getText().trim() + "%')";
            }

        }

        Statement stmt = con.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql);
        sections.getItems().clear();
        while (rs.next()) {
            sections.getItems().add(new section(rs.getString(1),rs.getString(3)+" "+rs.getString(4),rs.getString(2),rs.getString(5),rs.getString(6)+" "+rs.getString(7)));
        }
        con.close();




    }
    @FXML
    public void cards() throws SQLException, IOException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
String sql="SELECT distinct SEC_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.LAST_NAME,LAB.NAME,SECTION.LAB_NUM FROM SECTION,INSTRUCTOR,LAB WHERE INSTRUCTOR.F_ID=SECTION.INS_NUM AND SECTION.INS_NUM ='"+inst1+"' and SECTION.LAB_NUM=LAB.LAB_NUM";

    Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    sections.getItems().clear();
    while (rs.next()) {
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/fxml_instructor/button_labs_instructor.fxml"));
         AnchorPane cardBox = fx.load();
        sec_button_cont sc=fx.getController();
        sc.section_num.setText(rs.getString(1));
        sc.ins_name.setText(rs.getString(2)+" "+rs.getString(3));
        sc.lab_name.setText(rs.getString(4));
        sc.lab=rs.getString(5);
        cardly.getChildren().add(cardBox);
     }
    con.close();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lab_numb.setCellValueFactory(new PropertyValueFactory<section, String>("lab_num"));
        section_num.setCellValueFactory(new PropertyValueFactory<section, String>("section"));
        instr.setCellValueFactory(new PropertyValueFactory<section, String>("instructor"));
        lab.setCellValueFactory(new PropertyValueFactory<section, String>("lab"));
        superv_s.setCellValueFactory(new PropertyValueFactory<section, String>("superv"));


    }
}
