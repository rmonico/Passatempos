package br.org.ita.jogoscruzadoscomum;

public interface SequenciaPosicionada<T extends Celula> extends Sequencia<T> {

	public void setDisposicao(Disposicao disposicao);
	public Disposicao getDisposicao();
	
	public void setColunaInicio(int coluna);
	public int getColunaInicio();
	public int getColunaFim();
	
	public void setLinhaInicio(int linha);
	public int getLinhaInicio();
	public int getLinhaFim();
	
	public int getLargura();
	public int getAltura();

}
