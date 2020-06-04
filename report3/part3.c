#include <stdio.h>

// Macros
#define MAX_STRING_LENGTH 200

// Initiate Functions
void showIntro();
void getSrcStr(char*);
void copy(char*, char*);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    char str1[MAX_STRING_LENGTH];
    char str2[MAX_STRING_LENGTH];
    char* srcStr = str1;
    char* dstStr = str2;

    showIntro();
    getSrcStr(str1);
    copy(srcStr, dstStr);
    printf("Copied string: ");
    puts(str2);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-------------------+\n");
    printf("|   String Copier   |\n");
    printf("+-------------------+\n");
    printf("\n");
}

// getSrcStr() - Scan source string from user
void getSrcStr(char* srcStr) {
    printf("Input a string:\n");
    gets(srcStr);
}

// copy() - Copy string
void copy(char* srcStr, char* dstStr) {
    while (*srcStr != '\0') {
        *dstStr++ = *srcStr++; // Copy character and move both pointer
    }

    *dstStr = '\0'; // End of line
}
