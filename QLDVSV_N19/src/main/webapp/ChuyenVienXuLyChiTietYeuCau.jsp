<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="DAO.GXNDAO" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QLDVSV</title>
	<link rel="stylesheet" href="assets/vendors/toastify/toastify.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
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
            
	           <h4>Phản hồi yêu cầu </h4>
	           <%String status = (String)request.getParameter("status");
	           	String button = "0"; %>
	           <%if ( status != null && status.equals("true!")){
	        	   	status = "";
	        	   	button = "1";
	        	   	%>
	           		<script src="assets/vendors/toastify/toastify.js"></script>
							    <script>
							            Toastify({
							                text: "Xử lý thành công!",
							                duration: 3000,
							                close: true,
							                gravity: "top",
							                position: "center",
							                backgroundColor: "#198754",
							            }).showToast();
							    </script>
	           <%}%>
                            <div class="card">
                            	<a class="row-0" href="yeucau?action=ListYC&id=${taikhoan}&email=${email}" class='sidebar-link' style="margin-left: 90%; ">
		                                <i class="bi bi-arrow-left"></i>
		                                <span>Quay về</span>
		                            </a>
		                       <div style="margin-top: 30px">    
                            	<form action="PhanHoi?action=xuly&id=${taikhoan}&email=${email}" method="post" >
                            	
                            		<label class="col-sm-2 control-label" style ="margin: 0px 250px;">Mã yêu cầu:</label>
                                 <div class="col-sm-8 ">
                                     <input type="text" id="tieude" class="form-control" style="margin: -28px 400px;"
                                      value="<c:out value='${yeucau.getMaYC()}' />" name = "myc"></div>
                                 
                             	<div class="col-sm-2 control-label" style ="margin: 60px 250px;">
                                     <label>MSSV : </label>
                                 </div>
                                 <div class="col-sm-8" style="margin: -87px 400px;">
                                     <input type="text" id="tieude" class="form-control"
                                      value="<c:out value='${yeucau.getMSSV()}' />" name = "mssv">
                                 </div>
		
                                 <div class="col-sm-2" style ="margin: 120px 250px;">
                                     <label>Ngày gửi : </label>
                                 </div>
                                 
                                 <div class="col-sm-4" style ="margin: -150px 400px;">
                                     <div id="datepicker1" class="input-group date" data-date-format="dd-mm-yyyy">
                                             <input class="form-control" type="text" value="<c:out value='${yeucau.getNgayGui()}'/>" readonly />
                                             <span class="input-group-addon">
                                                 <i class="glyphicon glyphicon-calendar"></i>
                                             </span>
                                         </div>
                                 </div>
                                 
                                 <div class="col-sm-2" style ="margin: 185px 250px;">
                                     <label>Ngày phản hồi: </label>
                                 </div>
                                 <div class="col-sm-4" style ="margin: -210px 400px;">
                                     <div id="datepicker" class="input-group date" data-date-format="dd-mm-yyyy">
                                             <input class="form-control" type="text" readonly name="nph" />
                                             <span class="input-group-addon">
                                                 <i class="glyphicon glyphicon-calendar"></i>
                                             </span>
                                         </div>
                                 </div>
                                 
                                 <div class="col-sm-2" style ="margin: 230px 250px;">
                                     <label>Ngày nhận( nếu duyệt): </label>
                                 </div>
                                 
                                 <div class="col-sm-4" style ="margin: -210px 400px;">
                                     <div id="datepicker2" class="input-group date" data-date-format="dd-mm-yyyy">
                                             <input class="form-control" type="text" readonly name="nnhan" />
                                             <span class="input-group-addon">
                                                 <i class="glyphicon glyphicon-calendar"></i>
                                             </span>
                                         </div>
                                 </div>
                                 
                                 <div class="col-sm-2" style ="margin: 240px 250px;">
                                     <label>Loại giấy: </label>
                                 </div>
                                 <div class="col-sm-8" style ="margin: -270px 400px;">
                                 	<%String mgxn = (String)request.getAttribute("magxn"); %>
                                     <input type="text" id="tieude" class="form-control" name="tieude"
                                     	 value="<%= GXNDAO.getNameGXN(mgxn)%>">
                                 </div>
                                  
                                 <div class="col-sm-2" style="margin-left: 70%;">
                                 	<div>
                                 		<input class="form-check-input" type="radio" name="trangthai"
										id="option2" value="1" checked> <label>Duyệt</label>
                                 	</div>
									
									<div>
										<input class="form-check-input" type="radio" name="trangthai"
										id="option1" value="2"> <label>Không duyệt</label>
									</div>
									<div>
										<label>Lý do không duyệt:</label>
										<textarea id="tutorial" name="nd" rows="5" cols="40" ></textarea>
									</div>
								</div> 
                                <div class="col-sm-8">
                                <%if (button.equals("0")) {
                                	%>
                                       <button type="submit" class="btn btn-primary me-1 mb-1 "style="margin-left: 550px; margin-top: 70px;" >Gửi</button>
                                 <%} %>
                                 </div>
                                          <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>   
                            	</form>  
                            	<div>     
                    </div>	         
            </div>
        </div>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                    </script>
    <script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>

    <script src="assets/js/main.js"></script>
   
    <script>
        $(function () {
            $("#datepicker").datepicker({ 
                autoclose: true, 
                todayHighlight: true,
                todayBtn : "linked",
            }).datepicker('update', new Date());
            
            $("#datepicker2").datepicker({ 
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