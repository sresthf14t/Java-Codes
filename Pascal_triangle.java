import java.util.Scanner;
class Pascal_triangle {
	static int a[][];
	public static void Pascal() {
		for(int i=0;i<a.length;i++) {
			for(int j=1;j<i;j++) {
				a[i][j]=a[i-1][j-1]+a[i-1][j];
			}
		}
	}
	public static void initialize() {
		a[0][0]=1;
		for(int i=1,j=1;i<a.length;i++,j++)
			a[i][0]=a[i][j]=1;	
	}
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		a=new int[n+1][n+2];
		initialize();
		Pascal();
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}
}
				