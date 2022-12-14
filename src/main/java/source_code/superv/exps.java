package source_code.superv;

import com.ctc.wstx.shaded.msv_core.grammar.BinaryExp;
import com.ctc.wstx.shaded.msv_core.grammar.Grammar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import source_code.head.lab_button_cont;
import source_code.instructor.sec_button_cont;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class exps {
    String lab;
    @FXML
    GridPane grid;

    public void cards() throws SQLException, IOException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "SELECT distinct SEC_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.LAST_NAME,LAB.NAME,SECTION.LAB_NUM FROM SECTION,INSTRUCTOR,LAB WHERE INSTRUCTOR.F_ID=SECTION.INS_NUM and SECTION.LAB_NUM=LAB.LAB_NUM AND LAB.LAB_NUM='" + lab + "'";

        Statement stmt = con.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql);
        grid.getChildren().clear();
int x=0;
int y=0;

        while (rs.next()) {

            FXMLLoader fx = new FXMLLoader(getClass().getResource("/fxml_instructor/button_labs_instructor.fxml"));
            AnchorPane cardBox = fx.load();
            sec_button_cont sc = fx.getController();
            sc.section_num.setText(rs.getString(1));
            sc.ins_name.setText(rs.getString(2) + " " + rs.getString(3));
            sc.lab_name.setText(rs.getString(4));
            sc.lab = rs.getString(5);
            grid.add(cardBox, x, y);
            GridPane.setMargin(cardBox, new Insets(10));
            if (x == 4) {
                y++;
                x = 0;
            }
            x++;

        }


        con.close();
}
    }

