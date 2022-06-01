#include "testlib.h"
#include <iostream>

using namespace std;

// SMALL TESTS 10000 Test cases of n*m where 1<=n<=10 1<=m<=10

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = atoi(argv[1]);

    int test_cases=10000;

    cout<<test_cases<<endl;
    for(int tt=0;tt<test_cases;tt++) {
        int rows=rnd.next(1, 10);
        int cols=rnd.next(1, 10);
        cout<<rows<<" "<<cols<<endl;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                if(j==cols-1) {
                    cout<<rnd.next(1, 1000);
                }
                else {
                    cout<<rnd.next(1, 1000)<<" ";
                }
            }
            if(i!=rows-1) {
                cout<<endl;
            }
        }
        cout<<endl;
    }
}
