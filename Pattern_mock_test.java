import java.util.Scanner;
class Pattern_mock_test {
	public static void main(String arsg[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int temp=n;
		for(int i=0;i<=n;i++) {
		for(int l=temp;l>0;l--)
		System.out.print(" ");
		for(int j1=0;j1<=i;j1++)
		System.out.print(j1+" ");
		for(int j2=i-1;j2>=0;j2--)
		System.out.print(j2+" ");
		temp--;
		System.out.println();
		}
		temp=0;
		for(int i=(n-1);i>=0;i--) {
		for(int l=0;l<=temp;l++)
		System.out.print(" ");
		for(int j1=0;j1<=i;j1++)
		System.out.print(j1+" ");
		for(int j2=(i-1);j2>=0;j2--)
		System.out.print(j2+" ");
		temp++;
		System.out.println();
		}
	}
}