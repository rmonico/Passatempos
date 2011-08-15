package br.org.ita.numerox;

import br.org.ita.jogoscruzadoscomum.Coordenada;
import br.org.ita.jogoscruzadoscomum.Matriz;

public class MatrizNumerox implements Matriz<CelulaNumerox, NumeroPosicionado> {

	private int colunas;
	private int linhas;
	private CelulaNumerox[][] celulas;

	public MatrizNumerox(int colunas, int linhas) {
		this.colunas = colunas;
		this.linhas = linhas;

		celulas = new CelulaNumerox[colunas][linhas];
	}

	@Override
	public CelulaNumerox getCelula(int coluna, int linha) {
		if (celulas[coluna][linha] == null) {
			celulas[coluna][linha] = new CelulaNumerox();
			celulas[coluna][linha].setChar(' ');
		}

		return celulas[coluna][linha];
	}

	@Override
	public void setCelula(int coluna, int linha, CelulaNumerox celula) {
		celulas[coluna][linha] = celula;
	}

	@Override
	public int getUltimaColuna() {
		return colunas - 1;
	}

	@Override
	public int getUltimaLinha() {
		return linhas - 1;
	}

	@Override
	public int getLargura() {
		return colunas;
	}

	@Override
	public int getAltura() {
		return linhas;
	}

	@Override
	public void colocar(NumeroPosicionado sequencia) {
//		 TODO Tirar esse método daqui, pois se tornou inútil, já que todas as instâncias das células estão nessa classe
//		for (int i=0; i<sequencia.getCelulas().length; i++) {
//			if (sequencia.getDisposicao().EHorizontal()) {
//				setCelula(sequencia.getColunaInicio() + i, sequencia
//						.getLinhaInicio(), sequencia.getCelulas()[i]);
//			} else {
//				setCelula(sequencia.getColunaInicio(), sequencia
//						.getLinhaInicio()
//						+ i, sequencia.getCelulas()[i]);
//			}
//		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		for (int y = 0; y <= getUltimaLinha(); y++) {
			for (int x = 0; x <= getUltimaColuna(); x++) {
				s.append(getCelula(x, y));
			}
			s.append("\n");
		}

		return s.toString();
	}

	public boolean isCoordenadasDentroMatriz(Coordenada coordenada) {
		return coordenada.getColuna() >= 0
				&& coordenada.getColuna() <= getUltimaColuna()
				&& coordenada.getLinha() >= 0
				&& coordenada.getLinha() <= getUltimaLinha();
	}
}
