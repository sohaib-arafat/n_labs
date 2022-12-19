package source_code.student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class exp_cont_stu implements Initializable {
    @FXML
   Label submit;

    @FXML
    private Label exp_name;

    @FXML
    private TextFlow notes;
    String id;

    @FXML
    private TextFlow object;

    @FXML
    private TextFlow procs;

    @FXML
    private TextFlow tools;
    String number;
    String lab;
    String section;
    @FXML
    Button addf;
    @FXML
    Button rmv;
    @FXML
    Button sbmt;
    @FXML
    TextField path;
    @FXML
    TextField first;
    boolean op=true;
    @FXML
    TextField seconed;
    @FXML
    TextField third;
    String filename;
    String subb;


void setall() throws SQLException {
    System.out.println(id+"gkgkgkgkgkgkokrhoptreh");
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    System.out.println(number);
    String sql="SELECT OBJ,PROCED,TOOLS,NOTES,name, lab, week, num, obj, tools, notes, proced, resources FROM EXPIREMNT WHERE LAB="+lab+" AND NUM='"+number+"'";
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        object.getChildren().add(new Text(rs.getString(1)));
        procs.getChildren().add(new Text(rs.getString(2)));
        tools.getChildren().add(new Text(rs.getString(3)));
        notes.getChildren().add(new Text(rs.getString(4)));
        exp_name.setText(rs.getString(5));
    }
    String sql2="Select SUB_ID from sub_stu where stu_id="+id;

    java.sql.Statement stmt2 = con.createStatement();
    java.sql.ResultSet rs2 = stmt2.executeQuery(sql2);
    int i=0;
    while (rs2.next()) {
        String sql3="SELECT EXP_NUM,SUB_DATE,SUB_ID FROM SUBMESSION WHERE LAB="+lab+" and sub_id="+rs2.getString(1)+" and EXP_NUM="+number;
        java.sql.Statement stmt3 = con.createStatement();
        java.sql.ResultSet rs3 = stmt3.executeQuery(sql3);
        if(rs3.next()){
            i++;
            submit.setText("Submitted on "+rs3.getString(2));
            addf.setDisable(true);
             sbmt.setDisable(true);
            path.setDisable(true);
            first.setDisable(true);
            seconed.setDisable(true);
            third.setDisable(true);

subb=rs3.getString(3);
            break;


        }
    }
    if(i==0){
        submit.setText("Not yet submitted");
    }

    Statement st=con.createStatement();
    String sql4="Select open from EXPIREMNT where LAB="+lab+" and EXPIREMNT.NUM="+number;
    ResultSet rs4=st.executeQuery(sql4);
    rs4.next();
    if(rs4.getString(1).equals("0")){
        addf.setDisable(true);
        rmv.setDisable(true);
        sbmt.setDisable(true);
        path.setDisable(true);
        first.setDisable(true);
        seconed.setDisable(true);
        third.setDisable(true);
        op=false;
     }
    rs.close();
    con.close();



}
@FXML
void view() throws SQLException, IOException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="SELECT resources FROM EXPIREMNT WHERE LAB="+lab+" AND NUM='"+number+"'";
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(sql);
    rs.next();
    Blob blob = rs.getBlob(1);
    if (blob==null){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No resources");
        alert.setContentText("There are no resources for this experiment");
        alert.showAndWait();
        return;
   }
    byte[] bytes = blob.getBytes(1, (int) blob.length());
