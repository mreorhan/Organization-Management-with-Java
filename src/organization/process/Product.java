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
public class Product {

    private int productID;
    private String productName;
    private String productDescription;
    private Date productStartingDate;
    private String createdBy;
    private String projectLeader;
    private Date productDueDate;
    private String isActive;

     public Product(String productName, String productDescription, Date productStartingDate, String createdBy, String projectLeader, Date productDueDate, String isActive) {
        this.productName= productName;
        this.productDescription = productDescription;
        this.productStartingDate = productStartingDate;
        this.createdBy= createdBy;
        this.projectLeader = projectLeader;
        this.productDueDate = productDueDate;
        this.isActive = isActive;
    }
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getProductStartingDate() {
        return productStartingDate;
    }

    public void setProductStartingDate(Date productStartingDate) {
        this.productStartingDate = productStartingDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Date getProductDueDate() {
        return productDueDate;
    }

    public void setProductDueDate(Date productDueDate) {
        this.productDueDate = productDueDate;
    }

    public String isIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
