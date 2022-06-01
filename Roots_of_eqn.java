import java.util.Scanner;
public class Roots_of_eqn {
	public static void main(String args[]) {
		double a,b,c,x1,x2,d;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the coeffecient of x^2: ");
		a=input.nextDouble();
		System.out.println("Enter the coeffecient of x: ");
		b=input.nextDouble();
		System.out.println("Enter the constant: ");
		c=input.nextDouble();
		System.out.println();
		System.out.println("The Quadratic Equation is:  "+a+"x^2 + "+b+"x + "+c);
		d=(b*b)-4*a*c;
		if(d<0)
		System.out.println("The roots of this equation are imaginary");
		if(d>=0) {
		d=Math.sqrt(d);
		x1=((-b+d)/2*a);
		x2=((-b-d)/2*a);
		System.out.println("The roots are="+x1+"       &       "+x2);
		}
	}
}
			
		