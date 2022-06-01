/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_792_Div_1_Div_2;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class E {
    static class Scan {
        private byte[] buf=new byte[1024];
        private int index;
        private InputStream in;
        private int total;
        public Scan()
        {
            in=System.in;
        }
        public int scan()throws IOException
        {
            if(total<0)
            throw new InputMismatchException();
            if(index>=total)
            {
                index=0;
                total=in.read(buf);
                if(total<=0)
                return -1;
            }
            return buf[index++];
        }
        public int scanInt()throws IOException
        {
            int integer=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    integer*=10;
                    integer+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            return neg*integer;
        }
        public double scanDouble()throws IOException
        {
            double doub=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n)&&n!='.')
            {
                if(n>='0'&&n<='9')
                {
                    doub*=10;
                    doub+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            if(n=='.')
            {
                n=scan();
                double temp=1;
                while(!isWhiteSpace(n))
                {
                    if(n>='0'&&n<='9')
                    {
                        temp/=10;
                        doub+=(n-'0')*temp;
                        n=scan();
                    }
                    else throw new InputMismatchException();
                }
            }
            return doub*neg;
        }
        public String scanString()throws IOException
        {
            StringBuilder sb=new StringBuilder();
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            while(!isWhiteSpace(n))
            {
                sb.append((char)n);
                n=scan();
            }
            return sb.toString();
        }
        private boolean isWhiteSpace(int n)
        {
            if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
            return true;
            return false;
        }
    }
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    
    static class seg_tree {
        int seg_tree[], seg_indx[],seg_tree_sub[];
        seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            seg_tree_sub=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
            create_seg_indx(arr,0,0,n-1);
            create_seg_tree_sub(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }

        public int sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int total=0;

            //Left Child
            total+=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            total+=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            return total;
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_tree[vertex]=value;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update((2*vertex)+1,l,mid,pos,value);
            }
            //Right Child
            else {
                update((2*vertex)+2,mid+1,r,pos,value);
            }
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }
        
  
        
        public void create_seg_indx(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_indx[vertex]=arr[r]>0?r:-1;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_indx(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_indx(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_indx[vertex]=seg_indx[(2*vertex)+2]>0?seg_indx[(2*vertex)+2]:seg_indx[(2*vertex)+1];
        }
        
        public void update_indx(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_indx[vertex]=value>0?r:-1;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update_indx((2*vertex)+1,l,mid,pos,value);
            }
            //Right Child
            else {
                update_indx((2*vertex)+2,mid+1,r,pos,value);
            }
            seg_indx[vertex]=seg_indx[(2*vertex)+2]>0?seg_indx[(2*vertex)+2]:seg_indx[(2*vertex)+1];
        }
        
        public int find_indx(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_indx[vertex];
            }
            int mid=(l+r)/2;
            
            int ll=find_indx((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
            int rr=find_indx((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            return rr>0?rr:ll;
        }
        
        
        
        public void create_seg_tree_sub(int arr[],int vertex,int l,int r) {
            if(l==r) {
                if(r!=0) {
                    seg_tree_sub[vertex]=arr[r]/r;
                }
                else {
                    seg_tree_sub[vertex]=0;
                }
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree_sub(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree_sub(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree_sub[vertex]=seg_tree_sub[(2*vertex)+1]+seg_tree_sub[(2*vertex)+2];
        }
        
        public int sum_sub(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_tree_sub[vertex];
            }
            int mid=(l+r)/2;

            int total=0;

            //Left Child
            total+=sum_sub((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            total+=sum_sub((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            return total;
        }
        
        public void update_sub(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                if(r!=0) {
                    seg_tree_sub[vertex]=value/r;
                }
                else {
                    seg_tree_sub[vertex]=0;
                }
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update_sub((2*vertex)+1,l,mid,pos,value);
            }
            //Right Child
            else {
                update_sub((2*vertex)+2,mid+1,r,pos,value);
            }
            seg_tree_sub[vertex]=seg_tree_sub[(2*vertex)+1]+seg_tree_sub[(2*vertex)+2];
        }
    }

    
    
    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int k=input.scanInt();
//            int n=7;
//            int k=(int)(Math.random()*10);
            int tk=k;
            int arr[]=new int[n];
            int t_arr[]=new int[n];
            HashMap<Integer,Integer> map=new HashMap<>();
            HashMap<Integer,Integer> track=new HashMap<>();
            HashMap<Integer,Integer> chnged=new HashMap<>();
            ArrayList<Integer> arrli=new ArrayList<>();
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*10);
                t_arr[i]=arr[i];
                arrli.add(arr[i]);
                if(!map.containsKey(arr[i])) {
                    map.put(arr[i], 0);
                }
                map.replace(arr[i], map.get(arr[i])+1);
                
                if(!track.containsKey(arr[i])) {
                    track.put(arr[i], 0);
                }
                track.replace(arr[i], track.get(arr[i])+1);
            }
            sortBasedOnFrequencyAndValue(arr,arrli);
            for(int i=0;i<n;i++) {
                arr[i]=arrli.get(n-i-1);
//                System.out.print(arr[i]+" ");
            }
            
            int freq[]=new int[n+5];
            for(int i:map.keySet()) {
                freq[map.get(i)]+=map.get(i);
            }
//            for(int i=1;i<freq.length;i++) {
//                System.out.print(freq[i]+" ");
//            }
//            System.out.println();
            seg_tree st=new seg_tree(freq.length,freq);
            
            Queue<Integer> que=new LinkedList<>();
            int min=Integer.MAX_VALUE;
            for(int i=0,indx=0;i<n;i++) {
                while(indx<n && !map.containsKey(arr[indx])) {
                    indx++;
                }
                boolean done=false;
                if(map.containsKey(i)) {
                    st.update(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)) - map.get(i));
                    st.update_indx(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)));
                    st.update_sub(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)));
                    map.replace(i, map.get(i)-1);
//                    st.update(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)) + map.get(i));
                    st.update_indx(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)+ map.get(i)));
