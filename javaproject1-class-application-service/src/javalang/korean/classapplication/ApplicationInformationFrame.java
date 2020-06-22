package javalang.korean.classapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class ApplicationInformationFrame extends JFrame {
	private JPanel mainPanel;
	private JLabel imageLabel;

	ApplicationInformationFrame() {
		final int MARGIN_LEFTRIGHT = 64;
		final int MARGIN_TOPBOTTOM = 32;

		mainPanel.setBorder(new EmptyBorder(MARGIN_TOPBOTTOM, MARGIN_LEFTRIGHT, MARGIN_TOPBOTTOM, MARGIN_LEFTRIGHT));
		imageLabel.setIcon(new ImageIcon("resources/0rch1d_logo_small.png"));
		imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/iam0rch1d"));
				} catch (IOException | URISyntaxException exception) {
					exception.printStackTrace();
				}
			}
		});
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("프로그램 정보");
		pack();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}
}
