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

<script>
  function autoSpace(input) {
    input.value = input.value.replace(/\s/g, '').replace(/(\d{4})/g, '$1 ').trim();
  }
</script>

	<!-- Header -->
	<%@include file="/menu.jsp"%>
	<!-- Close Header -->

	<h3 style="margin-top: 20px; text-align: center">Procedi
		all'acquisto!</h3>

	<form class="regi" action="datiPagamento" method="post"
		enctype="multipart/form-data">

		<p>
		<table class="tabellaPagamento">
			<tr>
				<td><label for="nomeIntestatario" style="margin-top: 20px">Nome:</label></td>
				<td class="td2"><input value="${pagamento.nomeIntestatario}"
					type="text" name="nomeIntestatario" required
					style="border: 1px solid red; margin-top: 20px; width: 320px"></td>

			</tr>

			<tr>

				<td><label for="cognomeIntestatario" style="margin-top: 20px">Cognome:</label></td>
				<td class="td2"><input value="${pagamento.cognomeIntestatario}"
					type="text" name="cognomeIntestatario" required
					style="border: 1px solid red; margin-top: 20px; width: 320px"></td>

			</tr>


			<tr>

				<td><label for="circuitoCarta" style="margin-top: 20px">Carta:</label></td>
				<td class="td2"><input <c:if test="${pagamento.circuitoCarta=='MasterCard'}">checked</c:if> value="MasterCard"
					type="radio" name="circuitoCarta" style="width: 10px; margin-top: 20px">
					<label><img src="assets/img/master.png"
						style="margin-top: 20px"></label> <input <c:if test="${pagamento.circuitoCarta=='Maestro'}">checked</c:if>
					value="Maestro" type="radio" name="circuitoCarta"
					style="width: 10px; margin-top: 20px"> <label><img
						src="assets/img/mae.png" style="margin-top: 20px"></label> <input <c:if test="${pagamento.circuitoCarta=='PayPal'}">checked</c:if>
					value="PayPal" type="radio" name="circuitoCarta"
					style="width: 10px; margin-top: 20px"> <label><img
						src="assets/img/p.jpg" style="margin-top: 20px"></label> <input <c:if test="${pagamento.circuitoCarta=='American Express'}">checked</c:if>
					value="American Express" type="radio" name="circuitoCarta"
					style="width: 10px; margin-top: 20px"> <label><img
						src="assets/img/ae.png" style="margin-top: 20px"></label> <input <c:if test="${pagamento.circuitoCarta=='Visa'}">checked</c:if>
					value="Visa" type="radio" name="circuitoCarta"
					style="width: 10px; margin-top: 20px"> <label><img
						src="assets/img/visa.jpg" style="margin-top: 20px"></label></td>


			</tr>

			<tr>	

				<td><label for="cartaDiCredito" style="margin-top: 20px">Numero	carta:</label></td>

					<c:choose>
					  <c:when test="${empty pagamento.getCartaDiCreditoFor()}">
					    <td class="td2"><br><input value="" 
					      type="text" name="cartaDiCredito" placeholder="xxxx xxxx xxxx xxxx"
					      pattern="[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}" required
					      style="border: 1px solid red; margin-top: 20px; width: 320px"
					      onkeyup="autoSpace(this)"></td>	
					  </c:when>
					  <c:otherwise>
					    <td class="td2"><input value=""	type="text" name="cartaDiCredito" 
					      placeholder="${pagamento.getCartaDiCreditoFor()}"
					      pattern="[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}" 
					      style="border: 1px solid red; margin-top: 20px; width: 320px"
					      onkeyup="autoSpace(this)"></td>	
					  </c:otherwise>
					</c:choose>
							
			</tr>

			<tr>
				<td><label for="scadenzaCarta" style="margin-top: 20px">Data di scadenza:</label></td>
				<td class="td2"><select name="meseScadenza" required
					style="border: 1px solid red; margin-top: 20px; width: 168px; margin-left: 20px; border-radius: 5px;">
						<option value="">Mese</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==1}">selected</c:if> value="01">Gennaio</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==2}">selected</c:if> value="02">Febbraio</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==3}">selected</c:if> value="03">Marzo</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==4}">selected</c:if> value="04">Aprile</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==5}">selected</c:if> value="05">Maggio</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==6}">selected</c:if> value="06">Giugno</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==7}">selected</c:if> value="07">Luglio</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==8}">selected</c:if> value="08">Agosto</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==9}">selected</c:if> value="09">Settembre</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==10}">selected</c:if> value="10">Ottobre</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==11}">selected</c:if> value="11">Novembre</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getMonth()==12}">selected</c:if> value="12">Dicembre</option>
				</select> 
				<select name="annoScadenza" required
					style="border: 1px solid red; margin-top: 20px; width: 150px; border-radius: 5px;">
						<option  value="">Anno</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2023-1900)}">selected</c:if> value="2023">2023</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2024-1900)}">selected</c:if> value="2024">2024</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2025-1900)}">selected</c:if> value="2025">2025</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2026-1900)}">selected</c:if> value="2026">2026</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2027-1900)}">selected</c:if> value="2027">2027</option>
						<option <c:if test="${pagamento.getScadenzaCarta().getYear()==(2028-1900)}">selected</c:if> value="2028">2028</option>
				</select></td>
			</tr>

			<tr>

				<td><label for="indirizzoFatturazione" style="margin-top: 20px">Indirizzo
						di fatturazione:</label></td>
				<td class="td2"><input
					value="${pagamento.indirizzoFatturazione}" type="text"
					name="indirizzoFatturazione"
					style="border: 1px solid #535c4d; margin-top: 20px; width: 320px"></td>

			</tr>

			<tr>

				<td><label for="codiceSicurezza" style="margin-top: 20px">CVV/CVC:</label></td>
				<td class="td2"><input type="text" name="codiceSicurezza"
					pattern="\d{3}" maxlength="3"
					style="border: 1px solid red; margin-top: 20px; width: 320px"
					placeholder="***" required></td>

			</tr>

			<tr>

				<td><button type="submit" value="Invia" style="margin-top: 20px; text-align:center">Conferma pagamento</button></td>
				<td><button type="button" onclick="location.href='datiOrdine';" title="Torna indietro" style="margin-top: 20px; text-align:center">Torna indietro</button></td>
				
			</tr>

		</table>

	</form>


	<script src="index.js"></script>

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>