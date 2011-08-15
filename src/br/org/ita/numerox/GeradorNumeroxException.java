package br.org.ita.numerox;

import br.org.ita.jogoscruzadoscomum.GeradorMatrizException;

public class GeradorNumeroxException extends GeradorMatrizException {

	public enum Motivo {
		MINIMO_ALGARISMOS_INVALIDO(
				"A quantidade mínima de algarismos é inválida, deve estar entre 3 e a quantidade máxima de algarismos."), LARGURA_INVALIDA(
				"Valor de largura inválida, deve ser um valor positivo."), ALTURA_INVALIDA(
				"Valor de altura inválido, deve ser um valor positivo."), MAXIMO_ALGARISMOS_INVALIDO(
				"Quantidade máxima de algarismos inválida, deve estar entre o mínimo e a menor dimensão da matriz.");

		private String descricao;

		private Motivo(String descricao) {
			this.descricao = descricao;
		}

		public String toString() {
			return descricao;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6947940980200739369L;
	private Motivo motivo;

	public GeradorNumeroxException(Motivo motivo) {
		super(motivo.toString());
		this.motivo = motivo;
		
	}

	public Motivo getMotivo() {
		return motivo;
	}
}
