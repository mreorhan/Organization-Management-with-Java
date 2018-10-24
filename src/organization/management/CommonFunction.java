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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Emre
 */
public  class CommonFunction {
    
public  void _show(String url){
         try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        }catch(Exception e){
            System.out.println("Cant load");
        }
}
public Date _formatDate(String string)throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        Date date = format.parse(string);
        return date;
}
}
