#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 200

// Function prototypes
void copyString(char *, char *);

// Main function
int main() {
    char string1[MAX_STRING_LENGTH];
    char string2[MAX_STRING_LENGTH];
    char *sourceString = string1;
    char *destinationString = string2;

    printf("+-------------------+\n");
    printf("|   String Copier   |\n");
    printf("+-------------------+\n");
    printf("Input a string:\n");
    scanf("%s", sourceString);
    copyString(sourceString, destinationString);
    printf("Copied string: %s", destinationString);

    return 0;
}

// Functions
/**
 * void copyString(char *, char *)
 * Copies string source to destination.
 */
void copyString(char *sourceString, char *destinationString) {
    while (*sourceString != '\0') {
        *destinationString++ = *sourceString++; // Copy character and move both pointer
    }

    *destinationString = '\0'; // End of line
}
