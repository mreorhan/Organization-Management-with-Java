/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

import java.util.Date;

/**
 *
 * @author Emre
 */
public class Personnel extends Person{
    
    //Inheritance Constructure
    public Personnel(String name, String lastName, Date birthDate) {
        super(name, lastName, birthDate);
    }
    
    //Variables
    private int departmentID;
    private int jobID;
    private double salary;
    private Date recruitmentDate;

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(Date recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

}
