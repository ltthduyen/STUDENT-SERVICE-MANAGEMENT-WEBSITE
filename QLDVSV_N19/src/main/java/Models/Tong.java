package Models;
public class Tong {
	private String Nam;
	private String Thang;
	private int SoYC;
	
	public Tong() {
		super();
	}
	
	public Tong(String nam, String thang, int tong) {
		super();
		Nam = nam;
		Thang = thang;
		SoYC = tong;
	}
	
	public String getNam() {
		return Nam;
	}

	public void setNam(String nam) {
		Nam = nam;
	}

	public String getThang() {
		return Thang;
	}

	public void setThang(String thang) {
		Thang = thang;
	}

	public int getSoYC() {
		return SoYC;
	}

	public void setSoYC(int soYC) {
		SoYC = soYC;
	}
}