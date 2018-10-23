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
public class Company {
    private String companyName;
    private Date companyCreateDate;
    private Adress adress;

    public Company(String companyName, Date companyCreateDate, Adress adress) {
        this.companyName = companyName;
        this.companyCreateDate = companyCreateDate;
        this.adress = adress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCompanyCreateDate() {
        return companyCreateDate;
    }

    public void setCompanyCreateDate(Date companyCreateDate) {
        this.companyCreateDate = companyCreateDate;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
    
    
    
}
