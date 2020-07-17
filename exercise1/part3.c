#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>

// Macros
#define TIME_SLEEP 1000

enum boolean {
    false = 0,
    true
};

// Global variables
unsigned char *gpioModeRegister;
unsigned char *gpioInputRegister;
unsigned char *gpioOutputRegister;
unsigned char switch0;
unsigned char switch1;
unsigned char diode0;
unsigned char diode1;

// Function prototypes
void initialize(void);
void updateStatus(void);
void printCurrentStatus(void);
unsigned int isGpioPinTrue(const unsigned char *gpioRegister, unsigned int pinNo);
void setGpioPinTrue(unsigned char *gpioRegister, unsigned int pinNo);
void setGpioPinFalse(unsigned char *gpioRegister, unsigned int pinNo);
void setGpioMode(void);
void setGpioOutputVoltage(void);
void setSwitch(void);

// Main function
int main(void) {
    initialize();

    unsigned int menuSelection = 0;

    while (menuSelection != 4) {
        system("cls"); // Clean screen
        updateStatus();
        printCurrentStatus();
        printf("----------------------------------------\n");
        printf("1. Set GPIO Mode (Input / Output)\n");
        printf("2. Set GPIO Output Voltage (0V / 5V)\n");
        printf("3. Set Switch (On / Off)\n");
        printf("4. Exit\n");
        printf("----------------------------------------\n\n");
        printf("Select option ([1-4]):");

        scanf("%d", &menuSelection);
        printf("\n");

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

    // Release dynamic memory allocations
    free(gpioModeRegister);
    free(gpioInputRegister);
    free(gpioOutputRegister);

    return 0;
}

// Functions
/**
 * void initialize(void)
 * Initializes GPIO registers.
 */
void initialize(void) {
    gpioModeRegister = (unsigned char *) malloc(sizeof(unsigned char));
    gpioInputRegister = (unsigned char *) malloc(sizeof(unsigned char));
    gpioOutputRegister = (unsigned char *) malloc(sizeof(unsigned char));

    *gpioModeRegister = 0x44;   // 0b01000100
    *gpioInputRegister = 0x00;  // 0b00000000
    *gpioOutputRegister = 0x00; // 0b00000000
}

/**
 * void printCurrentStatus(void)
 * Prints UI about GPIO register table, diodes, switches.
 */
void printCurrentStatus(void) {
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO LIST   | GPIO 7 | GPIO 6 | GPIO 5 | GPIO 4 | GPIO 3 | GPIO 2 | GPIO 1 | GPIO 0 |\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO MODE   |");

    for (int i = 7; i >= 0; i--) {
        if (isGpioPinTrue(gpioModeRegister, i) == false) {
            printf("   IN   |");
        } else {
            printf("  OUT   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO INPUT  |");

    for (int i = 7; i >= 0; i--) {
        if (isGpioPinTrue(gpioInputRegister, i) == false) {
            printf("   0V   |");
        } else {
            printf("   5V   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("| GPIO OUTPUT |");

    for (int i = 7; i >= 0; i--) {
        if (isGpioPinTrue(gpioOutputRegister, i) == false) {
            printf("   0V   |");
        } else {
            printf("   5V   |");
        }
    }

    printf("\n");
    printf("---------------------------------------------------------------------------------------\n");
    printf("diode0 : ");

    if (diode0 == false) {
        printf("OFF\n");
    } else if (diode0 == true) {
        printf("ON\n");
    }

    printf("diode1 : ");

    if (diode1 == false) {
        printf("OFF\n");
    } else if (diode1 == true) {
        printf("ON\n");
    }

    printf("switch0 : ");

    if (switch0 == false) {
        printf("OFF\n");
    } else if (switch0 == true) {
        printf("ON\n");
    }

    printf("switch1 : ");

    if (switch1 == false) {
        printf("OFF\n");
    } else if (switch1 == true) {
        printf("ON\n");
    }
}

/**
 * unsigned int isGpioPinTrue(const unsigned char *gpioRegister, unsigned int pinNo)
 * Checks if single GPIO register pin is [true].
 */
unsigned int isGpioPinTrue(const unsigned char *gpioRegister, unsigned int pinNo) {
    return (*gpioRegister & ((unsigned int) true << pinNo)) >> pinNo;
}

/**
 * void setGpioPinTrue(unsigned char *gpioRegister, unsigned int pinNo)
 * Sets state of single GPIO register pin to [true].
 */
void setGpioPinTrue(unsigned char *gpioRegister, unsigned int pinNo) {
    *gpioRegister |= ((unsigned int) true << pinNo);
}

/**
 * void setGpioPinFalse(unsigned char *gpioRegister, unsigned int pinNo)
 * Sets state of single GPIO register pin to [false].
 */
void setGpioPinFalse(unsigned char *gpioRegister, unsigned int pinNo) {
    *gpioRegister = ~(*gpioRegister);
    *gpioRegister |= ((unsigned int) true << pinNo);
    *gpioRegister = ~(*gpioRegister);
}

/**
 * void updateStatus(void)
 * Updates all status of GPIO registers, global variables
 */
void updateStatus(void) {
    // GPIO input register pin 1 conditions
    if (switch0 == true
        && isGpioPinTrue(gpioModeRegister, 0) == true
        && isGpioPinTrue(gpioModeRegister, 1) == false
        && isGpioPinTrue(gpioOutputRegister, 0) == true
    ) {
        setGpioPinTrue(gpioInputRegister, 1);
    } else {
        setGpioPinFalse(gpioInputRegister, 1);
    }

    // GPIO output register pin 2 conditions
    if (isGpioPinTrue(gpioInputRegister, 1) == true) {
        setGpioPinTrue(gpioOutputRegister, 2);
    } else {
        setGpioPinFalse(gpioOutputRegister, 2);
    }

    // diode0 conditions
    if (isGpioPinTrue(gpioOutputRegister, 2) == true
        && (isGpioPinTrue(gpioModeRegister, 3) == false || isGpioPinTrue(gpioOutputRegister, 3) == false)
    ) {
        diode0 = true;
    } else {
        diode0 = false;
    }

    // GPIO input register pin 5 conditions
    if (switch1 == true
        && isGpioPinTrue(gpioModeRegister, 4) == true
        && isGpioPinTrue(gpioModeRegister, 5) == false
        && isGpioPinTrue(gpioOutputRegister, 4) == true
    ) {
        setGpioPinTrue(gpioInputRegister, 5);
    } else {
        setGpioPinFalse(gpioInputRegister, 5);
    }

    // GPIO output register pin 6 conditions
    if (isGpioPinTrue(gpioInputRegister, 5) == true) {
        setGpioPinTrue(gpioOutputRegister, 6);
    } else {
        setGpioPinFalse(gpioOutputRegister, 6);
    }

    // diode1 conditions
    if (isGpioPinTrue(gpioOutputRegister, 6) == true
        && (isGpioPinTrue(gpioModeRegister, 7) == false || isGpioPinTrue(gpioOutputRegister, 7) == false)
    ) {
        diode1 = true;
    } else {
        diode1 = false;
    }

    // All other conditions are 'don't care'
}

/**
 * void setGpioMode(void)
 * Function performed when selected menu is [1].
 */
void setGpioMode(void) {
    unsigned int pinNo = false;
    unsigned int gpioPinMode = false;

    printf("Select GPIO pin. ([0-7], except [2, 6]):");
    scanf("%d", &pinNo);
    printf("\n");

    pinNo &= (unsigned int) 0x00000007; // Bit masking

    if (pinNo == 2 || pinNo == 6) {
        printf("Can't select GPIO pin %d. Select again.\n", pinNo);
        Sleep(TIME_SLEEP);

        return;
    }

    printf("Set GPIO pin mode. ([0] - Input / [1] - Output):");
    scanf("%d", &gpioPinMode);
    printf("\n");

    if (gpioPinMode == false) {
        setGpioPinFalse(gpioModeRegister, pinNo);
    } else {
        setGpioPinTrue(gpioModeRegister, pinNo);
    }
}

/**
 * void setGpioOutputVoltage(void)
 * Function performed when selected menu is [2].
 */
void setGpioOutputVoltage(void) {
    unsigned int pinNo = false;
    unsigned int voltageLevel = false;

    printf("Select GPIO pin. ([0-7], except [2, 6]):");
    scanf("%d", &pinNo);
    printf("\n");

    pinNo &= (unsigned int) 0x00000007; // Bit masking

    if (pinNo == 2 || pinNo == 6) {
        printf("Can't select GPIO pin %d. Select again.\n", pinNo);
        Sleep(TIME_SLEEP);

        return;
    }

    printf("Set voltage level. ([0] - 0V / [1] - 5V):");
    scanf("%d", &voltageLevel);
    printf("\n");

    voltageLevel &= (unsigned int) 0x00000001; // Bit masking

    if (voltageLevel == false) {
        setGpioPinFalse(gpioOutputRegister, pinNo);
    } else {
        setGpioPinTrue(gpioOutputRegister, pinNo);
    }
}

/**
 * void setSwitch(void)
 * Function performed when selected menu is [3].
 */
void setSwitch(void) {
    unsigned int switchNo = false;
    unsigned int switchState = false;

    printf("Select switch. ([0] - switch0 / [1] - switch1):");
    scanf("%d", &switchNo);
    printf("\n");

    switchNo &= (unsigned int) 0x00000001; // Bit masking

    printf("Set switch state. ([0] - Off / [1] - On):");
    scanf("%d", &switchState);
    printf("\n");

    switchState &= (unsigned int) 0x00000001; // Bit masking

    if (switchNo == false && switchState == false) {
        switch0 = false;
    } else if (switchNo == false && switchState == true) {
        switch0 = true;
    } else if (switchNo == true && switchState == false) {
        switch1 = false;
    } else {
        switch1 = true;
    }
}
