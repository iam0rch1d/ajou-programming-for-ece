package javalang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StringList {
    private final List<String> list = new ArrayList<>();
    private final int listSize;
    private final int numIteration;

    // Constructor
    StringList(String[] stringArray) {
        list.addAll(Arrays.asList(stringArray));
        listSize = list.size();
        numIteration = listSize / 2;
    }

    // Class methods
    // printList() - Print current string list
    void printList() {
        for (String element: list) {
            System.out.print(element + " ");
        }

        System.out.println();
    }

    // reverseList() - Reverse element order in list
    void reverseList() {
        int i;

        String temp;

        for (i = 0; i < numIteration; i++) {
            temp = list.get(i);
            list.set(i, list.get(listSize - i - 1));
            list.set(listSize - i - 1, temp);
        }
    }
}


class Example1 {
    public static void main(String[] args) {
        String[] region = {"Seoul", "Daejeon", "Daegu", "Kwangju", "Incheon", "Jeju", "Busan"};
        StringList stringList = new StringList(region);

        System.out.print("Before: ");
        stringList.printList();
        region[2] = "Jinju"; // Test value protection of [kotlinlang.StringList] class member
        stringList.reverseList();
        System.out.print("After: ");
        stringList.printList();
    }
}
