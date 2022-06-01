import java.util.Scanner;
class node {
	int data;
	node left,right;
}
class BST_kth_smallest {
	static Scanner input=new Scanner(System.in);
	static int te=0;
	public static node insert(node root,int key) {
		 if(root==null) {
			root=new node();
			root.data=key;
			System.out.println("node "+key+" is created");
			return root;
		}
		if(key>root.data) {
			System.out.println("going to right sub tree of "+root.data);
			root.right=insert(root.right,key);
		}
		else if(key<root.data) {
			System.out.println("going to left sub tree of "+root.data);
			root.left=insert(root.left,key);
		}
		return root;
	}
	public static void inorder(node root,int k) {
		if(root==null)
			return;
		inorder(root.left,k);
		te++;
		if(te==k) {
			System.out.print(root.data+" ");
		}
		inorder(root.right,k);
	}
	public static void preorder(node root) {
		System.out.print(root.data+" ");
		if(root.left!=null)
			preorder(root.left);
		if(root.right!=null)
			preorder(root.right);
	}
	public static void postorder(node root) {
		if(root.left!=null)
			postorder(root.left);
		if(root.right!=null)
			postorder(root.right);
		System.out.print(root.data+" ");
	}
	public static void main(String args[]) {
		node root=null;
		System.out.println("Enter the number of nodes to be created");
		int n=input.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("Enter the data");
			root=insert(root,input.nextInt());
			System.out.println("\n");
		}
		for(int i=0;i<n;i++) {
			te=0;
			System.out.println("Insert k");
			inorder(root,input.nextInt());
		}
	}
}	