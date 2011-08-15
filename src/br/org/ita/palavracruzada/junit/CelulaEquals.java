package br.org.ita.palavracruzada.junit;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.org.ita.palavracruzada.Caractere;
import br.org.ita.palavracruzada.Dica;

public class CelulaEquals {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCaractereNotEquals() {
		Caractere base = new Caractere('x');
		Caractere teste = new Caractere('a');
		
		assertFalse(base.equals(teste));
		assertFalse(teste.equals(base));
	}
	
	@Test
	public void testCaractereEquals() {
		Caractere base = new Caractere('x');
		Caractere teste = new Caractere('x');
		
		assertTrue(base.equals(teste));
		assertTrue(teste.equals(base));
	}
	
	@Test 
	public void testDicaNotEquals() {
		Dica base = new Dica("Dica base");
		Dica teste = new Dica("Dica teste");
		
		assertFalse(base.equals(teste));
		assertFalse(teste.equals(base));
	}
	
	@Test
	public void testDicaEquals() {
		Dica base = new Dica("Dica base");
		Dica teste = new Dica("Dica base");
		
		assertTrue(base.equals(teste));
		assertTrue(teste.equals(base));
	}
}