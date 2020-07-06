#include <stdio.h>
#include <limits.h>

enum boolean {
    false = 0,
    true
};

// Initiate functions
int parseInt(char *);
int search(int *, int, int, int);
void showSearchResult(int, int);


// Main function
int main() {
    int data[] = {10, 12, 13, 14, 18, 20, 25, 27, 30, 35, 40, 45, 47};

    printf("Welcome to Programming for Electrical and Computer Engineering class!\n");
    printf("Input the target number:");

    char targetString[2];

    scanf("%s", targetString);

    int target = parseInt(targetString);
    int indexTarget = search(data, target, 0, sizeof(data) / sizeof(data[0]));

    showSearchResult(target, indexTarget);

    return 0;
}


// Functions

/** int parseInt(char *)
 * Parse string to integer.
 */
int parseInt(char *string) {
    char *stringCursor = string;
    int boolNegative = false;
    int value = 0;

    while (*stringCursor != '\0') {
        if (*stringCursor == '-' && stringCursor == string) {
            boolNegative = true;
        } else if (*stringCursor < '0' || *stringCursor > '9') {
            printf("Number Format Exception.\n");

            return INT_MAX;
        }

        value *= 10;

        if (boolNegative) {
            value -= *stringCursor - '0';
        } else {
            value += *stringCursor - '0';
        }

        stringCursor++;
    }

    return value;
}

/** int search(int *, int, int, int)
 * Perform binary search.
 * If search fails, return -1.
 */
int search(int *data, int target, int indexLow, int indexHigh) {
    if (indexLow > indexHigh) { // Target is not found
        return -1;
    }

    int indexPivot = (indexLow + indexHigh) / 2;

    if (target == data[indexPivot]) { // Target hits the pivot
        return indexPivot;
    } else if (target < data[indexPivot]) { // Target is smaller than the pivot
        return search(data, target, indexLow, indexPivot - 1);
    } else { // Target is larger than the pivot
        return search(data, target, indexPivot + 1, indexHigh);
    }
}

/** void showSearchResult(int, int)
 * Show binary search result.
 */
void showSearchResult(int target, int indexTarget) {
    if (indexTarget == -1) { // Search fails
        printf("%d is not found.", target);
    } else { // Search succeeds
        printf("%d is the index %d.\n", target, indexTarget);
    }
}
