import java.util.Scanner;
import java.io.*;
class Tic_tac_toe {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int a[][]=new int[3][3];
		System.out.println("Referance Coordinates:\n");		
		for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
		System.out.print("("+i+","+j+") ");
		a[i][j]=-5;
		}
		System.out.println();
		}
		System.out.println("\n");
		int x,y;
		System.out.println("Player 1->X\nPlayer 2->O");
		for(int i=1;i<=9;) {
		System.out.println();
		System.out.println();
		if(i%2==1)	
		System.out.println("Player 1 enter the coordinates");
		else
		System.out.println("Player 2 enter the coordinates");
		x=input.nextInt();
		y=input.nextInt();
		System.out.println();
		System.out.println();		
		if(x>2||y>2) {
		System.out.println("Invalid move");
		continue;
		}
		else if(a[x][y]!=-5) {
		System.out.println("Invalid move");
		continue;
		}
		else {
		if(i%2==1)
		a[x][y]=1;
		else
		a[x][y]=0;
		i++;	
		}
		for(int X=0;X<3;X++) {
		for(int Y=0;Y<3;Y++) {
		if(a[X][Y]==1)
		System.out.print("X ");
		else if(a[X][Y]==0)
		System.out.print("O ");
		else 
		System.out.print("_ ");
		}
		System.out.println();
		}
		check(a);
		}
		System.out.println("Match is Draw");
	}
	public static void check(int a[][]) {
		int sumr,sumc,sumd1,sumd2;
		for(int i=0;i<3;i++) {
		sumr=0;
		for(int j=0;j<3;j++) {
		sumr+=a[i][j];
		}
		if(sumr==0) 
		print(false);
		else if(sumr==3)
		print(true);
		}
		for(int i=0;i<3;i++) {
		sumc=0;
		for(int j=0;j<3;j++) {
		sumc+=a[j][i];
		}
		if(sumc==0)
		print(false);
		else if(sumc==3)
		print(true);
		}
		sumd1=a[0][0]+a[1][1]+a[2][2];
		if(sumd1==0)
		print(false);
		else if(sumd1==3)
		print(true);
		sumd2=a[0][2]+a[1][1]+a[2][0];
		if(sumd2==0)
		print(false);
		else if(sumd2==3)
		print(true);
	}	
	public static void print(boolean b) {
		if(b) {
		System.out.println("\nPlayer 1 wins");
		System.exit(0);
		}
		else
		System.out.println("\nPlayer 2 wins");
		System.exit(0);
	}
		public static void clrscr(){
    //Clears Screen in java
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {}
}

}
		