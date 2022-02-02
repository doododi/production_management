package jdbc_semi;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ProductAdd extends JPanel {
	ProductDao dao;
	ProductVo vo;
	String codeList[] = {"코드를 선택해주세요", "p1", "p2", "p3", "p4"};
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField pName;
	private JTextField pPrice;
	private JSeparator separator;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_3_1;
	private JTextField pDate;
	private JTextField pEa;
	private JButton btnNewButton;
	private JButton btnReset;
	private JComboBox pCode;

	/**
	 * Create the panel.
	 */
	public ProductAdd() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLblNewLabel_2());
		add(getPName());
		add(getPPrice());
		add(getSeparator());
		add(getLblNewLabel_3());
		add(getLblNewLabel_3_1());
		add(getPDate());
		add(getPEa());
		add(getBtnNewButton());
		add(getBtnReset());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		getPDate().setText(now);
		add(getPCode());
		
		if(dao == null) {
			dao = new ProductDao();
		}
		
		ProductMain.status.setText("입력하고자 하는 코드를 선택해주세요.");

	}

	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("코드");
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel.setBounds(117, 54, 57, 15);
		}
		return lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품명");
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(117, 92, 57, 15);
		}
		return lblNewLabel_1;
	}

	public JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("단가");
			lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(117, 133, 57, 15);
		}
		return lblNewLabel_2;
	}

	public JTextField getPName() {
		if (pName == null) {
			pName = new JTextField();
			pName.setEditable(false);
			pName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pName.setColumns(10);
			pName.setBounds(186, 89, 160, 21);
		}
		return pName;
	}

	public JTextField getPPrice() {
		if (pPrice == null) {
			pPrice = new JTextField();
			pPrice.setEditable(false);
			pPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pPrice.setColumns(10);
			pPrice.setBounds(186, 130, 160, 21);
		}
		return pPrice;
	}

	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(SystemColor.activeCaption);
			separator.setBounds(61, 178, 351, 21);
		}
		return separator;
	}

	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("생산날짜");
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(117, 209, 57, 15);
		}
		return lblNewLabel_3;
	}

	public JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("생산량");
			lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3_1.setBounds(117, 253, 57, 15);
		}
		return lblNewLabel_3_1;
	}

	public JTextField getPDate() {
		if (pDate == null) {
			pDate = new JTextField();
			pDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pDate.setBounds(186, 206, 160, 21);
			pDate.setColumns(10);
		}
		return pDate;
	}

	public JTextField getPEa() {
		if (pEa == null) {
			pEa = new JTextField();
			pEa.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pEa.setColumns(10);
			pEa.setBounds(186, 247, 85, 21);
		}
		return pEa;
	}

	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("저장");
			btnNewButton.setBorder(null);
			btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnNewButton.setBackground(new Color(220, 231, 239));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean r = false;
					vo.setCode(pCode.getSelectedItem().toString());
					vo.setEa(Integer.parseInt(pEa.getText()));
					vo.setMdate(pDate.getText());
					r = dao.insert(vo);
					
					if (r) {
						ProductMain.status.setText("데이터가 추가되었습니다.");
					} else {
						ProductMain.status.setText("저장 중에 오류가 발생했습니다.");
					}
					
				}
			});
			btnNewButton.setBounds(98, 316, 97, 30);
		}
		return btnNewButton;
	}

	public JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("Reset");
			btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			btnReset.setBorder(null);
			btnReset.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnReset.setBackground(new Color(255, 76, 76));
					btnReset.setForeground(Color.white);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnReset.setBackground(new Color(220, 231, 239));
					btnReset.setForeground(Color.black);
				}
			});
			btnReset.setBackground(new Color(220, 231, 239));
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pCode.setSelectedIndex(0);
					pName.setText("");
					pPrice.setText("");
//					pDate.setText("");
					pEa.setText("");
					pCode.requestFocus();
					ProductMain.status.setText("화면이 초기화되었습니다.");

				}
			});
			btnReset.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnReset.setBounds(293, 316, 97, 30);
		}
		return btnReset;
	}
	public JComboBox getPCode() {
		if (pCode == null) {
			pCode = new JComboBox(codeList);
			pCode.setToolTipText("코드를 선택해주세요.");
			pCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vo = dao.select(pCode.getSelectedItem().toString());
					pName.setText(vo.getCodeName());
					pPrice.setText(vo.getPrice()+"");
					
					ProductMain.status.setText("데이터가 조회되었습니다.");
				}
			});
			pCode.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pCode.setBounds(186, 52, 160, 20);
		}
		return pCode;
	}
}
