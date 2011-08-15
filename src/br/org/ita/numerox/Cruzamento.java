package br.org.ita.numerox;

import br.org.ita.jogoscruzadoscomum.Coordenada;

/**
 * Representa os dados do cruzamento de dois números.
 * 
 * @param <L>
 *            Listener da classe
 * @author rafael
 * 
 */
public class Cruzamento {
	private GeradorNumerox gerador;

	public Cruzamento(GeradorNumerox gerador) {
		super();

		this.gerador = gerador;
	}

	/**
	 * Representa os dados do cruzamento em um número.
	 * 
	 * @author rafael
	 * 
	 */
	public class CruzamentoNumero {
		private NumeroPosicionado numero;
		private int localCruzamento;

		/**
		 * @return the numero
		 */
		public NumeroPosicionado getNumero() {
			return numero;
		}

		/**
		 * @param numero
		 *            the numero to set
		 */
		public void setNumero(NumeroPosicionado numero) {
			this.numero = numero;
		}

		/**
		 * @return the localCruzamento
		 */
		public int getLocalCruzamento() {
			return localCruzamento;
		}

		/**
		 * @param localCruzamento
		 *            the localCruzamento to set
		 */
		public void setLocalCruzamento(int localCruzamento) {
			this.localCruzamento = localCruzamento;
		}
	}

	private CruzamentoNumero numeroFixo = new CruzamentoNumero();
	private CruzamentoNumero numeroTeste = new CruzamentoNumero();

	public CruzamentoNumero getNumeroFixo() {
		return numeroFixo;
	}

	public CruzamentoNumero getNumeroTeste() {
		return numeroTeste;
	}

	/**
	 * Devolve a instância de tipo de cruzamento correspondente para esse
	 * cruzamento
	 * 
	 * @return
	 */
	public TipoCruzamento getTipo() {
		return gerador.getTipoCruzamento(this);
	}

	/**
	 * Usado para marcar a célula do cruzamento com a referência ao cruzamento
	 * do qual ela faz parte
	 */
	public void confirmar() {
		getNumeroFixo().getNumero().getCelulas()[getNumeroFixo()
				.getLocalCruzamento()].setCruzamento(this);
	}

	public void atualizarNumeroTeste() {

		NumeroPosicionado numeroFixo = getNumeroFixo().getNumero();
		NumeroPosicionado numeroTeste = getNumeroTeste().getNumero();

		int localNumeroFixo = getNumeroFixo().getLocalCruzamento();
		int localNumeroTeste = getNumeroTeste().getLocalCruzamento();

		numeroTeste.setDisposicao(numeroFixo.getDisposicao()
				.getDisposicaoAlternativa());

		if (numeroTeste.getDisposicao().EHorizontal()) {
			numeroTeste.setLinhaInicio(localNumeroFixo
					+ numeroFixo.getLinhaInicio());

			numeroTeste.setColunaInicio(numeroFixo.getColunaInicio()
					- localNumeroTeste);
		} else {
			numeroTeste.setColunaInicio(localNumeroFixo
					+ numeroFixo.getColunaInicio());

			numeroTeste.setLinhaInicio(numeroFixo.getLinhaInicio()
					- localNumeroTeste);
		}

	}

