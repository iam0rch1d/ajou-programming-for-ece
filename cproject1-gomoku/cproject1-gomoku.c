#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <Windows.h>

// Macros
#define MAX_BOARD_SIZE 19
#define MIN_BOARD_SIZE 11
#define NUM_PLAYER 2
#define NUM_DIRECTION 4
#define TIME_SLEEP 5000

enum boolean {
    false = 0,
    true
};

enum boardState {
    STATE_BLACK = 0,
    STATE_WHITE,
    STATE_EMPTY
};

enum direction {
    DIRECTION_NUMPAD1_TO_NUMPAD9 = 0,
    DIRECTION_NUMPAD2_TO_NUMPAD8,
    DIRECTION_NUMPAD3_TO_NUMPAD7,
    DIRECTION_NUMPAD4_TO_NUMPAD6,
};

// Type definitions
typedef struct {
    int x;
    int y;
} Point;

// Function prototypes
void initializeBoard(int **, int);
void printCurrentBoard(int **, int, int, int, int);
int isPositionInBoard(int, int, int);
int isPlaceable(int **, int, int, int, int);
int isEmpty(int **, int, int);
int isThreeAndThree(int **, int, int, int, int);
int isGomoku(int **, int, int, int, int);
int isDrawGame(int, int);

// Main function
int main(void) {
    int **board = NULL;
    int boardSize;
    int countMoves = 0;
    int currentPlayer = STATE_BLACK;
    int i;
    char inputMoveCursor;
    Point CursorPosition;

    printf("Input board size. ([%d-%d]):", MIN_BOARD_SIZE, MAX_BOARD_SIZE);
    scanf("%d", &boardSize);

    if (boardSize < MIN_BOARD_SIZE || boardSize > MAX_BOARD_SIZE) {
        printf("Invalid board size.\n");
        Sleep(TIME_SLEEP);

        return 0;
    }

    board = (int **) malloc(boardSize * sizeof(int *)); // Allocate memory of board

    for (i = 0; i < boardSize; i++) { // Allocate memory of each line of board
        board[i] = (int *) malloc(boardSize * sizeof(int));
    }

    initializeBoard(board, boardSize);

    // Set initial cursor position
    CursorPosition.x = boardSize / 2;
    CursorPosition.y = boardSize / 2;

    system("cls"); // Clean screen
    printCurrentBoard(board, CursorPosition.x, CursorPosition.y, countMoves, boardSize); // Print initial board

    do { // Gomoku game session
        // Perform while loop to get input from user until user inputs carriage return('\r') in placeable point
        while ((inputMoveCursor = (char) getch()) != '\r'
        || isPlaceable(board, CursorPosition.x, CursorPosition.y, countMoves, boardSize) == false) {
            // Move cursor by 'w', 'a', 's', 'd' input
            switch (inputMoveCursor) {
                // Move cursor up
                case 'w': {
                    if (isPositionInBoard(CursorPosition.x, CursorPosition.y - 1, boardSize) == true) {
                        CursorPosition.y--;
                    }

                    break;
                }
                // Move cursor left
                case 'a': {
                    if (isPositionInBoard(CursorPosition.x - 1, CursorPosition.y, boardSize) == true) {
                        CursorPosition.x--;
                    }

                    break;
                }
                // Move cursor down
                case 's': {
                    if (isPositionInBoard(CursorPosition.x, CursorPosition.y + 1, boardSize) == true) {
                        CursorPosition.y++;
                    }

                    break;
                }
                // Move cursor right
                case 'd': {
                    if (isPositionInBoard(CursorPosition.x + 1, CursorPosition.y, boardSize) == true) {
                        CursorPosition.x++;
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            system("cls");
            printCurrentBoard(board, CursorPosition.x, CursorPosition.y, countMoves, boardSize); // Update board
        }

        currentPlayer = countMoves % NUM_PLAYER;
        board[CursorPosition.y][CursorPosition.x] = currentPlayer; // Place stone by carriage return('\r') input
    } while (isGomoku(board, CursorPosition.x, CursorPosition.y, countMoves, boardSize) == false
    && isDrawGame(countMoves++, boardSize) == false);

    // Game sets
    system("cls");
    printCurrentBoard(board, CursorPosition.x, CursorPosition.y, countMoves, boardSize);

    // Print result
    if (isDrawGame(countMoves, boardSize) == true) {
        printf("Draw game\n");
    } else if (currentPlayer == STATE_BLACK) {
        printf("Black wins !\n");
    } else {
        printf("White wins !\n");
    }

    Sleep(TIME_SLEEP);

    for (i = 0; i < boardSize; i++) { // Release memory of each line of board
        free(board[i]);
    }

    free(board); // Release memory of board

    return 0;
}

// Functions

// InitBoard() - Initialize board
void initializeBoard(int **board, int boardSize) {
    int i;
    int j;

    for (i = 0; i < boardSize; i++) {
        for (j = 0; j < boardSize; j++) {
            board[i][j] = STATE_EMPTY;
        }
    }
}

// PrintCurrentBoard() - Print current state of board
void printCurrentBoard(int **board, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int i;
    int j;

    for (i = 0; i < boardSize; i++) {
        printf("\n");

        for (j = 0; j < boardSize; j++) {
            if (i == yPosition && j == xPosition &&
                isGomoku(board, xPosition, yPosition, countMoves, boardSize) == false) {
                printf(" @ ");
            } else switch (board[i][j]) {
                case STATE_BLACK: {
                    printf(" B ");

                    break;
                }
                case STATE_WHITE: {
                    printf(" W ");

                    break;
                }
                case STATE_EMPTY: {
                    if (i == yPosition && j == xPosition) {
                        printf(" @ ");
                    } else {
                        printf(" . ");
                    }

                    break;
                }
                default: {
                    break;
                }
            }
        }

        printf("\n");
    }

    printf("\n");

    if (currentPlayer == STATE_BLACK) {
        printf("Move No.%d\n", countMoves + 1);
        printf("Black's turn.\n");
    } else {
        printf("Move No.%d\n", countMoves + 1);
        printf("White's turn.\n");
    }

    if (isThreeAndThree(board, xPosition, yPosition, countMoves, boardSize) == true) {
        printf("Banned move: 3-3\n");
    }
}

// IsPositionInBoard() - Check if move is in board
int isPositionInBoard(int xPosition, int yPosition, int boardSize) {
    return (xPosition >= 0 && xPosition < boardSize && yPosition >= 0 && yPosition < boardSize);
}

// IsPlaceable() - Check if point is placeable. It should be empty and not '3-3' (for black) to place stone.
int isPlaceable(int **board, int xPosition, int yPosition, int countMoves, int boardSize) {
    return (isEmpty(board, xPosition, yPosition) == true
    && isThreeAndThree(board, xPosition, yPosition, countMoves, boardSize) == false);
}

// IsEmpty() - Check if point is empty
int isEmpty(int **board, int xPosition, int yPosition) {
    return (board[yPosition][xPosition] == STATE_EMPTY);
}

// IsExplicitThreeAndThree() - Check if '3-3' has occurred, only for black.
int isThreeAndThree(int **board, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int xMargin;
    int yMargin;
    int isThree[NUM_DIRECTION];
    int countThree = 0;
    int i;

    // Conditions that '3-3' never happen
    if (currentPlayer == STATE_WHITE
    || xPosition == 0 || xPosition == boardSize - 1
    || yPosition == 0 || yPosition == boardSize - 1
    || isGomoku(board, xPosition, yPosition, countMoves, boardSize) == true) {
        return false;
    }

    // Check how many 'opened 3-in-a-row' placed position has in four directions
    for (i = 0; i < NUM_DIRECTION; i++) {
        isThree[i] = false;
        switch (i) {                      
            case DIRECTION_NUMPAD1_TO_NUMPAD9: {
                xMargin = 1;
                yMargin = -1;

                break;
            }
            case DIRECTION_NUMPAD2_TO_NUMPAD8: {
                xMargin = 1;
                yMargin = 0;

                break;
            }
            case DIRECTION_NUMPAD3_TO_NUMPAD7: {
                xMargin = 1;
                yMargin = 1;

                break;
            }
            case DIRECTION_NUMPAD4_TO_NUMPAD6: {
                xMargin = 0;
                yMargin = 1;

                break;
            }
            default: {
                break;
            }
        }

        // Pattern '.AAA.'
        //            ^
        //      [PLACING HERE]
        if (xPosition >= 2 && xPosition <= (boardSize - 3)
        && yPosition >= 2 && yPosition <= (boardSize - 3)
        && board[yPosition - 2 * yMargin][xPosition - 2 * xMargin] == STATE_EMPTY
        && board[yPosition - 1 * yMargin][xPosition - 1 * xMargin] == currentPlayer
        && board[yPosition + 1 * yMargin][xPosition + 1 * xMargin] == currentPlayer
        && board[yPosition + 2 * yMargin][xPosition + 2 * xMargin] == STATE_EMPTY) {
            isThree[i] = true;
        }
        // Pattern '?.AAA.'
        //            ^
        //      [PLACING HERE]
        else if (xPosition >= 1 && xPosition <= (boardSize - 4)
        && yPosition >= 3 && yPosition <= (boardSize - 2)
        && board[yPosition - 1 * yMargin][xPosition - 1 * xMargin] == STATE_EMPTY
        && board[yPosition + 1 * yMargin][xPosition + 1 * xMargin] == currentPlayer
        && board[yPosition + 2 * yMargin][xPosition + 2 * xMargin] == currentPlayer
        && board[yPosition + 3 * yMargin][xPosition + 3 * xMargin] == STATE_EMPTY) {
            isThree[i] = true;
        }
        // Pattern '.AAA.?'
        //             ^
        //      [PLACING HERE]
        else if (xPosition >= 3 && xPosition <= (boardSize - 2)
        && yPosition >= 1 && yPosition <= (boardSize - 4)
        && board[yPosition - 3 * yMargin][xPosition - 3 * xMargin] == STATE_EMPTY
        && board[yPosition - 2 * yMargin][xPosition - 2 * xMargin] == currentPlayer
        && board[yPosition - 1 * yMargin][xPosition - 1 * xMargin] == currentPlayer
        && board[yPosition + 1 * yMargin][xPosition + 1 * xMargin] == STATE_EMPTY) {
            isThree[i] = true;
        }
    }

    for (i = 0; i <= NUM_DIRECTION; i++) {
        countThree += isThree[i];
    }

    if (countThree == 2) { // Ban position if there are two 'opened 3-in-a-row'
        return true;
    } else {
        return false;
    }
}

// IsGomoku() - Check if there is victory
int isGomoku(int **board, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int lineStack[NUM_DIRECTION];
    int i;
    int j;

    for (i = 0; i < NUM_DIRECTION; i++) {
        switch (i) {
            case DIRECTION_NUMPAD1_TO_NUMPAD9: {
                lineStack[i] = 1;
                j = 1;

                while (isPositionInBoard(xPosition - j, yPosition + j, boardSize) == true
                && board[yPosition + j][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (isPositionInBoard(xPosition + j, yPosition - j, boardSize) == true
                && board[yPosition - j][xPosition + j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD2_TO_NUMPAD8: {
                lineStack[i] = 1;
                j = 1;

                while (isPositionInBoard(xPosition, yPosition + j, boardSize) == true
                && board[yPosition + j][xPosition] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (isPositionInBoard(xPosition, yPosition - j, boardSize) == true
                && board[yPosition - j][xPosition] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD3_TO_NUMPAD7: {
                lineStack[i] = 1;
                j = 1;

                while (isPositionInBoard(xPosition + j, yPosition + j, boardSize) == true
                && board[yPosition + j][xPosition + j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (isPositionInBoard(xPosition - j, yPosition - j, boardSize) == true
                && board[yPosition - j][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD4_TO_NUMPAD6: {
                lineStack[i] = 1;
                j = 1;

                while (isPositionInBoard(xPosition - j, yPosition, boardSize) == true
                && board[yPosition][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (isPositionInBoard(xPosition + j, yPosition, boardSize) == true
                && board[yPosition][xPosition + j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            default: {
                break;
            }
        }
    }

    for (i = 0; i < NUM_DIRECTION; i++) {
        if (lineStack[i] == 5) {
            return true;
        }
    }

    return false;
}

int isDrawGame(int countMove, int boardSize) {
    return (countMove >= (boardSize * boardSize - 1));
}
