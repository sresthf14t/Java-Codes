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
public class A_Love_Triangle {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt()-1;
        }
        int count=0;
        for(int i=0;i<n;i++) {
            if(arr[arr[arr[i]]]==i) {
                count++;
            }
        }
        if(count>0) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
