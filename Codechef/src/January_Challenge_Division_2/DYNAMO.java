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
import java.util.Scanner;
public class DYNAMO {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            int n=input.nextInt();
            long max=(long)(Math.pow(10,n));
            //a
            long a=input.nextLong();
            //s    
            long s=a+(2*max);
            long s_tmp=s-a;
            System.out.println("s_tmp="+s_tmp);
            System.out.println(s);
            //b
            long b=input.nextLong();
            s_tmp-=b;
            System.out.println("s_tmp="+s_tmp);
            //c
            long c=max-b;
            s_tmp-=c;
            System.out.println(c);
            System.out.println("s_tmp="+s_tmp);
            //d
            long d=input.nextLong();
            s_tmp-=d;
            System.out.println("s_tmp="+s_tmp);
            //e
            long e=max-d;
            s_tmp-=e;
            System.out.println(e);
            System.out.println("s_tmp="+s_tmp);
            int in=input.nextInt();
            if(in==-1) {
                System.exit(0);
            }
        }
    }
}
