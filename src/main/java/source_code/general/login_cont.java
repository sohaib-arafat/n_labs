package source_code.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import source_code.student.student_cont;

import java.io.IOException;
import java.sql.*;

public class login_cont {

    @FXML
    private Button login;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver ());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        if(user.getText().isEmpty() || pass.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("No login info was entered");
            a.setContentText("No username or password were entered");
            a.show();
            return;

        }
        Statement selectStmt=con.createStatement();
        ResultSet rs=selectStmt.executeQuery("Select * from LOGIN");
        while (rs.next()){
            if(rs.getString(1).equals(user.getText())&& rs.getString(2).equals(pass.getText())){
                        final Stage dialog = new Stage();
                        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.APPLICATION_MODAL);
       FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_student/nav_student.fxml"));
        Parent root=l.load();
         Scene dialogScene = new Scene(root);
         dialog.setScene(dialogScene);
         Stage tmp =(Stage) user.getScene().getWindow();
         student_cont sc=l.getController();
sc.name.setText(rs.getString(1));
         tmp.close();
          dialog.show();
            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Wrong login info");
                a.setContentText("No such username or password are found");
                a.show();

            }
        }



    }

}
