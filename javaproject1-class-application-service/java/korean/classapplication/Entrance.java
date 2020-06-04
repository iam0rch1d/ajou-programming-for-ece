package iam0rch1d.java.korean.classapplication;

import java.util.Scanner;

class Entrance {
	private static final int STUDENT = 1;
	private static final int PROFESSOR = 2;
	private static final int STAFF = 3;

	static String name;
	static String registrationNumber;
	static String id;
	static String password;
	static int grade;

	static void runCreateAccountUi() {
		Scanner scanner = new Scanner(System.in);
		int memberTypeSelection;

		while (true) {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("[1] 학생");
			System.out.println("[2] 교수");
			System.out.println("[3] 직원");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("회원 타입을 선택하십시오. ([1-3] - 회원 타입 선택, [0] - 취소): ");

			memberTypeSelection = scanner.nextInt();

			if (memberTypeSelection == 0) {
				System.out.println("회원가입이 취소되었습니다.");

				return;
			}

			scanner.nextLine();

			if (memberTypeSelection < STUDENT || memberTypeSelection > STAFF) {
				System.out.println("회원 타입이 존재하지 않습니다.");

				continue;
			}

			System.out.println("-----------------------------------------------------------------------------");

			// Name scan
			System.out.print("이름을 입력하십시오. ([/취소] - 취소): ");

			name = scanner.nextLine();

			if (name.equals("/취소")) {
				System.out.println("회원가입이 취소되었습니다.");

				return;
			}

			// Registration number scan, validity check, duplicate check
			while (true) {
				System.out.print("주민등록번호를 입력하십시오. ([XXXXXX-XXXXXXX] 형태로 구성, [/취소] - 취소): ");

				registrationNumber = scanner.nextLine();

				if (registrationNumber.equals("/취소")) {
					System.out.println("회원가입이 취소되었습니다.");

					return;
				} else if (!isRegistrationNumberValid(registrationNumber)) {
					System.out.println("유효하지 않은 주민등록번호입니다.");

					continue;
				} else if (isRegistrationNumberDuplicated(registrationNumber)) {
					System.out.println("이미 등록된 주민등록번호입니다.");

					continue;
				}

				break;
			}

			// ID scan, validity check, duplicate check
			while (true) {
				System.out.print("ID를 입력하십시오. (최대 16자 영어 소문자나 숫자로 구성, [/취소] - 취소): ");

				id = scanner.nextLine();

				if (id.equals("/취소")) {
					System.out.println("회원가입이 취소되었습니다.");

					return;
				} else if (!isIdValid(id)) {
					System.out.println("최대 16자 영어 소문자나 숫자로 ID를 구성해 주십시오.");

					continue;
				} else if (isIdDuplicated(id)) {
					System.out.println("중복된 ID가 존재합니다. 다른 ID를 입력하십시오.");

					continue;
				}

				break;
			}

			// Password scan, validity check
			while (true) {
				System.out.print("비밀번호를 입력하십시오. ");
				System.out.print("(최대 16자 영문자, 숫자 또는 기호로 구성, [/취소] - 취소): ");

				password = scanner.nextLine();

				if (password.equals("/취소")) {
					System.out.println("회원가입이 취소되었습니다.");

					return;
				} else if (!isPasswordValid(password)) {
					System.out.println("최대 16자 영문자, 숫자 또는 기호로 비밀번호를 구성해 주십시오.");

					continue;
				}

				break;
			}

			// Password confirmation
			while (true) {
				System.out.print("확인을 위해 비밀번호를 한 번 더 입력해 주십시오. ([/취소] - 취소): ");

				String passwordConfirmation = scanner.nextLine();

				if (passwordConfirmation.equals("/취소")) {
					System.out.println("회원가입이 취소되었습니다.");

					return;
				} else if (passwordConfirmation.equals(password)) {
					break;
				} else {
					System.out.println("비밀번호 확인이 일치하지 않습니다.");
				}
			}

			// Grade scan (if member type is student)
			switch (memberTypeSelection) {
				case STUDENT -> {
					while (true) {
						System.out.print("학년을 입력하십시오. ([1-4] - 학년 입력, [0] - 취소): ");

						grade = scanner.nextInt();

						if (grade == 0) {
							System.out.println("회원가입이 취소되었습니다.");

							return;
						} else if (grade < 1 || grade > 4) {
							System.out.println("유효하지 않은 학년입니다.");

							continue;
						}

						break;
					}

					Student student = new Student(name, id, password, registrationNumber, grade);

					Core.studentArrayList.add(student);
				}
				case PROFESSOR -> {
					Professor professor = new Professor(name, id, password, registrationNumber);

					Core.professorArrayList.add(professor);
				}
				case STAFF -> {
					Staff staff = new Staff(name, id, password, registrationNumber);

					Core.staffArrayList.add(staff);
				}
				default -> {
				}
			}

			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("회원님의 ID는 " + id + "입니다.");
			System.out.println("회원님의 비밀번호는 " + password + "입니다.");
			System.out.println("회원가입이 정상적으로 처리되었습니다.");

			break;
		}
	}

