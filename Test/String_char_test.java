import java.util.Scanner;
class String_char_test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		char c1,c2;
		c1=input.next().charAt(0);		
		c2=input.next().charAt(0);
		String s=(char)(c1+c2);
		System.out.println(s);
	}
}