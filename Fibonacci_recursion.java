import java.util.Scanner;
class Fibonacci_recursion {
	public static void main(String station_howrah[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int a=0,b=1;
		System.out.print(a+" "+b+" ");
		fib(a,b,(n-2));
	}
	public static void fib(int a,int b,int n) {
		if(n!=0) {
		System.out.print((a+b)+" ");
		fib(b,(a+b),(n-1));
		}
	}
} 