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
public class BF_3 {
    static int arr[][];
    static int n,m,arms,num_mounting_points,tasks,steps,m_points_x[],m_points_y[],score_of_task[],curr_pos_x[],curr_pos_y[],score_sorted_tasks[];
    static ArrayList<Integer> task_description[];
    static String filename[]={"a_example","b_single_arm","c_few_arms","d_tight_schedule","e_dense_workspace","f_decentralized"};
    public static void main(String args[]) throws IOException {
        for(int t=0;t<filename.length-1;t++) {
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
            curr_pos_x=new int[num_mounting_points];
            curr_pos_y=new int[num_mounting_points];
            score_of_task=new int[tasks];
            for(int i=0;i<num_mounting_points;i++) {
                m_points_x[i]=input.nextInt();
                m_points_y[i]=input.nextInt();
                curr_pos_x[i]=m_points_x[i];
                curr_pos_y[i]=m_points_y[i];
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
    static boolean fin_vis[][];
    public static void solve(int t) {
        score_sorted_tasks();
        int time=0,count=0;
        StringBuilder ans=new StringBuilder("");
        ArrayList<Integer> tasks_cmpleted[]=new ArrayList[num_mounting_points];
        StringBuilder mounting_point_path[]=new StringBuilder[num_mounting_points];
        for(int i=0;i<mounting_point_path.length;i++) {
            mounting_point_path[i]=new StringBuilder("");
            tasks_cmpleted[i]=new ArrayList<>(); 
        }
        for(int cnt=0;time<=steps && cnt<5;cnt++) {
            for(int i=0;i<num_mounting_points;i++) {
                curr_pos_x[i]=m_points_x[i];
                curr_pos_y[i]=m_points_y[i];
            }
            System.out.println("Cnt#"+cnt);
            fin_vis=new boolean[n][m];
            boolean tmp_fin_vis[][]=new boolean[n][m];
            for(int indx=0;indx<tasks;indx++) {
    //            int i=max_score_task();
                int i=score_sorted_tasks[indx];
                if(task_description[i].get(0)==-1) {
                    continue;
                }
//                System.out.println("Cnt#"+cnt+" TASK#"+i);
                StringBuilder tmp=new StringBuilder("");
                boolean is_pos=true;
                int closest_mount_indx=-1;
                for(int k=0;k<arms;k++) {
                    is_pos=true;
                    int strt_x=curr_pos_x[k],strt_y=curr_pos_y[k];
                    StringBuilder new_tmp=new StringBuilder("");
                    for(int j=1;j<task_description[i].size();j+=2) {
                        int target_x=task_description[i].get(j);
                        int target_y=task_description[i].get(j+1);
                        path=new StringBuilder("");
                        path=BFS(strt_x,strt_y,target_x,target_y);
                        if(path.length()==0) {
                            is_pos=false;
                            break;
                        }
                        new_tmp.append(path);
                        update_vis(strt_x,strt_y,path);
                        strt_x=target_x;
                        strt_y=target_y;
                    }
                    if(is_pos) {
                        if(tmp.length()==0 || new_tmp.length()<tmp.length()) {
                            tmp=new StringBuilder(new_tmp);
                            closest_mount_indx=k;
                        }
                    }
                }
                if(tmp.length()!=0) {
                    if(tmp.length()+mounting_point_path[closest_mount_indx].length()>steps) {
                        task_description[i].set(0, -1);
                    }
                    else if(tmp.length()+mounting_point_path[closest_mount_indx].length()<=steps) {
                        count++;
                        mounting_point_path[closest_mount_indx].append(tmp);
                        tasks_cmpleted[closest_mount_indx].add(i);
                        task_description[i].set(0, -1);
                        curr_pos_x[closest_mount_indx]=task_description[i].get(task_description[i].size()-2);
                        curr_pos_y[closest_mount_indx]=task_description[i].get(task_description[i].size()-1);
                        for(int I=0;I<n;I++) {
                            for(int J=0;J<m;J++) {
                                tmp_fin_vis[I][J]=fin_vis[I][J];
                            }
                        }
                    }
                    for(int I=0;I<n;I++) {
                        for(int J=0;J<m;J++) {
                            fin_vis[I][J]=tmp_fin_vis[I][J];
                        }
                    }
                }
            }
            if(cnt%2==0) {
                time=add_Buffer(mounting_point_path);
            }
            System.out.println(time);
            if(time>steps) {
                break;
            }
        }
        System.out.println("---------------------------PRINTING--------------------------------");
        Write_to_File(count,tasks_cmpleted,mounting_point_path,t);
        
//        for(int i=0;i<tasks;i++) {
//            System.out.println(task_description[i].get(0));
//            if(task_description[i].get(0)==-1) {
//                System.out.println("---------------------------------");
//            }
//        }
    }
    public static int add_Buffer(StringBuilder mounting_point_path[]) {
        int max_len=0;
        for(int i=0;i<mounting_point_path.length;i++) {
            if(mounting_point_path[i].length()==0) {
                continue;
            }
            backtrack(mounting_point_path[i]);
            mounting_point_path[i].append('W');
            max_len=Math.max(max_len,mounting_point_path[i].length());
        }
        for(int i=0;i<mounting_point_path.length;i++) {
            if(mounting_point_path[i].length()==0) {
                continue;
            }
            while(mounting_point_path[i].length()!=max_len) {
                mounting_point_path[i].append('W');
            }
        }
        for(int i=0;i<num_mounting_points;i++) {
            curr_pos_x[i]=m_points_x[i];
            curr_pos_y[i]=m_points_y[i];
        }
        return max_len;
    }
    public static void update_vis(int src_x,int src_y,StringBuilder path) {
        int x=src_x,y=src_y;
        for(int i=0;i<path.length();i++) {
            fin_vis[x][y]=true;
            if(path.charAt(i)=='R') {
                x++;
            }
            if(path.charAt(i)=='L') {
                x--;
            }
            if(path.charAt(i)=='U') {
                y++;
            }
            if(path.charAt(i)=='D') {
                y--;
            }
        }
    }
    public static void Write_to_File(int count,ArrayList<Integer> tasks_cmpleted[],StringBuilder mounting_point_path[],int t) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Java Codes\\My_Codes\\HashCode\\src\\Hash_Code_2020_Finals_Extended_Round\\Files\\output_"+filename[t]+".txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        int arms=0;
        for(int i=0;i<tasks_cmpleted.length;i++) {
            if(tasks_cmpleted[i].size()==0) {
                continue;
            }
            arms++;
        }
        outputstream.println(arms);
        for(int j=0;j<tasks_cmpleted.length;j++) {
            if(tasks_cmpleted[j].size()==0) {
                continue;
            }
            outputstream.println(m_points_x[j]+" "+m_points_y[j]+" "+tasks_cmpleted[j].size()+" "+Math.min(steps,mounting_point_path[j].length()));
            for(int i=0;i<tasks_cmpleted[j].size();i++) {
                outputstream.print(tasks_cmpleted[j].get(i)+" ");
            }
            outputstream.println();
            for(int i=0;i<mounting_point_path[j].length() && i<steps;i++) {
                outputstream.print(mounting_point_path[j].charAt(i)+" ");
            }
            outputstream.println();
        }
        
        outputstream.close();
    }
    public static void backtrack(StringBuilder str) {
        int len=str.length();
        StringBuilder tmp=new StringBuilder("");
        for(int i=len-1;i>=0 && str.charAt(i)!='W';i--) {
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
    
    public static StringBuilder BFS(int src_x,int src_y,int target_i,int target_j) {
//        System.out.println("\tBFS");
        boolean vis[][]=new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                vis[i][j]=fin_vis[i][j];
                if(arr[i][j]==1) {
                    vis[i][j]=true;
                }
            }
        }
        boolean is_pos=false;
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
                is_pos=true;
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
//        System.out.println("\tPATH FOUND");
//        System.out.println(src_x+" "+src_y+" "+target_i+" "+target_j);
        if(!is_pos) {
            return new StringBuilder("");
        }
        StringBuilder path=new StringBuilder("");
        int i=target_i,j=target_j;
        while(!(i==src_x && j==src_y)) {
            if(parent_i[i][j]==i-1) {
                i--;
                path.append('R');
            }
            else if(parent_i[i][j]==i+1) {
                i++;
                path.append('L');
            }
            else if(parent_j[i][j]==j-1) {
                j--;
                path.append('U');
            }
            else if(parent_j[i][j]==j+1) {
                j++;
                path.append('D');
            }
        }
        path.reverse();
        return path;
    }
    public static int max_score_task() {
        int max=-1,indx=-1;
        for(int i=0;i<task_description.length;i++) {
            if(task_description[i].get(0)>max) {
                max=task_description[i].get(0);
                indx=i;
            }
        }
        return indx;
    }
    public static int closest_mount(int indx) {
        int dist=Integer.MAX_VALUE,dist_indx=-1;
        for(int i=0;i<m_points_x.length;i++) {
            int tmp_dist=0;
            for(int j=1;j<task_description[indx].size();j+=2) {
                tmp_dist+=Math.abs(curr_pos_x[i]-task_description[indx].get(j))+Math.abs(curr_pos_y[i]-task_description[indx].get(j+1));
            }
            if(tmp_dist<dist) {
                dist=tmp_dist;
                dist_indx=i;
            }
        }
        return dist_indx;
    }
    public static void score_sorted_tasks() {
        score_sorted_tasks=new int[tasks];
        int scores[]=new int[tasks];
//        int max=0;
//        System.out.println("TASK LENGTH:");
        for(int i=0;i<tasks;i++) {
//            System.out.println(task_description[i].size()-1);
//            max=Math.max(max,task_description[i].size()-1);
            scores[i]=task_description[i].get(0);
            score_sorted_tasks[i]=i;
        }
//        System.out.println("MAX:"+max);
        for(int i=0;i<tasks;i++) {
            for(int j=i;j<tasks;j++) {
                if(scores[i]<scores[j]) {
                    int tmp=scores[i];
                    scores[i]=scores[j];
                    scores[j]=tmp;
                    
                    tmp=score_sorted_tasks[i];
                    score_sorted_tasks[i]=score_sorted_tasks[j];
                    score_sorted_tasks[j]=tmp;
                }
            }
        }
    }
}
