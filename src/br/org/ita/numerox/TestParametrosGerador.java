package br.org.ita.numerox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.GeradorMatrizException;

public class TestParametrosGerador {

	private GeradorNumerox gerador;

	/**
	 * Não é permitido passar uma valor de largura para o gerador menor ou igual
	 * a zero.
	 */
	@Test
	public void testLarguraNegativa() {
		gerador = new GeradorNumerox();

		gerador.setLargura(-1);
		gerador.setAltura(20);

		gerador.setMaximoAlgarismos(5);
		gerador.setMinimoAlgarismos(5);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Largura inválida.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.LARGURA_INVALIDA));

				return;
			}
		}

		fail("Largura inválida.");
	}

	/**
	 * Ver nota do testValidadeLargura.
	 */
	@Test
	public void testLarguraZero() {
		gerador = new GeradorNumerox();

		gerador.setLargura(0);
		gerador.setAltura(20);

		gerador.setMaximoAlgarismos(5);
		gerador.setMinimoAlgarismos(5);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Largura inválida.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.LARGURA_INVALIDA));

				return;
			}
		}

		fail("Largura inválida.");
	}

	/**
	 * Não é permitido passar um valor de altura menor ou igual a zero para o
	 * gerador.
	 */
	@Test
	public void testAlturaNegativa() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(-1);

		gerador.setMaximoAlgarismos(5);
		gerador.setMinimoAlgarismos(5);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Altura inválida.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.ALTURA_INVALIDA));

				return;
			}
		}

		fail("Altura inválida.");
	}

	/**
	 * Ver nota do teste testValidadeAltura.
	 */
	@Test
	public void testAlturaZero() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(0);

		gerador.setMaximoAlgarismos(5);
		gerador.setMinimoAlgarismos(5);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Altura inválida.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.ALTURA_INVALIDA));

				return;
			}
		}

		fail("Altura inválida.");
	}

	/**
	 * A quantidade mínima de algarismos deve ser maior que 2, menor ou igual a
	 * menor dimensão e menor ou igual ao máximo de algarismos
	 */
	@Test
	public void testMinimoAlgarismosMenor3() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(20);

		gerador.setMaximoAlgarismos(5);
		// Mínimo de algarismos menor que 3
		gerador.setMinimoAlgarismos(1);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Mínimo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MINIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Mínimo de algarismos inválido.");
	}
	
	/**
	 * Ver nota do <code>testMinimoAlgarismosMenor3</code>.
	 */
	@Test
	public void testMinimoAlgarismosMaiorLargura() {
		gerador = new GeradorNumerox();

		gerador.setLargura(15);
		gerador.setAltura(20);

		gerador.setMaximoAlgarismos(19);
		// Mínimo de algarismos maior do que a largura
		gerador.setMinimoAlgarismos(17);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Mínimo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MINIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Mínimo de algarismos inválido.");
	}

	/**
	 * Ver nota do <code>testMinimoAlgarismosMenor3</code>
	 */
	@Test
	public void testMinimoAlgarismosMaiorAltura() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(15);

		gerador.setMaximoAlgarismos(19);
		// Mínimo de algarismos maior do que a largura
		gerador.setMinimoAlgarismos(17);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Mínimo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MINIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Mínimo de algarismos inválido.");

	}
	
	/**
	 * Ver nota do <code>testMinimoAlgarismosMenor3</code>
	 */
	@Test
	public void testMinimoAlgarismosMaiorMaximo() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(20);

		gerador.setMaximoAlgarismos(5);
		// Mínimo de algarismos maior do que o máximo
		gerador.setMinimoAlgarismos(7);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Mínimo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MINIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Mínimo de algarismos inválido.");
	}
	
	/**
	 * O máximo de algarismos deve ser menor ou igual a menor dimensão da matriz.
	 */
	@Test
	public void testMaximoAlgarismosMaiorLargura() {
		gerador = new GeradorNumerox();

		gerador.setLargura(20);
		gerador.setAltura(22);

		// Máximo de algarismos maior que a Largura
		gerador.setMaximoAlgarismos(21);
		gerador.setMinimoAlgarismos(17);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Máximo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MAXIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Máximo de algarismos inválido.");

	}
	
	/**
	 * Ver nota do <code>testMaximoAlgarismosMaiorLargura</code>
	 */
	@Test
	public void testMaximoAlgarismosMaiorAltura() {
		gerador = new GeradorNumerox();

		gerador.setLargura(22);
		gerador.setAltura(20);

		// Máximo de algarismos maior que a Altura
		gerador.setMaximoAlgarismos(21);
		gerador.setMinimoAlgarismos(17);

		try {
			gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			if (e instanceof GeradorNumeroxException) {
				GeradorNumeroxException ex = (GeradorNumeroxException) e;

				assertTrue("Máximo de algarismos inválido.", ex.getMotivo().equals(
						GeradorNumeroxException.Motivo.MAXIMO_ALGARISMOS_INVALIDO));

				return;
			}
		}

		fail("Máximo de algarismos inválido.");		
	}
}