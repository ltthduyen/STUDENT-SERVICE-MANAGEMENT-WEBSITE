package Controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GXNDAO;
import DAO.YeuCauDAO;
import Models.YeuCau;

@WebServlet("/yeucau")
public class YeuCauController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public YeuCauController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "ListYC":
				getLstYC(request, response);
				break;
			case "yeucau":
				getYC(request, response);
				break;
			case "svyeucau":
				getthemYC(request, response);
				break;
			case "xoa":
				getxoaYC(request, response);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void getthemYC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mayc = YeuCauDAO.Id();
		String mssv = request.getParameter("id");
		String mgxn = request.getParameter("gxn");
		int trangthai = GXNDAO.getTrangThaiGXN(mgxn);
		
		if(trangthai == 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienGXN.jsp");
			request.setAttribute("status", "false"); 
			request.setAttribute("taikhoan", mssv); 
			dispatcher.forward(request,response);
			return;
		}
		int sl = Integer.parseInt(request.getParameter("soluong"));
		
		java.util.Date date = new java.util.Date();
		java.sql.Date ngui = new Date(date.getTime());
		java.sql.Date ngnhan = new Date(date.getTime());
		
		YeuCau yc = new YeuCau(mayc, mssv, mgxn, ngui, ngnhan,sl, "", 1, "", 0 );
		boolean status = YeuCauDAO.addYeuCau(yc);
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienGXN.jsp");
		 request.setAttribute("taikhoan", mssv); 
		 dispatcher.forward(request,response);
	}
	protected void getxoaYC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String myc = request.getParameter("mayc");
		System.out.print(myc);
		YeuCauDAO.xoaYeuCau(myc);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienGXN.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void getLstYC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<YeuCau> lst = YeuCauDAO.getLstYeuCau();
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienXuLyYeuCau.jsp");
		request.setAttribute("lstYeuCau", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
		dispatcher.forward(request, response);

	}

	protected void getYC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String myc = request.getParameter("myc");
		String email = request.getParameter("email");
		YeuCau yc = YeuCauDAO.getYeuCau(myc);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienXuLyChiTietYeuCau.jsp");
		request.setAttribute("magxn", yc.getMaGXN());
		request.setAttribute("yeucau", yc);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
		dispatcher.forward(request, response);

	}

}
