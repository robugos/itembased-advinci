package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.Pearson;

public class ItemBased {
	
	private Pearson correlacao = new Pearson();
	private static Map<String,String> Possib_Neighs = new HashMap<String,String>();
	private int itemA, userA, k;
	private double d = 0.5;
    private static Map<String,String> matrix = new HashMap<String,String>();
    private int iteracao, numSimilares = 0;
	private ArrayList<String> itensUA = new ArrayList<String>();
	static /*limpar essa inserção depois*/
    {
    	matrix.put( "1:1", new String("3"));
		matrix.put( "2:1", new String("1"));
		matrix.put( "3:1", new String());
		matrix.put( "1:2", new String());
		matrix.put( "2:2", new String("3"));
		matrix.put( "3:2", new String());
		matrix.put( "1:3", new String());
		matrix.put( "2:3", new String("4"));
		matrix.put( "3:3", new String("3"));
		matrix.put( "1:4", new String("4"));
		matrix.put( "2:4", new String());
		matrix.put( "3:4", new String());
    }
	

	
	public ArrayList<String> getItensUA(int userA) {
		for (String item : matrix.keySet()){
			if (item.contains(":"+userA) && !matrix.get(item).isEmpty()){
				String[] parts = item.split(":");
				itensUA.add(parts[0]+":"+matrix.get(item));
			}
		}
		//System.out.println(itensUA);
		return itensUA;
	}
	
	public List<Long> listaItem(int itemN){
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
	}
	
	public int somaItem(int itemN){
		int somaI = 0;
		for (String item : matrix.keySet()){
			if (item.contains(itemN+":") && !matrix.get(item).isEmpty()){
				somaI = somaI+Integer.parseInt(matrix.get(item));
			}
		}
		//System.out.println(mediaIA);
		return somaI;				
	}
	
	public double[] mediaItem(int itemN){
		double mediaI = 0;
		double q = 0;
		for (String item : matrix.keySet()){
			if (item.contains(itemN+":") && !matrix.get(item).isEmpty()){
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

	public void setItensUA(ArrayList<String> itensUA) {
		this.itensUA = itensUA;
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

	public Map<String,String> getMatrix() {
		return matrix;
	}

	public void setMatrix(Map<String,String> matrix) {
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
	
	public void CalculaSimilares(int itemA, int userA){
		setItemA(itemA);
		setUserA(userA);
		for (String item : getItensUA(getUserA())){
			String[] parts = item.split(":");
			//System.out.println("Antes das médias");
			if ((mediaItem(getItemA())[0]-mediaItem(Integer.parseInt(parts[0]))[0]<=getD())){
				double s = correlacao.correlacao(listaItem(getItemA()), listaItem(Integer.parseInt(parts[0])));
				System.out.println("|"+s);
				if (s > 0.0) {
					getPossib_Neighs().put(getNumSimilares()+":0", new String(""+mediaItem(itemA)[0]+varianciaItem(itemA)));
					getPossib_Neighs().put(getNumSimilares()+":1", new String(""+s));
					numSimilares = numSimilares + 1;
					//System.out.println("S É MAIOR QUE 0.0 = "+s);
				}
				
			}			
		}
		
		
		
		
	}

	public Map<String, String> getPossib_Neighs() {
		return Possib_Neighs;
	}

	public void setPossib_Neighs(Map<String, String> possib_Neighs) {
		Possib_Neighs = possib_Neighs;
	}
	

}
