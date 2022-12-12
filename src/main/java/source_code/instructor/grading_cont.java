package source_code.instructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class grading_cont implements Initializable {

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
        String sql = "UPDATE submession SET grade = '" + grade.getText() + "', GRADED='Y' WHERE submession.sub_id = " + id.getText();
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
        int i = 1;
        while (rs.next()) {
            String sql4="Update sub_stu set grade='"+grade.getText()+"' where sub_id="+id.getText()+" and stu_id="+rs.getString(1);
            Statement stmt4=con.createStatement();
            stmt4.executeUpdate(sql4);
            con.commit();
            String sql3 = "SELECT FIRST_NAME,last_name FROM STUDENT WHERE stu_reg_num=" + rs.getString(1);
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql3);
            rs2.next();
            students.getChildren().add(new Text(i + "- " + rs2.getString(1)+" "+rs2.getString(2) +" "+"("+rs.getString(1)+ ")\n"));
            i++;
        }
        submesiion.setText(id.getText());




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
        File file = new File("C:\\N_LABS\\reports" + rs.getString(2));
        FileOutputStream outPutStream = new FileOutputStream(file);
        outPutStream.write(bdata);
        outPutStream.close();



                Desktop.getDesktop().open(file);



        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        section.setStyle("-fx-background-color: WHITE ");
        section.setEditable(false);
        lab.setStyle("-fx-background-color: WHITE ");
        lab.setEditable(false);
        id.setStyle("-fx-background-color: WHITE ");
        id.setEditable(false);


    }
}

