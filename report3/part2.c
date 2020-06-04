#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

// Initiate Functions
void showIntro();
int getSize();
int getArray(int);
void printSumAvg(int, int);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    int size;

    showIntro();

    size = getSize();

    printSumAvg(getArray(size), size);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+---------------------+\n");
    printf("|   Average Finder    |\n");
    printf("+---------------------+\n");
    printf("\n");
}

// getSize() - Scan size of number array from user, return size
int getSize() {
    int size;

    printf("How many numbers ?:\n");
    scanf("%d", &size);

    return size;
}

// getArray() - Scan each element of number array from user, return sum of number array
int getArray(int size) {
    int i;
    int sum = 0;
    int* arr = NULL;

    // assert(size > 0);
    if (size <= 0) {
        printf("Failure !\n");
        exit(1);
    }

    arr = (int*)malloc(size * sizeof(int)); // Dynamic memory allocation - arr

    // assert(arr != NULL);
    if (arr == NULL) {
        printf("Failure !\n");
        exit(2);
    }

    printf("Enter %d numbers:\n", size);

    for (i = 0; i < size; i++) {
        scanf("%d", arr + i); // Put number in i-th element of array

        sum += arr[i];
    }

    free(arr); // Dynamic memory release - arr

    return sum;
}

// printSumAvg() - Print sum and average of number array
void printSumAvg(int sum, int size) {
    double average;

    average = (double)sum / size;

    printf("Sum: %d\n", sum);
    printf("Average: %.3lf", average);
}
