package javalang.korean.librarymanagement;

import java.util.Scanner;

public class Core {
    static final int PERSON_UID = 0;
    static final int PERSON_NAME = 1;
    static final int PERSON_CLASSNAME = 2;
    static final int COLLECTION_TITLE = 0;
    static final int COLLECTION_AUTHOR = 1;
    static final int COLLECTION_CLASSNAME = 2;

    public static void main(String[] args) throws Exception {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int menuSelection;

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

            menuSelection = Integer.parseInt(scanner.nextLine());

            switch (menuSelection) {
                case 1: {
                    String[] personInformationElement = new String[3];

                    System.out.print("회원의 고유번호(UID)를 입력하십시오.: ");

                    personInformationElement[PERSON_UID] = scanner.nextLine();

                    System.out.print("회원의 이름을 입력하십시오.: ");

                    personInformationElement[PERSON_NAME] = scanner.nextLine();

                    System.out.println("회원의 직위를 입력하십시오.)");
                    System.out.print("([s] 또는 [Student] - Student / [p] 또는 [Professor] - Professor): ");

                    personInformationElement[PERSON_CLASSNAME] = scanner.nextLine();

                    if (personInformationElement[PERSON_CLASSNAME].toLowerCase().equals("s")
                    || personInformationElement[PERSON_CLASSNAME].toLowerCase().equals("student")) {
                        personInformationElement[PERSON_CLASSNAME] = "Student";
                    } else if (personInformationElement[PERSON_CLASSNAME].toLowerCase().equals("p")
                    || personInformationElement[PERSON_CLASSNAME].toLowerCase().equals("professor")) {
                        personInformationElement[PERSON_CLASSNAME] = "Professor";
                    }

                    library.addPerson(personInformationElement);

                    break;
                }
                case 2: {
                    library.printPersonArrayList();

                    break;
                }
                case 3: {
                    String[] collectionInformationElement = new String[3];

                    System.out.print("자료의 제목을 입력하십시오.: ");

                    collectionInformationElement[COLLECTION_TITLE] = scanner.nextLine();

                    System.out.print("자료의 저자를 입력하십시오.: ");

                    collectionInformationElement[COLLECTION_AUTHOR] = scanner.nextLine();

                    System.out.println("자료의 종류를 입력하십시오.");
                    System.out.print(" ([b] 또는 [Book] - Book / [c] 또는 [ClassMaterial] - ClassMaterial): ");

                    collectionInformationElement[COLLECTION_CLASSNAME] = scanner.nextLine();

                    if (collectionInformationElement[PERSON_CLASSNAME].toLowerCase().equals("b")
                        || collectionInformationElement[PERSON_CLASSNAME].toLowerCase().equals("book")) {
                        collectionInformationElement[PERSON_CLASSNAME] = "Book";
                    } else if (collectionInformationElement[PERSON_CLASSNAME].toLowerCase().equals("c")
                        || collectionInformationElement[PERSON_CLASSNAME].toLowerCase().equals("classmaterial")) {
                        collectionInformationElement[PERSON_CLASSNAME] = "ClassMaterial";
                    }

                    library.addCollection(collectionInformationElement);

                    break;
                }
                case 4: {
                    library.printCollectionArrayList();

                    break;
                }
                case 5: {
                    library.saveLibrary();

                    break;
                }
                case 6: {
                    System.out.println("이름으로 대출할 경우 1, 고유번호로 대출할 경우 2를 입력하세요 : ");

                    int selectNum2 = Integer.parseInt(scanner.nextLine());

                    System.out.println("대출할 자료의 제목을 입력하세요 : ");

                    String title = scanner.nextLine();

                    switch (selectNum2) {
                        case 1:
                            System.out.println("대출할 사용자의 이름을 입력하세요 : ");
                            String name = scanner.nextLine();
                            library.checkoutCollection(title, name);
                            break;
                        case 2:
                            System.out.println("대출할 사용자의 고유번호를 입력하세요 : ");
                            int id = Integer.parseInt(scanner.nextLine());
                            library.checkoutCollection(title, id);
                            break;
                    }

                    break;
                }
                case 7: {
                    System.out.println("이름으로 반납할 경우 1, 고유번호로 반납할 경우 2를 입력하세요 : ");
                    int selectNum2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("반납할 자료의 제목을 입력하세요 : ");
                    String title = scanner.nextLine();
                    switch (selectNum2) {
                        case 1:
                            System.out.println("반납할 사용자의 이름을 입력하세요 : ");
                            String name = scanner.nextLine();
                            library.returnCollection(title, name);
                            break;
                        case 2:
                            System.out.println("반납할 사용자의 고유번호를 입력하세요 : ");
                            int id = Integer.parseInt(scanner.nextLine());
                            library.returnCollection(title, id);
                            break;
                    }

                    break;
                }
                case 8:
                    return;
                default:
                    break;
            }
        }
    }
}
