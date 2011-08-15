package br.org.ita.palavracruzada.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.palavracruzada.Caractere;
import br.org.ita.palavracruzada.Cruzamento;
import br.org.ita.palavracruzada.MatrizPalavraCruzada;
import br.org.ita.palavracruzada.PalavraPosicionada;

public class CruzamentoEValidoTest {

	private PalavraPosicionada base;
	private PalavraPosicionada teste;

	Cruzamento c;

	MatrizPalavraCruzada m;

	@Before
	public void setUp() throws Exception {
		base = new PalavraPosicionada("aviao", "Dica do aviao");
		/*
		 * colunaInicio e linhaInicio são as coordenadas da primeira letra do
		 * "aviao" na matriz
		 */
		base.setColunaInicio(5);
		base.setLinhaInicio(1);
		base.setDisposicao(Disposicao.VERTICAL);
		
		teste = new PalavraPosicionada("casa", "Dica da casa");
		
		c = new Cruzamento();
		c.setIndiceBase(3); // o segundo "a" do aviao
		c.setIndiceTeste(3); // o segundo "a" da casa
		
		// Situação em teste:

		// .0123456789
		// 0
		// 1.....#
		// 3.....a
		// 4.....v
		// 5.....i
		// 6.#casa
		// 7.....o
		// 8
		// 9

		// palavra base = aviao
		// palavra teste = casa

		// Obs: Só coloquei "." antes das palavras pois se usar ctrl+F no
		// eclipse tira os espaços, # representa as dicas
		
		m = new MatrizPalavraCruzada(10, 10);
	}

	@Test
	public void testPalavraDentroMatriz() {
		// Situação em teste:

		// .0123456789
		// 0
		// 1.....#
		// 3.....a
		// 4.....v
		// 5.....i
		// 6...#casa
		// 7.....o
		// 8
		// 9

		// palavra base = aviao
		// palavra teste = casa

		// Obs: Só coloquei "." antes das palavras pois se usar ctrl+F no
		// eclipse tira os espaços, # representa as dicas
		
		m = new MatrizPalavraCruzada(10, 10);
		m.colocar(base);

		c.setIndiceTeste(1);
		c.preencherPalavraTeste(base, teste);

		// A palavra de teste deverá estar totalmente dentro da matriz (nesse
		// teste a palavra fica colada na lateral direita da matriz)
		assertTrue(c.eValido(base, teste, m));
		
		
		// Situação em teste:

		// .0123456789
		// 0
		// 1..#
		// 3..a
		// 4..v
		// 5..i
		// 6#casa
		// 7..o
		// 8
		// 9
		
		base.setColunaInicio(2);
		m = new MatrizPalavraCruzada(10, 10);
		m.colocar(base);

		c.setIndiceTeste(1);
		c.preencherPalavraTeste(base, teste);

		// A palavra de teste deverá estar totalmente dentro da matriz (nesse
		// teste a palavra fica colada na lateral esquerda da matriz)
		assertTrue(c.eValido(base, teste, m));
		
		
		// Situação em teste:

		// .0123456789
		// 0
		// 1.#
		// 3.a
		// 4.v
		// 5.i
		// #casa
		// 7.o
		// 8
		// 9
		
		base.setColunaInicio(1);
		m = new MatrizPalavraCruzada(10, 10);
		m.colocar(base);

		c.setIndiceTeste(1);
		c.preencherPalavraTeste(base, teste);

		// A palavra de teste deverá estar totalmente dentro da matriz (nesse
		// teste a palavra fica sai uma célula para a esquerda)
		assertFalse(c.eValido(base, teste, m));
		
		
		// Situação em teste:

		// .012345
		// 0
		// 1....#
		// 3....a
		// 4....v
		// 5....i
		// 6..#casa
		// 7....o
		// 8
		// 9
		
		base.setColunaInicio(4);
		m = new MatrizPalavraCruzada(6, 10);
		m.colocar(base);

		c.setIndiceTeste(1);
		c.preencherPalavraTeste(base, teste);

		// A palavra de teste deverá estar totalmente dentro da matriz (nesse
		// teste a palavra fica sai uma célula para a direita)
		assertFalse(c.eValido(base, teste, m));
	}
	
	@Test
	public void testCaracterOcupadoAntesPalavra() {
		// Situação em teste:

		// .0123456789
		// 0
		// 1.....#
		// 2.....a
		// 3.....v
		// 4.....i
		// 5x....a
		// 6.....o
		// 7
		// 8

		// palavra base = aviao
		// palavra teste = casa
		
		m.colocar(base);
		m.setCelula(0, 5, new Caractere('x'));
		// Um caracter antes da palavra não pode estar ocupado
		assertFalse(c.eValido(base, teste, m));
	}

	@Test
	public void testCaracterOcupadoDepoisPalavra() {
		// Situação em teste:

		// .0123456789
		// 0
		// 1.....#
		// 2.....a
		// 3.....v
		// 4.....i
		// 5.....ax
		// 6.....o
		// 7
		// 8

		// palavra base = aviao
		// palavra teste = casa
		
		m.colocar(base);
		m.setCelula(6, 5, new Caractere('x'));
		// Um caracter depois da palavra não pode estar ocupado
		assertFalse(c.eValido(base, teste, m));
	}
	
	@Test
	public void testCaracteresOcupados() {
		// Situação em teste:

		// .012345
		// 0
		// 1....a
		// 2....v
		// 3....i
		// 4.x..a
		// 5....o
		// 6

		// palavra base = aviao
		// palavra teste = casa
		
		m.colocar(base);
		m.setCelula(1, 4, new Caractere('x'));
		c.preencherPalavraTeste(base, teste);

		// Nenhum dos caracteres que serão ocupados pela palavra de teste não poderão estar ocupados
		assertFalse(c.eValido(base, teste, m));
	}
	
	@Test
	public void testCaracteresVizinhosOcupados() {
		// Situação em teste:

		// .0123456789
		// 0
		// 1.....#
		// 2.....a
		// 3.....v
		// 4...x.i
		// 5.....a
		// 6.....o
		// 7
		// 8

		// palavra base = aviao
		// palavra teste = casa
		
		m.colocar(base);
		m.setCelula(3, 4, new Caractere('x'));

		// Nenhum dos caracteres vizinhos aos que serão ocupados pela palavra de teste não poderão estar ocupados
		assertFalse(c.eValido(base, teste, m));
	}
	
	@Test
	public void testCaractereDeCruzamentoInvalido() {
		// Situação em teste:

		// .012345
		// 0
		// 1....a
		// 2....v
		// 3....i
		// 4..ca?a   --> vai "a" do avião ou "s" da casa?
		// 5....o
		// 6

		// palavra base = aviao
		// palavra teste = casa
		
		m.colocar(base);
		c.preencherPalavraTeste(base, teste);
		c.setIndiceTeste(2); // Indíce inválido

		// O caractere onde ocorrer o cruzamento deverá ser o mesmo na palavra teste e na palavra base
		assertFalse(c.eValido(base, teste, m));
	}

	@Test
	public void testEValido() {
		c.preencherPalavraTeste(base, teste);

		m.colocar(base);
		assertTrue(c.eValido(base, teste, m));
	}
}
