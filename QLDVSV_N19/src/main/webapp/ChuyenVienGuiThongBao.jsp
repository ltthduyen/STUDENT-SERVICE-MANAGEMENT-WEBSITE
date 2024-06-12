<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.KhoaDAO" %>
<%@ page import="Models.Khoa" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QLDVSV</title>
    <link rel="stylesheet" href="assets/vendors/sweetalert2/sweetalert2.min.css">
    <link rel="stylesheet" href="assets/vendors/summernote/summernote-lite.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">
    <link rel="stylesheet" href="assets/vendors/toastify/toastify.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
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
    <script src="assets/vendors/toastify/toastify.js"></script>
            	<c:if test="${status == true}">
							    <script>
							            Toastify({
							                text: "Gửi thông báo thành công!",
							                duration: 3000,
							                close: true,
							                gravity: "top",
							                position: "center",
							                backgroundColor: "#198754",
							            }).showToast();
							    </script>
							${status = ''}
					</c:if> 
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
                        <li class="sidebar-item">
                            <a href="yeucau?action=ListYC&id=${taikhoan}&email=${email}" class='sidebar-link'>
                                <i class="bi bi-grid-1x2-fill"></i>
                                <span>Xử lý yêu cầu</span>
                            </a>
                        </li>
                        <li class="sidebar-item active ">
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
		<h4 style="margin-top: 30px;margin-left: 20px;">Gửi thông báo</h4>
            <div class="page-heading">
                <section class="section">
                <div class="col-12 mt-3 mb-1">
                    <div class="cardxl" style="width: auto;">
                        
                        
                    	<div class="card">
	                    	<form action="ThongBao?action=CVgui" method="post">
                            <div class="form-body1">
                                <div class="row-2">
                                        <div class="col-md-4 text-md-end" style="margin-top:40px">
                                            <label>Tiêu Đề: </label>
                                        </div>
                                        <div class="col-md-6 form-group">
                                            <input type="text" id="tieude" class="form-control" name="tieude" style="margin-left:370px; margin-top:-30px">
                                        </div>
                                        
                                        <input type="hidden" class="form-control" name="id" value="<%=request.getParameter("id")%>">
                                        
                                    	<div class="col-md-4 text-md-end" style="margin-top:40px">
                                            <label>Khoa nhận: </label>
                                        </div>
                                        
                                        <div class="col-md-8 mb-4"  style="margin-left:370px; margin-top:-30px">
                                            <fieldset class="form-group1">
                                                <select class="form-select" id="basicSelect" name ="khoa">
                                                	<%for(Khoa khoa: KhoaDAO.getAllKhoa()) {%>
                                                    	<option value="<%=khoa.getMaKhoa()%>"><%=khoa.getTenKhoa()%></option>
													<%}%>
                                                </select>
                                            </fieldset>
                                        </div>
                                    
                                        <div class="col-md-4 text-md-end" style="margin-top:40px">
                                            <label>Ngày Gửi: </label>
                                        </div>
                                        <div class="col-md-3" style="margin-left:370px; margin-top:-30px">
                                            <div id="datepicker" class="input-group date" data-date-format="dd-mm-yyyy">
                                                <input class="form-control" type="text" readonly name="ngui"/>
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-calendar"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-md-4 text-md-end" style="margin-top:40px">
                                            <label>Nội Dung: </label>
                                        </div>
                                        <textarea id="tutorial" name="nd" rows="5" cols="40" style="margin-left:370px; margin-top:-30px"></textarea>
                                        
                                    <div class="col-sm-8 d-flex justify-content-end" style=" margin-top: 25px;margin-left:-250px">
                                        <button type="Gui" class="btn btn-primary" >
                                            Gửi </button>
                                        
                                    </div>
                                </div>
                            </div>
                        </form>
                        	<a href="ThongBao?action=listTBCV&id=${taikhoan}&email=${email}" type="Huy" class="btn btn-light-secondary me-1 mb-1" style="margin-left: 550px; margin-top: -37px; width:100px"">Quay về
                            </a>
	                    </div>
                     </div>
                  </div>
                  </section>
	                    
                    </div>
                </div>

                </div>
    <script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                    </script>

    <script src="assets/vendors/jquery/jquery.min.js"></script>
    <script src="assets/vendors/summernote/summernote-lite.min.js"></script>
    <script>
        $('#summernote').summernote({
            tabsize: 2,
            height: 120,
        })
        $("#hint").summernote({
            height: 100,
            toolbar: false,
            placeholder: 'type with apple, orange, watermelon and lemon',
            hint: {
                words: ['apple', 'orange', 'watermelon', 'lemon'],
                match: /\b(\w{1,})$/,
                search: function (keyword, callback) {
                    callback($.grep(this.words, function (item) {
                        return item.indexOf(keyword) === 0;
                    }));
                }
            }
        });

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
     <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>

    <script src="assets/js/main.js"></script>


</body>

</html>