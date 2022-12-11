package source_code.instructor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import source_code.general.student;
import source_code.general.time;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class section_click implements Initializable {
    @FXML
    private TableColumn<student, String> stu_email;
    @FXML
    private TableColumn<student, String>level;

    @FXML
    private TableColumn<student, String> name;

    @FXML
    private TableColumn<student, String> phone;

    @FXML
    private TableColumn<student, String> reg_num;

    @FXML
    private TableView<student> students;
    @FXML
    private TableColumn<student, String> uni_email;
    @FXML
    private TableColumn<time, String> day;

    @FXML
    private TableColumn<time, String> end;

    @FXML
    private GridPane grid;

    @FXML
    public Label sec_num;

    @FXML
    private TableColumn<time, String> start;

    @FXML
    private TableView<time> times;
    void cards() throws SQLException, IOException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "select DAY,STARTING,ENDING from SEC_TIME where SEC_NUM='" + sec_num.getText().trim()+"'";
        java.sql.Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            time t = new time(rs.getString(1), rs.getString(2), rs.getString(3));
            times.getItems().add(t);

        }
        Statement st=con.createStatement();
        String sql2="Select lab_num from section where sec_num='"+sec_num.getText().trim()+"'";
        ResultSet rs2=st.executeQuery(sql2);
        rs2.next();
        String lab=rs2.getString(1);
        sql="SELECT NUM,NAME,WEEK FROM EXPIREMNT WHERE LAB='"+lab+"'";
        ResultSet rs3=st.executeQuery(sql);
        int x=0;
        int y=0;
        while (rs3.next()){
            x++;
            AnchorPane a=new AnchorPane();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml_instructor/exp_button.fxml"));
            a=loader.load();
            exp_button_cont e=loader.getController();
            e.exp_num.setText(rs3.getString(1));
            e.section=sec_num.getText().trim();
            if(Integer.parseInt(rs3.getString(1))>=1&&Integer.parseInt(rs3.getString(1))<=9){
                e.exp_num.setText("0"+rs3.getString(1));
            }
            e.week.setText(rs3.getString(3));
            e.number=rs3.getString(1);
            e.lab=lab;
             grid.add(a,x,y);
            GridPane.setMargin(a, new Insets(10));
            if(x==4){
                y++;
                x=0;
            }


        }



    }
    @FXML
    private Label stus_num;
void student() throws SQLException {
    ArrayList<student> stus=new ArrayList<>();
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql1 = "SELECT STU_NUM FROM REGST WHERE SEC_NUM ='" + sec_num.getText().trim() + "'";
    Statement st=con.createStatement();
    ResultSet rs1 = st.executeQuery(sql1);
Statement sr=con.createStatement();
    while (rs1.next()) {
        ResultSet rs2 = sr.executeQuery("SELECT * FROM STUDENT WHERE STU_REG_NUM='" + rs1.getString(1) + "'");
        rs2.next();
        stus.add(new student(rs2.getString(6), rs2.getString(5), rs2.getString(2) + " " + rs2.getString(3), rs2.getString(1), rs2.getString(7), rs2.getString(4)));
    }
    if(stus.isEmpty()){
        students.setItems(null);
        stus_num.setText(String.valueOf(0));

        return;
    }

    ObservableList<student> lst1 = FXCollections.observableArrayList(stus);
    students.setItems(lst1);
    String sql="SELECT STU_COUNT FROM SECTION WHERE SEC_NUM='"+sec_num.getText().trim()+"'";
    ResultSet s=sr.executeQuery(sql);
    s.next();
    stus_num.setText(s.getString(1));
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setCellValueFactory(new PropertyValueFactory<time,String>("day"));
        start.setCellValueFactory(new PropertyValueFactory<time,String>("starting"));
        end.setCellValueFactory(new PropertyValueFactory<time,String>("ending"));
        uni_email.setCellValueFactory(new PropertyValueFactory<student,String>("uni_email"));
        stu_email.setCellValueFactory(new PropertyValueFactory<student,String>("stu_email"));
        name.setCellValueFactory(new PropertyValueFactory<student,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<student,String>("phone"));
        level.setCellValueFactory(new PropertyValueFactory<student,String>("lvl"));
        reg_num.setCellValueFactory(new PropertyValueFactory<student,String>("reg"));
        students.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_instructor/student_clk.fxml"));
                Parent root = null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                student_cont sc = l.getController();
                String[] names = students.getSelectionModel().getSelectedItem().getName().split(" ");
                sc.First.setText(names[0]);
                sc.last.setText(names[1]);
                sc.user.setText(students.getSelectionModel().getSelectedItem().getName());
                sc.Level.setText(students.getSelectionModel().getSelectedItem().getLvl());
                sc.uni.setText(students.getSelectionModel().getSelectedItem().getUni_email());
                sc.number.setText(students.getSelectionModel().getSelectedItem().getReg());
                sc.phone.setText(students.getSelectionModel().getSelectedItem().getPhone());
                sc.personal.setText(students.getSelectionModel().getSelectedItem().getStu_email());
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    con.setAutoCommit(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String sql = "select PASSWORD from LOGIN where LOGIN.USERN='" + students.getSelectionModel().getSelectedItem().getUni_email() + "'";
                Statement st = null;
                try {
                    st = con.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    ResultSet rs = st.executeQuery(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                 } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();


            }
        });

    }
}
