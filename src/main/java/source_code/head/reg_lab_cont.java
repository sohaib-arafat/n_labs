package source_code.head;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import source_code.general.Lab;

import java.io.IOException;
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
    void reg_lab (ActionEvent e) throws SQLException, IOException {

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
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lab exists ");
            a.setContentText("The lab already exists");
            a.show();
            reg_level.clear();
            reg_num.clear();
            reg_super.clear();
            reg_level.clear();
            reg_room.clear();
            reg_name.clear();
            return;
        }
        ArrayList<String> atr=new ArrayList<>();
        ArrayList<String> val=new ArrayList<>();
        sql="INSERT INTO LAB (LAB_NUM,NAME,AC_LEVEL";
        String sql2="( '"+reg_num.getText().trim()+"' , '"+reg_name.getText().trim() +"' , '"+reg_level.getText().trim() +"'";
         if(!reg_super.getText().isEmpty()){
                atr.add("SUPERVISOR") ;
                val.add(reg_super.getText().trim() );
         }
        if(!reg_room.getText().isEmpty()){
            atr.add("ROOM") ;
            val.add(reg_room.getText().trim() );
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
        cards();
    }
    @FXML
    void gen_c(ActionEvent e) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT distinct LAB_NUM, NAME, ROOM, AC_LEVEL, SUPERVISOR.FIRST_NAME,SUPERVISOR.LAST_NAME,LAB.SUPERVISOR FROM LAB,SUPERVISOR WHERE (LAB.LAB_NUM LIKE '%"+general.getText().trim() +"%' OR LAB.NAME LIKE '%"+general.getText().trim() +"%' OR LAB.ROOM LIKE '%"+general.getText().trim() +"'OR AC_LEVEL LIKE '%"+general.getText().trim() +"' OR LAB.SUPERVISOR LIKE '"+general.getText() .trim()+"%' OR SUPERVISOR.LAST_NAME LIKE '%"+general.getText().trim() +"%' OR SUPERVISOR.FIRST_NAME LIKE '%"+general.getText().trim() +"%') AND LAB.SUPERVISOR=SUPERVISOR.F_ID ORDER BY LAB_NUM DESC";
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        ArrayList<Lab> res=new ArrayList<>();
        while (rs.next()){
            res.add(new Lab(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getString(3),rs.getString(5)+" "+rs.getString(6),rs.getInt(7)));
        }
        if(res.isEmpty()){
            table.setItems(null);
            return;
        }
        ObservableList<Lab> lst=FXCollections.observableArrayList(res);
        table.setItems(lst);
     }

     @FXML
     void cards() throws SQLException, IOException {
         grid.getChildren().clear();

         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
         String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
         Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
         con.setAutoCommit(false);
         String sql="SELECT LAB_NUM, NAME , SUPERVISOR.FIRST_NAME,SUPERVISOR.LAST_NAME,AC_LEVEL,LAB.SUPERVISOR,ROOM FROM LAB,SUPERVISOR WHERE  LAB.SUPERVISOR=SUPERVISOR.F_ID ORDER BY LAB_NUM DESC";
         Statement st= con.createStatement();
         ResultSet rs=st.executeQuery(sql);
         int x=0;
         int y=0;
         while (rs.next()){
             x++;
              FXMLLoader fx = new FXMLLoader();
             fx.setLocation(getClass().getResource("/fxml_head/button_labs_head.fxml"));
              AnchorPane cardBox = fx.load();
              lab_button_cont ex=fx.getController();
             ex.super_name.setText(rs.getString(3)+" "+rs.getString(4));
             ex.lab_name.setText(rs.getString(2));
             ex.lab_num.setText(rs.getString(1));
             ex.lvl=rs.getInt(5);
             ex.room=rs.getString(7);
             ex.super_num=rs.getInt(6);
             grid.add(cardBox,x,y);
             GridPane.setMargin(cardBox, new Insets(10));
             if(x==4){
                 y++;
                 x=0;
             }

         }
         con.close();



     }@FXML
     void ref(ActionEvent e) throws SQLException, IOException {
        cards();
     }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l=new FXMLLoader(getClass().getResource("/fxml_head/lab_click.fxml"));
                Parent root= null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sections_cont sc=l.getController();
                sc.header.setText(table.getSelectionModel().getSelectedItem().getName());
                sc.name.setText(table.getSelectionModel().getSelectedItem().getName());
                sc.lvl.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getLvl()));
                sc.lab_num.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getNumber()));
                sc.room.setText(table.getSelectionModel().getSelectedItem().getRoom());
                sc.superv.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getSup_num()));
                Scene dialogScene = new Scene(root);
                dialog.setScene(dialogScene);
                dialog.show();            }
        });

        lab_num.setCellValueFactory(new PropertyValueFactory<Lab,Integer>("number"));
        lab_level.setCellValueFactory(new PropertyValueFactory<Lab,Integer>("lvl"));
        lab_super.setCellValueFactory(new PropertyValueFactory<Lab,String>("superv"));
        lab_room.setCellValueFactory(new PropertyValueFactory<Lab,String>("room"));
        lab_name.setCellValueFactory(new PropertyValueFactory<Lab,String>("name"));
        try {
            cards();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
