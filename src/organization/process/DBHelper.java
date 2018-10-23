/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OGUZHAN
 */
public final class DBHelper {

    private final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/company";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn = null;
    private Statement stmt = null;
    
    public DBHelper(){
        try{
            Class.forName("JDBC_DRIVER");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean Insert(Personnel p) {
        String sorgu = "INSERT INTO Personnel (PersonID,DerpartmentID,JobID,Salary,DateOfEmployee) VALUES(";

        return false;
    }

    public static boolean Insert(Person p) {
        String sorgu = "INSERT INTO Person (Name,LastName,BirthDate) VALUES(" + p.getName() + "," + p.getLastName() + ","
                + p.getBirthDate().toString() + ")";

        return false;
    }

    public static boolean Select() {
        return false;
    }
}
