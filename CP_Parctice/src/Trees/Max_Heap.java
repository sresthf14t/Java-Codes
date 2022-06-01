/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trees;

/**
 *
 * @author User
 */
import java.util.*;

public class Max_Heap {
    //Left child=2*parent_indx+1;
    //Right child=2*parent_indx+2;
    //Parent=(child_indx-1)/2;
    static ArrayList<Integer> max_heap;
    public static void insert(int data) {
        max_heap.add(data);
        ins_heapify();
    }
    public static void ins_heapify() {
        int indx=max_heap.size()-1;
        while(max_heap.get(indx)>max_heap.get((indx-1)/2)) {
            int tmp=max_heap.get(indx);
            max_heap.set(indx, max_heap.get((indx-1)/2));
            max_heap.set((indx-1)/2,tmp);
            indx=(indx-1)/2;
        }
    }
    
    public static void del_heapify() {
        int indx=0;
        while(true) {
            int tmp_indx=-1;
            if((2*indx+1)<max_heap.size() && (2*indx+2)<max_heap.size()) {
                if(max_heap.get(2*indx+1)>max_heap.get(2*indx+2) && max_heap.get(2*indx+1)>max_heap.get(indx)) {
                    tmp_indx=2*indx+1;
                }
                else if(max_heap.get(2*indx+2)>max_heap.get(indx)){
                    tmp_indx=2*indx+2;
                }
            }
            else if((2*indx+1)<max_heap.size() && max_heap.get(2*indx+1)>max_heap.get(indx)) {
                tmp_indx=2*indx+1;
            }
            else if((2*indx+2)<max_heap.size() && max_heap.get(2*indx+2)>max_heap.get(indx)) {
                tmp_indx=2*indx+2;
            }
            if(tmp_indx==-1) {
                break;
            }
            int tmp=max_heap.get(indx);
            max_heap.set(indx, max_heap.get(tmp_indx));
            max_heap.set(tmp_indx,tmp);
            indx=tmp_indx;
        }
    }
    
    public static int delete_max() {
        int max=-1;
        if(max_heap.size()>0) {
            max=max_heap.get(0);
            max_heap.set(0, max_heap.get(max_heap.size()-1));
            max_heap.remove(max_heap.size()-1);
            if(max_heap.size()>0) {
                del_heapify();
            }
        }
        return max;
    }
    
    public static void print() {
        for(int i=0;i<max_heap.size();i++) {
            System.out.print(max_heap.get(i)+" ");
        }
        System.out.println();
    }
    
    public static void main(String args[]) {
        max_heap=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        while(true) {
            int choice=input.nextInt();
            if(choice==1) {
                insert(input.nextInt());
//                System.out.println(max_heap.get(0));
                System.out.println();
                print();
                System.out.println();
            }
            else if(choice==2) {
                System.out.println(delete_max());
                System.out.println();
                print();
                System.out.println();
            }
            else {
                break;
            }
        }
    }
}
