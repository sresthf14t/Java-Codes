/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adesh;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Simplified_DES {
     int left[];
    int right[];
    int key1[];
    int key2[];
    void leftshift(int left[],int right[],int bits){
        for(int j=0;j<bits;j++){
            int templeft=left[0];
            int tempright=right[0];
            for(int i=0;i<left.length;i++){
                if(i==left.length-1){
                    this.left[i]=templeft;
                    this.right[i]=tempright;
                }
                else{
                    this.left[i]=left[(i+1)];
                    this.right[i]=right[(i+1)];
                }
            }
        }
    }
    int[] StraightPbox(int Spbox[],int key[]){
        int temp[]=new int[key.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=key[i];
        }
        for(int i=0;i<Spbox.length;i++){
            key[i]=temp[Spbox[i]-1];
        }
        return key;
    }
    int[] Expansionpbox(int Epbox[],int key1[]){
        int temp[]=new int[Epbox.length];
        for(int i=0;i<Epbox.length;i++){
            temp[i]=key1[Epbox[i]-1];
        }
        return temp;
    }
    int[] XORfunc(int input1[],int input2[]){
        int res[]=new int[input1.length];
        for(int i=0;i<input1.length;i++){
            if((input1[i]==0 && input2[i]==0) || (input1[i]==1 && input2[i]==1)){
                res[i]=0;
            }
            if((input1[i]==1 && input2[i]==0) || (input1[i]==0 && input2[i]==1)){
                res[i]=1;
            }
        }
        return res;
    }
    int[] compr_pbox(int Cpbox[],int input[]){
        int temp[]=new int[Cpbox.length];
        for(int i=0;i<Cpbox.length;i++){
            temp[i]=input[Cpbox[i]-1];
        }
        return temp;

    }
    int[] swapping(int input1[],int input2[]){
        int input3[]=new int[input1.length+input2.length];
        for(int i=0;i<input3.length;i++){
            if(i<4){
                input3[i]=input2[i];
            }
            if(i>=4){
                input3[i]=input1[i-4];
            }
        }
        return input3;

    }
    int binary(int a,int b){
        int dec=b*1;
        dec=a*2+dec;
        return dec;
    }
    int[] decimal(int num){
        int temp[]=new int[2];
        for(int i=1;i>=0;i--){
            temp[i]=num%2;
            num=num/2;
        }
        return temp;
    }
    int[] key_generation(int key[],int R_num){
        int p10[]={3,5,2,7,4,10,1,9,8,6};
        int p8[]={6,3,7,4,8,5,10,9};
        if(R_num==1){
            key=StraightPbox(p10,key);
            left=new int[(key.length)/2];
            right=new int[(key.length)/2];
            for(int i=0;i<left.length;i++){
                left[i]=key[i];
            }
            for(int i=0;i<right.length;i++){
                right[i]=key[i+5];
            }
            leftshift(left,right,1);
        }
        if(R_num==2){
            leftshift(left,right,2);
        }
        for(int i=0;i<key.length;i++){
            if(i<=4){
                key[i]=left[i];
            }
            if(i>4){
                key[i]=right[i-5];
            }
        }
        int key1[]=compr_pbox(p8,key);
        return key1;
    }
    int[] round(int plaintext[],int key1[],int R_num){
        int IP[]={2,6,3,1,4,8,5,7};
        int IP_inv[]={4,1,3,5,7,2,8,6};
        int EP[]={4,1,2,3,2,3,4,1};
        int p4[]={2,4,3,1};
        int s0[][]={{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,0,2}};
        int s1[][]={{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};
        if(R_num==1){
            plaintext=StraightPbox(IP,plaintext);
        }
        left=new int[(plaintext.length)/2];
        right=new int[(plaintext.length)/2];
        for(int i=0;i<left.length;i++){
            left[i]=plaintext[i];
        }
        for(int i=0;i<right.length;i++){
            right[i]=plaintext[i+4];
        }
        plaintext=Expansionpbox(EP,right);
        plaintext=XORfunc(plaintext,key1);
        int i=binary(plaintext[0],plaintext[3]);
        int j=binary(plaintext[1],plaintext[2]);
        int k=binary(plaintext[4],plaintext[7]);
        int l=binary(plaintext[5],plaintext[6]);
        int m=s0[i][j];
        int n=s1[k][l];
        int temp1[]=decimal(m);
        int temp2[]=decimal(n);
        int temp3[]=new int[4];
        for(int t=0;t<temp3.length;t++){
            if(t<=1){
                temp3[t]=temp1[t];
            }
            if(t>1){
                temp3[t]=temp2[t-2];
            }
        }
        temp3=StraightPbox(p4,temp3);
        left=XORfunc(left,temp3);
        if(R_num==1){
            plaintext=swapping(left,right);
        }
        if(R_num==2){
            for(int t=0;t<plaintext.length;t++){
                if(t<4){
                    plaintext[t]=left[t];
                }
                if(t>=4){
                    plaintext[t]=right[t-4];
                }
            }
            plaintext=StraightPbox(IP_inv,plaintext);
        }
        return plaintext;

    }
    public int[] Encryption(int plaintext[],int key[]){
        key1=key_generation(key,1);
      /*  System.out.println("Key1");
        for(int i=0;i<key1.length;i++){
            System.out.print(key1[i]+" ");
        }
        System.out.println();
        System.out.println("Key2");*/
        key2=key_generation(key,2);
        /*for(int i=0;i<key2.length;i++){
            System.out.print(key2[i]+" ");
        }
        System.out.println();*/
        int plaintext1[]=round(plaintext,key1,1);
        /*System.out.println("plaintext after round 1");
        for(int i=0;i<plaintext1.length;i++){
            System.out.print(plaintext1[i]);
        }
        System.out.println();*/
        int ciphertext[]=round(plaintext1,key2,2);
        //System.out.println("The final ciphertext");
        //for(int i=0;i<ciphertext.length;i++){
          //  System.out.print(ciphertext[i]+" ");
        //}
       // System.out.println();
        return ciphertext;

    }
    public int[] Decryption(int ciphertext[],int key[]){
        int Decryption1[]=round(ciphertext,key2,1);
        int Decryption2[]=round(Decryption1,key1,2);
        //System.out.println("The plaintext after decryption");
        //for(int i=0;i<Decryption2.length;i++){
          //  System.out.print(Decryption2[i]);
        //}
        return Decryption2;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Simplified_DES d=new Simplified_DES();
        int plaintext[]=new int[8];
        int key[]=new int[10];
        System.out.println("Enter the plaintext");
        for(int i=0;i<plaintext.length;i++){
            plaintext[i]=sc.nextInt();
        }
       System.out.println("Enter the key");
        for(int i=0;i<key.length;i++){
            key[i]=sc.nextInt();
        }
        
        int ciphertext[]=d.Encryption(plaintext,key);
        int ptext[]=d.Decryption(ciphertext,key);
        System.out.println();
        for(int i=0;i<ciphertext.length;i++) {
            System.out.print(ciphertext[i]);
        }
        System.out.println();
        for(int i=0;i<ptext.length;i++) {
            System.out.print(ptext[i]);
        }

    }

}
