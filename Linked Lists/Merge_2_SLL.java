import java.util.Scanner;
class node{
	int data;
	node next;
}
class Merge_2_SLL {
	static Scanner input=new Scanner(System.in);
	static node head=null,tail=null,head_1=null,tail_1=null;
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
	public static void create_1(int n) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data : ");
			temp.data=input.nextInt();
			if(i==1) 
				head_1=tail_1=temp;
			else {
				tail_1.next=temp;
				tail_1=temp;
			}
			}	
		}
	public static void display() {
		node temp=new node();
		temp=head;
		if(head==null)
			System.out.println("List is empty");
		while(temp!=null) {
			System.out.print(temp.data+"\t");
			temp=temp.next;
		}
	}
	public static void display_1() {
		node temp=new node();
		temp=head_1;
		if(head_1==null)
			System.out.println("List is empty");
		while(temp!=null) {
			System.out.print(temp.data+"\t");
			temp=temp.next;
		}
	}
	public static void link() {
		tail.next=head_1;
		tail_1=tail;
	}
	public static void main(String args[]) {
		System.out.println("Enter the length of the first list ");
		create(input.nextInt());
		System.out.println("Enter the length of the second list ");
		create_1(input.nextInt());
		display();
		System.out.println();
		display_1();
		link();
		System.out.println("\nThe merged list is\n");
		display();
	}
}