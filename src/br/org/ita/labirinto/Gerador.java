package br.org.ita.labirinto;

import java.util.HashSet;
import java.util.Set;

import br.org.ita.jogoscruzadoscomum.Coordenada;
import br.org.ita.jogoscruzadoscomum.MyRandom;

public class Gerador implements GeradorLabirinto {

	@Override
	public Labirinto gerar() {
		Labirinto labirinto = new Labirinto(20, 20);

		labirinto.getInicio().setColuna(0);
		labirinto.getInicio().setLinha(0);

		labirinto.getFim().setColuna(labirinto.getUltimaColuna());
		labirinto.getFim().setLinha(labirinto.getUltimaLinha());

		gerarCaminho(labirinto, labirinto.getInicio());

		procurarEspacosVazios(labirinto);

		return labirinto;
	}

	private void procurarEspacosVazios(Labirinto labirinto) {
		Coordenada posicaoAtual = new Coordenada();

		for (posicaoAtual.setLinha(0); posicaoAtual.getLinha() <= labirinto
				.getUltimaLinha(); posicaoAtual.setLinha(posicaoAtual
				.getLinha() + 1)) {
			for (posicaoAtual.setColuna(0); posicaoAtual.getColuna() <= labirinto
					.getUltimaColuna(); posicaoAtual.setColuna(posicaoAtual
					.getColuna() + 1)) {
				Labirinto.Casa casa = labirinto.getCasa(posicaoAtual);

				if (!casa.isOcupado()) {
					Set<Direcao> direcoesIndisponiveis = verificarDirecoes(
							labirinto, posicaoAtual, false);

					Direcao direcao = (Direcao) direcoesIndisponiveis.toArray()[MyRandom.global()
							.randomInRange(0, direcoesIndisponiveis.size() - 1)];

					casa.setCaminho(direcao, true);

					gerarCaminho(labirinto, posicaoAtual);
				}
			}
		}

	}

	private void gerarCaminho(Labirinto labirinto, Coordenada inicio) {
		Coordenada posicaoAtual = inicio.clone();

		boolean finalizar = false;

		while (!finalizar) {
			labirinto.getCasa(posicaoAtual).ocupar();

			Set<Direcao> direcoesDisponiveis = verificarDirecoes(labirinto,
					posicaoAtual, true);

			AcaoCasa acao = verificarVizinhos(labirinto, posicaoAtual,
					direcoesDisponiveis);

			switch (acao) {
			case PREENCHER: {
				switch ((Direcao) direcoesDisponiveis.toArray()[MyRandom.global()
						.randomInRange(0, direcoesDisponiveis.size() - 1)]) {

				case ABAIXO: {
					labirinto.getCasa(posicaoAtual).setCaminhoAbaixo(true);
					posicaoAtual.setLinha(posicaoAtual.getLinha() + 1);
					break;
				}
				case ACIMA: {
					labirinto.getCasa(posicaoAtual).setCaminhoAcima(true);
					posicaoAtual.setLinha(posicaoAtual.getLinha() - 1);
					break;
				}
				case DIREITA: {
					labirinto.getCasa(posicaoAtual).setCaminhoDireita(true);
					posicaoAtual.setColuna(posicaoAtual.getColuna() + 1);
					break;
				}
				case ESQUERDA: {
					labirinto.getCasa(posicaoAtual).setCaminhoEsquerda(true);
					posicaoAtual.setColuna(posicaoAtual.getColuna() - 1);
					break;
				}
				}

				break;
			}

			case BIFURCAR_HORIZONTAL: {

				if (posicaoAtual.getColuna() > 0) {
					labirinto.getCasa(posicaoAtual).setCaminhoEsquerda(true);

					Coordenada caminhoEsquerda = new Coordenada(posicaoAtual
							.getColuna() - 1, posicaoAtual.getLinha());

					gerarCaminho(labirinto, caminhoEsquerda);
				}

				if (posicaoAtual.getColuna() < labirinto.getUltimaColuna()) {
					labirinto.getCasa(posicaoAtual).setCaminhoDireita(true);

					Coordenada caminhoDireita = new Coordenada(posicaoAtual
							.getColuna() + 1, posicaoAtual.getLinha());

					gerarCaminho(labirinto, caminhoDireita);
				}

				finalizar = true;
				break;
			}

			case BIFURCAR_VERTICAL: {

				if (posicaoAtual.getLinha() > 0) {
					labirinto.getCasa(posicaoAtual).setCaminhoAcima(true);

					Coordenada caminhoCima = new Coordenada(posicaoAtual
							.getColuna(), posicaoAtual.getLinha() - 1);

					gerarCaminho(labirinto, caminhoCima);
				}

				if (posicaoAtual.getLinha() < labirinto.getUltimaLinha()) {
					labirinto.getCasa(posicaoAtual).setCaminhoAbaixo(true);

					Coordenada caminhoBaixo = new Coordenada(posicaoAtual
							.getColuna(), posicaoAtual.getLinha() + 1);

					gerarCaminho(labirinto, caminhoBaixo);
				}

				finalizar = true;
				break;
			}

			case FINALIZAR: {
				finalizar = true;

				break;
			}
			}
		}
	}

