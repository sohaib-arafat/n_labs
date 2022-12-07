package source_code.head;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class home_head {
    @FXML
    private HBox cardly;
    @FXML
    private HBox cardly1;

    public void mouseEnter( ){
        cardly.setScaleX(1);
        cardly.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly);
        st.setByX(0.06f);
        st.setByY(0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
    public void mouseExit( ){
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
    public void mouseEnter1( ){
        cardly1.setScaleX(1);
        cardly1.setScaleY(1);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly1);
        st.setByX(0.06f);
        st.setByY(0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
    @FXML
    public void mouseExit1( ){
        cardly1.setScaleX(1.06);
        cardly1.setScaleY(1.06);
        ScaleTransition st = new ScaleTransition(Duration.millis(50),cardly1);
        st.setByX(-0.06f);
        st.setByY(-0.06f);
        st.setCycleCount((int) 1f);
        st.setAutoReverse(true);
        st.playFromStart();
    }
}
