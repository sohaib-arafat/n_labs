package source_code.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class home_student_cont implements Initializable {
    @FXML
    private Button b1;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;
    @FXML
    private Label ER;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inLine();
        inPie();
    }
    public void inLine(){
        XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data("1st Week",9.6));
        series.getData().add(new XYChart.Data("2nd Week",5.3));
        series.getData().add(new XYChart.Data("3rd Week",8.5));
        series.getData().add(new XYChart.Data("4th Week",7.2));
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke:#3eadcf");

    }
    public void inPie(){
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("MP Lab",12),new PieChart.Data("DCD1 Lab",8),new PieChart.Data("EC Lab",6)
        );
        pieChart.setData(pieChartData);
    }
    @FXML
    public void text1(ActionEvent e){
<<<<<<< HEAD:src/sourec_code/student/home_student_cont.java
        ER.setText(" 6");
=======
        ER.setText(" 8");
>>>>>>> b06e387bc7ea82fa458e09d97eb7ffd44581f1ce:src/source_code/student/home_student_cont.java
    }
}