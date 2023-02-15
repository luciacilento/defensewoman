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


<div class="contenitoreDatiSpedizione">
	<h2 class="titoloDatiSpedizione">Dati di spedizione</h2>

	<c:out value="${errore}"></c:out>

	<form class="regi" action="datiOrdine" method="post" enctype="multipart/form-data">

		<table class="tabellaDatiSpedizione">

			<tr>
				
				<td><label for="indirizzoSpedizione">Indirizzo di
						spedizione</label></td>
				<td><input value="${ordine.indirizzoSpedizione}" type="text"
					name="indirizzo" style="border: 1px solid red" required></td>
				<c:out value="${erroreUsername}"></c:out>
			</tr>

			<tr>

				<td><label for="numCiv">Numero Civico</label></td>
				<td><input value="${ordine.numeroCivico}" type="text"
					name="numCiv" style="border: 1px solid red" required></td>

			</tr>

			<tr>

				<td><label for="citta">Città</label></td>
				<td><input value="${ordine.citta}" type="text" name="citta"
					style="border: 1px solid red" required></td>

			</tr>




			<tr>

				<td><label for="provincia">Provincia</label></td>
				<td><input value="${ordine.provincia}" type="text"
					name="provincia" style="border: 1px solid red" maxlength="2" required></td>

			</tr>


			<tr>

				<td><label for="nazione">Nazione</label></td>
				<td><input value="${ordine.nazione}" type="text" name="nazione"
					style="border: 1px solid red" required></td>

			</tr>



			<tr>

				<td><label for="note">Note</label></td>
				<td><textarea name="note" style= "border: px solid #535c4d; resize:none; rows=6; cols=60" placeholder="Scrivi un commento..." ></textarea></td>

			</tr>

			
			<tr>				
				<td><button type="submit" value="Invia" style="margin-top: 20px; text-align:center">Conferma dati</button> </td>				
				<td><button type="button" onclick="location.href='main?scelta=carrello';" title="Torna indietro" style="margin-top: 20px; text-align:center">Torna indietro</button></td>		
			</tr>
			
		</table>
		
	</form>
	
	</div>

	<script src="index.js"></script>

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>




