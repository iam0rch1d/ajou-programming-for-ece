// Example2.kt
class Visitor(private val age: Int, private val membership: Boolean, private var money: Int) {
    constructor(age: Int, money: Int) : this(age, false, money)

    private val feeToPay: Int

    private companion object {
        private const val AGE_ADULT = 18
        private const val AGE_KID = 7
        private const val FEE_ADULT = 5
        private const val FEE_MINOR = 2
    }

    // Default constructor
    init {
        feeToPay = if (membership || isKid()) {
            0
        } else if (isAdult()) {
            FEE_ADULT
        } else {
            FEE_MINOR
        }
    }

    // Getters
    fun getAge(): Int {
        return age
    }

    fun isMember(): Boolean {
        return membership
    }

    fun getMoney(): Int {
        return money
    }

    fun getFeeToPay(): Int {
        return feeToPay
    }

    // Class methods
    // isEnterable() - Check if visitor can enter
    fun isEnterable(): Boolean {
        return money - feeToPay >= 0
    }

    // isAdult() - Check if visitor is adult
    private fun isAdult(): Boolean {
        return age >= AGE_ADULT
    }

    // isKid() - Check if visitor is kid
    private fun isKid(): Boolean {
        return age <= AGE_KID
    }

    // pay() - Give money to museum
    fun pay() {
        money -= feeToPay
    }
}


class Museum {
    // Members
    private var numCurrentVisitor = 0
    private var numTotalVisitor = 0
    private var sales = 0

    // Class methods
    // enter() - Decide whether visitor can get in
    fun enter(visitor: Visitor) {
        if (visitor.isEnterable()) {
            admit(visitor)
        } else {
            refuse(visitor)
        }
    }

    // admit() - Let visitor get in
    private fun admit(visitor: Visitor) {
        numCurrentVisitor++
        numTotalVisitor++
        sales += visitor.getFeeToPay()

        visitor.pay()
        print("Visitor admission - Age: " + visitor.getAge() + " / ")

        if (!visitor.isMember()) {
            print("Non-")
        }

        println("Member / Paid " + visitor.getFeeToPay() + "$ as admission fee")
        printNumVisitor()
        printLine()
    }

    // refuse() - Don't let visitor get in
    private fun refuse(visitor: Visitor) {
        print("Admission denied: No membership or lack of money.")
        println("(Money: " + visitor.getMoney() + "$ / Fee: " + visitor.getFeeToPay() + "$)")
        printLine()
    }

    // sendout() - Let visitor get out
    fun sendout(visitor: Visitor) {
        numCurrentVisitor--

        print("Visitor exit - Age: " + visitor.getAge() + " / ")

        if (!visitor.isMember()) {
            print("Non-")
        }

        println("Member")
        printNumVisitor()
        printLine()
    }

    // printNumVisitor() - Print each number of current visitors, total visitors
    private fun printNumVisitor() {
        println("Current visitors: $numCurrentVisitor / Total visitors: $numTotalVisitor")
    }

    // printLine() - For decoration
    private fun printLine() {
        println("---")
    }

    // printSales() - Print daily sales
    fun printSales() {
        println("Total sales is $sales$.")
        printLine()
    }
}


fun main() {
    val museum = Museum()
    val visitor1 = Visitor(25, 10)
    val visitor2 = Visitor(30, true, 200)
    val visitor3 = Visitor(14, 1)
    val visitor4 = Visitor(5, 10)
    val visitor5 = Visitor(17, true, 0)
    val visitor6 = Visitor(12, 10)

    museum.enter(visitor1)
    museum.enter(visitor2)
    museum.enter(visitor3)
    museum.sendout(visitor2)
    museum.enter(visitor4)
    museum.enter(visitor5)
    museum.sendout(visitor1)
    museum.enter(visitor6)
    museum.printSales()
}
