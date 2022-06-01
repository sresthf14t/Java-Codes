/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubstitutionCipher;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class MulInveExtendedEuclidean {
    public static int Inverse(int a,int n) {
        int q,r1=n,r2=a,r,t1=0,t2=1,t;
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
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a and n");
		int a=input.nextInt();
		int n=input.nextInt();
		int inv=Inverse(a,n);
		if(inv==-1)
			System.out.println("Inverse does not exists");	
		else
			System.out.println(a+" inverse in mod "+n+"="+inv);
	}
}
