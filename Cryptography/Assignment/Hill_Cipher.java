import java.util.Scanner;
class Hill_Cipher {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int r1,c1,r2,c2;
		System.out.println("Enter the number of rows and coloums of the first matrix:");
		r1=input.nextInt();
		c1=input.nextInt();
		System.out.println("Enter the number of rows and coloums of the second matrix:");
		r2=input.nextInt();
		c2=input.nextInt();
		int a[][]=new int[r1][c1];
		int b[][]=new int[r2][c2];
		int bt[][]=new int[c2][r2];
		bt=transpose(b,r2,c2);
		int m[][]=new int[r1][c2];
		for(int i=0;i<r1;i++)
		for(int j=0;j<c2;j++) 
		m[i][j]=multiply(a,bt,i,j);
		System.out.println("The multiplyed matrix is:");
		for(int i=0;i<r1;i++) {
		for(int j=0;j<c2;j++)
		System.out.print(m[i][j]+" ");
		System.out.println();
		}
	}
	public static int multiply(int a[][],int b[][],int c,int d) {
		int sum=0;
		for(int j=0;j<a[c].length;j++)
		sum+=a[c][j]*b[d][j];
		System.out.println("Sum="+sum);
		return sum;
	}	
	public static int[][] transpose(int a[][],int r,int c) {
		int t[][]=new int[c][r];
		for(int i=0;i<c;i++) {
		for(int j=0;j<r;j++ ) {
		t[i][j]=a[j][i];
		}
		}	
		return t;
	}
}
		
				