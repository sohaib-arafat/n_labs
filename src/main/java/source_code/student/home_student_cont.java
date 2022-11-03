package source_code.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import source_code.general.exp_cont;
import source_code.general.expiremnt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class home_student_cont implements Initializable {
    public HBox cardlay;
    @FXML
    private Button b1,b2;

    @FXML
    private LineChart<?, ?> lineChart;
    public  ArrayList<expiremnt> exps  ;

    @FXML
    private PieChart pieChart;
    @FXML
    private Label ER;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       exps=new ArrayList<expiremnt>();
        latest();
        inLine();
        inPie();
        b2.setOnMouseClicked(event -> {
            System.exit(0);
        });
        for (int i = 0; i < exps.size(); i++) {
            FXMLLoader fx = new FXMLLoader();
            fx.setLocation(getClass().getResource("/fxml_general/button.fxml"));
            try {
                HBox cardBox = fx.load();
                exp_cont exc = fx.getController();
                exc.set_data(exps.get(i));
                cardlay.getChildren().add(cardBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }




    }

    void   latest( ) {
        exps.add(new expiremnt("4", "DCD1L"));
        exps.add(new expiremnt("8", "mpl"));
        exps.add(new expiremnt("22","ECL1"));
        exps.add(new expiremnt("10","ECL1"));




     }

    public void inLine(){
        XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data("1st Week",9.6));
        series.getData().add(new XYChart.Data("2nd Week",5.3));
        series.getData().add(new XYChart.Data("3rd Week",8.5));
        series.getData().add(new XYChart.Data("4th Week",7.2));
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke:#ffffff");

    }
    public void inPie(){
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("MP Lab",12),new PieChart.Data("DCD1 Lab",8),new PieChart.Data("EC Lab",6)
        );
        pieChart.setData(pieChartData);
    }
    @FXML
    public void text1(ActionEvent e){
          ER.setText(" 8");
     }
}
