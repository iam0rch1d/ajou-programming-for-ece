#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>

// Macros
#define TIME_SLEEP 1000

// Global variables
unsigned char* gpioModeRegister;
unsigned char* gpioInputRegister;
unsigned char* gpioOutputRegister;
unsigned char switch0;
unsigned char switch1;
unsigned char diode0;
unsigned char diode1;

// Function prototypes
void init(void);
void printMenu(void);
unsigned int setMenuSelection(void);
void printCurrentStatus(void);
unsigned int checkGpioPin(const unsigned char* gpioRegister, unsigned int pinNo);
void setGpioPinTrue(unsigned char* gpioRegister, unsigned int pinNo);
void setGpioPinFalse(unsigned char* gpioRegister, unsigned int pinNo);
void update(void);
void setGpioMode(void);
void setGpioOutputVoltage(void);
void setSwitch(void);

//----------------------------------------------------------------------------------------------------------------------
// Main function
int main(void) {
    init();

    unsigned int menuSelection = 0;

    while (menuSelection != 4) {
        system("cls"); // Clean screen
        update();
        printCurrentStatus();
        printMenu();

        menuSelection = setMenuSelection();

        switch (menuSelection) {
            case 1: {
                setGpioMode();
                
                break;
            }
            case 2: {
                setGpioOutputVoltage();

                break;
            }
            case 3: {
                setSwitch();

                break;
            }
            case 4: {
                break;
            }
            default: {
                printf("Option does not exist.\n");
                Sleep(TIME_SLEEP);
                
                break;
            }
        }
    }

    free(gpioModeRegister); // Release dynamic memory allocations
    free(gpioInputRegister);
    free(gpioOutputRegister);

    return 0;
}
//----------------------------------------------------------------------------------------------------------------------
// Functions

// init() - Initialize GPIO registers
void init(void) {
    gpioModeRegister = (unsigned char*)malloc(sizeof(unsigned char));
    gpioInputRegister = (unsigned char*)malloc(sizeof(unsigned char));
    gpioOutputRegister = (unsigned char*)malloc(sizeof(unsigned char));

    *gpioModeRegister = 0x44;   // 0b01000100
    *gpioInputRegister = 0x00;  // 0b00000000
    *gpioOutputRegister = 0x00; // 0b00000000
}

// printMenu() - User interface about menu selection
void printMenu(void) {
    printf("----------------------------------------\n");
    printf("1. Set GPIO Mode (Input / Output)\n");
    printf("2. Set GPIO Output Voltage (0V / 5V)\n");
    printf("3. Set Switch Mode (On / Off)\n");
    printf("4. Exit\n");
    printf("----------------------------------------\n\n");
    printf("Select option:");
}

// setMenuSelection() - Scan menu selection from user
unsigned int setMenuSelection(void) {
    unsigned int menuSelection;

    scanf("%d", &menuSelection);
    printf("\n");

    return menuSelection;
}

