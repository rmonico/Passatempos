package br.org.ita.jogoscruzadoscomum;



public interface Matriz<C extends Celula, S extends SequenciaPosicionada<C>>{
	public Celula getCelula(int coluna, int linha);
	
	public static Celula celulaPreenchimento = null;

	public int getUltimaColuna();
	
	public int getUltimaLinha();

	int getLargura();

	int getAltura();

	void colocar(S sequencia);

	void setCelula(int x, int y, C celula);
}
