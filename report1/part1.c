#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Macros
#define NUM_PRIME 100 // Number of prime numbers
#define FIRST_PRIME 2 // First prime number(= 2)

enum boolean {
    false = 0,
    true
};

// Function prototypes
void initializeDatabase(int *, int *);
void runMainLoop(int *);
void printIsNumberPrime(int *, int);
void printPrimes(int *);
int isDynamicPrime(const int *, int);
int isStaticPrime(int);

// Main function
int main() {
    int memoPrime[NUM_PRIME] = { FIRST_PRIME };
    int countPrime = 1;

    initializeDatabase(memoPrime, &countPrime);
    printf("+--------------------------------------------------------------+\n");
    printf("|                     Prime Number Finder                      |\n");
    printf("+--------------------------------------------------------------+\n");
    printf("|   <natural number> : Check if the number is a prime number   |\n");
    printf("|               '-1' : Show all prime number database          |\n");
    printf("|                '0' : Exit                                    |\n");
    printf("+--------------------------------------------------------------+\n");
    runMainLoop(memoPrime);

    return 0;
}

// Functions
/**
 * void initializeDatabase()
 * Setups memoization database of prime numbers.
 */
void initializeDatabase(int *memoPrime, int *countPrime) {
    int i = FIRST_PRIME;

    while (*countPrime <= NUM_PRIME) {
        i++;

        if (isDynamicPrime(memoPrime, i)) {
            memoPrime[*countPrime] = i;
            (*countPrime)++;
        }
    }
}

/**
 * void runMainLoop()
 * Runs main loop.
 */
void runMainLoop(int *memoPrime) {
    setbuf(stdout, 0);

    char numberString[10];

    while (true) {
        printf("Input a number:");
        scanf("%s", numberString);

        int number = strtol(numberString, NULL, 10);

        if (number == 0) break;
        else if (number == -1) printPrimes(memoPrime);
        else if (number >= 1) printIsNumberPrime(memoPrime, number);
        else printf("Invalid number.\n");

        printf("--------------------------------------------------------------\n");
    }
}

/**
 * void printIsNumberPrime(int)
 * Function called when user inputs natural number.
 * Checks if the number is prime number.
 */
void printIsNumberPrime(int *memoPrime, int number) {
    int i;

    if (number <= memoPrime[NUM_PRIME - 1]) {
        // If the number is smaller than maximum of memoization database
        for (i = 0; memoPrime[i] <= number; i++) {
            if (number == memoPrime[i]) {
                printf("%d is a prime number.\n", number);

                return;
            }
        }
    } else if (number <= pow(memoPrime[NUM_PRIME - 1], 2) && isDynamicPrime(memoPrime, number)) {
        // If square root of the number is smaller than maximum of memoization database
        printf("%d is a prime number.\n", number);

        return;
    } else if (isStaticPrime(number)) { // If the number is larger than maximum of memoization database
        printf("%d is a prime number.\n", number);

        return;
    }

    printf("%d is not a prime number.\n", number);
}

/**
 * void printPrimes()
 * Function called when user inputs '-1'.
 * Shows all prime number database.
 */
void printPrimes(int *memoPrime) {
    int i;

    for (i = 0; i < NUM_PRIME; i++) {
        printf("%d ", memoPrime[i]);
    }

    printf("\n");
}

/**
 * int isDynamicPrime(int)
 * Checks if the number is prime number in memoization database.
 * If the number is a prime number, return true(= 1). Otherwise, return false(= 0).
 * The term 'Dynamic' means dynamic programming.
 */
int isDynamicPrime(const int *memoPrime, int number) {
    if (number == 1) return false;
    else {
        int i;

        for (i = 0; (memoPrime[i] != 0) && (memoPrime[i] <= sqrt(number)); i++) {
            if (number % memoPrime[i] == 0) return false;
        }
    }

    return true;
}

/**
 * int isStaticPrime(int)
 * Checks if the number is not in memoization database but prime number.
 * If the number is a prime number, return true(= 1). Otherwise, return false(= 0).
 */
int isStaticPrime(int number) {
    int i;

    for (i = FIRST_PRIME; i <= sqrt(number); i++) {
        if (number % i == 0) return false;
    }

    return true;
}
