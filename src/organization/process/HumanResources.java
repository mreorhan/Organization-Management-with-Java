/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization.process;

import java.util.ArrayList;

/**
 *
 * @author OGUZHAN
 */
public class HumanResources {
    private ArrayList<Personnel> personnels;
    private Company company;
    
    public boolean EmployeeAdd(Personnel personnel){
        if(EmployeeControl(personnel) != -1)
            return false;
        personnels.add(personnel);
        return true;
    }
    
    public boolean EmployeePop(Personnel personnel){
        int index = EmployeeControl(personnel);
        
        if(index == -1 )
            return  false;
        
        personnels.remove(index);
        return true;
    }
    
    public int EmployeeControl(Personnel personnel){
        for(int i=0;i<personnels.size();i++ ){
            if(personnel.getID() == personnels.get(i).getID())
                return i;
        }
        return  -1;
    }

}
