import java.util.Scanner;
import java.lang.*;
class GetChars_setLength {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer(input.nextLine());;
		s.setLength(40);
		System.out.println("Capacity="+s.capacity());
		char c[]=new char[s.length()];
		s.getChars(2,5,c,10);
		System.out.println();
		for(int i=0;i<c.length;i++)
		System.out.print(c[i]);
	}
}