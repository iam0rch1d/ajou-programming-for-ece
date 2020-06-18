package javalang.korean.classapplication;

public class Initialization {
	public static void initialize() {
		// Student sign-up
		Core.studentArrayList.add(
			new Student(
				"장현웅",
				"sas1200",
				"1234",
				"951014-1437811", 2
			)
		);
		Core.studentArrayList.add(
			new Student(
				"임창남",
				"chn0714",
				"1234",
				"961225-1164831", 1
			)
		);
		
		// Professor sign-up
		Core.professorArrayList.add(
			new Professor("이미연",
				"mylee",
				"1234",
				"901225-2164611"
			)
		);
		Core.professorArrayList.add(
			new Professor(
				"이정원",
				"jungwony",
				"1234",
				"801225-2164611"
			)
		);
		Core.professorArrayList.add(
			new Professor(
				"이세돌",
				"saedol",
				"1234",
				"770412-1743711"
			)
		);
		Core.professorArrayList.add(
			new Professor(
				"정형돈",
				"jung",
				"1234",
				"880820-2313813"
			)
		);
		
		// Staff sign-up
		Core.staffArrayList.add(
			new Staff(
				"홍길동",
				"hong",
				"1234",
				"941030-1919134"
			)
		);
		
		// Lectures
		Core.professorArrayList.get(0).registerLecture(
			"전자공학프로그래밍",
			"월B목B",
			2
		);
		Core.professorArrayList.get(0).registerLecture(
			"전자공학프로그래밍",
			"수B금B",
			2
		);
		Core.professorArrayList.get(1).registerLecture(
			"자료구조및알고리즘이해",
			"화A금A",
			2
		);
		Core.professorArrayList.get(1).registerLecture(
			"자료구조및알고리즘이해",
			"화C금C",
			2
		);
		Core.professorArrayList.get(2).registerLecture(
			"C++",
			"월B금B",
			3
		);
		Core.professorArrayList.get(2).registerLecture(
			"Python",
			"화C금C",
			3
		);
		Core.professorArrayList.get(3).registerLecture(
			"프로그래밍기초및실습",
			"월C목B",
			1
		);
		Core.professorArrayList.get(3).registerLecture(
			"프로그래밍기초및실습",
			"화A금B",
			1
		);
	}
}
