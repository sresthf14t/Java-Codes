import java.util.Scanner;
class Sum_of_n_num_recursion {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		System.out.println("Sum="+sum(n));
	}
	public static int sum(int n) {
		if(n==1)
		return 1;
		else
		return (n+sum(n-1));
	}
}