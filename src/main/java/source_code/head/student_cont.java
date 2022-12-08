package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

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
