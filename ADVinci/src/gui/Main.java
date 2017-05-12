package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.ItemBased;
import domain.ItemBasedADP;
import domain.Pearson;
import domain.Recommender;


public class Main {
	
	static Recommender rec = new Recommender();
	static Pearson teste = new Pearson();
	static ItemBased ib = new ItemBased();
	static ItemBasedADP iba = new ItemBasedADP();
	private static List<Long> lista1 = new ArrayList<Long>();
	private static List<Long> lista2 = new ArrayList<Long>();
	static int linha1[] = {3,1,0};
	static int linha2[] = {0,3,0};
	static int linha3[] = {0,4,3};
	static int linha4[] = {4,0,0};
	static int[] m[] = {linha1,linha2,linha3,linha4};
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		getLista1().add((long) 1);
		getLista1().add((long) 1);
		getLista1().add((long) 0);
		getLista1().add((long) 2);
		getLista1().add((long) 2);
		
		getLista2().add((long) 4);
		getLista2().add((long) 4);
		getLista2().add((long) 4);
		getLista2().add((long) 5);
		getLista2().add((long) 5);
		
		//getLista2().add((long) 1);
		//getLista2().add((long) 4);
		//getLista2().add(null);
		//getLista2().add(null);
		
		//System.out.print(teste.correlacao(getLista1(), getLista2()));
		
		/*for (int i=0; i < m.length; i++){
			Arrays.asList(m).get(3);
		}*/
		
		//ib.getItensUA(1);
		//ib.CalculaSimilares(2,1);
		//iba.getUserItens(4);
		//iba.getSomaItem(3);
		
		//iba.CalculaSimilares(2, 3);
		
		try {
			Recommender.calcular();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static List<Long> getLista1() {
		return lista1;
	}



	public void setLista1(List<Long> lista) {
		Main.lista1 = lista;
	}



	public static List<Long> getLista2() {
		return lista2;
	}



	public void setLista2(List<Long> lista) {
		Main.lista2 = lista;
	}

}