	/**
	 * Verifica a validade do cruzamento
	 * 
	 * @param matriz
	 * @return
	 */
	public boolean isValido(GeradorNumerox gerador) {
		MatrizNumerox matriz = gerador.matriz;

		NumeroPosicionado numeroFixo = getNumeroFixo().getNumero();
		int localNumero = getNumeroFixo().getLocalCruzamento();

		NumeroPosicionado numeroTeste = getNumeroTeste().getNumero();

		// Verifica se existe um cruzamento de fato entre os números
		if (numeroFixo.getDisposicao().EHorizontal()) {
			if (numeroTeste.getColunaInicio() < numeroFixo.getColunaInicio()
					|| numeroTeste.getColunaInicio() > numeroFixo
							.getColunaFim()) {
				return false;
			}

			if (numeroFixo.getLinhaInicio() < numeroTeste.getLinhaInicio()
					|| numeroFixo.getLinhaInicio() > numeroTeste.getLinhaFim()) {
				return false;
			}
		} else {
			if (numeroTeste.getLinhaInicio() < numeroFixo.getLinhaInicio()
					|| numeroTeste.getLinhaInicio() > numeroFixo.getLinhaFim()) {
				return false;
			}

			if (numeroFixo.getColunaInicio() < numeroTeste.getColunaInicio()
					|| numeroFixo.getColunaInicio() > numeroTeste
							.getColunaFim()) {
				return false;
			}
		}

		// Verifica se os números estão dentro da matriz
		if (!gerador.matriz.isCoordenadasDentroMatriz(numeroFixo
				.getCoordenadaInicio())
				|| !gerador.matriz.isCoordenadasDentroMatriz(numeroFixo
						.getCoordenadaFim())
				|| !gerador.matriz.isCoordenadasDentroMatriz(numeroTeste
						.getCoordenadaInicio())
				|| !gerador.matriz.isCoordenadasDentroMatriz(numeroTeste
						.getCoordenadaFim())) {
			return false;
		}
		
		// Verifica se a célula que fica antes e a que fica depois do número de teste estão ocupadas
		int colunaVizinhoPrimeiro = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste
				.getColunaInicio() - 1
				: numeroTeste.getColunaInicio();

		int linhaVizinhoPrimeiro = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste
				.getLinhaInicio()
				: numeroTeste.getLinhaInicio() - 1;

		if (matriz.isCoordenadasDentroMatriz(new Coordenada(
				colunaVizinhoPrimeiro, linhaVizinhoPrimeiro))) {
			// Célula que está antes da primeira célula do numero de teste
			CelulaNumerox vizinhoPrimeiro = matriz.getCelula(
					colunaVizinhoPrimeiro, linhaVizinhoPrimeiro);

			// A célula antes da primeira célula do numeroTeste nunca pode
			// estar ocupada, pois causaria concatenação de números
			if (!vizinhoPrimeiro.isVazia()) {
				return false;
			}
		}
		
		int colunaVizinhoUltimo = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste.getColunaFim() + 1
				: numeroTeste.getColunaInicio();

		int linhaVizinhoUltimo = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste
				.getLinhaInicio()
				: numeroTeste.getLinhaFim() + 1;

		if (matriz.isCoordenadasDentroMatriz(new Coordenada(
				colunaVizinhoUltimo, linhaVizinhoUltimo))) {
			// Célula que está depois da última célula do numero de teste
			CelulaNumerox vizinhoUltimo = matriz.getCelula(colunaVizinhoUltimo,
					linhaVizinhoUltimo);

			// A célula depois da última célula do numeroTeste nunca pode
			// estar ocupada, pois causaria uma concatenação de números
			if (!vizinhoUltimo.isVazia()) {
				return false;
			}
		}

		// Linha e coluna da célula que está cruzando com numeroTeste
		int colunaCruzamento = (numeroFixo.getDisposicao().EHorizontal() ? numeroFixo
				.getColunaInicio()
				+ localNumero
				: numeroFixo.getColunaInicio());
		int linhaCruzamento = (numeroFixo.getDisposicao().EHorizontal() ? numeroFixo
				.getLinhaInicio()
				: numeroFixo.getLinhaInicio() + localNumero);

		// Testa se cada uma das células do numeroTeste está ocupada, bem como
		// suas vizinhas
		// Itera por cada um dos algarismos do número sendo testado
		for (int localNumeroTeste = 0; localNumeroTeste < numeroTeste
				.getTamanho(); localNumeroTeste++) {

			// CelulaCentral: célula sendo testada
			int colunaCelulaCentral = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste
					.getColunaInicio()
					+ localNumeroTeste
					: numeroTeste.getColunaInicio();
			int linhaCelulaCentral = numeroTeste.getDisposicao().EHorizontal() ? numeroTeste
					.getLinhaInicio()
					: numeroTeste.getLinhaInicio() + localNumeroTeste;

			if (!matriz.isCoordenadasDentroMatriz(new Coordenada(
					colunaCelulaCentral, linhaCelulaCentral))) {
				return false;
			}

			// Ignorar célula do cruzamento com numero
			if (colunaCelulaCentral == colunaCruzamento
					&& linhaCelulaCentral == linhaCruzamento) {
				continue;
			}

			CelulaNumerox celulaCentral = matriz.getCelula(colunaCelulaCentral,
					linhaCelulaCentral);

			CelulaNumerox celulaVizinha1;
			int colunaCelulaVizinha1 = numeroTeste.getDisposicao()
					.EHorizontal() ? colunaCelulaCentral
					: colunaCelulaCentral - 1;
			int linhaCelulaVizinha1 = numeroTeste.getDisposicao().EHorizontal() ? linhaCelulaCentral - 1
					: linhaCelulaCentral;

			if (!matriz.isCoordenadasDentroMatriz(new Coordenada(
					colunaCelulaVizinha1, linhaCelulaVizinha1))) {
				celulaVizinha1 = null;
			} else {
				celulaVizinha1 = matriz.getCelula(colunaCelulaVizinha1,
						linhaCelulaVizinha1);
			}

			CelulaNumerox celulaVizinha2;
			int colunaCelulaVizinha2 = numeroTeste.getDisposicao()
					.EHorizontal() ? colunaCelulaCentral
					: colunaCelulaCentral + 1;
			int linhaCelulaVizinha2 = numeroTeste.getDisposicao().EHorizontal() ? linhaCelulaCentral + 1
					: linhaCelulaCentral;

			if (!matriz.isCoordenadasDentroMatriz(new Coordenada(
					colunaCelulaVizinha2, linhaCelulaVizinha2))) {
				celulaVizinha2 = null;
			} else {
				celulaVizinha2 = matriz.getCelula(colunaCelulaVizinha2,
						linhaCelulaVizinha2);
			}

			if (celulaCentral.isVazia()) {
				// Se a celulaCentral estiver vazia, as células vizinhas também
				// devem estar
				if ((celulaVizinha1 != null && !celulaVizinha1.isVazia())
						|| (celulaVizinha2 != null && !celulaVizinha2.isVazia())) {
					return false;
				}
			} else {
				NumeroPosicionado outroNumero = celulaCentral.getNumero();
				if (outroNumero.getDisposicao().equals(
						numeroTeste.getDisposicao())) {
					// Se numeroTeste interceptar outro número com a
					// mesma disposição, não permitir o cruzamento
					return false;
				}

				// Se o número teste interceptar outro número com disposição
				// diferente, só deve permitir se a célula interceptada não
				// fazer parte de um cruzamento
				if (celulaCentral.getCruzamento() != null) {
					return false;
				}
			}

		}

		return true;
	}
}
