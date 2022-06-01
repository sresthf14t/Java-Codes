/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adesh;

/**
 *
 * @author User
 */
import Mini_project.*;
import java.math.BigInteger;
import java.util.*;
public class RSA {
    static int p=17;
	static int q=19;
	static int n=p*q;
	static int phi=(p-1)*(q-1);
	static int e=getencrypter(phi);
	static int d=inv(phi,e);
	static int inv(int a,int b) {
		int t1=0,t2=1;
		int f=a;
		int d=b;
		while(b!=0) {
			int at=a%b;
			int q=a/b;
			int t3=t1-(q*t2);
			t1=t2;
			t2=t3;
			a=b;
			b=at;
		}
		while(t1<0) {
			t1=t1+f;
		}
		return t1;
	}
	static int GCD(int a,int b) {
		if(b==0) {
			return a;
		}
		else {
			return GCD(b,a%b);
		}
	}
	static int getencrypter(int phi) {
		int e=2;
		while(e<phi) {
			if(GCD(e,phi)==1)
				break;
			else
				e++;
		}
		return e;
	}
	static int encrypt(int msg) {
		BigInteger C=BigInteger.valueOf(msg); 
		C=C.pow(e);
		BigInteger N=BigInteger.valueOf(n); 
		C=C.mod(N);
		int f=C.intValue();
		return f;
	}
	static int decrypt(int c) {
		BigInteger C=BigInteger.valueOf(c); 
		C=C.pow(d);
		BigInteger N=BigInteger.valueOf(n); 
		C=C.mod(N);
		int f=C.intValue();
		return f;
	}
	public static void main(String args[]) {
		System.out.println(encrypt(255));
		System.out.println(decrypt(encrypt(255)));
	}
}
