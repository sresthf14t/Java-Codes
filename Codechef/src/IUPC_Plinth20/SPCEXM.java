/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IUPC_Plinth20;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class SPCEXM {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
         for(int test=1;test<=t;test++) {
             int n=input.nextInt();
             int m=input.nextInt();
             int count=0;
             for(int i=1;i<=m;i++) {
                 input=new Scanner(System.in);
                 float arr[]=new float[n];
                 String str=input.nextLine();
                 arr=str_to_arr(str,n);
                 float arr_avg=avg(arr);
                 count+=(count(arr,arr_avg));
             }
             System.out.println(count);
         }
    }
    public static float[] in_arr(float arr[]) {
        Scanner input=new Scanner(System.in);
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.nextFloat();
        }
        return arr;
    }
    
    public static float avg(float arr[]) {
        float avg=0;
        for(int i=0;i<arr.length;i++) {
            avg+=arr[i];
        }
        avg/=arr.length;
        return avg;
    }
    
    public static float count(float arr[],float avg) {
        int count=0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]>=avg) {
                count++;
            }
        }
        return count;
    }
    
    public static float[] str_to_arr(String str,int n) {
        float arr[]=new float[n];
        int point=0;
        for(int i=0;i<str.length();i++) {
            String str_tmp="";
            while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9') {
                str_tmp+=str.charAt(i);
                i++;
            }
            arr[point]=str_to_int(str_tmp);
            point++;
        }
        return arr;
    }
    
     public static int str_to_int(String str) {
         int num=0;
         for(int i=0;i<str.length();i++) {
             num+=(num*10)+(str.charAt(i)-48);
         }
         return num;
     }
}
