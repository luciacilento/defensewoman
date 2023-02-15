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

					<!-- col end -->
					<div class="containerProd">
						<div class="cardProd">
						
						<img src="${imgProdotto}"class="immagineProd" alt="Card image cap" id="product-detail">
							<div class="cardProd-body">
								<h1 class="h1-card">${prodotto.nome}</h1>
								<p class="p-card" style="font-size: 30px">${prodotto.prezzo}€</p>


								<h6>Descrizione:</h6>
								<p>${prodotto.descrizione}</p>

									<div class="rigaBottoni">
						
											<a href="insertCarrello?idProdotto=${prodotto.idProdotto}"> <button type="submit" class="btnProd"
												name="submit" value="addtocard" title="Add To Cart">Add To Cart</button></a>
												<a href="vediProdotti"><button type="submit" class="btnProd" title="Torna Indietro">Torna Indietro</button></a> 
							
									</div>
								
							</div>
						</div>
					</div>



	<%@include file="footer.jsp"%>


</body>
</html>