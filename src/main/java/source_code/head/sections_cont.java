package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class sections_cont implements Initializable {

    @FXML
     public Button add_sec;

    @FXML
    public HBox cardly;

    @FXML
    public Button del;

    @FXML
    public Label header;

    @FXML
    public TextField lab_num;

    @FXML
    public TextField lvl;

    @FXML
    public TextField name;

    @FXML
    public TextField new_cap;

    @FXML
    public TextField new_inst;

    @FXML
    public TextField new_num;

    @FXML
    public TextField room;

    @FXML
    public Button save;

    @FXML
    public TextField superv;

    @FXML
    public TableView<?> times;

    @FXML
    public Button upd;
    @FXML
    void update (ActionEvent e) throws SQLException {
        superv.setEditable(true);
        superv.setStyle("-fx-background-color:#dceef5");
        lvl.setEditable(true);
        lvl.setStyle("-fx-background-color:#dceef5");
        name.setEditable(true);
        name.setStyle("-fx-background-color:#dceef5");
        room.setEditable(true);
        room.setStyle("-fx-background-color:#dceef5");

    }
    @FXML
    void delete (ActionEvent e) throws SQLException {
        AtomicReference<Boolean> bi1= new AtomicReference<>(true);
        ButtonType Delete = new ButtonType("Delete");
        ButtonType Cancel = new ButtonType("Cancel");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this lab?",Delete,Cancel);
        a.setTitle("This lab is about to be deleted");
         a.showAndWait().ifPresent(response->{
             if(response==Delete){
                 try {
                     DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                 Connection con = null;
                 try {
                     con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 try {
                     con.setAutoCommit(false);
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 String sql="DELETE From LAB WHERE LAB_NUM='"+lab_num.getText()+"'";

                 Statement st= null;
                 try {
                     st = con.createStatement();
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 try {
                     st.executeUpdate(sql);
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 try {
                     con.commit();
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     throw new RuntimeException(ex);
                 }
                 a.close();
                 bi1.set(false);

             }
             else {
                 a.close();
             }
         });
         if(bi1.get().equals(false)){
             Stage close=(Stage) lab_num.getScene().getWindow();

             Alert b = new Alert(Alert.AlertType.INFORMATION);
             b.setTitle("Lab Deleted");
             b.setContentText("This lab was deleted succesfully");
             b.show();
             close.close();
         }

    }
    @FXML
    void save (ActionEvent e) throws SQLException {
        superv.setStyle("-fx-background-color: WHITE ");
        lvl.setStyle("-fx-background-color: WHITE ");
        name.setStyle("-fx-background-color: WHITE ");
        room.setStyle("-fx-background-color: WHITE ");
        lab_num.setStyle("-fx-background-color: WHITE ");
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="UPDATE LAB SET NAME='"+name.getText().trim()+"' ,AC_LEVEL='"+lvl.getText().trim()+"', Room='"+room.getText().trim()+"',SUPERVISOR='"+superv.getText().trim()+"' WHERE LAB_NUM='"+lab_num.getText()+"'";
        Statement st= con.createStatement();
        st.executeUpdate(sql);
        con.commit();
        con.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        superv.setStyle("-fx-background-color: WHITE ");
        lvl.setStyle("-fx-background-color: WHITE ");
        name.setStyle("-fx-background-color: WHITE ");
        room.setStyle("-fx-background-color: WHITE ");
        lab_num.setStyle("-fx-background-color: WHITE ");

    }
}
