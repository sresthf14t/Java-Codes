import java.util.Scanner;
class Print_n_num_recursion {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		sum(n);
	}
	public static int sum(int n) {
		if(n!=0) {
		System.out.print(n+" ");
		return (sum(n-1));
		}
		else
		return 0;
	}
}

		