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

	<div class="aggiungiProdotti">
		<form action="aggiungiProdotti" method="post" enctype="multipart/form-data">
			<input type="number" required name="disponibilita" placeholder="disponibilità (1-0)"> 
			<input type="text" required name="nome" placeholder="nome prodotto">
			<input type="text" required name="descrizione" placeholder="descrizione prodotto"> 
			<input type="number" required name="prezzo" placeholder="prezzo prodotto">
			<input type="file" name="imgProdotto"> 
			<input type="number" required name="FKCategoria" placeholder="FK categoria">
			<button type="submit" value="Upload">Aggiungi</button>
		</form>

	</div>
</body>
</html>