package br.org.ita.palavracruzada;

public class Caractere implements CelulaPalavraCruzada {
	private Character ch;
	
	public Caractere(Character ch) {
		this.ch = new Character(ch);
	}
	
	public String toString() {
		return ch.toString();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Caractere)) {
			return false;
		}
		
		Caractere c = (Caractere)o;
		
		return ch.equals(c.ch);
	}

	@Override
	public Character toChar() {
		return ch;
	}
}
