package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterLectureUi extends JPanel implements ActionListener {
    int rows = CoreWithGui.lectureArrayList.size() + 1;
    String[] legend = {"No.", "과목명", "교수명", "시간", "수강가능학년", ""};
    JButton[] button = new JButton[rows - 1];

    public RegisterLectureUi(Member member) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(rows, 6, 5, 5));

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < 6; column++) {
                if (row == 0) {
                    add(new JLabel(legend[column]));
                } else switch (column) {
                    case 0 -> add(new JTextArea("" + row));
                    case 1 -> add(new JTextArea(CoreWithGui.lectureArrayList.get(row - 1).getName()));
                    case 2 -> add(new JTextArea(CoreWithGui.lectureArrayList.get(row - 1).getProfessorName()));
                    case 3 -> add(new JTextArea(CoreWithGui.lectureArrayList.get(row - 1).getTime()));
                    case 4 -> add(new JTextArea("" + CoreWithGui.lectureArrayList.get(row - 1).getMinimumGrade()));
                    default -> {
                        button[row - 1] = new JButton("신청");
                        add(button[row - 1]);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
