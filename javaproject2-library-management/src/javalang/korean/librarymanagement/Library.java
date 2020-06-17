package javalang.korean.librarymanagement;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

public class Library {
	private static final Scanner scanner = new Scanner(System.in);
	private String personInformationLine;

	// Constants
	private static final int PERSON_CLASSNAME = 0;
	private static final int PERSON_UID = 1;
	private static final int PERSON_NAME = 2;
	private static final int COLLECTION_CLASSNAME = 0;
	private static final int COLLECTION_TITLE = 1;
	private static final int COLLECTION_AUTHOR = 2;
	private static final int COLLECTION_IS_BORROWABLE = 3;
	private static final int COLLECTION_BORROWER_NAME = 4;
	private static final int COLLECTION_BORROWED_DATE = 5;

	private final ArrayList<Person> personArrayList;
	private final ArrayList<Collection> collectionArrayList;

	Library() {
		personArrayList = new ArrayList<>();
		collectionArrayList = new ArrayList<>();

		// Load people data
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("data/people.txt"));

			loadPeopleData(bufferedReader);
			System.out.println("회원의 데이터를 불러왔습니다.");
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("에러: 회원의 데이터를 불러오는 데 실패했습니다. (" + e.getClass().getName() + ")");
		}

		// Load collections data
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("data/collections.txt"));

			loadCollectionsData(bufferedReader);
			System.out.println("자료의 데이터를 불러왔습니다.");
			bufferedReader.close();
		} catch (IOException | NumberFormatException e) {
			System.out.println("에러: 자료의 데이터를 불러오는 데 실패했습니다. (" + e.getClass().getName() + ")");
		}
	}

	public ArrayList<Person> getPersonArrayList() {
		return personArrayList;
	}

	public String getPersonInformationLine() {
		return personInformationLine;
	}

	private void loadPeopleData(BufferedReader bufferedReader) throws IOException {
		while (true) {
			personInformationLine = bufferedReader.readLine();

			try {
				boolean hasPersonExceptionOccurred = false;

				if (personInformationLine == null) {
					break;
				} else {
					String[] personInformationElement = personInformationLine.split("\\\\");

					if (personInformationElement.length == 3) {
						if (personInformationElement[PERSON_CLASSNAME].equals("Student")) {
							addPerson(
								new Student(
									this,
									Integer.parseInt(personInformationElement[PERSON_UID]),
									personInformationElement[PERSON_NAME]
								)
							);
						} else if (personInformationElement[PERSON_CLASSNAME].equals("Professor")) {
							addPerson(
								new Professor(
									this,
									Integer.parseInt(personInformationElement[PERSON_UID]),
									personInformationElement[PERSON_NAME]
								)
							);
						} else {
							hasPersonExceptionOccurred = true;
						}
					} else {
						hasPersonExceptionOccurred = true;
					}

					if (hasPersonExceptionOccurred) {
						throw new PersonException("회원의 데이터 파일의 line "
							+ (personArrayList.size() + 1)
							+ "\n'"
							+ personInformationLine
							+ "'에서 오류가 발생했습니다."
						);
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("회원의 데이터 파일의 line "
					+ (personArrayList.size() + 1)
					+ "\n'"
					+ personInformationLine
					+ "'에서 오류가 발생했습니다."
					+ " ("
					+ e.getClass().getName()
					+ ")"
				);
			} catch (PersonException e) {
				System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
			}
		}
	}

	private void loadCollectionsData(BufferedReader bufferedReader) throws IOException {
		while (true) {
			String collectionInformationLine = bufferedReader.readLine();

			try {
				boolean hasCollectionExceptionOccurred = false;

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
						} else if (collectionInformationElement.length == 6) {
							addCollection(
								new Book(
									this,
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR],
									collectionInformationElement[COLLECTION_IS_BORROWABLE],
									collectionInformationElement[COLLECTION_BORROWER_NAME],
									collectionInformationElement[COLLECTION_BORROWED_DATE]
								)
							);
						} else {
							hasCollectionExceptionOccurred = true;
						}
					} else if (collectionInformationElement[COLLECTION_CLASSNAME].equals("ClassMaterial")) {
						if (collectionInformationElement.length == 4) {
							addCollection(
								new ClassMaterial(
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR]
								)
							);
						} else if (collectionInformationElement.length == 6) {
							addCollection(
								new ClassMaterial(
									this,
									collectionInformationElement[COLLECTION_TITLE],
									collectionInformationElement[COLLECTION_AUTHOR],
									collectionInformationElement[COLLECTION_IS_BORROWABLE],
									collectionInformationElement[COLLECTION_BORROWER_NAME],
									collectionInformationElement[COLLECTION_BORROWED_DATE]
								)
							);
						} else {
							hasCollectionExceptionOccurred = true;
						}
					} else {
						hasCollectionExceptionOccurred = true;
					}

					if (hasCollectionExceptionOccurred) {
						throw new CollectionException("자료의 데이터 파일의 line "
							+ (collectionArrayList.size() + 1)
							+ "\n'"
							+ collectionInformationLine
							+ "'에서 오류가 발생했습니다."
						);
					}
				}
			} catch (CollectionException e) {
				System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
			}
		}
	}

	public boolean isUidDuplicated(int uid) {
		for (Person element : personArrayList) {
			if (element.getUid() == uid) {
				return true;
			}
		}

		return false;
	}

	void addPerson(Person person) {
		if (person != null) {
			personArrayList.add(person);
		}
	}

	void printPersonArrayList() throws PersonException {
		System.out.println("--------------------------------------------------------------------------------");

		if (personArrayList.isEmpty()) {
			throw new PersonException("회원의 데이터가 존재하지 않습니다.");
		}

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

	void printCollectionArrayList() throws CollectionException {
		System.out.println("--------------------------------------------------------------------------------");

		if (collectionArrayList.isEmpty()) {
			throw new CollectionException("자료의 데이터가 존재하지 않습니다.");
		}

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
				"| %-13s | %s | %s | %-14s | %s | %-10s |\n",
				element.getClass().getSimpleName(),
				Core.alignString(element.getTitle(), 32),
				Core.alignString(element.getAuthor(), 16),
				element.getIsBorrowable(),
				Core.alignString(element.getBorrowerInformation(), 27),
				element.getBorrowedDateToString()
			);
		}

		System.out.println("---------------------------------------------------------------------------------------"
			+ "--------------------------------------------"
		);
	}

	void saveLibraryData() {
		System.out.println("--------------------------------------------------------------------------------");

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
					+ element.getIsBorrowable() // COLLECTION_IS_BORROWABLE(3)
				);

				if (element.getBorrower() != null) {
					bufferedWriter.write("\\"
						+ element.getBorrower().getUid() // COLLECTION_BORROWER_NAME(4)
						+ "\\"
						+ element.getBorrowedDateToString() // COLLECTION_BORROWED_DATE(5)
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

	void lendCollection(Person person, Collection collection) {
		System.out.println("--------------------------------------------------------------------------------");

		person.getBorrowingCollectionArrayList().clear();

		for (Collection element : collectionArrayList) {
			if (element.getBorrower() != null && element.getBorrower().getUid() == person.getUid()) {
				person.getBorrowingCollectionArrayList().add(element);
			}
		}

		try {
			if (person.getBorrowingCollectionArrayList().size() >= person.getNumberOfBorrowable()) {
				throw new PersonException("대출한도를 초과하였습니다.");
			} else {
				for (Collection element : person.getBorrowingCollectionArrayList()) {
					if (ChronoUnit.DAYS.between(element.getBorrowedDate(), LocalDate.now())
						>= person.getDaysOfBorrowable()
					) {
						throw new PersonException("반납기한이 연체된 자료가 존재합니다.");
					}
				}
			}

			if (collection.getIsBorrowable()) {
				collection.setIsBorrowable(false);
				collection.setBorrower(person);
				collection.setBorrowedDate(LocalDate.now());
				person.getBorrowingCollectionArrayList().add(collection);
				System.out.println("자료를 성공적으로 대출했습니다.");
			} else {
				throw new CollectionException("해당 자료는 이미 대출 중입니다.");
			}
		} catch (PersonException | CollectionException e) {
			System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
			System.out.println("대출에 실패했습니다.");
		}
	}

	void redeemCollection(Collection collection) {
		Person borrower = collection.getBorrower();

		System.out.println("--------------------------------------------------------------------------------");

		try {
			if (borrower == null) {
				throw new CollectionException("해당 자료는 현재 대출 중이 아닙니다.");
			}

			borrower.getBorrowingCollectionArrayList().clear();

			for (Collection element : collectionArrayList) {
				if (element.getBorrower() != null && element.getBorrower().getUid() == borrower.getUid()) {
					borrower.getBorrowingCollectionArrayList().add(element);
				}
			}

			collection.setIsBorrowable(true);
			collection.setBorrower(null);
			collection.setBorrowedDate(null);
			borrower.getBorrowingCollectionArrayList().remove(collection);
			System.out.println("자료를 성공적으로 반납했습니다.");
		} catch (CollectionException e) {
			System.out.println(e.getMessage() + " (" + e.getClass().getName() + ")");
			System.out.println("반납에 실패했습니다.");
		}
	}

	Person runUiSearchPerson() throws PersonException {
		while (true) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("자료를 대출하려는 회원을 검색합니다. 메뉴를 선택하십시오. ([1-3])");
			System.out.print("([1] - 이름으로 검색, [2] - 고유번호(uid)로 검색, [3] - 취소): ");

			try {
				int menuSelection = Integer.parseInt(scanner.nextLine());

				switch (menuSelection) {
					case 1 -> {
						return runUiSearchPersonByName();
					}
					case 2 -> {
						return runUiSearchPersonByUid();
					}
					case 3 -> throw new PersonException("회원 검색이 취소되었습니다. 자료 대출을 취소합니다.");
					default -> System.out.println("존재하지 않는 메뉴입니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("에러: 숫자 형태로 입력해 주십시오. (" + e.getClass().getName() + ")");
			}
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
					System.out.println("선택하신 회원의 정보는 다음과 같습니다.");
					System.out.println("회원 타입: " + element.getClass().getSimpleName());
					System.out.println("고유번호(uid): " + element.getUid());
					System.out.println("이름: " + element.getName());

					return element;
				}
			}

			throw new PersonException("해당 고유번호(uid)를 가진 회원의 검색 결과가 존재하지 않습니다.");
		} catch (NumberFormatException e) {
			System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
		}

		return null;
	}

	Collection runUiSearchCollection() throws CollectionException {
		while (true) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("대출할 자료를 검색합니다. 메뉴를 선택하십시오. ([1-2])");
			System.out.print("([1] - 제목으로 검색, [2] - 취소): ");

			try {
				int menuSelection = Integer.parseInt(scanner.nextLine());

				switch (menuSelection) {
					case 1 -> {
						return runUiSearchCollectionByTitle();
					}
					case 2 -> throw new CollectionException("자료 검색이 취소되었습니다. 자료 대출을 취소합니다.");
					default -> System.out.println("존재하지 않는 메뉴입니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("에러: 숫자 형태로 입력해 주십시오. (" + e.getClass().getName() + ")");
			}
		}
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
						"| %s | %-13s | %s | %s | %-14s | %s | %-10s |\n",
						Core.alignString("[" + (i + 1) + "]", 5),
						collectionWithTitleArrayList.get(i).getClass().getSimpleName(),
						Core.alignString(collectionWithTitleArrayList.get(i).getTitle(), 32),
						Core.alignString(collectionWithTitleArrayList.get(i).getAuthor(), 16),
						collectionWithTitleArrayList.get(i).getIsBorrowable(),
						Core.alignString(collectionWithTitleArrayList.get(i).getBorrowerInformation(), 27),
						collectionWithTitleArrayList.get(i).getBorrowedDateToString()
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
}
