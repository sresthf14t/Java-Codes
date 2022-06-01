import java.util.Scanner;
class node {
	int data;
	node next,prev;
}
class Queue_using_DLL {
	static node front=null,rear=null;
	static Scanner input=new Scanner(System.in);
	public static void enqueue(int data) {
		node temp=new node();
		temp.data=data;
		if(front==null)
			front=rear=temp;
		else {
			rear.next=temp;
			temp.prev=rear;
			rear=temp;
		}
	}
	public static void dequeue() {
		if(front==null)
			System.out.println("Queue is empty ");
		else if(front==rear) {
			System.out.println(front.data+" is dequeued ");			
			front=rear=null;
		}
		else {
			System.out.println(front.data+" is dequeued ");
			front=front.next;
			front.prev.next=null;
			front.prev=null;
		}
	}
	public static void display() {
		node temp=front;
		while(temp!=null) {
			System.out.println(temp.data);
			temp=temp.next;
		}
	}
	public static void main(String args[]) {	
		int choice=0;
		while(choice!=4) {
			System.out.println("Enter the choice\n	1.Enqueue\n	2.Dequeue\n	3.Display\n	4.Exit");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the data: ");enqueue(input.nextInt());break;
				case 2:dequeue();break;
				case 3:display();break;
				case 4:break;
				default:System.out.println("Wrong choice");
			}
		}
	}
}
			
		
		