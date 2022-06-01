import java.util.Scanner;
class node{
	int data;
	node next;
}
class Sort_asc_SLL {
	static Scanner input=new Scanner(System.in);
	static node head=null,tail=null,temp;
	public static void create(int n) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data : ");
			temp.data=input.nextInt();
			if(i==1) 
				head=tail=temp;
			else {
				tail.next=temp;
				tail=temp;
			}
			}	
		}
	public static void sort() {
		node temp=head;
		int i=1;
		while(temp.next!=null) {
			i++;
			temp=temp.next;
		}
		temp=head;
		int a[]=new int[i];
		for(i=0;i<a.length;i++) {
			a[i]=temp.data;
			temp=temp.next;
		}
		System.out.println();
		int ind=0,t,tem=0;;
		for(i=0;i<a.length;i++) {
		int c=0;
		for(int j=i;j<a.length;j++) {
		if(a[i]>a[j]) {
		ind=j;
		tem=a[j];
		c=1;
		}
		}
		if(c==1) {
		a[ind]=a[i];
		a[i]=tem;
		}
		}
		for(i=0;i<a.length;i++)
			System.out.print(a[i]+"\t");
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the list");
		create(input.nextInt());
		System.out.println("\nThe sorted list: ");
		sort();
	}
}