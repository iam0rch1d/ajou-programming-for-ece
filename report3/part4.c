#include <stdio.h>

// Macros
#define SIZE 10

// Initiate Functions
void showIntro();
void getArr(int*);
void sort(int*);
void swap(int*, int*);
void printArr(const int*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    int arr[SIZE];

    showIntro();
    getArr(arr);
    sort(arr);
    printArr(arr);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-----------------+\n");
    printf("|   Bubble Sort   |\n");
    printf("+-----------------+\n");
    printf("\n");
}

// getArr() - Scan number array from user
void getArr(int* arr) {
    int i;

    printf("Input %d numbers:\n", SIZE);

    for (i = 0; i < SIZE; i++) {
        scanf("%d", arr + i);
    }
}

// sort() - Do bubble sort
void sort(int* arr) {
    int i;
    int j;

    for (i = 0; i < SIZE - 1; i++) { // Select left element
        for (j = i + 1; j < SIZE; j++) { // Select right element
            if (*(arr + i) > *(arr + j)) { // If left element is larger than right element, swap them
                swap(arr + i, arr + j);
            }
        }
    }
}

// swap() - Used for sort
void swap(int* a, int* b) {
    int tmp;

    tmp = *a;
    *a = *b;
    *b = tmp;
}

// printArr() - Print number array
void printArr(const int* arr) {
    int i;

    printf("After sort: ");

    for (i = 0; i < SIZE; i++) {
        printf("%d ", *(arr + i));
    }

    printf("\n");
}
