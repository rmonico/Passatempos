package br.org.ita.palavracruzada;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.org.ita.jogoscruzadoscomum.Disposicao;
import br.org.ita.jogoscruzadoscomum.GeradorMatriz;
import br.org.ita.jogoscruzadoscomum.GeradorMatrizException;
import br.org.ita.jogoscruzadoscomum.MyRandom;

public class GeradorPalavraCruzada implements GeradorMatriz {
	private int largura;
	private int altura;

	private MatrizPalavraCruzada matriz;

	private List<Palavra> palavrasDisponiveis;

	private Stack<List<PalavraPosicionada>> palavrasAtivas = new Stack<List<PalavraPosicionada>>();

	private List<Palavra> bancoPalavras;
	private boolean apenasUmaPalavra;

	@Override
	public int getLargura() {
		return largura;
	}

	@Override
	public void setLargura(int largura) {
		this.largura = largura;
	}

	@Override
	public int getAltura() {
		return altura;
	}

	@Override
	public void setAltura(int altura) {
		this.altura = altura;
	}

	@Override
	public MatrizPalavraCruzada gerarMatriz() {
		matriz = new MatrizPalavraCruzada(altura, largura);

		palavrasDisponiveis = filtrarBancoGlobal();

		palavrasAtivas = new Stack<List<PalavraPosicionada>>();

		colocarPrimeiraPalavraNaMatriz();

		colocarProximasPalavrasNaMatriz();
		
		if (apenasUmaPalavra) {
			// BUG: em alguns casos está gerando com apenas uma palavra na matriz
			matriz = gerarMatriz();
		}

		return matriz;
	}

	private void colocarProximasPalavrasNaMatriz() {
		List<PalavraPosicionada> palavrasUsadas = new ArrayList<PalavraPosicionada>();

		for (PalavraPosicionada palavraAtiva : palavrasAtivas.peek()) {

			// Não posso usar enhanced for, pois vou precisar do índice no array
			for (int i = 0; i < palavrasDisponiveis.size(); i++) {
				PalavraPosicionada palavraTeste = new PalavraPosicionada(
						palavrasDisponiveis.get(i));
//				palavraTeste.setDisposicao(palavraAtiva.getDisposicao()
//						.getDisposicaoAlternativa());

				// Lança em cruzamentosPossiveis todos os cruzamentos possíveis
				// entre a getPalavra() ativa e a getPalavra() teste
				List<Cruzamento> cruzamentosPossiveis = new ArrayList<Cruzamento>();

				for (int j = 0; j < palavraAtiva.getTexto().length; j++) {
					Caractere chAtivo = palavraAtiva.getTexto()[j];
					for (int k = 0; k < palavraTeste.getTexto().length; k++) {
						Caractere chTeste = palavraTeste.getTexto()[k];
						
						if (chAtivo.equals(chTeste)) {
							Cruzamento cruzamento = new Cruzamento();

							cruzamento.setIndiceBase(j);
							cruzamento.setIndiceTeste(k);

							cruzamentosPossiveis.add(cruzamento);
						}
					}
				}

				// Testa quais cruzamento são válidos
				List<Cruzamento> cruzamentosValidos = new ArrayList<Cruzamento>();

				for (Cruzamento cruzamento : cruzamentosPossiveis) {
					// Testar se cruzamentoPossivel.get(j) é valido, basedo em
					// palavraAtiva e palavraTeste e Matriz
					if (cruzamento.eValido(palavraAtiva, palavraTeste, matriz)) {
						cruzamentosValidos.add(cruzamento);
					}
				}
				// Se não existem cruzamentos válidos para a getPalavra() de
				// teste, ir para a próxima getPalavra()
				if (cruzamentosValidos.size() == 0) {
					continue;
				}

				// Seleciona um cruzamento válido
				int cruzamentoSelecionado = MyRandom.global().randomInRange(0,
						cruzamentosValidos.size() - 1);

				Cruzamento cruzamento = cruzamentosValidos
						.get(cruzamentoSelecionado);

				cruzamento.preencherPalavraTeste(palavraAtiva, palavraTeste);

				colocarPalavraNaMatriz(palavraTeste);

				palavrasUsadas.add(palavraTeste);

				// Se encontrou uma palavra e a usou, a palavra foi tirada da
				// lista, precisa manter o "i" atual
				i--;
			}

			palavrasAtivas.push(palavrasUsadas);

			colocarProximasPalavrasNaMatriz();

			palavrasAtivas.pop();
		}
	}

	private void colocarPrimeiraPalavraNaMatriz() {
		// Sorteia a primeira palavra para colocar na matriz, como se trata
		// da primeira palavra, as únicas restrições são em relação ao tamanho e
		// posição
		int indicePalavra = MyRandom.global().randomInRange(0, palavrasDisponiveis.size() - 1);

		PalavraPosicionada primeiraPalavra = new PalavraPosicionada(
				palavrasDisponiveis.get(indicePalavra));

		primeiraPalavra.setDisposicao(Disposicao.getAleatorio());

		/* Coluna onde a palavra será colocada */
		primeiraPalavra.setColunaInicio(MyRandom.global().randomInRange(0, largura
				- primeiraPalavra.getLargura()));
		primeiraPalavra.setLinhaInicio(MyRandom.global().randomInRange(0, altura
				- primeiraPalavra.getAltura()));

		palavrasAtivas.add(new ArrayList<PalavraPosicionada>());
		palavrasAtivas.peek().add(primeiraPalavra);

		/* Coloca a primeiraPalavra no array */
		colocarPalavraNaMatriz(primeiraPalavra);
		
		apenasUmaPalavra = true;
	}

	private void colocarPalavraNaMatriz(PalavraPosicionada palavra) {
		matriz.colocar(palavra);
		
		palavrasDisponiveis.remove(palavra);
		
		apenasUmaPalavra = false;
	}

	/**
	 * Filtra o banco de palavras global de acordo com os parâmetros passados
	 * para a classe
	 * 
	 * @return
	 */
	public List<Palavra> filtrarBancoGlobal() {
		List<Palavra> bancoFiltrado = new ArrayList<Palavra>();

		for (Palavra p : getBancoPalavras()) {
			if ((p.getCelulas().length > largura)
					|| (p.getCelulas().length > altura)) {
				continue;
			}
			
			bancoFiltrado.add(p);
		}

		return bancoFiltrado;
	}

	public List<Palavra> getBancoPalavras() {

		if (bancoPalavras == null) {
			bancoPalavras = new ArrayList<Palavra>();
		}

		return bancoPalavras;
	}

	@Override
	public void checarParametros() throws GeradorMatrizException {
		if (largura <= 0) {
			throw new GeradorPalavraCruzadaException(GeradorPalavraCruzadaException.Motivo.LARGURA_INVALIDA);
		}
		
		if (altura <= 0) {
			throw new GeradorPalavraCruzadaException(GeradorPalavraCruzadaException.Motivo.ALTURA_INVALIDA);
		}
	}
}
