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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OGUZHAN and MREORHAN
 */
public final class DBHelper {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/company?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private  Connection conn = null;
    private  Statement stmt = null;

    public DBHelper() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void test() {
        try {
            String sql;
            sql = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));

            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] _getDBData(String tableName){
        List<String> list = new ArrayList<String>();
        try {
            String sql;
            sql = "SELECT * FROM "+tableName;
            ResultSet rs = stmt.executeQuery(sql); 
            while (rs.next()) {
               list.add(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Sorgu İşletilemedi: DBHelper._getDBData()");
        }
        String[] array= new String[list.size()];
        for(int i=0,j=0;i<list.size();i++){
            {
                if(list.get(i) == null || list.get(i) == "")
                    continue;
                array[j]=list.get(i);
                j++;
            }
            
    }    
        return array;
    }

    public boolean Insert(Personnel p) {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sorgu = "INSERT INTO Personnel (PersonnelID,DepartmentID,JobID,Salary,recruitmentDate) VALUES("
                + p.getPersonID() + "," + p.getDepartmentID() + ","+p.getJobID()+"," + p.getSalary() + ",'" + df.format(p.getRecruitmentDate()) + "')";

        try {
            stmt.executeUpdate(sorgu);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean Insert(Person p) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sorgu = "INSERT INTO Person (Name,LastName,BirthDate,username,password) VALUES('" + p.getName() + "','" + p.getLastName() + "','"
                + df.format(p.getBirthDate()) + "','"+p.getUsername()+"','"+p.getPassword()+"')";
        try {
            stmt.executeUpdate(sorgu);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int ReturnID(Person p) {
        int i = 0;
        try {

            String sql;
            sql = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                if (rs.getString(5).equals(p.getUsername()) ) {
                    return rs.getInt(1);
                }

            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            return -1;
        }

        return -1;
    }
    
    public int ReturnID(String tableName,String findName) {
        int i = 0;
        try {

            String sql;
            sql = "SELECT * FROM "+ tableName;
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                if (rs.getString(2).equals(findName) ) {
                    return rs.getInt(1);
                }

            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            return -1;
        }

        return -1;
    }
    
    public  boolean UserControl(String username, String password){
        try {
            String sql;
            sql = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                if (rs.getString(5).equals(username) && rs.getString(6).equals(password) ) {
                    return true;
                }

            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
}
