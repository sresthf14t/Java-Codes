import java.util.Scanner;
class Transpose {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int r,c;
		r=input.nextInt();
		c=input.nextInt();
		int a[][]=new int[r][c];
		for(int i=0;i<r;i++)
		for(int j=0;j<c;j++)
		a[i][j]=input.nextInt();
		System.out.println("Thr Array you entered:\n");
		for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++)
		System.out.print(a[i][j]+" ");
		System.out.println();
		}
		int t[][]=new int[c][r];
		for(int i=0;i<c;i++) {
		for(int j=0;j<r;j++ ) {
		t[i][j]=a[j][i];
		}
		}
		System.out.println("The Transpose Matrix is:");
		for(int i=0;i<c;i++) {
		for(int j=0;j<r;j++)
		System.out.print(t[i][j]+" ");
		System.out.println();
		}
	}
}	