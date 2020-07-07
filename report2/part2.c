#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 256

// Function prototypes
char *scanInputString();
void changeString(const char *);

// Main function
int main() {
    printf("+-----------------------+\n");
    printf("|  String Changer Mk.I  |\n");
    printf("+-----------------------+\n");
    printf("|   Input any string    |\n");
    printf("+-----------------------+\n");
    changeString(scanInputString());

    return 0;
}

// Functions
/**
 * char *scanInputString()
 * Scans string from user.
 * Returns address of string.
 */
char *scanInputString() {
    static char inputString[MAX_STRING_LENGTH];

    scanf("%s", inputString);

    return inputString;
}

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
