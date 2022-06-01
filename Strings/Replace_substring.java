import java.util.Scanner;
import java.lang.*;
class Replace_substring {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer(input.nextLine());
		System.out.println("\nOriginal String="+s);
		s.replace(2,4,"ab");
		System.out.println("Strsing after replacing="+s);
		String s1=s.substring(3);
		System.out.println("Substring="+s1);
	}
}