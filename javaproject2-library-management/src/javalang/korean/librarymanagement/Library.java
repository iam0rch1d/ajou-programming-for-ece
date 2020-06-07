package javalang.korean.librarymanagement;

import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Library {
	private static final Scanner scanner = new Scanner(System.in);

	private static final int PERSON_UID = 0;
	private static final int PERSON_NAME = 1;
	private static final int PERSON_CLASSNAME = 2;
	private static final int COLLECTION_TITLE = 0;
	private static final int COLLECTION_AUTHOR = 1;
	private static final int COLLECTION_CLASSNAME = 2;

	private final ArrayList<Person> personArrayList;
	private final ArrayList<Collection> collectionArrayList;

	Library() {
		personArrayList = new ArrayList<>();
		collectionArrayList = new ArrayList<>();

		// Input people data
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

			bufferedReader.close();
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}

		// Input collections data
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("data/collections.txt"));

			while (true) {
				String collectionInformationLine = bufferedReader.readLine();

				if (collectionInformationLine == null) {
					break;
				} else {
					String[] collectionInformationElement = collectionInformationLine.split("\\\\");

					if (collectionInformationElement[COLLECTION_CLASSNAME].equals("Book")) {
						addCollection(
							new Book(
								collectionInformationElement[COLLECTION_TITLE],
								collectionInformationElement[COLLECTION_AUTHOR]
							)
						);
					} else {
						addCollection(
							new ClassMaterial(
								collectionInformationElement[COLLECTION_TITLE],
								collectionInformationElement[COLLECTION_AUTHOR]
							)
						);
					}
				}
			}

			bufferedReader.close();
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

	boolean isUidUnique(int uid) {
		for (Person element : personArrayList) {
			if (uid == element.getUid()) {
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
		System.out.println("--------------------------------------------");
		System.out.println("|    uid    |       이름       | 회원 타입 |");
		System.out.println("+-----------+------------------+-----------+");

		for (Person element : personArrayList) {
			System.out.printf(
				"| %9d | %s | %-9s |\n",
				element.getUid(),
				Core.alignString(element.getName(), 16),
				element.getClass().getSimpleName()
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
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("|               제목               |       저자       |   자료 타입   |");
		System.out.println("+----------------------------------+------------------+---------------+");

		for (Collection element : collectionArrayList) {
			System.out.printf(
				"| %s | %s | %-13s |\n",
				Core.alignString(element.getTitle(), 32),
				Core.alignString(element.getAuthor(), 16),
				element.getClass().getSimpleName()
			);
		}

		System.out.println("-----------------------------------------------------------------------");
	}

	public void saveLibrary() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/people.txt"));

			for (Person element : personArrayList) {
				bufferedWriter.write(element.getUid()
					+ "\\"
					+ element.getName()
					+ "\\"
					+ element.getClass().getSimpleName()
				);
				bufferedWriter.newLine();
			}

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException ioException) {
			System.out.println(Arrays.toString(ioException.getStackTrace()));
		}

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/collections.txt"));

			for (Collection element : collectionArrayList) {
				bufferedWriter.write(element.getTitle()
					+ "\\"
					+ element.getAuthor()
					+ "\\"
					+ element.getClass().getSimpleName()
				);
				bufferedWriter.newLine();
			}

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException ioException) {
			System.out.println(Arrays.toString(ioException.getStackTrace()));
		}
	}

	Person runUiSearchPersonByName() throws PersonException {
		System.out.print("찾으실 회원의 이름을 입력하십시오.: ");

		String name = scanner.nextLine();

		ArrayList<Person> personWithNameArrayList = new ArrayList<>();

		for (Person element : personArrayList) {
			if (name.equals(element.getName())) {
				personWithNameArrayList.add(element);
			}
		}

		switch (personWithNameArrayList.size()) {
			case 0 -> throw new PersonException("해당 이름을 가진 회원의 검색 결과가 존재하지 않습니다.");
			case 1 -> {
				return personWithNameArrayList.get(0);
			}
			default -> {
				System.out.println("해당 이름을 갖는 회원이 "
					+ personWithNameArrayList.size()
					+ "건 검색되었습니다."
				);

				System.out.println("----------------------------------------------------");
				System.out.println("| [No.] |    uid    |       이름       | 회원 타입 |");
				System.out.println("+-------+-----------+------------------+-----------+");

				for (int i = 0; i < personWithNameArrayList.size(); i++) {
					System.out.printf(
						"| %s | %9d | %s | %-9s |\n",
						Core.alignString("[" + (i + 1) + "]", 5),
						personWithNameArrayList.get(i).getUid(),
						Core.alignString(personWithNameArrayList.get(i).getName(), 16),
						personWithNameArrayList.get(i).getClass().getSimpleName()
					);
				}

				System.out.println("----------------------------------------------------");

				System.out.print("찾으시는 회원의 번호(No.)를 입력해 주십시오. ([1-"
					+ personWithNameArrayList.size()
					+ "]): "
				);

				try {
					int personNoSelection = Integer.parseInt(scanner.nextLine());

					return personWithNameArrayList.get(personNoSelection - 1);
				} catch (NumberFormatException e) {
					System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("에러: [1-"
						+ personWithNameArrayList.size()
						+ "] 사이의 값을 입력해 주십시오. ("
						+ e.getClass().getName()
						+ ")"
					);
				}
			}
		}

		return null;
	}

	Person runUiSearchPersonByUid() throws PersonException {
		System.out.print("검색할 회원의 고유번호(uid)를 입력하십시오.: ");

		try {
			int uid = Integer.parseInt(scanner.nextLine());

			for (Person element : personArrayList) {
				if (uid == element.getUid()) {
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
		System.out.print("찾으실 자료의 제목을 입력하십시오.: ");

		String title = scanner.nextLine();

		ArrayList<Collection> collectionWithTitleArrayList = new ArrayList<>();

		for (Collection element : collectionArrayList) {
			if (title.equals(element.getTitle())) {
				collectionWithTitleArrayList.add(element);
			}
		}

		switch (collectionWithTitleArrayList.size()) {
			case 0 -> throw new CollectionException("해당 제목을 가진 자료의 검색 결과가 존재하지 않습니다.");
			case 1 -> {
				return collectionWithTitleArrayList.get(0);
			}
			default -> {
				System.out.println("해당 제목을 가진 자료가 " + collectionWithTitleArrayList.size() + "건 검색되었습니다.");
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("| [No.] |               제목               |       저자       |   자료 타입   |");
				System.out.println("+-------+----------------------------------+------------------+---------------+");

				for (int i = 0; i < collectionWithTitleArrayList.size(); i++) {
					System.out.printf(
						"| %s | %s | %s | %-13s |\n",
						Core.alignString("[" + (i + 1) + "]", 5),
						Core.alignString(collectionWithTitleArrayList.get(i).getTitle(), 32),
						Core.alignString(collectionWithTitleArrayList.get(i).getAuthor(), 16),
						collectionWithTitleArrayList.get(i).getClass().getSimpleName()
					);
				}

				System.out.println("-------------------------------------------------------------------------------");

				System.out.print("찾으시는 자료의 번호를 입력해 주십시오. ([1-"
					+ collectionWithTitleArrayList.size()
					+ "]): "
				);

				try {
					int collectionNoSelection = Integer.parseInt(scanner.nextLine());

					return collectionWithTitleArrayList.get(collectionNoSelection - 1);
				} catch (NumberFormatException e) {
					System.out.println("에러: 숫자 형태로 입력해 주십시오." + e.getClass().getName() + ")");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("에러: [1-"
						+ collectionWithTitleArrayList.size()
						+ "] 사이의 값을 입력해 주십시오. ("
						+ e.getClass().getName()
						+ ")"
					);
				}
			}
		}

		return null;
	}

	public void borrowCollection(Person person, Collection collection) {

	}
	
	public void returnCollection(Person person, Collection collection) {

	}
}
