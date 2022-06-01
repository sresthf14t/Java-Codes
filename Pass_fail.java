import java.util.Scanner;
public class Pass_fail {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int m1,m2,m3,m4,grade;
		System.out.println("Enter the marks of the first sublect: ");
		m1=input.nextInt();
		System.out.println("Enter the marks of the second subject: ");
		m2=input.nextInt();
		System.out.println("Enter the marks of the third subject: ");
		m3=input.nextInt();
		System.out.println("Enter the marks of the fourth subject: ");
		m4=input.nextInt();
		grade=(m1+m2+m3+m4)/4;
		if(grade>=50)
		System.out.println("Pass");
		else
		System.out.println("Fail");
	}
}