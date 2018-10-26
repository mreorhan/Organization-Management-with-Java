/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Emre
 */
public  class CommonFunction {
    
public  void _show(String url,AnchorPane ap){
         try{
                Parent home_page_parent = FXMLLoader.load(getClass().getResource(url));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ap.getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show();  
        }catch(Exception e){
            System.out.println("Cant load");
        }
}
public static Date _formatDate(String string)throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        Date date = format.parse(string);
        return date;
}

}