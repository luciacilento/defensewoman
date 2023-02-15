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
<link rel="stylesheet" href="assets/css/templatemo2.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;700&family=Pangolin&family=Playfair+Display&family=Quicksand:wght@300;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">

</head>
<body>

	<c:choose>
	
		<c:when test="${empty utente}">
			<nav class="navbar navbar-expand-lg navbar-light shadow">
				<div class="container d-flex justify-content-between align-items-center">

					<a class="navbar-brand text-success align-self-center" href="index.jsp"> 
					<img alt="LOGO" src="assets/img/LogoProgetto.png" class="myLogo"> </a>

					<button class="navbar-toggler border-0" type="button"
						data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class=" navbar-collapse  d-lg-flex justify-content-lg-between"	id="templatemo_main_nav">
						<div class="flex-fill" style="width: 1300px">
							<ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
								<li class="nav-item"><a class="nav-link" href="main?scelta=index">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="main?scelta=about">About	Us</a></li>
								<li class="nav-item"><a class="nav-link" href="vediProdotti">Prodotti</a></li>
								<li class="nav-item"><a class="nav-link" href="checkout.jsp">Aiutaci</a></li>
							</ul>
						</div>
						<div class="navbar align-self-center d-flex">
							<div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
								<div class="input-group"></div>
							</div>
							<a class="nav-icon position-relative text-decoration-none" href="main?scelta=carrello" style="margin-left: 80px;">
								<i class="fa fa-fw fa-cart-arrow-down text-dark mr-1 fa-lg" title="Carrello"></i> 
								<span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${carrello.numProdotti}</span></a>
							
							<div class="login">
								<form action="login" method="post">
									<input type="text" required name="user" placeholder="Username" style="margin-bottom: 5px"> 
									<input type="password" required name="psw" placeholder="Password">

									<div class="divDeiBottoniNonLoggato">
										<button type="submit" value="Login" title="Login">Login</button>
										<button type="button" onclick="location.href='main?scelta=registrazione';" title="Registrati!">Sign in</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</nav>			
		</c:when>
		
		<c:when test="${utente.ruolo=='admin'}">
			<nav class="navbar navbar-expand-lg navbar-light shadow">
				<div class="container d-flex justify-content-between align-items-center">

					<a class="navbar-brand text-success align-self-center" href="index.jsp"> 
					<img alt="LOGO" src="assets/img/LogoProgetto.png" class="myLogo"> </a>

					<button class="navbar-toggler border-0" type="button"
						data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class=" navbar-collapse  d-lg-flex justify-content-lg-between"	id="templatemo_main_nav">
						<div class="flex-fill" style="width: 1300px">
							<ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
								<li class="nav-item"><a class="nav-link" href="main?scelta=index">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="main?scelta=about">About	Us</a></li>
								<li class="nav-item"><a class="nav-link" href="vediProdotti">Prodotti</a></li>
								<li class="nav-item"><a class="nav-link" href="contatti.jsp">Aiutaci</a></li>
							</ul>
						</div>
						<div class="navbar align-self-center d-flex">
							<div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
								<div class="input-group"></div>
							</div>
							<a class="nav-icon position-relative text-decoration-none" href="main?scelta=carrello"><i
								class="fa fa-fw fa-cart-arrow-down text-dark mr-1 fa-lg" title="Carrello"></i> 
								<span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${carrello.numProdotti}</span></a> 
								<a class="nav-icon position-relative text-decoration-none" href="#">
								<img class="utente" alt="UTENTE" title="Utente" src="assets/img/utente.png"></a> 
								Nome: <c:out value="${utente.nome}"></c:out><br> 
								Username:<c:out value="${utente.username}"></c:out><br> 
								<img alt="FOTO" src="${utente.imgProfilo}" style="width:100px"><br>
							<div class="divDeiBottoniNonLoggato">
								<a class="signin" href="logout"><button type="submit" value="Logout" style="width: 100px; margin-left: 20px">Logout</button></a>
								<a href="inserimento.jsp"><button type="button"	style="width: 100px; margin-left: 20px">Inserisci Prodotti</button></a>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</c:when>
		
		<c:otherwise>
			<nav class="navbar navbar-expand-lg navbar-light shadow">
				<div class="container d-flex justify-content-between align-items-center">

					<a class="navbar-brand text-success align-self-center" href="index.jsp"> 
					<img alt="LOGO" src="assets/img/LogoProgetto.png" class="myLogo">
					</a>

					<button class="navbar-toggler border-0" type="button"
						data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class=" navbar-collapse  d-lg-flex justify-content-lg-between"
						id="templatemo_main_nav" style="margin-left: -40px">
						<div class="flex-fill" style="max-width: 900px; margin-right: 40px">
							<ul	class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
								<li class="nav-item"><a class="nav-link" href="main?scelta=index">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="main?scelta=about">About	Us</a></li>
								<li class="nav-item"><a class="nav-link" href="vediProdotti">Prodotti</a></li>
								<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
								<li class="nav-item"><a	class="nav-icon position-relative text-decoration-none" href="main?scelta=carrello">
									<i class="fa fa-fw fa-cart-arrow-down text-dark mr-1 fa-lg" title="Carrello"></i>
									<span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${carrello.numProdotti}</span></a>
								<li class="nav-item"><a class="nav-icon position-relative text-decoration-none" href="account">
									<img class="utente" alt="UTENTE" title="Utente" src="assets/img/utente.png"></a></li>
							</ul>
						</div>
						<div class="navbar align-self-center d-flex">

							Nome: <c:out value="${utente.nome}"></c:out><br> 
							Username: <c:out value="${utente.username}"></c:out><br> 
							<img alt="FOTO" src="${utente.imgProfilo}" style="width:100px"><br>

							<div class="bottoniLogUtente" style="width:250px">
								<a class="signin" href="logout"><button type="submit" class="btnLogUtente" value="Logout" style="width: 80px">Logout</button></a>
								
							</div>
						</div>
					</div>
				</div>
			</nav>
		</c:otherwise>
	

	</c:choose>
	<%@include file="/popup.jsp"%>
</body>
</html>