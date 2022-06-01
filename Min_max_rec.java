import java.util.Scanner;
class Min_max_rec {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		a[i]=input.nextInt();
		System.out.println("Max="+max_rec(a,a[0],(n-1)));
		System.out.println("Min="+min_rec(a,a[0],(n-1)));	
	}
		public static int max_rec(int a[],int max,int i) {
		if(i==1) {
		if(a[i]>max)
		return(a[i]);
		else
		return max;
		}
		else {
		if(a[i]>max) 
		return max_rec(a,a[i],(i-1));
		else
		return max_rec(a,max,(i-1));
		}
	}
		public static int min_rec(int a[],int min,int i) {
		if(i==1) {
		if(a[i]<min)
		return(a[i]);
		else
		return min;
		}
		else {
		if(a[i]<min) 
		return min_rec(a,a[i],(i-1));
		else
		return min_rec(a,min,(i-1));
		}
	}
}
		
		
