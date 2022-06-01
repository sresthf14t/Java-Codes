import java.lang.*;
import java.util.Scanner;
class getChars_test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int beg,end,ch_beg;
		String s=new String(input.nextLine());
		beg=input.nextInt();
		end=input.nextInt();
		ch_beg=input.nextInt();
		char c[]=new char[s.length()];
		s.getChars(beg,end,c,ch_beg);
		System.out.println("The char Array");
		for(int i=0;i<c.length;i++)
		System.out.print(c[i]);
	}
}