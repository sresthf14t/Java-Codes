import java.util.Scanner;
class Pattern_star {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);	
		int n=input.nextInt();
		int s=n-1;
		for(int i=1;i<=n;i++) {	
		for(int k=s;k>=1;k--)
		System.out.print(" ");
		for(int j=1;j<=2*i-1;j++)
		System.out.print("*");
		s--;
		System.out.println();
		}
		s=1;
		for(int i=n-1;i>=1;i--) {
		for(int k=1;k<=s;k++)
		System.out.print(" ");
		for(int j=2*i-1;j>=1;j--)
		System.out.print("*");
		s++;
		System.out.println();
		}
	}
}