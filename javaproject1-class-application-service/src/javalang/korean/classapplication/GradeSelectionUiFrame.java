package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GradeSelectionUiFrame extends JFrame implements ActionListener {
	private final String name;
	private final String id;
	private final String password;
	private final String registrationNumber;
	private final ButtonGroup radioButtonGroup;
	private JPanel mainPanel;
	private JRadioButton gradeRadioButton1;
	private JRadioButton gradeRadioButton2;
	private JRadioButton gradeRadioButton3;
	private JRadioButton gradeRadioButton4;
	private JButton okButton;
	private int grade;

	GradeSelectionUiFrame(String name, String id, String password, String registrationNumber) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.registrationNumber = registrationNumber;

		setContentPane(mainPanel);
		setResizable(false);
		setTitle("학년 선택");
		pack();

		radioButtonGroup = new ButtonGroup();

		radioButtonGroup.add(gradeRadioButton1);
		radioButtonGroup.add(gradeRadioButton2);
		radioButtonGroup.add(gradeRadioButton3);
		radioButtonGroup.add(gradeRadioButton4);
		gradeRadioButton1.setActionCommand("freshman");
		gradeRadioButton2.setActionCommand("sophomore");
		gradeRadioButton3.setActionCommand("junior");
		gradeRadioButton4.setActionCommand("senior");
		okButton.addActionListener(this);

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(okButton)) {
			try {
				switch (radioButtonGroup.getSelection().getActionCommand()) {
					case "freshman" -> grade = 1;
					case "sophomore" -> grade = 2;
					case "junior" -> grade = 3;
					case "senior" -> grade = 4;
				}

				GuiCore.studentArrayList.add(new Student(name, id, password, registrationNumber, grade));
				JOptionPane.showMessageDialog(
					null,
					"회원가입이 정상적으로 처리되었습니다.",
					"회원가입 완료",
					JOptionPane.INFORMATION_MESSAGE
				);
				dispose();
			} catch (NullPointerException exception) {
				JOptionPane.showMessageDialog(
					null,
					"학년을 선택해 주십시오.",
					"학년 선택 오류",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}
}
