package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import source_code.general.Lab;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class reg_lab_cont implements Initializable {
    @FXML
    private Button add_reg;

    @FXML
    private Button gen_c;

    @FXML
    private TextField general;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox<?> instatr;

    @FXML
    private TextField insts;

    @FXML
    private TextField lvls;

    @FXML
    private TextField names;

    @FXML
    private TextField nums;

    @FXML
    private TextField reg_level;

    @FXML
    private TextField reg_name;

    @FXML
    private TextField reg_num;
    @FXML
    private TableColumn<Lab, Integer> lab_level;

    @FXML
    private TableColumn<Lab, String> lab_name;

    @FXML
    private TableColumn<Lab, Integer> lab_num;

    @FXML
    private TableColumn<Lab, String> lab_room;

    @FXML
    private TableColumn<Lab, String> lab_super;


    @FXML
    private TextField reg_room;

    @FXML
    private TextField reg_super;

    @FXML
    private TextField rooms;

    @FXML
    private Button spec_c;

    @FXML
    private ComboBox<?> supatr;

    @FXML
    private TextField supes;

    @FXML
    private TableView<Lab> table;
    @FXML
    void reg_lab (ActionEvent e) throws SQLException {

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        if(reg_level.getText().isEmpty()||reg_name.getText().isEmpty()||reg_num.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty field");
            a.setContentText("Please provide some more information");
            a.show();
            reg_level.clear();
            reg_num.clear();
            reg_super.clear();
            reg_level.clear();
            reg_room.clear();
            reg_name.clear();
            return;
        }
        String sql =  "Select LAB_NUM from LAB WHERE LAB_NUM='"+reg_num.getText().trim()+"'";
        Statement stnt=con.createStatement();
        ResultSet rs=stnt.executeQuery(sql);
        if(rs.next()){

        }
        ArrayList<String> atr=new ArrayList<>();
        ArrayList<String> val=new ArrayList<>();
        sql="INSERT INTO LAB (LAB_NUM,NAME,AC_LEVEL";
        String sql2="( '"+reg_num.getText().trim()+"' , '"+reg_name.getText().trim()+"' , '"+reg_level.getText().trim()+"'";
         if(!reg_super.getText().isEmpty()){
                atr.add("SUPERVISOR") ;
                val.add(reg_super.getText().trim());
         }
        if(!reg_room.getText().isEmpty()){
            atr.add("ROOM") ;
            val.add(reg_room.getText().trim());
        }
        if(!atr.isEmpty()){
    for(String r:atr){
        sql+=","+r;
    }
            for(String r:val){
                sql2+=", '"+r+"'";
            }
        }
        sql+=") VALUES";
        sql+=sql2+")";
        reg_level.clear();
        reg_num.clear();
        reg_super.clear();
        reg_level.clear();
        reg_room.clear();
        reg_name.clear();
        try{
            stnt.executeUpdate(sql);

        }
        catch (Exception ede){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid supervisor  ");
            a.setContentText("The supervisor doesnt exist or is in another lab");
            a.show();
            reg_level.clear();
            reg_num.clear();
            reg_super.clear();
            reg_level.clear();
            reg_room.clear();
            reg_name.clear();
            return;
        }
        con.commit();

        con.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
