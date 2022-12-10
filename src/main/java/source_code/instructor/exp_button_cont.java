package source_code.instructor;

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

public class exp_button_cont {

    @FXML
    private AnchorPane cardly;

    @FXML
    Label exp_num;
    String lab;

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

    @FXML
    void printe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_instructor/exp.fxml"));
        Parent root=loader.load();
        exp_cont controller = loader.getController();
        controller.lab=lab;
        controller.setall();
        Stage stage = new Stage();
        controller.number=exp_num.getText().trim();
        stage.setScene(new Scene(root));
        stage.initOwner(test.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();



    }

}
