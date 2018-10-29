/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import organization.process.DBHelper;
import organization.process.InstantData;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class BofD_Dashboard_ScreenController implements Initializable {

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

    @FXML
    private JFXTreeTableView<User> treeView;
    @FXML
    private Label lbl_user;
    @FXML
    private JFXTextField txt_productName;
    @FXML
    private JFXTextArea ta_productDescription;
    @FXML
    private JFXDatePicker txt_productStartingDate;
    @FXML
    private JFXDatePicker txt_productDueDate;
    @FXML
    private JFXComboBox<?> cb_projectLeader;
    @FXML
    private JFXButton btn_createProduct;
    @FXML
    private JFXToggleButton chk_isActive;
    @FXML
    private Label lbl_job;
    @FXML
    private JFXTextField txt_settings_username;
    @FXML
    private JFXPasswordField txt_settings_password;
    @FXML
    private JFXButton btn_settings_edit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_job.setText(InstantData.personJobName);
        lbl_user.setText(InstantData.person.getName() + " " + InstantData.person.getLastName());

        DBHelper db = new DBHelper();
        db.open();

        String[][] balanceSheet = db.SelectFromTable("balancesheet");
        double[] monthsIncomes = new double[12];
        double[] monthsExpenses = new double[12];

        for (int i = 0; i < balanceSheet.length; i++) {

            String[] mont = balanceSheet[i][4].split("-");
            int month = Integer.parseInt(mont[1]);
            monthsIncomes[month - 1] += Double.parseDouble(balanceSheet[i][2]);
            monthsExpenses[month - 1] += Double.parseDouble(balanceSheet[i][3]);
        }

        String[] months = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        // TREEVIEW SON--------------------------------------------------------------
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < 12; i++) {
            if (monthsIncomes[i] != 0) {
                series.getData().add(new XYChart.Data(months[i], monthsIncomes[i]));
            }
        }
        series.setName("Income");

        XYChart.Series series2 = new XYChart.Series();

        for (int i = 0; i < 12; i++) {
            if (monthsExpenses[i] != 0) {
                series2.getData().add(new XYChart.Data(months[i], monthsExpenses[i]));
            }
        }
        series2.setName("Expense");
        lineChart.getData().addAll(series, series2);

        db.close();
    }

    class User extends RecursiveTreeObject<User> {

        StringProperty name;
        StringProperty lastName;
        StringProperty age;
        StringProperty department;
        StringProperty job;
        StringProperty salary;

        public User(String name, String lastName, String age, String department, String job, String salary) {
            this.name = new SimpleStringProperty(name);
            this.lastName = new SimpleStringProperty(lastName);
            this.age = new SimpleStringProperty(age);
            this.department = new SimpleStringProperty(department);
            this.job = new SimpleStringProperty(job);
            this.salary = new SimpleStringProperty(salary);
        }

    }

    @FXML
    private void onLogoutAction(ActionEvent event) {
        CommonFunction fo = new CommonFunction();
        fo._show("SignIn_Screen.fxml", panel);
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

        //TreeView Başlangıç
        JFXTreeTableColumn<User, String> nameColumn = new JFXTreeTableColumn<>("Name");
        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<User, String> lastNameColumn = new JFXTreeTableColumn<>("Last Name");
        lastNameColumn.setPrefWidth(150);
        lastNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().lastName;
            }
        });

        JFXTreeTableColumn<User, String> ageColumn = new JFXTreeTableColumn<>("Age");
        ageColumn.setPrefWidth(150);
        ageColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().age;
            }
        });

        JFXTreeTableColumn<User, String> deptColumn = new JFXTreeTableColumn<>("Department");
        deptColumn.setPrefWidth(150);
        deptColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().department;
            }
        });

        JFXTreeTableColumn<User, String> jobColumn = new JFXTreeTableColumn<>("Job");
        jobColumn.setPrefWidth(150);
        jobColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().job;
            }
        });

        JFXTreeTableColumn<User, String> salaryColumn = new JFXTreeTableColumn<>("Salary");
        salaryColumn.setPrefWidth(150);
        salaryColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().salary;
            }
        });

        ObservableList<User> users = FXCollections.observableArrayList();
        DBHelper db = new DBHelper();
        db.open();
        String[][] persons = db.SelectFromTable("person");
        String[][] departments = db.SelectFromTable("departmenttype");
        String[][] jobs = db.SelectFromTable("jobtype");
        String[][] personnels = db.SelectFromTable("personnel");

        if (persons.length == 0) {
            System.out.println("Hata: Hiç Personel Bulunmamaktadır.");
            return;
        }

        User[] user = new User[persons.length];
        for (int i = 0; i < persons.length; i++) {
            Date date = null;

            try {
                date = CommonFunction._formatDate(persons[i][3]);
            } catch (ParseException ex) {
                System.out.println("Hatalı Çevirme: " + ex);
            }

            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(date.getTime());

            //create calendar object for current day
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);
            int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

            String pName = persons[i][1];
            String pLastName = persons[i][2];
            String pAge = "" + years;
            String pDept = "";
            String personnelDept = "";
            String personnelJob = "";
            String personnelSalary = "";

            for (int j = 0; j < personnels.length; j++) {
                if (persons[i][0].equals(personnels[j][0])) {
                    personnelDept = personnels[j][1];
                    personnelJob = personnels[j][2];
                    personnelSalary = personnels[j][3];
                    break;
                }
            }
            for (int j = 0; j < departments.length; j++) {
                if (personnelDept.equals(departments[j][0])) {
                    personnelDept = departments[j][1];
                    break;
                }
            }

            for (int j = 0; j < jobs.length; j++) {
                if (personnelJob.equals(jobs[j][0])) {
                    personnelJob = jobs[j][1];
                    break;
                }
            }
            user[i] = new User(pName, pLastName, pAge, personnelDept, personnelJob, personnelSalary);
        }

        for (int i = 0; i < user.length; i++) {
            users.add(user[i]);
        }

        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(nameColumn, lastNameColumn, ageColumn, deptColumn, jobColumn, salaryColumn);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

        db.close();
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