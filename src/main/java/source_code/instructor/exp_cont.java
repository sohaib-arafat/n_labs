package source_code.instructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class exp_cont implements Initializable {

    @FXML
    private Label exp_name;

    @FXML
    private TextFlow notes;

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
String path = "C:\\N_LABS\\src\\main\\java"+lab+"-"+number+".pdf";
    FileOutputStream f=new FileOutputStream(path);
    f.write(bytes);
    File file = new File(path);
    Desktop.getDesktop().open(file);

    f.close();
    rs.close();






}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
