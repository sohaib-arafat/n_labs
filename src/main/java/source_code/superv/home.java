
package source_code.superv;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Alert;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import source_code.general.equibment;
        import source_code.general.student;

        import javax.swing.text.TabableView;
        import java.net.URL;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.ResourceBundle;

public class home implements Initializable {
    String id;
    String lab;

    @FXML
    private TextField count;

    @FXML
    private TableColumn<equibment, String> discription;

    @FXML
    private TableColumn<equibment, String> equ_count;

    @FXML
    private TableColumn<equibment, String> equ_lab;

    @FXML
    private TableColumn<equibment, String> equ_name;

    @FXML
    private TableColumn<equibment, String> equ_num;

    @FXML
    private TableColumn<equibment, String> faulty;

    @FXML
    private TextField general;

    @FXML
    private TextField in1;

    @FXML
    private TextField in2;

    @FXML
    private TextField in3;

    @FXML
    private TextField in4;

    @FXML
    private TextField lab_num;

    @FXML
    private TextField name;

    @FXML
    private TextField num;

    @FXML
    private TableColumn<equibment, String> runnig;

    @FXML
    private TableColumn<equibment, String> service;

    @FXML
    private TableColumn<equibment, String> unknown;
    @FXML
    TableView<equibment> tools;

    @FXML
    void gen_c(ActionEvent event) throws SQLException {
        ArrayList<equibment> equibments = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        String sql="SELECT serial_num, name, describtion, count, service_date, faulty, unknown,WORKING from EQUIPMENT WHERE (SERIAL_NUM LIKE '%"+general.getText()+"%' OR NAME LIKE '%"+general.getText()+"%' OR DESCRIBTION LIKE '%"+general.getText()+"%' OR COUNT LIKE '%"+general.getText()+"%' OR SERVICE_DATE LIKE '%"+general.getText()+"%' OR FAULTY LIKE '%"+general.getText()+"%' OR UNKNOWN LIKE '%"+general.getText()+"%' OR WORKING LIKE '%"+general.getText()+"%') and LAB_NUM='"+lab+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while (rs.next()){
            equibments.add(new equibment(rs.getString(1),rs.getString(2),lab,rs.getString(4),rs.getString(3),rs.getString(6),rs.getString(8),rs.getString(7),rs.getString(5)));
        }
        ObservableList<equibment> lst1 = FXCollections.observableArrayList(equibments);
        tools.setItems(lst1);

    }

    @FXML
    void insert(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);

        String sql = "INSERT INTO EQUIPMENT(serial_num, name, describtion, count, service_date, faulty, unknown,WORKING, lab_num) VALUES('" + in1.getText().trim() + "','" + in2.getText().trim() + "','" + in4.getText().trim() + "','" + in3.getText().trim() + "','" + " -" + "'," + 0 + "," + 0 + "," + 1 + "," + lab + ")";
        Statement stmt = con.createStatement();
try
{
        stmt.executeUpdate(sql);
        con.commit();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Equipment inserted successfully");
        alert.showAndWait();
    }
       catch(SQLException e)

    {
        con.rollback();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Equipment not inserted");
        alert.showAndWait();
    }

}





    @FXML
    void spec_c(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        equ_count.setCellValueFactory(new PropertyValueFactory<equibment,String>("count"));
        equ_lab.setCellValueFactory(new PropertyValueFactory<equibment,String>("lab"));
        equ_name.setCellValueFactory(new PropertyValueFactory<equibment,String>("name"));
        equ_num.setCellValueFactory(new PropertyValueFactory<equibment,String>("serial"));
        discription.setCellValueFactory(new PropertyValueFactory<equibment,String>("discription"));
        faulty.setCellValueFactory(new PropertyValueFactory<equibment,String>("faulty"));
        runnig.setCellValueFactory(new PropertyValueFactory<equibment,String>("runnig"));
        service.setCellValueFactory(new PropertyValueFactory<equibment,String>("service"));
        unknown.setCellValueFactory(new PropertyValueFactory<equibment,String>("unknown"));
    }
}

