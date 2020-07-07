#include <stdio.h>

// Function prototypes
void identifyCharacter();

// Main function
int main() {
    printf("+-----------------------------------+\n");
    printf("|     Digit Character Identifier    |\n");
    printf("+-----------------------------------+\n");
    printf("|  Input any character to identify  |\n");
    printf("+-----------------------------------+\n");
    identifyCharacter();

    return 0;
}

// Functions
/**
 * void identifyCharacter()
 * Scans character from user and identifies if the character is digit.
 */
void identifyCharacter() {
    char character;

    printf("Input a character:");
    scanf("%c", &character);

    if (character >= '0' && character <= '9') {
        printf("\'%c\' is a digit.\n", character);
    } else {
        printf("\'%c\' is not a digit.\n", character);
    }
}
