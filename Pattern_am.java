import java.util.Scanner;
class Pattern_am {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int s=-1;
		for(int j=n;j>=1;j--) {
		if(j==n)
		for(int i=1;i<j;i++)
		System.out.print(i);
		else
		for(int i=1;i<=j;i++)
		System.out.print(i);
		for(int k=1;k<=s;k++)
		System.out.print(" ");
		for(int i=j;i>=1;i--)
		System.out.print(i);
		System.out.println();
		s=s+2;
		}
	}
}
	