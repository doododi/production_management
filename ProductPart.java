package jdbc_semi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ProductPart extends JPanel {
	ProductDao dao;
	ProductMain main;
	String partList[] = {"제품을 선택해주세요.", "컴퓨터", "냉장고", "마우스", "세탁기"};

	DefaultTableModel model;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField amount;
	private JTable table;
	private JComboBox find;
	static JRadioButton btnAsc;
	static JRadioButton btnDesc;
	private ButtonGroup btnGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public ProductPart() {
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
		
		ProductMain.status.setText("제품명을 입력하세요. (ex. 냉장고)");
	}
	public void part() {
		if(dao==null) {	dao = new ProductDao();	}
		
		int sumAmt = 0;
		Vector<Vector> list = dao.productParts(find.getSelectedItem().toString());
		
		model.getDataVector().clear();
		DecimalFormat df = new DecimalFormat("###,###");
		for(Vector<String> v : list) {
			int iEa = Integer.parseInt(v.get(2));
			int iAmt = Integer.parseInt(v.get(3));
			
			v.set(2, df.format(iEa));
			v.set(3, df.format(iAmt));
			model.addRow(v);
			
			sumAmt += iAmt;
		}
		table.updateUI();
		amount.setText(df.format(sumAmt));
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
					Vector<Vector> list = dao.productParts(find.getSelectedItem().toString());
					
					model.getDataVector().clear();
					DecimalFormat df = new DecimalFormat("###,###");
					for(Vector<String> v : list) {
						int iEa = Integer.parseInt(v.get(2));
						int iAmt = Integer.parseInt(v.get(3));
						
						v.set(2, df.format(iEa));
						v.set(3, df.format(iAmt));
						model.addRow(v);
						
						sumAmt += iAmt;
					}
					table.updateUI();
					amount.setText(df.format(sumAmt));
					
					if(list.size() > 0) {
						ProductMain.status.setText("제품별 자료가 조회되었습니다.");
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
			scrollPane.setBorder(null);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(12, 99, 466, 242);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제품명");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel.setBounds(12, 30, 40, 15);
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
			
			// 가운데 정렬
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = table.getColumnModel();
			tcm.getColumn(0).setCellRenderer(dtcr);
			tcm.getColumn(1).setCellRenderer(dtcr);
			
			// 우측 정렬
			DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
			dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);
			tcm.getColumn(2).setCellRenderer(dtcr2);
			tcm.getColumn(3).setCellRenderer(dtcr2);
		}
		return table;
	}
	public JComboBox getFind() {
		if (find == null) {
			find = new JComboBox(partList);
			find.setBorder(null);
			find.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			find.setBounds(64, 25, 305, 25);
		}
		return find;
	}
	public JRadioButton getBtnAsc() {
		if (btnAsc == null) {
			btnAsc = new JRadioButton("금액 오름차순");
			btnAsc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnAsc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					part();
					ProductMain.status.setText("금액 오름차순으로 정렬되었습니다.");
				}
			});
			btnGroup.add(btnAsc);
			btnAsc.setBackground(Color.WHITE);
			btnAsc.setBounds(82, 65, 121, 23);
		}
		return btnAsc;
	}
	public JRadioButton getBtnDesc() {
		if (btnDesc == null) {
			btnDesc = new JRadioButton("금액 내림차순");
			btnDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnDesc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					part();
					ProductMain.status.setText("금액 내림차순으로 정렬되었습니다.");
				}
			});
			btnGroup.add(btnDesc);
			btnDesc.setBackground(Color.WHITE);
			btnDesc.setBounds(285, 65, 121, 23);
		}
		return btnDesc;
	}
}
