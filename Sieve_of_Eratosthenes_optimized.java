import java.util.Scanner;
import java.util.concurrent.TimeUnit;
class Sieve_of_Eratosthenes_optimized {
	static Scanner input=new Scanner(System.in);
	static boolean a[];
	//false for prime number and true for composite number
	// 2 and multiples of 2 are ignored
	// 3 and multiples of 3 are ignored
	public static void sieve(int n) {
		int lim=n/100;
		boolean b=false;
		System.out.println();
		for(int i=5;i<n;) {
			if((i+1)%lim==0)
				System.out.print("=");
			if(!a[i]) {
				for(int j=2*i;j<n;j=j+i)
					a[j]=true;
			}
			if(!b) {
				i=i+2;
				b=true;
			}
			else {
				i=i+4;
				b=false;
			}
		}
	}
	public static void main(String args[])    throws InterruptedException {
		System.out.println("Enter the limit");
		int n=input.nextInt();
		long startTime = System.currentTimeMillis();
		a=new boolean[n];
		sieve(n);
		System.out.println("\n------------------------------Done!------------------------------");
		long endTime = System.currentTimeMillis();
		float timeElapsed = (endTime - startTime)/1000;
		System.out.println("Execution time in seconds: " + timeElapsed);
	}
}	