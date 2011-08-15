package br.org.ita.passatempo;

import br.org.ita.labirinto.GeradorDemo;
import br.org.ita.labirinto.Labirinto;

public class MainLabirinto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Labirinto labirinto = new GeradorDemo().gerar();
		
		System.out.println(labirinto.toString());
	}

}
