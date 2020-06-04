#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Macros
#define MAX_NUM_NODE 15
#define STACK_SIZE 15
#define MAX_NAME_LENGTH 9 // 8-byte character + 1-byte null character
#define MAX_AGE 200
#define MIN_AGE 0
#define MAX_OUTPUT_FILE_NAME_LENGTH 21 // 20-byte character + 1-byte null character

enum boolean {
    false,
    true
};

// Type definitions
typedef struct Data {
    char *name;
    unsigned int age;
    char gender;
} Data;

typedef struct Node {
    Data data;
    unsigned int nodeNo;
    struct Node* left_child;
    struct Node* right_sibling;
} Node;

// Global variables
Node* Head;
Node* Current;
Node* EntireList[MAX_NUM_NODE];
static Node* Stack[STACK_SIZE];
int Top = -1;

// Function prototypes
int PrintMenu(void);
Node* CreateNode(void);
Data CreateData(void);
void StoreAllNodesToList(Node*);
void PrintCurrentTree(void);
void PrintCurrentNode(Node*);
void AddChildNode(Node*);
void AddSiblingNode(Node*);
Node* SearchNode(int);
void CreateOutputFile(char*);
void ResetList(void);
int IsStackEmpty(void);
int IsStackFull(void);
void PushStack(Node*);
Node* PopStack(void);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main(void) {
    int menuSelection = 0;
    int nodeNoToMakeCurrent = 0;
    char outputFileName[MAX_OUTPUT_FILE_NAME_LENGTH];
    Node* temp = NULL;

    setbuf(stdout, NULL);
    printf("Input data of head node.\n");

    Head = CreateNode();
    Current = Head;

    while (menuSelection != 6) {
        ResetList();

        menuSelection = PrintMenu();

        switch (menuSelection) {
            case 1: {
                PrintCurrentTree();

                break;
            }
            case 2: {
                AddChildNode(Current);

                break;
            }
            case 3: {
                AddSiblingNode(Current);

                break;
            }
            case 4: {
                printf("Input node number to make it current.:");
                scanf("%d", &nodeNoToMakeCurrent);
                fflush(stdin);

                temp = Current;
                Current = SearchNode(nodeNoToMakeCurrent);

                if (Current == NULL) {
                    printf("Can't search node with that number.\n");

                    Current = temp;
                }

                break;
            }
            case 5: {
                while (true) { // File name input session
                    printf("Input output file name. (maximum [%d]-byte):", MAX_OUTPUT_FILE_NAME_LENGTH - 1);
                    scanf("%s", outputFileName);
                    fflush(stdin);

                    if (strlen(outputFileName) > 0 && strlen(outputFileName) < MAX_OUTPUT_FILE_NAME_LENGTH) {
                        // Validate output file name
                        break;
                    }
                    else {
                        printf("Invalid name.\n");
                    }
                }

                CreateOutputFile(outputFileName);
                printf("Created output file.\n");

                break;
            }
            case 6: {
                break;
            }
            default: {
                printf("Wrong number.\n");

                break;
            }
        }
    }

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// PrintMenu() - Print menu. Scan menu selection number from user. Return menu selection number
int PrintMenu(void) {
    int menuSelection;

    printf("---------------------------------------------------------------\n");
    printf("1. Print current tree\n");
    printf("2. Add left(child) node to current node\n");
    printf("3. Add right(sibling) node to current node\n");
    printf("4. Change current node\n");
    printf("5. Create output file\n");
    printf("6. Exit\n");
    printf("---------------------------------------------------------------\n");
    printf("Current Node Information\n");
    printf("+--------+----------+-----+--------+------------+-------------+\n");
    printf("| nodeNo |   name   | age | gender | leftNodeNo | rightNodeNo |\n");
    PrintCurrentNode(Current);
    printf("+--------+----------+-----+--------+------------+-------------+\n");
    printf("\n");
    printf("Input menu selection ([1-6]):");
    scanf("%d", &menuSelection);
    fflush(stdin);
    system("cls");

    return menuSelection;
}

// CreateNode() - Create LCRS tree node. Initialize members of node structure. Return node for 'Node*' type
Node* CreateNode(void) {
    Node* node = (Node*)malloc(sizeof(Node));
    static int nodeNo; // Initialize node number. Perform only once
    node->data = CreateData();
    node->nodeNo = nodeNo++;
    node->left_child = NULL;
    node->right_sibling = NULL;

    return node;
}

// CreateData() - Scan node data from user. Return data for 'Data' type
Data CreateData(void) {
    Data data;
    data.name = (char*)malloc(MAX_NAME_LENGTH * sizeof(char));
    char *bufferName = (char*)malloc(MAX_NAME_LENGTH * sizeof(char));
    int bufferAge;
    char bufferGender;

    while (true) { // Name input session
        printf("Input name. (maximum [%d]-byte):", MAX_NAME_LENGTH - 1);
        scanf("%s", bufferName);
        fflush(stdin);

        if (strlen(bufferName) > 0 && strlen(bufferName) < MAX_NAME_LENGTH) { // Validate name input
            break;
        }
        else {
            printf("Invalid name.\n");
        }
    }

    while (true) { // Age input session
        printf("Input age. ([%d-%d]):", MIN_AGE, MAX_AGE);
        scanf("%d", &bufferAge);
        fflush(stdin);

        if (bufferAge >= MIN_AGE && bufferAge <= MAX_AGE) { // Validate age input
            break;
        }
        else {
            printf("Invalid age.\n");
        }
    }

    while (true) { // Gender input session
        printf("Input gender. ('M' or 'F'):");

        bufferGender = (char)getchar();

        fflush(stdin);

        bufferGender = (char)toupper(bufferGender);

        if (bufferGender == 'M' || bufferGender == 'F') { // Validate gender input
            break;
        }
        else {
            printf("Invalid gender.\n");
        }
    }

    strcpy(data.name, bufferName); // Buffer data
    free(bufferName);

    data.age = bufferAge;
    data.gender = bufferGender;

    return data;
}

// StoreAllNodesToList() - Store all nodes in LCRS tree to node list by using breadth-first search(BFS)
void StoreAllNodesToList(Node *node) {
    static int listIndex; // Initialize index of node list. Perform only once
    EntireList[listIndex++] = node;

    if (node->left_child != NULL) { // Current node has child node
        PushStack(node->left_child);
    }

    if (node->right_sibling != NULL) { // Current node has sibling node
        StoreAllNodesToList(node->right_sibling); // Recursion step case 1. Move to sibling(same breadth-layer) node
    }
    else if (IsStackEmpty() == false) { // BFS dead-end. No more sibling on same breadth-layer
        StoreAllNodesToList(PopStack()); // Recursion step case 2. Move to child node(increment layer)
    }
    else { // Last node. End recursion
        listIndex = 0;
    }
}

// PrintCurrentTree() - Print all nodes in LCRS tree, in order of node list stored by BFS
void PrintCurrentTree() {
    int i = 0;

    StoreAllNodesToList(Head);
    printf("+--------+----------+-----+--------+------------+-------------+\n");
    printf("| nodeNo |   name   | age | gender | leftNodeNo | rightNodeNo |\n");

    while (EntireList[i] != NULL) {
        PrintCurrentNode(EntireList[i]);

        i++;
    }

    printf("+--------+----------+-----+--------+------------+-------------+\n");
}

// PrintCurrentNode() - Print current node information
void PrintCurrentNode(Node *node) {
    unsigned int leftNodeNo;
    unsigned int rightNodeNo;

    printf("+--------+----------+-----+--------+------------+-------------+\n");

    if (node->left_child == NULL) {
        leftNodeNo = -1;
    }
    else {
        leftNodeNo = node->left_child->nodeNo;
    }

    if (node->right_sibling == NULL) {
        rightNodeNo = -1;
    }
    else {
        rightNodeNo = node->right_sibling->nodeNo;
    }

    printf("| %6d | %8s | %3d | %6c | %10d | %11d |\n",
           node->nodeNo,
           node->data.name,
           node->data.age,
           node->data.gender,
           leftNodeNo,
           rightNodeNo);
}

// AddChildNode() - Add child node of current node. Make created child node current
void AddChildNode(Node* node) {
    node->left_child = CreateNode();
    Current = node->left_child;
}

// AddSiblingNode() - Add sibling node of current node. Make created sibling node current
void AddSiblingNode(Node* node) {
    node->right_sibling = CreateNode();
    Current = node->right_sibling;
}

// SearchNode() - Search and return node which has same number with argument number
Node* SearchNode(int nodeNo) {
    Node* temp = NULL;
    int i = 0;

    if (nodeNo < 0 || nodeNo >= MAX_NUM_NODE) {
        return NULL;
    }

    StoreAllNodesToList(Head);

    while (EntireList[i] != NULL) {
        if (nodeNo == EntireList[i]->nodeNo) {
            temp = EntireList[i];

            break;
        }

        i++;
    }

    return temp;
}

// ResetList() - Reinitialize node list
void ResetList() {
    memset(EntireList, 0, sizeof(int) * MAX_NUM_NODE);
}

// CreateOutputFile() - Create output file which has name of argument
void CreateOutputFile(char* name) {
    FILE* file = fopen(name, "w");
    unsigned int leftNodeNo;
    unsigned int rightNodeNo;
    int i = 0;

    if (file == NULL) {
        printf("Can't open file with \'w\' option.\n");
        return;
    }

    fprintf(file, "[nodeNo, name, age, gender, leftNodeNo, rightNodeNo]\n");
    StoreAllNodesToList(Head);

    while (EntireList[i] != NULL) {
        if (EntireList[i]->left_child == NULL) {
            leftNodeNo = -1;
        }
        else {
            leftNodeNo = EntireList[i]->left_child->nodeNo;
        }

        if (EntireList[i]->right_sibling == NULL) {
            rightNodeNo = -1;
        }
        else {
            rightNodeNo = EntireList[i]->right_sibling->nodeNo;
        }

        fprintf(file, "[%d, %s, %d, %c, %d, %d]\n",
                EntireList[i]->nodeNo,
                EntireList[i]->data.name,
                EntireList[i]->data.age,
                EntireList[i]->data.gender,
                leftNodeNo,
                rightNodeNo);

        i++;
    }

    fclose(file);
}

// IsStackEmpty() - Check if node stack is empty
int IsStackEmpty() {
    return (Top < 0);
}

// IsStackFull() - Check if node stack is full
int IsStackFull() {
    return (Top >= STACK_SIZE - 1);
}

// PushStack() - Push argument node in node stack
void PushStack(Node* node) {
    if (IsStackFull() == true) {
        printf("Stack is now full. Check your tree.\n");
    }
    else {
        Stack[++Top] = node;
    }
}

// PopStack() - Pop node out which is on top of node stack
Node* PopStack() {
    if (IsStackEmpty() == true) {
        return NULL;
    }

    return Stack[Top--];
}
