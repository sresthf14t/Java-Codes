/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_613_Div_2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class B {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            long arr[]=new long[n];
            long sum=0,part_sum=0,temp_sum=0;
            boolean bool=false;
            int count=0;
            for(int i=0;i<arr.length;i++,count++) {
                arr[i]=input.nextLong();
                sum+=arr[i];
                temp_sum+=arr[i];
                if(temp_sum<=0) {
                    temp_sum=count=0;
                }
                if(temp_sum>part_sum) {
                    part_sum=temp_sum;
                }
            }
            if(part_sum<sum) {
                System.out.println("YES");
            }
            else if(part_sum==sum   && count==arr.length) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
