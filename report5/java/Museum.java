class Museum {
    private int numCurrentVisitor;
    private int numTotalVisitor;
    private int sales;

    // Constructor
    Museum() {
        numCurrentVisitor = 0;
        numTotalVisitor = 0;
        sales = 0;
    }

    // Class methods
    // enter() - Decide whether visitor can get in
    void enter(Visitor visitor) {
        if (visitor.isEnterable()) {
            admit(visitor);
        } else {
            refuse(visitor);
        }
    }

    // admit() - Let visitor get in
    void admit(Visitor visitor) {
        numCurrentVisitor++;
        numTotalVisitor++;
        sales += visitor.getFeeToPay();

        visitor.pay();
        System.out.print("Visitor admission - Age: " + visitor.getAge() + " / ");

        if (!visitor.isMember()) {
            System.out.print("Non-");
        }

        System.out.println("Member / Paid " + visitor.getFeeToPay() + "$ as admission fee");
        printNumVisitor();
        printLine();
    }

    // refuse() - Don't let visitor get in
    void refuse(Visitor visitor) {
        System.out.print("Admission denied: No membership or lack of money.");
        System.out.println("(Money: " + visitor.getMoney() + "$ / Fee: " + visitor.getFeeToPay() + "$)");
        printLine();
    }

    // sendout() - Let visitor get out
    void sendout(Visitor visitor) {
        numCurrentVisitor--;

        System.out.print("Visitor exit - Age: " + visitor.getAge() + " / ");

        if (!visitor.isMember()) {
            System.out.print("Non-");
        }

        System.out.println("Member");
        printNumVisitor();
        printLine();
    }

    // printNumVisitor() - Print each number of current visitors, total visitors
    void printNumVisitor() {
        System.out.println("Current visitors: " + numCurrentVisitor + " / Total visitors: " + numTotalVisitor);
    }

    // printLine() - For decoration
    void printLine() {
        System.out.println("---");
    }

    // printSales() - Print daily sales
    void printSales() {
        System.out.println("Total sales is " + sales + "$.");
        printLine();
    }
}
