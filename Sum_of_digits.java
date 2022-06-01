import java.util.Scanner;
public class Sum_of_digits {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n,m,temp,sum=0;
		System.out.println("Enter the number: ");
		n=input.nextInt();
		m=n;
		temp=n;
		for(int i=1;i<=100;i++) {
		m=m%10;
		sum=sum+m;
		temp=temp/10;
		m=temp;
		if(temp==0)
		break;
		}
		System.out.println();
		System.out.println("Sum="+sum);
	}
}