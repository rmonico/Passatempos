package br.org.ita.numerox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.jogoscruzadoscomum.GeradorMatriz;
import br.org.ita.jogoscruzadoscomum.MyRandom;

public class GeradorNumerox implements GeradorMatriz {

	private int largura;
	private int altura;
	private int minimoAlgarismos;
	private int maximoAlgarismos;
	MatrizNumerox matriz;
	private Map<Integer, List<NumeroPosicionado>> bancoNumeros = new HashMap<Integer, List<NumeroPosicionado>>();
	private Map<TipoCruzamento, List<Cruzamento>> listaCruzamentos = new HashMap<TipoCruzamento, List<Cruzamento>>();
	private List<TipoCruzamento> listaTiposCruzamento = new ArrayList<TipoCruzamento>();

	/**
	 * @return the largura
	 */
	@Override
	public int getLargura() {
		return largura;
	}

	/**
	 * @param largura
	 *            the largura to set
	 */
	@Override
	public void setLargura(int largura) {
		this.largura = largura;
	}

	/**
	 * @return the altura
	 */
	@Override
	public int getAltura() {
		return altura;
	}

	/**
	 * @param altura
	 *            the altura to set
	 */
	@Override
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * @return Quantidade mínima de algarismos que cada número poderá ter.
	 */
	public int getMinimoAlgarismos() {
		return minimoAlgarismos;
	}

	/**
	 * @param minimoAlgarismos
	 *            Quantidade mínima de algarismos que cada número poderá ter.
	 */
	public void setMinimoAlgarismos(int minimoAlgarismos) {
		this.minimoAlgarismos = minimoAlgarismos;
	}

	/**
	 * @return Quantidade máxima de algarismos que cada número poderá ter.
	 */
	public int getMaximoAlgarismos() {
		return maximoAlgarismos;
	}

	/**
	 * @param maximoAlgarismos
	 *            Quantidade máxima de algarismos que cada número poderá ter.
	 */
	public void setMaximoAlgarismos(int maximoAlgarismos) {
		this.maximoAlgarismos = maximoAlgarismos;
	}

	/**
	 * Verificar se os parâmetros passados para a classe são válidos.
	 * 
	 * @throws GeradorNumeroxException
	 */
	@Override
	public void checarParametros() throws GeradorNumeroxException {
		if (largura <= 0) {
			throw new GeradorNumeroxException(
					GeradorNumeroxException.Motivo.LARGURA_INVALIDA);
		}

		if (altura <= 0) {
			throw new GeradorNumeroxException(
					GeradorNumeroxException.Motivo.ALTURA_INVALIDA);
		}

		int menorDimensao = largura < altura ? largura : altura;

		if (minimoAlgarismos < 3 || minimoAlgarismos > menorDimensao
				|| minimoAlgarismos > maximoAlgarismos) {
			throw new GeradorNumeroxException(
					GeradorNumeroxException.Motivo.MINIMO_ALGARISMOS_INVALIDO);
		}

		if (maximoAlgarismos > menorDimensao) {
			throw new GeradorNumeroxException(
					GeradorNumeroxException.Motivo.MAXIMO_ALGARISMOS_INVALIDO);
		}
	}

	@Override
	public MatrizNumerox gerarMatriz() throws GeradorNumeroxException {
		checarParametros();

		prepararMatriz();

		NumeroPosicionado primeiroNumero = colocarPrimeiroNumeroMatriz();

		List<NumeroPosicionado> primeiraLista = new ArrayList<NumeroPosicionado>();

		primeiraLista.add(primeiroNumero);

		colocarProximosNumerosMatriz(primeiraLista, 5);

		popularCruzamentos();
		
		if (getBancoNumeros().size() == 1) {
			// TODO Bug: ém alguns casos não identificados está gerando a matriz com apenas um número
			matriz = gerarMatriz();
		}

		return matriz;
	}

