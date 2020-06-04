class Main {
	public static void main(String[] args) {
		Student student1 = new Student();
		Student student2 = new Student(23, "Junyeong");
		Student student3 = new Student("Jaeho", 25);
		Student student4 = new Student(11);
		Student student5 = new Student("Hyerim");
		
		student1.printInformation();
		student2.printInformation();
		student3.printInformation();
		student4.printInformation();
		student5.printInformation();

		System.gc();
	}
}
