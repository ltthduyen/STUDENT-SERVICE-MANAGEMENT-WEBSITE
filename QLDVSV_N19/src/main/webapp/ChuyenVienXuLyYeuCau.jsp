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
                        <li class="sidebar-item  ">
                            <a href="ChuyenVien?action=ThongTinChuyenVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-person-square">
                            		</i>
                                <span>Thông tin cá nhân
                                </span>
                            </a>
                        </li>
                        <li class="sidebar-item active">
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
            <section class="section">
                <div class="col-12 mt-3 mb-1">
                    <div class="cardxl" style="width: fit-content;">
                        
                        <h4 style="margin-top: 30px;margin-left: 20px;">Danh sách yêu cầu</h4>
                    
                        <div class="card-bodyxl" >
                            <table class="table table-striped" style="width:900px;">
                                <thead>
                                    <tr>
                                    	<th>STT</th>
                                        <th>MaYC</th>
                                        <th>MSSV</th>
                                        <th>MaGXN</th>
                                        <th>Soluong</th>
                                        <th style="width:50px;">Trạng thái</th>
                                        <th style="width:50px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<% int stt = 0; %>
                                	<c:forEach var="yeucau" items="${lstYeuCau}">		
		                                <tr>
		                                	<%stt=stt+1;%>
		                                	<td><%=stt%></td>
		                                    <td><c:out value="${yeucau.getMaYC()}" /></td>
		                                    <td><c:out value="${yeucau.getMSSV()}" /></td>
		                                    <td><c:out value="${yeucau.getMaGXN()}" /></td>
		                                     <td>
		                                        <c:out value="${yeucau.getSoLuong()}" />
		                                    </td>
		                                     <td>
	                                     		<c:if test="${yeucau.getTrangThai()==0}">
						                             <span class="badge bg-danger">Chưa xử lý</span>
						                        </c:if> 
                                                  	<c:if test="${yeucau.getTrangThai()==1 or yeucau.getTrangThai()== 2 }">
						                             <span class="badge bg-success">Đã xử lý</span>
						                        </c:if>
                                       		 </td>
                                       		 <c:if test="${yeucau.getTrangThai()==0}">
						                             <td><a href="yeucau?action=yeucau&myc=<c:out value='${yeucau.getMaYC()}'/>&id=${taikhoan}&email=${email}">Xử lý</a> </td>
						                      </c:if> 
                                       		 
		                                </tr>
		                            </c:forEach>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                
                </div>
            </section>
        </div>
        </div>
    </div>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>

    <script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
<script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>

</html>