#include <stdio.h>
#include <stdlib.h>

// Macros
#define MAX_TOWER_SIZE 64
#define MIN_TOWER_SIZE 1

enum boolean {
    false = 0,
    true
};

// Global variables
unsigned long long countMove;

// Function prototypes
void moveHanoi(unsigned int, char, char, char);
void moveDisk(unsigned int, char, char);

// Main function
int main() {
    int towerSize;
    
    while (true) {
        printf("Input size of tower. [%d-%d]:", MIN_TOWER_SIZE, MAX_TOWER_SIZE);

        char towerSizeString[3];

        scanf("%s", towerSizeString);

        towerSize = strtol(towerSizeString, NULL, 10);

        // Limit tower size not to overflow at counting moves
        if (towerSize < MIN_TOWER_SIZE || towerSize > MAX_TOWER_SIZE) {
            printf("Invalid tower size. Please input again.\n");
        } else break;
    }

    moveHanoi(towerSize, 'A', 'B', 'C');

    return 0;
}

// Functions
/**
 * void moveHanoi(unsigned int, char, char, char)
 * Moves tower with size of [towerSize] by recursion.
 */
void moveHanoi(unsigned int towerSize, char sourcePeg, char auxiliaryPeg, char destinationPeg) {
    if (towerSize == 1) { // Base case. Move single disk
        moveDisk(towerSize, sourcePeg, destinationPeg);
    } else { // Recursion steps. Move multiple disks
        // Move all disks above the lowest disk from source peg to auxiliary peg, in order to move the lowest disk from
        // source peg to destination peg
        moveHanoi(towerSize - 1, sourcePeg, destinationPeg, auxiliaryPeg);
        // Move the lowest disk from source peg to destination peg
        moveDisk(towerSize, sourcePeg, destinationPeg);
        // Move left disks and put on the lowest disk
        moveHanoi(towerSize - 1, auxiliaryPeg, sourcePeg, destinationPeg);
    }
}

/**
 * void moveDisk(unsigned int, char, char)
 * Moves single disk
 */
void moveDisk(unsigned int diskSize, char sourcePeg, char destinationPeg) {
    printf("Move size %d disk from %c to %c. (Moves: %lld)\n", diskSize, sourcePeg, destinationPeg, ++countMove);
}
