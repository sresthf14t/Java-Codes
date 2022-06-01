import java.util.Scanner;
import java.io.*;
class Random_user_name_password {
	public static int Random(boolean bo) {
		double a=Math.random()+0.26;
		int b=(int)(100*a);
		if(bo)
		return (int)(b/10);
		else
		return b; 
 	}
	public static char[] word(int l) {
		char a[]=new char[l];
		int temp;
		for(int i=0;i<l;i++) {
		temp=Random(false);
		while(temp<33||temp>126){
		temp=Random(false);
		}
		a[i]=(char)temp;
		}
		return a;
		}
	public static void main(String args[]) {
		System.out.println("\nThis program will Generate n random usename and passwords\n\nEnter the value of n");
		Scanner input=new Scanner(System.in);
		int t=input.nextInt();
		for(int q=1;q<=t;q++) {
		System.out.println("\n------------------------Iteration "+q+"------------------------ \n");
		int lu,lp;
		lu=Random(true);
		lp=Random(true);
		char u[]=word(lu);
		char p[]=word(lp);
		System.out.print("\nUser Name=  ");
		for(int i=0;i<lu;i++)
		System.out.print(u[i]);	
		System.out.print("\nPassword=  s");
		for(int i=0;i<lp;i++)
		System.out.print(p[i]);
		System.out.println("\n");	
		}
	}
}