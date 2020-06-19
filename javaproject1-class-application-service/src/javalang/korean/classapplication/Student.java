package javalang.korean.classapplication;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Member implements Scholar {
	private final int grade;
	private final ArrayList<Lecture> lectureArrayList = new ArrayList<>();

	public Student(String name, String id, String password, String registrationNumber, int grade) {
		super(name, id, password, registrationNumber);
		this.grade = grade;
	}

	int getGrade() {
		return this.grade;
	}

	@Override
	public void runMemberUi() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println(this.getName() + " 님, 환영합니다.");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("[1] - 수강신청");
			System.out.println("[2] - 수강 목록 확인");
			System.out.println("[3] - 비밀번호 변경");
			System.out.println("[4] - 로그아웃");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("메뉴를 선택하십시오. ([1-4]): ");

			int menuSelection = scanner.nextInt();

			switch (menuSelection) {
				case 1 -> runRegisterLectureUi();
				case 2 -> runCheckLectureArrayListUi();
				case 3 -> super.runChangePasswordUi();
				case 4 -> {
					return;
				}
				default -> System.out.println("존재하지 않는 메뉴입니다.");
			}
		}
	}

	@Override
	public void runRegisterLectureUi() {
		Scanner scanner = new Scanner(System.in);

		Core.printLectureArrayList();
		
		while (true) {
			System.out.print("등록할 강의의 번호를 입력해 주십시오. ([0] - 수강신청 종료): ");

			int menuSelection = scanner.nextInt();

			if (menuSelection < 0 || menuSelection > Core.lectureArrayList.size()) {
				System.out.println("존재하지 않는 과목입니다.");
			} else if (menuSelection == 0) {
				System.out.println("수강신청을 종료합니다.");

				break;
			} else {
				Lecture lecture = Core.lectureArrayList.get(menuSelection - 1);

				if (isGradeEnough(lecture) && !isLectureDuplicated(lecture) && isTimeAvailable(lecture.getTime())) {
					lectureArrayList.add(lecture);
					System.out.println(lecture.getName() + " 강의을 신청했습니다.");
				}
			}
		}
	}

	@Override
	public void runCheckLectureArrayListUi() {
		int i = 1;
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("신청하신 강의 목록입니다.");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("| No. |          과목명          |      교수명      |  시간  | 수강가능학년 |");
		System.out.println("|-----+--------------------------+------------------+--------+--------------|");

		for (Lecture element: this.lectureArrayList) {
			System.out.printf(
				"| %3d | %s | %s | %s |      %2d      |",
				i,
				Core.alignString(element.getName(), 24),
				Core.alignString(element.getProfessorName(), 16),
				element.getTime(),
				element.getMinimumGrade()
			);
			System.out.println();

			i++;
		}

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------");
	}

	boolean isGradeEnough(Lecture lecture) {
		if (lecture.getMinimumGrade() > this.grade) {
			System.out.println("신청 자격을 갖추지 못했습니다. 해당 강의를 수강할 수 없습니다.");
			return false;
		} else {
			return true;
		}
	}

	boolean isLectureDuplicated(Lecture lecture) {
		for (Lecture element : this.lectureArrayList) {
			if (lecture == element || lecture.getName().equals(element.getName())) {
				System.out.println("중복 신청된 강의입니다.");
				System.out.println("중복 신청된 강의명: " + element.getName());

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isTimeAvailable(String lectureTime) {
		int[] timeCode = new int[2];

		timeCode[0] = Weekday.ordinalOf(lectureTime.charAt(0)) * 5 + lectureTime.charAt(1) - 'A';
		timeCode[1] = Weekday.ordinalOf(lectureTime.charAt(2)) * 5 + lectureTime.charAt(3) - 'A';

		for (Lecture element : this.lectureArrayList) {
			if (timeCode[0] == element.getTimeCode(0)
				|| timeCode[0] == element.getTimeCode(1)
				|| timeCode[1] == element.getTimeCode(0)
				|| timeCode[1] == element.getTimeCode(1)
			) {
				System.out.println("시간이 중복되는 강의가 존재합니다.");
				System.out.println("시간이 중복되는 강의명: " + element.getName());

				return false;
			}
		}

		return true;
	}
}
