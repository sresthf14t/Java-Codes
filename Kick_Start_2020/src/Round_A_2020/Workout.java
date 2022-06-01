/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.util.Scanner;
public class Workout {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int k=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            ArrayList<Integer> diff_arr=new ArrayList<>();
            for(int i=0;i<n-1;i++) {
                diff_arr.add(arr[i+1]-arr[i]);
            }
            for(int i=0;i<k;i++) {
//                for(int j=0;j<diff_arr.size();j++) {
//                    System.out.print(diff_arr.get(j)+" ");
//                }
//                System.out.println();
                Collections.sort(diff_arr);
                Collections.reverse(diff_arr);
                int a=(int)Math.ceil((double)diff_arr.get(0)/2),b=diff_arr.get(0)-a;
                diff_arr.remove(0);
                diff_arr.add(a);
                diff_arr.add(b);
            }
            Collections.sort(diff_arr);
            Collections.reverse(diff_arr);
            System.out.println("Case #"+t+": "+diff_arr.get(0));
        }
    }
}
