/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class Dashboard_ScreenController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private AnchorPane panel;
    @FXML
    private TabPane tab;
    @FXML
    private Tab dashboard;
    @FXML
    private Tab products;
    @FXML
    private JFXButton dashboard_link;
    @FXML
    private Label lbl_Title;
    @FXML
    private Tab meetings;
    @FXML
    private Tab marketing;
    @FXML
    private Tab employee;
    @FXML
    private Tab settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

        XYChart.Series series = new XYChart.Series();
        
        series.getData().add(new XYChart.Data("January",23));
        series.getData().add(new XYChart.Data("February",14));
        series.getData().add(new XYChart.Data("March",28));
        series.getData().add(new XYChart.Data("April",25));
        series.getData().add(new XYChart.Data("May",23));
        series.setName("Income");
        XYChart.Series series2 = new XYChart.Series();
        
        series2.getData().add(new XYChart.Data("January",15));
        series2.getData().add(new XYChart.Data("February",24));
        series2.getData().add(new XYChart.Data("March",5));
        series2.getData().add(new XYChart.Data("April",17));
        series2.getData().add(new XYChart.Data("May",19));
        series2.setName("Expense");
        lineChart.getData().addAll(series,series2);
    }    

    @FXML
    private void onLogoutAction(ActionEvent event) {
        CommonFunction fo = new CommonFunction();
        fo._show("SignIn_Screen.fxml",panel);
    }

    @FXML
    private void dashboard(MouseEvent event) {
        tab.getSelectionModel().select(dashboard);
        lbl_Title.setText("Dashboard");
    }

    @FXML
    private void products(MouseEvent event) {
        tab.getSelectionModel().select(products);
        lbl_Title.setText("Products");
    }

    @FXML
    private void marketing(MouseEvent event) {
        tab.getSelectionModel().select(marketing);
        lbl_Title.setText("Marketing");
    }

    @FXML
    private void employee(MouseEvent event) {
        tab.getSelectionModel().select(employee);
       lbl_Title.setText("Employees");
    }

    @FXML
    private void meetings(MouseEvent event) {
        tab.getSelectionModel().select(meetings);
        lbl_Title.setText("Meetings");
    }

    @FXML
    private void settings(MouseEvent event) {
        tab.getSelectionModel().select(settings);
        lbl_Title.setText("Settings");
    }
    
}
