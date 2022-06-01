/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashCode_2021;

/**
 *
 * @author Srest
 */
import java.io.*;
import java.util.*;
public class All_Schedule {
    public static void main(String args[]) throws IOException {
        
        String filename[]=new String[]{"a","b","c","d","e","f"};
        
        for(int tt=0;tt<6;tt++) {
            //Input from file
            Scanner input=new Scanner(new File("C:\\Users\\Srest\\Desktop\\HashCode 2021\\Input\\"+filename[tt]+".txt"));

            int sim_time=input.nextInt();
            int num_of_intersections=input.nextInt();
            int num_of_streets=input.nextInt();
            int num_of_cars=input.nextInt();
            int bonus_points=input.nextInt();

            int street_strt_intersection[]=new int[num_of_streets];
            int street_end_intersection[]=new int[num_of_streets];
            String street_name[]=new String[num_of_streets];
            int time_in_street[]=new int[num_of_streets];

            ArrayList<String> output[]=new ArrayList[num_of_intersections];
            for(int i=0;i<output.length;i++) {
                output[i]=new ArrayList<>();
            }

            for(int i=0;i<num_of_streets;i++) {
                street_strt_intersection[i]=input.nextInt();
                street_end_intersection[i]=input.nextInt();
                street_name[i]=input.next();
                time_in_street[i]=input.nextInt();

                output[street_end_intersection[i]].add(street_name[i]);
            }

            ArrayList<String> cars_path[]=new ArrayList[num_of_cars];


            for(int i=0;i<cars_path.length;i++) {
                cars_path[i]=new ArrayList<>();
                int n=input.nextInt();
                for(int j=0;j<n;j++) {
                    cars_path[i].add(input.next());
                }
            }







            //Output to File

            PrintWriter outputstream=null;
            try {
            outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\Srest\\Desktop\\HashCode 2021\\Outputs\\Output 1\\"+filename[tt]+".txt",true));
            }
            catch(FileNotFoundException e) {
            System.exit(0);
            }

            //Print to file here

            outputstream.println(output.length);
            for(int i=0;i<output.length;i++) {
                outputstream.println(i+"\n"+output[i].size());
                for(int j=0;j<output[i].size();j++) {
                    outputstream.println(output[i].get(j)+" "+5);
                }
            }

            outputstream.close();
        }
        
    }
}
