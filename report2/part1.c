#include <stdio.h>
#include <stdlib.h>

// Macros
#define NUM_INPUT 5

// Function prototypes
int *scanNumberArray();
void printMaxMinValue(const int *);

// Main function
int main() {
    printf("+-------------------------------------+\n");
    printf("|          Max./Min. Finder           |\n");
    printf("+-------------------------------------+\n");
    printf("|          Input %3d numbers          |\n", NUM_INPUT);
    printf("|  (divided by null space character)  |\n");
    printf("+-------------------------------------+\n");
    printMaxMinValue(scanNumberArray());

    return 0;
}

// Functions
/**
 * int *scanNumberArray()
 * Scans numbers from user.
 * Returns address of numbers array.
 */
int *scanNumberArray() {
    char numberString[11];
    static int numberArray[NUM_INPUT];
    int i;

    for (i = 0; i < NUM_INPUT; i++) {
        scanf("%s", numberString);

        numberArray[i] = strtol(numberString, NULL, 10);
    }

    return numberArray;
}

/**
 * void printMaxMinValue(const int *)
 * Prints maximum and minimum value of array.
 */
void printMaxMinValue(const int *numberArray) {
    int maxValue = numberArray[0];
    int minValue = numberArray[0];
    int i;

    for (i = 1; i < NUM_INPUT; i++) {
        if (numberArray[i] > maxValue) maxValue = numberArray[i];

        if (numberArray[i] < minValue) minValue = numberArray[i];
    }

    printf("Maximum value is %d.\nMinimum value is %d.\n", maxValue, minValue);
}
