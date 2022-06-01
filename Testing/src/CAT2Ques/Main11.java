/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAT2Ques;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
class ItemType {
    private String name;
    private Double deposit;
    private Double costPerDay;
    
    
    public void setmobile(String name) {
        this.name=name;
    }
    public void setalternateMobile(Double deposit) {
        this.deposit=deposit;
    }
    public void setlandline(Double costPerDay) {
        this.costPerDay=costPerDay;
    }

    public String getmobile() {
        return name;
    }
    public Double getalternateMobile() {
        return deposit;
    }
    public Double getlandline() {
        return costPerDay;
    }
    
    ItemType(String name, String deposit, String costPerDay) {
        this.name=name;
        this.deposit=Double.parseDouble(deposit);
        this.costPerDay=Double.parseDouble(costPerDay);
    }
    
    public String toString() {
        return name+" "+deposit+" "+costPerDay;
    }
    
}
public class Main11 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        ItemType it;
        try {
            String str1=input.next();
            
            String str2 = input.next();
            str2 += input.nextLine();
            
            String str3 = input.next();
            str3 += input.nextLine();
            it=new ItemType(str1,str2,str3);
            System.out.println(it.toString());
        }
        catch(NumberFormatException nfe) {
            System.out.println("java.lang.NumberFormatException: "+nfe.getMessage());
        }
    }
}
