package br.org.ita.palavracruzada.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.palavracruzada.Caractere;
import br.org.ita.palavracruzada.MatrizPalavraCruzada;
import br.org.ita.palavracruzada.PalavraPosicionada;

public class PalavraNaMatrizTest {

	PalavraPosicionada p;

	@Before
	public void setUp() throws Exception {
		p = new PalavraPosicionada(new StringBuilder("abcd"), new StringBuilder("Dica"));
		
		p.setDisposicao(Disposicao.HORIZONTAL);
		p.setColunaInicio(3);
		p.setLinhaInicio(4);
	}

	@Test
	public void testColocar() {
		p.setColunaInicio(1);
		p.setLinhaInicio(1);

		// Testa na horizontal
		MatrizPalavraCruzada m = new MatrizPalavraCruzada(6, 6);

		p.setDisposicao(Disposicao.HORIZONTAL);
		
		m.colocar(p);

		assertTrue(m.getCelula(0, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(1, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(0, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertFalse(m.getCelula(1, 1).equals(p.getDica()));
		assertTrue(m.getCelula(2, 1).equals(new Caractere('a')));
		assertTrue(m.getCelula(3, 1).equals(new Caractere('b')));
		assertTrue(m.getCelula(4, 1).equals(new Caractere('c')));
		assertTrue(m.getCelula(5, 1).equals(new Caractere('d')));

		assertTrue(m.getCelula(0, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(1, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(0, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(1, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(0, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(1, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(0, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(1, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));

		// Testa na vertical
		m = new MatrizPalavraCruzada(6, 6);

		p.setDisposicao(Disposicao.VERTICAL);
		m.colocar(p);

		assertTrue(m.getCelula(0, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(0, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(0, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(0, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(0, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(0, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(1, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertFalse(m.getCelula(1, 1).equals(p.getDica()));
		assertTrue(m.getCelula(1, 2).equals(new Caractere('a')));
		assertTrue(m.getCelula(1, 3).equals(new Caractere('b')));
		assertTrue(m.getCelula(1, 4).equals(new Caractere('c')));
		assertTrue(m.getCelula(1, 5).equals(new Caractere('d')));

		assertTrue(m.getCelula(2, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(2, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(3, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(3, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(4, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(4, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));

		assertTrue(m.getCelula(5, 0).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 1).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 2).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 3).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 4).equals(MatrizPalavraCruzada.celulaPreenchimento));
		assertTrue(m.getCelula(5, 5).equals(MatrizPalavraCruzada.celulaPreenchimento));
}

	@Test
	public void testGetColunaInicio() {
		assertEquals(3, p.getColunaInicio());
	}

	@Test
	public void testGetColunaFim() {
		assertEquals(7, p.getColunaFim());

		p.setDisposicao(Disposicao.VERTICAL);

		assertEquals(3, p.getColunaFim());
	}

	@Test
	public void testGetLinhaInicio() {
		assertEquals(4, p.getLinhaInicio());
	}

	@Test
	public void testGetLinhaFim() {
		assertEquals(4, p.getLinhaFim());

		p.setDisposicao(Disposicao.VERTICAL);
		assertEquals(8, p.getLinhaFim());
	}

	@Test
	public void testGetLargura() {
		assertEquals(5, p.getLargura());

		p.setDisposicao(Disposicao.VERTICAL);

		assertEquals(1, p.getLargura());
	}

	@Test
	public void testGetAltura() {
		assertEquals(1, p.getAltura());

		p.setDisposicao(Disposicao.VERTICAL);

		assertEquals(5, p.getAltura());
	}

}
