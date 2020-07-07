#include <stdio.h>
#include <stdlib.h>

// Function prototypes
int scanNumberArray(int);
void printSumAverage(int, int);

// Main function
int main() {
    printf("+------------------+\n");
    printf("|  Average Finder  |\n");
    printf("+------------------+\n");

    char sizeString[10];

    printf("How many numbers?:\n");
    scanf("%s", sizeString);

    int size = strtol(sizeString, NULL, 10);

    printSumAverage(scanNumberArray(size), size);

    return 0;
}


// Functions
/**
 * int scanNumberArray(int)
 * Scans each element of number array from user.
 * Returns sum of number array.
 */
int scanNumberArray(int size) {
    int sum = 0;

    // Assert if [size] is larger than [0]
    if (size <= 0) {
        printf("Failure !\n");
        exit(1);
    }

    int *numberArray = malloc(size * sizeof(int)); // Dynamic memory allocation of [numberArray]

    // Assert if [numberArray] is successfully allocated
    if (numberArray == NULL) {
        printf("Failure !\n");
        exit(2);
    }

    printf("Enter %d numbers:\n", size);

    for (int i = 0; i < size; i++) {
        char numberString[11];

        scanf("%s", numberString); // Put number in [i]-th element of array

        numberArray[i] = strtol(numberString, NULL, 10);

        sum += numberArray[i];
    }

    free(numberArray); // Dynamic memory release of [numberArray]

    return sum;
}

/**
 * void printSumAverage(int, int)
 * Prints sum and average of number array.
 */
void printSumAverage(int sum, int size) {
    double average;

    average = (double) sum / size;

    printf("Sum: %d\n", sum);
    printf("Average: %.3lf", average);
}
