package iam0rch1d.java.korean.classapplication;

class Lecture {
	private final String name;
	private final String professorName;
	private final String time; // Form of '[weekday0][timeAlphabet0][weekday1][timeAlphabet1]'
	private final int[] timeCode = new int[2];
	private final int minimumGrade;

	Lecture(String name, String professorName, String time, int minimumGrade) {
		this.name = name;
		this.professorName = professorName;
		this.time = time;
		this.minimumGrade = minimumGrade;
		timeCode[0] = Weekday.ordinalOf(time.charAt(0)) * 5 + time.charAt(1) - 'A';
		timeCode[1] = Weekday.ordinalOf(time.charAt(2)) * 5 + time.charAt(3) - 'A';
	}

	String getName() {
		return this.name;
	}

	String getProfessorName() {
		return this.professorName;
	}

	String getTime() {
		return this.time;
	}

	int getMinimumGrade() {
		return this.minimumGrade;
	}

	int getTimeCode(int index) {
		return this.timeCode[index];
	}
}
