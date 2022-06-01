import java.util.Scanner;
class node {
	int data;
	node left,right;
}
class BST_CAT_2_Q3 {
	static Scanner input=new Scanner(System.in);
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
	public static void inorder(node root) {
		if(root.left!=null)
			inorder(root.left);
		System.out.print(root.data+" ");
		if(root.right!=null)
			inorder(root.right);
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
	public static boolean find(node root,int key) {
		if(root.data==key)
			return true;
		if(key<root.data&&root.left!=null)
			return find(root.left,key);
		if(key>root.data&&root.right!=null)
			return find(root.right,key);
		return false;
	}
	public static void print(node root,int k1,int k2) {
		if(root.data>=k1&&root.data<=k2)
			System.out.print(root.data+" ");
		if(root.left!=null)
			print(root.left,k1,k2);
		if(root.right!=null)
			print(root.right,k1,k2);
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
		System.out.println("Inorder");
		inorder(root);
		System.out.println("\nPreorder");
		preorder(root);
		System.out.println("\nPostorder");
		postorder(root);
		System.out.println("\nEnter the value of k1 & k2\n");
		print(root,input.nextInt(),input.nextInt());
	}
}	