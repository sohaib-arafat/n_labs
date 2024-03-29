package source_code.head;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;

public class sec_button_cont {

    @FXML
    public AnchorPane cardly;

    @FXML
    public Label ins_name;

    @FXML
    public Label lab_name;
    public String lab;
    public void mouseEnter(MouseEvent event){
        cardly.setScaleX(1);
        cardly.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly);
        st.setByX(0.06f);
        st.setByY(0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
    public void mouseExit(MouseEvent event){
        cardly.setScaleX(1.06);
        cardly.setScaleY(1.06);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly);
        st.setByX(-0.06f);
        st.setByY(-0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
    @FXML
    public Label section_num;

    @FXML
    public Button test;




    @FXML
    void printe(ActionEvent event) throws IOException, SQLException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_head/section.fxml"));
        Parent root=l.load();
        section_cont sc=l.getController();
        sc.num.setText(section_num.getText());
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement st= con.createStatement();
        String sql="SELECT INS_NUM,STU_COUNT,CAPACITY FROM SECTION WHERE SEC_NUM='"+section_num.getText().trim()+"'";
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        sc.inst.setText(rs.getString(1));
        sc.cap.setText(rs.getString(3));
        sc.stus_num.setText(rs.getString(2));
        sc.lab=lab;
        sc.sec_num.setText(section_num.getText());
        sc.showTime();
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
