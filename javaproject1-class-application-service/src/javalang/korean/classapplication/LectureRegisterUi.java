package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

class LectureRegisterUi extends JPanel implements ActionListener {
	private final Student student;
	private final int numRow = CoreWithGui.lectureArrayList.size() + 1;
	private final JButton[] button = new JButton[numRow - 1];

	LectureRegisterUi(Student student) {
		this.student = student;

		final int MARGIN = 5;
		final int NUM_COLUMN = 6;

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
				} else switch (j) {
					case 0 -> add(new JLabel("" + i));
					case 1 -> add(new JLabel(CoreWithGui.lectureArrayList.get(i - 1).getName()));
					case 2 -> add(new JLabel(CoreWithGui.lectureArrayList.get(i - 1).getProfessorName()));
					case 3 -> add(new JLabel(CoreWithGui.lectureArrayList.get(i - 1).getTime()));
					case 4 -> add(new JLabel("" + CoreWithGui.lectureArrayList.get(i - 1).getMinimumGrade()));
					default -> {
						button[i - 1] = new JButton("신청");

						add(button[i - 1]);
						button[i - 1].addActionListener(this);
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		IntStream.range(0, CoreWithGui.lectureArrayList.size()).forEach(
			i -> {
				if (e.getSource().equals(button[i])) {
					Lecture lecture = CoreWithGui.lectureArrayList.get(i);

					if (lecture.getMinimumGrade() > student.getGrade()) {
						JOptionPane.showMessageDialog(
							null,
							"신청 자격을 갖추지 못했습니다.",
							"수강신청 오류",
							JOptionPane.ERROR_MESSAGE
						);

						return;
					}

					for (Lecture element : student.getLectureArrayList()) {
						if (element.getName().equals(lecture.getName())) {
							JOptionPane.showMessageDialog(
								null,
								"[" + element.getName() + "] 강의는 이미 신청한 강의입니다.",
								"수강신청 오류",
								JOptionPane.ERROR_MESSAGE
							);

							return;
						}
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
	}
}
