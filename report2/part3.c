#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 200

// Global variables
char str[MAX_STRING_LENGTH];

// Initiate Functions
void showIntro();
void getInput();
void change(char*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    getInput();
    change(str);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+--------------------------+\n");
    printf("|   String Changer Mk.II   |\n");
    printf("+--------------------------+\n");
    printf("|     Input any string     |\n");
    printf("+--------------------------+\n");
    printf("\n");
}

// getInput() - Scan string from user, return address of string
void getInput() {
    gets(str);
}

// change() - Replace all first lowercase of each words with uppercase
void change(char* _str) {
    int i = 0; // Cursor of string array

    printf("\"%s\" changed into \"", _str);

    while (_str[i] != '\0') {
        if (_str[i] >= 'a' && _str[i] <= 'z') { // If character is lowercase, replace it with uppercase
            _str[i] += 'A' - 'a';
        }

        while (_str[i] != ' ' && _str[i] != '\0') { // Move cursor until next blank letter to indicate first letter of
            i++;                                    // each word
        }

        i++;
    }

    printf("%s\".\n", _str);
}