//                    st.update_sub(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)));
                    if(map.get(i)==0) {
                        map.remove(i);
                    }
                    done=true;
                }
                else if(chnged.containsKey(i)) {
                    
                    while(indx<n && arr[indx]<indx) {
                        if(map.containsKey(arr[indx])) {
                            map.remove(arr[indx]);
                        }
                        que.add(arr[indx]);
                        indx++;
                    }
                    
                    if(indx==n && !que.isEmpty()) {
                        indx--;
                        arr[indx]=que.poll();
                        map.put(arr[indx], 1);
                    }
                    
                    
                    
                    st.update(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])) - map.get(arr[indx]));
                    st.update_indx(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    st.update_sub(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    map.replace(arr[indx], map.get(arr[indx])-1);
//                    st.update(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])) + map.get(arr[indx]));
                    st.update_indx(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx]) + map.get(arr[indx])));
//                    st.update_sub(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    if(map.get(arr[indx])==0) {
                        map.remove(arr[indx]);
                    }
                    track.replace(arr[indx], track.get(arr[indx])-1);
                    if(track.get(arr[indx])==0) {
                        track.remove(arr[indx]);
                    }
                    chnged.put(arr[indx], 0);
                    
                    
                    arr[indx]=i;
                    if(!track.containsKey(arr[indx])) {
                        track.put(arr[indx], 0);
                    }
                    track.replace(arr[indx], track.get(arr[indx])+1);
                    
                    
                    indx++;
//                    map.put(i, 0);
//                    map.replace(i, map.get(i)+1);
//                    st.update(0, 0, freq.length-1, map.get(i), st.sum(0, 0, freq.length-1, map.get(i), map.get(i)) + map.get(i));
//                    k--;
                    done=true;
                }
                else if(k>0){
                    
                    while(indx<n && arr[indx]<indx) {
                        if(map.containsKey(arr[indx])) {
                            map.remove(arr[indx]);
                        }
                        que.add(arr[indx]);
                        indx++;
                    }
                    
                    if(indx==n && !que.isEmpty()) {
                        indx--;
                        arr[indx]=que.poll();
                        map.put(arr[indx], 1);
                    }
                    
//                    System.out.println(indx+" "+arr[indx]+" "+map.containsKey(arr[indx]));
                    st.update(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])) - map.get(arr[indx]));
                    st.update_indx(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    st.update_sub(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    map.replace(arr[indx], map.get(arr[indx])-1);
//                    st.update(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])) + map.get(arr[indx]));
                    st.update_indx(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx]) + map.get(arr[indx])));
