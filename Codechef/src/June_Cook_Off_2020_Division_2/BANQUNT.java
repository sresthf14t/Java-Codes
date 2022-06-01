/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Cook_Off_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class BANQUNT {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long n=input.nextInt();
            long m=input.nextInt();
            long lim=n/m;
            long max_ele=n-lim;
            long ways=power(2,lim,998244353);
            System.out.println(max_ele+" "+ways);
        }
        
    }
    
    static long power(long x, long y, long p) 
    { 
        // Initialize result 
        long res = 1;      
         
        // Update x if it is more   
        // than or equal to p 
        x = x % p;  
  
       if (x == 0) return 0; // In case x is divisible by p;    
  
        while (y > 0) 
        { 
            // If y is odd, multiply x 
            // with result 
            if((y & 1)==1) 
                res = (res * x) % p; 
      
            // y must be even now 
            // y = y / 2 
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res; 
    } 
}
