package Models;

public class ToChuc {
	private String MaTC;
	private String TenTC;
	
	public ToChuc() {
		super();
	}
	
	public ToChuc(String maTC, String tenTC) {
		super();
		MaTC = maTC;
		TenTC = tenTC;
	}

	public String getMaTC() {
		return MaTC;
	}

	public void setMaTC(String maTC) {
		MaTC = maTC;
	}

	public String getTenTC() {
		return TenTC;
	}

	public void setTenTC(String tenTC) {
		TenTC = tenTC;
	}
	
	
}
