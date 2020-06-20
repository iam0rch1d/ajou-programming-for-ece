package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntranceUi extends JFrame implements ActionListener {
	public JPanel mainPanel;
	private JButton signInButton;
	private JButton createAccountButton;
	private JButton exitButton;
	private JTextField idField;
	private JPasswordField passwordField;

	public EntranceUi() {
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("아주대학교 수강신청 프로그램");
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
		if (e.getActionCommand().equals("로그인")) {
			Member member = Entrance.isSignInValid(idField.getText(), String.copyValueOf(passwordField.getPassword()));

			if (member == null) {
				JOptionPane.showMessageDialog(
					null,
					"ID나 비밀번호가 잘못되었습니다.",
					"로그인 오류",
					JOptionPane.ERROR_MESSAGE
				);
			} else if (member instanceof Student) {
				dispose();

				new StudentUi((Student) member);
			}
		}
	}
}
