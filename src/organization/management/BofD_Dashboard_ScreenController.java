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
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;
import organization.process.DBHelper;
import organization.process.InstantData;
import organization.process.Meeting;
import organization.process.Person;
import organization.process.Product;

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
    private JFXComboBox<String> cb_projectLeader;
    @FXML
    private JFXButton btn_createProduct;
    @FXML
    private JFXToggleButton chk_isActive;
    @FXML
    private Label lbl_job;
    @FXML
    private JFXPasswordField txt_settings_password;
    @FXML
    private JFXButton btn_settings_edit;
    @FXML
    private JFXTreeTableView<ProductClass> treeViewProducts;
    @FXML
    private JFXTextField filterProducts;
    @FXML
    private JFXTextField filterEmployees1;

    private  CommonFunction fo = new CommonFunction();
    
    private  CommonFunction fo2 = new CommonFunction();
    private JFXPasswordField txt_settings_password_2;
    @FXML
    private Label lbl_settings;
    @FXML
    private JFXPasswordField txt_settings_password_again;
    @FXML
    private JFXButton exportAsProduct;
    @FXML
    private Label lblMeetingTitle;
    @FXML
    private Label lblMeetingDesc;
    @FXML
    private JFXTextField txt_meetingTitle;
    @FXML
    private JFXTextArea ta_meetingDescription;
    @FXML
    private JFXDatePicker txt_meetingDate;
    @FXML
    private Label lblMeetingDate;
    @FXML
    private JFXButton btn_createMeeting;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxLoad();
        /*  Export Data Using Like That
        CommonFunction fo = new CommonFunction();
        try{
        fo._exportData("deneme", "test.txt");
        }catch(IOException e){
            System.out.println(e);
        }
        */
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
  public void ComboBoxLoad() {
        String[] array_department = null;
        DBHelper db = new DBHelper();
        db.open();
        array_department = db._getDBData("personnel","inner JOIN person ON personnel.PersonnelID=person.PersonID INNER join departmenttype on personnel.DepartmentID=departmenttype.DepartmentID where departmenttype.DepartmentID=1",8);
        db.close();
        // TODO: çekilecek veriler -job-  aşağıdaki gibi eklenecek
        cb_projectLeader.getItems().addAll(array_department);
    }
    @FXML
    private void createProduct(MouseEvent event) throws ParseException {
        //Products add section START
        DBHelper db = new DBHelper();
        db.open();
        
        String startDate = txt_productStartingDate.getValue().toString();
        String dueDate = txt_productDueDate.getValue().toString();
        String chk="0";
        System.out.println(chk_isActive.isSelected());
        if(chk_isActive.isSelected())
            chk="1";
        else
            chk="0";
        Product product1 = new Product(txt_productName.getText(), ta_productDescription.getText(), fo._formatDate(startDate),InstantData.person.getName()+InstantData.person.getLastName(),cb_projectLeader.getValue().toString(),fo2._formatDate(dueDate),  chk);
        if (db.Insert(product1)) {
            //Yeni formu açmak için kullanıyoruz
            System.out.println("insert ok ");
            getProductItems();
        } else {
            System.out.println("insert not working ");
        }

        db.close();
         //Products add section END
    }

    @FXML
    private void passwordUpdate(MouseEvent event) {
        String password1= txt_settings_password.getText();
        String password2=txt_settings_password_again.getText();
        System.out.println(password1+""+password2);
        if(!password1.equals(password2))
            lbl_settings.setText("Passwords must match!");
        else if(password1.equals("") ||password2.equals(""))
            lbl_settings.setText("You must be fill required fields");
        else{
            DBHelper db = new DBHelper();
            db.open();
            lbl_settings.setText(db._getUpdateData(password1.toString(), 3));
            db.close();
        }
    }

    @FXML
    private void exportProducts(MouseEvent event) throws IOException {
        DBHelper db = new DBHelper();
        db.open();
           if( db._exportXMLData("products")==1)
           {
                fo._modal("Info", "The data was successfully exported.", "OK", panel);
           }
           else{
            fo._modal("Error", "Something went wrong. Please try again.", "OK", panel);
           }
        db.close();
    }

    @FXML
    private void createMeeting(MouseEvent event) throws ParseException {
             //Meeting add section START
        DBHelper db = new DBHelper();
        db.open();
        Meeting meeting = new Meeting(txt_meetingTitle.getText(), ta_meetingDescription.getText(), fo._formatDate(txt_meetingDate.getValue().toString()), (int) InstantData.person.getPersonID());
        if (db.Insert(meeting)) {
            fo._modal("Info", "The meeting was recorded successfully", "OK", panel);
        } else {
            fo._modal("Info", "Opps! Something went wrong", "OK", panel);
        }

        db.close();
         //Meeting add section END
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

    class ProductClass extends RecursiveTreeObject<ProductClass> {
         StringProperty pID;
        StringProperty productName;
        StringProperty productDescription;
        StringProperty productStartingDate;
        StringProperty createdBy;
        StringProperty projectLeader;
        StringProperty productDueDate;
        StringProperty isActive;
         public ProductClass(String productName, String productDescription, String productStartingDate, String createdBy, String projectLeader, String productDueDate, String isActive) {
            String pid= new String();
             this.pID = new SimpleStringProperty(pid);
             this.productName = new SimpleStringProperty(productName);
            this.productDescription = new SimpleStringProperty(productDescription);
            this.productStartingDate = new SimpleStringProperty(productStartingDate);
            this.createdBy = new SimpleStringProperty(createdBy);
            this.projectLeader = new SimpleStringProperty(projectLeader);
            this.productDueDate = new SimpleStringProperty(productDueDate);
            this.isActive = new SimpleStringProperty(isActive);
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
    public void getProductItems(){
       //TreeView Başlangıç
        JFXTreeTableColumn<ProductClass, String> productNameColumn = new JFXTreeTableColumn<>("Product Name");
        productNameColumn.setPrefWidth(150);
        productNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().productName;
            }
        });

        JFXTreeTableColumn<ProductClass, String> productDescriptionColumn = new JFXTreeTableColumn<>("Product Description");
        productDescriptionColumn.setPrefWidth(220);
        productDescriptionColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().productDescription;
            }
        });

        JFXTreeTableColumn<ProductClass, String> productStartingDateColumn = new JFXTreeTableColumn<>("Starting Date");
        productStartingDateColumn.setPrefWidth(150);
        productStartingDateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().productStartingDate;
            }
        });

        JFXTreeTableColumn<ProductClass, String> createdByColumn = new JFXTreeTableColumn<>("Created By");
        createdByColumn.setPrefWidth(150);
        createdByColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().createdBy;
            }
        });

        JFXTreeTableColumn<ProductClass, String> projectLeaderColumn = new JFXTreeTableColumn<>("Project Leader");
        projectLeaderColumn.setPrefWidth(150);
        projectLeaderColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().projectLeader;
            }
        });

        JFXTreeTableColumn<ProductClass, String> productDueDateColumn = new JFXTreeTableColumn<>("Product Due Date");
        productDueDateColumn.setPrefWidth(150);
        productDueDateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().productDueDate;
            }
        });
        
        JFXTreeTableColumn<ProductClass, String> productisActiveColumn = new JFXTreeTableColumn<>("Is Active");
        productisActiveColumn.setPrefWidth(80);
        productisActiveColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductClass, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductClass, String> param) {
                return param.getValue().getValue().isActive;
            }
        });

        ObservableList<ProductClass> productsList2 = FXCollections.observableArrayList();
        DBHelper db = new DBHelper();
        db.open();
        String[][] productsList = db.SelectFromTable("products"); // productsList tablosundan seçilir ve productsList arrayine atılır.

        if (productsList.length == 0) {
            System.out.println("Hata: Hiç Personel Bulunmamaktadır.");
            return;
        }

        ProductClass[] productArray = new ProductClass[productsList.length];
        for (int i = 0; i < productsList.length; i++) {
            Date date = null;

            try {
                date = CommonFunction._formatDate(productsList[i][3]);
            } catch (ParseException ex) {
                System.out.println("Hatalı Çevirme: " + ex);
            }

            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(date.getTime());

            //create calendar object for current day
            Calendar now = Calendar.getInstance();
            String pID = productsList[i][0];
            String pName = productsList[i][1];
            String pLastName = productsList[i][2];
            String pAge = productsList[i][3];
            String pDept = productsList[i][4];
            String personnelDept = productsList[i][5];
            String personnelJob = productsList[i][6];
            String isActive = productsList[i][7];
            String x;
            if(isActive.equals("0"))
                x="No";
            else
                x="Yes";
            productArray[i] = new ProductClass(pName, pLastName, pAge,pDept, personnelDept, personnelJob,x);
        }

        for (int i = 0; i < productArray.length; i++) {
            productsList2.add(productArray[i]);
        }

        final TreeItem<ProductClass> root = new RecursiveTreeItem<ProductClass>(productsList2, RecursiveTreeObject::getChildren);
        treeViewProducts.getColumns().setAll(productNameColumn, productDescriptionColumn, productStartingDateColumn, createdByColumn, projectLeaderColumn, productDueDateColumn,productisActiveColumn);
        treeViewProducts.setRoot(root);
        treeViewProducts.setShowRoot(false);
        
        db.close();
    
           // Create ContextMenu
        ContextMenu contextMenu = new ContextMenu();
 
        MenuItem item1 = new MenuItem("Mark as Over");
        item1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //TODO: action for mark as over
            }
        });
        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //TODO: action for delete
               String x=  treeView.getSelectionModel().getSelectedItem().getParent().getValue().toString();
                System.out.println("listid"+productsList[0][0] +"++"+ x);
                DBHelper db = new DBHelper();
                db.open();
                db.Delete("products", "productID", 4);
                db.close();
                getProductItems();
            }
        });
 
        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1, item2);
        
        treeViewProducts.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                contextMenu.show(treeViewProducts, event.getScreenX(), event.getScreenY());
            }
        });
    }
    
    
    
    @FXML
    private void products(MouseEvent event) throws ParseException {
        tab.getSelectionModel().select(products);
        lbl_Title.setText("Products");
      getProductItems();
        
        
        filterProducts.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                    treeViewProducts.setPredicate(new Predicate<TreeItem<ProductClass>>() {
                        @Override
                        public boolean test(TreeItem<ProductClass> t) {
                          Boolean flag = t.getValue().productName.getValue().toLowerCase().contains(newValue) || t.getValue().productDescription.getValue().toLowerCase().contains(newValue)|| t.getValue().projectLeader.getValue().toLowerCase().contains(newValue);
                          return flag;
                        }
                    });
            }
        });
    }

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
        
        
        filterEmployees1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                    treeView.setPredicate(new Predicate<TreeItem<User>>() {
                        @Override
                        public boolean test(TreeItem<User> t) {
                          Boolean flag = t.getValue().name.getValue().toLowerCase().contains(newValue) || t.getValue().lastName.getValue().toLowerCase().contains(newValue)|| t.getValue().department.getValue().toLowerCase().contains(newValue);
                          return flag;
                        }
                    });
            }
        });
    }

    @FXML
    private void meetings(MouseEvent event) throws ParseException, SQLException {
        tab.getSelectionModel().select(meetings);
        lbl_Title.setText("Meetings");
        
       String[] meeting = null;
        DBHelper db = new DBHelper();
        db.open();
        meeting = db._getLastMeeting();
        db.close();
        
        lblMeetingTitle.setText(meeting[0]);
        lblMeetingDesc.setText(meeting[1]);
        lblMeetingDate.setText(meeting[2]);
    }

    @FXML
    private void settings(MouseEvent event) {
        tab.getSelectionModel().select(settings);
        lbl_Title.setText("Settings");
    }

}
