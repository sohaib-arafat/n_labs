package source_code.general;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
    public void printe(ActionEvent e) throws IOException {
     final Stage dialog = new Stage();
     dialog.initModality(Modality.APPLICATION_MODAL);
     FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_general/login.fxml"));
     Parent root=l.load( );
      Scene dialogScene = new Scene(root);
     dialog.setScene(dialogScene);
      dialog.show();
    }

}
