package br.org.ita.numerox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.Disposicao;

public class TestIsCruzamentoValido {

	private GeradorNumerox gerador;
	NumeroPosicionado numeroFixo;
	NumeroPosicionado numeroTeste;
	Cruzamento cruzamento;

	@Before
	public void setUp() throws Exception {
		gerador = new GeradorNumerox();

		gerador.setAltura(20);
		gerador.setLargura(20);
		gerador.setMaximoAlgarismos(12);
		gerador.setMinimoAlgarismos(5);

		gerador.prepararMatriz();

		numeroFixo = gerador.NumeroPosicionadoFactory();

		numeroFixo.setColunaInicio(3);
		numeroFixo.setLinhaInicio(3);
		numeroFixo.setDisposicao(Disposicao.HORIZONTAL);
		numeroFixo.setTamanho(5);

		numeroTeste = gerador.NumeroPosicionadoFactory();
		numeroTeste.setTamanho(5);

		cruzamento = new Cruzamento(gerador);

		cruzamento.getNumeroFixo().setNumero(numeroFixo);
		cruzamento.getNumeroFixo().setLocalCruzamento(0);

		cruzamento.getNumeroTeste().setNumero(numeroTeste);
		cruzamento.getNumeroTeste().setLocalCruzamento(2);

		cruzamento.atualizarNumeroTeste();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCruzamentoValido() {
		assertTrue(cruzamento.isValido(gerador));
	}

	/**
	 * Os números devem se cruzar
	 */
	@Test
	public void testNumerosNaoCruzam() {
		numeroTeste.setColunaInicio(1);

		assertFalse(cruzamento.isValido(gerador));
	}

	// O numeroTeste fica com linhaInicio = -1
	@Test
	public void testNumeroTesteForaMatriz1() {
		cruzamento.getNumeroTeste().setLocalCruzamento(4);

		cruzamento.atualizarNumeroTeste();

		assertFalse(cruzamento.isValido(gerador));
	}

	// O numeroTeste fica com linhaFinal = 20
	@Test
	public void testNumeroTesteForaMatriz2() {
		numeroFixo.setLinhaInicio(18);

		cruzamento.atualizarNumeroTeste();

		assertFalse(cruzamento.isValido(gerador));
	}

	// O numeroTeste fica com colunaInicio = -1
	@Test
	public void testNumeroTesteForaMatriz3() {
		numeroFixo.setDisposicao(Disposicao.VERTICAL);
		cruzamento.getNumeroTeste().setLocalCruzamento(4);

		cruzamento.atualizarNumeroTeste();

		assertFalse(cruzamento.isValido(gerador));
	}

	// O numeroTeste fica com colunaFinal = 20
	@Test
	public void testNumeroTesteForaMatriz4() {
		numeroFixo.setDisposicao(Disposicao.VERTICAL);
		numeroFixo.setColunaInicio(18);

		cruzamento.atualizarNumeroTeste();

		assertFalse(cruzamento.isValido(gerador));
	}

	// Se encontrar outra célula no caminho, e a palavra dessa célula tiver a
	// mesma disposicao do numeroTeste, não deve permitir o cruzamento
	@Test
	public void testCelulaNoCaminho() {
		NumeroPosicionado outroNumero = new NumeroPosicionado(gerador.matriz);
		outroNumero.setDisposicao(Disposicao.VERTICAL);

		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');
		celula.setNumero(outroNumero);

		gerador.matriz.setCelula(3, 5, celula);

		assertFalse(cruzamento.isValido(gerador));
	}

	// Se encontrar outra célula no caminho, e a palavra dessa célula tiver a
	// disposicao diferente do numeroTeste e nenhum cruzamento, DEVE permitir o
	// cruzamento
	@Test
	public void testCelulaNoCaminho2() {
		NumeroPosicionado outroNumero = new NumeroPosicionado(gerador.matriz);
		outroNumero.setDisposicao(Disposicao.HORIZONTAL);

		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');
		celula.setNumero(outroNumero);

		gerador.matriz.setCelula(3, 5, celula);

		assertTrue(cruzamento.isValido(gerador));
	}

	// Se encontrar outra célula no caminho, e a palavra dessa célula tiver a
	// disposicao diferente do numeroTeste e outros cruzamentos, não deve
	// permitir o
	// cruzamento
	@Test
	public void testCelulaNoCaminho3() {
		NumeroPosicionado outroNumero = new NumeroPosicionado(gerador.matriz);
		outroNumero.setDisposicao(Disposicao.HORIZONTAL);

		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');
		celula.setNumero(outroNumero);
		celula.setCruzamento(new Cruzamento(gerador));

		gerador.matriz.setCelula(3, 5, celula);

		assertFalse(cruzamento.isValido(gerador));
	}

	// Se alguma das células vizinhas as que serão ocupadas pelo número de teste
	// estiverem ocupadas, não permitir o cruzamento
	@Test
	public void testCelulaNoCaminho4() {
		NumeroPosicionado outroNumero = new NumeroPosicionado(gerador.matriz);
		outroNumero.setDisposicao(Disposicao.HORIZONTAL);

		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');
		celula.setNumero(outroNumero);

		gerador.matriz.setCelula(2, 5, celula);

		assertFalse(cruzamento.isValido(gerador));
	}

	// A célula que fica antes do número teste não pode estar ocupada
	@Test
	public void testCelulaNoCaminho5() {
		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');

		gerador.matriz.setCelula(3, 0, celula);

		assertFalse(cruzamento.isValido(gerador));
	}

	// A célula que fica depois do numeroTeste não pode estar ocupada
	@Test
	public void testCelulaNoCaminho6() {
		CelulaNumerox celula = new CelulaNumerox();

		celula.setChar('1');

		gerador.matriz.setCelula(3, 6, celula);

		assertFalse(cruzamento.isValido(gerador));
	}

	@Test
	public void testAdicionarCruzamentoNaMatriz() {
		numeroFixo.atualizarCelulas();
		gerador.adicionarCruzamentoNaMatriz(cruzamento);
		
		// A primeira e a última coluna e linhas devem estar vazias
		for (int x=0; x<7; x++) {
			assertTrue(gerador.matriz.getCelula(x+2, 0).isVazia());
			assertTrue(gerador.matriz.getCelula(x+2, 6).isVazia());
			
			assertTrue(gerador.matriz.getCelula(2, x).isVazia());
			assertTrue(gerador.matriz.getCelula(8, x).isVazia());			
		}
		
		// Verifica demais células que devem estar vazias
		for (int x=0; x<4; x++) {
			assertTrue(gerador.matriz.getCelula(x+4, 1).isVazia());
			assertTrue(gerador.matriz.getCelula(x+4, 2).isVazia());
			
			assertTrue(gerador.matriz.getCelula(x+4, 4).isVazia());
			assertTrue(gerador.matriz.getCelula(x+4, 5).isVazia());			
		}
		
		assertFalse(gerador.matriz.getCelula(3, 1).isVazia());
		assertFalse(gerador.matriz.getCelula(3, 2).isVazia());
		assertFalse(gerador.matriz.getCelula(3, 3).isVazia());
		assertFalse(gerador.matriz.getCelula(3, 4).isVazia());
		assertFalse(gerador.matriz.getCelula(3, 5).isVazia());
		assertFalse(gerador.matriz.getCelula(4, 3).isVazia());
		assertFalse(gerador.matriz.getCelula(5, 3).isVazia());
		assertFalse(gerador.matriz.getCelula(6, 3).isVazia());
		assertFalse(gerador.matriz.getCelula(7, 3).isVazia());
	}
}
