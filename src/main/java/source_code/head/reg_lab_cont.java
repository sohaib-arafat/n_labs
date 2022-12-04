package source_code.head;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
    ObservableList<Lab>lis;

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
    private ComboBox<String> supatr;

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
        String sql2="( '"+reg_num.getText().trim()+"' , '"+reg_name.getText().trim().toLowerCase()+"' , '"+reg_level.getText().trim().toLowerCase()+"'";
         if(!reg_super.getText().isEmpty()){
                atr.add("SUPERVISOR") ;
                val.add(reg_super.getText().trim().toLowerCase());
         }
        if(!reg_room.getText().isEmpty()){
            atr.add("ROOM") ;
            val.add(reg_room.getText().trim().toLowerCase());
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
    @FXML
    void gen_c(ActionEvent e) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT distinct LAB_NUM, NAME, ROOM, AC_LEVEL, SUPERVISOR.FIRST_NAME,SUPERVISOR.LAST_NAME FROM LAB,SUPERVISOR WHERE (LAB.LAB_NUM LIKE '%"+general.getText().trim().toLowerCase()+"%' OR LAB.NAME LIKE '%"+general.getText().trim().toLowerCase()+"%' OR LAB.ROOM LIKE '%"+general.getText().trim().toLowerCase()+"'OR AC_LEVEL LIKE '%"+general.getText().trim().toLowerCase()+"' OR LAB.SUPERVISOR LIKE '"+general.getText().toLowerCase().trim()+"%' OR SUPERVISOR.LAST_NAME LIKE '%"+general.getText().trim().toLowerCase()+"%' OR SUPERVISOR.FIRST_NAME LIKE '%"+general.getText().trim().toLowerCase()+"%') AND LAB.SUPERVISOR=SUPERVISOR.F_ID ORDER BY LAB_NUM DESC";
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ArrayList<Lab> res=new ArrayList<>();
        while (rs.next()){
            res.add(new Lab(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getString(3),rs.getString(5)+" "+rs.getString(6)));
        }
        if(res.isEmpty())
            return;
        ObservableList<Lab> lst=FXCollections.observableArrayList(res);
        table.setItems(lst);
     }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lab_num.setCellValueFactory(new PropertyValueFactory<Lab,Integer>("number"));
        lab_level.setCellValueFactory(new PropertyValueFactory<Lab,Integer>("lvl"));
        lab_super.setCellValueFactory(new PropertyValueFactory<Lab,String>("superv"));
        lab_room.setCellValueFactory(new PropertyValueFactory<Lab,String>("room"));
        lab_name.setCellValueFactory(new PropertyValueFactory<Lab,String>("name"));

        }

}
