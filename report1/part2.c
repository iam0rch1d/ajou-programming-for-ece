#include <stdio.h>

// Macros
enum boolean {
    false = 0,
    true
};

// Initiate Functions
void showIntro();
void identify();
int isDigit(char);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    showIntro();
    identify();

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// showIntro() - User interface
void showIntro() {
    printf("+-------------------------------------+\n");
    printf("|      Digit Character Identifier     |\n");
    printf("+-------------------------------------+\n");
    printf("|   Input any character to identify   |\n");
    printf("+-------------------------------------+\n");
    printf("\n");
}

// identify() - Scan a character from user and identify if the character is digit
void identify() {
    char ch;

    printf("Input a character:");
    scanf("%c", &ch);

    if (isDigit(ch) == false) {
        printf("\'%c\' is not a digit.\n", ch);
    }
    else {
        printf("\'%c\' is a digit.\n", ch);
    }
}

// isDigit() - Check if the character is number
int isDigit(char ch) {
    return (ch >= '0' && ch <= '9')? true : false;
}
