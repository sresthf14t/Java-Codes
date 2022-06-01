import java.util.Scanner;
class Sum_n_no_recursion {
	public static void main(String station_howrah[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		System.out.println("Sum="+sum(n));
	}
	public static int sum(int x) {
		if(x==1)
		return 1;
		else
		return(x+sum(x-1));
	}
}