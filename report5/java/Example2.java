class Example2 {
    public static void main(String[] args) {
        Museum museum = new Museum();
        Visitor visitor1 = new Visitor(25, 10);
        Visitor visitor2 = new Visitor(30, true, 200);
        Visitor visitor3 = new Visitor(14, 1);
        Visitor visitor4 = new Visitor(5, 10);
        Visitor visitor5 = new Visitor(17, true, 0);
        Visitor visitor6 = new Visitor(12, 10);

        museum.enter(visitor1);
        museum.enter(visitor2);
        museum.enter(visitor3);
        museum.sendout(visitor2);
        museum.enter(visitor4);
        museum.enter(visitor5);
        museum.sendout(visitor1);
        museum.enter(visitor6);
        museum.printSales();
    }
}
