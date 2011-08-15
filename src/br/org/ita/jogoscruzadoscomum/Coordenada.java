package br.org.ita.jogoscruzadoscomum;



public class Coordenada implements Cloneable {
	private int coluna;
	private int linha;
	
	public Coordenada() {
		super();
	}
	
	public static Coordenada gerarAleatorio(int minColuna, int minLinha, int maxColuna, int maxLinha) {
		Coordenada coordenada = new Coordenada(MyRandom.global().randomInRange(minColuna, maxColuna), MyRandom.global().randomInRange(minLinha, maxLinha));
		
		return coordenada;
	}
	
	public Coordenada(int coluna, int linha) {
		this();
		
		this.coluna = coluna;
		this.linha = linha;
	}
	
	/**
	 * @return the coluna
	 */
	public int getColuna() {
		return coluna;
	}
	/**
	 * @param coluna the coluna to set
	 */
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	/**
	 * @return the linha
	 */
	public int getLinha() {
		return linha;
	}
	/**
	 * @param linha the linha to set
	 */
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public Coordenada clone() {
		try {
			return (Coordenada) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "C " + coluna + " L " + linha;
	}
}
