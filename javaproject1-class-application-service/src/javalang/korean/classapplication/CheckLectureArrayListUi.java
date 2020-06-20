package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CheckLectureArrayListUi extends JPanel implements ActionListener {
	private final Student student;
	private JButton refreshButton;

	CheckLectureArrayListUi(Student student) {
		this.student = student;

		printLectureArrayList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(refreshButton)) {
			removeAll();
			printLectureArrayList();
		}
	}

	private void printLectureArrayList() {
		final int NUM_COLUMN = 5;
		final int MARGIN = 5;

		int numRow;

		if (student.getLectureArrayList().isEmpty()) {
			numRow = student.getLectureArrayList().size() + 3;
		} else {
			numRow = student.getLectureArrayList().size() + 2;
		}

		setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		setLayout(new GridLayout(numRow, NUM_COLUMN, MARGIN, MARGIN));

		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < NUM_COLUMN; j++) {
				if (i == 0) {
					JLabel[] legendLabel = new JLabel[NUM_COLUMN];
					String[] legendText = {"No.", "과목명", "교수명", "시간", "수강가능학년"};
					legendLabel[j] = new JLabel(legendText[j]);

					legendLabel[j].setHorizontalAlignment(JLabel.CENTER);
					add(legendLabel[j]);
				} else if (i == numRow - 1) {
					if (j == 0) {
						refreshButton = new JButton("새로고침");

						add(refreshButton);
						refreshButton.addActionListener(this);
					} else {
						add(new JLabel());
					}
				} else if (student.getLectureArrayList().size() > 0) switch (j) {
					case 0 -> {
						JLabel lectureNoLabel = new JLabel("" + i);

						lectureNoLabel.setHorizontalAlignment(JLabel.CENTER);
						add(lectureNoLabel);
					}
					case 1 -> {
						JLabel nameLabel = new JLabel(student.getLectureArrayList().get(i - 1).getName());

						nameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(nameLabel);
					}
					case 2 -> {
						JLabel professorNameLabel = new JLabel(
							student.getLectureArrayList().get(i - 1).getProfessorName()
						);

						professorNameLabel.setHorizontalAlignment(JLabel.CENTER);
						add(professorNameLabel);
					}
					case 3 -> {
						JLabel timeLabel = new JLabel(student.getLectureArrayList().get(i - 1).getTime());

						timeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(timeLabel);
					}
					default -> {
						JLabel minimumGradeLabel = new JLabel(
							"" + student.getLectureArrayList().get(i - 1).getMinimumGrade()
						);

						minimumGradeLabel.setHorizontalAlignment(JLabel.CENTER);
						add(minimumGradeLabel);
					}
				} else if (student.getLectureArrayList().isEmpty()) {
					if (i == 1 && j == 2) {
						JLabel emptyMessageLabel = new JLabel("등록된 강의가 없습니다.");

						emptyMessageLabel.setHorizontalAlignment(JLabel.CENTER);
						add(new JLabel("등록된 강의가 없습니다."));
					} else {
						add(new JLabel());
					}
				}
			}
		}
	}
}
