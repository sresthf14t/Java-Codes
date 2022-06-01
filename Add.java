import java.util.Scanner;
public class Add {
	public static void main(String args[])
		{
			Scanner input=new Scanner(System.in);
			System.out.println("Input the first number");
			int num1=input.nextInt();
			System.out.println("Input the secont number");
			int num2=input.nextInt();
			int sum=num1+num2;
			System.out.println();
			System.out.println("sum="+sum);
		}
}