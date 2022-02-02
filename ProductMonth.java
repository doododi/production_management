package jdbc_semi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ProductMonth extends JPanel {
	ProductDao dao;
	ProductMain main;
	
	DefaultTableModel model;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField amount;
	private JTable table;
	private JTextField find;
	static JRadioButton btnAsc;
	static JRadioButton btnDesc;
	private ButtonGroup btnGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public ProductMonth() {
		setBackground(Color.WHITE);
		setLayout(null);
		add(getBtnNewButton());
		add(getScrollPane());
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getAmount());
		add(getFind());
		add(getBtnAsc());
		add(getBtnDesc());
		
		ProductMain.status.setText("0000-00 형식으로 검색하세요. ex) 2021-11");
	}
	public void search() {
		if(dao==null) {	dao = new ProductDao();	}
		
		int sumAmt = 0;
		Vector<Vector> list = dao.productMonth(find.getText());
		
		model.getDataVector().clear();
		DecimalFormat df = new DecimalFormat("###,###");
		for(Vector<String> v : list) {
			int iEa = Integer.parseInt(v.get(2));
			int iAmt = Integer.parseInt(v.get(3));
			
			v.set(2, df.format(iEa));
			v.set(3, df.format(iAmt));
			model.addRow(v);
			// for문이 실행될 때, amt의 총 합계 내용을 tot에 담아서
			// 총액 textField에 출력하고 싶다.
			sumAmt += iAmt;
		}
		table.updateUI();
		amount.setText(df.format(sumAmt));
		find.requestFocus();
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("조회");
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(220, 231, 239));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dao==null) {	dao = new ProductDao();	}
					
					int sumAmt = 0;
					Vector<Vector> list = dao.productMonth(find.getText());
					
					model.getDataVector().clear();
					DecimalFormat df = new DecimalFormat("###,###");
					for(Vector<String> v : list) {
						int iEa = Integer.parseInt(v.get(2));
						int iAmt = Integer.parseInt(v.get(3));
						
						v.set(2, df.format(iEa));
						v.set(3, df.format(iAmt));
						model.addRow(v);
						// for문이 실행될 때, amt의 총 합계 내용을 tot에 담아서
						// 총액 textField에 출력하고 싶다.
						sumAmt += iAmt;
					}
					table.updateUI();
					amount.setText(df.format(sumAmt));
					find.requestFocus();
					
					if(list.size() > 0) {
						ProductMain.status.setText("월별 자료가 조회되었습니다.");
					} else if(list.size() == 0) {
						ProductMain.status.setText("검색 결과가 없습니다.");
					} else {
						ProductMain.status.setText("검색된 자료가 없습니다.");
					}
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnNewButton.setBounds(381, 25, 97, 25);
		}
		return btnNewButton;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 99, 466, 242);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제조연월");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel.setBounds(12, 30, 48, 15);
		}
		return lblNewLabel;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("총액");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(111, 370, 40, 15);
		}
		return lblNewLabel_1;
	}
	public JTextField getAmount() {
		if (amount == null) {
			amount = new JTextField();
			amount.setHorizontalAlignment(SwingConstants.CENTER);
			amount.setEditable(false);
			amount.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			amount.setColumns(10);
			amount.setBounds(162, 365, 175, 25);
		}
		return amount;
	}
	public JTable getTable() {
		if (table == null) {
			Vector colData = new Vector();
			colData.add("제조일");
			colData.add("제품명");
			colData.add("수량");
			colData.add("금액");
			
			model = new DefaultTableModel(colData, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			table = new JTable(model);
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			table.setBackground(Color.WHITE);
			
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = table.getColumnModel();
			tcm.getColumn(0).setCellRenderer(dtcr);
			tcm.getColumn(1).setCellRenderer(dtcr);
			tcm.getColumn(2).setCellRenderer(dtcr);
			
			DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
			dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);
			tcm.getColumn(3).setCellRenderer(dtcr2);
		}
		return table;
	}
	public JTextField getFind() {
		if (find == null) {
			find = new JTextField();
			find.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						search();
					}
				}
			});
			find.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			find.setBounds(72, 25, 297, 25);
			find.setColumns(10);
		}
		return find;
	}
	public JRadioButton getBtnAsc() {
		if (btnAsc == null) {
			btnAsc = new JRadioButton("금액 오름차순");
			btnAsc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
					ProductMain.status.setText("금액 오름차순으로 정렬되었습니다.");
				}
			});
			btnAsc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnGroup.add(btnAsc);
			btnAsc.setBackground(Color.WHITE);
			btnAsc.setBounds(82, 65, 121, 23);
		}
		return btnAsc;
	}
	public JRadioButton getBtnDesc() {
		if (btnDesc == null) {
			btnDesc = new JRadioButton("금액 내림차순");
			btnDesc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
					ProductMain.status.setText("금액 내림차순으로 정렬되었습니다.");
				}
			});
			btnDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnGroup.add(btnDesc);
			btnDesc.setBackground(Color.WHITE);
			btnDesc.setBounds(285, 65, 121, 23);
		}
		return btnDesc;
	}
}
