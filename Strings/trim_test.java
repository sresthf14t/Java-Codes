import java.util.Scanner;
import java.lang.*;
class trim_test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String s=new String(input.nextLine());
		System.out.println("original="+s);
		s=s.trim();
		System.out.println("modified="+s);
	}
}