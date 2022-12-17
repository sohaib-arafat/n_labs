package source_code.student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class exp_cont_stu implements Initializable {
    @FXML
   Label submit;

    @FXML
    private Label exp_name;

    @FXML
    private TextFlow notes;
    String id;

    @FXML
    private TextFlow object;

    @FXML
    private TextFlow procs;

    @FXML
    private TextFlow tools;
    String number;
    String lab;
    String section;

void setall() throws SQLException {
    System.out.println(id+"gkgkgkgkgkgkokrhoptreh");
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    System.out.println(number);
    String sql="SELECT OBJ,PROCED,TOOLS,NOTES,name, lab, week, num, obj, tools, notes, proced, resources FROM EXPIREMNT WHERE LAB="+lab+" AND NUM='"+number+"'";
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        object.getChildren().add(new Text(rs.getString(1)));
        procs.getChildren().add(new Text(rs.getString(2)));
        tools.getChildren().add(new Text(rs.getString(3)));
        notes.getChildren().add(new Text(rs.getString(4)));
        exp_name.setText(rs.getString(5));
    }
    String sql2="Select SUB_ID from sub_stu where stu_id="+id;

    java.sql.Statement stmt2 = con.createStatement();
    java.sql.ResultSet rs2 = stmt2.executeQuery(sql2);
    int i=0;
    while (rs2.next()) {
        String sql3="SELECT EXP_NUM FROM SUBMESSION WHERE LAB="+lab+" and sub_id="+rs2.getString(1)+" and EXP_NUM="+number;
        java.sql.Statement stmt3 = con.createStatement();
        java.sql.ResultSet rs3 = stmt3.executeQuery(sql3);
        if(rs3.next()){
            i++;
            submit.setText("Submitted");
            break;


        }
    }
    if(i==0){
        submit.setText("Not yet submitted");
    }


    rs.close();
    con.close();



}
@FXML
void view() throws SQLException, IOException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="SELECT resources FROM EXPIREMNT WHERE LAB="+lab+" AND NUM='"+number+"'";
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    rs.next();
    Blob blob = rs.getBlob(1);
    if (blob==null){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No resources");
        alert.setContentText("There are no resources for this experiment");
        alert.showAndWait();
        return;
   }
    byte[] bytes = blob.getBytes(1, (int) blob.length());
String path = "C:\\N_LABS\\src\\main\\java\\"+lab+"-"+number+".pdf";
    FileOutputStream f=new FileOutputStream(path);
    f.write(bytes);
    File file = new File(path);
    Desktop.getDesktop().open(file);

    f.close();
    rs.close();






}
void submession() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);

}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
