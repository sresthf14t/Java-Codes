import java.util.Scanner;
class PnC_alpha {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		char c[]=new char[26];
		for(int i=97,j=0;i<=122;i++,j++) 
		c[j]=(char)i;
		String s="";
		for(int i=1;i<=n;i++) {
		for(int j=1;j<=i;j++) {
		for(int k=0;k<26;k++) {
		s=s+c[k];
		System.out.println(s);
		}
		}
		}
	}
}