import java.util.Scanner;
class Swap_first_and_last_digit {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int first=0,last,c=0,rem,div;
		last=n%10;
		int temp=n;
		while(temp!=0)
		{
		first=temp%10;
		temp=temp/10;
		c++;
		}
		div=(int)Math.pow(10,(c-1));
		rem=n%div;
		rem=rem/10;
		System.out.println("rem="+rem);
		System.out.println("The resualtant number is:"+last+rem+first);
		if(first==last)
		System.out.println("The new number is same");
		else
		System.out.println("The new number is different");
	}
}
		
		