package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class rems {

    @FXML
    private TextArea text1;

    @FXML
    void close(ActionEvent event) {
        Stage t=(Stage) text1.getScene().getWindow();
        t.close();
    }

    @FXML
    void fann(ActionEvent event) throws SQLException {

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="select REMINDERS FROM NOUNS";
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        String text=rs.getString(1)+"\n"+text1.getText();
        String sql1="UPDATE NOUNS SET REMINDERS='"+text+"'";
        Statement st1= con.createStatement();
        st1.executeUpdate(sql1);
        con.commit();
        con.close();
        Stage t=(Stage) text1.getScene().getWindow();
        t.close();


    }
    @FXML
    void clear(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql1="UPDATE NOUNS SET REMINDERS='\t'";
        Statement st1= con.createStatement();
        st1.executeUpdate(sql1);
        con.commit();
        con.close();
        Stage t=(Stage) text1.getScene().getWindow();
        t.close();
    }


}
