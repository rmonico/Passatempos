package br.org.ita.esfera;

public class Ponto3D {

	private double x;
	private double y;
	private double z;
	
	public Ponto3D() {
		super();
	}
	
	public Ponto3D(double x, double y, double z) {
		this();
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	public Ponto2D project(double viewerDistance) {
		Ponto2D p = new Ponto2D();
		
		p.setX((int)((viewerDistance * x) / z));
		p.setY((int)((viewerDistance * y) / z));
		
		return p;
	}
}
