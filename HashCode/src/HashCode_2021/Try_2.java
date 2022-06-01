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
public class Try_2 {
    public static void main(String args[]) throws IOException {
        
        String filename[]=new String[]{"a","b","c","d","e","f"};
        
        for(int tt=0;tt<6;tt++) {
            System.out.println(tt);
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
            HashMap<String,Integer> street_name_map=new HashMap<>();

            for(int i=0;i<num_of_streets;i++) {
                street_strt_intersection[i]=input.nextInt();
                street_end_intersection[i]=input.nextInt();
                street_name[i]=input.next();
                time_in_street[i]=input.nextInt();

                street_name_map.put(street_name[i], i);
            }

            ArrayList<String> cars_path[]=new ArrayList[num_of_cars];

            int num_of_cars_in_street[]=new int[num_of_streets];

            for(int i=0;i<cars_path.length;i++) {
                cars_path[i]=new ArrayList<>();
                int n=input.nextInt();
                for(int j=0;j<n;j++) {
                    String tmp=input.next();
                    cars_path[i].add(tmp);
                    num_of_cars_in_street[street_name_map.get(tmp)]++;
                }
            }





            //Output to File

            PrintWriter outputstream=null;
            try {
            outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\Srest\\Desktop\\HashCode 2021\\Outputs\\Output 2\\"+filename[tt]+".txt",false));
            }
            catch(FileNotFoundException e) {
            System.exit(0);
            }

            //Print to file here

            StringBuilder ans=new StringBuilder("");

            int total_intersection=0;

            for(int i=0;i<num_of_intersections;i++) {
                int cnt=0;
                double total=0;
                for(int j=0;j<num_of_streets;j++) {
                    if(street_end_intersection[j]!=i) {
                        continue;
                    }
                    total+=(double)num_of_cars_in_street[j]/(double)time_in_street[j];
                    if(num_of_cars_in_street[j]>1) {
                        cnt++;
                    }
                }
                if(cnt==0) {
                    continue;
                }
                total_intersection++;
                ans.append(i+"\n");
                ans.append(cnt+"\n");
                int total_time=(int)((float)1.125*cnt);
                for(int j=0;j<num_of_streets;j++) {
                    if(street_end_intersection[j]!=i) {
                        continue;
                    }
                    if(num_of_cars_in_street[j]<2) {
                        continue;
                    }
                    int time=(int)((double)total_time*(((double)num_of_cars_in_street[j]/(double)time_in_street[j]))/total);
                    time=Math.max(time, 1);
                    ans.append(street_name[j]+" "+time+"\n");
                }
            }

            outputstream.println(total_intersection);
            outputstream.print(ans);
            outputstream.close();
        }
        
        
    }
}
