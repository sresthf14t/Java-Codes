import java.util.Scanner;
class GCD_of_n_nos {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int a[]=new int[n];
		a[0]=input.nextInt();		
		int max=a[0];
		for(int i=1;i<n;i++) {
		a[i]=input.nextInt();
		if(a[i]>max)
		max=a[i];
		}
		boolean b;
		int gcd=1;
		for(int i=1;i<=max;i++) {
		b=false;
		for(int j=0;j<n;j++) {
		if(a[j]==0)
		b=true;
		else {
		if(a[j]%i==0)
		b=true;
		else 
		b=false;
		if(!b)
		break;
		}
		}
		if(b)
		gcd=i;
		}
		System.out.println("The array you entered");
		for(int i=0;i<n;i++)
		System.out.print(a[i]+" ");
		System.out.println("\nGCD="+gcd);
	}
}