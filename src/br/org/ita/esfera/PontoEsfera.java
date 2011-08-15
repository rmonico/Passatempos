package br.org.ita.esfera;

public class PontoEsfera {
	private double alpha;
	private double beta;
	
	public PontoEsfera() {
		super();
	}
	
	public PontoEsfera(double alpha, double beta) {
		this();
		
		this.alpha = alpha;
		this.beta = beta;
	}
	
	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}
	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}
	/**
	 * @param beta the beta to set
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}
	
	public Ponto3D project(double raio, double x, double y, double z) {
		Ponto3D p = new Ponto3D();
		
		p.setX((Math.cos(alpha) * Math.sin(beta) * raio) + x);
		p.setY((Math.sin(alpha) * raio) + y);
		p.setZ((Math.cos(alpha) * Math.cos(beta) * raio) + z);
		
		return p;
	}
	
}
