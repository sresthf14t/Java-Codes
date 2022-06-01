import java.util.Scanner;
public class Pattern_xam {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int s=n-1;	
		for(int i=1;i<=n;i++) {
		for(int k=s;k>=1;k--)
		System.out.print(" ");
		for(int j1=i;j1>=1;j1--)
		System.out.print(j1);
		for(int j2=i;j2>1;j2--)
		System.out.print(j2);
		s--;
		System.out.println();
		}
		s=1;
		for(int i=n-1;i>=1;i--) {
		for(int k=1;k<=s;k++)
		System.out.print(" ");
		for(int j1=i;j1>=1;j1--)
		System.out.print(j1);
		for(int j2=i;j2>1;j2--)
		System.out.print(j2);
		s++;
		System.out.println();
		}
	}
}