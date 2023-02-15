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

	<form class="regi" action="registrazione" method="post"
		enctype="multipart/form-data">


		<table>
			<tr>

				<td><label for="user">Username</label></td>
				<td><input value="${utenteReg.username}" type="text" name="user"
					style="border: 1px solid red" required></td>				
			</tr>

			<tr>

				<td><label for="psw">Password</label></td>
				<td><input value="${utenteReg.pw}" type="password" name="psw"
					style="border: 1px solid red" required></td>

			</tr>

			<tr>

				<td><label for="nome">Nome</label></td>
				<td><input value="${utenteReg.nome}" type="text" name="nome"
					style="border: 1px solid red" required></td>

			</tr>

			<tr>

				<td><label for="cognome">Cognome</label></td>
				<td><input value="${utenteReg.cognome}" type="text" name="cognome"
					style="border: 1px solid red" required></td>

			</tr>


			<tr>
				<td><label for="dataNascita">Data di nascita</label></td>
				<td><input value="<fmt:formatDate value="${utenteReg.dataNascita}" pattern="yyyy-MM-dd"/>" type="date"
					name="dataNascita" style="border: 1px solid red" required
					min="1923-01-01" max="2007-12-31"></td>			
			</tr>

			<tr>

				<td><label for="cell">Cellulare</label></td>
				<td><input value="${utenteReg.numeroCellulare}" type="tel"
					name="cell" style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="mail">E-mail</label></td>
				<td><input value="${utenteReg.mail}" type="email" name="mail"
					style="border: 1px solid red" required></td>

			</tr>

			<tr>

				<td><label for="indirizzo">Indirizzo</label></td>
				<td><input value="${utenteReg.indirizzo}" type="text"
					name="indirizzo" style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="numCiv">Num. civico</label></td>
				<td><input value="${utenteReg.numCiv}" type="text" name="numCiv"
					style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="citta">Città</label></td>
				<td><input value="${utenteReg.citta}" type="text" name="citta"
					style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="provincia">Provincia</label></td>
				<td><input value="${utenteReg.provincia}" type="text"
					name="provincia" style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="cap">CAP</label></td>
				<td><input value="${utenteReg.cap}" type="text" name="cap"
					style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>

				<td><label for="nazione">Nazione</label></td>
				<td><input value="${utenteReg.nazione}" type="text" name="nazione"
					style="border: 1px solid #535c4d"></td>

			</tr>

			<tr>
				<td><label for="foto">Foto</label></td>
				<td><input type="file" name="imgProfilo"
					value="${utenteReg.imgProfilo}">Scegli file..</td>
			</tr>
			<tr>

				<td><c:out value="${erroreRegistrazione}"></c:out>						
					<button type="submit" value="Invia" style="margin-top: 20px">Invia</button>
				</td>
			</tr>

		</table>
	</form>

	<script src="index.js"></script>

	<!-- Start Footer -->
	<%@include file="/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>