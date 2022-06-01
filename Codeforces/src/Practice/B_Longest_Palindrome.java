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
public class B_Longest_Palindrome {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        StringBuilder str[]=new StringBuilder[n];
        StringBuilder front=new StringBuilder("");
        StringBuilder middle=new StringBuilder("");
        StringBuilder end=new StringBuilder("");
        for(int i=0;i<n;i++) {
            str[i]=new StringBuilder(input.next());
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                String str1=""+str[i],str2=""+(str[j].reverse());
                if(str1.equals(str2)) {
                    front.append(str[i]);
                    end.insert(0,str[j].reverse());
                    str[i]=new StringBuilder("");
                    str[j]=new StringBuilder("");
                    break;
                }
                str[j].reverse();
            }
        }
        for(int i=0;i<n;i++) {
            if(str[i].length()==0) {
                continue;
            }
            if((""+str[i]).equals(""+(str[i].reverse()))) {
                middle.append(str[i]);
                break;
            }
        }
        StringBuilder ans=new StringBuilder("");
        ans.append(front);
        ans.append(middle);
        ans.append(end);
        System.out.println(ans.length()+"\n"+ans);
    }
}
