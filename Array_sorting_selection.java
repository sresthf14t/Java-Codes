import java.util.Scanner;
class Array_sorting_selection {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++) 
		a[i]=input.nextInt();
		ArraySorter.selectionsort(a);
		display(a);
	}
}