package br.org.ita.palavracruzada;

public class Cruzamento {
	private int indiceBase;
	private int indiceTeste;

	public int getIndiceBase() {
		return indiceBase;
	}

	public void setIndiceBase(int indiceBase) {
		this.indiceBase = indiceBase;
	}

	public int getIndiceTeste() {
		return indiceTeste;
	}

	public void setIndiceTeste(int indiceTeste) {
		this.indiceTeste = indiceTeste;
	}

	/**
	 * Preenche os dados de disposicao, coluna e linha da palavraTeste baseado
	 * na palavraBase (notar que não verifica se a palavra cabe na matriz)
	 * 
	 * @param base
	 * @param teste
	 */
	public void preencherPalavraTeste(PalavraPosicionada base,
			PalavraPosicionada teste) {
		teste.setDisposicao(base.getDisposicao().getDisposicaoAlternativa());

		if (teste.getDisposicao().EHorizontal()) {
			teste.setColunaInicio(base.getColunaInicio() - indiceTeste - 1);
			teste.setLinhaInicio(base.getLinhaInicio() + indiceBase + 1);
		} else {
			teste.setLinhaInicio(base.getLinhaInicio() - indiceTeste - 1);
			teste.setColunaInicio(base.getColunaInicio() + indiceBase + 1);
		}
	}

	/**
	 * 
	 * Testes feitos pelo método:
	 * 
	 * - Um caractere antes do início da palavra não pode estar ocupado
	 * 
	 * - Um caractere depois do fim da palavra não pode estar ocupado
	 * 
	 * - A palavra de teste deve estar complementamente dentro da matriz
	 * 
	 * - As posicões que serão ocupadas pela palavra teste não podem estar
	 * ocupadas, bem como seus vizinhos
	 * 
	 * - O ponto de cruzamento entre a palavra de teste e a palavra base já deve
	 * estar ocupado pelo mesmo caractere que a palavra de teste deveria colocar
	 * (para isso assume que palavraBase já está na matriz)
	 */
	public boolean eValido(PalavraPosicionada palavraBase,
			PalavraPosicionada palavraTeste, MatrizPalavraCruzada matriz) {

		PalavraPosicionada palTeste = palavraTeste.clone();

		preencherPalavraTeste(palavraBase, palTeste);

		// Verifica se a palavra de teste fica dentro da matriz
		if ((palTeste.getLinhaInicio() < 0)
				|| (palTeste.getLinhaFim() > matriz.getUltimaLinha())
				|| (palTeste.getColunaInicio() < 0)
				|| (palTeste.getColunaFim() > matriz.getUltimaColuna())) {
			return false;
		}

		// Verifica o caractere antes do início e depois do fim da palavra
		if (palTeste.getDisposicao().EHorizontal()) {

			// Um caractere antes do início da palavra não pode estar
			// ocupado e nem um depois do final, mas só deve verificar se a
			// palavra não estiver "colada" nas laterais da matriz
			if (palTeste.getColunaInicio() > 0) {
				if (!matriz.isCellEmpty(palTeste.getColunaInicio() - 1,
						palTeste.getLinhaInicio())) {
					return false;
				}
			}

			// Um caracter depois do fim da palavra não pode estar ocupado
			if (palTeste.getColunaFim() < matriz.getUltimaColuna()) {
				if (!matriz.isCellEmpty(palTeste.getColunaFim() + 1, palTeste
						.getLinhaInicio())) {
					return false;
				}
			}
		} else {
			// Trata a palavra quando na vertical
			if (palTeste.getLinhaInicio() > 0) {
				if (!matriz.isCellEmpty(palTeste.getColunaInicio(), palTeste
						.getLinhaInicio() - 1)) {
					return false;
				}
			}

			if (palTeste.getLinhaFim() < matriz.getUltimaLinha()) {
				if (!matriz.isCellEmpty(palavraBase.getColunaInicio(), palTeste
						.getLinhaFim() + 1)) {
					return false;
				}
			}
		}

		// Verifica se as posicões que serão ocupadas pela palavra teste
		// estão ocupadas, bem como seus vizinhos
		for (int i = 0; i <= palTeste.getCelulas().length - 1; i++) {

			// Coluna e linha do caractere
			int colunaVizinho1 = 0;
			int linhaVizinho1 = 0;

			int colunaVizinho2 = 0;
			int linhaVizinho2 = 0;

			if (palTeste.getDisposicao().EHorizontal()) {
				colunaVizinho1 = palTeste.getColunaInicio() + i;
				linhaVizinho1 = palTeste.getLinhaInicio() == 0 ? 0 : palTeste
						.getLinhaInicio() - 1;

				colunaVizinho2 = palTeste.getColunaInicio() + i;
				linhaVizinho2 = palTeste.getLinhaFim() == matriz
						.getUltimaLinha() ? matriz.getUltimaLinha() : palTeste
						.getLinhaFim() + 1;
			} else {
				linhaVizinho1 = palTeste.getLinhaInicio() + i;
				colunaVizinho1 = palTeste.getColunaInicio() == 0 ? 0 : palTeste
						.getColunaInicio() - 1;

				linhaVizinho2 = palTeste.getLinhaInicio() + i;
				colunaVizinho2 = palTeste.getColunaFim() == matriz
						.getUltimaColuna() ? matriz.getUltimaColuna()
						: palTeste.getColunaFim() + 1;
			}

			// Verifica se está no cruzamento entre as palavras
			if (i == indiceTeste+1) {

				/* Cruzamento inválido */
				if (palTeste.getDisposicao().EHorizontal()) {
					if (!matriz.getCelula(palTeste.getColunaInicio() + i,
							palTeste.getLinhaInicio()).equals(
							palTeste.getCelulas()[indiceTeste+1])) {
						return false;
					}
				} else {
					if (!matriz.getCelula(palTeste.getColunaInicio(),
							palTeste.getLinhaInicio() + i).equals(
							palTeste.getCelulas()[indiceTeste+1])) {
						return false;
					}
				}

				continue;
			}

			// Verifica se as posições-chave do array estão desocupadas
			if (palTeste.getDisposicao().EHorizontal()) {
				if (!matriz.isCellEmpty(palTeste.getColunaInicio() + i, palTeste
						.getLinhaInicio())) {
					return false;
				}
			} else {
				if (!matriz.isCellEmpty(palTeste.getColunaInicio(), palTeste
						.getLinhaInicio()
						+ i)) {
					return false;
				}
			}

			if (!matriz.isCellEmpty(colunaVizinho1, linhaVizinho1)) {
				return false;
			}

			if (!matriz.isCellEmpty(colunaVizinho2, linhaVizinho2)) {
				return false;
			}
		}

		return true;
	}
}
