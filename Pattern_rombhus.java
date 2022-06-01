import java.util.Scanner;
class Pattern_rombhus {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,m;
		System.out.println("Enter the value of n:");
		n=input.nextInt();;
		m=n;
		for(int f=10;f>=1;f--)
		{
		for(int i=1;i<=n;i++)
		{
		System.out.println();
		for(int q=m;q>=1;q--)
		System.out.print(" ");
		for(int j=1;j<=i;j++)
		System.out.print("*");
		for(int k=i;k>1;k--)
		System.out.print("*");
		m--;
		}
		m=1;
		for(int i=n-1;i>=1;i--)
		{
		System.out.println();
		for(int q=0;q<=m;q++)
		System.out.print(" ");
		for(int j=1;j<=i;j++)
		System.out.print("*");
		for(int k=i;k>1;k--)
		System.out.print("*");
		m++;
		}
		}
	}	
}