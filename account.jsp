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
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/LogoProgettoNoBg.png">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;700&family=Pangolin&family=Playfair+Display&family=Quicksand:wght@300;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">

</head>
<body>

	<!-- Header -->
	<%@include file="/menu.jsp"%>
	<!-- Close Header -->

	<h3 style="margin-top: 20px; text-align: center">Il tuo profilo</h3>

	<!-- Scrivi cosa visualizzare nel account  -->

	<table>

		<tr>

			<td style="text-align:center"><img alt="FOTO" src="${utente.imgProfilo}"></td>
			
		</tr>

		<tr>
			<td><label for="username">Username:</label></td>
			<td><b> ${utenteBean.username}</b></td>
		</tr>


		<tr>
			<td><label for="nome">Nome:</label></td>
			<td><b>${utenteBean.nome}</b></td>
		</tr>

		<tr>
			<td><label for="cognome">Cognome:</label></td>
			<td><b>${utenteBean.cognome}</b></td>
		</tr>


		<tr>
			<td><label for="dataNascita">Data di Nascita:</label></td>
			<td><b>${utenteBean.dataNascita}</b></td>
		</tr>

		<tr>
			<td><label for="numeroCellulare">Numero di cellulare:</label></td>
			<td><b>${utenteBean.numeroCellulare}</b></td>
		</tr>

		<tr>
			<td><label for="mail">Mail:</label></td>
			<td><b>${utenteBean.mail}</b></td>
		</tr>

		<tr>
			<td><label for="indirizzo">Indirizzo:</label></td>
			<td><b>${utenteBean.indirizzo}</b></td>
		</tr>


		<tr>
			<td><label for="numCiv">Numero Civico:</label></td>
			<td><b>${utenteBean.numCiv}</b></td>
		</tr>

		<tr>
			<td><label for="citta">Città:</label></td>
			<td><b>${utenteBean.citta}</b></td>
		</tr>

		<tr>
			<td><label for="provincia">Provincia:</label></td>
			<td><b>${utenteBean.provincia}</b></td>
		</tr>

		<tr>
			<td><label for="cap">CAP:</label></td>
			<td><b>${utenteBean.cap}</b></td>
		</tr>

		<tr>
			<td><label for="nazione">Nazione:</label></td>
			<td><b>${utenteBean.nazione}</b></td>
		</tr>

		<tr>
			<td><button type="button" onclick="location.href='modificaProfilo';" title="Modifica profilo" style="margin-top: 20px; text-align:center">Modifica profilo</button></td>
			<td><button type="button" onclick="location.href='main?scelta=index';" title="Torna alla home" style="margin-top: 20px; text-align:center">Torna alla home</button></td>
			
		</tr>
	
	</table>

	<script src="index.js"></script>
	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->


</body>
</html>