#include <stdio.h>
#include <stdlib.h>

// Macros
#define BINARY_DIVIDER 2

enum boolean {
    false = 0,
    true
};

// Function prototypes
void printBinary(int, int);

// Main function
int main() {
    printf("+---------------------------------------+\n");
    printf("|           Binary converter            |\n");
    printf("+---------------------------------------+\n");
    printf("|  Input any natural number to convert  |\n");
    printf("+---------------------------------------+\n");
    printf("Input a natural number:");

    char numberString[11];

    scanf("%s", numberString);

    int number = strtol(numberString, NULL, 10);

    printBinary(number, false);
    printf(".\n");

    return 0;
}

// Functions
/**
 * void printBinary()
 * Converts decimal into binary and print it by using recursion.
 */
void printBinary(int number, int hasDecimalPrinted) {
    if (number <= -1) {
        printf("Invalid number");
    } else {
        if (hasDecimalPrinted == false) {
            printf("Decimal %d is binary ", number);

            hasDecimalPrinted = true;
        }

        if (number < BINARY_DIVIDER) {
            printf("%d", number);
        } else {
            printBinary(number / BINARY_DIVIDER, hasDecimalPrinted);
            printf("%d", number % BINARY_DIVIDER);
        }
    }
}
