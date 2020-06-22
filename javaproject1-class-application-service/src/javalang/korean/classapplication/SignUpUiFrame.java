package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SignUpUiFrame extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private final ButtonGroup radioButtonGroup;
	private JRadioButton studentRadioButton;
	private JRadioButton professorRadioButton;
	private JTextField nameField;
	private JTextField frontRegistrationNumberField;
	private JPasswordField backRegistrationNumberField;
	private JTextField idField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmationField;
	private JButton signUpButton;

	SignUpUiFrame() {
		setContentPane(mainPanel);
		setResizable(false);
		setTitle("회원가입");
		pack();

		radioButtonGroup = new ButtonGroup();

		radioButtonGroup.add(studentRadioButton);
		radioButtonGroup.add(professorRadioButton);
		studentRadioButton.setActionCommand("student");
		professorRadioButton.setActionCommand("professor");
		signUpButton.addActionListener(this);

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signUpButton)) {
			try {
				String name = nameField.getText();

				if (name.equals("")) {
					JOptionPane.showMessageDialog(
						null,
						"이름을 입력해 주십시오.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				String frontRegistrationNumber = frontRegistrationNumberField.getText();
				String backRegistrationNumber = String.copyValueOf(backRegistrationNumberField.getPassword());
				String registrationNumber = frontRegistrationNumber + "-" + backRegistrationNumber;

				if (!Entrance.isRegistrationNumberValid(registrationNumber)) {
					JOptionPane.showMessageDialog(
						null,
						"주민등록번호의 형태가 올바르지 않습니다.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				} else if (Entrance.isRegistrationNumberDuplicated(registrationNumber)) {
					JOptionPane.showMessageDialog(
						null,
						"이미 등록된 주민등록번호입니다.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				String id = idField.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(
						null,
						"ID를 입력해 주십시오.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				} else if (!Entrance.isIdValid(id)) {
					JOptionPane.showMessageDialog(
						null,
						"최대 16자 영어 소문자나 숫자로 ID를 구성해 주십시오.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				} else if (Entrance.isIdDuplicated(id)) {
					JOptionPane.showMessageDialog(
						null,
						"이미 등록된 ID가 존재합니다.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				String password = String.copyValueOf(passwordField.getPassword());

				if (password.equals("")) {
					JOptionPane.showMessageDialog(
						null,
						"비밀번호를 입력해 주십시오.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				} else if (!Entrance.isPasswordValid(password)) {
					JOptionPane.showMessageDialog(
						null,
						"최대 16자 영문자, 숫자 또는 기호로 비밀번호를 구성해 주십시오.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				String passwordConfirmation = String.copyValueOf(passwordConfirmationField.getPassword());

				if (!password.equals(passwordConfirmation)) {
					JOptionPane.showMessageDialog(
						null,
						"비밀번호 확인이 일치하지 않습니다.",
						"회원가입 오류",
						JOptionPane.ERROR_MESSAGE
					);

					return;
				}

				if (radioButtonGroup.getSelection().getActionCommand().equals("student")) {
					new GradeSelectionUiFrame(name, id, password, registrationNumber);
				} else if (radioButtonGroup.getSelection().getActionCommand().equals("professor")) {
					GuiCore.professorArrayList.add(new Professor(name, id, password, registrationNumber));
					JOptionPane.showMessageDialog(
						null,
						"회원가입이 정상적으로 처리되었습니다.",
						"회원가입 완료",
						JOptionPane.INFORMATION_MESSAGE
					);
				}

				dispose();
			} catch (NullPointerException exception) {
				JOptionPane.showMessageDialog(
					null,
					"회원 타입을 선택해 주십시오.",
					"회원가입 오류",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}
}
