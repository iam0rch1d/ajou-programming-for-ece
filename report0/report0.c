#include <stdio.h>
#include <stdlib.h>

// Function prototypes
int performBinarySearch(int *, int, int, int);
void printBinarySearchResult(int, int);

// Main function
int main() {
    int data[] = { 10, 12, 13, 14, 18, 20, 25, 27, 30, 35, 40, 45, 47 };

    printf("Welcome to Programming for Electrical and Computer Engineering class!\n");
    printf("Input the target number:");

    char targetString[2];

    scanf("%s", targetString);

    int target = strtol(targetString, NULL, 10);

    printBinarySearchResult(target, performBinarySearch(data, target, 0, sizeof(int)));

    return 0;
}

// Functions
/**
 * int performBinarySearch(int *, int, int, int)
 * Performs binary search.
 * If search fails, return -1.
 */
int performBinarySearch(int *data, int target, int indexLow, int indexHigh) {
    if (indexLow > indexHigh) return -1; // Target is not found

    int indexPivot = (indexLow + indexHigh) / 2;

    // Target hits the pivot
    if (target == data[indexPivot]) return indexPivot;
    // Target is smaller than the pivot
    else if (target < data[indexPivot]) return performBinarySearch(data, target, indexLow, indexPivot - 1);
    // Target is larger than the pivot
    else return performBinarySearch(data, target, indexPivot + 1, indexHigh);
}

/**
 * void printBinarySearchResult(int, int)
 * Prints binary search result.
 */
void printBinarySearchResult(int target, int indexTarget) {
    if (indexTarget == -1) printf("%d is not found.", target); // Search fails
    else printf("%d is the index %d.\n", target, indexTarget); // Search succeeds
}
