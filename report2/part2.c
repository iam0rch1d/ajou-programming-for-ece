#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 256

// Function prototypes
void changeString(const char *);

// Main function
int main() {
    printf("+-----------------------+\n");
    printf("|  String Changer Mk.I  |\n");
    printf("+-----------------------+\n");
    printf("|   Input any string    |\n");
    printf("+-----------------------+\n");

    char inputString[MAX_STRING_LENGTH];

    gets(inputString);
    changeString(inputString);

    return 0;
}

// Functions
/**
 * void changeString(const char *)
 * Cuts all spacings.
 */
void changeString(const char *inputString) {
    int i;
    int j;
    char outputString[MAX_STRING_LENGTH];

    for (i = 0, j = 0; inputString[i] != '\0'; i++) {
        // If current character is blank space(' '), skip saving the character
        if (inputString[i] != ' ') outputString[j++] = inputString[i];
    }

    outputString[j] = '\0';

    printf("\"%s\" changed into \"%s\".\n", inputString, outputString);
}
