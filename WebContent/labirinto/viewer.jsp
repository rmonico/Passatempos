<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.org.ita.labirinto.Labirinto"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Labirinto</title>
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
</style>
<script language="javascript">

var matriz = new Array();
<%
	Labirinto labirinto = (Labirinto) request.getAttribute("labirinto");

	for (int x=0; x<=labirinto.getUltimaColuna(); x++) {
		out.println("");
		out.println("matriz[" + x + "] = new Array();");
		for (int y=0; y<=labirinto.getUltimaLinha(); y++) {
			Labirinto.Casa casa = labirinto.getCasa(x, y);
			
			StringBuilder chamada = new StringBuilder("matriz[" + x + "][" + y + "]");
			
			chamada.append(" = new Celula(");
			chamada.append(casa.isCaminhoAcima());
			chamada.append(", " + casa.isCaminhoDireita());
			chamada.append(", " + casa.isCaminhoAbaixo());
			chamada.append(", " + casa.isCaminhoEsquerda());
			// Ocupado
			chamada.append(", false");
			// inicioFim
			chamada.append(", " + ((labirinto.getInicio().getColuna() == x && labirinto.getInicio().getLinha() == y) || (labirinto.getFim().getColuna() == x && labirinto.getFim().getLinha() == y)));
			chamada.append(");");
			out.println(chamada);
		}
	}
