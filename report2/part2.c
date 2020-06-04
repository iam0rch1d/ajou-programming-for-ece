#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 256

// Initiate Functions
void showIntro();
char* getInput();
void change(const char*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    change(getInput());

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-------------------------+\n");
    printf("|   String Changer Mk.I   |\n");
    printf("+-------------------------+\n");
    printf("|    Input any string     |\n");
    printf("+-------------------------+\n");
    printf("\n");
}

// getInput() - Scan string from user, return address of string
char* getInput() {
    static char str[MAX_STRING_LENGTH];

    gets(str);

    return str;
}

// change() - Cut all spacings
void change(const char* str) {
    int i;
    int j;
    char resultStr[MAX_STRING_LENGTH];

    for (i = 0, j = 0; str[i] != '\0'; i++) {
        if (str[i] != ' ') { // If current character is blank space(' '), skip saving the character
            resultStr[j++] = str[i];
        }
    }

    resultStr[j] = '\0';

    printf("\"%s\" changed into \"%s\".\n", str, resultStr);
}
