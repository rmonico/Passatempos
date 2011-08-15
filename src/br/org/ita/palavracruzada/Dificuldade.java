package br.org.ita.palavracruzada;

public enum Dificuldade {
	FACIL(0), MEDIO(1), DIFICIL(2);
	
	private int nivel;
	
	private Dificuldade(int nivel) {
		this.nivel = nivel;
	} 
	
	public int getNivel() {
		return nivel;
	}
}
