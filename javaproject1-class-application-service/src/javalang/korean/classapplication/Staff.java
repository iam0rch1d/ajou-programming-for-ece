package javalang.korean.classapplication;

import java.util.Scanner;

class Staff extends Member {
	Staff(String name, String id, String password, String registrationNumber) {
		super(name, id, password, registrationNumber);
	}

	@Override
	void runMemberUi() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println(this.getName() + " 님, 환영합니다.");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("[1] - 등록된 강의 목록");
			System.out.println("[2] - 등록된 학생 목록");
			System.out.println("[3] - 등록된 교수 목록");
			System.out.println("[4] - 비밀번호 변경");
			System.out.println("[5] - 로그아웃");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("메뉴를 선택하십시오. ([1-5]): ");

			int menuSelection = scanner.nextInt();

			switch (menuSelection) {
				case 1 -> Core.printLectureArrayList();
				case 2 -> Core.printStudentArrayList();
				case 3 -> Core.printProfessorArrayList();
				case 4 -> super.runChangePasswordUi();
				case 5 -> {
					return;
				}
				default -> System.out.println("존재하지 않는 메뉴입니다.");
			}
		}
	}
}
