import java.util.Scanner;
class String_length {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		int x;
		for(int i=0;i<s.length();i++)
		{
		x=(int)s.charAt(i);
		System.out.println("position "+i+"="+x);
		}
		System.out.println("\n\nString="+s);
	}
}