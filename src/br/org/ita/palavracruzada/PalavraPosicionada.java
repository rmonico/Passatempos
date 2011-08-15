package br.org.ita.palavracruzada;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.jogoscruzadoscomum.SequenciaPosicionada;

/**
 * Representa uma palavra colocada na matriz
 * */
public class PalavraPosicionada extends Palavra implements Cloneable, CelulaPalavraCruzada, SequenciaPosicionada<CelulaPalavraCruzada> {

	private Disposicao disposicao;
	private int coluna;
	private int linha;
	
	public PalavraPosicionada(StringBuilder texto, StringBuilder textoDica) {
		super(texto, textoDica);
	}
	
	public PalavraPosicionada(String texto, String textoDica) {
		this(new StringBuilder(texto), new StringBuilder(textoDica));
	}

	public PalavraPosicionada(Palavra palavra) {
		this(palavra.getTextoAsStringBuilder(), new StringBuilder(palavra.getDica().toString()));
	}


	public Disposicao getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(Disposicao disposicao) {
		this.disposicao = disposicao;
	}

	public int getColunaInicio() {
		return coluna;
	}

	public void setColunaInicio(int coluna) {
		this.coluna = coluna;
	}

	public int getColunaFim() {
		if (disposicao == Disposicao.HORIZONTAL) {
			return coluna + getCelulas().length - 1;
		} else {
			return coluna;
		}
	}

	public int getLinhaInicio() {
		return linha;
	}

	public void setLinhaInicio(int linha) {
		this.linha = linha;
	}

	public int getLinhaFim() {
		if (disposicao == Disposicao.VERTICAL) {
			return linha + getCelulas().length - 1;
		} else {
			return linha;
		}
	}

	public int getLargura() {
		return (disposicao == Disposicao.HORIZONTAL ? getCelulas().length : 1);
	}

	public int getAltura() {
		return (disposicao == Disposicao.VERTICAL ? getCelulas().length : 1);
	}
	
	@Override
	public PalavraPosicionada clone() {
		try {
			return (PalavraPosicionada) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public Character toChar() {
		return '#';
	}
	
}
 