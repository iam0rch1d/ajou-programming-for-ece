package javalang.korean.librarymanagement;

import java.util.Scanner;
import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.PersonException;

public class Core {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();

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
                    case 1 -> library.addPerson(library.runCreatePersonInformationUi());
                    case 2 -> library.printPersonArrayList();
                    case 3 -> library.addCollection(library.runCreateCollectionInformationUi());
                    case 4 -> library.printCollectionArrayList();
                    case 5 -> library.saveLibrary();
                    case 6 -> {
                        System.out.println("대출할 자료의 제목을 입력하세요 : ");

                        String title = scanner.nextLine();

                        System.out.println("자료를 대출하려는 회원을 검색합니다. 메뉴를 선택하십시오. ([1] 또는 [2])");
                        System.out.println("([1] - 이름으로 검색, [2] - 고유번호(UID)로 검색)");

                        try {
                            int submenuSelection = Integer.parseInt(scanner.nextLine());

                            switch (submenuSelection) {
                                case 1 -> {
                                    System.out.println("대출할 사용자의 이름을 입력하세요: ");
                                    String name = scanner.nextLine();
                                    library.borrowCollection(title, name);
                                }
                                case 2 -> {
                                    System.out.println("대출할 사용자의 고유번호를 입력하세요: ");
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
}
