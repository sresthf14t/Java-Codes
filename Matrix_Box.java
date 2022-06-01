import java.util.Scanner;
class Matrix_Box {
	public static void print(int a[][]) {
		for(int i=0;i<a.length;i++) {
			System.out.print(" ");
			for(int j=0;j<a[i].length;j++)
				System.out.print("========");
			System.out.println();
			System.out.print("   |   ");
			for(int j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+"   |   ");
			System.out.println();
			System.out.print(" ");
			for(int j=0;j<a[i].length;j++)
				System.out.print("========");
			System.out.println();
		}
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int a[][]=new int[8][8];
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++)
				a[i][j]=0;
		}
		print(a);
	}
}