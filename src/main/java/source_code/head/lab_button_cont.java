package source_code.head;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class lab_button_cont {

    @FXML
    private Button test;
    @FXML
    public AnchorPane cardly;
    @FXML
    public Label lab_name;

    @FXML
    public Label lab_num;

    @FXML
    public Button main_b;
    int lvl;
    String room;
    int super_num;

    @FXML
    public Label super_name;

    public void mouseEnter(){
        cardly.setScaleX(1);
        cardly.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly);
        st.setByX(0.06f);
        st.setByY(0.06f);
         st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
    public void mouseExit(){
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
    public void printe(ActionEvent e) throws IOException, SQLException {
     final Stage dialog = new Stage();
     dialog.initModality(Modality.APPLICATION_MODAL);
     FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_head/lab_click.fxml"));
     Parent root=l.load();
     lab_cont sc=l.getController();
     sc.header.setText(lab_name.getText());
     sc.name.setText(lab_name.getText());
     sc.lvl.setText(String.valueOf(lvl));
     sc.lab_num.setText(lab_num.getText());
     sc.room.setText(room);
     sc.superv.setText(String.valueOf(super_num));
     DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
     String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
     Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
     con.setAutoCommit(false);
     String sql = "select INST_NUM from coordinator where lab_num="+lab_num.getText();
        java.sql.Statement stmt = con.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        sc.crd.setText(rs.getString(1));
        con.close();

     Scene dialogScene = new Scene(root);
     dialog.setScene(dialogScene);
      dialog.show();
    }

}
