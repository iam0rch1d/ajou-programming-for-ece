#include <stdio.h>

// Macros
#define AVALUE 10
#define BVALUE 20
#define SWAP_MODE 0

// Function prototypes
void runFakeSwap(int, int);
void runRealSwap(int *, int *);

// Main function
int main() {
    int a = AVALUE;
    int b = BVALUE;

    printf("Before swap: a = %d, b = %d\n", a, b);

#if SWAP_MODE == 0
    runFakeSwap(a, b);

#elif SWAP_MODE == 1
    runRealSwap(&a, &b);

#endif

    printf("After swap: a = %d, b = %d\n", a, b);

    return 0;
}

// Functions
/**
 * void runFakeSwap(int, int)
 * Call-by-value function.
 * Does NOT swap.
 */
void runFakeSwap(int a, int b) {
    int temp;

    temp = a;
    a = b;
    b = temp;

    printf("Swapped with call-by-value\n");
}

/**
 * void runRealSwap(int, int)
 * Call-by-reference function.
 * Does swap.
 */
void runRealSwap(int* a, int* b) {
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

    printf("Swapped with call-by-reference\n");
}
