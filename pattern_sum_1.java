import java.util.Scanner;
class pattern_sum_1 {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		System.out.println();
		print(n);
		System.out.println("\nSum="+sum(n));
	}
	public static void print(int n) {
		for(int i=1;i<=n;i++) {
		System.out.print("(");
		for(int j=1;j<=i;j++) {
		if(j==i)
		System.out.print(j);
		else
		System.out.print(j+"+");
		}
		if(i==n)
		System.out.print(")");
		else
		System.out.print(")+");
		}
	}
	public static int sum(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
		for(int j=1;j<=i;j++) {
		sum=sum+j;
		}
		}
		return sum;
	}
}