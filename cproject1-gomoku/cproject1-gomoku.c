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
void InitBoard(int**, int);
void PrintCurrentBoard(int**, int, int, int, int);
int IsPositionInBoard(int, int, int);
int IsPlaceable(int**, int, int, int, int);
int IsEmpty(int**, int, int);
int IsExplicitThreeAndThree(int**, int, int, int, int);
int IsGomoku(int**, int, int, int, int);
int IsDrawGame(int, int);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main(void) {
    int** arrBoard = NULL;
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

    arrBoard = (int**)malloc(boardSize * sizeof(int*)); // Allocate memory of board

    for (i = 0; i < boardSize; i++) { // Allocate memory of each line of board
        arrBoard[i] = (int*)malloc(boardSize * sizeof(int));
    }

    InitBoard(arrBoard, boardSize);

    CursorPosition.x = boardSize / 2; // Set initial cursor position
    CursorPosition.y = boardSize / 2;

    system("cls"); // Clean screen
    PrintCurrentBoard(arrBoard, CursorPosition.x, CursorPosition.y, countMoves, boardSize); // Print initial board

    do { // Gomoku game sequence
        while ((inputMoveCursor = (char)getch()) != '\r' ||                                          // Perform while
        IsPlaceable(arrBoard, CursorPosition.x, CursorPosition.y, countMoves, boardSize) == false) { // loop to get
                                                                                                     // input from user
                                                                                                     // until user
                                                                                                     // inputs carriage
                                                                                                     // return('\r') in
                                                                                                     // placeable point
            switch (inputMoveCursor) { // Move cursor by 'w', 'a', 's', 'd' input
                case 'w': { // Move cursor up
                    if (IsPositionInBoard(CursorPosition.x, CursorPosition.y - 1, boardSize) == true) {
                        CursorPosition.y--;
                    }

                    break;
                }
                case 'a': { // Move cursor left
                    if (IsPositionInBoard(CursorPosition.x - 1, CursorPosition.y, boardSize) == true) {
                        CursorPosition.x--;
                    }

                    break;
                }
                case 's': { // Move cursor down
                    if (IsPositionInBoard(CursorPosition.x, CursorPosition.y + 1, boardSize) == true) {
                        CursorPosition.y++;
                    }

                    break;
                }
                case 'd': { // Move cursor right
                    if (IsPositionInBoard(CursorPosition.x + 1, CursorPosition.y, boardSize) == true) {
                        CursorPosition.x++;
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            system("cls");
            PrintCurrentBoard(arrBoard, CursorPosition.x, CursorPosition.y, countMoves, boardSize); // Update board
        }

        currentPlayer = countMoves % NUM_PLAYER;
        arrBoard[CursorPosition.y][CursorPosition.x] = currentPlayer; // Place stone by carriage return('\r') input
    } while (IsGomoku(arrBoard, CursorPosition.x, CursorPosition.y, countMoves, boardSize) == false &&
             IsDrawGame(countMoves++, boardSize) == false);

    system("cls"); // Game sets
    PrintCurrentBoard(arrBoard, CursorPosition.x, CursorPosition.y, countMoves, boardSize);

    if (IsDrawGame(countMoves, boardSize) == true) {
        printf("Draw game\n");
    }
    else if (currentPlayer == STATE_BLACK) { // Print result
        printf("Black wins !\n");
    }
    else {
        printf("White wins !\n");
    }

    Sleep(TIME_SLEEP);

    for (i = 0; i < boardSize; i++) { // Release memory of each line of board
        free(arrBoard[i]);
    }

    free(arrBoard); // Release memory of board

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// InitBoard() - Initialize board
void InitBoard(int** arrBoard, int boardSize) {
    int i;
    int j;

    for (i = 0; i < boardSize; i++) {
        for (j = 0; j < boardSize; j++) {
            arrBoard[i][j] = STATE_EMPTY;
        }
    }
}

// PrintCurrentBoard() - Print current state of board
void PrintCurrentBoard(int** arrBoard, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int i;
    int j;

    for (i = 0; i < boardSize; i++) {
        printf("\n");

        for (j = 0; j < boardSize; j++) {
            if (i == yPosition && j == xPosition &&
                IsGomoku(arrBoard, xPosition, yPosition, countMoves, boardSize) == false) {
                printf(" @ ");
            }
            else {
                switch (arrBoard[i][j]) {
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
                        }
                        else {
                            printf(" . ");
                        }

                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        printf("\n");
    }

    printf("\n");

    if (currentPlayer == STATE_BLACK) {
        printf("Move No.%d\n", countMoves + 1);
        printf("Black's turn.\n");
    }
    else {
        printf("Move No.%d\n", countMoves + 1);
        printf("White's turn.\n");
    }

    if (IsExplicitThreeAndThree(arrBoard, xPosition, yPosition, countMoves, boardSize) == true) {
        printf("Banned move: Explicit 3-3\n");
    }
}

// IsPositionInBoard() - Check if move is in board
int IsPositionInBoard(int xPosition, int yPosition, int boardSize) {
    return (xPosition >= 0 && xPosition < boardSize && yPosition >= 0 && yPosition < boardSize);
}

// IsPlaceable() - Check if point is placeable. It should be empty and not '3-3' (for black) to place stone.
int IsPlaceable(int** arrBoard, int xPosition, int yPosition, int countMoves, int boardSize) {
    return (IsEmpty(arrBoard, xPosition, yPosition) == true &&
            IsExplicitThreeAndThree(arrBoard, xPosition, yPosition, countMoves, boardSize) == false);
}

// IsEmpty() - Check if point is empty
int IsEmpty(int** arrBoard, int xPosition, int yPosition) {
    return (arrBoard[yPosition][xPosition] == STATE_EMPTY);
}

// IsExplicitThreeAndThree() - Check if 'explicit 3-3' has occurred, only for black.
int IsExplicitThreeAndThree(int** arrBoard, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int isExplicitThree[NUM_DIRECTION];
    int countExplicitThree = 0;
    int i;

    if (currentPlayer == STATE_WHITE || // Conditions that 'explicit 3-3' never happen
        xPosition == 0 || xPosition == boardSize - 1 ||
        yPosition == 0 || yPosition == boardSize - 1 ||
        IsGomoku(arrBoard, xPosition, yPosition, countMoves, boardSize) == true) {
        return false;
    }

    for (i = 0; i < NUM_DIRECTION; i++) { // Check how many 'explicit opened 3-in-a-row' placed position has in four
        switch (i) {                      // directions
            case DIRECTION_NUMPAD1_TO_NUMPAD9: {
                isExplicitThree[DIRECTION_NUMPAD1_TO_NUMPAD9] = false;

                if (xPosition >= 2 && xPosition <= (boardSize - 3) &&          // Pattern '.AAA.'
                    yPosition >= 2 && yPosition <= (boardSize - 3) &&          //            ^
                    arrBoard[yPosition - 1][xPosition + 1] == currentPlayer && //      [PLACING HERE]
                    arrBoard[yPosition + 1][xPosition - 1] == currentPlayer &&
                    arrBoard[yPosition - 2][xPosition + 2] == STATE_EMPTY &&
                    arrBoard[yPosition + 2][xPosition - 2] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD1_TO_NUMPAD9] = true;
                }
                else if (xPosition >= 1 && xPosition <= (boardSize - 4) &&          // Pattern '?.AAA.'
                         yPosition >= 3 && yPosition <= (boardSize - 2) &&          //            ^
                         arrBoard[yPosition - 1][xPosition + 1] == currentPlayer && //      [PLACING HERE]
                         arrBoard[yPosition + 1][xPosition - 1] == STATE_EMPTY &&
                         arrBoard[yPosition - 2][xPosition + 2] == currentPlayer &&
                         arrBoard[yPosition - 3][xPosition + 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD1_TO_NUMPAD9] = true;
                }
                else if (xPosition >= 3 && xPosition <= (boardSize - 2) &&          // Pattern '.AAA.?'
                         yPosition >= 1 && yPosition <= (boardSize - 4) &&          //             ^
                         arrBoard[yPosition + 1][xPosition - 1] == currentPlayer && //      [PLACING HERE]
                         arrBoard[yPosition - 1][xPosition + 1] == STATE_EMPTY &&
                         arrBoard[yPosition + 2][xPosition - 2] == currentPlayer &&
                         arrBoard[yPosition + 3][xPosition - 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD1_TO_NUMPAD9] = true;
                }

                break;
            }
            case DIRECTION_NUMPAD2_TO_NUMPAD8: {
                isExplicitThree[DIRECTION_NUMPAD2_TO_NUMPAD8] = false;

                if (yPosition >= 2 && yPosition <= (boardSize - 3) &&      // Pattern '.AAA.'
                    arrBoard[yPosition - 1][xPosition] == currentPlayer && //            ^
                    arrBoard[yPosition + 1][xPosition] == currentPlayer && //      [PLACING HERE]
                    arrBoard[yPosition - 2][xPosition] == STATE_EMPTY &&
                    arrBoard[yPosition + 2][xPosition] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD2_TO_NUMPAD8] = true;
                }
                else if (yPosition >= 3 && yPosition <= (boardSize - 2) &&      // Pattern '?.AAA.'
                         arrBoard[yPosition - 1][xPosition] == currentPlayer && //            ^
                         arrBoard[yPosition + 1][xPosition] == STATE_EMPTY &&   //      [PLACING HERE]
                         arrBoard[yPosition - 2][xPosition] == currentPlayer &&
                         arrBoard[yPosition - 3][xPosition] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD2_TO_NUMPAD8] = true;
                }
                else if (yPosition >= 1 && yPosition <= (boardSize - 4) &&      // Pattern '.AAA.?'
                         arrBoard[yPosition + 1][xPosition] == currentPlayer && //             ^
                         arrBoard[yPosition - 1][xPosition] == STATE_EMPTY &&   //      [PLACING HERE]
                         arrBoard[yPosition + 2][xPosition] == currentPlayer &&
                         arrBoard[yPosition + 3][xPosition] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD2_TO_NUMPAD8] = true;
                }

                break;
            }
            case DIRECTION_NUMPAD3_TO_NUMPAD7: {
                isExplicitThree[DIRECTION_NUMPAD3_TO_NUMPAD7] = false;

                if (xPosition >= 2 && xPosition <= (boardSize - 3) &&          // Pattern '.AAA.'
                    yPosition >= 2 && yPosition <= (boardSize - 3) &&          //            ^
                    arrBoard[yPosition + 1][xPosition + 1] == currentPlayer && //      [PLACING HERE]
                    arrBoard[yPosition - 1][xPosition - 1] == currentPlayer &&
                    arrBoard[yPosition + 2][xPosition + 2] == STATE_EMPTY &&
                    arrBoard[yPosition - 2][xPosition - 2] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD3_TO_NUMPAD7] = true;
                }
                else if (xPosition >= 1 && xPosition <= (boardSize - 4) &&          // Pattern '?.AAA.'
                         yPosition >= 1 && yPosition <= (boardSize - 4) &&          //            ^
                         arrBoard[yPosition + 1][xPosition + 1] == currentPlayer && //      [PLACING HERE]
                         arrBoard[yPosition - 1][xPosition - 1] == STATE_EMPTY &&
                         arrBoard[yPosition + 2][xPosition + 2] == currentPlayer &&
                         arrBoard[yPosition + 3][xPosition + 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD3_TO_NUMPAD7] = true;
                }
                else if (xPosition >= 3 && xPosition <= (boardSize - 2) &&          // Pattern '.AAA.?'
                         yPosition >= 3 && yPosition <= (boardSize - 2) &&          //             ^
                         arrBoard[yPosition - 1][xPosition - 1] == currentPlayer && //      [PLACING HERE]
                         arrBoard[yPosition + 1][xPosition + 1] == STATE_EMPTY &&
                         arrBoard[yPosition - 2][xPosition - 2] == currentPlayer &&
                         arrBoard[yPosition - 3][xPosition - 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD3_TO_NUMPAD7] = true;
                }

                break;
            }
            case DIRECTION_NUMPAD4_TO_NUMPAD6: {
                isExplicitThree[DIRECTION_NUMPAD4_TO_NUMPAD6] = false;

                if (xPosition >= 2 && xPosition <= (boardSize - 3) &&      // Pattern '.AAA.'
                    arrBoard[yPosition][xPosition - 1] == currentPlayer && //            ^
                    arrBoard[yPosition][xPosition + 1] == currentPlayer && //      [PLACING HERE]
                    arrBoard[yPosition][xPosition - 2] == STATE_EMPTY &&
                    arrBoard[yPosition][xPosition + 2] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD4_TO_NUMPAD6] = true;
                }
                else if (xPosition >= 3 && xPosition <= (boardSize - 2) &&      // Pattern '?.AAA.'
                         arrBoard[yPosition][xPosition - 1] == currentPlayer && //            ^
                         arrBoard[yPosition][xPosition + 1] == STATE_EMPTY &&   //      [PLACING HERE]
                         arrBoard[yPosition][xPosition - 2] == currentPlayer &&
                         arrBoard[yPosition][xPosition - 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD4_TO_NUMPAD6] = true;
                }
                else if (xPosition >= 1 && xPosition <= (boardSize - 4) &&      // Pattern '.AAA.?'
                         arrBoard[yPosition][xPosition + 1] == currentPlayer && //             ^
                         arrBoard[yPosition][xPosition - 1] == STATE_EMPTY &&   //      [PLACING HERE]
                         arrBoard[yPosition][xPosition + 2] == currentPlayer &&
                         arrBoard[yPosition][xPosition + 3] == STATE_EMPTY) {
                    isExplicitThree[DIRECTION_NUMPAD4_TO_NUMPAD6] = true;
                }

                break;
            }
            default: {
                break;
            }
        }
    }

    for (i = 0; i <= NUM_DIRECTION; i++) {
        countExplicitThree += isExplicitThree[i];
    }

    if (countExplicitThree >= 2) { // Ban position if there are two 'explicit opened 3-in-a-row'
        return true;
    }
    else {
        return false;
    }
}

