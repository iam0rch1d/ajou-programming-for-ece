#include <stdio.h>
#include <stdlib.h>

// Macros
#define SIZE 10

// Initiate Functions
void scanNumberArray(int *);
void performBubbleSort(int *);
void printNumberArray(const int *);

// Main function
int main() {
    int numberArray[SIZE];

    printf("+---------------+\n");
    printf("|  Bubble Sort  |\n");
    printf("+---------------+\n");
    scanNumberArray(numberArray);
    printf("Before sort: ");
    printNumberArray(numberArray);
    performBubbleSort(numberArray);
    printf("After sort: ");
    printNumberArray(numberArray);

    return 0;
}

// Functions
/**
 * void scanNumberArray(int *)
 * Scans number array from user.
 */
void scanNumberArray(int *numberArray) {
    int i;

    printf("Input %d numbers:\n", SIZE);

    for (i = 0; i < SIZE; i++) {
        char numberString[11];

        scanf("%s", numberString); // Put number in [i]-th element of array

        numberArray[i] = strtol(numberString, NULL, 10);
    }
}

/**
 * void performBubbleSort(int *);
 * Performs bubble sort.
 */
void performBubbleSort(int *numberArray) {
    int i;
    int j;

    for (i = 0; i < SIZE - 1; i++) { // Select left element
        for (j = i + 1; j < SIZE; j++) { // Select right element
            if (numberArray[i] > numberArray[j]) { // If left element is larger than right element, swap them
                int temp = numberArray[i];

                numberArray[i] = numberArray[j];
                numberArray[j] = temp;
            }
        }
    }
}

/**
 * void printNumberArray(const int *)
 * Prints number array.
 */
void printNumberArray(const int *numberArray) {
    int i;

    for (i = 0; i < SIZE; i++) {
        printf("%d ", numberArray[i]);
    }

    printf("\n");
}
