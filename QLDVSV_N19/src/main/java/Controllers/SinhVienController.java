package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.ChuyenVienDAO;
import DAO.NganhDAO;
import DAO.SinhVienDAO;
import DAO.TaiKhoanDAO;
import Models.ChuyenVien;
import Models.SinhVien;
import Models.TaiKhoan;

/**
 * Servlet implementation class SinhVienController
 */
@MultipartConfig
@WebServlet("/SinhVien")
public class SinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinhVienController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
        try {
            switch (action) {
                case "ThongTinSinhVien":
                	getSinhVien(request,response);
                    break;
                case "listSinhVien":
                	getLstSinhVien(request,response);
                	break;
                case "chinhsua":
                	getChinhSuaSinhVien(request,response);
                	break;
                case "them":
                	getThemSinhVien(request,response);
                	break;
                case "doithongtin":
                	//getDoiThongTinSinhVien(request,response);
                	break;
                default:
                	break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}

	
	protected void getSinhVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mssv = request.getParameter("id");
		String email = request.getParameter("email");
		SinhVien sv = SinhVienDAO.getSinhVien(mssv);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienThongTinCaNhan.jsp");
		request.setAttribute("tennganh", NganhDAO.tenNganh(sv.getMaNganh()));
		request.setAttribute("SinhVien", sv);
		request.setAttribute("email", email);
		dispatcher.forward(request, response);
	}
	
	protected void getChinhSuaSinhVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mssv = request.getParameter("mssv");//
		
		String ho = request.getParameter("ho");//
		System.out.print(ho);
		String maNganh = request.getParameter("nganh");//
		int diemCTXH = Integer.parseInt(request.getParameter("diemctxh"));
		String khoaHoc = request.getParameter("khoahoc");
		String tenDem = request.getParameter("tendem");//
		String ten = request.getParameter("ten");//
		String gt = request.getParameter("gt");
		
		int gioiTinh = 1;
		if( gt == "Nữ")
			gioiTinh = 0;
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");//
		java.util.Date date = format.parse(request.getParameter("ngaysinh"));
		java.sql.Date namSinh = new java.sql.Date(date.getTime());
		
		String cccd = request.getParameter("cccd");//
		String sdt = request.getParameter("sdt");//
		int trangthai = 1;//
		 
		SinhVien sv = new SinhVien(mssv, ho, maNganh, diemCTXH, khoaHoc, tenDem, ten, gioiTinh, namSinh,cccd, sdt, trangthai );
		SinhVienDAO.updateSinhVien(sv);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminChiTietSinhVien.jsp");
		request.setAttribute("mssv", mssv);
		request.setAttribute("status", "true");
		dispatcher.forward(request, response);
	}
	
	protected void getThemSinhVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mssv = request.getParameter("mssv");//
		String ho = request.getParameter("ho");//
		String maNganh = request.getParameter("nganh");//
		String tenDem = request.getParameter("tendem");//
		String ten = request.getParameter("ten");//
		
		int gt = Integer.parseInt(request.getParameter("gt"));
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");//
		java.util.Date date = format.parse(request.getParameter("ngaysinh"));
		java.sql.Date namSinh = new java.sql.Date(date.getTime());
		
		String cccd = request.getParameter("cccd");//
		String sdt = request.getParameter("sdt");//
		int trangthai = 1;//
		
		String matkhau = request.getParameter("mk");
		String email = request.getParameter("email");
		
		TaiKhoan tk = new TaiKhoan(mssv, matkhau, email, 2, 1);
		SinhVien sv = new SinhVien(mssv, ho, maNganh, 0, "2023-2027", tenDem, ten, gt, namSinh,cccd, sdt, trangthai );
		boolean status = TaiKhoanDAO.addTaiKhoan(tk);
		if(status )
			SinhVienDAO.themsv(sv);
		System.out.print(status);
		
		response.sendRedirect("SinhVien?action=listSinhVien");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void getLstSinhVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SinhVien> lst = SinhVienDAO.getLstSinhVienVien();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLSinhVien.jsp");
		request.setAttribute("listSinhVien", lst);
        dispatcher.forward(request, response);
	}

}
