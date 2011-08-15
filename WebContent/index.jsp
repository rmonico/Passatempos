<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Passa Tempos do ITA</title>
<style>
BODY {
	background-image: url(imagens/fundo_site.jpg);
	background-repeat: no-repeat;
	background-attachment: scroll;
	color: #003366;
	cursor: default;
}

A {
	color: #003366;
	text-decoration: none;
}

A:visited {
	color: #003366;
	text-decoration: none;
}

TD {
	text-align: center;
	font-family: "Courier New", Courier, monospace;
	font-size: 18px;
	font-weight: bold;
}

.efeitoTexto {
	position: relative;
	width: 45em;
	height: 5em;
	margin: 5px 0;
}

.efeitoTexto1 {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: 38px;
	font-style: italic;
	font-weight: bold;
	position:absolute;
	top: 0px;
	left: 0px;
	color: #0099FF;
}

.efeitoTexto2 {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: 38px;
	font-style: italic;
	font-weight: bold;
	position:absolute;
	left: 2px;
	top: 2px;
	color: #003366;
}
</style>
<script language="javascript" type="text/javascript">
<!--
function abreJogo(jogo){
	var pagina = "";

	switch(jogo){
		case 1:
			pagina += "PalavraCruzada";
			break;
		case 2:
			pagina += "Numerox";
			break;
		case 3:
			pagina += "Labirinto";
			break;
	}

	var janela = window.open(pagina,'JOGO_ATUAL','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=400,height=400');
}
//-->
</script>
</head>
<body>
<img src="imagens/logo.jpg" width="590" height="89" />

<div class="efeitoTexto">
	<div class="efeitoTexto1">Faça o Tempo voar nesse portal!</div>
	<div class="efeitoTexto2">Faça o Tempo voar nesse portal!</div>
</div>

<table width="650" border="0" cellspacing="2" cellpadding="3">
  <tr>
    <td><a href="#" onClick="abreJogo(1);"><img src="imagens/icone_palavra_cruzada.jpg" width="211" height="211" /></a></td>
    <td><a href="#" onClick="abreJogo(2);"><img src="imagens/icone_numerox.jpg" width="211" height="211" /></a></td>
    <td><a href="#" onClick="abreJogo(3);"><img src="imagens/icone_labirinto.jpg" width="211" height="211" /></a></td>
  </tr>
  <tr>
    <td><a href="#" onClick="abreJogo(1);">Palavra Cruzada</a></td>
    <td><a href="#" onClick="abreJogo(2);">Numerox</a></td>
    <td><a href="#" onClick="abreJogo(3);">Labirinto</a></td>
  </tr>
</table>

</body>
</html>