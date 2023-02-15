<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Defense Woman</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/LogoProgettoNoBg.png">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;700&family=Pangolin&family=Playfair+Display&family=Quicksand:wght@300;600&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<!--
    
TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

-->
</head>

<body>



	<!-- Header -->
	<%@include file="/menu.jsp"%>
	<!-- Close Header -->

	<!-- Start Banner Hero -->
	<%@include file="/banner.jsp" %>
	<!-- End Banner Hero -->


	<!-- Start Categories of The Month -->
	<section class="container py-5">
		<div class=" text-center pt-3">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">I NOSTRI PRODOTTI</h1>
				<p>Solo prodotti naturali, 100% veg, ecosostenibili e per una
					buona causa!</p>
			</div>
		</div>
		<div class="miaRigaProdotti">
			<div class="col-12 col-md-4 p-5 mt-3">
				<a href="#"><img src="assets/img/sap1.webp"
					class="rounded-circle img-fluid border"></a>
				<h5 class="text-center mt-3 mb-3">Per te</h5>
				<p class="text-center">
					<a class="btn btn-success">Vai ai prodotti</a>
				</p>
			</div>
			<div class="col-12 col-md-4 p-5 mt-3">
				<a href="#"><img src="assets/img/pianta2.webp"
					class="rounded-circle img-fluid border"></a>
				<h2 class="h5 text-center mt-3 mb-3">Per la casa</h2>
				<p class="text-center">
					<a class="btn btn-success">Vai ai prodotti</a>
				</p>
			</div>
			<div class="col-12 col-md-4 p-5 mt-3">
				<a href="#"><img src="assets/img/cucina3.avif"
					class="rounded-circle img-fluid border"></a>
				<h2 class="h5 text-center mt-3 mb-3">Per la cucina</h2>
				<p class="text-center">
					<a class="btn btn-success">Vai ai prodotti</a>
				</p>
			</div>

			<div class="col-12 col-md-4 p-5 mt-3">
				<a href="#"><img src="assets/img/kit1.jpg"
					class="rounded-circle img-fluid border"></a>
				<h2 class="h5 text-center mt-3 mb-3">Kit</h2>
				<p class="text-center">
					<a class="btn btn-success">Vai ai prodotti</a>
				</p>
			</div>
		</div>
	</section>
	<!-- End Categories of The Month -->


	<!-- Start Featured Product -->
	<section class="bg-light">
		<div class="container py-5"></div>
	
	</section>
	<!-- End Featured Product -->


	<!-- Start Footer -->
	<%@include file="footer.jsp" %>
	<!-- End Footer -->

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>	
	<!-- End Script -->
	
</body>
</html>