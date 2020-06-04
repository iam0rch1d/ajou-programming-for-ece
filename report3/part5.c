#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

// Initiate Functions
void showIntro();
int getLength();
void getString(int);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    getString(getLength());

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+---------------------+\n");
    printf("|   String Follower   |\n");
    printf("+---------------------+\n");
    printf("\n");
}

// getSize() - Scan size of number array from user, return size
int getLength() {
    int length;

    printf("Max length of the string:\n");
    scanf("%d", &length);

    return length;
}

// getString() - Scan each element of number array from user, return sum of number array
void getString(int length) {
    char *str = NULL;

    // assert(length > 0);
    if (length <= 0) {
        printf("Failure !\n");
        exit(1);
    }

    str = (char*)malloc((length + 1) * sizeof(char)); // Dynamic memory allocation - str

    // assert(str != NULL);
    if (str == NULL) {
        printf("Failure !\n");
        exit(2);
    }

    fflush(stdin);
    printf("Enter string:\n");
    fgets(str, length + 1, stdin);
    printf("=======================\n");
    printf("-> %s\n", str);

    free(str); // Dynamic memory release - str
}
