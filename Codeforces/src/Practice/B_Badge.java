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
import java.util.Scanner;
public class B_Badge {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt()-1;
        }
        boolean vis[];
        for(int i=0;i<n;i++) {
            vis=new boolean[n];
            int indx=i;
            while(!vis[indx]) {
                vis[indx]=true;
                indx=arr[indx];
            }
            System.out.print((indx+1)+" ");
        }
        System.out.println();
    }
}
