package jdbc_semi;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductModify extends JPanel {
	ProductDao dao;
	int serial;
	ProductVo vo;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField pCode;
	private JTextField pCodeName;
	private JTextField pPrice;
	private JSeparator separator;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_3_1;
	private JTextField pMdate;
	private JTextField pEa;
	private JButton btnTkrwp;
	private JButton btnNewButton_1;
	private JLabel lblNo;
	private JTextField pSerial;

	/**
	 * Create the panel.
	 */
	public ProductModify(String n) {
		this();
		serial = Integer.parseInt(n);
		select();
	}
	public ProductModify() {
		setBackground(Color.WHITE);
		setLayout(null);
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLblNewLabel_2());
		add(getPCode());
		add(getPCodeName());
		add(getPPrice());
		add(getSeparator());
		add(getLblNewLabel_3());
		add(getLblNewLabel_3_1());
		add(getPMdate());
		add(getPEa());
		add(getBtnTkrwp());
		add(getBtnNewButton_1());
		add(getLblNo());
		add(getPSerial());
		
		ProductMain.status.setText("조회 화면에서 자료를 선택해주세요.");
	}
	public void select() {
		if(dao == null) { dao = new ProductDao(); }
		vo = dao.select(serial);
		pSerial.setText(vo.getSerial()+"");
		pCode.setText(vo.getCode());
		pCodeName.setText(vo.getCodeName());
		pPrice.setText(vo.getPrice()+"");
		pMdate.setText(vo.getMdate());
		pEa.setText(vo.getEa()+"");
		
		ProductMain.status.setText("자료를 수정하거나 삭제할 수 있습니다.");
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("코드");
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel.setBounds(116, 79, 57, 15);
		}
		return lblNewLabel;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품명");
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(116, 117, 57, 15);
		}
		return lblNewLabel_1;
	}
	public JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("단가");
			lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(116, 158, 57, 15);
		}
		return lblNewLabel_2;
	}
	public JTextField getPCode() {
		if (pCode == null) {
			pCode = new JTextField();
			pCode.setEditable(false);
			pCode.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pCode.setColumns(10);
			pCode.setBounds(192, 76, 160, 21);
		}
		return pCode;
	}
	public JTextField getPCodeName() {
		if (pCodeName == null) {
			pCodeName = new JTextField();
			pCodeName.setEditable(false);
			pCodeName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pCodeName.setColumns(10);
			pCodeName.setBounds(192, 114, 160, 21);
		}
		return pCodeName;
	}
	public JTextField getPPrice() {
		if (pPrice == null) {
			pPrice = new JTextField();
			pPrice.setEditable(false);
			pPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pPrice.setColumns(10);
			pPrice.setBounds(192, 155, 160, 21);
		}
		return pPrice;
	}
	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(SystemColor.activeCaption);
			separator.setBounds(71, 203, 351, 21);
		}
		return separator;
	}
	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("입력날짜");
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(116, 234, 57, 15);
		}
		return lblNewLabel_3;
	}
	public JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("생산량");
			lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3_1.setBounds(116, 278, 57, 15);
		}
		return lblNewLabel_3_1;
	}
	public JTextField getPMdate() {
		if (pMdate == null) {
			pMdate = new JTextField();
			pMdate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pMdate.setColumns(10);
			pMdate.setBounds(192, 231, 160, 21);
		}
		return pMdate;
	}
	public JTextField getPEa() {
		if (pEa == null) {
			pEa = new JTextField();
			pEa.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pEa.setColumns(10);
			pEa.setBounds(192, 272, 85, 21);
		}
		return pEa;
	}
	public JButton getBtnTkrwp() {
		if (btnTkrwp == null) {
			btnTkrwp = new JButton("삭제");
			btnTkrwp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnTkrwp.setBackground(new Color(255, 76, 76));
					btnTkrwp.setForeground(Color.white);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnTkrwp.setBackground(new Color(220, 231, 239));
					btnTkrwp.setForeground(Color.black);
				}
			});
			btnTkrwp.setBorder(null);
			btnTkrwp.setBackground(new Color(220, 231, 239));
			btnTkrwp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dao == null) {
						dao = new ProductDao();
					}
					ProductVo vo = new ProductVo();
					vo.setSerial(Integer.parseInt(pSerial.getText()));
					vo.setCode(pCode.getText());
					
					boolean r = dao.delete(vo);
					int yn = JOptionPane.showConfirmDialog(ProductModify.this,
							"정말 삭제하겠습니까?",
							"삭제 확인",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE,
							new ImageIcon("C:\\Users\\soman\\Downloads\\delete.png"));
					if(yn == JOptionPane.OK_OPTION) {
						r = true;
						ProductMain.status.setText("자료가 정상적으로 삭제되었습니다.");
						pSerial.setText("");
						pCode.setText("");
						pCodeName.setText("");
						pPrice.setText("");
						pMdate.setText("");
						pEa.setText("");
					} else if(yn == JOptionPane.CANCEL_OPTION) {
						ProductMain.status.setText("삭제 작업을 취소했습니다.");
					} else {
						ProductMain.status.setText("삭제 중 오류가 발생했습니다. 데이터 확인 후 다시 시도해주세요.");
					}
				}
			});
			btnTkrwp.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnTkrwp.setBounds(293, 341, 97, 30);
		}
		return btnTkrwp;
	}
	public JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("수정");
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(new Color(220, 231, 239));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dao == null) { dao = new ProductDao(); }
					
					ProductVo vo = new ProductVo();

					vo.setSerial(Integer.parseInt(pSerial.getText()));
					vo.setCode(pCode.getText());
					vo.setCodeName(pCodeName.getText());
					vo.setPrice(Integer.parseInt(pPrice.getText()));
					vo.setMdate(pMdate.getText());
					vo.setEa(Integer.parseInt(pEa.getText()));
					
					boolean r = dao.update(vo);
					if(r) {
						ProductMain.status.setText("자료가 정상적으로 수정되었습니다.");
						pSerial.setText("");
						pCode.setText("");
						pCodeName.setText("");
						pPrice.setText("");
						pMdate.setText("");
						pEa.setText("");
					} else {
						ProductMain.status.setText("수정 중에 오류가 발생했습니다.");
					}
				}
			});
			btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnNewButton_1.setBounds(98, 341, 97, 30);
		}
		return btnNewButton_1;
	}
	public JLabel getLblNo() {
		if (lblNo == null) {
			lblNo = new JLabel("Serial");
			lblNo.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNo.setBounds(116, 36, 57, 15);
		}
		return lblNo;
	}
	public JTextField getPSerial() {
		if (pSerial == null) {
			pSerial = new JTextField();
			pSerial.setEditable(false);
			pSerial.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			pSerial.setColumns(10);
			pSerial.setBounds(192, 33, 160, 21);
		}
		return pSerial;
	}
}
