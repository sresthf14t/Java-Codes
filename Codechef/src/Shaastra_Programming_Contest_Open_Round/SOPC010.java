/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shaastra_Programming_Contest_Open_Round;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class SOPC010 {
    static Scanner input=new Scanner(System.in);
    static long arr[]=new long[38];
    static int indx_ptr=0;
    public static void main(String args[]) {
        creating_arr();
        
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long n=input.nextInt();
            System.out.println(sum_indx(n));
        }
    }
    
    public static void creating_arr() {
        long lim=(long)(Math.pow(10, 18));
        for(long i=1;i<=lim;i*=3) {
            arr[indx_ptr]=i;
            indx_ptr++;
        }
    } 
    public static long sum_indx(long n) {
        long sum=0;
        int i=0;
        for(;i<arr.length;i++) {
            sum+=arr[i];
            if(sum>n) {
                i--;
                break;
            }
            else if(sum==n) {
                break;
            }
        }
        int count=0;
        for(int j=i+1;j<arr.length;j++) {
            if(arr[j]<=n) {
                count++;
            }
            else {
                break;
            }
        }
        long tot=(long)(Math.pow(2, i+1));
        tot+=count;
        tot-=1;
        return tot;
    }
}
