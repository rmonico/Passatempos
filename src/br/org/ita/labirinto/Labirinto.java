package br.org.ita.labirinto;

import br.org.ita.jogoscruzadoscomum.Coordenada;
import br.org.ita.labirinto.Gerador.Direcao;

public class Labirinto {
	private Casa[][] casas;
	private Coordenada inicio = new Coordenada();
	private Coordenada fim = new Coordenada();
	private int colunas;
	private int linhas;
	
	
	public class Casa {
		private boolean caminhoDireita;
		private boolean caminhoAbaixo;
		private int coluna;
		private int linha;
		private boolean ocupado;
		
		public Casa(int coluna, int linha) {
			this.coluna = coluna;
			this.linha = linha;
		}
		/**
		 * @return the caminhoDireita
		 */
		public boolean isCaminhoDireita() {
			return coluna == getUltimaColuna() ? false : caminhoDireita;
		}
		/**
		 * @param caminhoDireita the caminhoDireita to set
		 */
		public void setCaminhoDireita(boolean caminhoDireita) {
			this.caminhoDireita = caminhoDireita;
		}
		/**
		 * @return the caminhoAbaixo
		 */
		public boolean isCaminhoAbaixo() {
			return linha == getUltimaLinha() ? false : caminhoAbaixo;
		}
		/**
		 * @param caminhoAbaixo the caminhoAbaixo to set
		 */
		public void setCaminhoAbaixo(boolean caminhoAbaixo) {
			this.caminhoAbaixo = caminhoAbaixo;
		}
		public boolean isCaminhoEsquerda() {
			if (coluna == 0) {
				return false;
			}
			
			return Labirinto.this.getCasa(coluna-1, linha).isCaminhoDireita();
		}
		public void setCaminhoEsquerda(boolean caminhoEsquerda) {
			if (coluna == 0) {
				return;
			}
			
			Labirinto.this.getCasa(coluna-1, linha).setCaminhoDireita(caminhoEsquerda);
		}
		public boolean isCaminhoAcima() {
			if (linha == 0) {
				return false;
			}
			
			return Labirinto.this.getCasa(coluna, linha-1).isCaminhoAbaixo();
		}
		public void setCaminhoAcima(boolean caminhoCima) {
			if (linha == 0) {
				return;
			}
			
			Labirinto.this.getCasa(coluna, linha-1).setCaminhoAbaixo(caminhoCima);
		}
		public boolean isOcupado() {
			return ocupado;
		}
		public void ocupar() {
			ocupado = true;
		}
		
		@Override
		public String toString() {
			
			if (!isCaminhoAcima() && !isCaminhoDireita() && !isCaminhoAbaixo() && !isCaminhoEsquerda()) {
				return " ";
			} else if (isCaminhoAcima() && isCaminhoDireita() && !isCaminhoAbaixo() && !isCaminhoEsquerda()) {
				return "╚";
			} else if (isCaminhoAcima() && !isCaminhoDireita() && isCaminhoAbaixo() && !isCaminhoEsquerda()) {
				return "║";
			} else if (isCaminhoAcima() && !isCaminhoDireita() && !isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╝";
			} else if (!isCaminhoAcima() && isCaminhoDireita() && isCaminhoAbaixo() && !isCaminhoEsquerda()) {
				return "╔";
			} else if (!isCaminhoAcima() && isCaminhoDireita() && !isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "═";
			} else if (!isCaminhoAcima() && !isCaminhoDireita() && isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╗";
			} else if (isCaminhoAcima() && isCaminhoDireita() && isCaminhoAbaixo() && !isCaminhoEsquerda()) {
				return "╠";
			} else if (isCaminhoAcima() && isCaminhoDireita() && !isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╩";
			} else if (isCaminhoAcima() && !isCaminhoDireita() && isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╣";
			} else if (!isCaminhoAcima() && isCaminhoDireita() && isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╦";
			} else if (isCaminhoAcima() && isCaminhoDireita() && isCaminhoAbaixo() && isCaminhoEsquerda()) {
				return "╬";
			}
			
//			═║╔╗╚╝╠╣╦╩╬
			
			assert false : "Configuração inválida de casa";
			
			return ocupado ? "O" : "N";
		}
		public void setCaminho(Direcao direcao, boolean b) {
			switch (direcao) {
			case ACIMA: {
				setCaminhoAcima(b);
				break;
			}
			
			case DIREITA: {
				setCaminhoDireita(b);
				break;
			}
			
			case ABAIXO: {
				setCaminhoAbaixo(b);
				break;
			}
			
			case ESQUERDA: {
				setCaminhoEsquerda(b);
				break;
			}
			}
			
		}
	}
	
	public Labirinto(int colunas, int linhas) {
		casas = new Casa[colunas][linhas];
		this.colunas = colunas;
		this.linhas = linhas;
	}

	/**
	 * @return the casas
	 */
	public Casa getCasa(int coluna, int linha) {
		if (casas[coluna][linha] == null) {
			casas[coluna][linha] = new Casa(coluna, linha);
		}
		
		return casas[coluna][linha];
	}
	
	public Casa getCasa(Coordenada posicao) {
		return getCasa(posicao.getColuna(), posicao.getLinha());
	}

	/**
	 * @return the inicio
	 */
	public Coordenada getInicio() {
		return inicio;
	}

	/**
	 * @return the fim
	 */
	public Coordenada getFim() {
		return fim;
	}

	public int getUltimaColuna() {
		return colunas-1;
	}
	
	public int getUltimaLinha() {
		return linhas-1;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		for (int y = 0; y <= getUltimaColuna(); y++) {
			for (int x = 0; x <= getUltimaLinha(); x++) {
				
				if (inicio.getColuna() == x && inicio.getLinha() == y) {
					s.append("I");
				} else if (fim.getColuna() == x && fim.getLinha() == y) {
					s.append("F");
				} else {					
					s.append(getCasa(x, y).toString());
				}
			}
			
			s.append("\n");
		}

		return s.toString();
	}
}