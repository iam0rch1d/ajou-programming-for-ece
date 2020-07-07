#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 256

// Initiate Functions
void changeString(char *);

// Main function
int main() {
    printf("+---------------------------+\n");
    printf("|   String Changer Mk.III   |\n");
    printf("+---------------------------+\n");
    printf("|     Input any string      |\n");
    printf("+---------------------------+\n");

    char string[MAX_STRING_LENGTH];

    scanf("%s", string);
    changeString(string);

    return 0;
}

// Functions
/**
 * void changeString(char *)
 * Replaces all first character of each words with uppercase.
 */
void changeString(char *string) {
    int i = 0; // Cursor of string array
    int indexWordFirstCharacter = 0; // Indicate first character of each words

    printf("\"%s\" changed into \"", string);

    while (string[i] != '\0') {
        // Move cursor until next blank letter to indicate first letter of each word
        while (string[i] != ' ' && string[i] != '\0') {
            i++;
        }

        // If first character is lowercase, replace it with uppercase
        if (string[indexWordFirstCharacter] >= 'a' && string[indexWordFirstCharacter] <= 'z') {
            string[indexWordFirstCharacter] += 'A' - 'a';
        }

        i++;
        indexWordFirstCharacter = i; // Indicate first character of next word
    }

    printf("%s\".\n", string);
}
