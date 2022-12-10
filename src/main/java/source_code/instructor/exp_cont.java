package source_code.instructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
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
    String sql="SELECT OBJ,PROCED,TOOLS,NOTES FROM EXPIREMNT WHERE LAB="+lab+" AND NUM='"+number+"'";
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        object.getChildren().add(new Text(rs.getString(1)));
        procs.getChildren().add(new Text(rs.getString(2)));
        tools.getChildren().add(new Text(rs.getString(3)));
        notes.getChildren().add(new Text(rs.getString(4)));
    }
    con.close();



}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
