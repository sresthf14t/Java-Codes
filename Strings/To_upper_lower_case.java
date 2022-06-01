import java.util.Scanner;
import java.lang.*;
class To_upper_lower_case {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);	
		String s=new String(input.nextLine());
		String su,sl;
		su=s.toUpperCase();
		sl=s.toLowerCase();
		System.out.println("Original="+s+"\nUpper Case="+su+"\nLower Case="+sl);
	}
}
		