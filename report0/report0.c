#include <stdio.h>

// Macros
#define NUM_DATA 13

// Global variables
int target;
int iTarget;

// Initiate Functions
void showIntro();
int setTarget();
int search(int*, int, int, int);
void showResult(int, int);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    int data[NUM_DATA] = {10, 12, 13, 14, 18, 20, 25, 27, 30, 35, 40, 45, 47};

    showIntro();
    setTarget();
    search(data, target, 0, NUM_DATA - 1);
    showResult(target, iTarget);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - Say Hi
void showIntro() {
    printf("Welcome to Programming for Electrical and Computer Engineering class!\n");
    printf("Input the target number:");
}

// setTarget() - Scan and set target from user
int setTarget() {
    scanf("%d", &target);

    return target;
}

// search() - Perform binary search
int search(int* arr, int _target, int iLow, int iHigh) {
    if (iLow > iHigh) { // Target is not found
        iTarget = -1;

        return 0;
    }

    int iPivot = (iLow + iHigh) / 2;

    if (_target == arr[iPivot]) { // Target hits the pivot
        iTarget = iPivot;

        return 0;
    }
    else if (_target < arr[iPivot]) { // Target is smaller than the pivot
        return search(arr, _target, iLow, iPivot - 1);
    }
    else { // Target is larger than the pivot
        return search(arr, _target, iPivot + 1, iHigh);
    }
}

// showResult() - Show result
void showResult(int _target, int _iTarget) {
    if (_iTarget == -1) { // Search fails
        printf("%d is not found.", _target);
    }
    else { // Search succeeds
        printf("%d is the index %d.\n", _target, _iTarget);
    }
}
