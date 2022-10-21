package source_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class m1  extends Application {
      @Override
    public void start(Stage stage) throws Exception {
          stage.setTitle("N-LABS");
          Parent  root = FXMLLoader.load(getClass().getResource("student/nav_student.fxml"));
          Scene scene= new Scene(root);

          Image g =new Image("resources/Logo_balck_no_writing.png");
          stage.getIcons().add(g);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();

    }
    public static void main (String [] args){
         launch(args);
    }
}