	private void popularCruzamentos() {
		for (TipoCruzamento tipoCruzamento : listaTiposCruzamento) {

			assert (listaCruzamentos.get(tipoCruzamento).size() <= 10);

			List<Integer> valoresDisponiveis = new ArrayList<Integer>();

			for (int i = 0; i < 10; i++) {
				valoresDisponiveis.add(i);
			}

			for (Cruzamento cruzamento : listaCruzamentos.get(tipoCruzamento)) {
				int i = MyRandom.global().randomInRange(0,
						valoresDisponiveis.size() - 1);

				CelulaNumerox celulaCruzamento = cruzamento.getNumeroFixo()
						.getNumero().getCelulas()[cruzamento.getNumeroFixo()
						.getLocalCruzamento()];

				celulaCruzamento.setChar(Integer.toString(i).charAt(0));
				celulaCruzamento.setNumero(cruzamento.getNumeroTeste()
						.getNumero());
				celulaCruzamento.setCruzamento(cruzamento);

				valoresDisponiveis.remove(i);
			}
		}
	}

	private void colocarProximosNumerosMatriz(
			List<NumeroPosicionado> numerosProcessar, int profundidade) {

		if (profundidade == 0 || numerosProcessar.size() == 0) {
			return;
		}

		List<NumeroPosicionado> proximaLista = new ArrayList<NumeroPosicionado>();

		// Itera por todos os números pendentes de processamento
		for (NumeroPosicionado numeroProcessar : numerosProcessar) {

			List<List<List<Cruzamento>>> cruzamentosPossiveis = listarCruzamentos(numeroProcessar);

			while (cruzamentosPossiveis.size() > 0) {

				// Sorteia em qual algarismo do numero a processar será colocado
				// o cruzamento
				int i = MyRandom.global().randomInRange(0,
						cruzamentosPossiveis.size() - 1);
				List<List<Cruzamento>> cruzamentosAlgarismo = cruzamentosPossiveis
						.get(i);
				// TODO Não pode eliminar o vizinho de acordo com o indice em
				// cruzamentos possíveis, pois após excluir um item dessa lista,
				// os índices dela não correspondem mais a posição dos
				// algarismos do número
				List<List<Cruzamento>> cruzamentosAlgarismoVizinho1 = (i > 0 ? cruzamentosPossiveis
						.get(i - 1)
						: null);

				List<List<Cruzamento>> cruzamentosAlgarismoVizinho2 = (i < cruzamentosPossiveis
						.size() - 1 ? cruzamentosPossiveis.get(i + 1) : null);

				// Eliminar a lista de cruzamentos do algarismo onde foi feito o
				// cruzamento e dos algarismos vizinhos
				cruzamentosPossiveis.remove(cruzamentosAlgarismo);
				if (cruzamentosAlgarismoVizinho1 != null) {
					cruzamentosPossiveis.remove(cruzamentosAlgarismoVizinho1);
				}
				if (cruzamentosAlgarismoVizinho2 != null) {
					cruzamentosPossiveis.remove(cruzamentosAlgarismoVizinho2);
				}

				// Sorteia qual será o tamanho do número usado no cruzamento
				int tamanhoNumeroCruzamento = MyRandom.global().randomInRange(
						0, cruzamentosAlgarismo.size() - 1);

				List<Cruzamento> cruzamentos = cruzamentosAlgarismo
						.get(tamanhoNumeroCruzamento);

				// Sorteia qual dos cruzamentos possíveis no algarismo sorteado
				// será usado
				int cruzamentoIndice = MyRandom.global().randomInRange(0,
						cruzamentos.size() - 1);

				Cruzamento cruzamento = cruzamentos.get(cruzamentoIndice);

				adicionarCruzamentoNaMatriz(cruzamento);

				proximaLista.add(cruzamento.getNumeroTeste().getNumero());
			}
		}

		colocarProximosNumerosMatriz(proximaLista, profundidade - 1);

	}

