package br.org.ita.palavracruzada.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.org.ita.jogoscruzadoscomum.MyRandom;

public class MyRandomTest {

	@Test
	public void testRandomInRange() {
		int zeros = 0;
		int ones = 0;

		for (int i = 0; i < 1000; i++) {
			int j = MyRandom.global().randomInRange(0, 1);

			assertFalse(((j < 0) || (j > 1)));

			switch (j) {
			case 0:
				zeros++;
				break;
			case 1:
				ones++;
				break;
			}
		}
		
		System.out.println("zeros: " + zeros);
		System.out.println("ones: " + ones);
		
		int metadeTotal = (zeros + ones) / 2;
		
		assertTrue("Quantidade discrepante: ", ((zeros >= metadeTotal * 0.95) && (zeros <= metadeTotal * 1.05)));
	}

}