%>

	function Coordenada(coluna, linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	function Celula(caminhoAcima, caminhoDireita, caminhoAbaixo, caminhoEsquerda, ocupado, inicioFim) {
		this.caminhoAcima = caminhoAcima;
		this.caminhoDireita = caminhoDireita;
		this.caminhoAbaixo = caminhoAbaixo;
		this.caminhoEsquerda = caminhoEsquerda;
		this.ocupado = ocupado;
		this.inicioFim = inicioFim;
	}

	function onCasaClick(coluna, linha) {
		//alert("onCasaClick(" + coluna + ", " + linha + ");");

		casaMaisProxima = casaOcupadaMaisProxima(coluna, linha);

		//alert("casaMaisProxima.coluna: " + casaMaisProxima.coluna + "; casaMaisProxima.linha: " + casaMaisProxima.linha);
		
		if (casaMaisProxima == null) {
			cliqueEmPosicaoInvalida(coluna, linha);
			return;
		}

		ocupar = !matriz[coluna, linha].ocupado;

		//alert(ocupar);

		if (casaMaisProxima.linha == linha) {
			if (casaMaisProxima.coluna > coluna) {
				//alert("Direita: " + coluna + "; " + casaMaisProxima.coluna);
				for (i=coluna; i<=casaMaisProxima.coluna; i++) {
					mudarOcupacaoCasa(i, linha, ocupar);
				}
			} else {
				//alert("Esquerda: " + casaMaisProxima.coluna + "; " + coluna);
				for (i=casaMaisProxima.coluna; i<=coluna; i++) {
					mudarOcupacaoCasa(i, linha, ocupar);
				}
			}
		} else {
			if (casaMaisProxima.linha > linha) {
				//alert("Abaixo: " + linha + "; " + casaMaisProxima.linha);
				for (i=linha; i<=casaMaisProxima.linha; i++) {
					mudarOcupacaoCasa(coluna, i, ocupar);
				}
			} else {
				//alert("Acima: " + casaMaisProxima.linha + "; " + linha);
				for (i=casaMaisProxima.linha; i<=linha; i++) {
					mudarOcupacaoCasa(coluna, i, ocupar);
				}
			}
		}

		//alert("returning: onCasaClick(" + coluna + ", " + linha + ");");
	}

	function casaOcupadaMaisProxima(coluna, linha) {
		//alert("casaOcupadaMaisProxima(" + x + ", " + y + ");");

		coordenada = new Coordenada(coluna, linha);

		i = procurarCasaOcupadaAcima(coluna, linha);

		//alert("acima: " + i);
		
		if (i != -1) {
			coordenada.linha = i;
		} else {
			i = procurarCasaOcupadaDireita(coluna, linha);

			//alert("direita: " + i);

			if (i != -1) {
				coordenada.coluna = i;
			} else {
				i = procurarCasaOcupadaAbaixo(coluna, linha);

				//alert("abaixo: " + i);

				if (i != -1) {
					coordenada.linha = i;
				} else {
					i = procurarCasaOcupadaEsquerda(coluna, linha);

					//alert("esquerda: " + i);

					if (i != -1) {
						coordenada.coluna = i;
					} else {
						return null;
					}
				}
			}
		}

		return coordenada;
	}

	function cliqueEmPosicaoInvalida() {
	}
	

	function mudarOcupacaoCasa(coluna, linha, ocupado) {
		//alert("mudarOcupacaoCasa(" + coluna + ", " + linha + ", " + ocupado + ")");
		matriz[coluna][linha].ocupado = ocupado;
		//alert(ocupado);
		imgCasa = document.getElementById("img_" + coluna + "_" + linha);
		
		if (ocupado) {
			imgCasa.src = imgCasa.getAttribute("src").replace("vazio", "ocupado");
		} else {
			imgCasa.src = imgCasa.getAttribute("src").replace("ocupado", "vazio");
		}
	}

	// Devolve se encontrar uma celula ocupada acima da celula em x, y, ou -1 se não tiver nenhuma
	function procurarCasaOcupadaAcima(x, y) {
		//alert("procurarCasaOcupadaAcima(" + x + ", " + y + ");");
		for (linha=y; linha>=0; linha--) {
			//alert("matriz[" + x + "][" + linha + "].caminhoAcima: " + matriz[x][linha].caminhoAcima);

			if (matriz[x][linha].ocupado || matriz[x][linha].inicioFim) {
				return linha;
			}
			
			if (!matriz[x][linha].caminhoAcima) {
				return -1;
			}
		}
	}

	// Devolve se encontrar uma celula ocupada a direita da celula em x, y
	function procurarCasaOcupadaDireita(x, y) {
		//alert("procurarCasaOcupadaDireita(" + x + ", " + y + ");");
		//alert("matriz[0].length: " + matriz[0].length);
		for (coluna=x; coluna<=matriz[0].length; coluna++) {
			//alert("matriz[" + coluna + "][" + y + "].caminhoDireita: " + matriz[coluna][y].caminhoDireita);
			
			if (matriz[coluna][y].ocupado || matriz[coluna][y].inicioFim) {
				return coluna;
			}
						
			if (!matriz[coluna][y].caminhoDireita) {
				return -1;
			}
		}
	}

	// Devolve se encontrar uma celula ocupada abaixo da celula em x, y
	function procurarCasaOcupadaAbaixo(x, y) {
		//alert("procurarCasaOcupadaAbaixo(" + x + ", " + y + ");");
		//alert("matriz.length: " + matriz.length);
		for (linha=y; linha<=matriz.length; linha++) {
			//alert("matriz[" + x + "][" + linha + "].caminhoAbaixo: " + matriz[x][linha].caminhoAbaixo);
			
			if (matriz[x][linha].ocupado || matriz[x][linha].inicioFim) {
				return linha;
			}
			
			if (!matriz[x][linha].caminhoAbaixo) {
				return -1;
			}
		}
	}


	// Devolve se encontrar uma celula ocupada a esquerda da celula em x, y
	function procurarCasaOcupadaEsquerda(x, y) {
		//alert("procurarCasaOcupadaEsquerda(" + x + ", " + y + ");");
		for (coluna=x; coluna>=0; coluna--) {
			//alert("matriz[" + coluna + "][" + y + "].caminhoEsquerda: " + matriz[coluna][y].caminhoEsquerda);

			if (matriz[coluna][y].ocupado || matriz[coluna][y].inicioFim) {
				return coluna;
			}
						
			if (!matriz[coluna][y].caminhoEsquerda) {
				return -1;
			}
		}
	}
		
</script>
</head>

<body>
<div class="efeitoTexto">
	<div class="efeitoTexto1">Labirinto</div>
	<div class="efeitoTexto2">Labirinto</div>
</div>
<table cellspacing="0 px" cellpadding="0 px">
<%
	for (int y = 0; y <= labirinto.getUltimaLinha(); y++) {
		out.println("  <tr>");

		for (int x = 0; x <= labirinto.getUltimaColuna(); x++) {
			out.print("    <td style=\"cursor: default; height: 18px; width: 18px;\"><img id=\"img_" + x + "_" + y + "\" src=\"");
			StringBuilder nomeImagem = new StringBuilder("");
		
			if (x == labirinto.getInicio().getColuna() && y == labirinto.getInicio().getLinha()) {
				nomeImagem.append("Inicio");
			} else if (x == labirinto.getFim().getColuna() && y == labirinto.getFim().getLinha()) {
				nomeImagem.append("Fim");
			} else {
				if (labirinto.getCasa(x, y).isCaminhoAcima()) {
					nomeImagem.append("Cima");
				}
		
				if (labirinto.getCasa(x, y).isCaminhoDireita()) {
					nomeImagem.append((nomeImagem.length() > 0 ? "-" : "") + "Direita");
				}
		
				if (labirinto.getCasa(x, y).isCaminhoAbaixo()) {
					nomeImagem.append((nomeImagem.length() > 0 ? "-" : "") + "Baixo");
				}
		
				if (labirinto.getCasa(x, y).isCaminhoEsquerda()) {
					nomeImagem.append((nomeImagem.length() > 0 ? "-" : "") + "Esquerda");
				}
			}
		
			String prefixoImagem = "labirinto/imagens-vazio/Labirinto-";
			String sufixoImagem = ".jpg";
		
			out.println(prefixoImagem + nomeImagem.toString() + sufixoImagem + "\" onclick=\"onCasaClick(" + x + ", " + y + ")\"/></td>");
		}
		out.println("  </tr>");
	}
%>
</table>

</body>
</html>