package Models;

public class HoatDong {
	private String MaHD;
	private String TenHD;
	private String ToChuc;
	private String MoTa;
	private java.sql.Date NgayBatDau;
	private java.sql.Date NgayKetThuc;
	private int SoLuong;
	private int Diem;
	private int TrangThai;
	
	public HoatDong() {
		super();
	}
	
	public HoatDong(String maHD, String tenHD, String toChuc, String moTa, java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc, int soLuong, int diem, int trangThai) {
		super();
		MaHD = maHD;
		TenHD = tenHD;
		ToChuc = toChuc;
		MoTa = moTa;
		NgayBatDau = ngayBatDau;
		NgayKetThuc = ngayKetThuc;
		SoLuong = soLuong;
		Diem = diem;
		TrangThai = trangThai;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public String getTenHD() {
		return TenHD;
	}

	public void setTenHD(String tenHD) {
		TenHD = tenHD;
	}

	public String getToChuc() {
		return ToChuc;
	}

	public void setToChuc(String toChuc) {
		ToChuc = toChuc;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public java.sql.Date getNgayBatDau() {
		return NgayBatDau;
	}

	public void setNgayBatDau(java.sql.Date ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}

	public java.sql.Date getNgayKetThuc() {
		return NgayKetThuc;
	}

	public void setNgayKetThuc(java.sql.Date ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public int getDiem() {
		return Diem;
	}

	public void setDiem(int diem) {
		Diem = diem;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	
}
