import java.util.Scanner;
public class SUDOKU {
	//'0' determines empty place
	static Scanner input=new Scanner(System.in);
	static int su[][]=new int[9][9];
	public static String check_missing(String s) { //returns string of missing digits between 1-9
		boolean check[]=new boolean[10];
		String missing="";
		for(int i=1;i<s.length();i++)
			check[s.charAt(i)-48]=true;
		for(int i=1;i<check.length;i++)  {
			if(!check[i])
				missing+="i";
		}
		return missing;
	}
	public static void print() {
		for(int i=0;i<su.length;i++) {
			System.out.println();
			for(int j=0;j<su[i].length;j++) {
				System.out.print(su[i][j]+" ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			if((i+1)%3==0)
				System.out.println();
		}
	}	
	public static void main(String args[]) {
		System.out.println("Enter the sudoku");
		for(int i=0;i<su.length;i++) {
			for(int j=0;j<su[i].length;j++) {
				su[i][j]=i;
			}
		}		
		print();
	}
}