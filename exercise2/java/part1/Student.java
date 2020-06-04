class Student {
    private String name = "";
    private int age = -1;

    // Constructors
    Student(String name, int age) {
        this.age = age;
        this.name = name;
    }

    Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    Student(String name) {
        this.name = name;
    }

    Student(int age) {
        this.age = age;
    }

    Student() {
    }

    // Class methods
    // printInformation() - Print student information
    void printInformation() {
        if (this.name.equals("")) {
            System.out.println("Unidentified student name.");
        } else {
            System.out.println("Student name is " + this.name + ".");
        }

        if (this.age < 0) {
            System.out.println("Unidentified age.");
        } else {
            System.out.println("Student is " + this.age + " year(s) old.");
        }

        System.out.println("---");
    }

    // Destructor
    // WARNING: finalize() is deprecated method
    protected void finalize() {
        if (this.name.equals("")) {
            System.out.println("Data of student who has no name destructed.");
        } else {
            System.out.println("Data of " + this.name + " destructed.");
        }

        System.out.println("---");
    }
}
