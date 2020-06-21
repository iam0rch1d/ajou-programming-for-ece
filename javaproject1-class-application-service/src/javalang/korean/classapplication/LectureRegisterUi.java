package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

class LectureRegisterUi extends JPanel implements ActionListener {
	private final Student student;
	private final Professor professor;
	private final ArrayList<JButton> studentButton = new ArrayList<>();
	private final JTextField lectureNameField = new JTextField();
	private final JTextField lectureTimeField = new JTextField();
	private final JTextField lectureMinimumGradeField = new JTextField();
	private final JButton professorButton = new JButton("등록");

	LectureRegisterUi(Student student) {
		this.student = student;
		this.professor = null;

		final int MARGIN = 4;
		final int NUM_COLUMN = 6;

		int numRow = CoreWithGui.lectureArrayList.size() + 2;

		setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		setLayout(new GridLayout(numRow, NUM_COLUMN, MARGIN, MARGIN));

		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < NUM_COLUMN; j++) {
				if (i == 0) {
					JLabel[] legendLabel = new JLabel[NUM_COLUMN];
					String[] legendText = {"No.", "과목명", "교수명", "시간", "수강가능학년", ""};
					legendLabel[j] = new JLabel(legendText[j]);

					legendLabel[j].setHorizontalAlignment(JLabel.CENTER);
					add(legendLabel[j]);
				} else if (i == 1) {
					add(new JSeparator());
				}
				else switch (j) {
					case 0 -> {
						JLabel lectureNoLabel = new JLabel("" + (i - 1));

						lectureNoLabel.setHorizontalAlignment(JLabel.CENTER);
						add(lectureNoLabel);
					}
					case 1 -> {
						JLabel nameLabel = new JLabel(CoreWithGui.lectureArrayList.get(i - 2).getName());

						nameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(nameLabel);
					}
					case 2 -> {
						JLabel professorNameLabel = new JLabel(
							CoreWithGui.lectureArrayList.get(i - 2).getProfessorName()
						);

						professorNameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(professorNameLabel);
					}
					case 3 -> {
						JLabel timeLabel = new JLabel(CoreWithGui.lectureArrayList.get(i - 2).getTime());

						timeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(timeLabel);
					}
					case 4 -> {
						JLabel minimumGradeLabel = new JLabel(
							"" + CoreWithGui.lectureArrayList.get(i - 2).getMinimumGrade()
						);

						minimumGradeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(minimumGradeLabel);
					}
					default -> {
						studentButton.add(new JButton("신청"));

						add(studentButton.get(i - 2));
						studentButton.get(i - 2).addActionListener(this);
					}
				}
			}
		}
	}

	LectureRegisterUi(Professor professor) {
		this.student = null;
		this.professor = professor;

		final int MARGIN = 4;

		setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		setLayout(new GridLayout(7, 1, MARGIN, MARGIN));

		for (int i = 0; i < CoreWithGui.lectureArrayList.size(); i++) {
			studentButton.add(new JButton());
		}

		add(new JLabel("강의명"));
		add(lectureNameField);
		add(new JLabel(
			"<html><p>강의 시간 ([요일1][시간1][요일2][시간2] 형태로 구성,<br>"
				+ "[요일]은 [월, 화, 수, 목, 금] 중 하나를 입력,<br>"
				+ "[시간]은 [A, B, C, D, E] 중 하나를 입력)</p><html>"
		));
		add(lectureTimeField);
		add(new JLabel("수강 가능 최소 학년 ([1-4])"));
		add(lectureMinimumGradeField);
		add(professorButton);
		professorButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Action performed by student
		if (student != null && professor == null) {
			IntStream.range(0, CoreWithGui.lectureArrayList.size()).forEach(
				i -> {
					if (e.getSource().equals(studentButton.get(i))) {
						Lecture lecture = CoreWithGui.lectureArrayList.get(i);

						if (!student.isGradeEnough(lecture)) {
							JOptionPane.showMessageDialog(
								null,
								"신청 자격을 갖추지 못했습니다.",
								"수강신청 오류",
								JOptionPane.ERROR_MESSAGE
							);

							return;
						}

						if (student.isLectureDuplicated(lecture)) {
							JOptionPane.showMessageDialog(
								null,
								"[" + lecture.getName() + "] 강의는 이미 신청한 강의입니다.",
								"수강신청 오류",
								JOptionPane.ERROR_MESSAGE
							);

							return;
						}

						for (Lecture element : student.getLectureArrayList()) {
							if (element.getTimeCode(0) == lecture.getTimeCode(0)
								|| element.getTimeCode(0) == lecture.getTimeCode(1)
								|| element.getTimeCode(1) == lecture.getTimeCode(0)
								|| element.getTimeCode(1) == lecture.getTimeCode(1)) {
								JOptionPane.showMessageDialog(
									null,
									"[" + element.getName() + "] 강의와 시간이 중복됩니다.",
									"수강신청 오류",
									JOptionPane.ERROR_MESSAGE
								);

								return;
							}
						}

						student.getLectureArrayList().add(lecture);
						JOptionPane.showMessageDialog(
							null,
							"[" + lecture.getName() + "] 강의을 신청했습니다.",
							"수강신청 완료",
							JOptionPane.INFORMATION_MESSAGE
						);
					}
				}
			);

			return;
		}

		// Action performed by professor
		if (student == null && professor != null) {
			if (e.getSource().equals(professorButton)) {
				String lectureName = lectureNameField.getText();

				if (lectureName.equals("")) {
					JOptionPane.showMessageDialog(
						null,
						"강의명을 입력해 주십시오.",
						"강의 등록 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				String lectureTime = lectureTimeField.getText().toUpperCase();
				int[] lectureTimeCode = {
					Weekday.ordinalOf(lectureTime.charAt(0)) * 5 + lectureTime.charAt(1) - 'A',
					Weekday.ordinalOf(lectureTime.charAt(2)) * 5 + lectureTime.charAt(3) - 'A'
				};

				if (!professor.isLectureTimeValid(lectureTime)) {
					JOptionPane.showMessageDialog(
						null,
						"[요일1][시간1][요일2][시간2]의 형태로 강의 시간을 구성해 주십시오.",
						"강의 등록 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				for (Lecture element : professor.getLectureArrayList()) {
					if (element.getTimeCode(0) == lectureTimeCode[0]
						|| element.getTimeCode(0) == lectureTimeCode[1]
						|| element.getTimeCode(1) == lectureTimeCode[0]
						|| element.getTimeCode(1) == lectureTimeCode[1]) {
						JOptionPane.showMessageDialog(
							null,
							"[" + element.getName() + "] 강의와 시간이 중복됩니다.",
							"수강신청 오류",
							JOptionPane.ERROR_MESSAGE
						);

						return;
					}
				}

				int lectureMinimumGrade;

				try {
					lectureMinimumGrade = Integer.parseInt(lectureMinimumGradeField.getText());

					if (lectureMinimumGrade < 1 || lectureMinimumGrade > 4) {
						JOptionPane.showMessageDialog(
							null,
							"유효하지 않은 학년입니다.",
							"강의 등록 오류",
							JOptionPane.ERROR_MESSAGE
						);

						return;
					}

					Lecture lecture = new Lecture(lectureName, super.getName(), lectureTime, lectureMinimumGrade);

					professor.lectureArrayList.add(lecture);
					CoreWithGui.lectureArrayList.add(lecture);
					JOptionPane.showMessageDialog(
						null,
						"[" + lecture.getName() + "] 강의을 개설했습니다.",
						"강의 개설 완료",
						JOptionPane.INFORMATION_MESSAGE
					);
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(
						null,
						"숫자 형태의 학년을 입력해 주십시오.",
						"강의 등록 오류",
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		}
	}
}
