import java.util.Scanner; 
class Series_1 {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the value of n:");
		int n=input.nextInt();
		int sum=0;
		for(int i=1;i<=n;i++)
		{
		System.out.print("(");
		for(int j=1;j<=i;j++)
		{
		if(j==i)
		System.out.print(j);
		else
		System.out.print(j+"+");
		sum=sum+j;
		}
		if(i==n)
		System.out.print(")");
		else
		System.out.print(")+");
		}
		System.out.print("="+sum);
	}
}