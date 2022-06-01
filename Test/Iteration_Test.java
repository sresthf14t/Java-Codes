import java.util.Scanner;
class Iteration_Test {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		long lim=input.nextLong();
		long check=lim/1000;
		System.out.println();
		for(long i=1;i<=lim;i++) {
			if(i%check==0) {
				System.out.print("=");
			}
		}
	}
}