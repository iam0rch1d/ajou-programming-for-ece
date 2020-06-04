#include <stdio.h>

// Macros
#define NUM_INPUT 5

// Initiate Functions
void showIntro();
int* getInput();
void printMaxMinValue(const int*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    printMaxMinValue(getInput());

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-----------------------------------------+\n");
    printf("|            Max./Min. Finder             |\n");
    printf("+-----------------------------------------+\n");
    printf("|            Input %3d numbers            |\n", NUM_INPUT);
    printf("|    (divided by null space character)    |\n");
    printf("+-----------------------------------------+\n");
    printf("\n");
}

// getInput() - Scan numbers from user, return address of numbers array
int* getInput() {
    static int num[NUM_INPUT];
    int i;

    for (i = 0; i < NUM_INPUT; i++) {
        scanf("%d", &num[i]);
    }

    return num;
}

// printMaxMin() - Print maximum and minimum value of array
void printMaxMinValue(const int* arr) {
    int maxValue = arr[0];
    int minValue = arr[0];
    int i;

    for (i = 1; i < NUM_INPUT; i++) {
        if (arr[i] > maxValue) {
            maxValue = arr[i];
        }

        if (arr[i] < minValue) {
            minValue = arr[i];
        }
    }

    printf("Max. value is %d, min. value is %d.", maxValue, minValue);
}
