import java.util.Scanner;
class Insert_array {
	public static int[] append(int a[],int curr_ele,int ele) {
		a[curr_ele+1]=ele;
		return a;
	}
	public static int[] insert_strt(int a[],int ele,int curr_ele) {	
		for(int i=curr_ele;i>=0;i--)
		a[i+1]=a[i];
		a[0]=ele;
		return a;
	}
	public static int[] insert(int a[],int ele,int pos) {
		for(int i=13;i>=pos;i--) 
		a[i+1]=a[i];
		a[pos]=ele;
		return a;
	}
	public static void main(String args[]) {
		int a[]=new int[15];
		Scanner input=new Scanner(System.in);
		int curr_ele=-1;
		int c=-1;
		while(c!=0) {
		System.out.println("Enter the element to be inserted");
		int ele=input.nextInt();
		System.out.println("Where the element is to be inserted");
		System.out.println("	0.Exit\n	1.Starting\n	2.End\n	3.elsewhere");
		c=input.nextInt();
		if(c==1) {
		a=insert_strt(a,ele,curr_ele);
		curr_ele++;
		}
		else if(c==2) {
		a=append(a,curr_ele,ele);
		curr_ele++;
		}
		else if(c==3) {
		System.out.println("Enter the position in which you want to enter the element");
		int pos=input.nextInt();
		a=insert(a,ele,pos);
		curr_ele++;
		}
		for(int i=0;i<=curr_ele;i++)
		System.out.print(a[i]+" ");
		System.out.println();
		}
	}
}
	