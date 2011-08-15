package br.org.ita.passatempo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.ita.labirinto.Gerador;
import br.org.ita.labirinto.GeradorLabirinto;

/**
 * Servlet implementation class Labirinto
 */
public class Labirinto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 209514220463233876L;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		GeradorLabirinto gerador = new GeradorDemo();
		
		GeradorLabirinto gerador = new Gerador();
		
		br.org.ita.labirinto.Labirinto labirinto = gerador.gerar();
		
		request.setAttribute("labirinto", labirinto);
		
		request.getRequestDispatcher("labirinto/viewer.jsp").forward(request, response);
		
	}
       
}
