import java.util.Scanner;
import java.io.*;
import java.lang.*;
class Pnc_Wordlist {
	static Scanner input=new Scanner(System.in);
	static int n=input.nextInt();
	static int a[]=new int[n];
	static int t=(int)Math.pow(26,n);
	static int c=t/100;
	static String filename=input.next();
	public static void main(String args[]) throws IOException {
		for(int i=0;i<n;i++)
			a[i]=97;
		int temp;
		String s[]=new String[t];
		System.out.println("\n\n----------------------------------Creating wordlist-----------------------------------\n\n");
		for(int i=1;i<=t;i++) {
			s[i-1]="";
			for(int j=0;j<n;j++) {
				s[i-1]=(char)a[j]+s[i-1];
				temp=(int)(Math.pow(26,j));
				if(i%temp==0)
					a[j]++;
				if(a[j]==123)
					a[j]=97;
			}
			if(i%c==0)
				System.out.print("=");
		}
		print_file(s);
	}
	public static void print_file(String s[]) throws IOException {
		System.out.println("\n\n----------------------------------Writing contents to the file----------------------------------\n\n");
		PrintWriter outputstream=new PrintWriter(filename);
		for(int i=0;i<s.length;i++) {
			outputstream.println(s[i]);
			if(i%c==0)
				System.out.print("=");
		}
		outputstream.close();
		System.out.println("\n\n----------------------------------DONE!----------------------------------\n\n");
	}	
		
}	