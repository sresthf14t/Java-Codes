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
public class Try_1 {
    public static void main(String args[]) throws IOException {
        
        //Input from file
        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\a_an_example.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\b_better_start_small.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\c_collaboration.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\d_dense_schedule.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\e_exceptional_skills.in.txt"));
//        Scanner input=new Scanner(new File("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\input_data\\f_find_great_mentors.in.txt"))
        
        int n_contributors = input.nextInt();   //no of contributors
        int n_projects = input.nextInt();   ////no of projects
        
        String contributir_name[]=new String[n_contributors];   //name of contributors
        
        ArrayList<String> skills_name[]=new ArrayList[n_contributors];  //name of skills possessed by a contributor
        ArrayList<Integer> skills_level[]=new ArrayList[n_contributors];   // level of skills possessed by a contributor
        for(int i=0;i<n_contributors;i++) {
            contributir_name[i]=input.next();
            int n_skills=input.nextInt();
            skills_name[i]=new ArrayList<>();
            skills_level[i]=new ArrayList<>();
            for(int j=0;j<n_skills;j++) {
                skills_name[i].add(input.next());
                skills_level[i].add(input.nextInt());
            }
        }
        
        
        String project_name[]=new String[n_projects];   //name of projects
        int duration_of_project[]=new int[n_projects];  //duration_of_project
        int score_of_project[]=new int[n_projects];     //score_of_project
        int deadline_of_project[]=new int[n_projects];  //deadline_of_project
        int n_roles_for_project[]=new int[n_projects];  //no_of_roles_reqiured_for_project
        
        ArrayList<String> skills_name_required_for_project[]=new ArrayList[n_contributors];  //skills_name_required_for_project
        ArrayList<Integer> skills_level_required_for_project[]=new ArrayList[n_contributors];   // skills_level_required_for_project
        
        for(int i=0;i<n_projects;i++) {
            project_name[i]=input.next();
            duration_of_project[i]=input.nextInt();
            score_of_project[i]=input.nextInt();
            deadline_of_project[i]=input.nextInt();
            n_roles_for_project[i]=input.nextInt();
            
            skills_name_required_for_project[i]=new ArrayList<>();
            skills_level_required_for_project[i]=new ArrayList<>();
            
            for(int j=0;j<n_roles_for_project[i];j++) {
                skills_name_required_for_project[i].add(input.next());
                skills_level_required_for_project[i].add(input.nextInt());
            }
            
        }
        
        
        
     
        //Output to File
        
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\a_an_example.in.txt",true));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\b_better_start_small.in.txt",true));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\c_collaboration.in.txt",true));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\d_dense_schedule.in.txt",true));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\e_exceptional_skills.in.txt",true));
//        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\SRESTH\\Desktop\\HashCode\\HashCode 2022\\Output\\f_find_great_mentors.in.txt",true));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }

        //Print to file here
        
        outputstream.close();
    }
}