// printCurrentStatus() - User interface about GPIO register table, diodes, switches
void printCurrentStatus(void) {
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO LIST   | GPIO 7 | GPIO 6 | GPIO 5 | GPIO 4 | GPIO 3 | GPIO 2 | GPIO 1 | GPIO 0 |\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO MODE   |");

    for (int i = 7; i >= 0; i--) {
        if (checkGpioPin(gpioModeRegister, i) == 0) {
            printf("   IN   |");
        }
        else {
            printf("  OUT   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO INPUT  |");

    for (int i = 7; i >= 0; i--) {
        if (checkGpioPin(gpioInputRegister, i) == 0) {
            printf("   0V   |");
        }
        else {
            printf("   5V   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO OUTPUT |");

    for (int i = 7; i >= 0; i--) {
        if (checkGpioPin(gpioOutputRegister, i) == 0) {
            printf("   0V   |");
        }
        else {
            printf("   5V   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("diode0 : ");

    if (diode0 == 0) {
        printf("OFF\n");
    }
    else if (diode0 == 1) {
        printf("ON\n");
    }

    printf("diode1 : ");

    if (diode1 == 0) {
        printf("OFF\n");
    }
    else if (diode1 == 1) {
        printf("ON\n");
    }

    printf("switch0 : ");

    if (switch0 == 0) {
        printf("OFF\n");
    }
    else if (switch0 == 1) {
        printf("ON\n");
    }

    printf("switch1 : ");

    if (switch1 == 0) {
        printf("OFF\n");
    }
    else if (switch1 == 1) {
        printf("ON\n");
    }
}

// checkGpioPin() - Check boolean state of single GPIO register pin
unsigned int checkGpioPin(const unsigned char* gpioRegister, unsigned int pinNo) {
    return (*gpioRegister & ((unsigned int)1 << pinNo)) >> pinNo;
}

// setGpioPinTrue() - Set state of single GPIO register pin to '1'
void setGpioPinTrue(unsigned char* gpioRegister, unsigned int pinNo) {
    *gpioRegister |= ((unsigned int)1 << pinNo);
}

// setGpioPinFalse() - Set state of single GPIO register pin to '0'
void setGpioPinFalse(unsigned char* gpioRegister, unsigned int pinNo) {
    *gpioRegister = ~(*gpioRegister);
    *gpioRegister |= ((unsigned int)1 << pinNo);
    *gpioRegister = ~(*gpioRegister);
}

// update() - Update all status of GPIO registers, global variables
void update(void) {
    // GPIO input register pin 1 condition
    if (switch0 == 1 && checkGpioPin(gpioModeRegister, 0) == 1 && checkGpioPin(gpioModeRegister, 1) == 0
    && checkGpioPin(gpioOutputRegister, 0) == 1) {
        setGpioPinTrue(gpioInputRegister, 1);
    }
    else {
        setGpioPinFalse(gpioInputRegister, 1);
    }

    // GPIO output register pin 2 condition
    if (checkGpioPin(gpioInputRegister, 1) == 1) {
        setGpioPinTrue(gpioOutputRegister, 2);
    }
    else {
        setGpioPinFalse(gpioOutputRegister, 2);
    }

    // diode0 condition
    if (checkGpioPin(gpioOutputRegister, 2) == 1 && (checkGpioPin(gpioModeRegister, 3) == 0
    || checkGpioPin(gpioOutputRegister, 3) == 0)) {
        diode0 = 1;
    }
    else {
        diode0 = 0;
    }

    // GPIO input register pin 5 condition
    if (switch1 == 1 && checkGpioPin(gpioModeRegister, 4) == 1 && checkGpioPin(gpioModeRegister, 5) == 0
    && checkGpioPin(gpioOutputRegister, 4) == 1) {
        setGpioPinTrue(gpioInputRegister, 5);
    }
    else {
        setGpioPinFalse(gpioInputRegister, 5);
    }

    // GPIO output register pin 6 condition
    if (checkGpioPin(gpioInputRegister, 5) == 1) {
        setGpioPinTrue(gpioOutputRegister, 6);
    }
    else {
        setGpioPinFalse(gpioOutputRegister, 6);
    }

    // diode1 condition
    if (checkGpioPin(gpioOutputRegister, 6) == 1 && (checkGpioPin(gpioModeRegister, 7) == 0
    || checkGpioPin(gpioOutputRegister, 7) == 0)) {
        diode1 = 1;
    }
    else {
        diode1 = 0;
    }

    // All other conditions are 'don't care'
}

// setGpioMode() - Function performed when selected menu is '1'
void setGpioMode(void) {
    unsigned int pinNo = 0;
    unsigned int modeIO = 0;

    printf("Select GPIO pin. ([0-7], except [2, 6]):");
    scanf("%d", &pinNo);
    printf("\n");

    pinNo &= (unsigned int)0x00000007; // Bit masking

    if (pinNo == 2 || pinNo == 6) {
        printf("Can't select GPIO pin %d. Select again.\n", pinNo);
        Sleep(TIME_SLEEP);

        return;
    }

    printf("Set GPIO pin mode. ([0] = Input / [1] = Output):");
    scanf("%d", &modeIO);
    printf("\n");

    if (modeIO == 0) {
        setGpioPinFalse(gpioModeRegister, pinNo);
    }
    else {
        setGpioPinTrue(gpioModeRegister, pinNo);
    }
}

// setGpioOutputVoltage() - Function performed when selected menu is '2'
void setGpioOutputVoltage(void) {
    unsigned int pinNo = 0;
    unsigned int powerOnOff = 0;

    printf("Select GPIO pin. ([0-7], except [2, 6]):");
    scanf("%d", &pinNo);
    printf("\n");

    pinNo &= (unsigned int)0x00000007; // Bit masking

    if (pinNo == 2 || pinNo == 6) {
        printf("Can't select GPIO pin %d. Select again.\n", pinNo);
        Sleep(TIME_SLEEP);

        return;
    }

    printf("Set voltage level. ([0] = 0V / [1] = 5V):");
    scanf("%d", &powerOnOff);
    printf("\n");

    powerOnOff &= (unsigned int)0x00000001; // Bit masking

    if (powerOnOff == 0) {
        setGpioPinFalse(gpioOutputRegister, pinNo);
    }
    else {
        setGpioPinTrue(gpioOutputRegister, pinNo);
    }
}

// setSwitch() - Function performed when selected menu is '3'
void setSwitch(void) {
    unsigned int switchNo = 0;
    unsigned int powerOnOff = 0;

    printf("Select switch. ([0] = switch0 / [1] = switch1):");
    scanf("%d", &switchNo);
    printf("\n");

    switchNo &= (unsigned int)0x00000001; // Bit masking

    printf("Set switch state. ([0] = Off / [1] = On):");
    scanf("%d", &powerOnOff);
    printf("\n");

    powerOnOff &= (unsigned int)0x00000001; // Bit masking

    if (switchNo == 0 && powerOnOff == 0) {
        switch0 = 0;
    }
    else if (switchNo == 0 && powerOnOff == 1) {
        switch0 = 1;
    }
    else if (switchNo == 1 && powerOnOff == 0) {
        switch1 = 0;
    }
    else {
        switch1 = 1;
    }
}
