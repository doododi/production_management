package jdbc_semi;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Intro extends JPanel {
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public Intro() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		add(getLblNewLabel_1());

	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\soman\\Downloads\\team-management.png"));
			lblNewLabel_1.setBounds(112, 82, 256, 256);
		}
		return lblNewLabel_1;
	}
}
