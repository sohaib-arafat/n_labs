package source_code.head;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class head_cont implements Initializable {

    @FXML
    private Button cp1;
    boolean h = false;
    @FXML
    private ImageView exit;

    @FXML
    private Button home;

    @FXML
    private Button logout;

    @FXML
    private ImageView logout2;

    @FXML
    public Label name;

    @FXML
    private Label nav_label;

    @FXML
    private AnchorPane navbar;

    @FXML
    private BorderPane pb;

    @FXML
    private Button profile;

    @FXML
    private Button regL;

    @FXML
    private Button regU;

    @FXML
    private Button search;


Parent root;
    @FXML
    private AnchorPane slider;

    @FXML
    private Button slide;
    public void initialize(URL location, ResourceBundle resources) {



        home.setStyle("-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");


        try {
            loader("/fxml_head/home_head");
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

        slide.setOnMouseClicked(event -> {
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
                slide.setDisable(true);
                GaussianBlur g = new GaussianBlur();
                g.setRadius(10);
                pb.setEffect(g);
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
            slide.setDisable(false);
            GaussianBlur g = new GaussianBlur();
            g.setRadius(0);
            pb.setEffect(g);
        });
        cp1.setOnMouseClicked(event->{
            Stage s=(Stage) cp1.getScene().getWindow();
            s.close();});
    }


    @FXML
    private void activate(ActionEvent e) throws IOException, SQLException {
        if (e.getSource() == home) {
            home.setStyle(  "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover" + "{-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;};");
            regL.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            search.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regU.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
             loader("/fxml_head/home_head");
            nav_label.setText("Home");

        }
        if (e.getSource() == regL) {
            regL.setStyle(  "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regU.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            search.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_head/labs_head");
            nav_label.setText("Labs-Registration");
            /*FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File f=fileChooser.showOpenDialog((Stage)labs.getScene().getWindow());
            System.out.println(f.getName());
            Desktop desktop = Desktop.getDesktop();
            desktop.mail();
            desktop.open(f);*/
        }
        if (e.getSource() == regU) {
            regU.setStyle( "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            search.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regL.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #D4F1F4;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            /*DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver ());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
             String f="C:\\Users\\sohai\\Desktop\\Screenshots\\SECD.png";
            InputStream in = new FileInputStream(f);
            String query = "INSERT INTO SUBMESSION(SUB_ID,FILE_NAME,FILEB) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "1202");
            pstmt.setString(2, "120");
            Blob blob;



            pstmt.setBinaryStream(3, in);
            pstmt.execute();*/










            loader("/fxml_head/reg_user_head");
            nav_label.setText("Users-Registration");


        }
        if (e.getSource() == profile) {
            profile.setStyle( "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regU.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            search.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regL.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_student/test2");
            nav_label.setText("Profile");


        }
        if (e.getSource() == search) {
            search.setStyle( "-fx-border-color: WHITE;" + "-fx-border-width: 0px 0px 0px 6px;");
            home.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regU.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            profile.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          BLACK;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            regL.setStyle(" -fx-background-color :transparent;" + ".button:hover {" + "-fx-background-color: #4592E8;" + "-fx-border-color:          WHITE;" + "-fx-border-width: 0px 0px 0px 6px;" + "};");
            loader("/fxml_head/search a user_head");
            nav_label.setText("Search A User");


        }

    }
    @FXML
    private void loader(String page) throws IOException {
          root = FXMLLoader.load(getClass().getResource(page + ".fxml"));

        pb.setCenter(root);

    }

}