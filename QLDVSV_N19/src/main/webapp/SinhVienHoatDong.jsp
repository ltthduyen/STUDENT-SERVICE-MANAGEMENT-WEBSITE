<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="DAO.SinhVienDAO" %>
<%@page import="DAO.HoatDongDAO" %>
<%@page import="DAO.ThamGiaDAO" %>
<%@page import="DAO.ToChucDAO" %>
<%@page import="Models.HoatDong" %>
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

                        <li class="sidebar-item active ">
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
                            <h3>THAM GIA CTXH</h3>
                        </div>
                    </div>
                </div>
				<div class="col-md-6 mb-4" style="margin-left: 500px">
					
                    <h6>Tổng số điểm tham gia của các hoạt động</h6>
                    <div class="short-form">
               			<input value="<%=SinhVienDAO.diemCTXH(mssv)%>" style="width:50px; margin-left: 250px" type="text" class="form-control" id="basicInput" placeholder="">
           			</div>
                </div>
				<div class="card">
					<div class="card-body">
					<section>
                    <div class="table-responsive">
                        <table class="table table-striped mb-0">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã HĐ</th>
                                    <th>Tên HĐ</th>
                                    <th>Thời gian</th>
                                    <th>Điểm</th>
                                    <th>Đơn vị tổ chức</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%int stt = 0; %>
                            <%for(HoatDong hd : HoatDongDAO.getlstHoatDong(mssv)){ %>
                            	<%stt= stt+1; %>
                                <tr>
                                    <td class="text-bold-500"><%=stt %></td>
                                    <td><%=hd.getMaHD() %></td>
                                    <td class="text-bold-500"><%=hd.getTenHD()%></td>
                                    <td><%=hd.getNgayBatDau() %> /<%=hd.getNgayKetThuc() %></td>
                                    <td><%=hd.getDiem() %></td>
									<td class="text-bold-500"><%=ToChucDAO.tenTC(hd.getToChuc())%></td>
                                </tr>
                             <%}%>
                            </tbody>
                        </table>
                    </div>
                </section>
                <hr>
                <div class="card">
                	<h6>Đăng ký tham gia hoạt động CTXH</h6>

                    <div class="table-responsive">
                        <table class="table table-striped mb-0">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Ma HĐ</th>
                                    <th>Tên HĐ</th>
                                    <th>Thời gian</th>
                                    <th>Diem</th>
                                    <th>Đơn vị tổ chức</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%stt = 0; %>
	                            <%for(HoatDong hd : HoatDongDAO.getlstSVHoatDongChuaThamGia(mssv)){ %>
	                            	<%stt= stt+1; %>
	                                <tr>
	                                    <td class="text-bold-500"><%=stt %></td>
	                                    <td><%=hd.getMaHD() %></td>
	                                    <td class="text-bold-500"><%=hd.getTenHD()%></td>
	                                    <td><%=hd.getNgayBatDau() %> /<%=hd.getNgayKetThuc() %></td>
	                                    <td><%=hd.getDiem() %></td>
										<td class="text-bold-500"><%=ToChucDAO.tenTC(hd.getToChuc())%></td>
										
										<%if (ThamGiaDAO.getTrangThai(hd.getMaHD(), mssv) == 0 ) {  %>
											<td> <a href="ThamGia?action=svhuythamgia&mhd=<%=hd.getMaHD()%>&mssv=<%=mssv%>">Hủy đăng ký</a></td>
										<%}else {%>
											<% if (HoatDongDAO.isSoLuongDu(hd.getMaHD())== false){ %>
												<td> <a href="ThamGia?action=svthamgia&mhd=<%=hd.getMaHD()%>&mssv=<%=mssv%>">Đăng ký</a></td>
											<%} else {%>
												<td> Đã đủ số lượng</td>
											<%} %>
										<%} %>
	                             	<%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
                	
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