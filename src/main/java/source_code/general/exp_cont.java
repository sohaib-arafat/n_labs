package source_code.general;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class exp_cont {

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
    public void printe(ActionEvent e){
        System.out.println(lab_name.getText());
    }

}
