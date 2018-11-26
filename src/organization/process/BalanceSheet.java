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
public class BalanceSheet {
    private int createdBy;
    private int income;
    private int expense;
    private  Date date;
    private String description;
    
    public BalanceSheet(int createdBy,int income,int expense, Date date,String description){
        this.createdBy=createdBy;
        this.income=income;
        this.expense=expense;
        this.date=date;
        this.description=description;
    }
    
    
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
