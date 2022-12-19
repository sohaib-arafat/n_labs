package source_code.student;

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
import source_code.instructor.exp_button_cont;
import source_code.instructor.student_cont;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class sec_cont  implements Initializable {
    String id;






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
        System.out.println(sec_num.getText().trim());
        String lab=rs2.getString(1);
        sql="SELECT NUM,NAME,WEEK FROM EXPIREMNT WHERE LAB='"+lab+"'";
        ResultSet rs3=st.executeQuery(sql);
        int x=0;
        int y=0;
        while (rs3.next()){
            x++;
            AnchorPane a=new AnchorPane();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml_student/exp_button.fxml"));
            a=loader.load();
            exp_b_cont e=loader.getController();
            e.exp_num.setText(rs3.getString(1));
            e.id=id;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setCellValueFactory(new PropertyValueFactory<time,String>("day"));
        start.setCellValueFactory(new PropertyValueFactory<time,String>("starting"));
        end.setCellValueFactory(new PropertyValueFactory<time,String>("ending"));


    }

}