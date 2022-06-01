import java.util.Scanner;
class Upper_Triangle {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int r,c;
		r=input.nextInt();
		c=input.nextInt();
		int a[][]=new int[r][c];
		for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++)
		if(i<=j)
		System.out.print("*");
		else
		System.out.print(" ");
		System.out.println();
		}
	}
}