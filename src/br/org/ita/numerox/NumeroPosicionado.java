package br.org.ita.numerox;

import br.org.ita.jogoscruzadoscomum.Coordenada;
import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.jogoscruzadoscomum.MyRandom;
import br.org.ita.jogoscruzadoscomum.SequenciaPosicionada;

public class NumeroPosicionado implements SequenciaPosicionada<CelulaNumerox> {

	private CelulaNumerox[] celulas;
	private MatrizNumerox matriz;
	private Disposicao disposicao = Disposicao.HORIZONTAL;
	private int coluna;
	private int linha;
	private int tamanho;
	private boolean parametrosMudados = false;
	private boolean primeiraPalavra;
	
	NumeroPosicionado(MatrizNumerox matriz) {
		super();

		this.matriz = matriz;
	}

	public Disposicao getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(Disposicao disposicao) {
		if (disposicao == this.disposicao) {
			return;
		}
		
		this.disposicao = disposicao;
		parametrosMudados = true;
		
		assert (disposicao.EHorizontal() ? (getColunaInicio() + getTamanho() - 1 <= matriz.getUltimaColuna()) : (getLinhaInicio()
				+ getTamanho() - 1 <= matriz.getUltimaLinha())) : "Valor inválido de coluna: " + getColunaInicio()
				+ " (tamanho: " + getTamanho() + ")";
	}

	public int getColunaInicio() {
		return coluna;
	}

	public void setColunaInicio(int coluna) {
		if (coluna == getColunaInicio()) {
			return;
		}
		
		this.coluna = coluna;
		parametrosMudados = true;
		
		assert (disposicao.EHorizontal() ? (getColunaInicio() + getTamanho() - 1 <= matriz.getUltimaColuna()) : (getColunaInicio()
				+ getTamanho() - 1 <= matriz.getUltimaLinha())) : "Valor inválido de coluna: " + getColunaInicio()
				+ " (tamanho: " + getTamanho() + ")";
	}

	public int getColunaFim() {
		return getDisposicao().EHorizontal() ? coluna + tamanho - 1 : coluna;
	}

	public int getLinhaInicio() {
		return linha;
	}

	public void setLinhaInicio(int linha) {
		
		if (linha == getLinhaInicio()) {
			return;
		}
		
		this.linha = linha;
		parametrosMudados = true;
		
		assert (disposicao.EHorizontal() ? (getColunaInicio() + getTamanho()-1 <= matriz.getUltimaColuna()) : (getLinhaInicio()
				+ getTamanho()-1 <= matriz.getUltimaLinha())) : "Valor inválido de linha: " + getLinhaInicio()
				+ " (tamanho: " + getTamanho() + ")";
	}

	public int getLinhaFim() {
		return getDisposicao().EHorizontal() ? linha : linha + tamanho - 1;
	}

	public int getLargura() {
		return (disposicao.EHorizontal() ? getTamanho() : 1);
	}

	public int getAltura() {
		return (!disposicao.EHorizontal() ? getTamanho() : 1);
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		
		if (tamanho == getTamanho()) {
			return;
		}

		this.tamanho = tamanho;
		parametrosMudados = true;

		assert (getDisposicao().EHorizontal() ? (getColunaInicio() + getTamanho() - 1 <= matriz.getUltimaColuna())
				: (getLinhaInicio() + getTamanho() - 1 <= matriz.getUltimaLinha())) : "Valor inválido de tamanho: "
				+ getTamanho() + " ("
				+ (getDisposicao().EHorizontal() ? "coluna" : "linha") + ": "
				+ (getDisposicao().EHorizontal() ? getColunaInicio() : getLinhaInicio()) + ")";
	}

	@Override
	public CelulaNumerox[] getCelulas() {
		if (celulas == null || parametrosMudados) {
			celulas = atualizarCelulas();
		}

		return celulas;
	}

	CelulaNumerox[] atualizarCelulas() {
		celulas = new CelulaNumerox[getTamanho()];

		for (int i = 0; i < getTamanho(); i++) {
			if (getDisposicao().EHorizontal()) {
				assert(getColunaInicio() + i > 0 && getColunaInicio() + i - 1 <= matriz.getUltimaColuna());
				
				celulas[i] = matriz.getCelula(getColunaInicio() + i, getLinhaInicio());
			} else {
				assert(getLinhaInicio() + i > 0 && getLinhaInicio() + i - 1 <= matriz.getUltimaColuna());
				
				celulas[i] = matriz.getCelula(getColunaInicio(), getLinhaInicio() + i);
			}
			celulas[i].setNumero(this);
			celulas[i].setChar(Integer.toString(
					MyRandom.global().randomInRange(0, 9)).charAt(0));
		}
		
		parametrosMudados = false;

		return celulas;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		for (CelulaNumerox celula : celulas) {
			s.append((celula == null ? ' ' : celula.toString()));
		}

		return s.toString();
	}
	
	public Coordenada getCoordenadaInicio() {
		return new Coordenada(getColunaInicio(), getLinhaInicio());
	}
	
	public Coordenada getCoordenadaFim() {
		return new Coordenada(getColunaFim(), getLinhaFim());
	}
	
	public boolean isPrimeiraPalavra() {
		return primeiraPalavra;
	}
	
	public void setPrimeiraPalavra() {
		this.primeiraPalavra = true;
		
		for (CelulaNumerox celula : celulas) {
			celula.setPrimeiraPalavra(true);
		}
	}
}
