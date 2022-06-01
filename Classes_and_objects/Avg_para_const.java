import java.util.Scanner;
class input {
	int s1,s2,s3;
	input(int a1,int a2,int a3) {
	System.out.println("Hello from inside the constructor");
	s1=a1;
	s2=a2;
	s3=a3;
	}
}
class Avg_para_const {
	public static void main(String args[]) {
		Scanner input1=new Scanner(System.in);
		int a1=input1.nextInt();
		int a2=input1.nextInt();
		int a3=input1.nextInt();
		input in=new input(a1,a2,a3);
		System.out.println(in.s1+"\n"+in.s2+"\n"+in.s3);
	}
}

		