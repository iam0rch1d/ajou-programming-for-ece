#include <stdio.h>

// Macros
#define AVALUE 10
#define BVALUE 20
#define SWAP_MODE 0

// Function prototypes
void performFakeSwap(int, int);
void performRealSwap(int *, int *);

// Main function
int main() {
    int a = AVALUE;
    int b = BVALUE;

    printf("Before swap: a = %d, b = %d\n", a, b);

#if SWAP_MODE == 0
    performFakeSwap(a, b);

#elif SWAP_MODE == 1
    performRealSwap(&a, &b);

#endif

    printf("After swap: a = %d, b = %d\n", a, b);

    return 0;
}

// Functions
/**
 * void performFakeSwap(int, int)
 * Call-by-value function.
 * Does NOT perform swap.
 */
void performFakeSwap(int a, int b) {
    int temp;

    temp = a;
    a = b;
    b = temp;

    printf("Swapped with call-by-value\n");
}

/**
 * void performRealSwap(int, int)
 * Call-by-reference function.
 * Performs swap.
 */
void performRealSwap(int* a, int* b) {
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

    printf("Swapped with call-by-reference\n");
}
