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
public class C1_Good_Numbers_easy_version {
    static ArrayList<Long> pow_lis,sum;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        pow_lis=new ArrayList<>();
        sum=new ArrayList<>();
        long pow=1;
        for(int i=0;pow<=2e18;i++) {
            pow_lis.add(pow);
            pow*=3L;
        }
        long total=0;
        for(int i=0;i<pow_lis.size();i++) {
            total+=pow_lis.get(i);
            sum.add(total);
        }
        int q=input.nextInt();
        for(int i=1;i<=q;i++) {
            System.out.println(solve(input.nextLong()));
        }
    }
    public static long solve(long n) {
        long tmp_n=n;
        for(int i=pow_lis.size()-1;i>=0;i--) {
            if(pow_lis.get(i)<=tmp_n) {
                tmp_n-=pow_lis.get(i);
            }
        }
        if(tmp_n==0) {
            return n;
        }
        long ans=0;
        for(int i=pow_lis.size()-1;i>=0;i--) {
            if(pow_lis.get(i)<=n) {
               ans+=pow_lis.get(i);
               n-=pow_lis.get(i);
            }
            else if(pow_lis.get(i-1)<n && sum.get(i-1)<n) {
                ans+=pow_lis.get(i);
                n-=pow_lis.get(i);
                break;
            }
        }
        return ans;
    }   
}
