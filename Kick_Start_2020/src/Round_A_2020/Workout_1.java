/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.util.Scanner;
public class Workout_1 {
    
    private static int[] Heap; 
    private static int size; 
    private static int maxsize; 
  
    // Constructor to initialize an 
    // empty max heap with given maximum 
    // capacity. 
    public Workout_1(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new int[this.maxsize + 1]; 
        Heap[0] = Integer.MAX_VALUE; 
    } 
  
    // Returns position of parent 
    private static int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Below two functions return left and 
    // right children. 
    private static int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
    private static int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Returns true of given node is leaf 
    private static boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    private static void swap(int fpos, int spos) 
    { 
        int tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    // A recursive function to max heapify the given 
    // subtree. This function assumes that the left and 
    // right subtrees are already heapified, we only need 
    // to fix the root. 
    private static void maxHeapify(int pos) 
    { 
        if (isLeaf(pos)) 
            return; 
  
        if (Heap[pos] < Heap[leftChild(pos)] ||  
            Heap[pos] < Heap[rightChild(pos)]) { 
  
            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) { 
                swap(pos, leftChild(pos)); 
                maxHeapify(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                maxHeapify(rightChild(pos)); 
            } 
        } 
    } 
  
    // Inserts a new element to max heap 
    public static void insert(int element) 
    { 
        Heap[++size] = element; 
  
        // Traverse up and fix violated property 
        int current = size; 
        while (Heap[current] > Heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
  
    public static void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + 
                      Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]); 
            System.out.println(); 
        } 
    } 
  
    // Remove an element from max heap 
    public static int extractMax() 
    { 
        int popped = Heap[1]; 
        Heap[1] = Heap[size--]; 
        maxHeapify(1); 
        return popped; 
    } 

    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            
            maxsize = 10000000; 
            size = 0; 
            Heap = new int[maxsize + 1]; 
            Heap[0] = Integer.MAX_VALUE; 
            
            
            int n=input.nextInt();
            int k=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            for(int i=0;i<n-1;i++) {
                insert(arr[i+1]-arr[i]);
            }
            int ans=-1;
            for(int i=0;i<k;i++) {
                System.out.println();
                print();
                System.out.println();
                int max=extractMax();
                if(max==1) {
                    ans=max;
                    break;
                }
                int a=(int)Math.ceil((double)max/2),b=max-a;
                insert(a);
                insert(b);
            }
            if(ans==-1) {
                ans=extractMax();
            }
            System.out.println("Case #"+t+": "+ans);
        }
    }
}