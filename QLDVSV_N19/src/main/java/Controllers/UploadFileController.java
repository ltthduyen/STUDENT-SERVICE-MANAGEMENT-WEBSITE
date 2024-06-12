package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TaiKhoanDAO;

@WebServlet("/upload")
@MultipartConfig
public class UploadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String taikhoan = request.getParameter("taikhoan");
		InputStream input =	request.getPart("file").getInputStream();
		List<String> lst = TaiKhoanDAO.ImportExcel(request, input);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLSinhVien.jsp");
		request.setAttribute("taikhoan", taikhoan);
		request.setAttribute("lst", lst);
        dispatcher.forward(request, response);	
	}

}
