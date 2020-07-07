#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 200

// Function prototypes
void changeString(char *);

// Main function
int main() {
    printf("+------------------------+\n");
    printf("|  String Changer Mk.II  |\n");
    printf("+------------------------+\n");
    printf("|    Input any string    |\n");
    printf("+------------------------+\n");

    char inputString[MAX_STRING_LENGTH];

    scanf("%s", inputString);
    changeString(inputString);

    return 0;
}

// Functions
/**
 * void changeString(char *)
 * Replaces all first lowercases of each word with uppercase
 */
void changeString(char *inputString) {
    int i = 0; // Cursor of string array

    printf("\"%s\" changed into \"", inputString);

    while (inputString[i] != '\0') {
        // If character is lowercase, replace it with uppercase
        if (inputString[i] >= 'a' && inputString[i] <= 'z') inputString[i] += 'A' - 'a';

        // Move cursor until next blank letter to indicate first letter of each word
        while (inputString[i] != ' ' && inputString[i] != '\0') {
            i++;
        }

        i++;
    }

    printf("%s\".\n", inputString);
}
