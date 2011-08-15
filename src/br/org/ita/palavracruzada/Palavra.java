package br.org.ita.palavracruzada;

import br.org.ita.jogoscruzadoscomum.Sequencia;

public class Palavra implements Sequencia<CelulaPalavraCruzada> {
	private Caractere texto[];
	private Dica dica;
	private CelulaPalavraCruzada[] celulas;

	public Palavra(StringBuilder texto, StringBuilder dica) {
		this.texto = convertFromString(texto);
		this.dica = new Dica(dica);
	}
	
	public Palavra(String texto, String dica) {
		this(new StringBuilder(texto), new StringBuilder(dica));
	}

	public Dica getDica() {
		return dica;
	}
	
	public Caractere[] getTexto() {
		return texto;
	}
	
	public CelulaPalavraCruzada[] getCelulas() {
		if (celulas == null) {
			celulas = new CelulaPalavraCruzada[texto.length + 1];
			
			celulas[0] = dica;
		
			for (int i=0; i<texto.length; i++) {
				celulas[i+1] = texto[i];
			}
		}
		
		return celulas;
	}


	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("");
		
		for (int i=0; i<texto.length; i++) {
			s.append(texto[i]);
		}
		
		return s.toString();
	}

	public StringBuilder getTextoAsStringBuilder() {
		StringBuilder s = new StringBuilder();
		
		for (Caractere ch : texto) {
			s.append(ch);
		}
		
		return s;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Palavra)) {
			return false;
		}
		
		CelulaPalavraCruzada[] teste = ((Palavra)o).getCelulas();
		
		if (teste.length != getCelulas().length) {
			return false;
		}
		
		CelulaPalavraCruzada testando;
		
		for (int i=0; i<teste.length; i++) {
			testando = teste[i];
			
			if (!testando.equals(celulas[i])) {
				return false;
			}
		}
		
		return true;
	}

	public static Caractere[] convertFromString(StringBuilder texto) {
		Caractere[] result = new Caractere[texto.length()];
		
		for (int i=0; i<texto.length(); i++) {
			result[i] = new Caractere(texto.charAt(i));
		}
		
		return result;
	}

}
