/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package February_Challenge_2020_Division2;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class EXPCH {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            String str=input.next();
            int co=0,cc=0,count=0;
            for(int i=0;i<n;i++) {
                co=0;
                cc=0;
                for(int j=i;j<n;j++) {
                    if(str.charAt(j)=='(') {
                        co++;
                    }
                    else if(str.charAt(j)==')') {
                        cc++;
                    }
                    if(cc>co) {
                        System.out.println(i+" "+j);
                        count+=(n-j);
                        cc--;
                        co++;
                    }
                }
            }
            System.out.println(count);
            long N=(long)n;
            long q=(N*(N+1))/2;
            long q_inv=Inverse(q,1000000007L);
            long out=(count*q_inv)%1000000007L;
            System.out.println(out);
        }
    }
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
                q=r1/r2;
                r=r1%r2;
                t=t1-(q*t2);
                r1=r2;
                r2=r;
                t1=t2;
                t2=t;
                if(r2==0) {
                        break;
                }
        }
                if(r1!=1) {
                        return -1;
                }
                t1%=n;
                if(t1<0) {
                        t1+=n;
                }
                return t1;
	}
}
