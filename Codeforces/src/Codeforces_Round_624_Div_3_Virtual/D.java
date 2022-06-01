/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_624_Div_3_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=1;i<20000;i++) {
            for(int j=1;i*j<20000;j++) {
                for(int k=1;i*j*k<20000;k++) {
                    arr.add(i);
                    arr.add(i*j);
                    arr.add(i*j*k);
                }
            }
        }
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int a=input.nextInt();
            int b=input.nextInt();
            int c=input.nextInt();
            int ans[]=new int[3];
            int min_diff=199999999;
            for(int i=0;i<arr.size();i+=3) {
                int sum=Math.abs(a-arr.get(i))+Math.abs(b-arr.get(i+1))+Math.abs(c-arr.get(i+2));
                if(sum<min_diff) {
                    min_diff=sum;
                    ans[0]=arr.get(i);
                    ans[1]=arr.get(i+1);
                    ans[2]=arr.get(i+2);
                }
            }
            System.out.println(min_diff+"\n"+ans[0]+" "+ans[1]+" "+ans[2]);
            
        }
    }
}
