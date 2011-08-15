package br.org.ita.jogoscruzadoscomum;



public enum Disposicao {
	HORIZONTAL, VERTICAL;

	public Disposicao getDisposicaoAlternativa() {
		return this == HORIZONTAL ? VERTICAL : HORIZONTAL;
	}
	
	public static Disposicao getAleatorio() {
		return values()[MyRandom.global().randomInRange(0, values().length-1)];
	}
	
	public boolean EHorizontal() {
		return equals(HORIZONTAL);
	}
}
