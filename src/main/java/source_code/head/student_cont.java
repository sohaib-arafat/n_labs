package source_code.head;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import source_code.general.Lab;
import source_code.general.grade;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class student_cont implements Initializable {
@FXML
    HBox cardly;
    @FXML
     public TextField First;

    @FXML
    public TextField Level;

    @FXML
    public  TextField last;

    @FXML
    public  TextField number;
@FXML
    public  TextField password;
    @FXML
    public  TextField personal;

    @FXML
    public   TextField phone;

    @FXML
    public TextField uni;

    @FXML
    public Label user;
    @FXML
    private TableColumn<grade, String> grade;

    @FXML
    private TableView<source_code.general.grade> grades;
    @FXML
    private TableColumn<grade, String> section;

    @FXML
    private TableColumn<grade, String> submession;

    @FXML
    private TableColumn<grade, String> lab;

   @FXML
    void del (ActionEvent e)    {
        AtomicReference<Boolean> bi1= new AtomicReference<>(true);
        ButtonType Delete = new ButtonType("Delete");
        ButtonType Cancel = new ButtonType("Cancel");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this student?",Delete,Cancel);
        a.setTitle("This student is about to be deleted");
        a.showAndWait().ifPresent(response->{
            if(response==Delete){
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    con.setAutoCommit(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql="DELETE From STUDENT WHERE STU_REG_NUM='"+number.getText()+"'";
                String sql2="DELETE From LOGIN WHERE USERN='"+uni.getText()+"'";

                Statement st= null;
                try {
                    st = con.createStatement();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    st.executeUpdate(sql);
                    st.executeUpdate(sql2);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    con.commit();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                a.close();
                bi1.set(false);

            }
            else {
                a.close();
            }
        });
        if(bi1.get().equals(false)){
            Stage close=(Stage) number.getScene().getWindow();

            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Student Deleted");
            b.setContentText("This student was deleted successfully");
            b.show();
            close.close();
        }

    }
    public void cards( ) throws SQLException, IOException {
        cardly.getChildren().clear();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT SEC_NUM,LAB_NUM FROM REGST WHERE REGST.STU_NUM ='"+number.getText()+"'";
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){
            sql="SELECT SEC_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.LAST_NAME,LAB.NAME FROM SECTION,INSTRUCTOR,LAB WHERE INSTRUCTOR.F_ID=SECTION.INS_NUM AND  SECTION.LAB_NUM='"+rs.getString(2)+"' AND LAB.LAB_NUM=SECTION.LAB_NUM AND SEC_NUM='"+rs.getString(1)+"'order by SEC_NUM asc ";
            Statement stt=con.createStatement();
            ResultSet rs2=stt.executeQuery(sql);
            rs2.next();
            FXMLLoader fx = new FXMLLoader();
            fx.setLocation(getClass().getResource("/fxml_head/button_sections_head.fxml"));
            AnchorPane cardBox = fx.load();
            sec_button_cont sc=fx.getController();
            sc.section_num.setText(rs2.getString(1));
            sc.ins_name.setText(rs2.getString(2)+" "+rs2.getString(3));
            sc.lab_name.setText(rs2.getString(4));
            sc.lab=rs.getString(2);
            cardly.getChildren().add(cardBox);
        }

        con.close();
    }
   @FXML
    void save(ActionEvent event) {
        personal.setStyle("-fx-background-color: WHITE ");
        First.setStyle("-fx-background-color: WHITE ");
        last.setStyle("-fx-background-color: WHITE ");
        Level.setStyle("-fx-background-color: WHITE ");
        number.setStyle("-fx-background-color: WHITE ");
        password.setStyle("-fx-background-color: WHITE ");
        phone.setStyle("-fx-background-color: WHITE ");
        uni.setStyle("-fx-background-color: WHITE ");
        personal.setEditable(false);
        First.setEditable(false);
        last.setEditable(false);
        Level.setEditable(false);
        number.setEditable(false);
        password.setEditable(false);
        phone.setEditable(false);
        uni.setEditable(false);
        String sql="UPDATE STUDENT SET FIRST_NAME='"+First.getText().trim()+"',LAST_NAME='"+last.getText().trim()+"',PHONE ='"+phone.getText().trim()+"',STU_EMAIL='"+personal.getText().trim()+"',AC_LEVEL='"+Level.getText().trim()+"' WHERE STU_REG_NUM='"+number.getText().trim()+"'";
        String sql1="UPDATE LOGIN SET PASSWORD='"+password.getText().trim()+"' WHERE USERN='"+uni.getText().trim()+"'";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            Statement stnt=con.createStatement();
            try {
                stnt.executeUpdate(sql);
                stnt.executeUpdate(sql1);


            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Error in saving data");
                alert.showAndWait();
                return;
            }
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
   @FXML
   void refreash(){
        try {
            cards();
            grades();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {

        personal.setStyle("-fx-background-color: #dceef5 ");
        phone.setStyle("-fx-background-color: #dceef5 ");
        password.setStyle("-fx-background-color: #dceef5 ");
         Level.setStyle("-fx-background-color: #dceef5 ");
        First.setStyle("-fx-background-color: #dceef5 ");
        last.setStyle("-fx-background-color: #dceef5 ");
        personal.setEditable(true);
        phone.setEditable(true);
        password.setEditable(true);
        Level.setEditable(true);
        last.setEditable(true);
        First.setEditable(true);
    }
    @FXML
    void grades() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT GRADE,SUB_ID FROM SUB_STU WHERE SUB_STU.STU_ID='"+number.getText().trim()+"'";
         Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ArrayList<grade>res=new ArrayList<>();
        while (rs.next()){
            String sql1="SELECT SECTION,LAB FROM SUBMESSION WHERE SUBMESSION.SUB_ID='"+rs.getString(2)+"'";
            Statement stt=con.createStatement();
            ResultSet rs2=stt.executeQuery(sql1);
            rs2.next();
             String sql2="SELECT NAME FROM LAB WHERE LAB.LAB_NUM='"+rs2.getString(2)+"'";
            Statement sttt=con.createStatement();
            ResultSet rs3=sttt.executeQuery(sql2);
            rs3.next();
            res.add(new grade(rs3.getString(1),rs.getString(1),rs2.getString(1),rs.getString(2)));

        }
        if (res.isEmpty())
            return;
        grades.setItems(FXCollections.observableArrayList(res));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personal.setStyle("-fx-background-color: WHITE ");
        First.setStyle("-fx-background-color: WHITE ");
        last.setStyle("-fx-background-color: WHITE ");
        Level.setStyle("-fx-background-color: WHITE ");
        number.setStyle("-fx-background-color: WHITE ");
        password.setStyle("-fx-background-color: WHITE ");
        phone.setStyle("-fx-background-color: WHITE ");
        uni.setStyle("-fx-background-color: WHITE ");
        personal.setEditable(false);
        First.setEditable(false);
        last.setEditable(false);
        Level.setEditable(false);
        number.setEditable(false);
        password.setEditable(false);
        phone.setEditable(false);
        uni.setEditable(false);
        submession.setCellValueFactory(new PropertyValueFactory<grade,String>("submession"));
        grade.setCellValueFactory(new PropertyValueFactory<grade,String>("grade"));
        lab.setCellValueFactory(new PropertyValueFactory<grade,String>("lab"));
        section.setCellValueFactory(new PropertyValueFactory<grade,String>("section"));
    }
}