	/**
	 * Verificar quais as direções para onde é possível ir a partir de
	 * <code>casa</code>, caso <code>disponiveis</code> seja <code>false</code>,
	 * então devolve as direções para onde não é possível ir.
	 * 
	 * @param labirinto
	 * @param casa
	 * @param disponiveis
	 * @return
	 */
	private Set<Direcao> verificarDirecoes(Labirinto labirinto,
			Coordenada casa, boolean disponiveis) {
		Set<Direcao> direcoes = new HashSet<Direcao>();
		
		boolean paredeAEsquerda = casa.getColuna() == 0;
		boolean esquerdaDisponivel = !paredeAEsquerda
				&& !labirinto.getCasa(casa.getColuna() - 1, casa.getLinha())
						.isOcupado();
		
		boolean paredeADireita = casa.getColuna() == labirinto.getUltimaColuna();
		boolean direitaDisponivel = !paredeADireita
				&& !labirinto.getCasa(casa.getColuna() + 1, casa.getLinha())
						.isOcupado();
		
		boolean paredeAcima = casa.getLinha() == 0;
		boolean acimaDisponivel = !paredeAcima
				&& !labirinto.getCasa(casa.getColuna(), casa.getLinha() - 1)
						.isOcupado();
		
		boolean paredeAbaixo = casa.getLinha() == labirinto.getUltimaLinha();
		boolean abaixoDisponivel = !paredeAbaixo
				&& !labirinto.getCasa(casa.getColuna(), casa.getLinha() + 1)
						.isOcupado();

		if (esquerdaDisponivel == disponiveis && !paredeAEsquerda) {
			direcoes.add(Direcao.ESQUERDA);
		}

		if (direitaDisponivel == disponiveis && !paredeADireita) {
			direcoes.add(Direcao.DIREITA);
		}

		if (acimaDisponivel == disponiveis && !paredeAcima) {
			direcoes.add(Direcao.ACIMA);
		}

		if (abaixoDisponivel == disponiveis && !paredeAbaixo) {
			direcoes.add(Direcao.ABAIXO);
		}

		return direcoes;
	}

	private enum AcaoCasa {
		PREENCHER, BIFURCAR_HORIZONTAL, BIFURCAR_VERTICAL, FINALIZAR
	}

	private AcaoCasa verificarVizinhos(Labirinto labirinto, Coordenada posicao,
			Set<Direcao> direcoesDisponiveis) {

		switch (direcoesDisponiveis.size()) {
		case 4:
		case 0: {
			// Entrou em um beco sem saída
			return AcaoCasa.FINALIZAR;
		}

		case 3: {
			return AcaoCasa.PREENCHER;
		}

		case 2: {
			if (direcoesDisponiveis.contains(Direcao.ESQUERDA) == direcoesDisponiveis
					.contains(Direcao.DIREITA)) {
				return direcoesDisponiveis.contains(Direcao.ESQUERDA) ? AcaoCasa.BIFURCAR_HORIZONTAL
						: AcaoCasa.BIFURCAR_VERTICAL;
			} else {
				return AcaoCasa.PREENCHER;
			}
		}

		case 1: {
			return AcaoCasa.PREENCHER;
		}

		default: {
			assert false : "Direções disponíveis com length < 0 ou > 4";
			return null;
		}
		}
	}

	enum Direcao {
		DIREITA, ESQUERDA, ACIMA, ABAIXO;

		public static Direcao getRandomDirecao() {
			int index = MyRandom.global().randomInRange(0, values().length - 1);
			return values()[index];
		}
	}

}
