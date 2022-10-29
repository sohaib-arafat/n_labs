package source_code.student;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class student_cont implements Initializable {

    @FXML
    private AnchorPane navbar, slider,tmp,card;
    @FXML
    BorderPane pb;
    @FXML
    Parent root;
    @FXML
    private Button unfold, home, labs, grades, profile, logout;

    @FXML
    private ImageView exit;
    boolean h = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loader("/fxml_student/home_student");
           // home.setStyle("-fx-background-color : #4364f7 ;" + "-fx-border-color:BLACK;" + "-fx-border-width: 0px 0px 0px 6px;");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logout.setOnMouseClicked(event -> {
            System.exit(0);
        });

        slider.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), navbar);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), slider);
        translateTransition.setByX(-600);
        translateTransition.play();

        unfold.setOnMouseClicked(event -> {
            if (
                    !h) {
                slider.setVisible(true);

                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), navbar);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(0.15);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), slider);
                translateTransition1.setByX(+600);
                translateTransition1.play();
                h = true;
                unfold.setDisable(true);
            }

        });
        slider.setOnMouseClicked(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), navbar);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                navbar.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), slider);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            h = false;
            unfold.setDisable(false);

        });

    }
    @FXML
    private void activate(ActionEvent e) throws IOException {
        if (e.getSource() == home) {
            home.setStyle(  "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover" + "{-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;};");
            labs.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            grades.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_student/home_student");

        }
        if (e.getSource() == labs) {
            labs.setStyle(  "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            grades.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_student/test");
         }
        if (e.getSource() == grades) {
            grades.setStyle( "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            labs.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");

            loader("/fxml_student/test1");

        }
        if (e.getSource() == profile) {
            profile.setStyle( "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            labs.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            grades.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_student/test2");

        }

    }
    @FXML
    private void loader(String page) throws IOException {
        root=FXMLLoader.load(getClass().getResource(page+".fxml"));

        pb.setCenter(root);

    }

}