package Models;

public class TaiKhoan {
	private String TaiKhoan;
	private String MatKhau;
	private String Email;
	private int Loai;
	private int TrangThai;
	
	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String taiKhoan, String matKhau, String email, int loai, int trangThai) {
		super();
		TaiKhoan = taiKhoan;
		MatKhau = matKhau;
		Email = email;
		Loai = loai;
		TrangThai = trangThai;
	}
	
	public String getTaiKhoan() {
		return TaiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		TaiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getLoai() {
		return Loai;
	}
	public void setLoai(int loai) {
		Loai = loai;
	}
	public int getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
}
