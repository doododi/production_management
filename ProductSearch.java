package jdbc_semi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

public class ProductSearch extends JPanel {
	ProductDao dao;
	ProductMain main;
	DefaultTableModel model;
	
	private JTextField find;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	static JRadioButton btnAsc;
	static JRadioButton btnDesc;
	private ButtonGroup btnGroup = new ButtonGroup();
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public ProductSearch(ProductMain main) {
		this();
		this.main = main;
	}
	
	public void search() {
		if(dao == null) {
			dao = new ProductDao();
		}
		
		model.getDataVector().clear();
		
		Vector<Vector> list = dao.search(find.getText());
		for(Vector v : list) {
			model.addRow(v);
		}
		find.requestFocus();
		table.updateUI();
		
		if(list.size() > 0) {
			find.requestFocus();
			String tmp = String.format("자료가 %d건 검색되었습니다.", list.size());
			
			ProductMain.status.setText(tmp);
		} else {
			find.setText("");
			ProductMain.status.setText("검색된 자료가 없습니다.");
		}
	}
	
	public ProductSearch() {
		setBackground(Color.WHITE);
		setLayout(null);
		add(getFind());
		add(getBtnNewButton());
		add(getScrollPane());
		add(getBtnAsc());
		add(getBtnDesc());
		add(getLblNewLabel());
		
		ProductMain.status.setText("자료를 검색해주세요.");
	}

	public JTextField getFind() {
		if (find == null) {
			find = new JTextField();
			find.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						search();
					}
				}
			});
			find.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			find.setColumns(10);
			find.setBounds(12, 52, 365, 25);
		}
		return find;
	}
	
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("조회");
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(220, 231, 239));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnNewButton.setBounds(389, 51, 89, 26);
		}
		return btnNewButton;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(12, 119, 466, 271);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			// 테이블 헤더
			Vector<String> colData = new Vector();
			colData.add("NO");
			colData.add("제조일");
			colData.add("코드");
			colData.add("제품명");
			colData.add("단가");
			colData.add("수량");
			colData.add("금액");
			
			// 검색 값 수정 불가
			model = new DefaultTableModel(colData, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			table = new JTable(model);
			table.setBackground(Color.WHITE);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						int row = table.getSelectedRow();
						int col = table.getSelectedColumn();
						String v = (String)table.getValueAt(row, 0);
						
						JPanel panel = new ProductModify(v);
						main.getContentPane().remove(main.panel);
						main.setPanel(panel);
						main.getContentPane().add(panel);
						main.getContentPane().updateUI();
						
					}
				}
			});
			
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			
			// 넓이 설정
			table.getColumnModel().getColumn(0).setPreferredWidth(5);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(5);
			table.getColumnModel().getColumn(3).setPreferredWidth(20);
			table.getColumnModel().getColumn(4).setPreferredWidth(20);
			table.getColumnModel().getColumn(5).setPreferredWidth(20);
			table.getColumnModel().getColumn(6).setPreferredWidth(40);
			
			// 가운데 정렬 = 원하는 열만
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = table.getColumnModel();
			tcm.getColumn(0).setCellRenderer(dtcr);
			tcm.getColumn(1).setCellRenderer(dtcr);
			tcm.getColumn(2).setCellRenderer(dtcr);
			tcm.getColumn(3).setCellRenderer(dtcr);
			
			// 우측 정렬
			DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
			dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);
			tcm.getColumn(4).setCellRenderer(dtcr2);
			tcm.getColumn(5).setCellRenderer(dtcr2);
			tcm.getColumn(6).setCellRenderer(dtcr2);			
		}
		return table;
	}
	public JRadioButton getBtnAsc() {
		if (btnAsc == null) {
			btnAsc = new JRadioButton("금액 오름차순");
			btnAsc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnAsc.setBackground(Color.WHITE);
			btnAsc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dao==null) { dao = new ProductDao(); }
					model.getDataVector().clear();
					Vector<Vector> list = dao.search(find.getText());
					for(Vector v : list) {
						model.addRow(v);
					}
					table.updateUI();
					ProductMain.status.setText("금액 오름차순으로 정렬되었습니다.");
					find.requestFocus();
				}
			});
			btnGroup.add(btnAsc);
			btnAsc.setBounds(85, 87, 121, 23);
		}
		return btnAsc;
	}
	public JRadioButton getBtnDesc() {
		if (btnDesc == null) {
			btnDesc = new JRadioButton("금액 내림차순");
			btnDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnDesc.setBackground(Color.WHITE);
			btnDesc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dao==null) { dao = new ProductDao(); }
					model.getDataVector().clear();
					Vector<Vector> list = dao.search(find.getText());
					for(Vector v : list) {
						model.addRow(v);
					}
					table.updateUI();
					ProductMain.status.setText("금액 내림차순으로 정렬되었습니다.");
					find.requestFocus();
				}
			});
			btnGroup.add(btnDesc);
			btnDesc.setBounds(288, 87, 121, 23);
		}
		return btnDesc;
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("자료를 더블클릭하면, 수정·삭제가 가능합니다.");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel.setBounds(12, 10, 466, 23);
		}
		return lblNewLabel;
	}
}
