import java.util.Scanner;
class node {
	int data;
	node next,prev;
}
class Right_shift_DLL {
	static node head=null,tail=null;
	static Scanner input=new Scanner(System.in);	
	public static void create(int n) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data");
			temp.data=input.nextInt();
			if(i==1) 
				head=tail=temp;
			else {
				tail.next=temp;
				temp.prev=tail;
				tail=temp;
			}
		}
	}
	public static void display() {
		node temp=head;
		System.out.println("head-->"+head);
		while(temp!=null) {
			System.out.println(temp.data+"-->"+temp);
			temp=temp.next;
		}
		System.out.println("tail-->"+tail);
	}
	public static void rotate_right(int k) {
		for(int i=1;i<=k;i++) {
			node temp=tail;
			int t=tail.data;
			while (temp!=head) {
				temp.data=temp.prev.data;
				temp=temp.prev;
			}
			head.data=t;
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the number of elements of the list: ");
		create(input.nextInt());
		System.out.println();
		display();
		System.out.println("Enter the number of time to shift right: ");
		rotate_right(input.nextInt());
		System.out.println("After Shifting\n");
		display();
	}
}