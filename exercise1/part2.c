#include <stdio.h>
#include <math.h>

// Macros
#define MAX_RANGE 1000
#define FIRST_PRIME 2
#define COMPOSITE 0

// Global variables
int range;
int arrNumTable[MAX_RANGE + 1]; // Each index of number table array corresponds to the number
                                // ('arrNumTable[0]' is not used)

// Function prototypes
int setRange(void);
void getArrNumTable(void);
void sift(void);
void printAllPrimesInRange(void);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    range = setRange();
    getArrNumTable();
    sift();
    printAllPrimesInRange();

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// setRange() - Scan range from user
int setRange(void) {
    while (1) {
        printf("Input range. [1-%d]:", MAX_RANGE);
        scanf("%d", &range);

        if (range < 1 || range > MAX_RANGE) { // Limit range not to exceed number table array
            printf("Invalid range. Please input again.\n");
        }
        else {
            return range;
        }
    }
}

// getArrNumTable() - Make sifting number table
void getArrNumTable(void) {
    int i;

    for (i = 0; i <= range; i++) {
        arrNumTable[i] = i;
    }
}

// sift() - Sift composite numbers in number table
void sift(void) {
    int i;
    int multiplier;
    int multiple;

    arrNumTable[1] = COMPOSITE; // Number '1' is not prime number (exception)

    for (i = FIRST_PRIME; i <= sqrt(range); i++) {
        multiplier = i;
        multiple = i;

        if (arrNumTable[multiplier] == COMPOSITE) { // Skip loop for composite number index
            continue;
        }
        else {
            while (multiple <= range) {
                multiple += multiplier;
                arrNumTable[multiple] = COMPOSITE; // Set composite number index element to 'COMPOSITE'(0)
            }
        }
    }
}

// printAllPrimesInRange() - Print all prime numbers in range, print number of primes in range
void printAllPrimesInRange(void) {
    int countPrime = 0;
    int i;

    printf("Primes in range of %d:\n", range);

    for (i = 0; i <= range; i++) {
        if (arrNumTable[i] != COMPOSITE) {
            countPrime++;

            printf("%d ", arrNumTable[i]);
        }
    }

    printf("\n");
    printf("Number of primes in range of %d:\n", range);
    printf("%d\n", countPrime);
}
