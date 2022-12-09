package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class inst_cont implements Initializable {

    @FXML
      TextField First;

    @FXML
      HBox cardly;

    @FXML
      TextField last;

    @FXML
      TextField number;

    @FXML
      TextField office;

    @FXML
      TextField password;

    @FXML
      TextField personal;

    @FXML
      TextField phone;

    @FXML
      Button refresh;

    @FXML
      Label user;
    public void cards( ) throws SQLException, IOException {
        cardly.getChildren().clear();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT SEC_NUM,LAB_NUM FROM SECTION WHERE SECTION.INS_NUM ='"+number.getText()+"'";
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
    void del(ActionEvent event) {

    }

    @FXML
    void refreash(ActionEvent event) throws SQLException, IOException {
        cards();

    }

    @FXML
    void save(ActionEvent event) {
         First.setStyle("-fx-background-color: WHITE ");
        last.setStyle("-fx-background-color: WHITE ");
         number.setStyle("-fx-background-color: WHITE ");
        password.setStyle("-fx-background-color: WHITE ");
        phone.setStyle("-fx-background-color: WHITE ");
        office.setStyle("-fx-background-color: WHITE ");
         First.setEditable(false);
        last.setEditable(false);
         number.setEditable(false);
        password.setEditable(false);
        phone.setEditable(false);
        office.setEditable(false);
         String sql="UPDATE INSTRUCTOR SET FIRST_NAME='"+First.getText().trim()+"',LAST_NAME='"+last.getText().trim()+"',PHONE_NUMBER ='"+phone.getText().trim()+"',EMAIL='"+personal.getText().trim()+"',OFFICE_NUMBER='"+office.getText().trim()+"' WHERE F_ID='"+number.getText().trim()+"'";
         String sql1="UPDATE LOGIN SET PASSWORD='"+password.getText().trim()+"' WHERE USERN='"+personal.getText().trim()+"'";
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

    void update(ActionEvent event) throws SQLException, IOException {

         phone.setStyle("-fx-background-color: #dceef5 ");
        password.setStyle("-fx-background-color: #dceef5 ");
         First.setStyle("-fx-background-color: #dceef5 ");
        last.setStyle("-fx-background-color: #dceef5 ");
        personal.setEditable(true);
        phone.setEditable(true);
        password.setEditable(true);
         last.setEditable(true);
        First.setEditable(true);
        office.setEditable(true);
        office.setStyle("-fx-background-color: #dceef5 ");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personal.setStyle("-fx-background-color: WHITE ");
        First.setStyle("-fx-background-color: WHITE ");
        last.setStyle("-fx-background-color: WHITE ");
         number.setStyle("-fx-background-color: WHITE ");
        password.setStyle("-fx-background-color: WHITE ");
        phone.setStyle("-fx-background-color: WHITE ");
        office.setStyle("-fx-background-color: WHITE ");
         personal.setEditable(false);
        First.setEditable(false);
        last.setEditable(false);
         number.setEditable(false);
        password.setEditable(false);
        phone.setEditable(false);
        office.setEditable(false);
     }
}
