#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 256

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
    printf("+---------------------------+\n");
    printf("|   String Changer Mk.III   |\n");
    printf("+---------------------------+\n");
    printf("|     Input any string      |\n");
    printf("+---------------------------+\n");
    printf("\n");
}

// getInput() - Scan string from user, return address of string
void getInput() {
    gets(str);
}

// change() - Replace all first character of each words with uppercase
void change(char* _str) {
    int i = 0; // Cursor of string array
    int iWordFirstChar = 0; // Indicate first character of each words

    printf("\"%s\" changed into \"", _str);

    while (_str[i] != '\0') {
        while (_str[i] != ' ' && _str[i] != '\0') { // Move cursor until next blank letter to indicate first letter of
            i++;                                    // each word
        }

        if (_str[iWordFirstChar] >= 'a' && _str[iWordFirstChar] <= 'z') { // If first character is lowercase, replace it
            _str[iWordFirstChar] += 'A' - 'a';                            // with uppercase
        }

        i++;
        iWordFirstChar = i; // Indicate first character of next word
    }

    printf("%s\".\n", _str);
}