	/**
	 * A List externa deve ter um item para cada celula do número, a
	 * List<Cruzamento> interna deve ter todos os cruzamentos que forem
	 * possíveis a partir da posição da list externa
	 * 
	 * @param numero
	 * @return
	 */
	private List<List<List<Cruzamento>>> listarCruzamentos(
			NumeroPosicionado numero) {

		List<List<List<Cruzamento>>> cruzamentosPossiveis = new ArrayList<List<List<Cruzamento>>>();

		// Itera por cada um dos algarismos de numero
		for (int localNumero = 0; localNumero < numero.getTamanho(); localNumero++) {
			List<List<Cruzamento>> cruzamentosCelula = listarCruzamentosCelula(
					numero, localNumero);

			if (cruzamentosCelula.size() > 0) {
				cruzamentosPossiveis.add(cruzamentosCelula);
			}
		}

		return cruzamentosPossiveis;
	}

	/**
	 * Devolve uma lista com um item para cada tamanho possível de número todos
	 * os cruzamentos possíveis para a célula <code>localNumero</code> do número
	 * <code>numero</code>
	 * 
	 * @param numero
	 * @param localNumero
	 * @return
	 */
	private List<List<Cruzamento>> listarCruzamentosCelula(
			NumeroPosicionado numero, int localNumero) {
		List<List<Cruzamento>> cruzamentosPosicao = new ArrayList<List<Cruzamento>>();

		// Itera por todos os tamanhos possíveis de numeros
		for (int tamanhoNumeroTeste = minimoAlgarismos; tamanhoNumeroTeste <= maximoAlgarismos; tamanhoNumeroTeste++) {
			List<Cruzamento> cruzamentosNumeroTeste = listarCruzamentosNumeroTeste(
					numero, localNumero, tamanhoNumeroTeste);

			if (cruzamentosNumeroTeste.size() > 0) {
				cruzamentosPosicao.add(cruzamentosNumeroTeste);
			}
		}

		return cruzamentosPosicao;
	}

	/**
	 * Devolve uma lista de todos os cruzamentos possíveis para a célula
	 * <code>localNumero</code> do número <code>numero</code> com números de
	 * <code>tamanhoNumeroTeste</code>
	 * 
	 * @param numero
	 * @param localNumero
	 * @param tamanhoNumeroTeste
	 * @return
	 */
	private List<Cruzamento> listarCruzamentosNumeroTeste(
			NumeroPosicionado numero, int localNumero, int tamanhoNumeroTeste) {

		List<Cruzamento> cruzamentosNumeroTeste = new ArrayList<Cruzamento>();

		for (int posicaoNumeroTeste = tamanhoNumeroTeste-1; posicaoNumeroTeste > 0; posicaoNumeroTeste--) {
			NumeroPosicionado numeroTeste = NumeroPosicionadoFactory();

			numeroTeste.setTamanho(tamanhoNumeroTeste);

			Cruzamento cruzamento = new Cruzamento(this);

			cruzamento.getNumeroFixo().setNumero(numero);
			cruzamento.getNumeroFixo().setLocalCruzamento(localNumero);
			cruzamento.getNumeroTeste().setNumero(numeroTeste);
			cruzamento.getNumeroTeste().setLocalCruzamento(posicaoNumeroTeste);

			cruzamento.atualizarNumeroTeste();

			// Só permite o cruzamento se houverem cruzamentos
			// desse tipo disponíveis na lista
			// Se a lista dos cruzamentos desse tipo for null é por que
			// ainda não existe nenhum cruzamento desse tipo
			if (listaCruzamentos.get(cruzamento.getTipo()) != null
					&& listaCruzamentos.get(cruzamento.getTipo()).size() >= 10) {
				continue;
			}

			if (cruzamento.isValido(this)) {
				cruzamentosNumeroTeste.add(cruzamento);
			}
		}

		return cruzamentosNumeroTeste;
	}

