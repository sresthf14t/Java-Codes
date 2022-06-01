import java.util.Scanner;
class G_rectangle{
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int i,l,b;
		System.out.println("Enter the length of the rectangle:");
		l=input.nextInt();
		System.out.println("Enter the breth of the rectangle:");
		b=input.nextInt();
		for(i=0;i<=l;i++)
		System.out.print("*  ");
		System.out.println("");
		for(i=0;i<(b-1);i++)
		{
		System.out.print("*");
		for(int j=0;j<(3*l);j++)
		System.out.print(" ");
		System.out.println("*");
		}
		for(i=0;i<=l;i++)
		System.out.print("*  ");
	}
}
		