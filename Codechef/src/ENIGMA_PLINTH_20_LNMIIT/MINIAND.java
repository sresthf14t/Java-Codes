
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENIGMA_PLINTH_20_LNMIIT;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class MINIAND {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            arr=in_arr(arr);
            int odd_arr[]=count_odd(arr);
            int query=input.nextInt();
            for(int q=0;q<query;q++) {
                int l=input.nextInt();
                int r=input.nextInt();
                int no_of_odd=odd_arr[r-1]-odd_arr[l-1];
                if(no_of_odd%2==0) {
                    System.out.println("EVEN");
                }
                else {
                    System.out.println("ODD");
                }
            }
        }
    }
    
    public static int[] in_arr(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextInt();
        }
        return arr;
    }
    
    public static int[] count_odd(int arr[]) {
        int odd_arr[]=new int[arr.length];
        int count_odd=0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]%2==1) {
                count_odd++;
            }
            odd_arr[i]=count_odd;
        }
        return odd_arr;
    } 
}
