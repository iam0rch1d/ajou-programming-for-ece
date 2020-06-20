package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentUi extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;

	JMenuBar menuBar = new JMenuBar();
	JMenu[] menu = {new JMenu("기능"), new JMenu("정보")};
	JMenuItem[][] menuItem = {
		{new JMenuItem("로그아웃"), new JMenuItem("종료")},
		{new JMenuItem("프로그램 정보")}
	};

	public StudentUi(Student student) {
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < menu.length; i++) {
			menuBar.add(menu[i]);

			for (int j = 0; j < menuItem[i].length; j++) {
				menu[i].add(menuItem[i][j]);
				menuItem[i][j].addActionListener(this);
			}
		}

		tabbedPane.addTab("수강신청", new LectureRegisterUi(student));
		tabbedPane.addTab("수강 목록 확인", new LectureArrayListCheckUi(student));
		setJMenuBar(menuBar);
		setResizable(false);
		setTitle("학생 메뉴 - 아주대학교 수강신청 프로그램");
		pack();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(menuItem[0][0])) {
			dispose();

			new EntranceUi();
		} else if (e.getSource().equals(menuItem[0][1])) {
			System.exit(0);
		} else if (e.getSource().equals(menuItem[1][0])) {
			new ApplicationInformation();
		}
	}
}
