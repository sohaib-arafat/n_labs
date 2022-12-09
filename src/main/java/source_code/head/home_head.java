package source_code.head;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class home_head implements Initializable {
    @FXML
    private Label inus;

    @FXML
    private Label secs;

    @FXML
    private Label labs;
    @FXML
    private Label stus;

    @FXML
    private Label sups;
    @FXML
    private TextArea test1;

    @FXML
    private TextArea text2;

    @FXML
    private TextArea text3;
    @FXML
    private HBox cardly;
    private LineChart<?, ?> chart;
    @FXML
    private HBox cardly1;
    @FXML
void show(MouseEvent e) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_head/announce_fac.fxml"));
        Stage st=new Stage();
        Parent root = loader.load();
        st.setScene(new Scene(root));
        st.show();

}
@FXML
    void show111(MouseEvent e) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_head/rems.fxml"));
        Stage st=new Stage();
        Parent root = loader.load();
        st.setScene(new Scene(root));
        st.show();

    }
@FXML
    void show1(MouseEvent e) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_head/announce_all.fxml"));

        Stage st=new Stage();
        Parent root = loader.load();
        st.setScene(new Scene(root));
        st.show();

    }
@FXML
void sann(MouseEvent e){

}
@FXML
void remann(MouseEvent e){

}
@FXML
void remann1( ) throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="select FCAULTY FROM NOUNS";
    Statement st1= con.createStatement();
    ResultSet rs=st1.executeQuery(sql);
    rs.next();
    test1.setText(rs.getString(1));
}
@FXML
    void remann11( ) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="select STUS FROM NOUNS";
        Statement st1= con.createStatement();
        ResultSet rs=st1.executeQuery(sql);
        rs.next();
        text2.setText(rs.getString(1));
    }
    @FXML
    void remann111( ) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="select REMINDERS FROM NOUNS";
        Statement st1= con.createStatement();
        ResultSet rs=st1.executeQuery(sql);
        rs.next();
        text3.setText(rs.getString(1));
    }
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
    void counts(){
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            String sql="select count(*) FROM INSTRUCTOR";
            Statement st1= con.createStatement();
            ResultSet rs=st1.executeQuery(sql);
            rs.next();
            inus.setText(rs.getString(1));
            String sql1="select count(*) FROM LAB";
            Statement st2= con.createStatement();
            ResultSet rs1=st2.executeQuery(sql1);
            rs1.next();
            labs.setText(rs1.getString(1));
            String sql2="select count(*) FROM STUDENT";
            Statement st3= con.createStatement();
            ResultSet rs2=st3.executeQuery(sql2);
            rs2.next();
            stus.setText(rs2.getString(1));
            String sql3="select count(*) FROM supervisor";
            Statement st4= con.createStatement();
            ResultSet rs3=st4.executeQuery(sql3);
            rs3.next();
            sups.setText(rs3.getString(1));
            String sql4="select count(*) FROM Section";
            Statement st5= con.createStatement();
            ResultSet rs4=st5.executeQuery(sql4);
            rs4.next();
            secs.setText(rs4.getString(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    @FXML
    private CategoryAxis xs=new CategoryAxis();

    @FXML
    private NumberAxis ys=new NumberAxis();


    @FXML
    private LineChart<?, ?> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = null;
        try {
            con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql="SELECT FCAULTY FROM NOUNS ";
        Statement st= null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ResultSet rs= st.executeQuery(sql);
            rs.next();
            test1.setText(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            remann11();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            remann111();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
counts();

ArrayList <Integer> x=new ArrayList<>();
ArrayList<Integer>y=new ArrayList<>();
        Statement statement= null;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<=10;i++){
    sql="SELECT COUNT(*) FROM SUB_STU WHERE GRADE ="+i;
            ResultSet resultSet= null;
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            x.add(i);
            try {
                y.add(resultSet.getInt(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

      XYChart.Series series=new XYChart.Series();
for(int i=0;i<10;i++){
    series.getData().add(new XYChart.Data(String.valueOf(x.get(i)),y.get(i)));
}
    lineChart.getData().addAll(series);
    lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    series.getNode().setStyle("-fx-stroke:#ffffff");

}
}
