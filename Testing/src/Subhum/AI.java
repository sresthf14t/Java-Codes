/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subhum;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;

class ItemType {
    private String name;
    private double deposite,costPerDay;
    ItemType(String n,double d,double c) {
        name=n;
        deposite=d;
        costPerDay=c;
    }
    public String gn() {
        return name;
    } 
    
    public String gd() {
        return String.format("%.1f", deposite);
    } 
    
    public String gc() {
        return String.format("%.1f", costPerDay);
    } 
}
public class AI {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        
        int n=input.nextInt();
        ItemType it[]=new ItemType[n];
        for(int i=0;i<n;i++) {
            it[i]=new ItemType(input.next(),input.nextDouble(),input.nextDouble());
        }
        System.out.printf("%-20s%-20s%-20s","Name","Deposit","costPerDay");
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.printf("%-20s%-20s%-20s",it[i].gn(),it[i].gd(),it[i].gc());
            System.out.println();
        }
    }
}
