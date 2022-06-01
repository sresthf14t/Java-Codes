import java.util.Scanner;
class node {
	int data;
	node next;
}
class Union_of_2_SLL {
	static node head1,tail1,head2,tail2,head,tail;	
	static Scanner input=new Scanner(System.in);
	public static void create1(int n) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data : ");
			temp.data=input.nextInt();
			if(i==1) 
				head1=tail1=temp;
			else {
				tail1.next=temp;
				tail1=temp;
			}
		}	
	}
	public static void create2(int n) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data : ");
			temp.data=input.nextInt();
			if(i==1) 
				head2=tail2=temp;
			else {
				tail2.next=temp;
				tail2=temp;
			}
		}	
	}
	public static boolean check(int data) {
		node temp=head;
		boolean b=false;
		while(temp!=null) {
			if(temp.data==data) {
				b=true;break;
			}
			temp=temp.next;
		}
		return b;
	}	
	public static void L1UL2() {
		node temp,t=head1;int i=1;
		while(t!=null) {
			temp=new node();
			temp.data=t.data;
			if(i==1) 
				head=tail=temp;
			else {
				tail.next=temp;
				tail=temp;
			}
			i++;
			t=t.next;
		}
		t=head2;
		boolean b;
		while(t!=null) {
			b=check(t.data);
			if(!b) {
				temp=new node();
				temp.data=t.data;
				tail.next=temp;
				tail=temp;
			}
			t=t.next;
		}
	}
	public static void display() {
		node temp=head;
		System.out.println("head-->"+head.data+"-->"+head);
		while(temp!=null) {
			System.out.println(temp.data+"  ");
			temp=temp.next;
		}
		System.out.println("tail-->"+tail.data+"-->"+tail);
	}
	public static void display1() {
		node temp=head1;
		while(temp!=null) {
			System.out.println(temp.data+"  ");
			temp=temp.next;
		}
	}
	public static void display2() {
		node temp=head2;
		while(temp!=null) {
			System.out.println(temp.data+"  ");
			temp=temp.next;
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the first list");
		create1(input.nextInt());
		System.out.println("Enter the size of the second list");
		create2(input.nextInt());
		L1UL2();
		System.out.println("The UNION list\n");
		display();
	}
}
			
			
			