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
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane panel;

    
      private  CommonFunction fo = new CommonFunction();
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
        fo._show("SignIn_Screen.fxml",panel);
    }

    @FXML
    private void signUp(MouseEvent event) {
        //Yeni formu açmak için kullanıyoruz
        fo._show("SignIn_Screen.fxml",panel);
    }

    @FXML
    private void signUpEvent(ActionEvent event) throws ParseException {
        DBHelper db = new DBHelper();
        db.open();
        
        
        String string = txt_birthDay.getValue().toString();

        Person p1 = new Person(txt_name.getText(), txt_lastName.getText(), fo._formatDate(string));
        p1.setUsername(txt_username.getText());
        p1.setPassword(txt_password.getText());

        if (db.Insert(p1)) {
            //Yeni formu açmak için kullanıyoruz
            p1.setPersonID(db.ReturnID(p1));
            InstantData.person = p1;
            CommonFunction fo = new CommonFunction();
            fo._show("Create_New_Employee_Screen.fxml",panel);
        } else {
            System.out.println("insert not working ");
        }

        db.close();
    }

}