	static void runSignInUi() {
		Scanner scanner = new Scanner(System.in);
		String id;
		String password;

		System.out.print("ID를 입력하십시오. ([/취소] - 취소): ");

		id = scanner.nextLine();

		if (id.equals("/취소")) {
			System.out.println("로그인이 취소되었습니다.");

			return;
		}

		System.out.print("비밀번호를 입력하십시오. ([/취소] - 취소): ");

		password = scanner.nextLine();

		if (password.equals("/취소")) {
			System.out.println("로그인이 취소되었습니다.");

			return;
		}

		Member member = isSignInValid(id, password);

		if (member == null) {
			System.out.println("ID나 비밀번호가 잘못되었습니다.");
		} else {
			System.out.println("-----------------------------------------------------------------------------");
			member.runMemberUi();
		}
	}

	static boolean isRegistrationNumberValid(String registrationNumber) {
		if (registrationNumber.length() != 14) { // Number of total character check
			return false;
		} else if (registrationNumber.charAt(6) != '-') { // Hyphen check
			return false;
		}

		int i;
		int[] parsedRegistrationNumber = new int[14];

		// Digit check
		for (i = 0; i < registrationNumber.length(); i++) {
			if (i != 6) {
				if (!Character.isDigit(registrationNumber.charAt(i))) {
					return false;
				} else {
					parsedRegistrationNumber[i] = registrationNumber.charAt(i) - '0';
				}
			}
		}

		int parsedYear = parsedRegistrationNumber[0] * 10 + parsedRegistrationNumber[1];
		int parsedMonth = parsedRegistrationNumber[2] * 10 + parsedRegistrationNumber[3];
		int parsedDate = parsedRegistrationNumber[4] * 10 + parsedRegistrationNumber[5];

		// Birthday validation (check if date exists actually)
		if (parsedMonth == 2) {
			if ((parsedYear % 4) == 0) {
				return (parsedDate >= 1 && parsedDate <= 29); // February at leap year
			} else {
				return (parsedDate >= 1 && parsedDate <= 28); // February at non-leap year
			}
		} else {
			if (parsedMonth < 8) {
				if ((parsedMonth % 2) == 0) {
					return (parsedDate >= 1 && parsedDate <= 30); // April, June
				} else {
					return (parsedDate >= 1 && parsedDate <= 31); // January, March, May, July
				}
			} else {
				if ((parsedMonth % 2) == 0) {
					return (parsedDate >= 1 && parsedDate <= 31); // August, October, December
				} else {
					return (parsedDate >= 1 && parsedDate <= 30); // September, November
				}
			}
		}
	}

	static boolean isRegistrationNumberDuplicated(String registrationNumber) {
		for (Student element : Core.studentArrayList) {
			if (registrationNumber.equals(element.getRegistrationNumber())) {
				return true;
			}
		}

		for (Professor element : Core.professorArrayList) {
			if (registrationNumber.equals(element.getRegistrationNumber())) {
				return true;
			}
		}

		for (Staff element : Core.staffArrayList) {
			if (registrationNumber.equals(element.getRegistrationNumber())) {
				return true;
			}
		}

		return false;
	}

	static boolean isIdDuplicated(String id) {
		for (Student element : Core.studentArrayList) {
			if (id.equals(element.getId())) {
				return true;
			}
		}

		for (Professor element : Core.professorArrayList) {
			if (id.equals(element.getId())) {
				return true;
			}
		}

		for (Staff element : Core.staffArrayList) {
			if (id.equals(element.getId())) {
				return true;
			}
		}

		return false;
	}

	static boolean isIdValid(String id) {
		for (char element : id.toCharArray()) {
			if (!Character.isLowerCase(element) && !Character.isDigit(element)) {
				return false;
			}
		}

		return id.length() <= 16;
	}

	static boolean isPasswordValid(String password) {
		for (char element : password.toCharArray()) {
			if (element < 0x21 || element > 0x7e) { // 1-byte character
				return false;
			}
		}

		return password.length() <= 16;
	}

	static Member isSignInValid(String id, String password) {
		for (Student element : Core.studentArrayList) {
			if (id.equals(element.getId()) && password.equals(element.getPassword())) {
				return element;
			}
		}

		for (Professor element : Core.professorArrayList) {
			if (id.equals(element.getId()) && password.equals(element.getPassword())) {
				return element;
			}
		}

		for (Staff element : Core.staffArrayList) {
			if (id.equals(element.getId()) && password.equals(element.getPassword())) {
				return element;
			}
		}

		return null;
	}
}
