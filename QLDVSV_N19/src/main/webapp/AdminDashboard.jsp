<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import ="DAO.TongDAO" %>
<%@page import ="Models.Tong" %>
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
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/vendors/iconly/bold.css">
<link rel="stylesheet"
	href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="assets/css/app.css">
<link rel="shortcut icon" href="assets/images/favicon.svg"
	type="image/x-icon">
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
                        <li class="sidebar-item active">
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
                        
                        <li class="sidebar-item  ">
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
                <h4>Dashboard</h4>
            </div>
            <div class="page-content">
            	<section class="section">
                            <div class="card">
                                <div class="card-body">
                                <div class="col-md-6 mb-4">
                                                <h6>Chọn năm biểu đồ</h6>
                                                <fieldset class="short-form">
                                                    <select class="form-select" id="basicSelect" onclick="getOption()">
                                                    </select>
                                                    <h6> Biểu đồ tổng số yêu cầu năm 
												        <span class="output"></span>
												    </h6>
                                                </fieldset>
                                            </div>
									<canvas id="bar"></canvas>
                                    
                                </div>
                                
                            </div>
                </section>
            </div>
        </div>
    </div>
    <script src="assets/vendors/chartjs/Chart.min.js"></script>
    <script src="assets/vendors/chartjs/Chart.min.css"></script>
	<%
		List<Tong> lst = TongDAO.getTongYC();
		
		List<String> Thang = new ArrayList<>();
		List<String> Nam = new ArrayList<>();
		List<Integer> sl = new ArrayList<>();
		
		 for(Tong t:lst){  
		      Thang.add(t.getThang());
		      Nam.add(t.getNam());
		      sl.add(t.getSoYC());
		   }  
	%>
    <Script>
    var thang = <%=Thang%>;
    var nam = <%=Nam%>;
    var sl = <%=sl%>;
    
    var select = document.getElementById("basicSelect");
	var x = nam.slice(1, -1).split(/[.,!,?,;,...]/);
	var findmax = x.map(function (item) {
		return parseInt(item, 10);
	});
	var max = Math.max(...findmax);
    for (var year = 2022; year <= 2024; year++) {
        var option = document.createElement("option");
        option.text = year;
        option.value = year;
        select.appendChild(option);
    }
    
    function getOption() {
        selectElement = document.querySelector('#basicSelect');
        output = selectElement.options[selectElement.selectedIndex].value;
        document.querySelector('.output').textContent = output;
        
        var canvases = document.getElementsByTagName('canvas');
        for (var i = 0; i < canvases.length; i++) {
            var canvas = canvases[i];
            canvas.parentNode.removeChild(canvas);
        }

        var newCanvas = document.createElement('canvas');
        newCanvas.id = 'bar'; 
        newCanvas.width = 1061;
        newCanvas.height = 530;

        var cardBody = document.querySelector('.card-body');
        cardBody.appendChild(newCanvas);
        
     if (document.getElementById('basicSelect').value == "2022"){
    	  var chartColors = {
    	    	    red: 'rgb(255, 99, 132)',
    	    	    orange: 'rgb(255, 159, 64)',
    	    	    yellow: 'rgb(255, 205, 86)',
    	    	    green: 'rgb(75, 192, 192)',
    	    	    info: '#41B1F9',
    	    	    blue: '#3245D1',
    	    	    purple: 'rgb(153, 102, 255)',
    	    	    grey: '#EBEFF6'
    	    	};
    	  
    	    	var ctxBar = document.getElementById("bar").getContext("2d");
    	    	var bar = new Chart(ctxBar, {
    	    	    type: 'bar',
    	    	    data: {
    	    	        labels: thang.slice(1, 7).split(/[.,!,?,;,...]/),
    	    	        datasets: [{
    	    	            label: 'Số yêu cầu',
    	    	            backgroundColor: chartColors.blue,
    	    	            data: sl.slice(1, 5).split(/[.,!,?,;,...]/),
    	    	        }]
    	    	    },
    	    	    options: {
    	    	        responsive: true,
    	    	        barRoundness: 0,
    	    	        title: {
    	    	            display: true,
    	    	            text: "Tổng số yêu cầu trang web đã thực hiện"
    	    	        },
    	    	        legend: {
    	    	            display: false
    	    	        },
    	    	        scales: {
    	    	            yAxes: [{
    	    	                ticks: {
    	    	                    beginAtZero: true,
    	    	                    suggestedMax: 5,
    	    	                    padding: 10,
    	    	                },
    	    	                gridLines: {
    	    	                    drawBorder: true,
    	    	                }
    	    	            }],
    	    	            xAxes: [{
    	    	                gridLines: {
    	    	                    display: false,
    	    	                    drawBorder: false
    	    	                }
    	    	            }]
    	    	        }
    	    	    }
    	    	});
    	    	
    		}
   		else if (document.getElementById('basicSelect').value == "2023"){
   			var chartColors = {
   	        	    red: 'rgb(255, 99, 132)',
   	        	    orange: 'rgb(255, 159, 64)',
   	        	    yellow: 'rgb(255, 205, 86)',
   	        	    green: 'rgb(75, 192, 192)',
   	        	    info: '#41B1F9',
   	        	    blue: '#3245D1',
   	        	    purple: 'rgb(153, 102, 255)',
   	        	    grey: '#EBEFF6'
   	        	};
   	    	
   	        	var ctxBar = document.getElementById("bar").getContext("2d");
   	        	var bar = new Chart(ctxBar, {
   	        	    type: 'bar',
   	        	    data: {
   	        	        labels: thang.slice(8,-1).split(/[.,!,?,;,...]/),
   	        	        datasets: [{
   	        	            label: 'Số yêu cầu',
   	        	            backgroundColor: chartColors.blue,
   	        	            data: sl.slice(7,-1).split(/[.,!,?,;,...]/),
   	        	        }]
   	        	    },
   	        	    options: {
   	        	        responsive: true,
   	        	        barRoundness: 0,
   	        	        title: {
   	        	            display: true,
   	        	            text: "Tổng số yêu cầu trang web đã thực hiện"
   	        	        },
   	        	        legend: {
   	        	            display: false
   	        	        },
   	        	        scales: {
   	        	            yAxes: [{
   	        	                ticks: {
   	        	                    beginAtZero: true,
   	        	                    suggestedMax: 5,
   	        	                    padding: 10,
   	        	                },
   	        	                gridLines: {
   	        	                    drawBorder: true,
   	        	                }
   	        	            }],
   	        	            xAxes: [{
   	        	                gridLines: {
   	        	                    display: false,
   	        	                    drawBorder: false
   	        	                }
   	        	            }]
   	        	        }
   	        	    }
   	        	});
   		} 
    }
    </Script>
	<script src="assets/js/extensions/sweetalert2.js"></script>
    <script src="assets/vendors/sweetalert2/sweetalert2.all.min.js"></script>
</body>
</html>