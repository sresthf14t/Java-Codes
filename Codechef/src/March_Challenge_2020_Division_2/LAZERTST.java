/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Challenge_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class LAZERTST {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
           int n=input.nextInt();
           int m=input.nextInt();
           int k=input.nextInt();
           int q=input.nextInt();
           int l[]=new int[q];
           int r[]=new int[q];
           for(int i=0;i<q;i++) {
               l[i]=input.nextInt();
               r[i]=input.nextInt();
           }
           String ans="2";
           if(m<=10) {
               for(int i=0;i<q;i++) {
                   ans+=" "+(m-1);
               }
               System.out.println(ans);
               System.out.flush();
               if(input.nextInt()==-1) {
                   System.exit(0);
               }
               continue;
           }
           for(int i=0;i<q;i++) {
               int max = m; 
               int min = 2; 
               int range = max - min + 1; 
               int y = m/2;
               System.out.println(1+" "+l[i]+" "+r[i]+" "+y);
               System.out.flush();
               int h=input.nextInt();
               ans+=" "+h;
           }
           System.out.println(ans);
           System.out.flush();
           if(input.nextInt()==-1) {
               System.exit(0);
           }
        }
    }
}
