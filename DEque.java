import java.util.Scanner;
class DEque {
	static int a[],front=-1,rear=-1;
	static Scanner input=new Scanner(System.in);
	public static void enqueue_front(int data) {
		if((rear+1)%a.length==front)
			System.out.println("Queue is full");
		else if(front==-1) {
			rear=front=0;
			a[front]=data;
		}
		else {
			if(front==0) {
				front=a.length-1;
				a[front]=data;
			}
			else {
				front--;
				a[front]=data;
			}
		}
	}
	public static void enqueue_rear (int data) {
		if((rear+1)%a.length==front)
			System.out.println("Queue is full");
		else if(front==-1) {
			rear=front=0;
			a[rear]=data;
		}
		else {
			rear=(rear+1)%a.length;
			a[rear]=data;
		}
	}
	public static void dequeue_front() {
		if(front==-1) 
			System.out.println("Queue is empty");
		else {
			System.out.println(a[front]+" is dequeued ");
			if(front==rear)
				rear=front=-1;
			else 
				front=(front+1)%a.length;
		}
	}
	public static void dequeue_rear() {
		if(front==-1) 
			System.out.println("Queue is empty");
		else {
			System.out.println(a[rear]+" is dequeued ");				
			if(rear==0)
				rear=a.length-1;
			else 
				rear--;
		}
	}
	public static void display() {	
		System.out.println("\n\n");
		System.out.println("front="+front);
		if(front==-1)
			System.out.println("Queue is empty ");
		else {
			for(int i=front;;i=(i+1)%a.length) {
				System.out.println(a[i]+" ");
				if(i==rear)
					break;
			}
		}
		System.out.println("rear="+rear);
		System.out.println("\n\n");
	}
	public static void main(String args[]) {
		System.out.println("Enter the length of the queue: ");
		a=new int[input.nextInt()];
		int choice=0;
		while(choice!=6) {
			System.out.println("Enter the choice\n	1.Enqueue Fornt\n	2.Enqueue Rear\n	3.Dequeue Front\n	4.Dequeue Rear\n	5.Display\n	6.Exit");
			choice=input.nextInt();
			switch(choice) {
				case 1:System.out.println("Enter the data: ");enqueue_front(input.nextInt());break;
				case 2:System.out.println("Enter the data: ");enqueue_rear(input.nextInt());break;
				case 3:dequeue_front();break;
				case 4:dequeue_rear();break;
				case 5:display();break;
				case 6:break;
				default:System.out.println("Wrong choice");
			}
		}
	}
}
	 