/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import organization.process.DBHelper;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class Create_New_Employee_ScreenController implements Initializable {

    @FXML
    private Label lbl_register;
    @FXML
    private Label lbl_Message;
    @FXML
    private JFXDatePicker txt_recruitmentDate;
    @FXML
    private JFXTextField txt_salary;
    @FXML
    private JFXButton btn_createEmployee;
    @FXML
    private JFXComboBox<String> cb_department;
    @FXML
    private JFXComboBox<String> cb_job;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxLoad();
    }

    public void ComboBoxLoad() {
            String[] array=null;
            DBHelper db = new DBHelper();
            db.open();
            try{
            array = db._getDBData("departmenttype", "DepartmentName");
            }catch (Exception e){
                System.out.println(e);
                
            }

            //cb_department.getItems().addAll(db._getDBData("departmenttype","DepartmentName"));
            db.close();
            // TODO: çekilecek veriler -job-  aşağıdaki gibi eklenecek
            cb_department.getItems().addAll(array);
           
        
    }

    @FXML
    private void signUp(MouseEvent event) {
    }

    @FXML
    private void createEmployeeAction(ActionEvent event) {
    }

}
