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
import java.util.*;
import java.lang.Math;
public class Hill_Cipher {
    static int GCD(int a,int b) {
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
	static int[][] inverse(int[][] a){
		int deta=a[0][0]*((a[1][1]*a[2][2])-(a[2][1]*a[1][2]));
		deta=deta-a[0][1]*((a[1][0]*a[2][2])-(a[1][2]*a[2][0]));
		deta=deta+a[0][2]*((a[1][0]*a[2][1])-(a[1][1]*a[2][0]));
		if(deta<0) {
			while(deta<0) {
				deta=deta+26;
			}
		}
		deta=deta%26;
		deta=GCD(26,deta);
		int i[][]=new int[a.length][a[1].length];
		i[0][0]=((a[1][1]*a[2][2])-(a[2][1]*a[1][2]))*((int)Math.pow((-1),(0)));
		i[0][1]=((a[0][1]*a[2][2])-(a[0][2]*a[2][1]))*((int)Math.pow((-1),(1)));
		i[0][2]=((a[0][1]*a[1][2])-(a[0][2]*a[1][1]))*((int)Math.pow((-1),(2)));
		i[1][0]=((a[1][0]*a[2][2])-(a[2][0]*a[1][2]))*((int)Math.pow((-1),(1)));
		i[1][1]=((a[0][0]*a[2][2])-(a[2][0]*a[0][2]))*((int)Math.pow((-1),(2)));
		i[1][2]=((a[0][0]*a[1][2])-(a[0][2]*a[1][0]))*((int)Math.pow((-1),(3)));
		i[2][0]=((a[1][0]*a[2][1])-(a[2][0]*a[1][1]))*((int)Math.pow((-1),(2)));
		i[2][1]=((a[0][0]*a[2][1])-(a[0][1]*a[2][0]))*((int)Math.pow((-1),(3)));
		i[2][2]=((a[1][1]*a[0][0])-(a[0][1]*a[1][0]))*((int)Math.pow((-1),(4)));
		for(int g=0;g<3;g++) {
			for(int h=0;h<3;h++) {
				i[g][h]=i[g][h]*deta;
				while(i[g][h]<0) {
					i[g][h]=i[g][h]+26;
				}
				i[g][h]=i[g][h]%26;
			}
		}
		return i;
	}
	static void Encrypt(int a[][],String s) {
		int l;
		if(s.length()%3!=0) {
			l=1+s.length()/3;
		}
		else{
			l=s.length()/3;
		}
		int p[][]=new int[l][3];            //Plaintext Matrix
		int i1=0;
		for(int i=0;i<l;i++) {
			for(int j=0;j<3;j++) {
				if(i1<s.length()) {
					p[i][j]=(int)s.charAt(i1)-65;
					i1=i1+1;
				}
				else {
					p[i][j]=(int)('X')-65;
				}
			}
		}
		int q[][]=new int[l][3];            //Encrypted Matrix
		String s2="";                       //Encrypted String
		for(int i=0;i<l;i++) {
			for(int j=0;j<3;j++) {
				q[i][j]=p[i][0]*a[0][j]+p[i][1]*a[1][j]+p[i][2]*a[2][j];
				q[i][j]=q[i][j]%26;
				s2=s2+(char)(q[i][j]+65);
			}
		}
		System.out.println(s2);
	}
	static void Decrypt(int a[][],String s) {
		int l;
		a=inverse(a);
		if(s.length()%3!=0) {
			l=1+s.length()/3;
		}
		else{
			l=s.length()/3;
		}
		int p[][]=new int[l][3];            //Ciphertext Matrix
		int i1=0;
		for(int i=0;i<l;i++) {
			for(int j=0;j<3;j++) {
				if(i1<s.length()) {
					p[i][j]=(int)s.charAt(i1)-65;
					i1=i1+1;
				}
				else {
					p[i][j]=(int)('X')-65;
				}
			}
		}
		int q[][]=new int[l][3];            //Plaintext Matrix
		String s2="";                       //Plaintext String
		for(int i=0;i<l;i++) {
			for(int j=0;j<3;j++) {
				q[i][j]=p[i][0]*a[0][j]+p[i][1]*a[1][j]+p[i][2]*a[2][j];
				q[i][j]=q[i][j]%26;
				s2=s2+(char)(q[i][j]+65);
			}
		}
		System.out.println(s2);
	}
	public static void main(String mms[]) {
		int a[][]=new int[3][3];
		System.out.println("Enter the Key matrix of 3X3 for encription(Range 0:25) : ");
		Scanner s=new Scanner(System.in);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				a[i][j]=s.nextInt();
				a[i][j]=a[i][j]%26;
				while(a[i][j]<0) {
					a[i][j]=a[i][j]+26;
				}
			}
		}
		System.out.println("Enter the string you want to encrypt or decrypt : ");
		String l1=s.next();
		l1=l1.toUpperCase();
		System.out.print("\n1.Encrypt\n2.Decrypt\nEnter your choice:");
		int l2=s.nextInt();
		switch(l2) {
		case 1:System.out.print("\nThe encrypted message is:");Encrypt(a,l1);break;
		case 2:System.out.print("\nThe decrypted message is:");Decrypt(a,l1);break;
		}
	}
}
