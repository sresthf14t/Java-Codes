/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeCraft_20_Div_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.io.*; 
import java.util.*; 
public class B {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            StringBuilder str=new StringBuilder(input.next());
            String arr[]=new String[n];
            String arr_cpy[]=new String[n];
            arr[0]=""+str;
            arr_cpy[0]=""+str;
            for(int i=1;i<str.length();i++) {
                StringBuilder str_tmp=new StringBuilder(str);
                StringBuilder tmp=new StringBuilder("");
                for(int j=0;j<i;j++) {
                    tmp.append(str.charAt(j));
                }
                if(n%2==0 && i%2==1) {
                   tmp=tmp.reverse(); 
                }
                else if(n%2==1 && i%2==0) {
                   tmp=tmp.reverse(); 
                }
                str_tmp.delete(0, i);
                str_tmp.append(tmp);
                arr[i]=""+str_tmp;
                arr_cpy[i]=""+str_tmp;
            }
            Arrays.sort(arr);
            int indx=-1;
            for(int i=0;i<n;i++) {
                if(arr_cpy[i].equals(arr[0])) {
                    indx=i;
                    break;
                }
            }
            System.out.println(arr[0]+"\n"+(indx+1));
        }
    }
}
