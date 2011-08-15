package br.org.ita.labirinto.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.org.ita.labirinto.Labirinto;

public class CasaTest {
	Labirinto labirinto;

	@Before
	public void setUp() throws Exception {
		labirinto = new Labirinto(2, 2);
	}

	@Test
	public void testCaminhoDireita() {
		labirinto.getCasa(0, 0).setCaminhoDireita(true);
		
		assertTrue(labirinto.getCasa(0, 0).isCaminhoDireita());
		
		labirinto.getCasa(1, 0).setCaminhoDireita(true);
		
		assertFalse(labirinto.getCasa(1, 0).isCaminhoDireita());
	}
	
	@Test
	public void testCaminhoEsquerda() {
		labirinto.getCasa(1, 0).setCaminhoEsquerda(true);
		
		assertTrue(labirinto.getCasa(1, 0).isCaminhoEsquerda());
		
		labirinto.getCasa(0, 0).setCaminhoEsquerda(true);
		
		assertFalse(labirinto.getCasa(0, 0).isCaminhoEsquerda());
	}

	@Test
	public void testCaminhoAbaixo() {
		labirinto.getCasa(0, 0).setCaminhoAbaixo(true);
		
		assertTrue(labirinto.getCasa(0, 0).isCaminhoAbaixo());
		
		labirinto.getCasa(0, 1).setCaminhoAbaixo(true);
		
		assertFalse(labirinto.getCasa(0, 1).isCaminhoAbaixo());
	}

	@Test
	public void testCaminhoAcima() {
		labirinto.getCasa(0, 1).setCaminhoAcima(true);
		
		assertTrue(labirinto.getCasa(0, 1).isCaminhoAcima());
		
		labirinto.getCasa(0, 0).setCaminhoAcima(true);
		
		assertFalse(labirinto.getCasa(0, 0).isCaminhoAcima());
	}
	
	@Test
	public void testOcupado() {
		Labirinto.Casa casa = labirinto.getCasa(0, 0);
		
		assertFalse(casa.isOcupado());
		
		casa.ocupar();
		
		assertTrue(casa.isOcupado());
		
		
	}

}
