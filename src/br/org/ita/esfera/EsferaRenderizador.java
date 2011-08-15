package br.org.ita.esfera;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class EsferaRenderizador implements EsferaRenderizavel {
	
	private List<PontoEsfera> pontos = new ArrayList<PontoEsfera>();
	private double raio;
	private double viewerDistance = 140;
	private double x;
	private double y;
	private double z;
	
	@Override
	public void drawPoint(double alpha, double beta) {
		pontos.add(new PontoEsfera(alpha, beta));
	}

	@Override
	public void renderizar(Graphics g) {
		
		for (PontoEsfera pe : pontos) {
			Ponto2D p = pe.project(raio, x, y, z).project(viewerDistance);
			
			g.drawLine(p.getX() + 250, p.getY() + 250, p.getX() + 250, p.getY() + 250);
		}
	}

	@Override
	public double getRaio() {
		return raio;
	}

	@Override
	public void setRaio(double raio) {
		this.raio = raio;
	}

	public void setViewerDistance(double viewerDistance) {
		this.viewerDistance = viewerDistance;
	}

	public double getViewerDistance() {
		return viewerDistance;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public double getZ() {
		return z;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void setZ(double z) {
		this.z = z;
	}
}
