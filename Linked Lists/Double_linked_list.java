import java.util.Scanner;
class node {
	int data;
	node prev,next;
}
class Double_linked_list {
	static node tail=null,head=null;
	static Scanner input=new Scanner(System.in);
	public static void create(int n) {
		node temp;
		for(int i=1;i<=n;i++) {
			temp=new node();
			System.out.println("\nEnter the data: ");
			temp.data=input.nextInt();
			if(i==1) {
				temp.prev=null;
				head=tail=temp;
			}
			else {
				tail.next=temp;
				temp.prev=tail;
				tail=temp;
			}
		}
	}
	public static void display() {	
		node temp=head;
		if(head==null)
			System.out.println("\nList is empty");
		System.out.println("head-->"+head);
		while(temp!=null) {
			System.out.println(temp.data+"-->"+temp);			
			temp=temp.next;
		}
		System.out.println("tail-->"+tail);
	}
	public static void ins_beg() {
		node temp=new node();
		System.out.println("Enter the data: ");
		temp.data=input.nextInt();
		if(head==null) 
			head=tail=temp;
		else {
			temp.next=head;
			head.prev=temp;
			temp.prev=null;
			head=temp;
		}
	}
	public static void ins_end() {
		node temp=new node();
		System.out.println("Enter the data: ");
		temp.data=input.nextInt();
		if(head==null) 
			head=tail=temp;
		else {
			tail.next=temp;
			temp.prev=tail;
			temp.next=null;
			tail=temp;
		}
	}
	public static void ins_pos(int pos) {
		node c=head;
		node temp=new node();
		System.out.println("Enter the data: ");
		temp.data=input.nextInt();
		for(int i=2;i<pos;i++) {
			c=c.next;
			if(c==null)
				break;
		}
		if(c!=null) {
			temp.next=c.next;
			c.next=temp;
			temp.prev=c;
			if(c!=tail)
				c.next.prev=temp;
			else
				tail=temp;
		}
		else 
			System.out.println(pos+" is an invalid position");
	}
	public static void del_beg() {
		if(head==tail)
			head=tail=null;
		else {
			node temp=head;
			head=head.next;
			head.prev=null;
			temp.next=null;
			temp=null;
		}
	}
	public static void del_end() {
		if(head==tail)
			head=tail=null;
		else {
			node temp=new node();
			temp=tail;
			tail=tail.prev;
			tail.next=null;temp.prev=null;
			temp=null;
		}
	}
	public static void del_pos(int pos) {
		node c=head;
		for(int i=1;i<pos;i++) {
			c=c.next;
			if(c==null)
				break;
		}
		if(c!=null) {
			c.prev.next=c.next;
			if(c!=tail)
				c.next.prev=c.prev;
			else
				tail=c.prev;
			c.next=c.prev=null;
			c=null;
		}
		else 
			System.out.println(pos+" is invalid");
	}
	public static void search(int key) {
		node temp=head;
		int i=1;
		while(temp!=null) {
			if(temp.data==key) {
				System.out.println(key+" found at "+i+"th position");
				break;
			}
			temp=temp.next;
			i++;
		}
		if(temp==null)
			System.out.println("Key not found");
	}	
	public static void main(String args[]) {
		System.out.println("Enter the number of nodes to be created: ");
		int n=input.nextInt();
		create(n);
		int choice=0;
		while(choice!=9) {
			System.out.println("Enter the choice: ");
			System.out.println("\n1.Insert at begining\n2.Insert at end\n3.Insert at position\n4.Delete at begening\n5.Delete at end\n6.Delete at position\n7.Search\n8.Display\n9.Exit\n");
			choice=input.nextInt();
			switch(choice) {
				case 1:ins_beg();break;
				case 2:ins_end();break;
				case 3:System.out.println("Enter the position: ");ins_pos(input.nextInt());break;
				case 4:del_beg();break;
				case 5:del_end();break;
				case 6:System.out.println("Enter the position: ");del_pos(input.nextInt());break;
				case 7:System.out.println("Enter the key: ");search(input.nextInt());break;
				case 8:display();break;
			}
		}
	}
}
				
			
				