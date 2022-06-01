import java.lang.*;
import java.util.Scanner;
class String_input_passing_variable {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String s1=new String(input.nextLine());
		String s2=new String(s1);
		String s3=s1;
		String s4=s2;
		System.out.println("\n\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4);
	}
}