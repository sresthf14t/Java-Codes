import java.util.Scanner;
import java.io.*;
class Prime_Sieve_File {
	static boolean a[];
	//false for prime number and true for composite number
	public static void sieve(int n) {
		for(int i=2;i<n;i++) {
			if(!a[i]) {
				for(int j=2*i;j<n;j=j+i)
					a[j]=true;
			}
		}
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=1000000000;
		a=new boolean[1000000001];
		sieve(1000000000);
		System.out.println("Done creating array!\n\nEnter the filename: ");
		String filename=input.next();
		PrintWriter outputstream=null;
		try {
			outputstream=new PrintWriter(new FileOutputStream(filename,true));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error in opening file"+filename);
			System.exit(0);
		}
		System.out.println("Writing to text file "+filename+"\n");
		int lim=n/100;
		for(int i=0;i<a.length;i++) {
			if(i%lim==0)
				System.out.print("=");
			if(!a[i])
				outputstream.print(i+",");
		}
		outputstream.close();
		System.out.println("\nCompleted!");
	}
}
	