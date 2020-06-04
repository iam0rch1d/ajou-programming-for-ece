package iam0rch1d.java.korean.classapplication;

import java.util.Scanner;

class Member {
	private final String name;
	private final String id;
	private String password;
	private final String registrationNumber;

	Member(String name, String id, String password, String registrationNumber) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.registrationNumber = registrationNumber;
	}

	String getName() {
		return this.name;
	}

	String getId() {
		return this.id;
	}

	String getPassword() {
		return this.password;
	}

	String getRegistrationNumber() {
		return this.registrationNumber;
	}

	void runMemberUi() {
	}

	void runChangePasswordUi() {
		Scanner scanner = new Scanner(System.in);
		String passwordTemp;

		// Password scan, validity check
		while (true) {
			System.out.print("새로운 비밀번호를 입력하십시오. ");
			System.out.print("(최대 16자 영문자, 숫자 또는 기호로 구성, [/취소] - 취소): ");

			passwordTemp = scanner.nextLine();

			if (passwordTemp.equals("/취소")) {
				System.out.println("비밀번호 변경이 취소되었습니다.");
				System.out.println("-----------------------------------------------------------------------------");

				return;
			} else if (!Entrance.isPasswordValid(passwordTemp)) {
				System.out.println("최대 16자 영문자, 숫자 또는 기호로 비밀번호를 구성해 주십시오.");

				continue;
			} else if (passwordTemp.equals(password)) {
				System.out.print("비밀번호가 변경되지 않았습니다. ");
				System.out.println("비밀번호 변경을 취소하려면 [/취소]를 입력해 주십시오.");

				continue;
			}

			break;
		}

		// Password confirmation
		while (true) {
			System.out.print("확인을 위해 비밀번호를 한번 더 입력해 주십시오. ([/취소] - 취소): ");

			String passwordConfirmation = scanner.nextLine();

			if (passwordConfirmation.equals("/취소")) {
				System.out.println("비밀번호 변경이 취소되었습니다.");

				return;
			} else if (passwordConfirmation.equals(passwordTemp)) {
				password = passwordTemp;

				System.out.println("비밀번호가 변경되었습니다.");
				System.out.println("-----------------------------------------------------------------------------");

				break;
			} else {
				System.out.println("비밀번호 확인이 일치하지 않습니다.");
			}
		}
	}
}
