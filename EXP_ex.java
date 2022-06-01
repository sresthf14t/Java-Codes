import java.util.Scanner;
public class EXP_ex {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,x,fact=1;
		System.out.println("Enter the number of terms to be calculated");
		n=input.nextInt();
		System.out.println("Enter the value of x");
		x=input.nextInt();
		double sum=1,temp=0;
		for(int i=1;i<=n;i++) {
		fact=fact*i;
		temp=Math.pow(x,i);
		sum=sum+(temp/fact);
		}
		System.out.println("e^"+x+"= "+sum);
	}
}