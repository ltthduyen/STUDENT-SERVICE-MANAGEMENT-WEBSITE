<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/vendors/toastify/toastify.css">
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container py-5 h-100">
		<div class="row d-flex justify-content-center  h-100">
			<div class="card align-items-center" style="border-radius: 1rem; height: 450px; width: 900px;">
			<div class="col-md-9 col-lg-6 col-xl-5">
			        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
			          class="img-fluid" alt="Sample image" style="margin-top:60px;margin-left: -250px;">
			      </div>
				<div class="align-items-center">
					
					<form action="taikhoan?action=forgotpass" method="post" style="margin-left: 420px;margin-top:-250px;">
				<c:if test="${status == 'failed!'}"> 
								<script src="assets/vendors/toastify/toastify.js"></script>
								    <script>
								            Toastify({
								                text: "Tài khoản hoặc mật khẩu không đúng!",
								                duration: 3000,
								                close: true,
								                gravity: "top",
								                position: "center",
								                backgroundColor: "#dc3545",
								            }).showToast();
								    </script>
								  ${status = ''}
					</c:if> 	
					<h2 class="fw-bold mb-2 text-uppercase text-center fontstytle" style="margin:auto;">Quên mật khẩu</h2>
						<div class="input-group mb-3" style="width: 410px; margin-top: 10px;">
							<span class="input-group-text" id="basic-addon1" style="margin-top: 30px;" > 
							<i
								class="bi bi-person-circle"></i>
							</span> 
							<input type="text" class="form-control"
								id="validationCustomUsername" placeholder="Nhập tên tài khoản"
								aria-describedby="inputGroupPrepend" required name="taikhoan"
								style="width: 410px;margin-left: 40px;margin-top:-37.5px;">
						</div>
						<!-- Mat khau input -->
						<div class="input-group mb-3" style="width: 410px;">
							<span class="input-group-text" id="basic-addon2"> <i
								class="bi bi-envelope-at-fill"></i>
							</span> <input type="text" class="form-control"
								id="validationCustomPassword" placeholder="Nhập email"
								aria-describedby="basic-addon2" required name="email"
								style="width: 410px;margin-left: 40px;margin-top:-37.5px;">
						</div>
						<!-- Button Đăng nhập -->
						<div class="button">
							<button type="submit"  class="btn btn-block mb-4 "style="width: 410px;margin-top: 20px;background-color:#25396f; color: #fff;"
								>Gửi</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>