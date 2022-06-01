import java.util.Scanner;
class Pi {
public static void main(String args[]) {
	Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		double sump=0,sumn=0,sum=0;
		int c=0;
		for(int i=1;i<=(2*n);i++) {
		if(i%2==0) {
		if(c%2==0)
		sump=sump+(1.0/i);
		else
		sumn=sumn+(1.0/i);
		c++;
		}
		}sum=4*(sump-sumn);
		System.out.println(sum);
	}
}