package br.org.ita.numerox;

import br.org.ita.jogoscruzadoscomum.Celula;

public class CelulaNumerox implements Celula {
	
	private char ch;
	private NumeroPosicionado numero;
	private Cruzamento cruzamento;
	private boolean primeiraPalavra;
	
	CelulaNumerox() {
		super();
	}
	
	public Character getChar() {
		return ch;
	}
	
	void setChar(Character ch) {
		this.ch = ch;
	}
	
	@Override
	public String toString() {
		return getChar().toString();
	}
	
	public boolean isVazia() {
		// TODO: Mover esse método para a interface br.org.ita.jogoscruzadoscomum.Celula
		return ch == ' ';
	}
	
	public NumeroPosicionado getNumero() {
		return numero;
	}
	
	void setNumero(NumeroPosicionado numero) {
		this.numero = numero;
	}
	
	public Cruzamento getCruzamento() {
		return cruzamento;
	}
	
	void setCruzamento(Cruzamento cruzamento) {
		this.cruzamento = cruzamento;
	}
	
	public boolean isPrimeiraPalavra() {
		return primeiraPalavra;
	}
	
	void setPrimeiraPalavra(boolean primeiraPalavra) {
		this.primeiraPalavra = primeiraPalavra;
	}
}
