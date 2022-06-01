/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_Taxi {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int one=0,two=0,three=0,count=0;
        for(int i=0;i<n;i++) {
            int in=input.nextInt();
            if(in==1) {
                one++;
            }
            else if(in==2) {
                two++;
            }
            else if(in==3) {
                three++;
            }
            else {
                count++;
            }
        }
        count+=two/2;
        two%=2;
        int min=Math.min(one,three);
        count+=min;
        one-=min;
        three-=min;
        if(one!=0) {
            if(two!=0 && one>0) {
                one-=Math.min(2, one);
                two=0;
                count++;
            }
            if(one>0) {
                count+=one/4;
                one%=4;
            }
        }
        count+=three;
        three=0;
        count+=two;
        two=0;
        if(one!=0) {
            count++;
        }
        System.out.println(count);
    }
}
