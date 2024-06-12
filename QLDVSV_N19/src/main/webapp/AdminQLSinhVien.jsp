<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.NganhDAO" %>
<%@ page import="DAO.SinhVienDAO" %>
<%@ page import="Models.SinhVien" %>
<%@ page import="Models.Nganh" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>QLDVSV</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/vendors/iconly/bold.css">
<link rel="stylesheet"
	href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="assets/css/app.css">
<link rel="shortcut icon" href="assets/images/favicon.svg"
	type="image/x-icon">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
                                <span>Trang chủ</span>
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
				<a href="#" class="burger-btn d-block d-xl-none"> <i
					class="bi bi-justify fs-3"></i>
				</a>
			</header>
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>Danh sách sinh viên</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb"
								class="breadcrumb-header float-start float-lg-end">	
							</nav>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="card">
						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>Mã số sinh viên</th>
										<th>Họ và tên</th>
										<th>Ngành</th>
										<th>Khóa học</th>
										<th>Chi tiết</th>
									</tr>
								</thead>
								<tbody>
									<%for(SinhVien sv: SinhVienDAO.getLstSinhVienVien()){%>
                                     	<tr>
												<td><c:out value="<%=sv.getMSSV()%>" /></td>
												<td><c:out value="<%=sv.getHo() %>" /> <c:out
														   value="<%=sv.getTenDem() %>" /> <c:out
														   value="<%=sv.getTen() %>" /></td>
												<% String nganh =  sv.getMaNganh();%>
												<td><c:out value="<%=NganhDAO.tenNganh(nganh)%>" /></td>
												<td><c:out value="<%=sv.getKhoaHoc()%>" /></td>
												<td>
													<a href="AdminChiTietSinhVien.jsp?mssv=<c:out value='<%=sv.getMSSV() %>' />">Chi tiết</a> 
												</td>
											</tr>
                                     <%} %>
								</tbody>
							</table>
							
							<!-- Button trigger modal -->
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
								  Thêm sinh viên
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
								  <div class="modal-dialog modal-dialog-centered" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLongTitle">Thêm sinh viên</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								        	<form action="SinhVien?action=them&id=${taikhoan}&email=${email}" method="post" accept-charset="UTF-8" >
										  <div class="form-row">
										    <div class="form-group col-md-4">
										      <label for="inputEmail4">Họ</label>
										      <input style="width:120px" type="text" name ="ho" class="form-control" id="inputEmail4" placeholder="Nhập họ">
										    </div>
										    <div class="form-group col-md-4">
										      <label for="inputPassword4">Tên đệm</label>
										      <input style="width:150px" type="text" name="tendem" class="form-control" id="inputPassword4" placeholder="Nhập tên đệm">
										    </div>
										    <div class="form-group col-md-4">
										      <label for="inputPassword4">Tên </label>
										      <input style="width:120px" type="text" name="ten" class="form-control" id="inputPassword4" placeholder="Nhập tên">
										    </div>
										  </div>
										  <div class="form-row">
										    <div class="form-group col-md-4">
										      <label for="inputEmail4">Mã số sinh viên</label>
										      <input style="width:150px" type="text" class="form-control" name="mssv" id="inputEmail4" placeholder="mã số sinh viên">
										    </div>
										    <div class="form-group col-md-4">
										      <label for="inputPassword4">Mật khẩu</label>
										      <input style="width:120px" type="password" name="mk" class="form-control" id="inputPassword4" >
										    </div>
										  </div>
										  <div class="form-row">
										    <div class="form-group col-md-4">
										      <label for="inputEmail4">Email</label>
										      <input style="width:200px" type="email"  name="email" class="form-control" id="inputEmail4" placeholder="..@gmail.com">
										    </div>
										    
										    <div class="form-group col-md-3" style="margin-left:70px; margin-top:40px;">
											<input class="form-check-input" type="radio" name="gt"
													id="option2" value="1" checked> <label class="form-check-label"
													for="option2">Nam</label>
											</div>
											<div class="form-group col-md-3" style="margin-top:40px;">
												<input class="form-check-input" type="radio" name="gt"
													id="option1" value="0"> <label class="form-check-label"
													for="option1">Nữ</label>
											</div>
										  </div>
										  
										  <div class="form-row">
										    <div class="form-group col-md-6" style="width:300px;">
										      <label for="inputState">Ngành</label>
										      <select id="inputState" class="form-control" name ="nganh" style="width:200px;">
										      <%  for(Nganh ng: NganhDAO.getAllNganh()){%>
										        <option value = "<%=ng.getMaNganh()%>"><%=ng.getTenNganh() %></option>
										      <%} %>
										      </select>
										    </div>
										    <div class="form-group col-md-4">
										      <label for="inputEmail4">CCCD</label>
										      <input style="width:200px;" name="cccd" type="text" class="form-control" id="inputEmail4" placeholder="CCCD">
										    </div>
										  </div>
										  <div class="form-row">
										  	
										  	<div id="datepicker" class="form-group col-md-6 date" data-date-format="dd-mm-yyyy" style="width:200px;" >
										  		<label >Ngày sinh</label>
						                      <input style="width:200px;" class="form-control" type="text" readonly  name="ngaysinh" />
						                      <span class="input-group-addon">
						                          <i class="glyphicon glyphicon-calendar"></i>
						                      </span>
						                  	</div>
						                  	<div class="form-group col-md-4">
										      <label for="inputEmail4">SDT</label>
										      <input style="width:200px;" name="sdt" type="text" class="form-control" id="inputEmail4" placeholder="SDT">
										    </div>
										  </div>
										  
										  <button type="submit" class="btn btn-primary">Thêm</button>
										  
										</form>
								      </div>
								    </div>
								  </div>
								</div>
							<div class="body" id="drop"  style="margin-left:60%;margin-top:-30px">
								   <form action="upload?id=${taikhoan}&email=${email}"
										method="post" enctype="multipart/form-data" accept-charset="UTF-8">
										<div>
											<input type="file" name="file" accept=".xls, .xlsx">
										</div>
										<button type="submit" class="btn btn-primary">Import</button>
										
									</form>
									<%	List<String> lst = (List<String>)request.getAttribute("lst");
											if(lst != null)
											for(String x: lst){
												%>
													<label> Lỗi không thể thêm sinh viên <%=x %></label>
												<% 
											}
										%> 
						  </div>
						</div>
						
					</div>

				</section>
			</div>
		</div>
	</div>
	<script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
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
	<script src="assets/vendors/apexcharts/apexcharts.js"></script>
	<script src="assets/js/pages/dashboard.js"></script>
	<script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
</body>

</html>