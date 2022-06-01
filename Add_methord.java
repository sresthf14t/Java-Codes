import java.util.Scanner;
class Add_methord {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int a,b,c;
		System.out.println("Enter two numbers:");
		a=input.nextInt();
		b=input.nextInt();
		c=add(a,b);
		System.out.println(a+"+"+b+"="+c);
	}
	public static int add(int m,int n) {
		int k=m+n;
		return(k);
	}
}
		
		