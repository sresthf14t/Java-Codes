import java.util.Scanner;
class Timec {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		double sum=0,n,perc;
		n=input.nextInt();
		System.out.println();
		for(double i=1;i<=n;i++) {
		sum=sum+(Math.pow(i,(-1)));
		if(i%1000000==0)
		{
		perc=(i/n)*100;
		System.out.printf("%.2f",perc);
		System.out.print("%\b\b\b\b\b\b\b\b\b\b");
		}
		}
		System.out.println("\n\n\nSum="+sum);
	}
}