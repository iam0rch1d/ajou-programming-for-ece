package javalang.korean.classapplication;

import java.util.Scanner;

class Professor extends Member implements Scholar {
	Professor(String name, String id, String password, String registrationNumber) {
		super(name, id, password, registrationNumber);
	}

	@Override
	void runMemberUi() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println(this.getName() + " 교수님, 환영합니다.");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("[1] - 강의 등록");
			System.out.println("[2] - 강의 목록 확인");
			System.out.println("[3] - 비밀번호 변경");
			System.out.println("[4] - 로그아웃");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("메뉴를 선택하십시오. ([1-4]): ");

			int menuSelection = scanner.nextInt();

			switch (menuSelection) {
				case 1 -> runLectureRegisterUi();
				case 2 -> runLectureArrayListCheckUi();
				case 3 -> super.runPasswordChangeUi();
				case 4 -> {
					return;
				}
				default -> System.out.println("존재하지 않는 메뉴입니다.");
			}
		}
	}

	@Override
	public void runLectureRegisterUi() {
		Scanner scanner = new Scanner(System.in);
		String lectureName;
		String lectureTime;
		int lectureMinimumGrade;

		System.out.print("개설할 강의의 이름을 입력하십시오. ([/취소] - 취소): ");

		lectureName = scanner.nextLine();

		if (lectureName.equals("/취소")) {
			System.out.println("강의 개설이 취소되었습니다.");
			System.out.println("-----------------------------------------------------------------------------");

			return;
		}

		while (true) {
			System.out.println("강의 시간을 입력하십시오. ([요일1][시간1][요일2][시간2] 형태로 구성, [/취소] - 취소");
			System.out.print("[요일]은 [월, 화, 수, 목, 금] 중 하나를 입력, ");
			System.out.print("[시간]은 [A, B, C, D, E] 중 하나를 입력): ");

			lectureTime = scanner.nextLine().toUpperCase();

			if (lectureTime.equals("/취소")) {
				System.out.println("강의 개설이 취소되었습니다.");
				System.out.println("-----------------------------------------------------------------------------");

				return;
			} else if (!isLectureTimeValid(lectureTime)) {
				System.out.println("[요일1][시간1][요일2][시간2]의 형태로 강의 시간을 구성해 주십시오.");

				continue;
			} else if (!isTimeAvailable(lectureTime)) {
				continue;
			}

			break;
		}

		while (true) {
			System.out.print("수강 가능한 최소 학년을 입력하십시오. ");
			System.out.print("([1-4] - 수강 가능 최소 학년 입력, [0] - 취소): ");

			lectureMinimumGrade = scanner.nextInt();

			if (lectureMinimumGrade == 0) {
				System.out.println("강의 개설이 취소되었습니다.");
				System.out.println("-----------------------------------------------------------------------------");

				return;
			} else if (lectureMinimumGrade < 1 || lectureMinimumGrade > 4) {
				System.out.println("유효하지 않은 학년입니다.");

				continue;
			}

			break;
		}

		Lecture lecture = new Lecture(lectureName, super.getName(), lectureTime, lectureMinimumGrade);

		lectureArrayList.add(lecture);
		Core.lectureArrayList.add(lecture);

		System.out.println(lecture.getName() + " 강의를 개설했습니다.");
		System.out.println("-----------------------------------------------------------------------------");
	}

	@Override
	public void runLectureArrayListCheckUi() {
		int i = 1;

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("개설하신 강의 목록입니다.");
		System.out.println("----------------------------------------------------------");
		System.out.println("| No. |          과목명          |  시간  | 수강가능학년 |");
		System.out.println("|-----+--------------------------+--------+--------------|");

		for (Lecture element : this.lectureArrayList) {
			System.out.printf(
				"| %3d | %s | %s |      %2d      |",
				i,
				Core.alignString(element.getName(), 24),
				element.getTime(),
				element.getMinimumGrade()
			);
			System.out.println();

			i++;
		}

		System.out.println("----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------");
	}

	boolean isLectureTimeValid(String lectureTime) {
		if (lectureTime.length() != 4) {
			return false;
		}

		int i;

		for (i = 0; i < 4; i++) {
			if ((i % 2) == 0
				&& lectureTime.charAt(i) != Weekday.MONDAY.getValue()
				&& lectureTime.charAt(i) != Weekday.TUESDAY.getValue()
				&& lectureTime.charAt(i) != Weekday.WEDNESDAY.getValue()
				&& lectureTime.charAt(i) != Weekday.THURSDAY.getValue()
				&& lectureTime.charAt(i) != Weekday.FRIDAY.getValue()
			) {
				return false;
			} else if ((i % 2) == 1 && (lectureTime.charAt(i) < 'A' || lectureTime.charAt(i) > 'E')) {
				return false;
			}
		}

		return true;
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

	// USED IN [Initialization] CLASS ONLY
	void registerLecture(String name, String time, int minimumGrade) {
		Lecture lecture = new Lecture(name, this.getName(), time, minimumGrade);

		lectureArrayList.add(lecture);
		Core.lectureArrayList.add(lecture);
	}
}
