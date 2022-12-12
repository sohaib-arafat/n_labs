package source_code.crd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import source_code.general.exp;
import source_code.general.student;
import source_code.general.time;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Expiremnts implements Initializable {

    @FXML
    private TextField exp_num;
    @FXML
    private TextField name;
    @FXML
    private TextField week;

    @FXML
    private TextArea notes;

    @FXML
    private TextArea objectives;

    @FXML
    private TextField path;
@FXML
    private TableView<exp> table;

    @FXML
    private TableColumn<exp, String> col1;

    @FXML
    private TableColumn<exp, String> col2;

    @FXML
    private TableColumn<exp, String> col3;
    @FXML
    private TextArea procedures;

    @FXML
    private TextArea tools;
    public String lab;
    String path1;

    public Expiremnts() {
    }
void sl() throws SQLException {
    ArrayList<exp> list = new ArrayList<>();

    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="SELECT NAME,NUM,WEEK FROM EXPIREMNT WHERE LAB="+lab;
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        list.add(new exp(rs.getString(1),rs.getString(2),rs.getString(3)));
    }
    if (list.isEmpty())
        return;
    rs.close();
    stmt.close();
    con.close();
    ObservableList<exp> lst = FXCollections.observableArrayList(list);
    table.setItems(lst);


}
    @FXML
    void add() throws SQLException, IOException {
        String query = "INSERT INTO EXPIREMNT( name,lab,week,num,obj,tools,notes,proced,OPEN,resources) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name.getText().trim());
        ps.setString(2, lab);
        ps.setString(3, week.getText().trim());
        ps.setString(4, exp_num.getText().trim());
        ps.setString(5, objectives.getText().trim());
        ps.setString(6, tools.getText().trim());
        ps.setString(7, notes.getText().trim());
        ps.setString(8, procedures.getText().trim());
        ps.setString(9, "0");
        if(!path.getText().isEmpty()){
            FileInputStream fin = new FileInputStream(path1);
            ps.setBinaryStream(10, fin);
        }
        else {
            ps.setBinaryStream(10, null);
        }

        try{
            ps.executeUpdate();
            con.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Experiment added successfully");
            alert.showAndWait();
            con.close();
            name.clear();
            exp_num.clear();
            week.clear();
            objectives.clear();
            tools.clear();
            notes.clear();
            procedures.clear();
            path.clear();


        }
        catch (Exception e){
            con.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You have enterd wrong data");
            alert.showAndWait();
            con.close();
            name.clear();
            exp_num.clear();
            week.clear();
            objectives.clear();
            tools.clear();
            notes.clear();
            procedures.clear();
            path.clear();
            return;

        }
    }
    @FXML
    void res(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f=fileChooser.showOpenDialog(null);
        if(f!=null){
            path.setText(f.getAbsolutePath());
            path1=f.getAbsolutePath();
        }

    }
    @FXML
   void clear(){
        name.clear();
        exp_num.clear();
        week.clear();
        objectives.clear();
        tools.clear();
        notes.clear();
        procedures.clear();
        path.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col1.setCellValueFactory(new PropertyValueFactory<exp, String>("number"));
        col2.setCellValueFactory(new PropertyValueFactory<exp, String>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<exp, String>("week"));
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass() .getResource("/fxml_crd/pop.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PopController popController = loader.getController();
                stage.setScene(new Scene(root));
                stage.show();




            }

        });
    }
}
