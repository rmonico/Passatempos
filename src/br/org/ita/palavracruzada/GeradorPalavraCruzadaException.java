package br.org.ita.palavracruzada;

import br.org.ita.jogoscruzadoscomum.GeradorMatrizException;

public class GeradorPalavraCruzadaException extends GeradorMatrizException {
	
	public enum Motivo {
		LARGURA_INVALIDA("Largura inválida, deve ser um valor positivo."), ALTURA_INVALIDA("Altura da matriz inválida, deve ser um valor positivo.");
		
		private String descricao; 
		
		private Motivo(String descricao) {
			this.descricao = descricao;
		}
		
		@Override
		public String toString() {
			return descricao;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5384806405141585823L;

	public GeradorPalavraCruzadaException(Motivo motivo) {
		super(motivo.toString());
	}
}
