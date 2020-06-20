package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApplicationInformation extends JFrame {
	private JPanel mainPanel;
	private JLabel logoLabel;

	ApplicationInformation() {
		final int MARGIN = 40;

		mainPanel.setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		logoLabel.setIcon(new ImageIcon("data/0rch1d_logo_small.png"));
		setContentPane(mainPanel);
		setTitle("프로그램 정보");
		pack();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}
}
