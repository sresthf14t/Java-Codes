#include "testlib.h"
#include <iostream>

using namespace std;

// 1 Test case of 1000*1000

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = atoi(argv[1]);

    int test_cases=1;

    cout<<test_cases<<endl;
    for(int tt=0;tt<test_cases;tt++) {
        int rows=1000;
        int cols=1000;
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
