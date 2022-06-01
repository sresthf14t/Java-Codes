#include "testlib.h"
#include <iostream>

using namespace std;


int main(int argc, char* argv[]) {
    registerValidation(argc, argv);
    int testCount = inf.readInt(1, 10000, "testCount");
    inf.readEoln();

    int sum=0;

    for (int tt = 0; tt < testCount; tt++) {
        int rows = inf.readInt(1, 1000, "Rows");
        inf.readSpace();
        int cols = inf.readInt(1, 1000, "Columns");
        sum+=(rows*cols);
        inf.readEoln();

        for(int i = 0; i < rows; i++) {
                for(int j=0;j< cols;j++) {
                    inf.readInt(1, 100000, "Val");
                    if(j!=cols-1) {
                        inf.readSpace();
                    }
                }
            if (i !=rows-1) {
                inf.readEoln();
            }
        }

        inf.readEoln();

    }

    ensuref(sum<=1000000, "Total Size of Matrix Exceded");

    inf.readEof();
    return 0;
}