//                    st.update_sub(0, 0, freq.length-1, map.get(arr[indx]), st.sum(0, 0, freq.length-1, map.get(arr[indx]), map.get(arr[indx])));
                    if(map.get(arr[indx])==0) {
                        map.remove(arr[indx]);
                    }
                    track.replace(arr[indx], track.get(arr[indx])-1);
                    if(track.get(arr[indx])==0) {
                        track.remove(arr[indx]);
                    }
                    chnged.put(arr[indx], 0);
                    
                    
                    arr[indx]=i;
                    if(!track.containsKey(arr[indx])) {
                        track.put(arr[indx], 0);
                    }
                    track.replace(arr[indx], track.get(arr[indx])+1);
                    
                    
                    indx++;
                    k--;
                    done=true;
                }
                if(!done) {
                    break;
                }
                int diff=track.size();
                int sub=0;
                if(!track.containsKey(0)) {
                    sub=can_be_updated(0,st.find_indx(0, 0, freq.length-1, 0, freq.length-1)-1,k,st,freq);
                }
                else {
                    sub=can_be_updated(0,freq.length-1,k,st,freq);
                }
                System.out.println(i+" "+k+" "+indx+" "+diff+" "+sub+" "+track+" "+map+" "+(diff-sub-(i+1)));
                diff-=sub;
                min=Math.min(min,diff-(i+1));
            }
            if(min==Integer.MAX_VALUE) {
                min=Math.min(min, get_val(arr));
            }
            int val=solve(t_arr,0,tk);
            if(val!=min) {
                System.out.println("\n");
                System.out.println(val+" "+min);
                System.out.println("k="+tk);
                for(int i=0;i<n;i++) {
                    System.out.print(t_arr[i]+" ");
                }
                System.out.println("\n");
            }
            ans.append(min+"\n");
        }
        System.out.print(ans);
    }
    
    public static int can_be_updated(int l,int r,int k,seg_tree st,int freq[]) {
        int last=r;
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            int sum=st.sum(0, 0, last, 0, mid);
            if(sum<=k) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        if(ans==-1) {
            return 0;
        }
        
        k-=st.sum(0, 0, freq.length-1, 0, ans);
//        for(int i=0;i<=last;i++) {
//            System.out.print(st.sum_sub(0, 0, freq.length-1, 0, ans)+" ");
//        }
//        System.out.println();
//        for(int i=0;i<=last;i++) {
//            System.out.print(st.sum(0, 0, freq.length-1, 0, ans)+" ");
//        }
//        System.out.println();
        return st.sum_sub(0, 0, freq.length-1, 0, ans);
    }
    
    
    
    public static void sortBasedOnFrequencyAndValue(int arr[], ArrayList<Integer> list)
    {
        int n = arr.length;
        final HashMap<Integer, Integer> mapCount
            = new HashMap<Integer, Integer>();
        final HashMap<Integer, Integer> mapIndex
            = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (mapCount.containsKey(arr[i])) {
                mapCount.put(arr[i],
                             mapCount.get(arr[i]) + 1);
            }
            else {
                mapCount.put(arr[i],1); // Map to capture Count of elements
                mapIndex.put(arr[i],i); // Map to capture 1st occurrence of elements
            }
        }
 
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2)
            {
                int freq1 = mapCount.get(n1);
                int freq2 = mapCount.get(n2);
                if (freq1 != freq2) {
                    return freq2 - freq1;
                }
                else {
                    return n2 - n1;
                }
            }
        });
        
//        System.out.println(list);
    }
    
    
    
    public static int solve(int arr[],int indx,int k) {
        if(indx==arr.length || k==0) {
            return get_val(arr);
        }
        
        int min=Integer.MAX_VALUE/2;
        int val=arr[indx];
        min=Math.min(min,solve(arr,indx+1,k));
        for(int i=0;i<=10;i++) {
            arr[indx]=i;
            min=Math.min(min,solve(arr,indx+1,k-1));
        }
        arr[indx]=val;
        return min;
    }
    
    public static int get_val(int arr[]) {
        HashSet<Integer> hs=new HashSet<>();
        for(int i=0;i<arr.length;i++) {
            hs.add(arr[i]);
        }
        int mex=0;
        for(int i=0;!hs.isEmpty();i++) {
            if(!hs.contains(i)) {
                break;
            }
            mex++;
        }
        return hs.size()-mex;
    }
}
