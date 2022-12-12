package source_code.instructor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import source_code.general.student;
import source_code.general.submession;
import source_code.head.lab_cont;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
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
    private DatePicker date;

    @FXML
    private TextField GRADED;

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
void all() throws SQLException, FileNotFoundException {
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

    ObservableList<submession> lst1 = FXCollections.observableArrayList(submessions);
    submessions1.setItems(lst1);




}
@FXML
void spec_c() throws SQLException {

    ArrayList<submession> submessions = new ArrayList<>();
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql = "SELECT SUB_ID,GRADE,EXP_NUM,SUB_DATE,section,lab FROM SUBMESSION,Section WHERE";
    if (!id.getText().trim().isEmpty()) {
        sql += " SUB_ID='" + id.getText() + "'";
    }
    if (!section.getText().trim().isEmpty()) {
        if (!id.getText().trim().isEmpty()) {
            sql += " AND SECTION='" + section.getText() + "'";
        } else {
            sql += " SECTION='" + section.getText() + "'";
        }

    }
    if (!lab.getText().trim().isEmpty()) {
        if (!id.getText().trim().isEmpty() || !section.getText().trim().isEmpty()) {
            sql += " AND LAB='" + lab.getText() + "'";
        } else {
            sql += " LAB='" + lab.getText() + "'";
        }

    }
    if (!grade.getText().trim().isEmpty()) {
        String sc=specifier.getValue();
        if (!id.getText().trim().isEmpty() || !section.getText().trim().isEmpty() || !lab.getText().trim().isEmpty()) {
            sql += " AND GRADE"+sc+ grade.getText() ;
        } else {
            sql += " GRADE"+sc + grade.getText() ;
        }

    }
    if(!GRADED.getText().trim().isEmpty()){
        if (!id.getText().trim().isEmpty() || !section.getText().trim().isEmpty() || !lab.getText().trim().isEmpty() || !grade.getText().trim().isEmpty()) {
            sql += " AND GRADED='" + GRADED.getText() + "'";
        } else {
            sql += " GRADED='" + GRADED.getText() + "'";
        }
    }
    java.sql.Date DATE1;
    if(!(date.getValue() ==null)){
        DATE1=java.sql.Date.valueOf(date.getValue());
        System.out.println(DATE1.toString());

        if (!id.getText().trim().isEmpty() || !section.getText().trim().isEmpty() || !lab.getText().trim().isEmpty() || !grade.getText().trim().isEmpty() || !GRADED.getText().trim().isEmpty()) {
            sql += " AND SUB_DATE='" +DATE1.toString()+"" ;
        } else {
            sql += " SUB_DATE='" +DATE1.toString()+ "'";
        }
    }
    sql+=" AND SUBMESSION.LAB=SECTION.LAB_NUM AND SUBMESSION.SECTION=SECTION.SEC_NUM AND SECTION.INS_NUM='"+inst+"'";
    Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()){
        submessions.add(new submession(rs.getString(6),rs.getString(5),rs.getString(1),rs.getString(4),rs.getString(3),rs.getString(2)));
    }
    ObservableList<submession> lst1 = FXCollections.observableArrayList(submessions);
    submessions1.setItems(lst1);
    con.close();
}
@FXML
void gen() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql2="SELECT SUB_ID,GRADE,EXP_NUM,SUB_DATE,section,lab FROM SUBMESSION,Section WHERE (LAB LIKE '%"+general.getText()+"%' OR SECTION LIKE '%"+general.getText()+"%' OR SUB_ID LIKE '%"+general.getText()+"%' OR EXP_NUM LIKE '%"+general.getText()+"%' OR GRADE LIKE '%"+general.getText()+"%' OR SUB_DATE LIKE '%"+general.getText()+"%'   OR FILE_NAME LIKE '%"+general.getText()+"%' OR GRADEd LIKE '%"+general.getText()+"%') and SECTION =Section.sec_num and Section.INS_NUM='"+inst+"'";
    Statement stmt2 = con.createStatement();
    java.sql.ResultSet rs2 = stmt2.executeQuery(sql2);
    ArrayList<submession> submessions = new ArrayList<>();
    while (rs2.next()){
        submessions.add(new submession(rs2.getString(6),rs2.getString(5),rs2.getString(1),rs2.getString(4),rs2.getString(3),rs2.getString(2)));
    }
    ObservableList<submession> lst1 = FXCollections.observableArrayList(submessions);
    submessions1.setItems(lst1);
    con.close();

}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sub_grade.setCellValueFactory(new PropertyValueFactory<submession, String>("grade"));
        sub_exp.setCellValueFactory(new PropertyValueFactory<submession, String>("exp"));
        sub_date.setCellValueFactory(new PropertyValueFactory<submession, Date>("date"));
        sub_id.setCellValueFactory(new PropertyValueFactory<submession, String>("id"));
        sub_lab.setCellValueFactory(new PropertyValueFactory<submession, String>("lab"));
        sub_section.setCellValueFactory(new PropertyValueFactory<submession, String>("section"));
        submessions1.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_instructor/submission_clk.fxml"));
                Parent root= null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                grading_cont garding_cont=l.getController();
                garding_cont.id.setText(submessions1.getSelectionModel().getSelectedItem().getId());
                garding_cont.lab.setText(submessions1.getSelectionModel().getSelectedItem().getLab());
                garding_cont.section.setText(submessions1.getSelectionModel().getSelectedItem().getSection());
                garding_cont.grade.setText(submessions1.getSelectionModel().getSelectedItem().getGrade());
                try {
                    garding_cont.show();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(date.getValue());

                Scene dialogScene = new Scene(root);
                dialog.setScene(dialogScene);
                dialog.show();            }
        });
        specifier.setItems(FXCollections.observableArrayList("=",">","<",">=","<="));

    }
}
