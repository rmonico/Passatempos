<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.org.ita.numerox.MatrizNumerox"%>
<%@page import="br.org.ita.numerox.CelulaNumerox"%>
<%@page import="br.org.ita.numerox.NumeroPosicionado"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Numerox</title>

<style>
BODY {
	background-image: url(imagens/fundo_site.jpg);
	background-repeat: no-repeat;
	background-attachment: scroll;
	color: #003366;
	cursor: default;
	font-family: Geneva, Arial, Helvetica, sans-serif;
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

td.numero {
	width: 20px;
	height: 20px;
	text-align: center;
	vertical-align: middle;
}

td.ocupado {
	border: 1px solid;
}

td.desocupado {
	
}

p.dica {
	font-face: arial;
	font-size: 7pt;
}
</style>

<script language="javascript">
	function mudarTexto(linha, coluna, direcao, deslocamento, tamanho) {
		//alert('coluna: ' + coluna + '\nlinha: ' + linha + '\nDirecao: ' + direcao + '\ntamanho: ' + tamanho);
		s = prompt(tamanho + ' algarismos', '');

		if (s.length != tamanho) {
			alert('Quantidade de caracteres incorreta.\nDigite um número com '
			+ tamanho + ' algarismos.');
			return;
		}

		//alert(s.length);
		for (i = 1; i < s.length + 1; i++) {
			if (direcao == "VERTICAL") {
				nomeElemento = "td_L" + (linha + i - deslocamento - 1) + "C" + coluna;
			} else if (direcao == "HORIZONTAL") {
				nomeElemento = "td_L" + linha + "C" + (coluna + i - deslocamento - 1);
			}

			//alert(nomeElemento);

			$(nomeElemento).innerHTML = new String(
				s.substr(i - 1, 1)).toUpperCase();
		}
	}

	function mudarTextoNoCruzamento() {
		alert("Para adicionar um número, clique em uma célula fora do cruzamento.");
	}

	function $(id) {
		return document.getElementById(id);
	}
</script>

</head>
<body>
<div class="efeitoTexto">
	<div class="efeitoTexto1">Numerox</div>
	<div class="efeitoTexto2">Numerox</div>
</div>
<%
	MatrizNumerox numerox = (MatrizNumerox) request
			.getAttribute("numerox");
%>

<table border="0" cellspacing="0 px">
<%
		for (int y = 0; y <= numerox.getUltimaLinha(); y++) {
			StringBuilder nomeTR = new StringBuilder("\"tr_L" + y + "\"");
			
			out.print("  <tr id=" + nomeTR + ">\n");
			
			for (int x = 0; x <= numerox.getUltimaColuna(); x++) {
					CelulaNumerox celula = numerox.getCelula(x, y);
					NumeroPosicionado numero = celula.getNumero();
					
					StringBuilder classeCSSCelula = new StringBuilder("\"numero ");
					
					classeCSSCelula.append(celula.isVazia() ? "desocupado" : "ocupado");
					if (celula.getCruzamento() != null) {
						classeCSSCelula.append(" cruzamento");
					}
					classeCSSCelula.append("\"");
					
					StringBuilder nomeCelula = new StringBuilder("\"td_L" + y + "C" + x + "\"");
					
					StringBuilder eventoOnClick = new StringBuilder("\"");
					
					if (!celula.isVazia() && !celula.isPrimeiraPalavra()) {
						int deslocamento;
						
						if (numero.getDisposicao().EHorizontal()) {
							deslocamento = x - numero.getColunaInicio();
						} else {
							deslocamento = y - numero.getLinhaInicio();
						}
						
						if (celula.getCruzamento() != null) {
							eventoOnClick.append("mudarTextoNoCruzamento()");
						} else {
							eventoOnClick.append("mudarTexto(" + y + ", " + x + ", '" + numero.getDisposicao() + "', " + deslocamento + ", " + numero.getTamanho() + ")");
						}
					}
					
					eventoOnClick.append("\"");
					
					StringBuilder tdString = new StringBuilder("");
					tdString.append("    <td id=" + nomeCelula + " class=" + classeCSSCelula + " onclick=" + eventoOnClick + ">");
					
					if (celula.isPrimeiraPalavra()) {
						tdString.append(celula.getChar());
					} else {
						tdString.append("&nbsp");
					}
					
					tdString.append("</td>");
					
					out.println(tdString);
			}
			out.println("</tr>");
		}
%>
</table>
<br />
<br />
<%
@SuppressWarnings("unchecked")
	Map<Integer, List<NumeroPosicionado>> bancoNumeros = (Map<Integer, List<NumeroPosicionado>>) request
			.getAttribute("bancoNumeros");

	for (Integer digito : bancoNumeros.keySet()) {
		
		out.println("<h1>" + digito + " dígitos</h1>");
		out.println("<ul>");

		for (NumeroPosicionado numero : bancoNumeros.get(digito)) {
			StringBuilder listItem = new StringBuilder();
			
			listItem.append("<li>");
			
			if (numero.isPrimeiraPalavra()) {
				listItem.append("<b>");
			}
			
			listItem.append(numero.toString());
			
			if (numero.isPrimeiraPalavra()) {
				listItem.append("</b>");
			}
			
			listItem.append("</li>");
			
			out.println(listItem);
		}
		out.println("</ul>");
	}
%>
</body>
</html>