import java.util.Scanner;
public class Sin_x {
	public static void main(String args[])
		{
		Scanner input=new Scanner(System.in);
		int n,c=0,fact=1;
		double x, temp,sum=0;
		System.out.println("Enter the number of term to be calculated: ");
		n=input.nextInt();
		System.out.println("Enter the value of x in radians: ");
		x=input.nextDouble();
		for(int i=1;i<=(2*n);i++)
		{
		fact=fact*i;
		if(i%2==1)
		{
		temp=(Math.pow(x,i))/fact;
		c++;
		if(c%2==1)
		sum=sum+temp;
		else
		sum=sum-temp;
		}
		}
		System.out.println("\n"+"\n"+"Sin"+x+"="+sum);
	}
} 