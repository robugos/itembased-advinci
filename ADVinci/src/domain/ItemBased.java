package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import domain.Pearson;

public class ItemBased {
	
	private Pearson correlacao = new Pearson();
	private int itemA, userA, k;
	private static Map<String, String> Possib_Neighs;
	private double d = 0.5;
    private static List<String> matrix = new ArrayList<String>();
    private int iteracao, numSimilares = 0;
	static /*limpar essa inserção depois*/
    {
		
		//PONTO:ITEM:USER
    	matrix.add("P3:I1:U1");
		matrix.add("P1:I2:U1");
		matrix.add("P0:I3:U1");
		matrix.add("P0:I1:U2");
		matrix.add("P3:I2:U2");
		matrix.add("P0:I3:U2");
		matrix.add("P0:I1:U3");
		matrix.add("P4:I2:U3");
		matrix.add("P3:I3:U3");
		matrix.add("P4:I1:U4");
		matrix.add("P0:I2:U4");
		matrix.add("P0:I3:U4");
    }
	

	
	/*public List<String> getItensUA(int userA) {
		for (String item : matrix.keySet()){
			if (item.contains(":"+userA) && !matrix.get(item).isEmpty()){
				String[] parts = item.split(":");
				itensUA.add(parts[0]+":"+matrix.get(item));
			}
		}
		//System.out.println(itensUA);
		return itensUA;
	}*/
	public List<String> getItensUA(int userA){
		List<String> itensUA = new ArrayList<String>();
		for (String item : matrix){
			if (item.contains(":U"+userA)){
				String[] parts = item.split(":");
				itensUA.add(parts[2].substring(1, 2));
			}
		}
		return itensUA;
	}
	
	/*public List<Long> listaItem(int itemN){
		List<Long> lista = new ArrayList<Long>();
		for (String item : matrix.keySet()){
			if (item.contains(itemN+":") && !matrix.get(item).isEmpty()){
				lista.add((long) Integer.parseInt(matrix.get(item)));
				
			}else if (item.contains(itemN+":") && matrix.get(item).isEmpty()){
				lista.add(null);
				
			}
		}
		//System.out.println(lista);
		return lista;
	}*/
	public List<Long> listaItem(int itemN){
		List<Long> lista = new ArrayList<Long>();
		for (String item : matrix){
			if (item.contains(":I"+itemN) && !item.isEmpty()){
				//System.out.println(item.substring(3,6));
				lista.add((long) Integer.parseInt(item.substring(4, 5)));
				
			}else if (item.contains(":I"+itemN) && item.isEmpty()){
				lista.add(null);
				
			}
		}
		//System.out.println(lista);
		return lista;
	}
	
	public int somaItem(int itemN){
		int somaI = 0;
		for (String item : matrix){
			if (item.contains(":I"+itemN+":") && !item.isEmpty()){
				somaI = somaI+Integer.parseInt(item.substring(4, 5));
			}
		}
		//System.out.println(mediaIA);
		return somaI;				
	}
	
	public double[] mediaItem(int itemN){
		double mediaI = 0;
		double q = 0;
		for (String item : matrix){
			if (item.contains(":I"+itemN+":") && !item.isEmpty()){
				q = q+1;
			}
		}
		mediaI = somaItem(itemN)/q;
		double[] media = {mediaI,q};
		//System.out.println(mediaIA);
		return media;				
	}
	
	public double varianciaItem(int itemN){
		double sumMinusAverage = somaItem(itemN)-mediaItem(itemN)[0];
		double varianciaI = sumMinusAverage*sumMinusAverage/((somaItem(itemN)*mediaItem(itemN)[1])-1);

		return varianciaI;
	}

	public void CalculaSimilares(int itemA, int userA){
		setItemA(itemA);
		setUserA(userA);
		//System.out.print(itemA+" "+userA);
		for (String item : getItensUA(userA)){
			String[] parts = item.split(":");
			System.out.println("ITEM: "+item);
			System.out.println("ARRAY: "+Arrays.toString(parts));
			//System.out.println("Antes das médias");
			////if ((mediaItem(itemA)[0]-mediaItem(itemN)[0]<=getD())){
				///double s = correlacao.correlacao(listaItem(getItemA()), listaItem(itemN));
				////System.out.println("|"+s);
				////if (s > 0.0) {
					
				////}
				
			////}			
		}
		
		
	}

	private void ordena(Map<String,String> possib_Neighs) {
		Map<String,Integer> chaves = new HashMap<String,Integer>();
		Map<String,Integer> valores = new HashMap<String,Integer>();
		for (int i=0; i<possib_Neighs.size(); i++){
			chaves.put((String) getKeyFromValue(possib_Neighs,possib_Neighs.get(i)), i);
			valores.put(possib_Neighs.get(i), i);
			
		}
		System.out.println(chaves);
		System.out.println(valores);
		
		
	}
	
	public static Object getKeyFromValue(Map<String,String> hm, Object value){
		for (Object o : hm.keySet()){
			if (hm.get(o).equals(value)){
				return o;
			}
		}
		return null;
	}

	public Map<String, String> getPossib_Neighs() {
		return Possib_Neighs;
	}

	public void setPossib_Neighs(Map<String, String> possib_Neighs) {
		Possib_Neighs = possib_Neighs;
	}
	
	public int getItemA() {
		return itemA;
	}

	public void setItemA(int itemA) {
		this.itemA = itemA;
	}

	public int getUserA() {
		return userA;
	}

	public void setUserA(int userA) {
		this.userA = userA;
	}	

	public List<String> getMatrix() {
		return matrix;
	}

	public void setMatrix(List<String> matrix) {
		ItemBased.matrix = matrix;
	}
	
	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
	public int getIteracao() {
		return iteracao;
	}

	public void setIteracao(int iteracao) {
		this.iteracao = iteracao;
	}

	public int getNumSimilares() {
		return numSimilares;
	}

	public void setNumSimilares(int numSimilares) {
		this.numSimilares = numSimilares;
	}
	

}
