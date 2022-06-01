import java.util.Scanner;
class Circular_Queue_using_array {
	static int a[],front=-1,rear=-1;
	static Scanner input=new Scanner(System.in);
	public static void enqueue(int data) {
		if(front==(rear+1)%a.length)
			System.out.println("Queue is full");
		else {
			if(front==-1)
				front=0;
			rear=(rear+1)%a.length;
			a[rear]=data;
		}
	}
	public static void dequeue() {
		if(front==-1)
			System.out.println("Queue is empty ");
		else {
			System.out.println(a[front]+" is dequeued");
			if(front==rear)
				front=rear=-1;
			else
			front=(front+1)%a.length;
		}
	}
	public static void display() {
		System.out.println("\nFront="+front+"\tRear="+rear);
		if(front==-1)
			System.out.println("Queue is empty ");
		else {
			int i=front;
			for(;;i=(i+1)%a.length) {
				System.out.println(a[i]);
				if(i==rear)	
				break;
			}
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the queue: ");
		a=new int[input.nextInt()];
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
	
			

	