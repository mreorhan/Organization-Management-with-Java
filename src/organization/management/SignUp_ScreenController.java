/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import organization.process.*;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class SignUp_ScreenController implements Initializable {

    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXTextField txt_lastName;
    @FXML
    private JFXDatePicker txt_birthDay;
    @FXML
    private Label lbl_register;
    @FXML
    private Label lbl_Message;
    @FXML
    private JFXButton btn_signUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void signUpClickAction(ActionEvent event) throws ParseException {
   
    }


    private void signIn(ActionEvent event) {
        //Yeni formu açmak için kullanıyoruz
        CommonFunction fo=new CommonFunction();
        fo._show("SignIn_Screen.fxml");
    }

    @FXML
    private void signUp(MouseEvent event) {
        //Yeni formu açmak için kullanıyoruz
         CommonFunction fo=new CommonFunction();
        fo._show("SignIn_Screen.fxml");
    }

    @FXML
    private void signUpEvent(ActionEvent event) throws ParseException{
        String string = txt_birthDay.getValue().toString();
        CommonFunction fa = new CommonFunction();
        
        Person p1 = new Person(txt_name.getText(),txt_lastName.getText(), fa._formatDate(string));
        p1.setUsername(txt_username.getText());
        p1.setPassword(txt_password.getText());
        System.out.println(txt_password.getText());
        
        
        DBHelper dp = new DBHelper();
        dp.open();
        
        
       
        if(dp.Insert(p1)){
            //Yeni formu açmak için kullanıyoruz
         CommonFunction fo=new CommonFunction();
        fo._show("Create_New_Employee_Screen.fxml");
        }
        else
            System.out.println("insert not working ");
        
        dp.close();
    }
    
}
