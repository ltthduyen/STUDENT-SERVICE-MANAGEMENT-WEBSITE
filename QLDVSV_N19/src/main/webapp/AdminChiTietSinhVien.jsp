<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="DAO.SinhVienDAO" %>
<%@ page import="DAO.NganhDAO" %>
<%@ page import="Models.SinhVien" %>

<!DOCTYPE html>
<html lang="VN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QLDVSV</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/vendors/iconly/bold.css">
<link rel="stylesheet"
	href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="assets/css/app.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

<link rel="shortcut icon" href="assets/images/favicon.svg"
	type="image/x-icon">
</head>
<body>
	<%
		String taikhoan = (String) session.getAttribute("taikhoan");
		if (taikhoan == null ){
			response.sendRedirect("DangNhap.jsp");
		}
	%>
	
    <div id="app">
    	<div style="margin-left: 1110px;" class="avatar avatar-lg me-2">
    	<img src="assets/images/faces/3.jpg" alt="Logo" srcset="">
  	</div>
    <a style ="margin-left: 150px" class="logout-link" onclick="myFunction()"> Đăng xuất</a>
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
                            <a href="AdminDashboard.jsp"><img src="assets/images/logo/logo.png"  alt="Logo"></a>
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-item ">
                            <a href="AdminDashboard.jsp?id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="sidebar-item  ">
                            <a href="quantrivien?action=ThongTinQuanTriVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>
                        
                        <li class="sidebar-item active">
                            <a href="SinhVien?action=listSinhVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Quản lý Sinh viên</span>
                                
                            </a>
                        </li>
                        <li class="sidebar-item ">
                            <a href="ChuyenVien?action=listChuyenVien&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Quản lý Chuyên viên</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="quantrivien?action=LstQTV&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Quản lý Quản trị viên</span>
                            </a>
                        </li>
                        <li class="sidebar-item ">
                            <a href="GXN?action=listGXN&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Quản lý Giấy xác nhận</span>
                            </a>
                        </li>
						<li class="sidebar-item  ">
                            <a href="HoatDong?action=list&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Quản lý Hoạt động</span>
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
            	<%
            		String mssv = request.getParameter("mssv");
            		SinhVien sv = SinhVienDAO.getSinhVien(mssv);
            	%>
                <h4>Thông tin sinh viên: <%= mssv %> </h4>
            </div>
            
            <div class="page-content">
            	<div class="card">
            		<form action ="SinhVien?action=chinhsua&id=${taikhoan}" method="post" accept-charset="UTF-8">
				  <!-- 2 column grid layout with text inputs for the first and last names -->
				  <div class="row mb-4" style="margin-left:88px;margin-top:50px ">
				    <div class="col" style="margin-right:-200px;">
				      <div data-mdb-input-init class="form-outline">
				      	<label class="form-label" for="form6Example1">Họ</label>
				        <input type="text" id="form6Example1" class="form-control" value = "<%=sv.getHo()%>" name="ho" />
				      </div>
				    </div>
				    <div class="col" style="margin-right:-200px;">
				      <div data-mdb-input-init class="form-outline">
				      	<label class="form-label" for="form6Example2">Tên đệm</label>
				        <input type="text" id="form6Example2" class="form-control" value = "<%=sv.getTenDem()%>" name="tendem" />
				        
				      </div>
				    </div>
				     <div class="col">
				      <div data-mdb-input-init class="form-outline">
				      	<label class="form-label" for="form6Example2">Tên </label>
				        <input type="text" id="form6Example3" class="form-control" value = "<%=sv.getTen()%>" name="ten" />
				        
				      </div>
				    </div>
				  </div>
				 <input type="hidden" id="form6Example5" class="form-control" value="<%=sv.getMSSV()%>" name="mssv"/>
				  <!-- Text input -->
				  <div data-mdb-input-init class="form-outline mb-4" style="margin-left:100px;">
				  	<label class="form-label" for="form6Example3">Giới tính</label>
				  	<%if (sv.getGioiTinh() == 1) {%>
				  		<input type="text" id="form6Example3" class="form-control" value = "Nam" name="gt"/>
				  	<%} else{ %>
				  		<input type="text" id="form6Example3" class="form-control" value = "Nữ" name="gt"/>
				  	<%} %>
				    
				  </div>
					<input class="form-control" type="hidden" readonly value = "<%=sv.getDiemCTXH()%>"  name="diemctxh" />
				  <!-- Text input -->
				   <div id="datepicker" class="form-outline mb-4 date" data-date-format="dd-mm-yyyy" style="margin-left:100px;">
                      <input class="form-control" type="text" readonly value = "<%=sv.getNamSinh()%>"  name="ngaysinh" />
                      <span class="input-group-addon">
                          <i class="glyphicon glyphicon-calendar"></i>
                      </span>
                  </div>
                  <input type="hidden" id="form6Example5" class="form-control" value="<%=sv.getKhoaHoc()%>" name="khoahoc"/>
				  <!-- Email input -->
				  <div data-mdb-input-init class="form-outline mb-4" style="margin-left:100px;">
				  	<label class="form-label" for="form6Example5">Ngành</label>
				    <input type="text" id="form6Example5" class="form-control" value="<%=NganhDAO.tenNganh(sv.getMaNganh())%>" />
				    <input type="hidden" id="form6Example5" class="form-control" value="<%=sv.getMaNganh()%>" name="nganh"/>
				  </div>
				  <!-- Number input -->
				  <div data-mdb-input-init class="form-outline mb-4" style="margin-left:100px;">
				  	<label class="form-label" for="form6Example6">CCCD</label>
				    <input type="text" id="form6Example6" class="form-control" value = "<%=sv.getCCCD()%>"  name="cccd"  />
				  </div>
				  <div data-mdb-input-init class="form-outline mb-4"style="margin-left:100px;">
				  	<label class="form-label" for="form6Example6">SDT</label>
				    <input type="text" id="form6Example6" class="form-control" value = "<%=sv.getSdt()%>"  name="sdt" />
				  </div>
				  
				  <!-- Submit button -->
				  <button style=" margin-left:400px;width:400px;" data-mdb-ripple-init type="submit" class="btn btn-primary btn-block mb-3">Chỉnh sửa</button>
				</form>
					<a   href = "SinhVien?action=listSinhVien&id=${taikhoan}&email=${email}" class="btn btn-light-secondary me-1 mb-1">
	                      <span> Quay về</span>
	                    </a>
					
            	</div>
            </div>
        </div>
    </div>
  <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                    </script>
  <script>
        $(function () {
            $("#datepicker").datepicker({ 
                autoclose: true, 
                todayHighlight: true,
                todayBtn : "linked",
            }).datepicker('update', new Date());
        });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
</body>

</html>