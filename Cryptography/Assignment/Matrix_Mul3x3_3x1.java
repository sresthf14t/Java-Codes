import java.util.Scanner;
class Matrix_Mul3x3_3x1 {
	static int[][] Multiply(int k[][],int p[][]) {
		int c[][]=new int[p.length][p[0.length]];
		for(int i=0;i<3;i++) {
			for(int j=0;j<p.length;j++) {
				c[i][j]=(k[i][0]*p[0][i])+(k[i][1]*p[1][i])+(k[i][2]*p[2][i]);
			}
		}
	}