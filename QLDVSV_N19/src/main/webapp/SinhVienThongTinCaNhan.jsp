<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.SinhVien" %>
<%@ page import="DAO.NganhDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý dịch vụ sinh viên</title>
	<link rel="stylesheet" href="assets/vendors/sweetalert2/sweetalert2.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="stylesheet" href="assets/css/appsv.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
	<%
		String mssv = (String) session.getAttribute("taikhoan");
		if (mssv == null){
			response.sendRedirect("DangNhap.jsp");
		}
	%>
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
        <div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="assets/images/logo/logo.png" alt="Logo" srcset=""></a>
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
                        <li class="sidebar-item active">
                            <a href="SinhVien?action=ThongTinSinhVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-person-square"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>

                        <li class="sidebar-item  ">
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
        
        <div class="page-heading">
            	
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

            
                <div class="page-title">
                    <div class="row">
                        <div class="col-12 col-md-6 order-md-1 order-last">
                            <h3>Thông tin cá nhân</h3>
                        </div>
                    </div>
                </div>

                <section id="basic-horizontal-layouts">
                    <div class="row match-height">
                        <div class="col-md-6 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Thông tin sinh viên</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-horizontal">
                                            <div class="form-body">
                                                <div class="row" style="margin-top:-350px;">
                                                    <div class="col-md-8">
                                                    <label>MSSV : </label>
                                                    <label><c:out value="${SinhVien.getMSSV()}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <br><label>Họ và tên :</label>
                                                        <label><c:out value="${SinhVien.getHo()}" /> <c:out value="${SinhVien.getTenDem()}" /> <c:out value="${SinhVien.getTen()}" /> </label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <br><label>Ngày sinh :</label>
                                                        <label><c:out value="${SinhVien.getNamSinh()}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <br><label>Giới tính : <c:if test="${SinhVien.getGioiTinh()==1}">
										                             Nam
										                        </c:if> 
                                                            	<c:if test="${SinhVien.getGioiTinh()==0}">
										                             Nữ
										                        </c:if> </label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <br><label>CCCD : <c:out value="${SinhVien.getCCCD()}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <br><label>Ngành : <c:out value="${tennganh}" /></label>
                                                    </div>
                                                    
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <br><label>Khóa học : <c:out value="${SinhVien.getKhoaHoc()}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-12">
                       
                            <div class="card">
	            
                                <div class="card-header">
                                    <h4 class="card-title">Thông tin liên lạc</h4>
                                </div>
                                 <div class="avatar avatar-xl me-3" >
	                                <img src="assets/images/faces/2.jpg" alt="" srcset="" style="height: 150px; width: auto;margin-top:100px; margin-left:100px;">
	                            </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-horizontal">
                                            <div class="form-body">
                                                <div class="row" style="margin-top: -350px;">
                                                    <div class="col-md-8">
                                                        <label>Điện thoại : <c:out value="${SinhVien.getSdt()}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    <div class="col-md-12">
                                                      	<label>Email : <c:out value="${email}" /></label>
                                                    </div>
                                                    <div class="col-md-8 form-group">
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
				<div class="buttons1" style="margin-left:63%; margin-top: -45%;">
                            <a href="DoiMatKhau.jsp?taikhoan=${taikhoan}&email=${email}&loai=${loai}" class="btn btn-primary">Đổi mật khẩu</a>
                            <a href="CapNhatThongTin.jsp?taikhoan=${taikhoan}&email=${email}&loai=${loai}" class="btn btn-primary">Cập nhật thông tin</a>
                </div>
        </div>
    </div>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <script src="assets/vendors/apexcharts/apexcharts.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>
	<script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>

</html>