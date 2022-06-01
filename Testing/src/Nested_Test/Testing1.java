/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nested_Test;

/**
 *
 * @author User
 */

public class Testing1 {
    public static void main(String srgd[]) {
        int count=0;
        for(int i=0;i<1000;i++) {
            String tmp=""+i;
            int count_0=0;
            for(int j=0;j<tmp.length();j++) {
                if(tmp.charAt(j)=='0') {
                    count_0++;
                }
            }
            if(tmp.length()==1) {
                count_0+=2;
            }
            if(tmp.length()==2) {
                count_0+=1;
            }
            if(count_0%2==0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
