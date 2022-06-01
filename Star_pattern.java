import java.util.Scanner;
class Star_pattern {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,x=105;
		System.out.println("Enter the number of rows:");
		n=input.nextInt();
		for(int i=1;i<=n;i++) {
		for(int k=x;k>=1;k--) {
		System.out.print(" ");
		}
		for(int j=1;j<=i;j++) {
		System.out.print("* ");
		}
		System.out.println("");
		x--;
		}
	}
}
