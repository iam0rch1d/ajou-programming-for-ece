package javalang.korean.librarymanagement;

import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Library {
	static final int PERSON_UID = 0;
	static final int PERSON_NAME = 1;
	static final int PERSON_CLASSNAME = 2;
	static final int COLLECTION_TITLE = 0;
	static final int COLLECTION_AUTHOR = 1;
	static final int COLLECTION_CLASSNAME = 2;

	private static final Scanner scanner = new Scanner(System.in);
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

	void addPerson(Person person) {
		personArrayList.add(person);
	}

	Person runCreatePersonInformationUi() throws PersonException {
		System.out.print("회원의 고유번호(UID)를 입력하십시오.: ");

		try {
			int uid = Integer.parseInt(scanner.nextLine());

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

	public void addCollection(Collection collection) {
		collectionArrayList.add(collection);
	}

	Collection runCreateCollectionInformationUi() throws CollectionException {
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

	public void borrowCollection(String title, String name) {

	}

	public void borrowCollection(String title, int id) {

	}
	
	public void returnCollection(String title, String name) {

	}
	
	public void returnCollection(String title, int id) {

	}
}
