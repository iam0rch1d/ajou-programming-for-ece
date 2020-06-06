package javalang.korean.librarymanagement;

import java.util.Scanner;
import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.Person;
import javalang.korean.librarymanagement.person.PersonException;
import javalang.korean.librarymanagement.person.Professor;
import javalang.korean.librarymanagement.person.Student;

public class Core {
    private static final Scanner scanner = new Scanner(System.in);
    static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---------------- 아주대학교 도서관 관리 페이지입니다 --------------");
            System.out.println("1. 회원 등록");
            System.out.println("2. 회원 목록 출력");
            System.out.println("3. 자료 등록");
            System.out.println("4. 자료 목록 출력");
            System.out.println("5. 도서관 정보 저장");
            System.out.println("6. 자료 대출");
            System.out.println("7. 자료 반납");
            System.out.println("8. 종료");
            System.out.println("--------------------------------------------------------");
            System.out.print("메뉴를 선택하십시오. ([1-8]): ");

            try {
                int menuSelection = Integer.parseInt(scanner.nextLine());

                switch (menuSelection) {
                    case 1 -> library.addPerson(runCreatePersonInformationUi());
                    case 2 -> library.printPersonArrayList();
                    case 3 -> library.addCollection(runCreateCollectionInformationUi());
                    case 4 -> library.printCollectionArrayList();
                    case 5 -> library.saveLibrary();
                    case 6 -> {
                        System.out.print("대출할 자료의 제목을 입력하세요: ");

                        String title = scanner.nextLine();

                        System.out.println("자료를 대출하려는 회원을 검색합니다. 메뉴를 선택하십시오. ([1] 또는 [2])");
                        System.out.print("([1] - 이름으로 검색, [2] - 고유번호(UID)로 검색): ");

                        try {
                            int submenuSelection = Integer.parseInt(scanner.nextLine());

                            switch (submenuSelection) {
                                case 1 : {
                                    System.out.print("자료를 대출할 사용자의 이름을 입력하세요: ");

                                    String name = library.searchByName(scanner.nextLine());
                                }
                                case 2 : {
                                    System.out.print("자료를 대출할 사용자의 고유번호를 입력하세요: ");
                                    int id = Integer.parseInt(scanner.nextLine());

                                    library.borrowCollection(title, id);
                                }
                            }
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println(
                                "에러: [1] 또는 [2]를 입력해 주십시오. ("
                                    + numberFormatException.getClass().getName()
                                    + ")"
                            );
                        }
                    }
                    case 7 -> {
                        System.out.println("이름으로 반납할 경우 1, 고유번호로 반납할 경우 2를 입력하세요: ");
                        int selectNum2 = Integer.parseInt(scanner.nextLine());
                        System.out.println("반납할 자료의 제목을 입력하세요 : ");
                        String title = scanner.nextLine();
                        switch (selectNum2) {
                            case 1 -> {
                                System.out.println("반납할 사용자의 이름을 입력하세요: ");
                                String name = scanner.nextLine();
                                library.returnCollection(title, name);
                            }
                            case 2 -> {
                                System.out.println("반납할 사용자의 고유번호를 입력하세요: ");
                                int id = Integer.parseInt(scanner.nextLine());
                                library.returnCollection(title, id);
                            }
                        }
                    }
                    case 8 -> {
                        return;
                    }
                    default -> System.out.println("존재하지 않는 메뉴입니다.");
                }
            } catch (NumberFormatException exception) {
                System.out.println(
                    "에러: [1-8] 사이의 숫자를 입력해 주십시오. (" + exception.getClass().getName() + ")"
                );
            } catch (PersonException | CollectionException exception) {
                System.out.println(
                    exception.getMessage() + " (" + exception.getClass().getName() + ")"
                );
            }
        }
    }

    private static Person runCreatePersonInformationUi() throws PersonException {
        try {
            System.out.print("회원의 고유번호(UID)를 입력하십시오.: ");
            int uid;

            while (true) {
                uid = Integer.parseInt(scanner.nextLine());

                if (library.isUidUnique(uid)) {
                    break;
                } else {
                    System.out.print("중복된 고유번호(UID)가 존재합니다. 다시 입력해 주십시오.: ");
                }
            }

            System.out.print("회원의 이름을 입력하십시오.: ");

            String name = scanner.nextLine();

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
                throw new PersonException("에러: 회원 타입의 입력이 올바르지 않습니다.");
            }

            System.out.println("생성할 회원의 정보는 다음과 같습니다.");
            System.out.println("고유번호(UID): " + uid);
            System.out.println("이름: " + name);
            System.out.println("타입: " + className);

            if (className.equals("Student")) {
                return new Student(uid, name);
            } else {
                return new Professor(uid, name);
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println(
                "에러: 숫자 형태의 고유번호(UID)를 입력해 주십시오. (" + numberFormatException.getClass().getName() + ")"
            );
        }

        return null;
    }

    private static Collection runCreateCollectionInformationUi() throws CollectionException {
        System.out.print("자료의 제목을 입력하십시오.: ");

        String title = scanner.nextLine();

        System.out.print("자료의 저자를 입력하십시오.: ");

        String author = scanner.nextLine();

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
            throw new CollectionException("에러: 자료 타입의 입력이 올바르지 않습니다.");
        }

        System.out.println("생성할 자료의 정보는 다음과 같습니다.");
        System.out.println("제목: " + title);
        System.out.println("저자: " + author);
        System.out.println("타입: " + className);

        if (className.equals("Book")) {
            return new Book(title, author);
        } else {
            return new ClassMaterial(title, author);
        }
    }
}
