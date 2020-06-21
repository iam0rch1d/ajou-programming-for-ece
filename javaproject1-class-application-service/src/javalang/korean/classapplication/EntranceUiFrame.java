package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EntranceUiFrame extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton signInButton;
	private JButton createAccountButton;
	private JButton exitButton;
	private SignUpUiFrame signUpUiFrame;

	EntranceUiFrame() {
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("로그인");
		pack();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);

		signInButton.addActionListener(this);
		createAccountButton.addActionListener(this);
		exitButton.addActionListener(e -> System.exit(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signInButton)) {
			Member member = Entrance.isSignInValid(idField.getText(), String.copyValueOf(passwordField.getPassword()));

			if (member == null) {
				JOptionPane.showMessageDialog(
					null,
					"ID나 비밀번호가 잘못되었습니다.",
					"로그인 오류",
					JOptionPane.ERROR_MESSAGE
				);
			} else {
				if (signUpUiFrame != null) {
					signUpUiFrame.dispose();
				}

				dispose();

				new MemberUiFrame(member);
			}
		} else if (e.getSource().equals(createAccountButton)) {
			signUpUiFrame = new SignUpUiFrame();
		}
	}
}
