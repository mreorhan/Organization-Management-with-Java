/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

import java.util.Date;

/**
 *
 * @author OGUZHAN
 */
public class Person {
    private String name;
    private String lastName;
    private Date birtDate;
    private long ID;

    public Person(String name, String lastName, Date birtDate, long ID) {
        this.name = name;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    
    
}
