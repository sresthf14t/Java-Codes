#include "testlib.h"
#include <iostream>

using namespace std;

// 50 Test cases of n*m where 1<=n<=100 1<=m<=100

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = atoi(argv[1]);

    int test_cases=50;

    cout<<test_cases<<endl;
    for(int tt=0;tt<test_cases;tt++) {
        int rows=rnd.next(1, 100);
        int cols=rnd.next(1, 100);
        cout<<rows<<" "<<cols<<endl;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                if(j==cols-1) {
                    cout<<rnd.next(1, 100000);
                }
                else {
                    cout<<rnd.next(1, 100000)<<" ";
                }
            }
            if(i!=rows-1) {
                cout<<endl;
            }
        }
        cout<<endl;
    }
}
