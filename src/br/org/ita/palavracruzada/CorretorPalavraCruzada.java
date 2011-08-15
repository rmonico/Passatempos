package br.org.ita.palavracruzada;


public class CorretorPalavraCruzada {
	private MatrizPalavraCruzada matriz;
	private Character[][] respostas;
	private Correcao[][] correcao;

	public void corrigir() {
		correcao = new Correcao[matriz.getLargura()][matriz
				.getAltura()];

		for (int x = 0; x < matriz.getLargura(); x++) {
			for (int y = 0; y < matriz.getAltura(); y++) {

				Correcao correcaoCelula = new Correcao();
				correcao[x][y] = correcaoCelula;

				CelulaPalavraCruzada celula = matriz.getCelula(x, y);

				if (celula.equals(MatrizPalavraCruzada.celulaPreenchimento)
						|| celula instanceof Dica) {
					correcaoCelula.setIgnorar(true);

					continue;
				}

				correcaoCelula.setCorreto(celula.toChar());
				correcaoCelula.setResposta(respostas[x][y]);
			}
		}
	}

	public void setMatriz(MatrizPalavraCruzada matriz) {
		this.matriz = matriz;
	}
	
	public MatrizPalavraCruzada getMatriz() {
		return matriz;
	}

	public void setRespostas(Character[][] respostas) {
		this.respostas = respostas;
	}
	
	public Character[][] getRespostas() {
		return respostas;
	}

	public Correcao[][] getCorrecao() {
		return correcao;
	}
}
