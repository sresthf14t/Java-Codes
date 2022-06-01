import java.util.Scanner;
class node{
	int data;
	node next;
}
class Find_ele__SLL_recursive {
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
	public static void search(int key,int i) {
		if(i==1)
			temp=head;
		if(temp==null) {
			System.out.println(key+" not found in the list");
			return;
		}		
		else if(temp.data==key) {
			System.out.println(key+" found at the "+i+"th position");
			return;
		}
		else {
			temp=temp.next;
			search(key,i+1);
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the number of nodes to be created: ");
		int n=input.nextInt();
		create(n);
		int choice=0;
		while(choice!=3) {
			System.out.println("\nEnter the choice: ");
			System.out.println("\n1.Search\n2.Display\n3.Exit\n");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the key: ");int key=input.nextInt();search(key,1);break;
				case 2:display();break;
			}
		}
	}
}
				