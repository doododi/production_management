// VO : Value Object | 값을 위해 쓰인다. = 불변(getter의 기능만 존재)
package jdbc_semi;

import java.util.Vector;

public class ProductVo {
	String code;
	String codeName;
	String spec;
	int price;

	int serial;
	int ea;
	String mdate;
	
	public Vector getVector() { // Vector 타입의 getter(?)
		Vector<String> v = new Vector();
		v.add(code);
		v.add(codeName);
		v.add(spec);
		v.add(price + "");
		v.add(serial + "");
		v.add(ea + "");
		v.add(mdate);
		
		return v;
	}

	// getter, setter
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getCodeName() {return codeName;}
	public void setCodeName(String codeName) {this.codeName = codeName;}
	public String getSpec() {return spec;}
	public void setSpec(String spec) {this.spec = spec;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getSerial() {return serial;}
	public void setSerial(int serial) {this.serial = serial;}
	public int getEa() {return ea;}
	public void setEa(int ea) {this.ea = ea;}
	public String getMdate() {return mdate;}
	public void setMdate(String mdate) {this.mdate = mdate;}
}
