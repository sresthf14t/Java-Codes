import java.util.Scanner;
import java.util.Random; 
import java.util.Scanner;
class node{
	int data;
	node next;
}
class Seperate_chaining {
	static Scanner input=new Scanner(System.in);
	static node a[];
	public static node ins_beg(node head,int data) {
		node temp=new node();
		temp.data=data;
		if(head==null)
			head=temp;
		else {
			temp.next=head;
			head=temp;
		}
		return head;
	}
	public static void display(node head) {
		node temp=new node();
		temp=head;
		if(head==null)
			System.out.print("List is empty");
		while(temp!=null) {
			System.out.print(temp.data+"-->");
			temp=temp.next;
		}
		System.out.println("\n");
	}
	public static node[] Hash_table(int n) {
		a=new node[n];
		Random rand = new Random(); 
		int temp,ind;
		for(int i=0;i<n;i++) {
			temp=rand.nextInt(n*n);
			ind=temp%n;
			a[ind]=ins_beg(a[ind],temp);
		}
		return a;
	}
	public static void print_HT(int n) {
		for(int i=0;i<n;i++) {
			System.out.print("\n"+i+".)   "+a[i]+"--> ");
			display(a[i]);
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the number of elements");
		int n=input.nextInt();
		Hash_table(n);
		print_HT(n);
	}
}