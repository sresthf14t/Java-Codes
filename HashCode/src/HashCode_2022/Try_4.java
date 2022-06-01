/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashCode_2022;

/**
 *
 * @author SRESTH
 */
import java.io.*;
import java.util.*;
public class Try_4 {
    public static void main(String args[]) throws IOException {
        
        long start2 = System.currentTimeMillis();
        
        //Input from file
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\a_an_example.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\b_better_start_small.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\c_collaboration.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\d_dense_schedule.in.txt"));
        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\e_exceptional_skills.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\f_find_great_mentors.in.txt"));
        
        int n_contributors = input.nextInt();   //no of contributors
        int n_projects = input.nextInt();   ////no of projects
        
        String contributor_name[]=new String[n_contributors];   //name of contributors
        
        ArrayList<String> skills_name[]=new ArrayList[n_contributors];  //name of skills possessed by a contributor
        ArrayList<Integer> skills_level[]=new ArrayList[n_contributors];   // level of skills possessed by a contributor
        
        int power_level[]=new int[n_contributors];  //sum of levels of all skills
        
        for(int i=0;i<n_contributors;i++) {
            contributor_name[i]=input.next();
            int n_skills=input.nextInt();
            skills_name[i]=new ArrayList<>();
            skills_level[i]=new ArrayList<>();
            
            for(int j=0;j<n_skills;j++) {
                skills_name[i].add(input.next());
                skills_level[i].add(input.nextInt());
                power_level[i]+=skills_level[i].get(j);
            }
        }
        
        
        String project_name[]=new String[n_projects];   //name of projects
        int duration_of_project[]=new int[n_projects];  //duration_of_project
        int score_of_project[]=new int[n_projects];     //score_of_project
        int deadline_of_project[]=new int[n_projects];  //deadline_of_project
        int n_roles_for_project[]=new int[n_projects];  //no_of_roles_reqiured_for_project
        
        int avrage_duration_of_projects = 0;
        
        ArrayList<String> skills_name_required_for_project[]=new ArrayList[n_projects];  //skills_name_required_for_project
        ArrayList<Integer> skills_level_required_for_project[]=new ArrayList[n_projects];   // skills_level_required_for_project
        
        for(int i=0;i<n_projects;i++) {
            project_name[i]=input.next();
            duration_of_project[i]=input.nextInt();
            score_of_project[i]=input.nextInt();
            deadline_of_project[i]=input.nextInt();
            n_roles_for_project[i]=input.nextInt();
            
            avrage_duration_of_projects += duration_of_project[i];
            
            skills_name_required_for_project[i]=new ArrayList<>();
            skills_level_required_for_project[i]=new ArrayList<>();
            
            for(int j=0;j<n_roles_for_project[i];j++) {
                skills_name_required_for_project[i].add(input.next());
                skills_level_required_for_project[i].add(input.nextInt());
            }
            
        }
        
        avrage_duration_of_projects/=n_projects;
        avrage_duration_of_projects++;
        
        
        int n_days_schedule=200000;  // no of days the office will run
        int contributor_busy_till[]=new int[n_contributors]; //contributor is busy till which date
        Arrays.fill(contributor_busy_till, -1);
        
        boolean project_done[]=new boolean[n_projects]; //if the project has been done
        Arrays.fill(project_done, false);
        
        int no_of_projects_done=0;  //No of projects completed
        
        StringBuilder project_contributor_list = new StringBuilder();   ///final output list
        
        int start_index_to_search_for_project = 0;  // start index to search for project in case of faliure to complete project
        HashSet<Integer> projects_on_standby = new HashSet<>();
        
        
        for(int i=0;i<n_days_schedule;i++) {
            
//            if(i%avrage_duration_of_projects == 0) {
//                projects_on_standby = new HashSet<>();
//            }
            
            long end2 = System.currentTimeMillis();
            
            long runtime = (end2-start2)/1000;
            
//            if(runtime>30) {
//                break;
//            }
//            
            
            int index_of_pending_project = index_of_pending_project(project_done, start_index_to_search_for_project, 0, duration_of_project, deadline_of_project, score_of_project, projects_on_standby);
            if(index_of_pending_project == -1) {
                continue;
            }
            
            boolean project_completed = true;
            
            ArrayList<Integer> index_of_contributors = new ArrayList<>();   //index of contributors for the current project
            
            for(int j=0;j<skills_name_required_for_project[index_of_pending_project].size();j++) {
                
                int contributor_index = index_of_free_contributor_with_reqiurnments(skills_name_required_for_project[index_of_pending_project].get(j), skills_level_required_for_project[index_of_pending_project].get(j), n_contributors, skills_name, skills_level, contributor_busy_till, power_level, i, index_of_contributors);
                
                if(contributor_index == -1) {
                    project_completed=false;
                    break;
                }
                
                index_of_contributors.add(contributor_index);
                
            }
            
//            System.out.println("Day = "+i);
//            System.out.println("Project index = "+index_of_pending_project);
//            System.out.println("Project Completed = "+project_completed);
            
            if(!project_completed) {
                start_index_to_search_for_project++;
                projects_on_standby.add(index_of_pending_project);
                continue;
            }
            start_index_to_search_for_project = 0;
            projects_on_standby = new HashSet<>();
            
            
            no_of_projects_done++;
            project_done[index_of_pending_project] = true;
            project_contributor_list.append(project_name[index_of_pending_project]+"\n");
            for(int j=0;j<index_of_contributors.size();j++) {
                project_contributor_list.append(contributor_name[index_of_contributors.get(j)]+" ");
                contributor_busy_till[index_of_contributors.get(j)] = i+duration_of_project[index_of_pending_project]-1;
                
                if(skills_level[index_of_contributors.get(j)].get(skills_name[index_of_contributors.get(j)].indexOf(skills_name_required_for_project[index_of_pending_project].get(j))) == skills_level_required_for_project[index_of_pending_project].get(j)) {
                    
                    skills_level[index_of_contributors.get(j)].set(skills_name[index_of_contributors.get(j)].indexOf(skills_name_required_for_project[index_of_pending_project].get(j)), skills_level_required_for_project[index_of_pending_project].get(j) + 1);
                    
                }
            }
            project_contributor_list.append("\n");
        }
        
        
        
     
        //Output to File
        
        PrintWriter outputstream=null;
        try {
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\a_an_example.in.txt",false));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\b_better_start_small.in.txt",false));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\c_collaboration.in.txt",false));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\d_dense_schedule.in.txt",false));
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\e_exceptional_skills.in.txt",false));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\f_find_great_mentors.in.txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }

        //Print to file here
        
        outputstream.append(no_of_projects_done+"\n");
        outputstream.append(project_contributor_list);
        
        outputstream.close();
        
        System.out.println("% = "+ (double)(no_of_projects_done)*100/(n_projects));
        
    }
    
    //index_of_pending_projec
    
    public static int index_of_pending_project(boolean project_done[], int start_index, int date, int duration_of_project[], int deadline_of_project[], int score_of_project[], HashSet<Integer> projects_on_standby) {
        
        int max_score_possible=0;
        int index=-1;
        
        for(int i=start_index;i<project_done.length;i++) {
            
            if(project_done[i]) {
                continue;
            }
            
            if(projects_on_standby.contains(i)) {
                continue;
            }
            
            int project_score=-1;
            
            if( (date+duration_of_project[i]-1) >=deadline_of_project[i]) {
                int extra_days_required = (date+duration_of_project[i]-1) -deadline_of_project[i];
                if(score_of_project[i]-extra_days_required <= 0) {
                    project_score = score_of_project[i]-extra_days_required;
                    continue;
                }
            }
            else {
                project_score = score_of_project[i];
            }
            
            if(project_score > max_score_possible) {
                max_score_possible = project_score;
                index=i;
            }
            
        }
        return index;
    }
    
    //index of free contributor with the required skills
    
    public static int index_of_free_contributor_with_reqiurnments(String required_skill, int required_skill_level, int n_contributors, ArrayList<String> skills_name[], ArrayList<Integer> skills_level[], int contributor_busy_till[], int power_level[], int date, ArrayList<Integer> index_of_contributors) {
        
        int curr_power = 99999999;
        int contributor_index = -1;

        for(int i=0;i<n_contributors;i++) {
            
            if(contributor_busy_till[i]>=date) {
                continue;
            }
            
            if(index_of_contributors.contains(i)) {
                continue;
            }
            
            if(skills_name[i].contains(required_skill) && skills_level[i].get(skills_name[i].indexOf(required_skill)) >= required_skill_level) {
                
                if(power_level[i] < curr_power) {
                    curr_power = power_level[i];
                    contributor_index = i;
                }
                
            }
        }
        
        return contributor_index;
        
    }
}
