package jdbc_semi;

import java.util.Vector;

import javax.swing.JTextField;

public interface ProductInter {
	public boolean insert(ProductVo vo); // 데이터 저장
	public boolean update(ProductVo vo); // 데이터 수정
	public boolean delete(ProductVo vo); // 데이터 삭제
	public Vector<Vector> search(String find); // 데이터 조회
	public ProductVo select(int serial); // 1건의 생산제품 정보
	public Vector<Vector> productMonth(String find); // 월별 생산량
	public Vector<Vector> productParts(String parts); // 제품별 생산량
}
