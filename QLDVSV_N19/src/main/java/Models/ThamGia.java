package Models;

public class ThamGia {
	private String MSSV;
	private String MaHD;
	private java.sql.Date NgayDK;
	private int TrangThai;
	
	public ThamGia() {
		super();
	}
	
	public ThamGia(String mssv, String maHD, java.sql.Date ngayDK, int trangThai) {
		super();
		MSSV = mssv;
		MaHD = maHD;
		NgayDK = ngayDK;
		TrangThai = trangThai;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public java.sql.Date getNgayDK() {
		return NgayDK;
	}

	public void setNgayDK(java.sql.Date ngayDK) {
		NgayDK = ngayDK;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	
}
