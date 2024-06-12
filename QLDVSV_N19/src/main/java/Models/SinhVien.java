package Models;

public class SinhVien {
	private String MSSV;
	private String Ho;
	private String MaNganh;
	private int DiemCTXH;
	private String KhoaHoc;
	private String TenDem;
	private String Ten;
	private int GioiTinh;
	private java.sql.Date NamSinh;
	private String CCCD;
	private String Sdt;
	private int TrangThai;
	
	public SinhVien() {
		super();
	}
	
	public SinhVien(String mssv, String ho, String maNganh, int diemCTXH, String khoaHoc, String tenDem, String ten, int gioiTinh, java.sql.Date namSinh, String cccd, String sdt, int trangThai) {
		super();
		MSSV = mssv;
		Ho = ho;
		MaNganh = maNganh;
		DiemCTXH = diemCTXH;
		KhoaHoc = khoaHoc;
		TenDem = tenDem;
		Ten = ten;
		GioiTinh = gioiTinh;
		NamSinh = namSinh;
		CCCD = cccd;
		Sdt = sdt;
		TrangThai = trangThai;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getMaNganh() {
		return MaNganh;
	}

	public void setMaNganh(String maNganh) {
		MaNganh = maNganh;
	}

	public int getDiemCTXH() {
		return DiemCTXH;
	}

	public void setDiemCTXH(int diemCTXH) {
		DiemCTXH = diemCTXH;
	}

	public String getKhoaHoc() {
		return KhoaHoc;
	}

	public void setKhoaHoc(String khoaHoc) {
		KhoaHoc = khoaHoc;
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

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	
}
