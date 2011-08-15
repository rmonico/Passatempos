package br.org.ita.passatempo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.ita.palavracruzada.GeradorPalavraCruzada;
import br.org.ita.palavracruzada.MatrizPalavraCruzada;
import br.org.ita.palavracruzada.Palavra;

/**
 * Servlet implementation class PalavraCruzada
 */
public class PalavraCruzada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GeradorPalavraCruzada gerador = new GeradorPalavraCruzada();
		
		gerador.getBancoPalavras().add(new Palavra("bola", "Esfera"));
		gerador.getBancoPalavras().add(new Palavra("porta", "Abertura na parede"));
		gerador.getBancoPalavras().add(new Palavra("patrick", "Melhor amigo do Bob Esponja"));
		gerador.getBancoPalavras().add(new Palavra("raio", "Relâmpago"));
		gerador.getBancoPalavras().add(new Palavra("casa", "Moradia"));
		gerador.getBancoPalavras().add(new Palavra("carro", "Meio de transporte"));
		gerador.getBancoPalavras().add(new Palavra("melt", "Degelo (inglês)"));
		gerador.getBancoPalavras().add(new Palavra("patria", "Nação"));
		gerador.getBancoPalavras().add(new Palavra("coroa", "Acima do rei"));
		gerador.getBancoPalavras().add(new Palavra("libelula", "Inseto voador de quatro asas"));
		gerador.getBancoPalavras().add(new Palavra("fogao", "Prepara a comida"));
		gerador.getBancoPalavras().add(new Palavra("cinco", "Quarta decimal do pi"));
		gerador.getBancoPalavras().add(new Palavra("oito", "Terceira decimal do e"));
		gerador.getBancoPalavras().add(new Palavra("constituicao", "Conjunto de leis de um pais"));
		gerador.getBancoPalavras().add(new Palavra("telurico", "Rochoso"));
		gerador.getBancoPalavras().add(new Palavra("cama", "Catre"));
		gerador.getBancoPalavras().add(new Palavra("mitocondria", "Organela celular que guarda energia"));
		gerador.getBancoPalavras().add(new Palavra("cogumelos", "? azuis: comp. mus. de Ventania"));
		gerador.getBancoPalavras().add(new Palavra("phobos", "Lua de Marte"));
		gerador.getBancoPalavras().add(new Palavra("eu", "Primeira pessoa sing."));
		gerador.getBancoPalavras().add(new Palavra("pa", "Cava buracos no chão"));
		gerador.getBancoPalavras().add(new Palavra("po", "Ao ? voltarás"));
		gerador.getBancoPalavras().add(new Palavra("martins", "Eduardo ? Guerra"));
		gerador.getBancoPalavras().add(new Palavra("ita", "Instituto Tecnológico da Aeronaútica"));
		
		gerador.setAltura(10);
		gerador.setLargura(10);
		
		MatrizPalavraCruzada matriz = gerador.gerarMatriz();
		
		request.getSession().setAttribute("matriz", matriz);
		
		request.getRequestDispatcher("palavracruzada/viewer.jsp").forward(request, response);
		
		//super.service(request, response);
	}

}
