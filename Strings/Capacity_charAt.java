import java.util.Scanner;
import java.lang.*;
class Capacity_charAt {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer(input.nextLine());
		System.out.println("Capacity="+s.capacity()+"\ncharAt(2)="+s.charAt(2));
	}
}