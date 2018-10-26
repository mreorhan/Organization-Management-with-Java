/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import organization.process.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
    @FXML
    private Label lbl_Message;
    @FXML
    private AnchorPane panel;
    
    private CommonFunction fo=new CommonFunction();
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
         if(user){
            //Yeni formu açmak için kullanıyoruz
        fo._show("Dashboard_Screen.fxml",panel);
        }
        else {
             lbl_Message.setText("Error");
        }
        db.close();
    }
    @FXML
    private void signUpClick(MouseEvent event) {
        fo._show("SignUp_Screen.fxml",panel);
    }
    
}
