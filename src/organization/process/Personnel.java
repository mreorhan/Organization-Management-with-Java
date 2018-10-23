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
public class Personnel extends Person{
    
    //Inheritance Constructure
    public Personnel(String name, String lastName, Date birtDate, long ID) {
        super(name, lastName, birtDate, ID);
    }
    
    //Variables
    private DepartmentType department;
    private JobType job;
    private double salary;
    private Date dateOfEmployment;

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }

    public JobType getJob() {
        return job;
    }

    public void setJob(JobType job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

}
