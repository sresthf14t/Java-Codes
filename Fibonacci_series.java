import java.util.Scanner;
public class Fibonacci_series {
	public static void main(String args[])
		{
		Scanner input=new Scanner(System.in);
		int n,a,b,temp;
		a=0;
		b=1;
		System.out.println();
		System.out.println();
		System.out.println("Enter the number of terms to be printed: ");
		n=input.nextInt();
		System.out.print("\n"+a+" "+b);
		for(int i=1;i<=(n-2);i++)
		{ 
		temp=a+b;
		System.out.print(" "+temp);
		a=b;
		b=temp;
		}
	}
}