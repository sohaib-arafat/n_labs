
package source_code.superv;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import net.sf.jasperreports.engine.*;
        import source_code.general.equibment;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;
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
    DatePicker date;
    @FXML
    ComboBox<String> specifier;



    @FXML
    void gen_c(ActionEvent event) throws SQLException {
        ArrayList<equibment> equibments = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        String sql="SELECT serial_num, name,LAB_NUM,  count,DESCRIBTION,   faulty,  WORKING,UNKNOWN,SERVICE_DATE from EQUIPMENT WHERE (SERIAL_NUM LIKE '%"+general.getText()+"%' OR NAME LIKE '%"+general.getText()+"%' OR DESCRIBTION LIKE '%"+general.getText()+"%' OR COUNT LIKE '%"+general.getText()+"%' OR SERVICE_DATE LIKE '%"+general.getText()+"%' OR FAULTY LIKE '%"+general.getText()+"%' OR UNKNOWN LIKE '%"+general.getText()+"%' OR WORKING LIKE '%"+general.getText()+"%') or LAB_NUM like '%"+general.getText()+"%'";
        ResultSet rs=stmt.executeQuery(sql);
        while (rs.next()){
            equibments.add(new equibment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9)));        }
        ObservableList<equibment> lst1 = FXCollections.observableArrayList(equibments);
        tools.setItems(lst1);

    }


    @FXML
    void insert(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
System.out.println(lab);
        String sql = "INSERT INTO EQUIPMENT(serial_num, name, describtion, count, service_date, faulty, unknown,WORKING, lab_num) VALUES('" + in1.getText().trim() + "','" + in2.getText().trim() + "','" + in4.getText().trim() + "','" + in3.getText().trim() + "','" + " -" + "'," + 0 + "," + 0 + "," + 1 + "," + lab + ")";
        Statement stmt = con.createStatement();

        stmt.executeUpdate(sql);
        con.commit();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Equipment inserted successfully");
        alert.showAndWait();

     /*  catch(SQLException e)

    {
        con.rollback();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Equipment not inserted");
        alert.showAndWait();
    }*/

}

