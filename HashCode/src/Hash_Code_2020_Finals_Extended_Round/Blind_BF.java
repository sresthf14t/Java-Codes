/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash_Code_2020_Finals_Extended_Round;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Blind_BF {
    static int arr[][];
    static int n,m,arms,num_mounting_points,tasks,steps,m_points_x[],m_points_y[],score_of_task[];
    static ArrayList<Integer> task_description[];
    static String filename[]={"a_example","b_single_arm","c_few_arms","d_tight_schedule","e_dense_workspace","f_decentralized"};
    public static void main(String args[]) throws IOException {
        for(int t=0;t<filename.length;t++) {
            Scanner input=new Scanner(new File("C:\\Java Codes\\My_Codes\\HashCode\\src\\Hash_Code_2020_Finals_Extended_Round\\Files\\"+filename[t]+".txt"));
            n=input.nextInt();
            m=input.nextInt();
            arr=new int[n][m];
            arms=input.nextInt();
            num_mounting_points=input.nextInt();
            tasks=input.nextInt();
            steps=input.nextInt();
            m_points_x=new int[num_mounting_points];
            m_points_y=new int[num_mounting_points];
            score_of_task=new int[tasks];
            for(int i=0;i<num_mounting_points;i++) {
                m_points_y[i]=input.nextInt();
                m_points_x[i]=input.nextInt();
                arr[m_points_x[i]][m_points_y[i]]=1;
            }
            task_description=new ArrayList[tasks]; //Score x0 y0 x1 y10..... xn yn
            for(int i=0;i<tasks;i++) {
                task_description[i]=new ArrayList<>();
                int score=input.nextInt();
                task_description[i].add(score);
                int p=input.nextInt();
                for(int j=0;j<p;j++) {
                    task_description[i].add(input.nextInt());
                    task_description[i].add(input.nextInt());
                }
            }
            System.out.println("Input Complete");
            solve(t);
        }
    }
    public static void solve(int t) {
        int time=0,count=0;
        StringBuilder ans=new StringBuilder("");
        ArrayList<Integer> tasks_cmpleted=new ArrayList<>();
        for(int i=0;i<tasks;i++) {
            System.out.println("TASK#"+i+" "+task_description[i].size());
            boolean is_pos=true;
            int strt_x=m_points_x[0],strt_y=m_points_y[0];
            StringBuilder tmp=new StringBuilder("");
            for(int j=1;j<task_description[i].size();j+=2) {
                int target_y=task_description[i].get(j);
                int target_x=task_description[i].get(j+1);
                path=new StringBuilder("");
//                DFS(strt_x,strt_y,target_x,target_y,new StringBuilder(""),strt_x,strt_y,new boolean[n][m]);
                path=BFS(strt_x,strt_y,target_x,target_y);
                if(path.length()==0) {
                    is_pos=false;
                    break;
                }
                backtrack(path);
                tmp.append(path);
//                System.out.println(strt_x+" "+strt_y+" "+target_x+" "+target_y+" "+tmp);
//                strt_x=target_x;
//                strt_y=target_y;
            }
            if(is_pos) {
                if(time+tmp.length()<=steps) {
                    count++;
                    ans.append(tmp);
                    tasks_cmpleted.add(i);
                    time+=tmp.length();
                }
                else {
                    break;
                }
//                time+=2*tmp.length();
//                backtrack(tmp);
//                count++;
//                ans.append(tmp);
//                tasks_cmpleted.add(i);
            }
        }
        Write_to_File(count,tasks_cmpleted,ans,t);
//        System.out.println(count);
//        System.out.println(m_points_x[0]+" "+m_points_y[0]+" "+count+" "+ans.length());
//        for(int i=0;i<tasks_cmpleted.size();i++) {
//            System.out.print(tasks_cmpleted.get(i)+" ");
//        }
//        System.out.println();
//        for(int i=0;i<ans.length();i++) {
//            System.out.print(ans.charAt(i)+" ");
//        }
//        System.out.println();
    }
    public static void Write_to_File(int count,ArrayList<Integer> tasks_cmpleted,StringBuilder ans,int t) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Java Codes\\My_Codes\\HashCode\\src\\Hash_Code_2020_Finals_Extended_Round\\Files\\output_"+filename[t]+".txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        outputstream.println(1);
        outputstream.println(m_points_y[0]+" "+m_points_x[0]+" "+count+" "+ans.length());
        for(int i=0;i<tasks_cmpleted.size();i++) {
            outputstream.print(tasks_cmpleted.get(i)+" ");
        }
        outputstream.println();
        for(int i=0;i<ans.length();i++) {
            outputstream.print(ans.charAt(i)+" ");
        }
        outputstream.println();
        outputstream.close();
    }
    public static void backtrack(StringBuilder str) {
        int len=str.length();
        StringBuilder tmp=new StringBuilder("");
        for(int i=len-1;i>=0;i--) {
            if(str.charAt(i)=='D') {
                tmp.append('U');
            }
            if(str.charAt(i)=='U') {
                tmp.append('D');
            }
            if(str.charAt(i)=='R') {
                tmp.append('L');
            }
            if(str.charAt(i)=='L') {
                tmp.append('R');
            }
        }
        str.append(tmp);
    }
    static StringBuilder path=new StringBuilder("");
