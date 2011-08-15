package br.org.ita.palavracruzada.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.palavracruzada.Cruzamento;
import br.org.ita.palavracruzada.PalavraPosicionada;

public class CruzamentoTest {

	PalavraPosicionada base = new PalavraPosicionada(new StringBuilder("aviao"), new StringBuilder("Dica do aviao"));
	PalavraPosicionada teste = new PalavraPosicionada(new StringBuilder("casa"), new StringBuilder("Dica da casa"));


	Cruzamento c = new Cruzamento();

	@Test
	public void test1PreencherPalavraTeste() {

		// Primeiro teste

		/*
		 * colunaInicio e linhaInicio são as coordenadas do "c" da casa na
		 * matriz
		 */
		c.setIndiceBase(0); // O primeiro "a" do aviao
		c.setIndiceTeste(1); // O primeiro "a" da casa

		base.setDisposicao(Disposicao.HORIZONTAL);
		base.setColunaInicio(1);
		base.setLinhaInicio(3);

		// .012345
		// 0
		// 1..#
		// 2..c
		// 3.#aviao
		// 4..s
		// 5..a

		// palavra base = aviao
		// palavra teste = casa
		
		// Obs: Só coloquei "." antes das palavras pois se usar ctrl+F no eclipse tira os espaços, # representa as dicas

		c.preencherPalavraTeste(base, teste);

		assertEquals(Disposicao.VERTICAL, teste.getDisposicao());

		assertEquals(2, teste.getColunaInicio());
		assertEquals(2, teste.getColunaFim());

		assertEquals(1, teste.getLinhaInicio());
		assertEquals(5, teste.getLinhaFim());
	}
	
	@Test
	public void test2PreencherPalavraTeste() {
		// Segundo teste
		
		/*
		 * colunaInicio e linhaInicio são as coordenadas do "c" da casa na
		 * matriz
		 */
		c.setIndiceBase(3); // o segundo "a" do aviao
		c.setIndiceTeste(3); // o segundo "a" da casa

		/*
		 * colunaInicio e linhaInicio são as coordenadas do "c" da casa na
		 * matriz
		 */
		base.setColunaInicio(5);
		base.setLinhaInicio(1);
		base.setDisposicao(Disposicao.VERTICAL);

		// .0123456
		// 0
		// 1.....#
		// 2.....a
		// 3.....v
		// 4.....i
		// 5.#casa
		// 6.....o

		// palavra base = aviao
		// palavra teste = casa
		
		// Obs: Só coloquei "." antes das palavras pois se usar ctrl+F no eclipse tira os espaços, # representam as dicas

		c.preencherPalavraTeste(base, teste);

		assertEquals(Disposicao.HORIZONTAL, teste.getDisposicao());

		assertEquals(1, teste.getColunaInicio());
		assertEquals(5, teste.getColunaFim());

		assertEquals(5, teste.getLinhaInicio());
		assertEquals(5, teste.getLinhaFim());
	}
}
