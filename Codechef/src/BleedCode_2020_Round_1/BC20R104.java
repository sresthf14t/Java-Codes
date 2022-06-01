/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BleedCode_2020_Round_1;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class BC20R104 {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int m=input.nextInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                int min=999999;
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.nextInt();
                    if(arr[i][j]<min) {
                        min=arr[i][j];
                    }
                }
                if(i!=n-1) {
                    System.out.print(min+" ");
                }
                else {
                    System.out.print(min);
                }
            }
        }
    }
}
