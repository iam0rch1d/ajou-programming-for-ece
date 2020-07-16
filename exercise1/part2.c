#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Macros
#define MAX_RANGE 1000
#define FIRST_PRIME 2
#define COMPOSITE 0

enum boolean {
    false = 0,
    true
};

// Main function
int main() {
    int numberArray[MAX_RANGE + 1];
    int range;

    while (1) {
        char rangeString[10];

        printf("Input range. [1-%d]:", MAX_RANGE);
        scanf("%s", rangeString);

        range = strtol(rangeString, NULL, 10);

        if (range < 1 || range > MAX_RANGE) { // Limit range not to exceed number array
            printf("Invalid range. Please input again.\n");
        } else break;
    }

    int i;

    for (i = 0; i <= range; i++) {
        numberArray[i] = i;
    }

    int multiplier;
    int multiple;

    numberArray[1] = COMPOSITE; // Number '1' is not prime number (exception)

    for (i = FIRST_PRIME; i <= sqrt(range); i++) {
        multiplier = i;
        multiple = i;

        if (numberArray[multiplier] == COMPOSITE) { // Skip loops for composite number index
            continue;
        } else {
            while (multiple <= range) {
                multiple += multiplier;
                numberArray[multiple] = COMPOSITE; // Set composite number index element to [COMPOSITE](0)
            }
        }
    }

    printf("Primes in range of %d:\n", range);

    int countPrime = 0;

    for (i = 0; i <= range; i++) {
        if (numberArray[i] != COMPOSITE) {
            countPrime++;

            printf("%d ", numberArray[i]);
        }
    }

    printf("\n");
    printf("Number of primes in range of %d:\n", range);
    printf("%d\n", countPrime);

    return 0;
}
