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
import java.io.*;
import java.util.*;
public class C_Dividing_the_numbers {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long n=input.nextInt();
        solve(n);
    }
    public static void solve(long n) {
        long total=(n*(n+1))/2,sum=0;
        ArrayList<Long> arrli=new ArrayList<>();
        for(long i=n;i>=1;i--) {
            total-=i;
            sum+=i;
            if(total<sum) {
                total+=i;
                sum-=i;
                continue;
            }
            arrli.add(i);
        }
        System.out.println(total-sum);
        System.out.print(arrli.size()+" ");
        for(int i=0;i<arrli.size();i++) {
            System.out.print(arrli.get(i)+" ");
        }
    }
}
