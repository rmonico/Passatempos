package br.org.ita.esfera;

import java.awt.Graphics;

public interface EsferaRenderizavel {
	
	public void drawPoint(double alpha, double beta);
	
	public void setRaio(double raio);
	
	public double getRaio();
	
	public void renderizar(Graphics g);
	
	public void setViewerDistance(double viewerDistance);
	
	public double getViewerDistance();
	
	public double getX();
	
	public void setX(double x);
	
	public double getY();
	
	public void setY(double y);
	
	public double getZ();
	
	public void setZ(double z);
}
