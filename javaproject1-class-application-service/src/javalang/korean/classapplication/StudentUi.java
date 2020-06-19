package javalang.korean.classapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentUi extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public StudentUi(Member member) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane.addTab("수강신청", new RegisterLectureUi(member));
        setResizable(false);
        setTitle("학생 메뉴");
        pack();

        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
