<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/vendors/toastify/toastify.css">
    <link rel="stylesheet" href="assets/vendors/iconly/bold.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>
<body>
	<%
		String mssv = (String) session.getAttribute("taikhoan");
		String email = (String) session.getAttribute("email");
	 	int loai = (int) session.getAttribute("loai");
		if (mssv == null){
			response.sendRedirect("DangNhap.jsp");
		}
	%>
    <div id="app">
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>
           
            <div class="cardStyle">
            	  <script src="assets/vendors/toastify/toastify.js"></script>
            	
            	<c:if test="${status == 'successfull!'}">
							    <script>
							            Toastify({
							                text: "Đổi mật khẩu thành công!",
							                duration: 3000,
							                close: true,
							                gravity: "top",
							                position: "center",
							                backgroundColor: "#198754",
							            }).showToast();
							    </script>
							${status = ''}
					</c:if> 
					<c:if test="${status == 'failed!'}">
							    <script>
							            Toastify({
							                text: "Mật khẩu cũ có thể không đúng!",
							                duration: 3000,
							                close: true,
							                gravity: "top",
							                position: "center",
							                backgroundColor: "#dc3545",
							            }).showToast();
							    </script>
							${status = ''}
					</c:if>
                  <form action="taikhoan?action=doimatkhau" method="post" >
                    <h3 class="formTitle">
                      ĐỔI MẬT KHẨU</h3>
                	<input type ="hidden" value = <%=loai%> name = "loai">
					<input type="hidden" value= <%=mssv%> name = "taikhoan" required>
                	<h3></h3>
                <div class="inputDiv">
                    <label class="inputLabel" for="passwordold">Mật khẩu cũ</label>
                    <input type="password" id="passwordold" name = "omk" required>
                    </div>
                
                  <div class="inputDiv">
                    <label class="inputLabel" for="password">Mật khẩu mới</label>
                    <input type="password" id="password" name = "nmk" required>
                  </div>  
                  <div class="inputDiv">
                    <label class="inputLabel" for="confirmPassword">Nhập lại mật khẩu mới</label>
                    <input type="password" id="confirmPassword" name="mkxn">
                  </div>
                  <div class="buttonWrapper1">
                    <button type="submit" id="submitButton" onclick="validateSignupForm()" class="btn btn-primary me-1 mb-1">
                      <span>Đồng ý</span>
                    </button>
                    
                  </div>

                  
                </form>
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
            </div>
         
        </div>
    </div>
    
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendors/apexcharts/apexcharts.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>
    <script src="assets/js/extensions/DoiMatKhau.js"></script>
    
    <script src="assets/js/main.js"></script>
</body>

</html>