import java.util.Scanner;
import java.math.*;
class Project_Euler_48 {
	static Scanner input=new Scanner(System.in);
	public static String sum(int n) {
		BigInteger temp,sum;
		sum=new BigInteger("0");
		for(int i=1;i<=n;i++) {
			temp=new BigInteger(""+i);
			temp=temp.pow(i);
			sum=sum.add(temp);
		}
		return (""+sum);
	}
	public static void main(String args[]) {
		int n=input.nextInt();
		String s=sum(n);
		System.out.println(s);
	}
}
	

			