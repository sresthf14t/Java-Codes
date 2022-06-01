import java.util.Scanner;
class node{
	int data;
	node next;
}
class Single_linked_list {
	static Scanner input=new Scanner(System.in);
	static node head=null,tail=null;
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
		System.out.println("head-->\t"+head);
		if(head==null)
			System.out.println("List is empty");
		while(temp!=null) {
			System.out.println(temp.data+"-->\t"+temp);
			temp=temp.next;
		}
		System.out.println("tail-->\t"+tail);
		System.out.println("tail.next-->"+tail.next);
		System.out.println("\n");
	}
	public static void ins_beg() {
		node temp=new node();
		System.out.println("Enter the data");
		temp.data=input.nextInt();
		if(head==null)
			head=tail=temp;
		else {
			temp.next=head;
			head=temp;
		}
	}
	public static void ins_end() {
		node temp=new node();
		System.out.println("Enter the data");
		temp.data=input.nextInt();
		if(head==null)
			head=tail=temp;
		else {
			tail.next=temp;
			tail=temp;
		}
	}
	public static void ins_pos(int p) {
		node temp=new node();
		node c=head;
		System.out.println("Enter the data");
		temp.data=input.nextInt();
		for(int i=2;i<p;i++)
			c=c.next;
		if(c==tail)
			tail=temp;
		temp.next=c.next;
		c.next=temp;
	}
	public static void del_beg() {
		if(head==tail)
			head=tail=null;
		else {
			node temp=head;	
			head=head.next;
			temp=null;
		}
	}
	public static void del_end() {
		if(head==tail) 
			head=tail=null;
		else {
			node c=head;
			while(c.next!=tail) 
				c=c.next;
			c.next=null;
			tail=c;
			c=null;
		}
	}
	public static void del_pos(int pos) {
		node c=head,temp=head.next;
		for(int i=2;i<pos;i++) {
			c=c.next;
			temp=temp.next;
		}
		c.next=temp.next;
		if(temp==tail)
			tail=c;
		temp.next=null;
		temp=null;
	}
	public static void search(int key) {
		node temp=head;
		int i=1;
		while(temp!=null) {
			if(temp.data==key) {
				System.out.println("Key found at "+i+"th position");
				break;
			}
			temp=temp.next;
			i++;
		}
		if(temp==null)
			System.out.println("Key not found");
	}
	public static void reverse() {
		node curr=head,prev=null,aft=head.next,temp=head;
		while(curr!=null) {
			curr.next=prev;
			prev=curr;
			curr=aft;
			if(curr!=null)
				aft=aft.next;		
		}
		head=tail;
		tail=temp;
	}
	public static void main(String args[]) {
		System.out.println("Enter the number of nodes to be created: ");
		int n=input.nextInt();
		create(n);
		int choice=0;
		while(choice!=10) {
			System.out.println("Enter the choice: ");
			System.out.println("\n1.Insert at begining\n2.Insert at end\n3.Insert at position\n4.Delete at begening\n5.Delete at end\n6.Delete at position\n7.Search\n8.Display\n9.Reverse\n10.Exit\n");
			choice=input.nextInt();
			switch(choice) {
				case 1:ins_beg();break;
				case 2:ins_end();break;
				case 3:System.out.println("Enter the position: ");ins_pos(input.nextInt());break;
				case 4:del_beg();break;
				case 5:del_end();break;
				case 6:System.out.println("Enter the position: ");del_pos(input.nextInt());break;
				case 7:System.out.println("Enter the key: ");int key=input.nextInt();search(key);break;
				case 8:display();break;
				case 9:reverse();break;
			}
		}
	}
}
				
				