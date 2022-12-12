package source_code.crd;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Expiremnts {

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
    private TextArea procedures;

    @FXML
    private TextArea tools;
    public String lab;
    String path1;
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

}