String path = "C:\\N_LABS\\src\\main\\java\\"+lab+"-"+number+".pdf";
    FileOutputStream f=new FileOutputStream(path);
    f.write(bytes);
    File file = new File(path);
    Desktop.getDesktop().open(file);

    f.close();
    rs.close();






}
@FXML
void submession() throws SQLException, IOException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql="Insert into submession (sub_id,exp_num,lab,sub_date,graded,fileb,section,FILE_NAME) values (?,?,?,?,?,?,?,?)";
    int index = 0;
    while (true){
        String test="SELECT * FROM submession WHERE sub_id ='"+ index+"'";
        Statement st=con.createStatement();
        ResultSet rs1=st.executeQuery(test);
        if (rs1.next()){
            index++;
        }
        else
            break;
    }
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, index+"" );
    ps.setString(2, number);
    ps.setString(3, lab);
    ps.setString(4, LocalDate.now().toString());
    ps.setString(5, "N");
    if (path.getText().equals("")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No file");
        alert.setContentText("Please select a file");
        alert.showAndWait();
        return;
    }
    FileInputStream f=new FileInputStream(path.getText());

    ps.setBinaryStream(6, f);
    ps.setString(7, section);
    ps.setString(8, filename);
    ps.executeUpdate();
    con.commit();
    String sql2="Insert into sub_stu (stu_id,sub_id) values (?,?)";
    PreparedStatement ps2 = con.prepareStatement(sql2);
    ps2.setString(1, id);
    ps2.setString(2, index+"" );
    ps2.executeUpdate();
    con.commit();
    if(!first.getText().isEmpty()){
        String sql4="Select sub_id from sub_stu where stu_id="+first.getText().trim();
        Statement st=con.createStatement();
        ResultSet rs4=st.executeQuery(sql4);
        while(rs4.next()){
            String sql5="Select * from submession where sub_id="+rs4.getString(1)+" and exp_num="+number+" and SECTION='"+section+"'";
            Statement st2=con.createStatement();
            ResultSet rs5=st2.executeQuery(sql5);
            if(rs5.next()){
                System.out.println(rs4.getString(1));

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Already submitted");
                alert.setContentText("This student"+first.getText()+" already submitted this experiment");
                alert.showAndWait();
                con.rollback();
                sql5="Delete from SUBMESSION where sub_id="+index;
                st2.executeUpdate(sql5);
                con.commit();
                path.clear();
                first.clear();
                seconed.clear();
                third.clear();
                return;
            }

        }
        String sub="Select sec_num from regst where STU_NUM="+first.getText().trim();
        Statement st3=con.createStatement();
        ResultSet rs6=st3.executeQuery(sub);
        rs6.next();
        if(!rs6.getString(1).equals(section)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong section");
            alert.setContentText("This student"+first.getText()+" is not in your section");
            alert.showAndWait();
            con.rollback();
            String sql5="Delete from SUBMESSION where sub_id="+index;
            Statement st2=con.createStatement();
              st2.executeUpdate(sql5);
            con.commit();
            alert.showAndWait();
path.clear();
first.clear();
seconed.clear();
third.clear();

            con.rollback();
            return;
        }
        ps2.setString(1, first.getText().trim());
        ps2.setString(2, index+"" );
        ps2.executeUpdate();
        con.commit();
    }
    if(!seconed.getText().isEmpty()){
        String sql4="Select sub_id from sub_stu where stu_id="+seconed.getText().trim();
        Statement st=con.createStatement();
        ResultSet rs4=st.executeQuery(sql4);
        while(rs4.next()) {
            String sql5 = "Select from submession where sub_id=" + rs4.getString(1) + " and exp_num=" + number + " and lab=" + lab;
            Statement st2 = con.createStatement();
            ResultSet rs5 = st2.executeQuery(sql5);
            if (rs5.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Already submitted");
                alert.setContentText("This student" + seconed.getText() + " already submitted this experiment");
                alert.showAndWait();

                con.rollback();
                String sql6="Delete from SUBMESSION where sub_id="+index;
                Statement st4=con.createStatement();
                st4.executeUpdate(sql6);
                con.commit();
                alert.showAndWait();
                path.clear();
                first.clear();
                seconed.clear();
                third.clear();
                return;
            }
        }
        String sub="Select sec_num from regst where regst.STU_NUM="+seconed.getText().trim();
        Statement st3=con.createStatement();
        ResultSet rs6=st3.executeQuery(sub);
        rs6.next();
        if(!rs6.getString(1).equals(section)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong section");
            alert.setContentText("This student"+seconed.getText()+" is not in your section");
            alert.showAndWait();

            con.rollback();
            String sql6="Delete from SUBMESSION where sub_id="+index;
            Statement st4=con.createStatement();
            st4.executeUpdate(sql6);
            con.commit();
            alert.showAndWait();
            path.clear();
            first.clear();
            seconed.clear();
            third.clear();
            return;
        }
        ps2.setString(1, seconed.getText().trim());
        ps2.setString(2, index+"" );
        ps2.executeUpdate();
        con.commit();
    }
    if(!third.getText().isEmpty()){
        String sql4="Select sub_id from sub_stu where stu_id="+seconed.getText().trim();
        Statement st=con.createStatement();
        ResultSet rs4=st.executeQuery(sql4);
        while(rs4.next()){
            String sql5="Select from submession where sub_id="+rs4.getString(1)+" and exp_num="+number+" and lab="+lab;
            Statement st2=con.createStatement();
            ResultSet rs5=st2.executeQuery(sql5);
            if(rs5.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Already submitted");
                alert.setContentText("This student"+seconed.getText()+" already submitted this experiment");
                alert.showAndWait();

                con.rollback();
                String sql6="Delete from SUBMESSION where sub_id="+index;
                Statement st4=con.createStatement();
                st4.executeUpdate(sql6);
                con.commit();
                alert.showAndWait();
                path.clear();
                first.clear();
                seconed.clear();
                third.clear();
                return;
            }

    }
        String sub="Select sec_num from regst where STU_NUM="+third.getText().trim();
        Statement st3=con.createStatement();
        ResultSet rs6=st3.executeQuery(sub);
        rs6.next();
        if(!rs6.getString(1).equals(section)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong section");
            alert.setContentText("This student"+third.getText()+" is not in your section");
            alert.showAndWait();

            con.rollback();
            String sql6="Delete from SUBMESSION where sub_id="+index;
            Statement st4=con.createStatement();
            st4.executeUpdate(sql6);
            con.commit();
            alert.showAndWait();
            path.clear();
            first.clear();
            seconed.clear();
            third.clear();
            return;
        }
        ps2.setString(1, third.getText().trim());
        ps2.setString(2, index+"" );
        ps2.executeUpdate();
        con.commit();
    }
    subb= String.valueOf(+index);

    f.close();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("Submitted");
    alert.setContentText("Your submission has been submitted successfully");
    alert.showAndWait();
    submit.setText("Submitted on "+LocalDate.now().toString());
    addf.setDisable(true);
    sbmt.setDisable(true);
    path.setDisable(true);
    first.setDisable(true);
    seconed.setDisable(true);
    third.setDisable(true);




}
@FXML
void file() throws SQLException {
 FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
        path.setText(file.getAbsolutePath());
        filename=file.getName();
    }

}
@FXML
void del() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    if (subb.isEmpty())
        return;
    String sql="delete from sub_stu where sub_id="+subb+" and stu_id="+id;
    try {
        Statement st=con.createStatement();
        st.executeUpdate(sql);
        con.commit();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String sql2="Select count (*) from sub_stu where sub_id="+subb;
    Statement st2=con.createStatement();
    ResultSet rs=st2.executeQuery(sql2);
    rs.next();
    if(rs.getInt(1)==0){
        String sql3="delete from submession where sub_id="+subb;
        Statement st3=con.createStatement();
        st3.executeUpdate(sql3);
        con.commit();
    }
    con.close();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("Deleted");
    alert.setContentText("Your submission has been deleted successfully");
    alert.showAndWait();
    submit.setText("Submit");
    addf.setDisable(false);
    sbmt.setDisable(false);
    path.setDisable(false);
     if(op){
            first.setDisable(false);
            seconed.setDisable(false);
            third.setDisable(false);
         addf.setDisable(false);
         sbmt.setDisable(false);
         path.setDisable(false);
     }
        else{
                first.setDisable(true);
                seconed.setDisable(true);
                third.setDisable(true);
                addf.setDisable(true);
                sbmt.setDisable(true);
                path.setDisable(true);
                rmv.setDisable(true);
        }


}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
