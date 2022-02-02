package jdbc_semi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.SystemColor;

public class ProductMain extends JFrame {
	JPanel panel = new JPanel();
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	public static JLabel status;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductMain frame = new ProductMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductMain() {
		setForeground(new Color(0, 0, 0));
		setTitle("생산관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 520);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel = new Intro();
		contentPane.add(panel);
		contentPane.updateUI();
		contentPane.add(getStatus(), BorderLayout.SOUTH);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
			menuBar.add(getMnNewMenu_1());
			menuBar.add(getBtnNewButton());
		}
		return menuBar;
	}
	public JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("생산관리");
			mnNewMenu.setForeground(new Color(0, 0, 0));
			mnNewMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			mnNewMenu.add(getMntmNewMenuItem());
			mnNewMenu.add(getMntmNewMenuItem_1());
			mnNewMenu.add(getMntmNewMenuItem_2());
		}
		return mnNewMenu;
	}
	public JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("입력");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new ProductAdd();
					contentPane.add(panel);
					contentPane.updateUI();
				}
			});
			mntmNewMenuItem.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return mntmNewMenuItem;
	}
	public JMenu getMnNewMenu_1() {
		if (mnNewMenu_1 == null) {
			mnNewMenu_1 = new JMenu("통계자료");
			mnNewMenu_1.setForeground(new Color(0, 0, 0));
			mnNewMenu_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			mnNewMenu_1.add(getMntmNewMenuItem_3());
			mnNewMenu_1.add(getMntmNewMenuItem_4());
		}
		return mnNewMenu_1;
	}
	public JMenuItem getMntmNewMenuItem_1() {
		if (mntmNewMenuItem_1 == null) {
			mntmNewMenuItem_1 = new JMenuItem("조회");
			mntmNewMenuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new ProductSearch(ProductMain.this);
					contentPane.add(panel);
					contentPane.updateUI();
				}
			});
			mntmNewMenuItem_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return mntmNewMenuItem_1;
	}
	public JMenuItem getMntmNewMenuItem_2() {
		if (mntmNewMenuItem_2 == null) {
			mntmNewMenuItem_2 = new JMenuItem("수정 | 삭제");
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new ProductModify();
					contentPane.add(panel);
					contentPane.updateUI();
				}
			});
			mntmNewMenuItem_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return mntmNewMenuItem_2;
	}
	public JMenuItem getMntmNewMenuItem_3() {
		if (mntmNewMenuItem_3 == null) {
			mntmNewMenuItem_3 = new JMenuItem("월별");
			mntmNewMenuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new ProductMonth();
					contentPane.add(panel);
					contentPane.updateUI();
				}
			});
			mntmNewMenuItem_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return mntmNewMenuItem_3;
	}
	public JMenuItem getMntmNewMenuItem_4() {
		if (mntmNewMenuItem_4 == null) {
			mntmNewMenuItem_4 = new JMenuItem("제품별");
			mntmNewMenuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new ProductPart();
					contentPane.add(panel);
					contentPane.updateUI();
				}
			});
			mntmNewMenuItem_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return mntmNewMenuItem_4;
	}
	public JLabel getStatus() {
		if (status == null) {
			status = new JLabel("생산관리 프로그램입니다. 원하는 메뉴를 선택하세요.");
			status.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			status.setPreferredSize(new Dimension(57, 30));
			status.setOpaque(true);
			status.setBackground(new Color(220, 231, 239));
			status.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return status;
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("HOME");
			btnNewButton.setFocusable(false);
			btnNewButton.setFocusPainted(false);
			btnNewButton.setRequestFocusEnabled(false);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(panel);
					panel = new Intro();
					contentPane.add(panel);
					contentPane.updateUI();
					status.setText("생산관리 프로그램입니다. 원하는 메뉴를 선택하세요.");
				}
			});
			btnNewButton.setForeground(new Color(0, 0, 0));
			btnNewButton.setBorderPainted(false);
			btnNewButton.setOpaque(false);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		}
		return btnNewButton;
	}
}
