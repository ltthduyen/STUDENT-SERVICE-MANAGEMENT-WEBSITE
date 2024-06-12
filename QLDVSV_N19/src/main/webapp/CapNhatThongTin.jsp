<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.SinhVienDAO" %>
<%@ page import="DAO.ChuyenVienDAO" %>
<%@ page import="DAO.QuanTriVienDAO" %>
<%@ page import="Models.ChuyenVien" %>
<%@ page import="Models.SinhVien" %>
<%@ page import="Models.QuanTriVien" %>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QLDVSV</title>
	<link rel="stylesheet" href="assets/vendors/toastify/toastify.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/iconly/bold.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
	<%
		String taikhoan = (String) session.getAttribute("taikhoan");
		int loai = (int) session.getAttribute("loai");
		if (taikhoan == null){
			response.sendRedirect("DangNhap.jsp");
		}
	%>
	<script src="assets/vendors/toastify/toastify.js"></script>
	<c:if test="${status == true}">
		    <script>
		            Toastify({
		                text: "Cập nhật thành công!",
		                duration: 3000,
		                close: true,
		                gravity: "top",
		                position: "center",
		                backgroundColor: "#198754",
		            }).showToast();
		    </script>
		${status = null}
</c:if> 
    <div id="app">
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>
        
            <div class="cardStyle">
            	<% 
            		String ho, tendem , ten, sdt;
            		if(loai == 0) {
            			QuanTriVien qtv = QuanTriVienDAO.getQuanTriVien(taikhoan);
            			ho = qtv.getHo();
            			tendem = qtv.getTenDem();
            			ten = qtv.getTen();
            			sdt = qtv.getSdt();
            		} else if(loai==1){
            			ChuyenVien qtv = ChuyenVienDAO.getChuyenVien(taikhoan);
            			ho = qtv.getHo();
            			tendem = qtv.getTenDem();
            			ten = qtv.getTen();
            			sdt = qtv.getSdt();
            		} else{
            			SinhVien qtv = SinhVienDAO.getSinhVien(taikhoan);
            			ho = qtv.getHo();
            			tendem = qtv.getTenDem();
            			ten = qtv.getTen();
            			sdt = qtv.getSdt();
            		}
            	%>
                  <form action="taikhoan?action=capnhat" method="post" name="signupForm" id="signupForm" accept-charset="UTF-8">
                    
                    <img src="" id="signupLogo"/>
                    
                    <h3 class="formTitle1">
                      CẬP NHẬT THÔNG TIN</h3>
                
                <div class="inputDiv">
                    <label class="inputLabel" for="sdt">Họ</label>
                    <input id="Ho"  value ="<c:out value='<%=ho %>'/>" name="ho" required>
                    <label class="inputLabel" for="sdt">Tên đệm</label>
                    <input id="td" value ="<c:out value='<%=tendem %>' />" name="tendem" required>
                    <label class="inputLabel" for="sdt">Tên</label>
                    <input id="ten" value ="<c:out value='<%=ten%>' />" name="ten" required>
                    <input type="hidden" value="<c:out value='<%=loai%>' />" name="loai"> 
                    <input type="hidden" value="<c:out value='<%=taikhoan%>' />" name="taikhoan"> 
                </div>
                    
                <div class="inputDiv">
                    <label class="inputLabel" for="sdt">Số điện thoại</label>
                    <input id="sdt" value ="<c:out value='<%=sdt %>' />" name="sdt" required>
                    </div>
                  
                  <div class="buttonWrapper1">
                    <button type="submit" id="submitButton" onclick="validateSignupForm()" class="btn btn-primary me-1 mb-1">
                      <span>Đồng ý</span>
                    </button>
                  </div>
                  
                  <div class="buttonWrapper">
                  	<c:if test="${loai == 1}">
                  		<a  href = "ChuyenVien?action=ThongTinChuyenVien&id=${taikhoan}&email=${email}" class="btn btn-light-secondary me-1 mb-1">
	                      <span>Quay về</span>
	                    </a>
                  	</c:if>
                  	<c:if test="${loai == 0}">
                  		<a  href = "quantrivien?action=ThongTinQuanTriVien&id=${taikhoan}&email=${email}" class="btn btn-light-secondary me-1 mb-1">
	                      <span>Quay về</span>
	                    </a>
                  	</c:if>
                  	<c:if test="${loai == 2}">
                  		<a   href = "SinhVien?action=ThongTinSinhVien&id=${taikhoan}&email=${email}" class="btn btn-light-secondary me-1 mb-1">
	                      <span> Quay về</span>
	                    </a>
                  	</c:if>
                    
                  </div>
                    
                </form>
            </div>
   
        </div>
    </div>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <script src="assets/vendors/apexcharts/apexcharts.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>
    <script src="assets/js/extensions/CapNhatThongTin.js"></script>

    <script src="assets/js/main.js"></script>
</body>

</html>