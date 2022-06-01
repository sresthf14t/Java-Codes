import java.util.Scanner;
import java.lang.*;
class Reverse_length {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer(input.nextLine());
		s.reverse();
		System.out.println("Reverse="+s);
		System.out.println("Length="+s.length());
	}
}