import java.util.Scanner;
class Array_Sort_n {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int a[]=new int[5];
		for(int i=0;i<5;i++)
		a[i]=input.nextInt();
		int ind=0,t,temp=0;;
		for(int i=0;i<5;i++) {
		int c=0;
		for(int j=i;j<5;j++) {
		if(a[i]>a[j]) {
		ind=j;
		temp=a[j];
		c=1;
		}
		}
		if(c==1) {
		a[ind]=a[i];
		a[i]=temp;
		}
		}
		System.out.println();
		for(int i=0;i<5;i++)
		System.out.print(a[i]+" ");
	}
}