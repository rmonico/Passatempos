package br.org.ita.passatempo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class Testes {
	
	private static class MinhaClasse {}

	static Vector<List<MinhaClasse>> vector2 = new Stack<List<MinhaClasse>>();
	
	static void testGenericAdd() {
		vector2.add(new ArrayList<MinhaClasse>());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("═║╔╗╚╝╠╣╦╩╬");
		Integer i = 1;
		
		System.out.println(String.format("%02d", i));
		
		testGenericAdd();

		testInterruptedFor();
		
		testQueue();
		
		testVector();

	}

	private static void testVector() {
		Stack<String> s = new Stack<String>();
		
		s.push("string 0");
		s.push("string 1");
		s.push("string 2");
		s.push("string 3");
		
		System.out.println(s);
		
		s.push("string 4");
		
		System.out.println(s);
		
		System.out.println("poping: " + s.pop());
		
		System.out.println(s);
		
		System.out.println("poping: " + s.pop());
		
		System.out.println(s);
		
		System.out.println("poping: " + s.pop());
		
		System.out.println(s);
	}

	private static void testQueue() {
		Queue<String> q = new PriorityQueue<String>();
		
		q.offer("String 1");
		q.offer("String 2");
		q.offer("String 3");
		q.offer("String 4");
		q.offer("String 5");
		
		System.out.println(q);
		
		System.out.println(q.poll());
		System.out.println(q);

		System.out.println(q.poll());
		System.out.println(q);
		
		System.out.println(q.poll());
		System.out.println(q);
	}

	private static void testInterruptedFor() {
		List<String> listaPalavra = new ArrayList<String>();
		
		listaPalavra.add("palavra 0");
		listaPalavra.add("palavra 1");
		listaPalavra.add("palavra 2");
		listaPalavra.add("palavra 3");
		listaPalavra.add("palavra 4");
		listaPalavra.add("palavra 5");
		listaPalavra.add("palavra 6");
		listaPalavra.add("palavra 7");
		listaPalavra.add("palavra 8");
		listaPalavra.add("palavra 9");
		
		for (int i=0; i<listaPalavra.size(); i++) {
			System.out.println("i = " + i + "; Testando: " + listaPalavra.get(i) + "; lista = " + listaPalavra);
			
			if ((listaPalavra.get(i) == "palavra 4") || (listaPalavra.get(i) == "palavra 6")) {
				listaPalavra.remove(i);
				i--;
			}
		}
		
	}

}
