package Models;

public class GXN {
	private String MaGXN;
	private String TenGXN;
	private String MoTa;
	private int TrangThai;
	
	public GXN() {
		super();
	}
	
	public GXN(String maGXN, String tenGXN, String moTa, int trangThai) {
		super();
		MaGXN = maGXN;
		TenGXN = tenGXN;
		MoTa = moTa;
		TrangThai = trangThai;
	}

	public String getMaGXN() {
		return MaGXN;
	}

	public void setMaGXN(String maGXN) {
		MaGXN = maGXN;
	}

	public String getTenGXN() {
		return TenGXN;
	}

	public void setTenGXN(String tenGXN) {
		TenGXN = tenGXN;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	
}
