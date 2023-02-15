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
	
<div clas="maxiContainerCheckout" style="text-align:center">
	<div class="containerCeckout">
	<h3 style="margin-top: 20px; text-align: center">Riepilogo
		prodotti</h3>

	<!-- recuperiamo i prodotti nel carrello da carrelloRiepilogo????-->

			<h4>Numero prodotti: <c:out value="${carrello.numProdotti}"></c:out></h4>
			<c:forEach items="${carrello.listaProdotti}" var="prodotto">
			<div class="containerCarrello">			
			<div class="cardCarrello">
				<img class="fotoProdottoCarrello"src="${prodotto.imgProdotto}">
				<div class="dettagliProdottoCarrello">
				<p><c:out value="${prodotto.nome}"></c:out></p>
				<p><c:out value="${prodotto.prezzo}"></c:out>€	</p>		
							
							</div>
							</div>
							</div>
			</c:forEach>			
			<h4>Totale: <c:out value="${carrello.getTotaleForm()}"></c:out> €</h4>

	<!-- recuperiamo i dati della spedizione da riepilogoServlet -->
	<hr>

	<h4 style="margin-top: 20px;text-align: center">Ecco i tuoi dati
		di spedizione</h4>

	<div class="containerRiepilogo" style=" margin: auto; width: 500px; padding:40px; line-height:2em; border:1px solid black; border-radius:20px; margin-bottom:40px">
	<table>
		<tr>
			<td><label for="indirizzoSpedizione">Indirizzo di
					spedizione:</label></td>
			<td><b>${ordine.indirizzoSpedizione}</b></td>
		</tr>

		<tr>
			<td><label for="numeroCivico">Numero Civico:</label></td>
			<td><b>${ordine.numeroCivico}</b></td>
		</tr>

		<tr>
			<td><label for="citta">Città:</label></td>
			<td><b>${ordine.citta}</b></td>
		</tr>

		<tr>
			<td><label for="provincia">Provincia:</label></td>
			<td><b>${ordine.provincia}</b></td>
		</tr>

		<tr>
			<td><label for="nazione">Nazione:</label></td>
			<td><b>${ordine.nazione}</b></td>
		</tr>

		<tr>
			<td><label for="note">Note:</label></td>
			<td><b>${ordine.note}</b></td>
		</tr>
	</table>




	<!-- DAti pagamento -->

<h4 style="margin-top: 20px">Riepilogo dati
		di pagamento</h4>

	<table>
		<tr>
			<td><label for="nomeIntestatario">Nome intestatario:</label></td>
			<td><b>${pagamento.nomeIntestatario}</b></td>
		</tr>

		<tr>
			<td><label for="cognomeIntestatario">Cognome
					intestatario:</label></td>
			<td><b>${pagamento.cognomeIntestatario}</b></td>
		</tr>

		<tr>
			<td><label for="circuitoCarta">Circuito carta:</label></td>
			<td><b>${pagamento.circuitoCarta}</b></td>
		</tr>

		<tr>
			<td><label for="cartaDiCredito">Carta di credito:</label></td>
			<td><b>${pagamento.getCartaDiCreditoFor()}</b></td>
		</tr>

		<tr>
			<td><label for="scadenzaCarta">Scadenza carta:</label></td>
			<td><b>${pagamento.getScadenzaCarta().getMonth()}/${pagamento.getScadenzaCarta().getYear()+1900}</b></td>
		</tr>

		<tr>
			<td><label for="indirizzoFatturazione">Indirizzo
					fatturazione:</label></td>
			<td><b>${pagamento.indirizzoFatturazione}</b></td>
		</tr>

	</table>

	<table>
	
	
	<tr>
		<td><button type="button" onclick="location.href='riepilogo';" title="Conferma" style="margin-top: 20px; text-align:center">Conferma</button></td>
		<td><button type="button" onclick="location.href='datiPagamento';" title="Torna indietro" style="margin-top: 20px; text-align:center">Torna indietro</button></td>

</tr>
	</table>
	
	</div>
</div>
</div>


	<script src="index.js"></script>

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>