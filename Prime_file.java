import java.util.Scanner;
import java.io.*;
class Prime_file {
	static Scanner input=new Scanner(System.in);
	static boolean a[];
	//false for prime number and true for composite number
	public static void sieve(int n) {
		System.out.println("Creating Sieve Array\n");
		for(int i=2;i<n;i++) {
			if(i%1000000==0)
				System.out.print("=");
			if(!a[i]) {
				for(int j=2*i;j<n;j=j+i)
					a[j]=true;
			}
		}
	}
	public static boolean IsPrime(long n) {
		int lim=(int)Math.sqrt(n);
		for(int i=3;i<=lim;i=i+2) {
			if(!a[i]) {
				if(n%i==0)
					return false;
			}
		}
		return true;
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		a=new boolean[1000000000];
		sieve(1000000000);
		System.out.println("\n\nEnter the filename\n\n");
		String filename=input.next();
		PrintWriter outputstream=null;
		try {
			outputstream=new PrintWriter(new FileOutputStream(filename,true));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error in opening file"+filename);
			System.exit(0);
		}
		String line;
		System.out.println("\n\nWriting to file PART-1\n\n");
		for(int i=1;i<a.length;i++) {
			if(i%10000000==0)
				System.out.print("=");
			if(!a[i])
				outputstream.println(i);
		}
		System.out.println();
		System.out.println("\n\nWriting to file PART-2\n\n");
		for(long i=a.length+1;i<=1000000000000000000L;i=i+2L) {
			if((i+1L)%10000000L==0)
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+(i+1L));
			if(IsPrime(i))
				outputstream.println(i);
		}	
		outputstream.close();
	}
}	