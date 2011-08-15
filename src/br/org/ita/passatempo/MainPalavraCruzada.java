package br.org.ita.passatempo;

import br.org.ita.palavracruzada.CelulaPalavraCruzada;
import br.org.ita.palavracruzada.GeradorPalavraCruzada;
import br.org.ita.palavracruzada.MatrizPalavraCruzada;
import br.org.ita.palavracruzada.Palavra;

public class MainPalavraCruzada {

	public static void main(String[] args) {
		GeradorPalavraCruzada gerador = new GeradorPalavraCruzada();

		gerador.getBancoPalavras().add(new Palavra("papagaio", "Dica da bola"));
		gerador.getBancoPalavras().add(new Palavra("sapo", "Dica da porta"));
		gerador.getBancoPalavras().add(new Palavra("queijo", "Dica do ovo"));
		gerador.getBancoPalavras().add(new Palavra("raiz", "Dica do raio"));
		gerador.getBancoPalavras().add(new Palavra("arvore", "Dica do casa"));
		gerador.getBancoPalavras().add(new Palavra("praia", "Dica do carro"));
		gerador.getBancoPalavras().add(new Palavra("tubarao", "Dica do anus"));

		gerador.getBancoPalavras().add(new Palavra("patria", "Dica do patria"));
		gerador.getBancoPalavras().add(new Palavra("carroca", "Dica da carroça"));
		gerador.getBancoPalavras().add(new Palavra("plateia", "Dica da plateia"));
		gerador.getBancoPalavras().add(new Palavra("orlando", "Dica do orlando"));

		gerador.getBancoPalavras().add(new Palavra("xuxa", "Dica da onomatopeia"));
		gerador.getBancoPalavras().add(new Palavra("ceu", "Dica da maravilhoso"));
		gerador.getBancoPalavras().add(new Palavra("constelacao", "Dica da constituicao"));
		gerador.getBancoPalavras().add(new Palavra("megalomaniaco", "Dica da constituicao"));
		gerador.getBancoPalavras().add(new Palavra("aracnofobia", "Dica da constituicao"));
		gerador.getBancoPalavras().add(new Palavra("churrasco", "Dica da constituicao"));
		gerador.getBancoPalavras().add(new Palavra("veu", "Dica da constituicao"));
		gerador.getBancoPalavras().add(new Palavra("onomatopeia", "Dica da onomatopeia"));
		gerador.getBancoPalavras().add(new Palavra("ourico", "Dica do ourico"));

		gerador.setAltura(20);
		gerador.setLargura(20);

		MatrizPalavraCruzada matriz = gerador.gerarMatriz();

		System.out.println(" 0123456789");
		for (int y=0; y <= matriz.getUltimaLinha(); y++) {
			System.out.print(y);

			for (int x = 0; x <= matriz.getUltimaColuna(); x++) {
				CelulaPalavraCruzada ch = matriz.getCelula(x, y);

				System.out.print(ch.toChar());
			}

			System.out.println();
		}
	}
}
