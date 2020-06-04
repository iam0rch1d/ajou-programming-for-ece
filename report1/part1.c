#include <stdio.h>
#include <math.h>

// Macros
#define NUM_PRIME 100 // Number of prime numbers
#define FIRST_PRIME 2 // First prime number(= 2)

enum boolean {
    false = 0,
    true
};

// Global variables
int memoPrime[NUM_PRIME] = {FIRST_PRIME};
int countPrime;

// Initiate Functions
void init();
void showIntro();
void loopMain();
void showOne(int);
void showAllPrimes();
void showInputError();
int isDynamicPrime(int);
int isStaticPrime(int);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    init();
    showIntro();
    loopMain();

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// init() - Setup memoization database of prime numbers
void init() {
    int i = 0;

    while (countPrime <= NUM_PRIME) {
        i++;

        if (isDynamicPrime(i) == true) {
            memoPrime[countPrime] = i;
            countPrime++;
        }
    }
}

// showIntro() - User interface
void showIntro() {
    printf("+--------------------------------------------------------------+\n");
    printf("|                     Prime Number Finder                      |\n");
    printf("+--------------------------------------------------------------+\n");
    printf("|   <natural number> : Check if the number is a prime number   |\n");
    printf("|               '-1' : Show all prime number database          |\n");
    printf("|                '0' : Exit                                    |\n");
    printf("+--------------------------------------------------------------+\n");
    printf("\n");
}

// loopMain() - Scan a number from user
void loopMain() {
    int num;

    while (true) {
        printf("Input a number:");
        scanf("%d", &num);

        if (num == 0) {
            break;
        }
        else if (num == -1) {
            showAllPrimes();
        }
        else if (num >= 1) {
            showOne(num);
        }
        else {
            showInputError();
        }
    }
}

// showOne() - User inputs <natural number>, check if the number is prime number
void showOne(int num) {
    int i;

    if (num <= memoPrime[NUM_PRIME - 1]) { // If the number is smaller than maximum of memoization database
        for (i = 0; memoPrime[i] <= num; i++) {
            if (num == memoPrime[i]) {
                printf("%d is a prime number.\n", num);

                return;
            }
        }
    }
    else if (num <= pow(memoPrime[NUM_PRIME - 1], 2)) { // If square root of the number is smaller than maximum of
                                                        // memoization database
        if (isDynamicPrime(num) == true) {
            printf("%d is a prime number.\n", num);

            return;
        }
    }
    else { // If the number is larger than maximum of memoization database
        if (isStaticPrime(num) == true) {
            printf("%d is a prime number.\n", num);

            return;
        }
    }

    printf("%d is not a prime number.\n", num);
}

// showAllPrimes() - User inputs '-1', show all prime number database
void showAllPrimes() {
    int i;

    for (i = 0; i < NUM_PRIME; i++) {
        printf("%d ", memoPrime[i]);
    }

    printf("\n");
}

// showInputError() - User inputs wrong number
void showInputError() {
    printf("Invalid data.\n");
}

// isDynamicPrime() - Check if the number is prime number in memoization database
// (The term 'Dynamic' means dynamic programming.)
int isDynamicPrime(int num) {
    if (num == 1) {
        return false;
    }
    else {
        int i;

        for (i = 0; (memoPrime[i] != 0) && (memoPrime[i] <= sqrt(num)); i++) {
            if (num % memoPrime[i] == 0) {
                return false;
            }
        }
    }

    return true;
}

// isStaticPrime() - Check if the number is not in memoization database but prime number
int isStaticPrime(int num) {
    int i;

    for (i = FIRST_PRIME; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return false;
        }
    }

    return true;
}
