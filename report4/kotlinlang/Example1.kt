/**
 * Example1.kt
 */

package kotlinlang

class StringList(stringArray: Array<String>) {
    private val list: MutableList<String> = ArrayList()
    private val listSize: Int
    private val numIteration: Int

    // Default constructor
    init {
        list.addAll(stringArray)
        listSize = list.size
        numIteration = listSize / 2;
    }

    // printList() - Print current string list
    fun printList() {
        for (element in list) {
            print("$element ")
        }

        println()
    }

    // reverseList() - Reverse element order in list
    fun reverseList() {
        var temp: String

        for (i in 0 until numIteration) {
            temp = list[i]
            list[i] = list[listSize - i - 1]
            list[listSize - i - 1] = temp
        }
    }
}


fun main() {
    val region = arrayOf("Seoul", "Daejeon", "Daegu", "Kwangju", "Incheon", "Jeju", "Busan")
    val stringList = StringList(region)

    print("Before: ")
    stringList.printList()
    region[2] = "Jinju" // Test value protection of [kotlinlang.StringList] class member
    stringList.reverseList()
    print("After: ")
    stringList.printList()
}
