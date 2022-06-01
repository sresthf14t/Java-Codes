import java.util.Scanner;
import java.lang.*;
class Class_object_avg {
	int s1,s2,s3;
	String name;
	double avg;
	Class_object_avg() {
	Scanner input=new Scanner(System.in);
	s1=input.nextInt();
	s2=input.nextInt();
	s3=input.nextInt();
	name=new String(input.next());
	}
}
class Avg {
	public static void main(String args[]) {
		Class_object_avg a1=new Class_object_avg();
		a1.avg=(a1.s1+a1.s2+a1.s3)/3.0;
		System.out.println("Avrage marks of "+a1.name+"="+a1.avg);
	}
}
	