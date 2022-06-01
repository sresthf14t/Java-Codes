import java.util.Scanner;
class Pattern_121 {
	public static void main(String args[]) {		
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n=input.nextInt();
		int p=n;
		for(int i=1;i<=n;i++)
		{
		System.out.println();
		for(int m=p;m>=1;m--)
		System.out.print("  ");
		for(int j=1;j<=i;j++)
		System.out.print(j+" ");
		for(int k=(i-1);k>=1;k--)
		System.out.print(k+" ");
		p--;
		}
	}
}