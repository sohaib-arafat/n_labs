package source_code.student;

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
import java.sql.SQLException;

public class exp_b_cont {

    @FXML
    private AnchorPane cardly;
    String section;
    @FXML
    Label exp_num;
    String lab;
    String number;
    String id;

    @FXML
    private Button test;

    @FXML
    Label week;

    @FXML
    void mouseEnter(MouseEvent event) {
        cardly.setScaleX(1);
        cardly.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50), cardly);
        st.setByX(0.06f);
        st.setByY(0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }

    @FXML
    void mouseExit(MouseEvent event) {
        cardly.setScaleX(1.06);
        cardly.setScaleY(1.06);
        ScaleTransition st = new ScaleTransition(Duration.millis(50), cardly);
        st.setByX(-0.06f);
        st.setByY(-0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
String subb;
    @FXML
    void printe(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_student/exp.fxml"));
        Parent root=loader.load();
        exp_cont_stu controller = loader.getController();
        controller.lab=lab;
        controller.section=section;
        controller.number=number;
        controller.id=id;
        controller.setall();
         Stage stage = new Stage();

         stage.setScene(new Scene(root));
        stage.initOwner(test.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();



    }

}
