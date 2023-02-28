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

</head>
<body>



<!-- Header -->
	<%@include file="/menu.jsp"%>
	<!-- Close Header -->

	<!-- Start Banner Hero -->
	<%@include file="/bannerAb.jsp" %>
	<!-- End Banner Hero -->
	
	 
    <!-- Close Header -->

   <!-- Modal -->
    <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="w-100 pt-1 mb-5 text-right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="get" class="modal-content modal-body border-0 p-0">
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="inputModalSearch" name="q" placeholder="Search ...">
                    <button type="submit" class="input-group-text bg-success text-light">
                        <i class="fa fa-fw fa-search text-white"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>


<div class='superContainerAbout'>
    <!-- Close Banner -->

    <!-- End Section -->

     <div class="image-containerNostro">
        <span style="--i: 1">
        <img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
    
        <span style="--i: 2">
        <img src="assets/img/girls/amalia.jpeg" alt="FOTO"></span>
    
        <span style="--i: 3">
        <img src="assets/img/girls/chiara.jpeg" alt="FOTO"></span>
    
        <span style="--i: 4">
 		<img src="assets/img/girls/dalila.jpg" alt="FOTO"></span>
 		
        <span style="--i: 5">
 		<img src="assets/img/girls/federicaS.jpg" alt="FOTO"></span>
 		
        <span style="--i: 6">
 		<img src="assets/img/girls/lucia.jpg" alt="FOTO"></span>
 		
        <span style="--i: 7">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
 		
        <span style="--i: 8">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>  
 		
 		<span style="--i: 9">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
 		
 		<span style="--i: 10">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
 		
 		<span style="--i: 11">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
 		
 		<span style="--i: 12">
 		<img src="assets/img/girls/adele.jpeg" alt="FOTO"></span>
 		  
 		</div>

    <div class="btn-containerNostro">
        <button class="btn" id="prev">Prev</button>
        <button class="btn" id="next">Next</button>
        
    </div>
    
</div>
    
    <!--End Brands-->
	
	<%@include file="footer.jsp" %>
<script src="assets/js/index.js"></script>
<script src="assets/js/jquery-1.11.0.min.js"></script>
<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/templatemo.js"></script>
<script src="assets/js/custom.js"></script>

</body>
</html>
