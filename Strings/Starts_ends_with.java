import java.util.Scanner;
import java.lang.*;
class Starts_ends_with {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String strt=new String(input.nextLine());
		String end=new String(input.nextLine());
		String s1=new String(input.nextLine());
		System.out.println(s1+" Starts with "+strt+"="+s1.startsWith(strt));
		System.out.println(s1+" end with "+end+"="+s1.endsWith(end));
	}
}
		