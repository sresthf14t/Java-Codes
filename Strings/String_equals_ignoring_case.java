import java.lang.*;
import java.util.Scanner;
class String_equals_ignoring_case {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String s1=new String(input.nextLine());
		String s2=new String(input.nextLine());
		System.out.println(s1.equalsIgnoreCase(s2));
	}
}