// Part1.kt
class Student(private val name: String?, private val age: Int?) {
    constructor(age: Int, name: String): this(name, age)
    constructor(name: String): this(name, null)
    constructor(age: Int): this(null, age)
    constructor(): this(null, null)

    // Class methods
    // printInformation() - Print student information
    fun printInformation() {
        if (name == null) {
            println("Unidentified student name.")
        } else {
            println("Student name is $name.")
        }

        if (age == null || age < 0) {
            println("Unidentified age.")
        } else {
            println("Student is $age year(s) old.")
        }

        println("---")
    }
}


fun main() {
    val student1 = Student()
    val student2 = Student(23, "Junyeong")
    val student3 = Student("Jaeho", 25)
    val student4 = Student(11)
    val student5 = Student("Hyerim")

    student1.printInformation()
    student2.printInformation()
    student3.printInformation()
    student4.printInformation()
    student5.printInformation()
}
