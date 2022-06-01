#include "testlib.h"
#include <iostream>

using namespace std;

// 100 Test cases of 100*100

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = atoi(argv[1]);
    cout<<100<<endl;
    for(int tt=0;tt<100;tt++) {
        cout<<100<<" "<<100<<endl;
        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                if(j==99) {
                    cout<<rnd.next(1, 100000);
                }
                else {
                    cout<<rnd.next(1, 100000)<<" ";
                }
            }
            if(i!=99) {
                cout<<endl;
            }
        }
        cout<<endl;
    }
}
