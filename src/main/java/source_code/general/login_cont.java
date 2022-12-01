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
import source_code.instructor.crd_cont;
import source_code.instructor.inst_cont;
import source_code.student.student_cont;
import source_code.superv.super_cont;

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
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        if (user.getText().isEmpty() || pass.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("No login info was entered");
            a.setContentText("No username or password were entered");
            a.show();
            return;

        }
        String sql =  "Select * from LOGIN WHERE USERN='"+user.getText()+"' AND PASSWORD= '"+pass.getText()+"'";
        Statement stnt=con.createStatement();
        ResultSet rs=stnt.executeQuery(sql);
if (rs.next()){
    final Stage dialog = new Stage();
    dialog.initStyle(StageStyle.TRANSPARENT);
    dialog.initModality(Modality.APPLICATION_MODAL);

    if (rs.getString(3).equals("stu")) {
        FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_student/nav_student.fxml"));
        Parent root = l.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        Stage tmp = (Stage) user.getScene().getWindow();
        student_cont sc = l.getController();
        ResultSet rs1 = stnt.executeQuery("Select FIRST_NAME,LAST_NAME from STUDENT WHERE UNI_EMAIL=" + "'" + rs.getString(1) + "'");
        rs1.next();
        sc.name.setText(rs1.getString(1) + " " + rs1.getString(2));
        tmp.close();
        dialog.show();
        return;
    }
    if (rs.getString(3).equals("inst")) {
        Stage tmp = (Stage) user.getScene().getWindow();
        FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_instructor/inst_nav.fxml"));
        Parent root = l.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        inst_cont ic = l.getController();
        ResultSet rs1 = stnt.executeQuery("Select FIRST_NAME,LAST_NAME from INSTRUCTOR WHERE EMAIL=" + "'" + rs.getString(1) + "'");
        rs1.next();
        ic.name.setText(rs1.getString(1) + " " + rs1.getString(2));
        tmp.close();
        dialog.show();
        return;
    }
    if (rs.getString(3).equals("crd")) {
        Stage tmp = (Stage) user.getScene().getWindow();
        FXMLLoader l1 = new FXMLLoader(getClass().getResource("/fxml_crd/nav_crd.fxml"));
        Parent root = l1.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        crd_cont cc = l1.getController();
        ResultSet rs1 = stnt.executeQuery("Select FIRST_NAME,LAST_NAME from INSTRUCTOR WHERE EMAIL=" + "'" + rs.getString(1) + "'");
        rs1.next();
        cc.name.setText(rs1.getString(1) + " " + rs1.getString(2));
        tmp.close();
        dialog.show();
        return;
    }
    if (rs.getString(3).equals("sup")) {
        Stage tmp = (Stage) user.getScene().getWindow();
        FXMLLoader l2 = new FXMLLoader(getClass().getResource("/fxml_super/nav_super.fxml"));
        Parent root = l2.load();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        super_cont sc = l2.getController();
        ResultSet rs1 = stnt.executeQuery("Select FIRST_NAME,LAST_NAME from SUPERVISOR WHERE EMAIL=" + "'" + rs.getString(1) + "'");
        rs1.next();
        sc.name.setText(rs1.getString(1) + " " + rs1.getString(2));
        tmp.close();
        dialog.show();
        return;
    }
}
else {
    Alert a = new Alert(Alert.AlertType.ERROR);
    a.setTitle("Wrong login info");
    a.setContentText("No such username or password are found");
    a.show();
    return;
}


    }



}





