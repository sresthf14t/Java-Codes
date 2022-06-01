import java.util.Scanner;
public class Main_power {
	public static void main(String[] args) {
		My_methord p1=new My_methord();
		Scanner input=new Scanner(System.in);
		double x,y;
		System.out.println("Enter the values of x and y");
		x=input.nextDouble();
		y=input.nextDouble();
		double p=p1.power(x,y);
		System.out.println(x+"^"+y+"="+p);
	}
}
class My_methord {
	public double power(double x,double y) {
		double p2=x;
		for(int i=2;i<=y;i++)
			p2=p2*x;
		return p2;
	}
}
