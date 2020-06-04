#include <stdio.h>

// Macros
#define AVALUE 10
#define BVALUE 20
#define SWAP_MODE 0

// Initiate Functions
void fakeSwap(int, int);
void realSwap(int*, int*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    int a = AVALUE;
    int b = BVALUE;

    printf("Before swap: a = %d, b = %d\n", a, b);

#if SWAP_MODE == 0
    fakeSwap(a, b);

#elif SWAP_MODE == 1
    realSwap(&a, &b);

#endif

    printf("After swap: a = %d, b = %d\n", a, b);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// fakeSwap() - Call-by-value, do NOT swap
void fakeSwap(int a, int b) {
    int temp;

    temp = a;
    a = b;
    b = temp;

    printf("Swapped with call-by-value\n");
}

// realSwap() - Call-by-reference, do swap
void realSwap(int* a, int* b) {
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

    printf("Swapped with call-by-reference\n");
}
