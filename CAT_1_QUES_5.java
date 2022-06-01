import java.util.Scanner;
class node { 
	int data;
	node next;
}
class CAT_1_QUES_5 {	
	static node head_l,tail_l,head_p,tail_p;
	static Scanner input=new Scanner(System.in);
	public static void create(int n,boolean b) {
		for(int i=1;i<=n;i++) {
			node temp=new node();
			System.out.println("Enter the data");
			temp.data=input.nextInt();
			if(i==1) {
				if(b)
					head_l=tail_l=temp;
				else
					head_p=tail_p=temp;
			}
			else {
				if(b) {
					tail_l.next=temp;
					tail_l=temp;
				}
				else {
					tail_p.next=temp;
					tail_p=temp;
				}
			}
		}
	}
	public static void display(boolean b) {
		node temp;
		if(b) {
			temp=head_l;
			System.out.println("head_l-->"+head_l);
		}
		else {
			temp=head_p;
			System.out.println("head_p-->"+head_p);
		}
		int i=1;
		while(temp!=null) {
			System.out.println(i+". "+temp.data+"-->"+temp);
			temp=temp.next;
			i++;
		}
		if(b)
			System.out.println("tail_l-->"+tail_l);
		else
			System.out.println("tail_p-->"+tail_p);
	}
	public static void printlots() {
		node temp_l,temp_p=head_p;
		int temp;
		while(temp_p!=null) {
			temp=temp_p.data;
			temp_l=head_l;
			for(int i=1;i<temp;i++) 
				temp_l=temp_l.next;
			System.out.println(temp_p.data+" position of list L contains-->"+temp_l.data);
			temp_p=temp_p.next;
		}
	}
	public static void main(String args[]) {
		System.out.println("Enter the size of the list L");
		create(input.nextInt(),true);
		System.out.println();
		System.out.println("Enter the size of the list P");
		create(input.nextInt(),false);
		System.out.println("\nList L");
		display(true);
		System.out.println("\nList P");
		display(false);	
		System.out.println("\nPrintLost: \n");
		printlots();
	}
}