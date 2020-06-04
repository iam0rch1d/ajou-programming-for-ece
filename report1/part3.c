#include <stdio.h>

// Macros
#define BINARY_DIVIDER 2

enum boolean {
    false = 0,
    true
};

// Global variables
int flagPrintDecimal;

// Initiate Functions
void showIntro();
int getInput();
void printBinary(int);
void printPeriod();

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    printBinary(getInput());
    printPeriod();

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-----------------------------------------+\n");
    printf("|            Binary converter             |\n");
    printf("+-----------------------------------------+\n");
    printf("|   Input any natural number to convert   |\n");
    printf("+-----------------------------------------+\n");
    printf("\n");
}

// getInput() - Scan a natural number from user
int getInput() {
    int num;

    printf("Input a natural number:");
    scanf("%d", &num);

    if (num < 0) {
        return -1;
    }
    else {
        return num;
    }
}

// printBinary() - Convert decimal into binary and print it by using recursion
void printBinary(int num) {
    if (num <= -1) {
        printf("Invalid number");
    }
    else {
        if (flagPrintDecimal == false) {
            printf("Decimal %d is binary ", num);

            flagPrintDecimal = true;
        }

        if (num < BINARY_DIVIDER) {
            printf("%d", num);
        }
        else {
            printBinary(num / BINARY_DIVIDER);
            printf("%d", num % BINARY_DIVIDER);
        }
    }
}

// printPeriod() - Just for decoration
void printPeriod() {
    printf(".\n");
}
