package javalang.korean.classapplication;

import java.util.ArrayList;
import java.util.Scanner;

class Core {
	static final ArrayList<Student> studentArrayList = new ArrayList<>();
	static final ArrayList<Professor> professorArrayList = new ArrayList<>();
	static final ArrayList<Staff> staffArrayList = new ArrayList<>();
	static final ArrayList<Lecture> lectureArrayList = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);

	static void runCoreUi() {
		while (true) {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("                        아주대학교 수강신청 프로그램");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("[1] - 회원가입");
			System.out.println("[2] - 로그인");
			System.out.println("[3] - 종료");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("메뉴를 선택하십시오. ([1-3]): ");

			int menuSelection = scanner.nextInt();

			switch (menuSelection) {
				case 1 -> Entrance.runSignUpUi();
				case 2 -> Entrance.runSignInUi();
				case 3 -> {
					System.out.println("프로그램이 종료됩니다.");

					return;
				}
			}
		}
	}

	static void printLectureArrayList() {
		int i = 1;

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("등록된 강의 목록");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("| No. |          과목명          |      교수명      |  시간  | 학년 |");
		System.out.println("|-----+--------------------------+------------------+--------+------|");

		for (Lecture element : Core.lectureArrayList) {
			System.out.printf(
				"| %3d | %s | %s | %s |  %2d  |",
				i++,
				alignString(element.getName(), 24),
				alignString(element.getProfessorName(), 16),
				element.getTime(),
				element.getMinimumGrade()
			);

			System.out.println();
		}

		System.out.println("---------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------");
	}

	static void printStudentArrayList() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("등록된 학생 목록");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("|       이름       |        ID        |     비밀번호     | 학년 |");
		System.out.println("|------------------+------------------+------------------+------|");

		for (Student element : Core.studentArrayList) {
			System.out.printf(
				"| %s | %-16s | %-16s |  %2d  |",
				alignString(element.getName(), 16),
				element.getId(),
				element.getPassword(),
				element.getGrade()
			);

			System.out.println();
		}

		System.out.println("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------");
	}

	static void printProfessorArrayList() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("등록된 교수 목록");
		System.out.println("----------------------------------------------------------");
		System.out.println("|       이름       |        ID        |     비밀번호     |");
		System.out.println("|------------------+------------------+------------------|");

		for (Professor element : Core.professorArrayList) {
			System.out.printf(
				"| %s | %-16s | %-16s |",
				alignString(element.getName(), 16),
				element.getId(),
				element.getPassword()
			);

			System.out.println();
		}

		System.out.println("----------------------------------------------------------");
	}

	static String alignString(String string, int length) {
		int i;

		for (char element : string.toCharArray()) {
			if (element >= 0x21 && element <= 0x7e) { // 1-byte character
				length--;
			} else {
				length -= 2;
			}
		}

		StringBuilder stringBuilder = new StringBuilder(string);

		for (i = 0; i < length; i++) {
			stringBuilder.append(' ');
		}

		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		Initialization.initialize();
		runCoreUi();
	}
}
