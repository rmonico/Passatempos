package br.org.ita.esfera;

import java.awt.Graphics;

import javax.swing.JFrame;


public class EsferaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1202719137655706628L;
	
	private EsferaRenderizavel esfera;

	public EsferaFrame() {
		super("Labirinto - teste");
		
		setSize(600, 400);
		
		setLocation(100, 100);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
//		LayoutManager layout = new GroupLayout(this);
		
//		setLayout(layout);
		
//		JTextField label = new JTextField();
//		this.add(label);
		
	}

	private static final int MIN_ALPHA = 0;
	private static final int MAX_ALPHA = 360;
	private static final int INC_ALPHA = 10;
	
	private static final int MIN_BETA = 0;
	private static final int MAX_BETA = 180;
	private static final int INC_BETA = 10;
	
	public void run() {
		setVisible(true);
		
		esfera = new EsferaRenderizador();
		
		esfera.setRaio(200);
		
		esfera.setViewerDistance(140);
		
		esfera.setX(0);
		esfera.setY(0);
		esfera.setZ(300);
		
		for (int alpha = MIN_ALPHA; alpha <= MAX_ALPHA; alpha += INC_ALPHA) {
			for (int beta = MIN_BETA; beta <= MAX_BETA; beta += INC_BETA) {
				esfera.drawPoint(Math.toRadians(alpha), Math.toRadians(beta));
			}
		}
	}
	
	public void paint(Graphics g) {
		esfera.renderizar(g);
	}
	
	
}
