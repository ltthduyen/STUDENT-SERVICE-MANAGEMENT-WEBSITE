<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="DAO.YeuCauDAO" %>
<%@page import="DAO.GXNDAO" %>
<%@page import ="Models.GXN" %>
<%@page import ="Models.YeuCau" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<%
		String mssv = (String) session.getAttribute("taikhoan");
		if (mssv == null){
			response.sendRedirect("DangNhap.jsp");
		}
	%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý dịch vụ sinh viên</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/vendors/sweetalert2/sweetalert2.min.css">
    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
    
    
</head>

<body>
    <div id="app">
    <div style="margin-left: 1110px;" class="avatar avatar-lg me-2">
    	<img src="assets/images/faces/3.jpg" alt="Logo" srcset="">
  	</div>
       <a style ="margin-left: 150px" class="logout-link" onclick="myFunction()">Đăng xuất</a>
    <script>
        function myFunction() {
            Swal.fire({
                icon: 'question',
                title: 'Bạn có muốn thoát không?',
                showCancelButton: true,
                confirmButtonText: 'Đồng ý',
                cancelButtonText: 'Hủy'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Perform logout actions here
                    window.location.href = 'DangNhap.jsp';
                }
            });
        }
    </script>
    <c:if test="${status == 'false'}">
		    <script>
		            Toastify({
		                text: "Giấy xác nhận đang bảo trì, không thể đăng ký!",
		                duration: 3000,
		                close: true,
		                gravity: "top",
		                position: "center",
		                backgroundColor: "#dc3545",
		            }).showToast();
		    </script>
		${status = ''}
	</c:if> 
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="assets/images/logo/logo.png" alt="Logo"srcset=""></a>
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-item  ">
                            <a href="SinhVienTrangChu.jsp?id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Trang của bạn</span>
                            </a>
                        </li>
                        
                        <li class="sidebar-item  ">
                            <a href="SinhVien?action=ThongTinSinhVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-person-square"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>

                        <li class="sidebar-item active ">
                            <a href="SinhVienGXN.jsp" class='sidebar-link'>
                                <i class="bi bi-file-earmark-medical-fill"></i>
                                <span>Đăng ký giấy xác nhận</span>
                            </a>
                        </li>

                        <li class="sidebar-item  ">
                            <a href="SinhVienHoatDong.jsp" class='sidebar-link'>
                                <i class="bi bi-life-preserver"></i>
                                <span>Tham gia CTXH</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
            </div>
        </div>
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

            <div class="page-heading">
            	
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h4>Đăng ký giấy tờ</h4>
                        </div>
                    </div>
                </div>        
                <div class="col-12">
                    <div class="card">
                        <div class="card-header"></div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="row" style="margin-top:-380px;">
                                	<form action="yeucau?action=svyeucau" method="post">
                                		<input type ="hidden" name = "id" value ="${taikhoan}"}>
                                		<h6>Chọn giấy xác nhận :</h6>
                                        <fieldset class="form-group">
                                            <select class="form-select" id="basicSelect" style="width: 35%; margin-left: 200px;margin-top:-40px" name='gxn'>
                                                 <%for(GXN gxn: GXNDAO.getlstGXN()){%>
                                                 	<option value="<%=gxn.getMaGXN()%>"> <%=gxn.getTenGXN() %>
                                                 		<%if(gxn.getTrangThai() == 0 ) {%>
                                                 			(Đang bảo trì!)
                                                 		<%} %>
                                                 	</option>
                                                 <%} %>
                                            </select>
                                        </fieldset>
                                        <h6 style="margin-left:36px;margin-top:20px">Chọn số lượng :</h6>
                                        <div class="short-form">
                                            <input style="width:10%;margin-left: 200px;margin-top:-35px" value = "1" name = "soluong" min ="1" max="5" type="number" class="form-control" id="basicInput" placeholder="1">
                                        </div>
                                        <div >
		                                    <button style="width:fit-content; margin-top:20px;margin-left: 350px;" id="basic" class="btn btn-outline-primary btn-lg btn-block">Đăng ký</button>
		                                </div>
                                	</form>
                                </div>
                            <table class="table table-striped" id="table1" style="margin-top:10px;">
                                <thead>
                                    <tr>
                                        <th>Thời gian đăng ký</th>
                                        <th>Tên giấy xác nhận</th>
                                        <th>Mã nhận</th>
                                        <th>Thời gian nhận</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                        <%for(YeuCau yc: YeuCauDAO.getLstYeuCauSV(mssv)){ %>
                                        <tr>
                                        	<td> <c:out value="<%=yc.getNgayGui()%>"></c:out></td>
                                        	<td> <c:out value="<%=GXNDAO.getNameGXN(yc.getMaGXN())%>"></c:out></td>
                                        	<td> <c:out value="<%=yc.getMaYC()%>"></c:out></td>
                                        	<td> <c:out value="<%=yc.getNgayNhan()%>"></c:out> </td>
                                        	<td>
                                        	<%if (yc.getTrangThai() == 0 ){%>
						                             Chưa xử lý
						                     <%} else if (yc.getTrangThai() == 1 ){ %>
						                     		 Đã duyệt
						                     <%} else { %>
						                     		 Không duyệt
						                     <%} %>
						                    </td>
						                    <%if (yc.getTrangThai() == 0 ){%>
						                    	<td> <a href ="yeucau?action=xoa&mayc=<%=yc.getMaYC()%>"> Xóa </a></td>
						                    <%} %>
                                        <%} %>
                                    </tr>
                                </tbody>
                            </table>   	
                        </div>
                    </div>
                
                </div>
            </div>
    </div>        
                
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/extensions/sweetalert2.js"></script>
	<script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>
    <script src="assets/js/main.js"></script>

</body>

</html>
