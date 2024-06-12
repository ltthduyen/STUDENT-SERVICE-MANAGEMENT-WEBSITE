package Models;

public class YeuCau {
	private String MaYC;
	private String MSSV;
	private String MaGXN;
	private java.sql.Date NgayGui;
	private java.sql.Date NgayNhan;
	private int SoLuong;
	private String NoiDung;
	private int HocKi;
	private String NamHoc;
	private int TrangThai;
	
	public YeuCau() {
		super();
	}
	
	public YeuCau(String maYC, String mssv, String maGXN, java.sql.Date ngayGui, java.sql.Date ngayNhan, int soLuong, String noiDung, int hocKi, String namHoc, int trangThai) {
		super();
		MaYC = maYC;
		MSSV = mssv;
		MaGXN = maGXN;
		NgayGui = ngayGui;
		NgayNhan = ngayNhan;
		SoLuong = soLuong;
		NoiDung = noiDung;
		HocKi = hocKi;
		NamHoc = namHoc;
		TrangThai = trangThai;
	}

	public String getMaYC() {
		return MaYC;
	}

	public void setMaYC(String maYC) {
		MaYC = maYC;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public String getMaGXN() {
		return MaGXN;
	}

	public void setMaGXN(String maGXN) {
		MaGXN = maGXN;
	}

	public java.sql.Date getNgayGui() {
		return NgayGui;
	}

	public void setNgayGui(java.sql.Date ngayGui) {
		NgayGui = ngayGui;
	}

	public java.sql.Date getNgayNhan() {
		return NgayNhan;
	}

	public void setNgayNhan(java.sql.Date ngayNhan) {
		NgayNhan = ngayNhan;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public int getHocKi() {
		return HocKi;
	}

	public void setHocKi(int hocKi) {
		HocKi = hocKi;
	}

	public String getNamHoc() {
		return NamHoc;
	}

	public void setNamHoc(String namHoc) {
		NamHoc = namHoc;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	
}