	private NumeroPosicionado colocarPrimeiroNumeroMatriz() {
		NumeroPosicionado primeiroNumero = NumeroPosicionadoFactory();

		primeiroNumero.setDisposicao(Disposicao.getAleatorio());

		primeiroNumero.setTamanho(MyRandom.global().randomInRange(
				minimoAlgarismos, maximoAlgarismos));

		// TODO: Corrigir erro de geração do número aqui, está gerando números
		// fora da matriz
		primeiroNumero.setColunaInicio(MyRandom.global().randomInRange(0,
				largura - primeiroNumero.getLargura()));
		primeiroNumero.setLinhaInicio(MyRandom.global().randomInRange(0,
				altura - primeiroNumero.getAltura()));

		primeiroNumero.atualizarCelulas();
		primeiroNumero.setPrimeiraPalavra();
		adicionarNumeroNoBanco(primeiroNumero);

		return primeiroNumero;
	}

	public NumeroPosicionado NumeroPosicionadoFactory() {
		return new NumeroPosicionado(matriz);
	}

	void adicionarCruzamentoNaMatriz(Cruzamento cruzamento) {
		cruzamento.confirmar();
		cruzamento.getNumeroTeste().getNumero().atualizarCelulas();
		adicionarNumeroNoBanco(cruzamento.getNumeroTeste().getNumero());
		adicionarCruzamentoNaLista(cruzamento);
	}

	private void adicionarNumeroNoBanco(NumeroPosicionado numero) {
		List<NumeroPosicionado> numerosDoMesmoTamanho = bancoNumeros.get(numero
				.getTamanho());

		if (numerosDoMesmoTamanho == null) {
			numerosDoMesmoTamanho = new ArrayList<NumeroPosicionado>();

			bancoNumeros.put(numero.getTamanho(), numerosDoMesmoTamanho);
		}

		numerosDoMesmoTamanho.add(numero);
	}

	private void adicionarCruzamentoNaLista(Cruzamento cruzamento) {
		List<Cruzamento> listaCruzamentosMesmoTipo = listaCruzamentos
				.get(cruzamento.getTipo());

		if (listaCruzamentosMesmoTipo == null) {
			listaCruzamentosMesmoTipo = new ArrayList<Cruzamento>();
			listaCruzamentos.put(cruzamento.getTipo(),
					listaCruzamentosMesmoTipo);
		}

		listaCruzamentosMesmoTipo.add(cruzamento);
	}

	public Map<Integer, List<NumeroPosicionado>> getBancoNumeros() {
		return bancoNumeros;
	}

	TipoCruzamento getTipoCruzamento(Cruzamento cruzamento) {
		int tamanhoNumeroFixo = cruzamento.getNumeroFixo().getNumero()
				.getTamanho();
		int localCruzamentoNumeroFixo = cruzamento.getNumeroFixo()
				.getLocalCruzamento();

		int tamanhoNumeroTeste = cruzamento.getNumeroTeste().getNumero()
				.getTamanho();
		int localCruzamentoNumeroTeste = cruzamento.getNumeroTeste()
				.getLocalCruzamento();

		for (TipoCruzamento tipoCruzamento : listaTiposCruzamento) {
			if (tipoCruzamento.getTamanhoNumeroFixo() == tamanhoNumeroFixo
					&& tipoCruzamento.getLocalCruzamentoNumeroFixo() == localCruzamentoNumeroFixo
					&& tipoCruzamento.getTamanhoNumeroTeste() == tamanhoNumeroTeste
					&& tipoCruzamento.getLocalCruzamentoNumeroTeste() == localCruzamentoNumeroTeste) {
				return tipoCruzamento;
			}
		}

		TipoCruzamento novoTipoCruzamento = new TipoCruzamento();

		novoTipoCruzamento.setTamanhoNumeroFixo(tamanhoNumeroFixo);
		novoTipoCruzamento
				.setLocalCruzamentoNumeroFixo(localCruzamentoNumeroFixo);
		novoTipoCruzamento.setTamanhoNumeroTeste(tamanhoNumeroTeste);
		novoTipoCruzamento
				.setLocalCruzamentoNumeroTeste(localCruzamentoNumeroTeste);

		return novoTipoCruzamento;
	}

	public void prepararMatriz() {
		matriz = new MatrizNumerox(largura, altura);
	}
}