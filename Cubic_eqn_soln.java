import java.util.Scanner;
import java.lang.*;
class Cubic_eqn_soln {
	static Scanner input=new Scanner(System.in);
	static double a,b,c,d;
	static double[] soln=new double[3];
	static int count=0;
	public static void check_int_soln() {
		double temp_p,temp_p_1,temp_n,temp_n_1;
		for(double i=1,j=-1;;i=i+0.01,j=j-0.01) {
			temp_p=a*Math.pow(i,3)+b*Math.pow(i,2)+c*i+d;
			temp_n=a*Math.pow(j,3)+b*Math.pow(j,2)+c*j+d;
			if(temp_p>=-0.01&&temp_p<=0.01) {
				soln[count]=i;
				count++;
			}
			if(temp_n>=-0.01&&temp_n<=0.01) {
				soln[count]=i;
				count++;
			}
			if(count==3)
				break;
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the value of a,b,c,d ");
		a=input.nextDouble();
		b=input.nextDouble();
		c=input.nextDouble();
		d=input.nextDouble();
		check_int_soln();
		System.out.println("\n\n-----------------------------------SLOUTION-----------------------------------");
		for(int k=0;k<3;k++)
			System.out.println((k+1)+".)  "+soln[k]);
		System.out.println("\n\n------------------------------------------------------------------------------");
	}
}