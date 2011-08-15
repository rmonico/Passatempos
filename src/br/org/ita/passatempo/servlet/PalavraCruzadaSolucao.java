package br.org.ita.passatempo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.ita.palavracruzada.CorretorPalavraCruzada;
import br.org.ita.palavracruzada.MatrizPalavraCruzada;

/**
 * Servlet implementation class PalavraCruzadaSolucao
 */
public class PalavraCruzadaSolucao extends HttpServlet {
	private static final long serialVersionUID = 1L;

/*	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		MatrizPalavraCruzada matriz = (MatrizPalavraCruzada) request
				.getSession().getAttribute("matriz");
		
		Character[][] respostas = new Character[matriz.getLargura()][matriz.getAltura()];

		for (int x = 0; x < matriz.getLargura(); x++) {
			for (int y = 0; y < matriz.getAltura(); y++) {
				String resposta = request.getParameter("inputText_L" + y + "C" + x);
				
				respostas[x][y] = (resposta != null ? resposta.charAt(0) : null);
			}
		}
		
		CorretorPalavraCruzada corretorPalavraCruzada = new CorretorPalavraCruzada();

		corretorPalavraCruzada.setMatriz(matriz);
		corretorPalavraCruzada.setRespostas(respostas);
		
		
		corretorPalavraCruzada.corrigir();
		
		request.setAttribute("corretorPalavraCruzada", corretorPalavraCruzada);
		request.getRequestDispatcher("palavracruzada/solucao.jsp").forward(request, response);
	}*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MatrizPalavraCruzada matriz = (MatrizPalavraCruzada) request
				.getSession().getAttribute("matriz");
		
		Character[][] respostas = new Character[matriz.getLargura()][matriz.getAltura()];

		for (int x = 0; x < matriz.getLargura(); x++) {
			for (int y = 0; y < matriz.getAltura(); y++) {
				String resposta = request.getParameter("inputText_L" + y + "C" + x);
				
				respostas[x][y] = (resposta != null ? resposta.charAt(0) : null);
			}
		}
		
		CorretorPalavraCruzada corretorPalavraCruzada = new CorretorPalavraCruzada();

		corretorPalavraCruzada.setMatriz(matriz);
		corretorPalavraCruzada.setRespostas(respostas);
		
		
		corretorPalavraCruzada.corrigir();
		
		request.setAttribute("corretorPalavraCruzada", corretorPalavraCruzada);
		request.getRequestDispatcher("palavracruzada/solucao.jsp").forward(request, response);
	}
}
