package br.org.ita.jogoscruzadoscomum;

public interface GeradorMatriz {
	
	public void setLargura(int largura);
	public int getLargura();
	
	public int getAltura();
	public void setAltura(int altura);
	
	public void checarParametros() throws GeradorMatrizException;
	
	public Matriz<? extends Celula, ? extends SequenciaPosicionada<? extends Celula>> gerarMatriz() throws GeradorMatrizException;
}
