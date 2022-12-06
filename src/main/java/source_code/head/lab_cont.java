package source_code.head;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import source_code.general.time;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class lab_cont implements Initializable {
    ArrayList<time> res=new ArrayList<>();

    @FXML
     public Button add_sec;

    @FXML
    public HBox cardly;
    @FXML
    private TableColumn<time, String> start;
    @FXML
    private TableColumn<time, String> end;
    @FXML
    private TableColumn<time, String> day;
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
    public TableView<time> times;
    @FXML
    private ComboBox<String> startt;
    @FXML
    private ComboBox<String> endd;
    @FXML
    private ComboBox<String> dayy;

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
    void save (ActionEvent e) throws SQLException, IOException {
        superv.setStyle("-fx-background-color: WHITE ");
        lvl.setStyle("-fx-background-color: WHITE ");
        name.setStyle("-fx-background-color: WHITE ");
        room.setStyle("-fx-background-color: WHITE ");
        lab_num.setStyle("-fx-background-color: WHITE ");
        superv.setEditable(false);
        name.setEditable(false);
        room.setEditable(false);
        lvl.setEditable(false);
        lab_num.setEditable(false);


        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="UPDATE LAB SET NAME='"+name.getText().trim()+"' ,AC_LEVEL='"+lvl.getText().trim()+"', Room='"+room.getText().trim()+"',SUPERVISOR='"+superv.getText().trim()+"' WHERE LAB_NUM='"+lab_num.getText()+"'";
        Statement st= con.createStatement();
        try {
            st.executeUpdate(sql);
        }
        catch (Exception ee) {
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setTitle("Invalid info");
            b.setContentText("Either section exits or you have entered invalid info");
            b.show();
            return;
        }
        con.commit();
        con.close();
        cards();

    }
 @FXML
public void cards( ) throws SQLException, IOException {
       cardly.getChildren().clear();
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
     String sql="SELECT SEC_NUM,INSTRUCTOR.FIRST_NAME,INSTRUCTOR.LAST_NAME,LAB.NAME FROM SECTION,INSTRUCTOR,LAB WHERE INSTRUCTOR.F_ID=SECTION.INS_NUM AND  SECTION.LAB_NUM='"+lab_num.getText()+"' AND LAB.LAB_NUM=SECTION.LAB_NUM order by SEC_NUM asc ";
    Statement st= con.createStatement();
    ResultSet rs=st.executeQuery(sql);
    while (rs.next()){
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getResource("/fxml_head/button_sections_head.fxml"));
        AnchorPane cardBox = fx.load();
        sec_button_cont sc=fx.getController();
        sc.section_num.setText(rs.getString(1));
        sc.ins_name.setText(rs.getString(2)+" "+rs.getString(3));
        sc.lab_name.setText(rs.getString(4));
        sc.lab=lab_num.getText().trim();
        cardly.getChildren().add(cardBox);
    }
   con.close();
}
@FXML
void adddate(ActionEvent e) throws SQLException {
        if(dayy.getValue() ==null||startt.getValue() ==null||endd.getValue() ==null){
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setTitle("Empty filed");
            b.setContentText("Please make sure all values are set");
            b.show();
            return;
        }
        if(startt.getValue().equals(endd.getValue())){
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setTitle("Invalid info");
            b.setContentText("Either section exits or you have entered invalid info");
            b.show();
            return;
        }
        for (time t:res){
            if(dayy.getValue().equals(t.getDay())){
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("Invalid info");
                b.setContentText("Either day exits or you have entered invalid info");
                b.show();
                return;
            }



        }


                if (Integer.parseInt(startt.getValue()) > Integer.parseInt(endd.getValue())) {
                    Alert b = new Alert(Alert.AlertType.ERROR);
                    b.setTitle("Invalid info");
                    b.setContentText("Back in future huh?");
                    b.show();
                    return;
                }

   res.add(new time(dayy.getValue(),startt.getValue(),endd.getValue()));
    ObservableList<time> lst=FXCollections.observableArrayList(res);
    times.setItems(lst);
}
@FXML
void addsec(ActionEvent e) throws SQLException, IOException {
if(new_cap.getText().isEmpty()|| new_inst.getText().isEmpty()){
    Alert b = new Alert(Alert.AlertType.ERROR);
    b.setTitle("Empty filed");
    b.setContentText("Please make sure all values are set");
    b.show();
    return;
}
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    Statement st= con.createStatement();
String sql2="SELECT COUNT(*) FROM SECTION WHERE LAB_NUM ='"+lab_num.getText().trim()+"'";
ResultSet rs1=st.executeQuery(sql2);
rs1.next();
int index=rs1.getInt(1)+1;
String SEC_NUM=lab_num.getText().trim()+"-"+(index);
     String test=null;
while (true){
    test="SELECT * FROM SECTION WHERE SEC_NUM ='"+SEC_NUM+"'";
        rs1=st.executeQuery(test);
        if (rs1.next()){
            index++;
        }
        else
            break;
        SEC_NUM=lab_num.getText().trim()+"-"+(index);

}

    String sql="INSERT INTO SECTION (STU_COUNT,SEC_NUM,INS_NUM,LAB_NUM,CAPACITY) VALUES  ('0', '"+SEC_NUM+"' ,'"+new_inst.getText().trim()+"' , '"+lab_num.getText().trim()+"' ,'"+new_cap.getText().trim()+"' )";
    try {
        st.executeUpdate(sql);

    }catch (Exception exception){
    Alert b = new Alert(Alert.AlertType.ERROR);
    b.setTitle("Invalid info");
    b.setContentText("Either section exits or you have entered invalid info");
    b.show();
    return;
}
    con.commit();
     if(res.isEmpty()){
         times.setItems(null);
          new_cap.clear();
         new_inst.clear();

         cards();
         return;
     }

    else {
        for(time t : res){
            sql="INSERT INTO SEC_TIME(DAY,SEC_NUM, STARTING, ENDING) values ('"+t.getDay()+"' , '"+SEC_NUM+"' "+" ,'"+t.getStarting()+"' ,'"+t.getEnding()+"')";

try{
    st.executeUpdate(sql);

}
catch (Exception ee){
    Alert b = new Alert(Alert.AlertType.ERROR);
    b.setTitle("Invalid info");
    b.setContentText("Either section exits or you have entered invalid info");
    b.show();
}
        }
    }
    con.commit();
    times.setItems(null);
     new_cap.clear();
    new_inst.clear();
    cards();

}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        superv.setStyle("-fx-background-color: WHITE ");
        lvl.setStyle("-fx-background-color: WHITE ");
        name.setStyle("-fx-background-color: WHITE ");
        room.setStyle("-fx-background-color: WHITE ");
        lab_num.setStyle("-fx-background-color: WHITE ");
        day.setCellValueFactory(new PropertyValueFactory<time,String>("day"));
        start.setCellValueFactory(new PropertyValueFactory<time,String>("starting"));
        end.setCellValueFactory(new PropertyValueFactory<time,String>("ending"));
        dayy.setItems(FXCollections.observableArrayList("Sun","Mon","Tue","Wed","Thu","Fri","Sat"));
        startt.setItems(FXCollections.observableArrayList("8","9","10","11","12","13","14","15","16"));
        endd.setItems(FXCollections.observableArrayList("9","10","11","12","13","14","15","16","17"));

    }
}
