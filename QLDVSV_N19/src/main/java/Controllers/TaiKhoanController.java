package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChuyenVienDAO;
import DAO.NganhDAO;
import DAO.QuanTriVienDAO;
import DAO.SinhVienDAO;
import DAO.TaiKhoanDAO;
import Models.TaiKhoan;
import Util.Email;

@WebServlet("/taikhoan")
public class TaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.print(action);
        try {
            switch (action) {
                case "login":
                	postLogin(request,response);
                	break;
                case "doimatkhau":
                	postDoimatKhau(request,response);
                	break;
                case "forgotpass":
    				postforgotpass(request, response);
    				break;
                case "capnhat":
    				capnhat(request, response);
    				break;
                default:
                	break;
            }
		}catch (Exception e) {
			e.printStackTrace();
		}		
    }
	
	protected void capnhat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taikhoan = request.getParameter("taikhoan");
		String ho = request.getParameter("ho");
		String tendem = request.getParameter("tendem");
		String ten = request.getParameter("ten");
		String sdt = request.getParameter("sdt");
		System.out.print(taikhoan+ho+tendem+ten);
		
		int loai = Integer.parseInt(request.getParameter("loai"));
		boolean status = false;
		if(loai == 0){
			status = QuanTriVienDAO.CapNhat(taikhoan, ho, tendem, ten, sdt);
		} else if(loai == 1) {
			status = ChuyenVienDAO.CapNhat(taikhoan, ho, tendem, ten, sdt);
		} else {
			status = SinhVienDAO.CapNhat(taikhoan, ho, tendem, ten, sdt);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("CapNhatThongTin.jsp");
		request.setAttribute("status", status);
		request.setAttribute("taikhoan", taikhoan);
		request.setAttribute("loai", loai);
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void postLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taikhoan = request.getParameter("taikhoan");
        String matkhau = request.getParameter("matkhau");
        int loai = Integer.parseInt(request.getParameter("loai"));
        
        TaiKhoan tk = new TaiKhoan();
        tk.setTaiKhoan(taikhoan);
        tk.setMatKhau(matkhau);
        tk.setLoai(loai);
        
        try {
        	HttpSession session = request.getSession();  
        	boolean status = TaiKhoanDAO.onLogin(tk);
            if (status){
            	TaiKhoan rTk = TaiKhoanDAO.getTaiKhoan(taikhoan);
                session.setAttribute("taikhoan", taikhoan);
                session.setAttribute("email", rTk.getEmail());
                session.setAttribute("loai", rTk.getLoai());
                
            	if(loai == 0) 
            	{
            		response.sendRedirect("AdminDashboard.jsp");
            	}else if( loai == 1 ){
            		response.sendRedirect("ChuyenVienTrangChu.jsp");
            	} else if( loai == 2 ) {
            		response.sendRedirect("SinhVienTrangChu.jsp");
            	}
            	
            } else {
                session.setAttribute("status", "failed!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("DangNhap.jsp") ;
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	protected void postforgotpass(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String host;
		String port;
		String user;
		String pass;
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
		String taikhoan = request.getParameter("taikhoan");
		String email = request.getParameter("email");
		String matkhau = TaiKhoanDAO.QuenMatKhau(taikhoan);
		System.out.println("\n" + taikhoan);
		System.out.println(email);
		System.out.println(matkhau);
		
		try {
			Email.sendEmail(host, port, user, pass, email, "Forgot Password", matkhau);
			request.setAttribute("status", "successfully!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("DangNhap.jsp");
			dispatcher.forward(request, response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	protected void postDoimatKhau(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taikhoan = request.getParameter("taikhoan");
		String nmk = request.getParameter("nmk");
		String omk = request.getParameter("omk");
		String mkxn = request.getParameter("mkxn");
		
		String email = request.getParameter("email");
		int loai = Integer.parseInt(request.getParameter("loai"));
		
		TaiKhoan tk = new TaiKhoan();
		tk.setTaiKhoan(taikhoan);
		tk.setMatKhau(omk);
		tk.setLoai(loai);
		
		boolean status = TaiKhoanDAO.onLogin(tk);
		HttpSession session = request.getSession(); 
		session.setAttribute("taikhoan", taikhoan);
        session.setAttribute("email", email);
        
		if(status) {
				session.setAttribute("status", "successfull!");
				boolean check = TaiKhoanDAO.doiMatKhau(taikhoan, nmk);
				System.out.println(check);
			
				
		} else{
			session.setAttribute("status", "failed!");
			
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("DoiMatKhau.jsp") ;
		dispatcher.forward(request, response);
	}
	protected void postGetTaiKhoan(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
