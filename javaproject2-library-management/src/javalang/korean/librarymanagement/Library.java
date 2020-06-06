package javalang.korean.librarymanagement;

import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Library {
	static final int PERSON_UID = 0;
	static final int PERSON_NAME = 1;
	static final int PERSON_CLASSNAME = 2;
	static final int COLLECTION_TITLE = 0;
	static final int COLLECTION_AUTHOR = 1;
	static final int COLLECTION_CLASSNAME = 2;

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
		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
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
		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
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
		personArrayList.add(person);
	}

	void addCollection(Collection collection) {
		collectionArrayList.add(collection);
	}

	public void printPersonArrayList() {
		for (Person element : personArrayList) {
			System.out.print(element.getUid() + " | ");
			System.out.print(element.getName() + " | ");
			System.out.println(element.getClass().getSimpleName());
		}
	}

	public void printCollectionArrayList() {
		for (Collection element : collectionArrayList) {
			System.out.print(element.getTitle() + " | ");
			System.out.print(element.getAuthor() + " | ");
			System.out.println(element.getClass().getSimpleName());
		}
	}

	public void saveLibrary() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/people.txt"));

			for (Person element : personArrayList) {
				bufferedWriter.write(
					element.getUid() + "\\"
					+ element.getName() + "\\"
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
				bufferedWriter.write(
					element.getTitle() + "\\"
						+ element.getAuthor() + "\\"
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

	String searchByName(String name) throws PersonException {
		int countPersonWithName = 0;

		for (Person element : personArrayList) {
			if (name.equals(element.getName())) {
				countPersonWithName++;
			}
		}

		switch (countPersonWithName) {
			case 0 -> throw new PersonException("입력한 이름을 가진 회원의 검색 결과가 존재하지 않습니다.");
			case 1 -> {
				return name;
			}
			default -> {
				System.out.println("입력한 이름을 가진 회원이 " + countPersonWithName + "건 검색되었습니다.");

				for (Person element : personArrayList) {
					if (name.equals(element.getName())) {
						System.out.print(element.getUid() + " | ");
						System.out.print(element.getName() + " | ");
						System.out.println(element.getClass().getSimpleName());
					}
				}

				throw new PersonException("uid로 검색해 주십시오.");
			}
		}
	}

	public void borrowCollection(String title, String name) {

	}

	public void borrowCollection(String title, int id) {

	}
	
	public void returnCollection(String title, String name) {

	}
	
	public void returnCollection(String title, int id) {

	}
}
