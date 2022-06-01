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
public class C_Team {
    public static void main(String rag[]) {
        Scanner input=new Scanner(System.in);
        int zero=input.nextInt();
        int one=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        while(zero>0) {
//            System.out.println(zero+" "+one);
            if(cnt%2==0 && zero>0) {
                ans.append('0');
                zero--;
            }
            if(cnt%2==1 && one>0) {
                ans.append('1');
                one--;
                if(one>zero-1) {
                    ans.append('1');
                    one--;
                }
            }
            cnt++;
        }
        if(one>0) {
            ans.insert(0,'1');
            one--;
        }
        if(one>0) {
            ans.insert(0,'1');
            one--;
        }
        while(one>0) {
            ans.append('1');
            one--;
        }
        boolean is_pos=true;
        for(int i=0;i<ans.length()-1;i++) {
            if(ans.charAt(i)=='0' && ans.charAt(i+1)=='0') {
                is_pos=false;
                break;
            }
        }
        for(int i=0;i<ans.length()-2;i++) {
            if(ans.charAt(i)=='1' && ans.charAt(i+1)=='1' && ans.charAt(i+2)=='1') {
                is_pos=false;
                break;
            }
        }
        if(!is_pos) {
            ans=new StringBuilder("-1");
        }
        System.out.println(ans);
    }
}
