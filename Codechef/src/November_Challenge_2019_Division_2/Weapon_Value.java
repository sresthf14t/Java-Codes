/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Challenge_2019_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Weapon_Value {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=0;
        if(input.hasNextInt()) {
           t=input.nextInt(); 
        }
        for(int i=1;i<=t;i++) {
            int n=0;
            if(input.hasNextInt()) {
                n=input.nextInt();
            }
            int bin=input.nextInt(2);
            for(int j=1;j<n;j++) {
                int temp=0;
                if(input.hasNextInt()) {
                    temp=input.nextInt(2);
                }
                bin=bin^temp;
            }
            int count=0;
            String bin_str=dec_to_bin(bin);
            for(int j=0;j<bin_str.length();j++) {
                if(bin_str.charAt(j)=='1') {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    public static String dec_to_bin(int in) {
        String out="",temp="";
        while(in!=0) {
            temp+=""+(in%2);
            in/=2;
        }
        out=temp;
        return out;
    }
}
