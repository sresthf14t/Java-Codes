import java.util.Scanner;
class n_div_n_fact {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		double fact=1.00;
		double sum=0,temp;
		for(int i=1;i<=n;i++) {
		fact=fact*i;
		temp=(double)(fact/i);
		sum=sum+temp;
		}
		System.out.println(sum);
	}
}