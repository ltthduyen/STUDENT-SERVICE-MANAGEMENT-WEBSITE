package Models;

public class ChuyenVien {
	private String MSCTSV;
	private String Ho;
	private String TenDem;
	private String Ten;
	private int GioiTinh;
	private java.sql.Date NamSinh;
	private String CCCD;
	private String Sdt;
	
	public ChuyenVien() {
		super();
	}
	
	public ChuyenVien(String msctsv, String ho, String tenDem, String ten, int gioiTinh, java.sql.Date namSinh, String cccd, String sdt) {
		MSCTSV = msctsv;
		Ho = ho;
		TenDem = tenDem;
		Ten = ten;
		GioiTinh = gioiTinh;
		NamSinh = namSinh;
		CCCD = cccd;
		Sdt = sdt;
	}

	public String getMSCTSV() {
		return MSCTSV;
	}

	public void setMSCTSV(String mSCTSV) {
		MSCTSV = mSCTSV;
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

	public java.util.Date getNamSinh() {
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
