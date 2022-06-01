import java.util.Scanner;
public class Plaindrome {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,temp,m,rev;
		System.out.println("Enter the number to be checked");
		n=input.nextInt();
		rev=0;
		m=n;
		temp=n;
		for(int i=1;i<=100;i++)
		{
		m=m%10;
		rev=rev*10+m;
		temp=temp/10;
		m=temp;
		if(temp==0)
		break;
		}
		System.out.println("Revrse number="+rev);
		if(rev==n)
		System.out.println("The number is palindrome");
		else
		System.out.println("The number is not palindrome");
	}
}
		

		