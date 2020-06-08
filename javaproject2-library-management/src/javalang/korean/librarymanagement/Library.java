package javalang.korean.librarymanagement;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

public class Library {
	private static final Scanner scanner = new Scanner(System.in);

	// Constants
	private static final int PERSON_CLASSNAME = 0;
	private static final int PERSON_UID = 1;
	private static final int PERSON_NAME = 2;
	private static final int COLLECTION_CLASSNAME = 0;
	private static final int COLLECTION_TITLE = 1;
	private static final int COLLECTION_AUTHOR = 2;
	private static final int COLLECTION_IS_BORROWABLE_TOSTRING = 3;
	private static final int COLLECTION_BORROWER_NAME = 4;
	private static final int COLLECTION_BORROWED_DATE_TOSTRING = 5;

	private final ArrayList<Person> personArrayList;
	private final ArrayList<Collection> collectionArrayList;

	Library() {
		personArrayList = new ArrayList<>();
		collectionArrayList = new ArrayList<>();

		// Load people data
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("data/people.txt"));

			while (true) {
				String personInformationLine = bufferedReader.readLine();

				if (personInformationLine == null) {
					break;
				} else {
					String[] personInformationElement = personInformationLine.split("\\\\");

					if (personInformationElement[PERSON_CLASSNAME].equals("Student")) {
						addPerson(
							new Student(
								Integer.parseInt(personInformationElement[PERSON_UID]),
								personInformationElement[PERSON_NAME]
							)
						);
					} else {
						addPerson(
							new Professor(
								Integer.parseInt(personInformationElement[PERSON_UID]),
								personInformationElement[PERSON_NAME]
							)
						);
					}
				}
			}

