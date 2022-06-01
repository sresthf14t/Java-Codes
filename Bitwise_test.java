import java.util.Scanner;
class Bitwise_test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter two number");
		int a=input.nextInt();
		int b=input.nextInt();
		System.out.println(a+"&"+b+"="+(a&b));
	}
}