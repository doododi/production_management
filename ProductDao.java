// DAO : Data Access Object | 데이터베이스의 data에 접근하기 위한 객체 = 가변(setter)

package jdbc_semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

public class ProductDao implements ProductInter {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ProductDao() {
		conn = new DBConn().getConn();
	}

	@Override
	public boolean insert(ProductVo vo) {
		boolean r = false;
		String sql = "insert into products(code, mdate, ea) values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1,  vo.getCode());
			ps.setString(2, vo.getMdate());
			ps.setInt(3, vo.getEa());
			
			int cnt = ps.executeUpdate();
			if(cnt>0) {
				r = true;
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	@Override
	public boolean update(ProductVo vo) {
		boolean r = false;
		String sql = "update products set ea=?, mdate=? where serial=?";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getEa());
			ps.setString(2, vo.getMdate());
			ps.setInt(3, vo.getSerial());
			
			int rst = ps.executeUpdate();
			if(rst>0) {
				r = true;
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean delete(ProductVo vo) {
		boolean r = false;
		String sql = "delete from products where serial=? and code=?";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getSerial());
			ps.setString(2, vo.getCode());
			
			int rst = ps.executeUpdate();
			if(rst>0) {
				r = true;
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Vector<Vector> search(String find) {
		Vector<Vector> list = new Vector<Vector>();
		String sql = "select serial, mdate, P.code, codeName, format(price, 0) as price, ea, format(ea*price, 0) as amt "
				   + "from products P join parts S on P.code = S.code "
				   + "where P.code like ? or S.codeName like ? or date_format(mdate, '%Y-%m') like ?";
		try {
			if(ProductSearch.btnAsc.isSelected()) {
				sql += " order by ea*price";
			} else if(ProductSearch.btnDesc.isSelected()) {
				sql += " order by ea*price desc";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + find + "%");
			ps.setString(2, "%" + find + "%");
			ps.setString(3, "%" + find + "%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getInt("serial")+"");
				v.add(rs.getString("mdate"));
				v.add(rs.getString("P.code"));
				v.add(rs.getString("codeName"));
				v.add(rs.getString("price"));
				v.add(rs.getString("ea")+"");
				v.add(rs.getString("amt")+"");
				
				list.add(v);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductVo select(int serial) {
		ProductVo vo = new ProductVo();
		String sql = "select serial, P.code, codeName, price, ea, mdate from products P join parts S on P.code = S.code where serial = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, serial);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo.setSerial(serial);
				vo.setCode(rs.getString("P.code"));
				vo.setCodeName(rs.getString("codeName"));
				vo.setPrice(rs.getInt("price"));
				vo.setEa(rs.getInt("ea"));
				vo.setMdate(rs.getString("mdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public ProductVo select(String code) {
		ProductVo vo = new ProductVo();
		String sql = "select * from parts where code=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo.setCode(code);
				vo.setCodeName(rs.getString("codeName"));
				vo.setPrice(rs.getInt("price"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public Vector<Vector> productMonth(String find) {
		Vector<Vector> list = new Vector<>();
		String sql = "select date_format(mdate, '%Y-%m-%d') as mdate, p.codeName, sum(pd.ea) as ea, sum(pd.ea*p.price) as amt "
				+ "from products pd join parts p on pd.code = p.code where date_format(mdate, '%Y-%m') like ? group by p.codeName, mdate, ea, price";
		try {
			if(ProductMonth.btnAsc.isSelected()) {
				sql += " order by ea*price";
			} else if(ProductMonth.btnDesc.isSelected()) {
				sql += " order by ea*price desc";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + find + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("mdate"));
				v.add(rs.getString("codeName"));
				v.add(rs.getString("ea"));
				v.add(rs.getString("amt"));
				
				list.add(v);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Vector<Vector> productParts(String parts) {
		Vector<Vector> list = new Vector<>();
		String sql = "select mdate, p.codeName, sum(pd.ea) as ea, sum(pd.ea*p.price) as amt "
				+ "from products pd join parts p on pd.code = p.code where p.codeName=? group by mdate, p.codeName, ea, price";
		try {
			if(ProductPart.btnAsc.isSelected()) {
				sql += " order by ea*price";
			} else if (ProductPart.btnDesc.isSelected()) {
				sql += " order by ea*price desc";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, parts);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("mdate"));
				v.add(rs.getString("codeName"));
				v.add(rs.getString("ea"));
				v.add(rs.getString("amt"));
				
				list.add(v);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}