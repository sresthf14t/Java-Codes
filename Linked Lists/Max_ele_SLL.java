import java.util.Scanner;
class node{
	int data;
	node next;
}
class Max_ele_SLL
 {
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
	public static void largest() {
		node temp=head;
		int max=head.data;
		while(temp!=null) {
			if(max<temp.data)
				max=temp.data;
			temp=temp.next;
		}
		System.out.println("\nThe largest element in the List is:"+max);
	}
	public static void main(String args[]) {
		System.out.println("Enter the length of the list ");
		create(input.nextInt());
		System.out.println();
		display();
		largest();
	}
}