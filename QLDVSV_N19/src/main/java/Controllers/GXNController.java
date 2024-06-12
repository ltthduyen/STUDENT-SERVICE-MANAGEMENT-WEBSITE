package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GXNDAO;
import Models.GXN;


@WebServlet("/GXN")
public class GXNController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GXNController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "listGXN":
					getList(request,response);
					break;
				case "on":
					getBat(request,response);
					break;
				case "off":
					getTat(request,response);
					break;
				default:
					break;
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<GXN> lst = GXNDAO.getlstGXN();
		String status = request.getParameter("status");
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLGiayXacNhan.jsp");
		request.setAttribute("listGXN", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
		request.setAttribute("status", status);
		dispatcher.forward(request, response);
	}
	protected void getBat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		String mgxn = request.getParameter("gxn");
		GXNDAO.setTrangThai(mgxn, 0);
		response.sendRedirect("GXN?action=listGXN&id="+msctsv+"&email="+email+"&status=on");
	}
	protected void getTat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		String mgxn = request.getParameter("gxn");
		GXNDAO.setTrangThai(mgxn, 1);
		response.sendRedirect("GXN?action=listGXN&id="+msctsv+"&email="+email+"&status=off");
	}

}
