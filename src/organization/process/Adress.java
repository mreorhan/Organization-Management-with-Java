/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

/**
 *
 * @author OGUZHAN
 */
public class Adress {
    private int postCode;
    private String country;
    private String province;
    private String street;

    public Adress(int postCode, String country, String province, String street) {
        this.postCode = postCode;
        this.country = country;
        this.province = province;
        this.street = street;       
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    
}
