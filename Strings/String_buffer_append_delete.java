import java.util.Scanner;
import java.lang.*;
class String_buffer_append_delete {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer(input.nextLine());
		s=s.insert(5,'s');
		System.out.println("After inserting->"+s);
		s=s.delete(2,6);
		System.out.println("After deleting->"+s);
	}
}