//    public static void DFS(int i,int j, int target_x,int target_y,StringBuilder str,int strt_x,int strt_y,boolean vis[][]) {
////        System.out.println(i+" "+j);
//        if(arr[i][j]==1 && i!=strt_x && j!=strt_y) {
//            return;
//        }
//        if(vis[i][j]) {
//            return;
//        }
//        if(i==target_x && j==target_y) {
//            if(path.length()==0 || str.length()<path.length()) {
//                path=new StringBuilder(str);
//            }
//            return;
//        }
//        vis[i][j]=true;
//        if(i!=0 && !vis[i-1][j]) {
//            str.append('L');
//            DFS(i-1,j,target_x,target_y,str,strt_x,strt_y,vis);
//            str.deleteCharAt(str.length()-1);
//        }
//        if(i!=n-1 && !vis[i+1][j]) {
//            str.append('R');
//            DFS(i+1,j,target_x,target_y,str,strt_x,strt_y,vis);
//            str.deleteCharAt(str.length()-1);
//        }
//        
//        if(j!=0 && !vis[i][j-1]) {
//            str.append('D');
//            DFS(i,j-1,target_x,target_y,str,strt_x,strt_y,vis);
//            str.deleteCharAt(str.length()-1);
//        }
//        if(j!=m-1 && !vis[i][j+1]) {
//            str.append('U');
//            DFS(i,j+1,target_x,target_y,str,strt_x,strt_y,vis);
//            str.deleteCharAt(str.length()-1);
//        }
//        vis[i][j]=false;
//    }
    
    public static StringBuilder BFS(int src_x,int src_y,int target_i,int target_j) {
        System.out.println("\tBFS");
        boolean vis[][]=new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]==1) {
                    vis[i][j]=true;
                }
            }
        }
        int parent_i[][]=new int[n][m];
        int parent_j[][]=new int[n][m];
        Queue<Integer> que_i = new LinkedList<>();
        Queue<Integer> que_j = new LinkedList<>();
        que_i.add(src_x);
        que_j.add(src_y);
        vis[src_x][src_y]=true;
        parent_i[src_x][src_y]=-1;
        parent_j[src_x][src_y]=-1;
        while(!que_i.isEmpty() && !que_j.isEmpty()) {
            int i=que_i.peek();
            int j=que_j.peek();
            if(target_i==i && target_j==j) {
                break;
            }
            if(i!=0 && !vis[i-1][j]) {
                que_i.add(i-1);
                que_j.add(j);
                vis[i-1][j]=true;
                parent_i[i-1][j]=i;
                parent_j[i-1][j]=j;
            }
            if(i!=n-1 && !vis[i+1][j]) {
                que_i.add(i+1);
                que_j.add(j);
                vis[i+1][j]=true;
                parent_i[i+1][j]=i;
                parent_j[i+1][j]=j;
            }
            if(j!=0 && !vis[i][j-1]) {
                que_i.add(i);
                que_j.add(j-1);
                vis[i][j-1]=true;
                parent_i[i][j-1]=i;
                parent_j[i][j-1]=j;
            }
            if(j!=m-1 && !vis[i][j+1]) {
                que_i.add(i);
                que_j.add(j+1);
                vis[i][j+1]=true;
                parent_i[i][j+1]=i;
                parent_j[i][j+1]=j;
            }
            que_i.poll();
            que_j.poll();
        }
        System.out.println("\tPATH FOUND");
//        System.out.println(parent_i[53][36]+" "+parent_j[53][36]);
        StringBuilder path=new StringBuilder("");
        int i=target_i,j=target_j;
        while(!(i==src_x && j==src_y)) {
//            System.out.println("\t"+i+" "+j);
            if(parent_i[i][j]==i-1) {
                i--;
                path.append('U');
            }
            else if(parent_i[i][j]==i+1) {
                i++;
                path.append('D');
            }
            else if(parent_j[i][j]==j-1) {
                j--;
                path.append('R');
            }
            else if(parent_j[i][j]==j+1) {
                j++;
                path.append('L');
            }
        }
        path.reverse();
        return path;
    }
}
