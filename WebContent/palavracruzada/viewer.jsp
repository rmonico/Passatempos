<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.org.ita.palavracruzada.MatrizPalavraCruzada"%>
<%@page import="br.org.ita.palavracruzada.CelulaPalavraCruzada"%>
<%@page import="br.org.ita.palavracruzada.Caractere"%>
<%@page import="br.org.ita.palavracruzada.PalavraPosicionada"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Palavra Cruzada</title>

<style>
div.caractere {
	
}

div.ocupado {
	
}

td.caractere {
	width: 50px;
	height: 50px;
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

input.semborda {
	border: 0px;
}
</style>

<script language="javascript">
	function mudarTexto(linha, coluna, direcao, tamanho, dica) {
		//alert('coluna: ' + coluna + '\nlinha: ' + linha + '\ndirecao: ' + direcao + '\ntamanho: ' + tamanho + '\ndica: ' + dica);

		s = prompt(dica + '\n(' + tamanho + ' letra'
				+ (tamanho == 1 ? '' : 's') + ')', '');

		if (s.length != tamanho) {
			alert('Quantidade de caracteres incorreta.\nDigite uma palavra com '
					+ tamanho + ' caractere' + (tamanho == 1 ? '' : 's') + '.');
			return;
		}

		//alert(s.length);
		for (i = 1; i < s.length + 1; i++) {
			if (direcao == "VERTICAL") {
				nomeElemento = "td_L" + (linha + i) + "C" + coluna;
			} else if (direcao == "HORIZONTAL") {
				nomeElemento = "td_L" + linha + "C" + (coluna + i);
			}

			//alert(nomeElemento);

			$(nomeElemento).innerHTML = '<p class="caractere palavra">' + new String(
					s.substr(i - 1, 1)).toUpperCase() + '</p>';
		}
	}

	function $(id) {
		return document.getElementById(id);
	}
</script>

</head>
<body>

<form action="PalavraCruzadaSolucao" method="get">
<%
	MatrizPalavraCruzada matriz = (MatrizPalavraCruzada) request
			.getSession().getAttribute("matriz");
%>

<table border="0" cellspacing="0 px">
<%
		for (int y = 0; y <= matriz.getUltimaLinha(); y++) {
			out.println("  <tr id=\"tr_L" + y + "\">");

			for (int x = 0; x <= matriz.getUltimaColuna(); x++) {
				CelulaPalavraCruzada celula = matriz.getCelula(x, y);

				StringBuilder idTD = new StringBuilder();

				idTD.append("\"td_L" + y + "C" + x + "\"");

				StringBuilder classeTD = new StringBuilder("\"caractere");

				StringBuilder eventoOnclick = new StringBuilder();

				StringBuilder innerHTML = new StringBuilder();

				if (celula.equals(MatrizPalavraCruzada.celulaPreenchimento)) {
					classeTD.append(" desocupado");
				} else {
					classeTD.append(" ocupado");

					if (celula instanceof Caractere) {
						innerHTML
								.append("<input class=\"semborda\"  name=\"inputText_L"
										+ y
										+ "C"
										+ x
										+ "\" type=text size=1 value=\"\" />");

					} else {

						if (celula instanceof PalavraPosicionada) {
							PalavraPosicionada palavra = (PalavraPosicionada) celula;
							eventoOnclick.append("\"mudarTexto("
									+ palavra.getLinhaInicio() + ", "
									+ palavra.getColunaInicio() + ", '"
									+ palavra.getDisposicao() + "', "
									+ palavra.getTexto().length + ", '"
									+ palavra.getDica() + "')\"");
							innerHTML.append("<p class=\"dica\">" + palavra.getDica().toString() + "</p>");
						}
					}
				}

				classeTD.append("\"");

				StringBuilder stringTD = new StringBuilder();

				stringTD.append("    <td id="
						+ idTD
						+ " class="
						+ classeTD
						+ (eventoOnclick.length() > 0 ? " onclick="
								+ eventoOnclick : "") + ">");

				out.print(stringTD);

				out.print(innerHTML);

				out.println("</td>");
			}
			out.println("  </tr>");
		}
	%>
</table>

<!--  <button type="submit">Ver solução</button> -->
<!-- <button type="submit">Salvar</button> -->
</form>
</body>
</html>