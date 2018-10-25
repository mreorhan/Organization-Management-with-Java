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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import organization.process.*;

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
        String[] array_department = null;
        String[] array_job = null;
        DBHelper db = new DBHelper();
        db.open();
        array_department = db._getDBData("departmenttype");
        array_job = db._getDBData("jobtype");
        db.close();
        // TODO: çekilecek veriler -job-  aşağıdaki gibi eklenecek
        cb_department.getItems().addAll(array_department);
        cb_job.getItems().addAll(array_job);
    }

    @FXML
    private void signUp(MouseEvent event) {
        System.out.println("ifü" + cb_job.getValue());
    }

    @FXML
    private void createEmployeeAction(ActionEvent event) {
        DBHelper db = new DBHelper();
        db.open();
        double salary = Double.parseDouble(txt_salary.getText());
        String departmenTypeName = cb_department.getValue();
        String jobTypeName = cb_job.getValue();

        String req = txt_recruitmentDate.getValue().toString();
        Date tarih = null;

        try {

            tarih = CommonFunction._formatDate(req);
        } catch (Exception e) {
            System.out.println("Parse Exception");
        }

        int departmentTypeID = db.ReturnID("departmenttype", departmenTypeName);
        int jobTypeID = db.ReturnID("jobtype", jobTypeName);
        int personID = db.ReturnID(InstantData.person);

        if (departmentTypeID == -1 || jobTypeID == -1) {
            System.out.println("Personnel is not created");
            return;
        }

        Personnel p1 = new Personnel("", "", tarih);
        p1.setDepartmentID(departmentTypeID);
        p1.setJobID(jobTypeID);
        p1.setSalary(salary);
        p1.setRecruitmentDate(tarih);
        p1.setPersonID(personID);

        if (!db.Insert(p1)) {
            System.out.println("Error: Personnel is not Created!");
        }
        
        System.out.println("Personnel is Created***");
        db.close();
    }

}
