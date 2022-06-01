import java.util.Scanner;
public class Check_prime_no {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,c=0;
		System.out.println("Enter the number to be checked: ");
		n=input.nextInt();
		for(int i=2;i<n;i++) {
		if(n%i==0) 
		{
		System.out.println("The number is not prime");
		break;
		}
		else
		c++;
		}
		if(c==(n-2))
		System.out.println("The number is prime");	
	}
}	