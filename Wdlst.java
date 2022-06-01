import java.util.Scanner;
import java.io.*;
import java.lang.*;
class Wdlst {
	public static int Ran() {
		int n=(int)((Math.random()+0.32)*100);
		if(n>=97&&n<=122)
		return n;
		else
		return Ran();
	}
	public static void print_file(String s[],String filename) throws IOException {
		PrintWriter outputstream=new PrintWriter(filename);
		int n=s.length;
		int cal=n/100;
		for(int i=0;i<n;i++) { 
			if(check(s[i],s,i))
				outputstream.println(s[i]);
			if(i%cal==0)
				System.out.print("=");
		}
		outputstream.close();
	}
	public static boolean check(String si,String s[],int temp){
		for(int i=(temp+1);i<s.length;i++) {
			if(si.equals(s[i])) 
				return false;
		}
		return true;
	}		
	public static void main(String args[]) throws IOException {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the length of the Words");
		int len=input.nextInt();
		System.out.println("Enter the number of words");
		int n=input.nextInt();
		System.out.println("Enter the file name");
		String filename=input.next();
		String s[]=new String[n];
		int cal=n/100;
		System.out.println("\n\n--------------------------Generating the worlds--------------------------\n\n");
		for(int i=0;i<n;i++) {
			s[i]="";
			for(int j=1;j<=len;j++) {
				s[i]+=(char)Ran();
			}
		if(i%cal==0)
		System.out.print("=");
		}
		System.out.println("\n\n--------------------------Writing the worlds to file--------------------------\n\n");
		print_file(s,filename);
		System.out.println("\n\n\n\n-----------------------------------DONE!-----------------------------------");
	}
}
		