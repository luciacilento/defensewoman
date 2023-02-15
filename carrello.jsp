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
<div class="maxi-containerCarrello">
	<h3 style="margin-top: 20px; text-align: center">Ecco il tuo carrello:</h3> 
	



	<c:choose>
		<c:when test="${empty carrello}">
			<p>Carrello vuoto!</p>
			<video width="50%" height="auto" autoplay muted>
			<source src="assets/img/video3.mp4" type="video/mp4">
		</video>
		<br>
		<button type="button" onclick="location.href='main?scelta=index';" title="Torna alla home" style="margin-top: 20px; text-align: center">Torna alla home</button>
		</c:when>
		<c:otherwise>
		<h4>Numero prodotti: <c:out value="${carrello.numProdotti}"></c:out></h4>
			<c:forEach items="${carrello.listaProdotti}" var="prodotto">
			<div class="containerCarrello">			
			<div class="cardCarrello">
				<img class="fotoProdottoCarrello"src="${prodotto.imgProdotto}">
				<div class="dettagliProdottoCarrello">
				<p><c:out value="${prodotto.nome}"></c:out></p>
				<p><c:out value="${prodotto.prezzo}"></c:out>€	</p>			
							<a href="rimuoviProdotto?idProdotto=${prodotto.idProdotto}">
							<img class="iconaCestino"src="assets/img/elimina.png"></a>
							</div>
							</div>
							</div>
			</c:forEach>			
			<h4>Totale: <c:out value="${carrello.getTotaleForm()}"></c:out> €</h4>
			<div class="fineCarrello">
			
	<a href="vediProdotti"><button type="submit" value="Invia" style="margin-top: 20px; text-align: center">Torna agli acquisti</button> </a>
	<a href="svuotaCarrello"><button type="submit"value="Invia" style="margin-top: 20px; text-align: center">SvuotaCarrello</button></a>
	 <a href="datiOrdine"><button type="submit" value="Invia"style="margin-top: 20px; text-align: center">Procedi</button></a> 
			
			</div>
			
		</c:otherwise>
	</c:choose>
	


	<hr>

			
	

</div>
	<script src="index.js"></script>

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>