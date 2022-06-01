import java.util.Scanner;
class node {
	int data;
	node left,right;
	node(int d) {
		data=d;
		left=right=null;
	}
}
class Binary_Tree {
	static Scanner input=new Scanner(System.in);
	public static node insert(int data,node root) {
		if(root==null) {
			node temp=new node(data);
			root=temp;
			System.out.println("Val="+1);
		}
		else {
			System.out.println("Enter L for left node and R for right node of "+root.data);
			char ch=input.next().charAt(0);
			if(ch=='L'||ch=='l') {
				insert(data,root.left);
				System.out.println("Val="+2.0);
			}
			else {
				insert(data,root.right);
				System.out.println("Val="+2.1);
			}
		} 
		return root;
	}	
	public static void main(String args[]) {
		node root=null;
		System.out.println("How many nodes you want to insert ");
		int n=input.nextInt();
		for(int i=1;i<=n;i++) {
			System.out.println("Enter the data ");
			insert(input.nextInt(),root);
			root=System.out.println("Root.data="+root.data);
			System.out.println("\nNode is inserted");
		}
	}
}
		 		
			
		
					