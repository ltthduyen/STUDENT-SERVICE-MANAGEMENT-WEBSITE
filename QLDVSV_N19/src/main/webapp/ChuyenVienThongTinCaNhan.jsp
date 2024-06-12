<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QLDVSV</title>

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
                            <a href="ChuyenVienThongTinCaNhan.jsp"><img src="assets/images/logo/logo.png" alt="Logo" srcset=""></a>
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
						<li class="sidebar-item ">
                            <a href="ChuyenVienTrangChu.jsp?id=${taikhoan}&email=${email}&loai=${loai}" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>Trang chủ</span>
                            </a>
                        </li>
                        <li class="sidebar-item active ">
                            <a href="ChuyenVien?action=ThongTinChuyenVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-person-square">
                            		</i>
                                <span>Thông tin cá nhân
                                </span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="yeucau?action=ListYC&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>Xử lý yêu cầu</span>
                            </a>
                        </li>
                        <li class="sidebar-item  ">
                            <a href="ThongBao?action=listTBCV&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-bell"></i>
                                <span>Gửi thông báo</span>
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
                <h4>Thông Tin CTSV</h4>
            </div>
            <div class="page-content">
                
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <!-- Table with outer spacing -->
                                            <div class="table-responsive1">
                                                <table class="table table-lg">
                        
                                                    <tbody>
                                                        <tr>
                                                            <td class="text-bold-500">MSCTSV : </td>
                                                            <td><c:out value="${ChuyenVien.getMSCTSV()}" /></td>                                                        
    
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">Họ và tên : </td>
                                                            <td>
                                                            <c:out value="${ChuyenVien.getHo()}" />
                                                            <c:out value="${ChuyenVien.getTenDem()}" />
                                                            <c:out value="${ChuyenVien.getTen()}" /></td>
                                                            
    
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">Ngày sinh : </td>
                                                            <td><c:out value="${ChuyenVien.getNamSinh()}" /></td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">Giới tính : </td>
                                                            <td>
                                                               <c:if test="${ChuyenVien.getGioiTinh()==1}">
										                             Nam
										                        </c:if> 
                                                            	<c:if test="${ChuyenVien.getGioiTinh()==0}">
										                             Nữ
										                        </c:if> 
                                                            </td>
                                                           
    
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">CCCD : </td>
                                                            <td><c:out value="${ChuyenVien.getCCCD()}" /></td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">Điện thoại : </td>
                                                            <td><c:out value="${ChuyenVien.getSdt()}" /></td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td class="text-bold-500">Email : </td>
                                                            <td><c:out value="${email}" /></td>
                                                            
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
            
                    </div>
                        <div class="avatar avatar-xs me-2">
                            <img src="assets/images/faces/2.jpg" alt="" srcset="">
                        </div>
                        <div class="buttons1">
                            <a href="DoiMatKhau.jsp?taikhoan=${taikhoan}&email=${email}&loai=${loai}" class="btn btn-primary">Đổi mật khẩu</a>
                            <a href="CapNhatThongTin.jsp?taikhoan=${taikhoan}&email=${email}&loai=${loai}" class="btn btn-primary">Cập nhật thông tin</a>
                        </div>
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