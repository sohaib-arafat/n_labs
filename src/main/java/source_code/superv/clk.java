package source_code.superv;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class clk implements Initializable {

    @FXML
      TextField count;

    @FXML
      DatePicker date;

    @FXML
      TextArea disc;

    @FXML
      TextField name;

    @FXML
      TextField num;

    @FXML
      RadioButton tg1;

    @FXML
      RadioButton tg2;

    @FXML
      RadioButton tg3;
    ToggleGroup tg;
    @FXML
    Label name1;
    @FXML
void update() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    Statement stmt = con.createStatement();
        String sql="Update EQUIPMENT set count="+count.getText()+",name=+'"+  name.getText().trim()+"',DESCRIBTION='"+ disc.getText()+"' ";

        if(date!=null){
            sql="Update EQUIPMENT set count="+count.getText()+",name=+'"+  name.getText().trim()+"',DESCRIBTION='"+ disc.getText()+"',SERVICE_DATE='"+date.getValue().toString()+"' ";

    }
      if(tg2.isSelected()){
         sql+=",FAULTY=1";
     }
     else{
         sql+=",FAULTY=0";
     }
        if(tg1.isSelected()){
            sql+=",WORKING=1";
        }
        else{
            sql+=",WORKING=0";
        }
        if(tg3.isSelected()){
            sql+=",UNKNOWN=1";
        }
        else{
            sql+=",UNKNOWN=0";
        }
        sql+=" where serial_num='"+num.getText()+"'";
        stmt.executeUpdate(sql);
        con.commit();
        con.close();

        count.setEditable(false);
        name.setEditable(false);
        disc.setEditable(false);
        tg1.setDisable(true);
        tg2.setDisable(true);
        tg3.setDisable(true);
        date.setDisable(true);
        count.setStyle("-fx-background-color:WHITE");
        name.setStyle("-fx-background-color:WHITE");
        disc.setStyle("-fx-background-color:WHITE");
        tg1.setStyle("-fx-background-color:WHITE");
        tg2.setStyle("-fx-background-color:WHITE");

        tg3.setStyle("-fx-background-color:WHITE");
        date.setStyle("-fx-background-color:WHITE");



    }
    @FXML
void upd(){
        count.setEditable(true);
        name.setEditable(true);
        disc.setEditable(true);
        tg1.setDisable(false);
        tg2.setDisable(false);
        tg3.setDisable(false);
        date.setDisable(false);
    count.setStyle("-fx-background-color:#dceef5");
    name.setStyle("-fx-background-color:#dceef5");
    disc.setStyle("-fx-background-color:#dceef5");
    tg1.setStyle("-fx-background-color:#dceef5");
    tg2.setStyle("-fx-background-color:#dceef5");
    tg3.setStyle("-fx-background-color:#dceef5");
    date.setStyle("-fx-background-color:#dceef5");



}
@FXML
void delete() throws SQLException {

    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    Statement stmt = con.createStatement();
    String sql="Delete from EQUIPMENT  where serial_num="+num.getText();
    stmt.executeUpdate(sql);
    con.commit();
    con.close();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Equipment deleted successfully");
    alert.showAndWait();
    Stage stage = (Stage) num.getScene().getWindow();
    stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tg = new ToggleGroup();
        tg1.setToggleGroup(tg);
        tg2.setToggleGroup(tg);
        tg3.setToggleGroup(tg);
        count.setEditable(false);
        name.setEditable(false);
        disc.setEditable(false);
        tg1.setDisable(true);
        tg2.setDisable(true);
        tg3.setDisable(true);
        date.setDisable(true);
        num.setEditable(false);
        count.setStyle("-fx-background-color:WHITE");
        name.setStyle("-fx-background-color:WHITE");
        disc.setStyle("-fx-background-color:WHITE");
        tg1.setStyle("-fx-background-color:WHITE");
        tg2.setStyle("-fx-background-color:WHITE");
num.setStyle("-fx-background-color:WHITE");
        tg3.setStyle("-fx-background-color:WHITE");
        date.setStyle("-fx-background-color:WHITE");




    }
}
