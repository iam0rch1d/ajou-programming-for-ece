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
					case 0 -> add(new JLabel("" + i));
					case 1 -> add(new JLabel(student.getLectureArrayList().get(i - 1).getName()));
					case 2 -> add(new JLabel(student.getLectureArrayList().get(i - 1).getProfessorName()));
					case 3 -> add(new JLabel(student.getLectureArrayList().get(i - 1).getTime()));
					case 4 -> add(new JLabel("" + student.getLectureArrayList().get(i - 1).getMinimumGrade()));
				} else if (student.getLectureArrayList().size() == 0) {
					if (i == 1 && j == 2) {
						add(new JLabel("등록된 강의가 없습니다."));
					} else {
						add(new JLabel());
					}
				}
			}
		}
	}
}
