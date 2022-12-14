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
        String sql="Update EQUIPMENT set count="+count.getText()+",name=+'"+  name.getText().trim()+"',DESCRIBTION='"+ disc.getText()+"',SERVICE_DATE='"+ date.getValue().toString()+"'";

        if(date.getValue()== null){
            sql="Update EQUIPMENT set count="+count.getText()+",name=+'"+  name.getText().trim()+"',DESCRIBTION='"+ disc.getText()+"'";

    }
      if(tg1.isSelected()){
         sql+=",FAULTY=1";
     }
     else{
         sql+=",FAULTY=0";
     }
        if(tg2.isSelected()){
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

     }
}
