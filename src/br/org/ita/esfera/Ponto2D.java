package br.org.ita.esfera;

public class Ponto2D {
	private int x;
	private int y;
	
	public Ponto2D() {
		super();
	}
	
	public Ponto2D(int x, int y) {
		this();
		
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
}
