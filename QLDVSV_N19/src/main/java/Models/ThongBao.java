package Models;

public class ThongBao {
	private String MaTB;
	private String TieuDe;
	private String NoiDung;
	private String NguoiGui;
	private String NguoiNhan;
	private java.sql.Date NgayGui;
	
	public ThongBao() {
		super();
	}
	
	public ThongBao(String maTB, String tieuDe, String noiDung, String nguoiGui, String nguoiNhan, java.sql.Date ngayGui) {
		super();
		MaTB = maTB;
		TieuDe = tieuDe;
		NoiDung = noiDung;
		NguoiGui = nguoiGui;
		NguoiNhan = nguoiNhan;
		NgayGui = ngayGui;
	}

	public String getMaTB() {
		return MaTB;
	}

	public void setMaTB(String maTB) {
		MaTB = maTB;
	}

	public String getTieuDe() {
		return TieuDe;
	}

	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getNguoiGui() {
		return NguoiGui;
	}

	public void setNguoiGui(String nguoiGui) {
		NguoiGui = nguoiGui;
	}

	public String getNguoiNhan() {
		return NguoiNhan;
	}

	public void setNguoiNhan(String nguoiNhan) {
		NguoiNhan = nguoiNhan;
	}

	public java.sql.Date getNgayGui() {
		return NgayGui;
	}

	public void setNgayGui(java.sql.Date ngayGui) {
		NgayGui = ngayGui;
	}
	
	
}
