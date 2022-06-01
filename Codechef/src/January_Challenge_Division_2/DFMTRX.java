/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package January_Challenge_Division_2;

/**
 *
 * @author User
 */
public class DFMTRX {
  
// Driver program to test above functions  
    public static void main(String[] args) {
        for(long i=7777777777777777L;i>=1111111111111111L;i--) {
            if(i%100000000L==0) {
                System.out.println(i);
            }
            String str=""+i;
            int arr[][]=new int[4][4];
            int count=0;
            for(int j=0;i<4;i++) {
                for(int k=0;k<4;k++) {
                    arr[j][k]=str.charAt(count)-48;
                    count++;
                }
            }
            if(isDoofish(arr,4)) {
                System.out.println("\n");
                for(int j=0;j<4;j++) {
                   for(int k=0;k<4;k++) {
                       System.out.print(arr[j][k]+" ");
                   } 
                   System.out.println();
                }
                System.out.println("\n");
            }
        } 
    }
    
    public static boolean isDoofish(int arr[][],int n) {
        
        
        for(int i=0;i<n;i++) {
           boolean bool[]=new boolean[10];
           bool[0]=true;
           for(int j=0;j<n;j++) {
               bool[arr[i][j]]=true;
               bool[arr[j][i]]=true;
           }
           for(int k=0;k<2*n;k++) {
               if(!bool[k]) {
                   return false;
               }
           }
        }
        
        return true;
    }
}
