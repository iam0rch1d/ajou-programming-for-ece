package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MemberUiFrame extends JFrame implements ActionListener {
	JMenuBar menuBar = new JMenuBar();
	JMenu[] menu = {new JMenu("기능"), new JMenu("정보")};
	JMenuItem[][] menuItem = {
		{new JMenuItem("로그아웃"), new JMenuItem("종료")},
		{new JMenuItem("프로그램 정보")}
	};
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private ApplicationInformationFrame applicationInformationFrame;

	MemberUiFrame(Member member) {
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < menu.length; i++) {
			menuBar.add(menu[i]);

			for (int j = 0; j < menuItem[i].length; j++) {
				menu[i].add(menuItem[i][j]);
				menuItem[i][j].addActionListener(this);
			}
		}

		if (member.getClass().getSimpleName().equals("Student")) {
			tabbedPane.addTab("수강신청", new LectureRegisterUiPanel((Student) member));
			tabbedPane.addTab("수강 목록 확인", new LectureArrayListCheckUiPanel(member));
			setTitle("학생 메뉴");
		} else if (member.getClass().getSimpleName().equals("Professor")) {
			tabbedPane.addTab("강의 개설", new LectureRegisterUiPanel((Professor) member));
			tabbedPane.addTab("강의 목록 확인", new LectureArrayListCheckUiPanel(member));
			setTitle("교수 메뉴");
		}

		setJMenuBar(menuBar);
		setResizable(false);
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

			new EntranceUiFrame();
		} else if (e.getSource().equals(menuItem[0][1])) {
			System.exit(0);
		} else if (e.getSource().equals(menuItem[1][0])) {
			if (applicationInformationFrame != null) {
				applicationInformationFrame.dispose();
			}

			applicationInformationFrame = new ApplicationInformationFrame();
		}
	}
}
