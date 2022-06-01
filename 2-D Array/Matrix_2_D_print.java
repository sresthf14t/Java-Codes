import java.util.Scanner;
class Matrix_2_D_print {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int r,c;
		System.out.println("Enter the number of rows and coloums");
		r=input.nextInt();
		c=input.nextInt();
		int a[][]=new int[r][c];
		System.out.println("Enter the array:");
		for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++)
		a[i][j]=input.nextInt();
		}
		System.out.println("The Array You entered:\n");
		for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++) 
		System.out.print(a[i][j]+" ");
		System.out.println();
		}
	}
}