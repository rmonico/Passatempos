package br.org.ita.labirinto;

public class GeradorDemo implements GeradorLabirinto {

	@Override
	public Labirinto gerar() {
		Labirinto labirinto = new Labirinto(5, 5);
//		 01234
//		0╔═╦═╗
//		1║ ║ ║
//		2╠═╬═╣
//		3║ ║ ║
//		4╚═╩═╝
		
		labirinto.getInicio().setColuna(0);
		labirinto.getInicio().setLinha(0);
		
		labirinto.getFim().setColuna(labirinto.getUltimaColuna());
		labirinto.getFim().setLinha(labirinto.getUltimaLinha());
		
		labirinto.getCasa(0, 0).setCaminhoAbaixo(true);
		labirinto.getCasa(0, 0).setCaminhoDireita(true);
		
		labirinto.getCasa(1, 0).setCaminhoDireita(true);
		
		labirinto.getCasa(2, 0).setCaminhoDireita(true);
		labirinto.getCasa(2, 0).setCaminhoAbaixo(true);
		
		labirinto.getCasa(3, 0).setCaminhoDireita(true);
		
		labirinto.getCasa(4, 0).setCaminhoAbaixo(true);
		
		labirinto.getCasa(0, 1).setCaminhoAbaixo(true);

		labirinto.getCasa(2, 1).setCaminhoAbaixo(true);

		labirinto.getCasa(4, 1).setCaminhoAbaixo(true);
		
		labirinto.getCasa(0, 2).setCaminhoAbaixo(true);
		labirinto.getCasa(0, 2).setCaminhoDireita(true);
		
		labirinto.getCasa(1, 2).setCaminhoDireita(true);
		
		labirinto.getCasa(2, 2).setCaminhoDireita(true);
		labirinto.getCasa(2, 2).setCaminhoAbaixo(true);
		
		labirinto.getCasa(3, 2).setCaminhoDireita(true);
		
		labirinto.getCasa(4, 2).setCaminhoAbaixo(true);
		
		labirinto.getCasa(0, 3).setCaminhoAbaixo(true);
		
		labirinto.getCasa(2, 3).setCaminhoAbaixo(true);
		
		labirinto.getCasa(4, 3).setCaminhoAbaixo(true);

		labirinto.getCasa(0, 4).setCaminhoDireita(true);

		labirinto.getCasa(1, 4).setCaminhoDireita(true);
		
		labirinto.getCasa(2, 4).setCaminhoDireita(true);
		
		labirinto.getCasa(3, 4).setCaminhoDireita(true);
		
		labirinto.getCasa(4, 4).setCaminhoDireita(true);
		
		return labirinto;
	}

}