			System.out.println("회원의 데이터를 불러왔습니다.");
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("에러: 회원의 데이터를 불러오는 데 실패했습니다. (" + e.getClass().getName() + ")");
		}

		// Load collections data
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("data/collections.txt"));

			while (true) {
				String collectionInformationLine = bufferedReader.readLine();

				if (collectionInformationLine == null) {
					break;
				} else {
					String[] collectionInformationElement = collectionInformationLine.split("\\\\");

					if (collectionInformationElement[COLLECTION_CLASSNAME].equals("Book")) {
						if (collectionInformationElement.length == 4) {
							addCollection(
								new Book(
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR]
								)
							);
						} else {
							addCollection(
								new Book(
									personArrayList,
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR],
									collectionInformationElement[COLLECTION_IS_BORROWABLE_TOSTRING],
									collectionInformationElement[COLLECTION_BORROWER_NAME],
									collectionInformationElement[COLLECTION_BORROWED_DATE_TOSTRING]
								)
							);
						}
					} else {
						if (collectionInformationElement[COLLECTION_BORROWER_NAME] == null) {
							addCollection(
								new ClassMaterial(
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR]
								)
							);
						} else {
							addCollection(
								new ClassMaterial(
									personArrayList,
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR],
									collectionInformationElement[COLLECTION_IS_BORROWABLE_TOSTRING],
									collectionInformationElement[COLLECTION_BORROWER_NAME],
									collectionInformationElement[COLLECTION_BORROWED_DATE_TOSTRING]
								)
							);
						}
					}
				}
			}

			System.out.println("자료의 데이터를 불러왔습니다.");
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("에러: 회원의 데이터를 불러오는 데 실패했습니다. (" + e.getClass().getName() + ")");
		}
	}

	boolean isUidUnique(int uid) {
		for (Person element : personArrayList) {
			if (element.getUid() == uid) {
				return false;
			}
		}

		return true;
	}

	void addPerson(Person person) {
		if (person != null) {
			personArrayList.add(person);
		}
	}

	public void printPersonArrayList() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("등록된 회원 목록");
		System.out.println("--------------------------------------------");
		System.out.println("| 회원 타입 |    uid    |       이름       |");
		System.out.println("+-----------+-----------+------------------+");

		for (Person element : personArrayList) {
			System.out.printf(
				"| %-9s | %9d | %s |\n",
				element.getClass().getSimpleName(),
				element.getUid(),
				Core.alignString(element.getName(), 16)
			);
		}

		System.out.println("--------------------------------------------");
	}

	void addCollection(Collection collection) {
		if (collection != null) {
			collectionArrayList.add(collection);
		}
	}

	public void printCollectionArrayList() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("소장 중인 자료 목록");
		System.out.println("---------------------------------------------------------------------------------------"
			+ "--------------------------------------------"
		);
		System.out.println("|   자료 타입   |               제목               |       저자       | 대출 가능 여부 "
			+ "|          대출인(uid)        |  대출일자  |"
		);
		System.out.println("+---------------+----------------------------------+------------------+----------------"
			+ "+-----------------------------+------------+"
		);

		for (Collection element : collectionArrayList) {
			System.out.printf(
				"| %-13s | %s | %s | %-14s | %s | %s |\n",
				element.getClass().getSimpleName(),
				Core.alignString(element.getTitle(), 32),
				Core.alignString(element.getAuthor(), 16),
				element.getIsBorrowable(),
				Core.alignString(element.getBorrowerInformation(), 27),
				Core.alignString(element.getBorrowedDateToString(), 10)
			);
		}

		System.out.println("---------------------------------------------------------------------------------------"
			+ "--------------------------------------------"
		);
	}

	public void saveLibrary() {
		// Save people data
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/people.txt"));

			for (Person element : personArrayList) {
				bufferedWriter.write(element.getClass().getSimpleName() // PERSON_CLASSNAME(0)
					+ "\\"
					+ element.getUid() // PERSON_UID(1)
					+ "\\"
					+ element.getName() // PERSON_NAME(2)
				);
				bufferedWriter.newLine();
			}

			bufferedWriter.flush();
			bufferedWriter.close();
			System.out.println("회원의 데이터를 저장했습니다.");
		} catch (IOException e) {
			System.out.println("에러: 회원의 데이터를 저장하는 데 실패했습니다. (" + e.getClass().getName() + ")");
		} catch (NullPointerException e) {
			System.out.println("저장할 회원의 데이터가 없습니다. (" + e.getClass().getName() + ")");
		}

		// Save collections data
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/collections.txt"));

			for (Collection element : collectionArrayList) {
				bufferedWriter.write(element.getClass().getSimpleName() // COLLECTION_CLASSNAME(0)
					+ "\\"
					+ element.getTitle() // COLLECTION_TITLE(1)
					+ "\\"
					+ element.getAuthor() // COLLECTION_AUTHOR(2)
					+ "\\"
					+ element.getIsBorrowable() // COLLECTION_IS_BORROWABLE_TOSTRING(3)
				);

				if (element.getBorrower() != null) {
					bufferedWriter.write("\\"
						+ element.getBorrower().getUid() // COLLECTION_BORROWER_NAME(4)
						+ "\\"
						+ element.getBorrowedDateToString() // COLLECTION_BORROWED_DATE_TOSTRING(5)
					);
				}

				bufferedWriter.newLine();
			}

			bufferedWriter.flush();
			bufferedWriter.close();
			System.out.println("자료의 데이터를 저장했습니다.");
		} catch (IOException e) {
			System.out.println("에러: 자료의 데이터를 저장하는 데 실패했습니다. (" + e.getClass().getName() + ")");
		}
	}

	Person runUiSearchPersonByName() throws PersonException {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("찾으실 회원의 이름을 입력하십시오.: ");

		String name = scanner.nextLine();

		System.out.println("--------------------------------------------------------------------------------");

		ArrayList<Person> personWithNameArrayList = new ArrayList<>();

		for (Person element : personArrayList) {
			if (element.getName().equals(name)) {
				personWithNameArrayList.add(element);
			}
		}

		switch (personWithNameArrayList.size()) {
			case 0 -> throw new PersonException("해당 이름을 가진 회원의 검색 결과가 존재하지 않습니다.");
			case 1 -> {
				System.out.println("선택하신 회원의 정보는 다음과 같습니다.");
				System.out.println("회원 타입: " + personWithNameArrayList.get(0).getClass().getSimpleName());
				System.out.println("고유번호(uid): " + personWithNameArrayList.get(0).getUid());
				System.out.println("이름: " + name);

				return personWithNameArrayList.get(0);
			}
			default -> {
				System.out.println("해당 이름을 갖는 회원이 "
					+ personWithNameArrayList.size()
					+ "건 검색되었습니다."
				);

				System.out.println("----------------------------------------------------");
				System.out.println("| [No.] | 회원 타입 |    uid    |       이름       |");
				System.out.println("+-------+-----------+-----------+------------------+");

				for (int i = 0; i < personWithNameArrayList.size(); i++) {
					System.out.printf(
						"| %s | %-9s | %9d | %s |\n",
						Core.alignString("[" + (i + 1) + "]", 5),
						personWithNameArrayList.get(i).getClass().getSimpleName(),
						personWithNameArrayList.get(i).getUid(),
						Core.alignString(personWithNameArrayList.get(i).getName(), 16)
					);
				}

				System.out.println("----------------------------------------------------");
				System.out.println("--------------------------------------------------------------------------------");

				while (true) {
					System.out.print("찾으시는 회원의 번호(No.)를 입력해 주십시오. ([1-"
						+ personWithNameArrayList.size()
						+ "]): "
					);

					try {
						int personNoSelection = Integer.parseInt(scanner.nextLine());

						if (personNoSelection < 1 || personNoSelection > personWithNameArrayList.size()) {
							System.out.println("[1-"
								+ personWithNameArrayList.size()
								+ "] 사이의 값을 입력해 주십시오."
							);

							continue;
						}

						System.out.println("---------------------------------------------------------------------------"
							+ "-----"
						);

						Person selectedPerson = personWithNameArrayList.get(personNoSelection - 1);

						System.out.println("선택하신 회원의 정보는 다음과 같습니다.");
						System.out.println("회원 타입: " + selectedPerson.getClass().getSimpleName());
						System.out.println("고유번호(uid): " + selectedPerson.getUid());
						System.out.println("이름: " + name);

						return selectedPerson;
					} catch (NumberFormatException e) {
						System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
					}
				}
			}
		}
	}

	Person runUiSearchPersonByUid() throws PersonException {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("검색할 회원의 고유번호(uid)를 입력하십시오.: ");

		try {
			int uid = Integer.parseInt(scanner.nextLine());

			System.out.println("--------------------------------------------------------------------------------");

			for (Person element : personArrayList) {
				if (element.getUid() == uid) {
					return element;
				}
			}

			throw new PersonException("해당 고유번호(uid)를 가진 회원의 검색 결과가 존재하지 않습니다.");
		} catch (NumberFormatException e) {
			System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
		}

		return null;
	}

	Collection runUiSearchCollectionByTitle() throws CollectionException {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("찾으실 자료의 제목을 입력하십시오.: ");

		String title = scanner.nextLine();

		System.out.println("--------------------------------------------------------------------------------");

		ArrayList<Collection> collectionWithTitleArrayList = new ArrayList<>();

		for (Collection element : collectionArrayList) {
			if (element.getTitle().equals(title)) {
				collectionWithTitleArrayList.add(element);
			}
		}

		switch (collectionWithTitleArrayList.size()) {
			case 0 -> throw new CollectionException("해당 제목을 가진 자료의 검색 결과가 존재하지 않습니다.");
			case 1 -> {
				System.out.println("선택하신 자료의 정보는 다음과 같습니다.");
				System.out.println("제목: " + title);
				System.out.println("작가: " + collectionWithTitleArrayList.get(0).getAuthor());
				System.out.println("자료 타입: " + collectionWithTitleArrayList.get(0).getClass().getSimpleName());

				return collectionWithTitleArrayList.get(0);
			}
			default -> {
				System.out.println("해당 제목을 가진 자료가 "
					+ collectionWithTitleArrayList.size()
					+ "건 검색되었습니다."
				);
				System.out.println("-------------------------------------------------------------------------------"
					+ "------------------------------------------------------------"
				);
				System.out.println("| [No.] |   자료 타입   |               제목               |       저자       |"
					+ " 대출 가능 여부 |          대출인(uid)        |  대출일자  |"
				);
				System.out.println("+-------+---------------+----------------------------------+------------------+"
					+ "----------------+-----------------------------+------------+"
				);

				for (int i = 0; i < collectionWithTitleArrayList.size(); i++) {
					System.out.printf(
						"| %s | %-13s | %s | %s | %-14s | %s | %s |\n",
						Core.alignString("[" + (i + 1) + "]", 5),
						collectionWithTitleArrayList.get(i).getClass().getSimpleName(),
						Core.alignString(collectionWithTitleArrayList.get(i).getTitle(), 32),
						Core.alignString(collectionWithTitleArrayList.get(i).getAuthor(), 16),
						collectionWithTitleArrayList.get(i).getIsBorrowable(),
						Core.alignString(collectionWithTitleArrayList.get(i).getBorrowerInformation(), 27),
						Core.alignString(collectionWithTitleArrayList.get(i).getBorrowedDateToString(), 10)
					);
				}

				System.out.println("-------------------------------------------------------------------------------"
					+ "------------------------------------------------------------"
				);
				System.out.println("--------------------------------------------------------------------------------");

				while (true) {
					System.out.print("찾으시는 자료의 번호를 입력해 주십시오. ([1-"
						+ collectionWithTitleArrayList.size()
						+ "]): "
					);

					try {
						int collectionNoSelection = Integer.parseInt(scanner.nextLine());

						if (collectionNoSelection < 1 || collectionNoSelection > collectionWithTitleArrayList.size()) {
							System.out.println("[1-"
								+ collectionWithTitleArrayList.size()
								+ "] 사이의 값을 입력해 주십시오."
							);

							continue;
						}

						System.out.println("---------------------------------------------------------------------------"
							+ "----"
						);

						Collection selectedCollection = collectionWithTitleArrayList.get(collectionNoSelection - 1);

						System.out.println("선택하신 자료의 정보는 다음과 같습니다.");
						System.out.println("제목: " + title);
						System.out.println("작가: " + selectedCollection.getAuthor());
						System.out.println("자료 타입: " + selectedCollection.getClass().getSimpleName());

						return collectionWithTitleArrayList.get(collectionNoSelection - 1);
					} catch (NumberFormatException e) {
						System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
					}
				}
			}
		}
	}

	public void borrowCollection(Person person, Collection collection) throws PersonException {
		if (person.getBorrowingCollection().size() >= person.getNumberOfBorrowable()) {
			throw new PersonException("대출한도를 초과하였습니다.");
		} else {
			for (Collection element : person.getBorrowingCollection()) {
				if (Duration.between(element.getBorrowedDate(), LocalDate.now()).toDays()
					> person.getDaysOfBorrowable()
				) {
					throw new PersonException("반납기한이 연체된 자료가 존재합니다.");
				}
			}
		}

		try {
			collection.setIsBorrowable(false);
			collection.setBorrower(person);
			collection.setBorrowedDate(LocalDate.now());
			person.getBorrowingCollection().add(collection);
			System.out.println("자료를 성공적으로 대출했습니다.");
		} catch (CollectionException e) {
			System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
			System.out.println("대출에 실패했습니다.");
		}
	}
	
	public void returnCollection(Collection collection) throws PersonException, CollectionException {
		Person borrower = collection.getBorrower();

		if (borrower == null) {
			throw new CollectionException("해당 자료는 대출 중이 아닙니다.");
		} else if (!borrower.getBorrowingCollection().contains(collection)) {
			throw new PersonException(borrower + " 님은 해당 자료를 대출하지 않았습니다.");
		}

		collection.setIsBorrowable(true);
		collection.setBorrower(null);
		collection.setBorrowedDate(null);
		borrower.getBorrowingCollection().remove(collection);
		System.out.println("자료를 성공적으로 반납했습니다.");
	}
}
