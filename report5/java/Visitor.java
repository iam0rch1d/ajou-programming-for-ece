class Visitor {
    private final int age;
    private final boolean membership;
    private int money;
    private final int feeToPay;
    private final static int AGE_ADULT = 18;
    private final static int AGE_KID = 7;
    private final static int FEE_ADULT = 5;
    private final static int FEE_MINOR = 2;

    // Default constructor
    Visitor(int age, boolean membership, int money) {
        this.age = age;
        this.membership = membership;
        this.money = money;

        if (membership || isKid()) {
            feeToPay = 0;
        } else if (isAdult()) {
            feeToPay = FEE_ADULT;
        } else {
            feeToPay = FEE_MINOR;
        }
    }

    // Constructor overriding. Set default value of [membership] as [false]
    Visitor(int age, int money) {
        this(age, false, money);
    }

    // Getters
    int getAge() {
        return age;
    }

    boolean isMember() {
        return membership;
    }

    int getMoney() {
        return money;
    }

    int getFeeToPay() {
        return feeToPay;
    }

    // Class methods
    // isEnterable() - Check if visitor can enter
    boolean isEnterable() {
        return money - feeToPay >= 0;
    }

    // isAdult() - Check if visitor is adult
    boolean isAdult() {
        return age >= AGE_ADULT;
    }

    // isKid() - Check if visitor is kid
    boolean isKid() {
        return age <= AGE_KID;
    }

    // pay() - Give money to museum
    void pay() {
        money -= feeToPay;
    }
}
