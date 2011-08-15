package br.org.ita.passatempo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.ita.jogoscruzadoscomum.GeradorMatrizException;
import br.org.ita.numerox.GeradorNumerox;
import br.org.ita.numerox.MatrizNumerox;

/**
 * Servlet implementation class Numerox
 */
public class Numerox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GeradorNumerox gerador = new GeradorNumerox();
		
		gerador.setLargura(20);
		gerador.setAltura(20);
		gerador.setMaximoAlgarismos(12);
		gerador.setMinimoAlgarismos(5);
		
		MatrizNumerox numerox;
		try {
			numerox = gerador.gerarMatriz();
		} catch (GeradorMatrizException e) {
			throw new ServletException(e);
		}
		
		request.setAttribute("numerox", numerox);
		request.setAttribute("bancoNumeros", gerador.getBancoNumeros());
		
		request.getRequestDispatcher("numerox/viewer.jsp").forward(request, response);
	}
}
