#include <stdio.h>
#include <stdlib.h>

// Function prototypes
void scanString(int);

// Main function
int main() {
    printf("+-------------------+\n");
    printf("|  String Follower  |\n");
    printf("+-------------------+\n");

    char lengthString[10];

    printf("Maximum length of the string:\n");
    scanf("%s", lengthString);
    scanString(strtol(lengthString, NULL, 10));

    return 0;
}

// Functions
/**
 * void scanString(int);
 * Scans input stream with [length] characters and stores at [string].
 */
void scanString(int length) {
    char *string = NULL;

    // Assert if [length] is larger than [0]
    if (length <= 0) {
        printf("Failure !\n");
        exit(1);
    }

    string = (char *) malloc((length + 1) * sizeof(char)); // Dynamic memory allocation of [string]

    // Assert if [string] is successfully allocated
    if (string == NULL) {
        printf("Failure !\n");
        exit(2);
    }

    fflush(stdin);
    printf("Enter string:\n");
    fgets(string, length + 1, stdin);
    printf("=====================\n");
    printf("-> %s\n", string);

    free(string); // Dynamic memory release of [string]
}
