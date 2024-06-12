package Models;

public class PhanHoi {
	private String MaPH;
	private String MaYC;
	private String NguoiPH;
	private String NoiDung;
	private java.sql.Date NgayPH;
	
	public PhanHoi() {
		super();
	}
	
	public PhanHoi(String maPH, String maYC, String nguoiPH, String noiDung, java.sql.Date ngayPH) {
		super();
		MaPH = maPH;
		MaYC = maYC;
		NguoiPH = nguoiPH;
		NoiDung = noiDung;
		NgayPH = ngayPH;
	}

	public String getMaPH() {
		return MaPH;
	}

	public void setMaPH(String maPH) {
		MaPH = maPH;
	}

	public String getMaYC() {
		return MaYC;
	}

	public void setMaYC(String maYC) {
		MaYC = maYC;
	}

	public String getNguoiPH() {
		return NguoiPH;
	}

	public void setNguoiPH(String nguoiPH) {
		NguoiPH = nguoiPH;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public java.sql.Date getNgayPH() {
		return NgayPH;
	}

	public void setNgayPH(java.sql.Date ngayPH) {
		NgayPH = ngayPH;
	}
	
	public String ID(String myc){
		
		return "";
	}
}