@FXML
void rep() throws JRException, SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
    con.setAutoCommit(false);
    String sql = "SELECT * FROM EQUIPMENT WHERE LAB_NUM = " + lab;
    String num = "";
    String name = "";
    String lab_num = lab;
    String count = "";
    String working = "";
    String faulty = "";
    String unknown = "";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        num += rs.getString(1) + "\n\n";
        name += rs.getString(2) + "\n\n";
        count += rs.getString(4) + "\n\n";
        working += rs.getString(6) + "\n\n";
        faulty += rs.getString(7) + "\n\n";
        unknown += rs.getString(8) + "\n\n";


    }
    JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\sohai\\JaspersoftWorkspace\\MyReports\\tools.jrxml");
    JRDataSource data = new JREmptyDataSource();
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Lab_num", lab);
    parameters.put("Tool_num", num);
    parameters.put("Tool_name", name);
    parameters.put("Tool_count", count);
    parameters.put("working", working);
    parameters.put("faulty", faulty);
    parameters.put("Unknown", unknown);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, data);
    JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\N_LABS\\src\\tools.pdf");
}


    @FXML
    void spec_c(ActionEvent event) throws SQLException {
        ArrayList<equibment> equibments = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        String sql="SELECT serial_num, name,LAB_NUM,  count,DESCRIBTION,   faulty,  WORKING,UNKNOWN,SERVICE_DATE from EQUIPMENT ";
                if(!num.getText().isEmpty()){
                     sql+="WHERE SERIAL_NUM='"+num.getText()+"'";
                }
                if(!name.getText().isEmpty()){
                    if(!num.getText().isEmpty()){
                        sql+=" AND NAME='"+name.getText()+"'";
                    }
                    else{
                        sql+=" NAME='"+name.getText()+"'";
                    }
                }

                if(!count.getText().isEmpty()){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()){
                        sql+=" AND COUNT='"+count.getText()+"'";
                    }
                    else{
                        sql+="WHERE COUNT='"+count.getText()+"'";
                    }
                }
                if(date.getValue()!=null){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()||!count.getText().isEmpty()){
                        sql+=" AND SERVICE_DATE='"+date.getValue().toString()+"'";
                    }
                    else{
                        sql+="WHERE SERVICE_DATE='"+date.getValue().toString()+"'";
                    }
                }
                if(specifier.getValue().equals("Faulty")){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()||!count.getText().isEmpty()||date.getValue()!=null){
                        sql+=" AND FAULTY=1";
                    }
                    else{
                        sql+="WHERE FAULTY=1";
                    }
                }
                if(specifier.getValue().equals("Unknown")){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()||!count.getText().isEmpty()||date.getValue()!=null||specifier.getValue().equals("Faulty")){
                        sql+=" AND UNKNOWN=1";
                    }
                    else{
                        sql+="WHERE UNKNOWN=1";
                    }
                }
                if(specifier.getValue().equals("Working")){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()|| !count.getText().isEmpty()||date.getValue()!=null||specifier.getValue().equals("Faulty")||specifier.getValue().equals("Unknown")){
                        sql+=" AND WORKING=1";
                    }
                    else{
                        sql+="WHERE WORKING=1";
                    }
                }
                if(!lab_num.getText().isEmpty()){
                    if(!num.getText().isEmpty()||!name.getText().isEmpty()||!count.getText().isEmpty()||date.getValue()!=null){
                        sql+=" AND LAB_NUM='"+lab_num.getText()+"'";
                    }
                    else{
                        sql+="WHERE LAB_NUM='"+lab_num.getText()+"'";
                    }
                }
                if(specifier.getValue().equals("All")){
                }
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql);
        while (rs.next()){
            equibments.add(new equibment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9)));
        }
        ObservableList<equibment> lst1 = FXCollections.observableArrayList(equibments);
        tools.setItems(lst1);
        System.out.println(sql);

    }
    String getLab(){
        return lab;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        equ_count.setCellValueFactory(new PropertyValueFactory<equibment, String>("count"));
        equ_lab.setCellValueFactory(new PropertyValueFactory<equibment, String>("lab"));
        equ_name.setCellValueFactory(new PropertyValueFactory<equibment, String>("name"));
        equ_num.setCellValueFactory(new PropertyValueFactory<equibment, String>("serial"));
        discription.setCellValueFactory(new PropertyValueFactory<equibment, String>("discription"));
        faulty.setCellValueFactory(new PropertyValueFactory<equibment, String>("faulty"));
        runnig.setCellValueFactory(new PropertyValueFactory<equibment, String>("runnig"));
        service.setCellValueFactory(new PropertyValueFactory<equibment, String>("service"));
        unknown.setCellValueFactory(new PropertyValueFactory<equibment, String>("unknown"));
        specifier.getItems().addAll("Faulty", "Unknown", "Working", "All");
        tools.setOnMouseClicked((MouseEvent event) -> {
            System.out.println(getLab());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_super/tool_clk.fxml"));
                Stage d=new Stage();
                Parent root= null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                d.setScene(scene);
                clk controller = loader.getController();
                controller.name1.setText(tools.getSelectionModel().getSelectedItem().getName());
                controller.num.setText(tools.getSelectionModel().getSelectedItem().getSerial());
                controller.count.setText(tools.getSelectionModel().getSelectedItem().getCount());
                 controller.disc.setText(tools.getSelectionModel().getSelectedItem().getDiscription());
                 controller.name.setText(tools.getSelectionModel().getSelectedItem().getName());
                 controller.date.setPromptText(tools.getSelectionModel().getSelectedItem().getService());
                 if(tools.getSelectionModel().getSelectedItem().getFaulty().equals("1")){
                     controller.tg2.setSelected(true);
                 }
                    if(tools.getSelectionModel().getSelectedItem().getUnknown().equals("1")){
                        controller.tg3.setSelected(true);
                    }
                    if(tools.getSelectionModel().getSelectedItem().getRunnig().equals("1")){
                        controller.tg1.setSelected(true);
                    }
                d.show();







        });
    }
}

