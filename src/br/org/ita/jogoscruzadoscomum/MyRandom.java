package br.org.ita.jogoscruzadoscomum;

import java.util.Calendar;
import java.util.Random;

public class MyRandom extends Random {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8495805795945848081L;
	private static MyRandom globalRandom = new MyRandom();
	
	public static MyRandom global() {
		return globalRandom;
	}
	
	public MyRandom() {
//		super();
		super(Calendar.getInstance().get(Calendar.MILLISECOND));
	}

	
	public int randomInRange(int min, int max) {
		return nextInt(max-min+1) + min;
//		return min;
//		return max;
	}
}
