package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LectureArrayListCheckUiPanel extends JPanel implements ActionListener {
	private final JButton refreshButton;
	private final Member member;

	LectureArrayListCheckUiPanel(Member member) {
		refreshButton = new JButton("새로고침");
		this.member = member;

		if (member.getClass().getSimpleName().equals("Student")) {
			printLectureArrayList((Student) this.member);
		} else {
			printLectureArrayList((Professor) this.member);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(refreshButton)) {
			removeAll();

			if (member.getClass().getSimpleName().equals("Student")) {
				printLectureArrayList((Student) member);
			} else {
				printLectureArrayList((Professor) member);
			}
		}
	}

	private void printLectureArrayList(Student student) {
		final int MARGIN = 4;
		final int NUM_COLUMN = 5;

		int numRow = student.getLectureArrayList().size() + 2;

		if (!student.getLectureArrayList().isEmpty()) {
			numRow += 2;
		}

		setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		setLayout(new GridLayout(numRow, NUM_COLUMN, MARGIN, MARGIN));

		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < NUM_COLUMN; j++) {
				if (student.getLectureArrayList().isEmpty()) {
					if (i == 0 && j == 0) {
						JLabel emptyMessageLabel = new JLabel("등록된 강의가 없습니다.");

						emptyMessageLabel.setHorizontalAlignment(JLabel.CENTER);
						add(new JLabel("등록된 강의가 없습니다."));
					} else if (i == numRow - 1 && j == 0) {
						add(refreshButton);
						refreshButton.addActionListener(this);
					} else {
						add(new JLabel());
					}
				} else if (i == 0) {
					JLabel[] legendLabel = new JLabel[NUM_COLUMN];
					String[] legendText = {"No.", "과목명", "교수명", "시간", "수강가능학년"};
					legendLabel[j] = new JLabel(legendText[j]);

					legendLabel[j].setHorizontalAlignment(JLabel.CENTER);
					add(legendLabel[j]);
				} else if (i == 1 || i == numRow - 2)
					add(new JSeparator());
				else if (i == numRow - 1) {
					if (j == 0) {
						add(refreshButton);
						refreshButton.addActionListener(this);
					} else {
						add(new JLabel());
					}
				} else switch (j) {
					case 0 -> {
						JLabel lectureNoLabel = new JLabel("" + (i - 1));

						lectureNoLabel.setHorizontalAlignment(JLabel.CENTER);
						add(lectureNoLabel);
					}
					case 1 -> {
						JLabel nameLabel = new JLabel(student.getLectureArrayList().get(i - 2).getName());

						nameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(nameLabel);
					}
					case 2 -> {
						JLabel professorNameLabel = new JLabel(
							student.getLectureArrayList().get(i - 2).getProfessorName()
						);

						professorNameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(professorNameLabel);
					}
					case 3 -> {
						JLabel timeLabel = new JLabel(student.getLectureArrayList().get(i - 2).getTime());

						timeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(timeLabel);
					}
					default -> {
						JLabel minimumGradeLabel = new JLabel(
							"" + student.getLectureArrayList().get(i - 2).getMinimumGrade()
						);

						minimumGradeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(minimumGradeLabel);
					}
				}
			}
		}
	}

	private void printLectureArrayList(Professor professor) {
		final int MARGIN = 4;
		final int NUM_COLUMN = 4;

		int numRow = professor.getLectureArrayList().size() + 2;

		if (!professor.getLectureArrayList().isEmpty()) {
			numRow += 2;
		}

		setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		setLayout(new GridLayout(numRow, NUM_COLUMN, MARGIN, MARGIN));

		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < NUM_COLUMN; j++) {
				if (professor.getLectureArrayList().isEmpty()) {
					if (i == 0 && j == 0) {
						JLabel emptyMessageLabel = new JLabel("등록된 강의가 없습니다.");

						emptyMessageLabel.setHorizontalAlignment(JLabel.CENTER);
						add(new JLabel("등록된 강의가 없습니다."));
					} else if (i == numRow - 1 && j == 0) {
						add(refreshButton);
						refreshButton.addActionListener(this);
					} else {
						add(new JLabel());
					}
				} else if (i == 0) {
					JLabel[] legendLabel = new JLabel[NUM_COLUMN];
					String[] legendText = {"No.", "과목명", "시간", "수강가능학년"};
					legendLabel[j] = new JLabel(legendText[j]);

					legendLabel[j].setHorizontalAlignment(JLabel.CENTER);
					add(legendLabel[j]);
				} else if (i == 1 || i == numRow - 2) {
					add(new JSeparator());
				} else if (i == numRow - 1) {
					if (j == 0) {
						add(refreshButton);
						refreshButton.addActionListener(this);
					} else {
						add(new JLabel());
					}
				} else switch (j) {
					case 0 -> {
						JLabel lectureNoLabel = new JLabel("" + (i - 1));

						lectureNoLabel.setHorizontalAlignment(JLabel.CENTER);
						add(lectureNoLabel);
					}
					case 1 -> {
						JLabel nameLabel = new JLabel(professor.getLectureArrayList().get(i - 2).getName());

						nameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(nameLabel);
					}
					case 2 -> {
						JLabel timeLabel = new JLabel(professor.getLectureArrayList().get(i - 2).getTime());

						timeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(timeLabel);
					}
					default -> {
						JLabel minimumGradeLabel = new JLabel(
							"" + professor.getLectureArrayList().get(i - 2).getMinimumGrade()
						);

						minimumGradeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(minimumGradeLabel);
					}
				}
			}
		}
	}
}
