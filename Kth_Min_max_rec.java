import java.util.Scanner;
class Kth_Min_max_rec {
	public static void main(String args[]) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		a[i]=input.nextInt();
		int b[]=new int[n];
		b=a;
		int k=input.nextInt();
		int max=0,min=0;
		for(int i=1;i<=k;i++) {
		max=max_rec(a,a[0],(n-1));
		min=min_rec(a,a[0],(n-1));
		for(int p=0;p<n;p++) {
		if(a[p]==max)
		a[p]=min;
		}
		}
		System.out.println(k+"th maximum element="+max);
		for(int i=1;i<=k;i++) {
		max=max_rec(a,a[0],(n-1));
		min=min_rec(a,a[0],(n-1));
		for(int p=0;p<n;p++) {
		if(a[p]==min)
		a[p]=max;
		}
		}
		System.out.println(k+"th mainimum element="+min);					
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
		
		
