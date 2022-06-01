import java.util.Scanner;
class Sieve_of_Eratosthenes {
	static Scanner input=new Scanner(System.in);
	static boolean a[];
	//false for prime number and true for composite number
	public static void sieve(int n) {
		for(int i=2;i<n;i++) {
			for(int j=2*i;j<n;j=j+i)
				a[j]=true;
		}
	}
	public static void main(String args[]) {
		int n=input.nextInt();
		a=new boolean[n];
		sieve(n);
		for(int i=0;i<n;i++) {
			if(!a[i])
				System.out.println(i+".) "+a[i]);
		}
	}
}	