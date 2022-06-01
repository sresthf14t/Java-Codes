import java.util.Scanner;
import java.math.*;
class Factorial {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		BigInteger fact=new BigInteger("1");
		System.out.println("Enter the value:-");
		int n=input.nextInt();
		BigInteger temp;
		for(int i=2;i<=n;i++) {
			temp=new BigInteger(""+i);
			fact=fact.multiply(temp);
		}
		System.out.println(""+fact);
	}
}