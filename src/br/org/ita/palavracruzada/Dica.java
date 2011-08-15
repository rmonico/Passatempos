package br.org.ita.palavracruzada;

public class Dica implements CelulaPalavraCruzada {
	
	private StringBuilder texto;
	
	public Dica(StringBuilder texto) {
		this.texto = new StringBuilder(texto);
	}
	
	public Dica(String texto) {
		this(new StringBuilder(texto));
	}

	
	@Override
	public String toString() {
		return texto.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Dica)) {
			return false;
		}
		
		StringBuilder dica = ((Dica)o).texto;
		
		if (dica.length() != texto.length()) {
			return false;
		}
		
		for (int i=0; i<dica.length(); i++) {
			if (dica.charAt(i) != texto.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}


	@Override
	public Character toChar() {
		return '#';
	}
}
