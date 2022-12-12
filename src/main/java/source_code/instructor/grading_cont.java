package source_code.instructor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.*;
import java.sql.*;

public class grading_cont {

    @FXML
    TextField grade;

    @FXML
    TextField id;

    @FXML
    TextField lab;

    @FXML
    TextField section;

    @FXML
    private TextFlow students;

    @FXML
    private Label submesiion;
@FXML
    void grade() throws SQLException {
        String sql = "UPDATE submession SET grade = '" + grade.getText() + "' WHERE submession.sub_id = " + id.getText();
        String sql3 = "UPDATE SUB_STU SET GRADE = '" + grade.getText() + "' WHERE SUB_STU.SUB_ID= " + id.getText();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        con.commit();
        Statement stmt3 = con.createStatement();
    stmt3.executeUpdate(sql3);
        con.commit();


    }
    @FXML
    void show() throws SQLException, IOException {
        String sql2 = "SELECT STU_ID from SUB_STU WHERE SUB_ID=" + id.getText();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql2);
        int i = 0;
        while (rs.next()) {
            students.getChildren().add(new Text(i + "- " + rs.getString(1) + "\t"));
        }
        submesiion.setText("submession id: "+id.getText());




        con.close();
    }

    @FXML

    void view() throws SQLException, IOException {
        String sql = "SELECT FILEB,FILE_NAME FROM SUBMESSION WHERE SUB_ID=" + id.getText();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        Blob blob = rs.getBlob(1);
        byte[] bdata = blob.getBytes(1, (int) blob.length());
        File file = new File("C:\\Users\\sohai\\Desktop\\New folder (4)\\" + rs.getString(2));
        FileOutputStream outPutStream = new FileOutputStream(file);
        outPutStream.write(bdata);
        outPutStream.close();



                Desktop.getDesktop().open(file);



        }

    }

