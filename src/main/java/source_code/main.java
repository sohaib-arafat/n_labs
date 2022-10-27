package source_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class main extends Application {
      @Override
    public void start(Stage stage) throws Exception {
          stage.setTitle("N-LABS");
          Parent  root = FXMLLoader.load(getClass().getResource("/fxml_student/nav_student.fxml"));
          Scene scene= new Scene(root);

          Image g =new Image("/images/Logo_balck_no_writing.png");
          stage.getIcons().add(g);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.initStyle(StageStyle.TRANSPARENT);
          stage.show();

    }
    public static void main (String [] args){
         launch(args);
    }
}
