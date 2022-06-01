import java.util.Scanner;
class Sinx_graph {
	public static void main(String arsg[])  {
		char g[][]=new char[201][800];
		double a,temp;
		int b;
		for(int y=0;y<750;y++) {
			temp=(double)y;
			temp=temp/100;
			a=Math.sin(temp);
			a*=100;
			b=(int)a;
			g[100-b][y]='.';
			System.out.println("b="+b+"\ta="+a+"\ty="+y);
		}
		for(int i=0;i<g.length;i++) {
			for(int j=0;j<g[i].length;j++)
				System.out.print(g[i][j]);
			System.out.println();
		}
	}
}