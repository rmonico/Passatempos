package br.org.ita.palavracruzada;

public class Correcao {
	private Character resposta;
	private Character correto;
	private boolean ignorar;

	public Character getResposta() {
		return resposta;
	}

	public void setResposta(Character resposta) {
		this.resposta = resposta;
	}

	public Character getCorreto() {
		return correto;
	}

	public void setCorreto(Character correto) {
		this.correto = correto;
	}

	public boolean isIgnorar() {
		return ignorar;
	}

	public void setIgnorar(boolean ignorar) {
		this.ignorar = ignorar;
	}

	public boolean isOk() {
		if (resposta != null) {
			return resposta.equals(correto);
		} else {
			return correto == null;
		}
	}
}
