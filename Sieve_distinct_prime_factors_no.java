import java.util.Scanner;
class Sieve_distinct_prime_factors_no {
	static int a[];
	public static void sieve(int n) {
		for(int i=2;i<n;i++) {
			if(a[i]==0) {
				for(int j=1;i*j<n;j++)
					a[j*i]++;
			}
		}
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		a=new int[n];
		sieve(n);
		for(int j=0;j<n;j++)
			System.out.println(j+".) "+a[j]);
	}
}
