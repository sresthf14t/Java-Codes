import java.util.Scanner;
class Set_precision_test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n;
		float f;
		while(true) {
			System.out.println("\nEnter the number");
			n=input.nextInt();
			f=(float)Math.sqrt(n);
			System.out.printf("%.100f",f);
		}
	}
}