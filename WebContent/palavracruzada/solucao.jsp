<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="br.org.ita.palavracruzada.MatrizPalavraCruzada"%>
<%@page import="br.org.ita.palavracruzada.CelulaPalavraCruzada"%>
<%@page import="br.org.ita.palavracruzada.Caractere"%>
<%@page import="br.org.ita.palavracruzada.PalavraPosicionada"%>
<%@page import="br.org.ita.palavracruzada.CorretorPalavraCruzada"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Palavra Cruzada - Solução</title>

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

</head>
<body>
<%
	CorretorPalavraCruzada corretor = (CorretorPalavraCruzada) request
			.getAttribute("corretorPalavraCruzada");

	MatrizPalavraCruzada matriz = corretor.getMatriz();
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
								.append("<input class=\"semborda\"  type=text size=1 value=\""
										+ corretor.getCorrecao()[x][y]
												.getResposta() + "\" />");

					} else {

						if (celula instanceof PalavraPosicionada) {
							PalavraPosicionada palavra = (PalavraPosicionada) celula;
							eventoOnclick.append("\"mudarTexto("
									+ palavra.getLinhaInicio() + ", "
									+ palavra.getColunaInicio() + ", '"
									+ palavra.getDisposicao() + "', "
									+ palavra.getTexto().length + ", '"
									+ palavra.getDica() + "')\"");
							innerHTML
									.append("<p class=\"dica\">"
											+ palavra.getDica().toString()
											+ "</p>");
						}
					}
				}

				classeTD.append("\"");

				StringBuilder stringTD = new StringBuilder();

				StringBuilder backgroundColor = new StringBuilder();

				if (!corretor.getCorrecao()[x][y].isIgnorar()) {
					if (corretor.getCorrecao()[x][y].isOk()) {
						backgroundColor.append(" bgcolor=\"#88FF88\""); // verde
					} else {
						backgroundColor.append(" bgcolor=\"#FF8888\""); // vermelho
					}
				}

				stringTD.append("    <td id="
						+ idTD
						+ backgroundColor
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

</body>
</html>