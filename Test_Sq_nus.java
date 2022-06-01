import java.util.Scanner;
class Test_Sq_nus {
	public static int sq(int n) {
		return 1+(4*((n*n)+n));
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		for(int i=1;i<=n;i++) {
			System.out.println(sq(i)+"-->"+Math.sqrt(sq(i)));
		}
	}
}