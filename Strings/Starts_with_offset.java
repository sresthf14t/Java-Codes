import java.util.Scanner;
import java.lang.*;
class Starts_with_offset {
	public static void main(String args[]) { 
		Scanner input=new Scanner(System.in);
		String s=new String(input.nextLine());
		String strt=new String(input.nextLine());
		int off=input.nextInt();
		System.out.println(s.startsWith(strt,off));
	}
}
