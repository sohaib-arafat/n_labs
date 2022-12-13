package source_code.crd;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PopController {
    public String exp_num;
    public String name;
    String lab;
@FXML
    void oopen() throws SQLException {
        String sql="UPDATE EXPIREMNT SET OPEN=1 WHERE num="+exp_num+" AND LAB="+lab;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        con.commit();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Experiment opened successfully");
        alert.showAndWait();

    }
    @FXML
    void del(){
        String sql="DELETE FROM EXPIREMNT WHERE num="+exp_num+" AND LAB="+lab;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.commit();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Experiment deleted successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Label yt;

    @FXML
    void close(){
    Stage st=(Stage)yt.getScene().getWindow();
    st.close();

    }
}
