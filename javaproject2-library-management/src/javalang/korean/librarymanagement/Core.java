package javalang.korean.librarymanagement;

import java.util.Scanner;
import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

public class Core {
    private static final Scanner scanner = new Scanner(System.in);
    static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("                        아주대학교 도서관 관리 프로그램");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("[1] - 회원 등록");
            System.out.println("[2] - 회원 목록 출력");
            System.out.println("[3] - 자료 등록");
            System.out.println("[4] - 자료 목록 출력");
            System.out.println("[5] - 도서관 정보 저장");
            System.out.println("[6] - 자료 대출");
            System.out.println("[7] - 자료 반납");
            System.out.println("[8] - 종료");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("메뉴를 선택하십시오. ([1-8]): ");

            try {
                int menuSelection = Integer.parseInt(scanner.nextLine());

                switch (menuSelection) {
                    case 1 -> library.addPerson(runUiCreatePersonInformation());
                    case 2 -> library.printPersonArrayList();
                    case 3 -> library.addCollection(runUiCreateCollectionInformation());
                    case 4 -> library.printCollectionArrayList();
                    case 5 -> library.saveLibrary();
                    case 6 -> library.borrowCollection(runUiSearchPerson(), runUiSearchCollection());
                    case 7 -> library.returnCollection(runUiSearchCollection());
                    case 8 -> {
                        return;
                    }
                    default -> System.out.println("존재하지 않는 메뉴입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("에러: 숫자 형태로 입력해 주십시오. (" + e.getClass().getName() + ")");
            } catch (PersonException | CollectionException e) {
                System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
            }
        }
    }

    private static Person runUiCreatePersonInformation() throws PersonException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("회원의 고유번호(uid)를 입력하십시오.: ");

        try {
            System.out.println("회원의 타입을 입력하십시오.");
            System.out.print("([s] 또는 [Student] - Student / [p] 또는 [Professor] - Professor): ");

            String className = scanner.nextLine();

            if (className.toLowerCase().equals("s")
                || className.toLowerCase().equals("student")) {
                className = "Student";
            } else if (className.toLowerCase().equals("p")
                || className.toLowerCase().equals("professor")) {
                className = "Professor";
            } else {
                throw new PersonException("회원 타입의 입력이 올바르지 않습니다.");
            }

            int uid = Integer.parseInt(scanner.nextLine());

            if (!library.isUidUnique(uid)) {
                throw new PersonException("중복된 고유번호(uid)가 존재합니다.");
            }

            System.out.print("회원의 이름을 입력하십시오.: ");

            String name = scanner.nextLine();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("다음 정보를 갖는 회원을 생성합니다.");
            System.out.println("회원 타입: " + className);
            System.out.println("고유번호(uid): " + uid);
            System.out.println("이름: " + name);

            if (className.equals("Student")) {
                return new Student(uid, name);
            } else {
                return new Professor(uid, name);
            }
        } catch (NumberFormatException e) {
            System.out.println("에러: 숫자 형태의 고유번호(uid)를 입력해 주십시오. (" + e.getClass().getName() + ")");
        }

        return null;
    }

    private static Collection runUiCreateCollectionInformation() throws CollectionException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("자료의 타입을 입력하십시오.");
        System.out.print("([b] 또는 [Book] - Book / [c] 또는 [ClassMaterial] - ClassMaterial): ");

        String className = scanner.nextLine();

        if (className.toLowerCase().equals("b")
            || className.toLowerCase().equals("book")) {
            className = "Book";
        } else if (className.toLowerCase().equals("c")
            || className.toLowerCase().equals("classmaterial")) {
            className = "ClassMaterial";
        } else {
            throw new CollectionException("자료 타입의 입력이 올바르지 않습니다.");
        }

        System.out.print("자료의 제목을 입력하십시오.: ");

        String title = scanner.nextLine();

        System.out.print("자료의 저자를 입력하십시오.: ");

        String author = scanner.nextLine();

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("다음 정보를 갖는 자료를 생성합니다.");
        System.out.println("자료 타입: " + className);
        System.out.println("제목: " + title);
        System.out.println("저자: " + author);

        if (className.equals("Book")) {
            return new Book(title, author);
        } else {
            return new ClassMaterial(title, author);
        }
    }

    private static Person runUiSearchPerson() throws PersonException {
        while (true) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("자료를 대출하려는 회원을 검색합니다. 메뉴를 선택하십시오. ([1-3])");
            System.out.print("([1] - 이름으로 검색, [2] - 고유번호(uid)로 검색, [3] - 취소): ");

            try {
                int menuSelection = Integer.parseInt(scanner.nextLine());

                switch (menuSelection) {
                    case 1 -> {
                        return library.runUiSearchPersonByName();
                    }
                    case 2 -> {
                        return library.runUiSearchPersonByUid();
                    }
                    case 3 -> throw new PersonException("회원 검색이 취소되었습니다. 자료 대출을 취소합니다.");
                    default -> System.out.println("존재하지 않는 메뉴입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("에러: 숫자 형태로 입력해 주십시오. (" + e.getClass().getName() + ")");
            }
        }
    }

    private static Collection runUiSearchCollection() throws CollectionException {
        while (true) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("대출할 자료를 검색합니다. 메뉴를 선택하십시오. ([1-2])");
            System.out.print("([1] - 제목으로 검색, [2] - 취소): ");

            try {
                int menuSelection = Integer.parseInt(scanner.nextLine());

                switch (menuSelection) {
                    case 1 -> {
                        return library.runUiSearchCollectionByTitle();
                    }
                    case 2 -> throw new CollectionException("자료 검색이 취소되었습니다. 자료 대출을 취소합니다.");
                    default -> System.out.println("존재하지 않는 메뉴입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("에러: 숫자 형태로 입력해 주십시오. (" + e.getClass().getName() + ")");
            }
        }
    }

    static String alignString(String string, int length) {
        StringBuilder stringBuilder = new StringBuilder();

        int stringLength = 0;

        // Count character bytes
        for (char element : string.toCharArray()) {
            if (stringLength <= length) {
                if (element <= 0x7f) { // 1-byte character
                    stringLength++;
                } else {
                    stringLength += 2;
                }

                stringBuilder.append(element);
            }
        }

        // Fill rest of [length] character bytes except [string] with whitespace character
        // If total character bytes of [string] exceeds [length], cut into [length] character bytes with '++' end
        if (stringLength <= length) {
            stringBuilder.append(" ".repeat(Math.max(0, length - stringLength)));
        } else {
            while (stringLength > length - 2) {
                char stringLastChar = stringBuilder.charAt(stringBuilder.length() - 1);
                if (stringLastChar <= 0x7f) {
                    stringLength--;
                } else {
                    stringLength -= 2;
                }

                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

            if ((stringLength % 2) == 1) {
                stringBuilder.append(" ");
            }

            stringBuilder.append("++");
        }

        return stringBuilder.toString();
    }
}
