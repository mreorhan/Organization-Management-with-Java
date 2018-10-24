/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import organization.process.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author OGUZHAN
 */
public class SignIn_ScreenController implements Initializable {
    
    
    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private Label lbl_register;
    @FXML
    private JFXButton btn_fotgotpassword;
    @FXML
    private JFXButton btn_login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void loginClickAction(ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();
        DBHelper db= new DBHelper();
        db.open();
        boolean user = db.UserControl(username, password);
        System.out.println(user);
        db.close();
    }

    @FXML
    private void signUpClick(MouseEvent event)  throws  Exception{
        /*
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SignUp_screen.fxml"));
        rootPane.getChildren().setAll(pane);
        System.err.println("ok");*/
    }

    
}