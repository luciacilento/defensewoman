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

	<%@include file="/menu.jsp"%>

	<!-- Start Content -->
	<div class="container py-5">
		<div class="contProdottiTot">

			<!-- APERTURA PANNELLO FILTRI -->
			<div class="col-lg-3">
				<h1 class="h2 pb-4">Prodotti</h1>
				<ul class="list-unstyled templatemo-accordion">
					<li class="pb-3">
						<div class="d-flex">

							<form action="vediProdotti" method="post">
								<div class="form-group">
									<label for="prezzo1">Prezzo minimo:</label> <input
										type="number" class="form-control" id="prezzo1" name="prezzo1"
										min="1" max="200">
								</div>
								<div class="form-group">
									<label for="prezzo2">Prezzo massimo:</label> <input
										type="number" class="form-control" id="prezzo2" name="prezzo2"
										min="1" max="200">
								</div>
								<div class="form-group">
									<label for="categoria">Categoria:</label> 
									
									
									<select	class="form-control" id="categoria" name="idCategoria">										
										<option value="">Tutte</option>
										<c:forEach var="c" items="${categorie}">
											<option value="${c.idCategoria}">${c.nomeCategoria}</option>											
										</c:forEach>										
									</select>
									
								</div>
								<button type="submit" class="btn btn-primary">Cerca</button>
							</form>

						</div> <br>
				</ul>
			</div>

			<!-- CHIUSURA PANNELLO FILTRI -->

			<div class="col-lg-9">
				<c:choose>
					<c:when test="${empty prodotti}">
						<p>Nessun prodotto trovato</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="prodotto" items="${prodotti}">
							<div class="row">
								<div class="card mb-4 product-wap"
									style="max-height: 395px; padding: 10px">
									<div class="card rounded-0">
										<img
											style="padding: 5px; border-radius: 5px; max-height: 290px; min-height: 290px"
											class="card-img rounded-0 img-fluid"
											src="${prodotto.imgProdotto}">
										<div
											class="card-img-overlay product-overlay d-flex align-items-center justify-content-center">
											<ul class="list-unstyled">
												<li><a class="btn btn-success text-white mt-2"
													href="dettaglioProdotto?idProdotto=${prodotto.idProdotto}"><i
														class="far fa-eye"></i></a></li>
												<li><a class="btn btn-success text-white mt-2"
													href="insertCarrello?idProdotto=${prodotto.idProdotto}"><i
														class="fas fa-cart-plus"></i></a></li>
											</ul>
										</div>
									</div>
									<div class="card-body">
										<a href="shop-single.html" class="h3 text-decoration-none"><c:out
												value="${prodotto.nome}"></c:out></a>
										<ul
											class="w-100 list-unstyled d-flex justify-content-between mb-0">

											<li class="pt-2"><span
												class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
												<span
												class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
												<span
												class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
												<span
												class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
												<span
												class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
											</li>
										</ul>

										<p class="text-center mb-0" style="font-size: 45px">
											<c:out value="${prodotto.prezzo}"></c:out>
											€
										</p>
									</div>
								</div>

							</div>

						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

	</div>
	<!-- End Content -->

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
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