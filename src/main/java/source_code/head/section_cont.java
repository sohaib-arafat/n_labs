package source_code.head;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import source_code.general.student;
import source_code.general.time;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class section_cont implements Initializable {

    @FXML
    private Button add;

    @FXML
    public TextField cap;

    @FXML
    private Button del;
    @FXML
    private ComboBox<String> startt;
    @FXML
    private ComboBox<String> endd;
    @FXML
    private ComboBox<String> dayy;
    @FXML
    private TableColumn<time, String> start;
    @FXML
    private TableColumn<time, String> end;
    @FXML
    private TableColumn<time, String> day;
    @FXML
    public TextField inst;

    @FXML
    public TextField num;

    @FXML
    private Button save;

    @FXML
    private TextField student_num;

    @FXML
    private TableView<student> students;

    @FXML
    private Label stus;

    @FXML
    private TableView<time> times;

    @FXML
    private Button update;
    @FXML
    private TableColumn<student, String> reg_num;
    @FXML
    private TableColumn<student, String> stu_email;
    @FXML
    private TableColumn<student, String> uni_email;
    @FXML
    private TableColumn<student, String> name;
    @FXML
    private TableColumn<student, String> level;
    @FXML
    private TableColumn<student, String> phone;

@FXML
Label stus_num;
    ArrayList<time> res=new ArrayList<>();
    @FXML
    void update (ActionEvent e)   {
        cap.setEditable(true);
        cap.setStyle("-fx-background-color:#dceef5");
        inst.setEditable(true);
        inst.setStyle("-fx-background-color:#dceef5");

    }
    @FXML
    void save (ActionEvent e) throws SQLException, IOException {
        inst.setStyle("-fx-background-color: WHITE ");
        num.setStyle("-fx-background-color: WHITE ");
        cap.setStyle("-fx-background-color: WHITE ");
        inst.setEditable(false);
        cap.setEditable(false);
        inst.setEditable(false);
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "UPDATE SECTION SET CAPACITY='" + cap.getText().trim() + "' ,INS_NUM='" + inst.getText().trim() + "' WHERE SEC_NUM='" + num.getText().trim() + "'";
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        con.commit();
        for (time t : res) {
            sql = "INSERT INTO SEC_TIME(DAY,SEC_NUM, STARTING, ENDING) values ('" + t.getDay() + "' , '" + num.getText().trim() + "' " + " ,'" + t.getStarting() + "' ,'" + t.getEnding() + "')";

            try {
                st.executeUpdate(sql);

            } catch (SQLException ex) {
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("Invalid info");
                b.setContentText("Either section exits or you have entered invalid info");
                b.show();            }


        }
        con.commit();
     }
    @FXML
    void adddate(ActionEvent e) throws SQLException {
        if(dayy.getValue() ==null||startt.getValue()==null||endd.getValue()==null){
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
    void del_time(ActionEvent actionEvent) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "DELETE FROM SEC_TIME WHERE SEC_NUM='"+num.getText().trim()+"' AND DAY= '"+times.getSelectionModel().getSelectedItem().getDay()+"'";
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        con.commit();
                 res.remove(times.getSelectionModel().getSelectedItem());


        ObservableList<time> lst=FXCollections.observableArrayList(res);
        times.setItems(lst);
    }
    @FXML
    void delete (ActionEvent e) throws SQLException {
        AtomicReference<Boolean> bi1= new AtomicReference<>(true);
         ButtonType Delete = new ButtonType("Delete");
        ButtonType Cancel = new ButtonType("Cancel");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this section?",Delete,Cancel);
        a.setTitle("This section is about to be deleted");
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
                String sql="DELETE From SECTION WHERE SEC_NUM='"+num.getText().trim()+"'";

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
            Stage close=(Stage) num.getScene().getWindow();

            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Section Deleted");
            b.setContentText("This section was deleted succesfully");
            b.show();
            close.close();
        }

    }
    @FXML
    void showTime(ActionEvent e) throws SQLException {
res.clear();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql="SELECT DAY,STARTING,ENDING FROM SEC_TIME WHERE SEC_NUM ='"+num.getText().trim()+"'";
        Statement sr=con.createStatement();
        ResultSet rs=sr.executeQuery(sql);
        while (rs.next()){
            res.add(new time(rs.getString(1),rs.getString(2),rs.getString(3)));
        }
        times.setItems(null);
        ObservableList<time> lst=FXCollections.observableArrayList(res);
        times.setItems(lst);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cap.setStyle("-fx-background-color: WHITE ");
        inst.setStyle("-fx-background-color: WHITE ");
        num.setStyle("-fx-background-color: WHITE ");
        uni_email.setCellValueFactory(new PropertyValueFactory<student,String>("uni_email"));
        stu_email.setCellValueFactory(new PropertyValueFactory<student,String>("stu_email"));
        name.setCellValueFactory(new PropertyValueFactory<student,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<student,String>("phone"));
        level.setCellValueFactory(new PropertyValueFactory<student,String>("lvl"));
        reg_num.setCellValueFactory(new PropertyValueFactory<student,String>("reg"));
        day.setCellValueFactory(new PropertyValueFactory<time,String>("day"));
        start.setCellValueFactory(new PropertyValueFactory<time,String>("starting"));
        end.setCellValueFactory(new PropertyValueFactory<time,String>("ending"));
        dayy.setItems(FXCollections.observableArrayList("Sun","Mon","Tue","Wed","Thu","Fri","Sat"));
        startt.setItems(FXCollections.observableArrayList("8","9","10","11","12","13","14","15","16"));
        endd.setItems(FXCollections.observableArrayList("9","10","11","12","13","14","15","16","17"));

    }
}