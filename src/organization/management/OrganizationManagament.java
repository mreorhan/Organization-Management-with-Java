/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.management;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import organization.process.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author OGUZHAN
 */
public class OrganizationManagament extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn_Screen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Software Company Management");
        stage.setScene(scene);
        stage.show();
        
        /*Calendar takvim = new GregorianCalendar(1984, 01, 01);
        Date tarih = takvim.getTime();
        
        Personnel p = new Personnel("Ahmet", "Sağlam", tarih);
        p.setDepartmentID(1);
        p.setJobID(0);
        p.setSalary(4000);
        p.setRecruitmentDate(tarih);
        
        Person p1 = new Person("Ahmet", "Sağlam", tarih);
        p1.setUsername("ahmet");
        p1.setPassword("1234");
        
            
        DBHelper dp = new DBHelper();
        dp.open();
        
        
        dp.Insert(p1);
        int personID = dp.SelectPersonID(p1);
        
        p.setPersonID(personID);
        System.out.println(p.getPersonID());
        System.out.println(p.getRecruitmentDate());
        dp.Insert(p);
        
        dp.test();
        dp.close();
        */
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
