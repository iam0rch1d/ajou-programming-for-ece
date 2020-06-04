#include <stdio.h>

// Macros
#define MAX_TOWER_SIZE 64
#define MIN_TOWER_SIZE 1

// Global variables
unsigned long long countMove;

// Function prototypes
unsigned int setTowerSize(void);
void moveHanoi(unsigned int, char, char, char);
void moveDisk(unsigned int, char, char);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main() {
    moveHanoi(setTowerSize(), 'A', 'B', 'C');

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// setTowerSize() - Scan size of tower from user
unsigned int setTowerSize(void) {
    int towerSize;

    while (1) {
        printf("Input size of tower. [%d-%d]:", MIN_TOWER_SIZE, MAX_TOWER_SIZE);
        scanf("%d", &towerSize);

        if (towerSize < MIN_TOWER_SIZE || towerSize > MAX_TOWER_SIZE) { // Limit tower size not to overflow at counting
                                                                        // moves
            printf("Invalid tower size. Please input again.\n");
        }
        else {
            return towerSize;
        }
    }
}

// moveHanoi() - Move whole tower by recursion
void moveHanoi(unsigned int towerSize, char srcPeg, char auxPeg, char dstPeg) {
    if (towerSize == 0) { // Exception. Does not occur
        printf("Can't move.\n");
    }
    else if (towerSize == 1) { // Base case. Move single disk
        moveDisk(towerSize, srcPeg, dstPeg);
    }
    else { // Recursion steps. Move multiple disk
        moveHanoi(towerSize - 1, srcPeg, dstPeg, auxPeg); // Move all disks above lowest disk from source peg to
                                                          // auxiliary peg, in order to move lowest disk from source peg
                                                          // to destination peg
        moveDisk(towerSize, srcPeg, dstPeg); // Move lowest disk from source peg to destination peg
        moveHanoi(towerSize - 1, auxPeg, srcPeg, dstPeg); // Move left disks and put on lowest disk
    }
}

// moveDisk() - Move single disk
void moveDisk(unsigned int diskSize, char srcPeg, char dstPeg) {
    printf("Move size %d disk from %c to %c. (Moves: %lld)\n", diskSize, srcPeg, dstPeg, ++countMove);
}
