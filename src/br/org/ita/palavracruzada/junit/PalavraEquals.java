package br.org.ita.palavracruzada.junit;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.org.ita.palavracruzada.Palavra;
import br.org.ita.palavracruzada.PalavraPosicionada;

public class PalavraEquals {
	
	private Palavra base;

	@Before
	public void setUp() throws Exception {
		base = new Palavra("Palavra", "Dica da palavra");
	}
	
	@Test
	public void testDicaDiferente() {
		Palavra teste = new Palavra("Palavra", "Dica diferente");
		
		assertFalse(teste.equals(base));
		assertFalse(base.equals(teste));
	}
	
	@Test
	public void testPalavraDiferente() {
		Palavra teste = new Palavra("Diferente", "Dica da palavra");
		
		assertFalse(teste.equals(base));
		assertFalse(base.equals(teste));
	}
	
	@Test
	public void testPalavraNaMatrizEquals() {
		PalavraPosicionada teste = new PalavraPosicionada("Palavra", "Dica da palavra");
		
		assertTrue(teste.equals(base));
		assertTrue(base.equals(teste));
	}
	
	@Test
	public void testEqualsOk() {
		Palavra teste = new Palavra("Palavra", "Dica da palavra");
		
		assertTrue(teste.equals(base));
		assertTrue(base.equals(teste));
	}

}
