import java.util.Scanner;
class Fibonacci_array {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		fib(n);
	}
	public static void fib(int n) {
		int a[]=new int[n];
		a[0]=0;
		a[1]=1;
		System.out.print("\n\n"+a[0]+" "+a[1]+" ");
		for(int i=2;i<n;i++) {
		a[i]=(a[i-1]+a[i-2]);
		System.out.print(a[i]+" ");
		}
	}
}