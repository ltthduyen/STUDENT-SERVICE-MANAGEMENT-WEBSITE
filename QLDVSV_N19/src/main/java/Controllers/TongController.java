package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAO.TongDAO;
import Models.Tong;

@WebServlet("/Tong")
public class TongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public TongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        try {
            switch (action) {
                case "TongYC":
                	getTongYC(request,response);
                	break;
                default:
                	break;
            }
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getTongYC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Tong> lst = TongDAO.getTongYC();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.jsp");
		List<String> Thang = new ArrayList<>();
		List<String> Nam = new ArrayList<>();
		List<Integer> sl = new ArrayList<>();
		
		 for(Tong t:lst){  
		      Thang.add(t.getThang());
		      Nam.add(t.getNam());
		      sl.add(t.getSoYC());
		   }  
		request.setAttribute("Thang", Thang);
		request.setAttribute("Nam", Nam);
		request.setAttribute("sl", sl);
        dispatcher.forward(request, response);
		
	}

}