// IsGomoku() - Check if there is victory
int IsGomoku(int** arrBoard, int xPosition, int yPosition, int countMoves, int boardSize) {
    int currentPlayer = countMoves % NUM_PLAYER;
    int lineStack[NUM_DIRECTION];
    int i;
    int j;

    for (i = 0; i < NUM_DIRECTION; i++) {
        switch (i) {
            case DIRECTION_NUMPAD1_TO_NUMPAD9: {
                lineStack[i] = 1;
                j = 1;

                while (IsPositionInBoard(xPosition - j, yPosition + j, boardSize) == true &&
                       arrBoard[yPosition + j][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (IsPositionInBoard(xPosition + j, yPosition - j, boardSize) == true &&
                       arrBoard[yPosition - j][xPosition + j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD2_TO_NUMPAD8: {
                lineStack[i] = 1;
                j = 1;

                while (IsPositionInBoard(xPosition, yPosition + j, boardSize) == true &&
                       arrBoard[yPosition + j][xPosition] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (IsPositionInBoard(xPosition, yPosition - j, boardSize) == true &&
                       arrBoard[yPosition - j][xPosition] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD3_TO_NUMPAD7: {
                lineStack[i] = 1;
                j = 1;

                while (IsPositionInBoard(xPosition + j, yPosition + j, boardSize) == true &&
                       arrBoard[yPosition + j][xPosition + j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (IsPositionInBoard(xPosition - j, yPosition - j, boardSize) == true &&
                       arrBoard[yPosition - j][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                break;
            }
            case DIRECTION_NUMPAD4_TO_NUMPAD6: {
                lineStack[i] = 1;
                j = 1;

                while (IsPositionInBoard(xPosition - j, yPosition, boardSize) == true &&
                       arrBoard[yPosition][xPosition - j] == currentPlayer) {
                    lineStack[i]++;
                    j++;
                }

                j = 1;

                while (IsPositionInBoard(xPosition + j, yPosition, boardSize) == true &&
                       arrBoard[yPosition][xPosition + j] == currentPlayer) {
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

int IsDrawGame(int countMove, int boardSize) {
    return (countMove >= (boardSize * boardSize - 1));
}
