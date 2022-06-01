import java.util.Scanner;
public class Factorial {
	public static void main(String args[]) {
		int fact=1,n;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the number whose factorial is to be calculated: ");
		n=input.nextInt();
		for(int i=1;i<=n;i++)
		fact=fact*i;
		System.out.println();
		System.out.println(n+"!="+fact);
	}
}