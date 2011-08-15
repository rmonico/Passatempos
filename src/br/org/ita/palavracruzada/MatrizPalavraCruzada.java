package br.org.ita.palavracruzada;

import br.org.ita.jogoscruzadoscomum.Matriz;

public class MatrizPalavraCruzada implements Matriz<CelulaPalavraCruzada, PalavraPosicionada> {
	private CelulaPalavraCruzada[][] matriz;
	private int largura;
	private int altura;

	public MatrizPalavraCruzada(int l, int a) {
		largura = l;
		altura = a;

		matriz = new CelulaPalavraCruzada[l][a];
	}

	public CelulaPalavraCruzada getCelula(int x, int y) {
		if (matriz[x][y] == null) {
			return celulaPreenchimento;
		} else {
			return matriz[x][y];
		}
	}

	@Override
	public void setCelula(int x, int y, CelulaPalavraCruzada ch) {
		this.matriz[x][y] = ch;
	}

	public boolean isCellEmpty(int x, int y) {
		return (matriz[x][y] == null)
				|| (celulaPreenchimento.equals(matriz[x][y]));
	}

	public int getUltimaColuna() {
		return largura - 1;
	}

	public int getUltimaLinha() {
		return altura - 1;
	}

	@Override
	public void colocar(PalavraPosicionada palavra) {
		for (int i = 0; i < palavra.getCelulas().length; i++) {
			if (i == 0) {
				setCelula(palavra.getColunaInicio(), palavra
						.getLinhaInicio(), palavra);
			} else if (palavra.getDisposicao().EHorizontal()) {
				setCelula(palavra.getColunaInicio() + i, palavra
						.getLinhaInicio(), palavra.getCelulas()[i]);
			} else {
				setCelula(palavra.getColunaInicio(), palavra.getLinhaInicio()
						+ i, palavra.getCelulas()[i]);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		for (int y = 0; y <= getUltimaLinha(); y++) {
			for (int x = 0; x <= getUltimaColuna(); x++) {

				s.append(getCelula(x, y).toChar());
			}
			s.append('\n');
		}

		return s.toString();
	}

	private static class CelulaPreenchimento implements CelulaPalavraCruzada {
		public String toString() {
			return " ";
		}
		
		@Override
		public boolean equals(Object o) {
			return (o == this) || (o == null);
		}

		@Override
		public Character toChar() {
			return ' ';
		}
	}
	
	public static CelulaPreenchimento celulaPreenchimento = new CelulaPreenchimento();

	@Override
	public int getAltura() {
		return altura;
	}

	@Override
	public int getLargura() {
		return largura;
	}
}