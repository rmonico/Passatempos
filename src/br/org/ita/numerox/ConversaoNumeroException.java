package br.org.ita.numerox;


public class ConversaoNumeroException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -624284532618995285L;

	public ConversaoNumeroException(StringBuilder numero) {
		super("Erro convertendo número: " + numero);
	}
}
