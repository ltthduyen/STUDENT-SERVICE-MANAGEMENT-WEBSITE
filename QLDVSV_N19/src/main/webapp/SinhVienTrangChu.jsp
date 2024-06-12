<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.ThongBaoDAO" %>
<%@ page import="Models.ThongBao" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý dịch vụ sinh viên</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/iconly/bold.css">
	<link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">
    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
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
       <a class="logout-link" onclick="myFunction()">Đăng xuất</a>
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
                        <li class="sidebar-item active ">
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
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>
			
            <div class="page-heading">
            	<div class="user-info">
  					<span class="user-name"></span>
				</div>
                <h3>THÔNG BÁO</h3> 
            </div>
            <div class="card-body">
                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                <div class="dataTable-top">
                <div class="dataTable-dropdown">
                </div>
                	</div><div class="dataTable-container">
                	<div class="card">
                		<table class="table table-striped dataTable-table" id="table1">
                                <thead>
                                    <tr>
                                    	<th data-sortable="" style="width: 11.7819%;">
                                    		Mã thông báo</th>
                                    	<th data-sortable="" style="width: 41.8537%;">
                                    		Tiêu đề</th>
                                    	<th data-sortable="" style="width: 18.851%;">
                                    		Người gửi</th>
                                    	<th data-sortable="" style="width: 16.3824%;">
                                    		Ngày gửi</th>
                                    	<th data-sortable="" style="width: 11.1086%;">
                                    	</th></tr>
                                </thead>
                                <tbody>
                                	<%for(ThongBao tb: ThongBaoDAO.getAllThongBaoSV(mssv)) {%>
                                		<tr>
                                			<td> <c:out value="<%=tb.getMaTB()%>"></c:out></td>
                                			<td> <c:out value="<%=tb.getTieuDe()%>"></c:out></td>
                                			<td> <c:out value="<%=tb.getNguoiGui()%>"></c:out></td>
                                			<td> <c:out value="<%=tb.getNgayGui()%>"></c:out></td>
                                			<td><a onclick="show('<%=tb.getNoiDung()%>')">Chi tiết</a> </td>
                                		</tr>
                                	<%}%>
                                </tbody>
                            </table>
                	</div>
                	
                	
                            </div>
                 </div>
           	</div>
            	<div class="card">
            		<h4>NỘI DUNG</h4>
            	<lable id = "noidung">
            		
            	</lable>
            	 </div>
             
        </div>
    </div>
    <script>
        function show(nd){
        	document.getElementById('noidung').innerHTML = nd;
        }
    </script>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/pages/dashboard.js"></script>
	<script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
</body>

</html>