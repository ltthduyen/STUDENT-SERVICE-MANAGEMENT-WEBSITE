package Models;

public class QuanTriVien {
	private String MSQT;
	private String Ho;
	private String TenDem;
	private String Ten;
	private int GioiTinh;
	private java.sql.Date NamSinh;
	private String CCCD;
	private String Sdt;
	
	public QuanTriVien(){
		super();
	}
	
	public QuanTriVien(String msqt, String ho, String tenDem, String ten, int gioiTinh, java.sql.Date namSinh, String cccd, String sdt) {
		super();
		MSQT = msqt;
		Ho = ho;
		TenDem = tenDem;
		Ten = ten;
		GioiTinh = gioiTinh;
		NamSinh = namSinh;
		CCCD = cccd;
		Sdt = sdt;
	}

	public String getMSQT() {
		return MSQT;
	}

	public void setMSQT(String mSQT) {
		MSQT = mSQT;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getTenDem() {
		return TenDem;
	}

	public void setTenDem(String tenDem) {
		TenDem = tenDem;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public int getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public java.sql.Date getNamSinh() {
		return NamSinh;
	}

	public void setNamSinh(java.sql.Date namSinh) {
		NamSinh = namSinh;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public String getSdt() {
		return Sdt;
	}

	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	
	
}


