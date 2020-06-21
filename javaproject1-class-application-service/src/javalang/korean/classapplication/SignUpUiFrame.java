package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SignUpUiFrame extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JTextField idField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmationField;
	private JButton signUpButton;
	private JRadioButton studentRadioButton;
	private JRadioButton professorRadioButton;
	private JTextField nameField;
	private JTextField frontRegistrationNumberField;
	private JPasswordField backRegistrationNumberField;

	SignUpUiFrame() {
		setContentPane(mainPanel);
		setResizable(false);
		setTitle("회원가입");
		pack();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signUpButton)) {

		}
	}
}
