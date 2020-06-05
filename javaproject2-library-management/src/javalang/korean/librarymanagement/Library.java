package javalang.korean.librarymanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javalang.korean.librarymanagement.collection.*;
import javalang.korean.librarymanagement.person.*;

public class Library {
	private final ArrayList<Person> personArrayList;
	private final ArrayList<Collection> collectionArrayList;

	public Library() {
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

					addPerson(personInformationElement);
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

					addCollection(collectionInformationElement);
				}
			}

			bufferedReader.close();
		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
		}
	}

	public void addPerson(String[] personInformationElement) {
		try {
			if (personInformationElement[Core.PERSON_CLASSNAME].equals("Student")) {
				Student student = new Student(
					Integer.parseInt(personInformationElement[Core.PERSON_UID]),
					personInformationElement[Core.PERSON_NAME]
				);

				personArrayList.add(student);
				System.out.println("생성한 회원의 정보는 다음과 같습니다.");
				System.out.println("고유번호(UID): " + student.getId());
				System.out.println("이름: " + student.getName());
				System.out.println("직위: " + student.getClass().getSimpleName());

			} else if (personInformationElement[Core.PERSON_CLASSNAME].equals("Professor")) {
				Professor professor = new Professor(
					Integer.parseInt(personInformationElement[Core.PERSON_UID]),
					personInformationElement[Core.PERSON_NAME]
				);

				personArrayList.add(professor);
				System.out.println("생성한 회원의 정보는 다음과 같습니다.");
				System.out.println("고유번호(UID): " + professor.getId());
				System.out.println("이름: " + professor.getName());
				System.out.println("직위: " + professor.getClass().getSimpleName());
			}


		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
		}
	}

	public void addCollection(String[] collectionInformationElement) {
		try {
			if (collectionInformationElement[Core.COLLECTION_CLASSNAME].equals("Book")) {
				Book book = new Book(
					collectionInformationElement[Core.COLLECTION_TITLE],
					collectionInformationElement[Core.COLLECTION_AUTHOR]
				);

				collectionArrayList.add(book);
				System.out.println("생성한 자료의 정보는 다음과 같습니다.");
				System.out.println("제목: " + book.getTitle());
				System.out.println("저자: " + book.getAuthor());
				System.out.println("타입: " + book.getClass().getSimpleName());

			} else if (collectionInformationElement[Core.PERSON_CLASSNAME].equals("ClassMaterial")) {
				ClassMaterial classMaterial = new ClassMaterial(
					collectionInformationElement[Core.COLLECTION_TITLE],
					collectionInformationElement[Core.COLLECTION_AUTHOR]
				);

				collectionArrayList.add(classMaterial);
				System.out.println("생성한 자료의 정보는 다음과 같습니다.");
				System.out.println("제목: " + classMaterial.getTitle());
				System.out.println("저자: " + classMaterial.getAuthor());
				System.out.println("타입: " + classMaterial.getClass().getSimpleName());
			}


		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
		}
	}

	public void printPersonArrayList() {
		for (Person element : personArrayList) {
			System.out.print(element.getId() + " | ");
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
					element.getId() + "\\"
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

	public void checkoutCollection(String title, String name){

	}

	public void checkoutCollection(String title, int id) {

	}
	
	public void returnCollection(String title, String name) {

	}
	
	public void returnCollection(String title, int id) {

	}
}
