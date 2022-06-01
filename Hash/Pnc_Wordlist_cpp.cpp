#include<iostream>
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<math.h>
#include<fstream>

using namespace std;
int main() {
		int n;
		cout<<"Enter the size of the string: ";
		cin>>n;
		int a[n];
		int t=(int)((pow(26,n))/4);
		int c=t/100 ;
		for(int i=0;i<n;i++)
			a[i]=97;
		int temp;
		string s;
		ofstream myfile;
		myfile.open ("abc.txt");
		cout<<"\n\n----------------------------------Creating wordlist-----------------------------------\n\n";
		for(int i=1;i<=t;i++) {
			s="";
			for(int j=0;j<n;j++) {
				s=(char)a[j]+s;
				temp=(int)(pow(26,j));
				if(i%temp==0)
					a[j]++;
				if(a[j]==123)
					a[j]=97;
			}
			myfile<<s<<endl;
			s="";
			if(i%c==0)
				cout<<"=";
		}
		myfile.close();

	}
