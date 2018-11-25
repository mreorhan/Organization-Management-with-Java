/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import organization.management.CommonFunction;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * @author OGUZHAN and MREORHAN
 */
public final class DBHelper {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/company?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn = null;
    private Statement stmt = null;

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

    public String[] _getDBData(String tableName) {
        List<String> list = new ArrayList<String>();
        try {
            String sql;
            sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Sorgu İşletilemedi: DBHelper._getDBData()");
        }
        String[] array = new String[list.size()];
        for (int i = 0, j = 0; i < list.size(); i++) {
            {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                array[j] = list.get(i);
                j++;
            }

        }
        return array;
    }
     public String[] _getDBData(String tableName,String tableExtra,int column) {
        List<String> list = new ArrayList<String>();
        try {
            String sql;
            sql = "SELECT * FROM " + tableName+" "+tableExtra;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(column));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Sorgu İşletilemedi: DBHelper._getDBData()");
        }
        String[] array = new String[list.size()];
        for (int i = 0, j = 0; i < list.size(); i++) {
            {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                array[j] = list.get(i);
                j++;
            }

        }
         System.out.println(array);
        return array;
    }
     public String _getUpdateData(String password,int userId) {
        try
    {
    
      // create the java mysql update preparedstatement
      String query = "update person set person.password=? where person.PersonID=?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString(1, password);
      preparedStmt.setInt(2, userId);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
        return "Your password changed!";
    }
    public String[][] SelectFromTable(String tablename) {
        String[][] table = null;
        List<String> list = new ArrayList<String>();

        try {
            String sql;
            sql = "SELECT * FROM " + tablename;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(rs.getString(1));
            }

            int rows = list.size();

            sql = "SELECT * FROM " + tablename;
            ResultSet rs2 = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs2.getMetaData();
            
            int columnsNumber = rsmd.getColumnCount();
            
            table = new String[rows][columnsNumber];
            
            int i = 0;
            
            while (rs2.next()) {

                for (int j = 1,k=0; j <= columnsNumber; j++,k++) {
                    table[i][k] = rs2.getString(j);
                }
                i++;
            }
            rs.close();
            rs2.close();
        } catch (SQLException ex) {
            System.out.println("Sorgu İşletilemedi: DBHelper.SelectFromTable");
        }

        return table;
    }

    public boolean Insert(Personnel p) {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sorgu = "INSERT INTO Personnel (PersonnelID,DepartmentID,JobID,Salary,recruitmentDate) VALUES("
                + p.getPersonID() + "," + p.getDepartmentID() + "," + p.getJobID() + "," + p.getSalary() + ",'" + df.format(p.getRecruitmentDate()) + "')";

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
                + df.format(p.getBirthDate()) + "','" + p.getUsername() + "','" + p.getPassword() + "')";
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
                if (rs.getString(5).equals(p.getUsername())) {
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

    public int ReturnID(String tableName, String findName) {
        int i = 0;
        try {

            String sql;
            sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                if (rs.getString(2).equals(findName)) {
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

    public Person UserControl(String username, String password) {
        Person p1= null;
        try {
            String sql;
            sql = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                if (rs.getString(5).equals(username) && rs.getString(6).equals(password)) {
                    p1 = new Person(rs.getString(2), rs.getString(3),CommonFunction._formatDate(rs.getString(4)));
                    p1.setPersonID(rs.getInt(1));
                    p1.setUsername(username);
                }

            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p1;
    }

    public boolean Insert(Product p) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sorgu = "INSERT INTO products (productName,productDescription,productStartingDate,createdBy,projectLeader,productDueDate,isActive) VALUES('" +
                p.getProductName()+ "','" +
                p.getProductDescription()+ "','"+ 
                df.format(p.getProductStartingDate()) + "','" + 
                p.getCreatedBy()+ "','" + 
                p.getProjectLeader()+ "','" + 
                df.format(p.getProductDueDate())+ "','" + 
                p.isIsActive()+
                "')";
        
        try {
            System.out.println(sorgu);
            stmt.executeUpdate(sorgu);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
