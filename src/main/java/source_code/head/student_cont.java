package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class student_cont implements Initializable {

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
    void del(ActionEvent event) {

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
        String sql="UPDATE STUDENT SET FIRST_NAME='"+First.getText().trim()+"',LAST_NAME='"+last.getText().trim()+"',PHONE ='"+phone.getText().trim()+"',STU_EMAIL='"+password.getText().trim()+"',AC_LEVEL='"+Level.getText().trim()+"' WHERE STU_REG_NUM='"+number.getText().trim()+"'";
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            Statement stnt=con.createStatement();
            try {
                stnt.executeUpdate(sql);


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
    void update(ActionEvent event) {
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
    }
}
