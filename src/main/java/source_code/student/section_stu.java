package source_code.student;

import javafx.animation.ScaleTransition;
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

public class section_stu {

    @FXML
    public AnchorPane cardly;


    @FXML
    public
    Label ins_name;

    @FXML
    public
    Label lab_name;
    String id;

    @FXML
    public
    Label section_num;

    @FXML
    Button test;

    public String lab;

    public void mouseEnter(MouseEvent event) {
        cardly.setScaleX(1);
        cardly.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50), cardly);
        st.setByX(0.06f);
        st.setByY(0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }

    public void mouseExit(MouseEvent event) {
        cardly.setScaleX(1.06);
        cardly.setScaleY(1.06);
        ScaleTransition st = new ScaleTransition(Duration.millis(50), cardly);
        st.setByX(-0.06f);
        st.setByY(-0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }

    @FXML
    void open() {
       try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_student/scetion.fxml"));
            Parent root1 =  fxmlLoader.load();
            sec_cont sc=fxmlLoader.getController();
            sc.id=id;
            sc.sec_num.setText(section_num.getText());
            sc.cards();
            System.out.println(section_num.getText()+"  ghghgh");


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Section");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    }



