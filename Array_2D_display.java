import java.util.Scanner;
class Array_2D_display {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int r,c;
		r=input.nextInt();
		c=input.nextInt();
		int a[][]=new int[r][c];
		for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++) {
		a[i][j]=input.nextInt();
		}
		}
		System.out.println("The array you entered\n\n");
		for(int i=0;i<r;i++) {
		System.out.println();
		for(int j=0;j<c;j++) {
		System.out.print(a[i][j]+" ");
		}
		}
	}
}