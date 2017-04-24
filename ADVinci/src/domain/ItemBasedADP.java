package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import domain.Pearson;

public class ItemBasedADP {
	private List<String> matrix = new ArrayList<String>();
	{
		//IEM1
		/*matrix.add("I1:U1:P4");
		matrix.add("I1:U2:P4");
		matrix.add("I1:U3:P4");
		matrix.add("I1:U4:P5");
		matrix.add("I1:U5:P5");
		//ITEM2
		matrix.add("I2:U1:P1");
		matrix.add("I2:U2:P1");
		//matrix.add("I2:U3:P3");
		matrix.add("I2:U4:P2");
		matrix.add("I2:U5:P2");
		//ITEM3
		matrix.add("I3:U1:P1");
		matrix.add("I3:U2:P4");
		matrix.add("I3:U3:P4");
		matrix.add("I3:U4:P5");
		matrix.add("I3:U5:P5");*/

		//IEM1
		matrix.add("I1:U1:P1");
		matrix.add("I1:U2:P1");
		matrix.add("I1:U3:P1");
		matrix.add("I1:U4:P2");
		matrix.add("I1:U5:P2");
		//ITEM2
		matrix.add("I2:U1:P1");
		matrix.add("I2:U2:P1");
		//matrix.add("I2:U3:P3");
		matrix.add("I2:U4:P2");
		matrix.add("I2:U5:P2");
		//ITEM3
		matrix.add("I3:U1:P5");
		matrix.add("I3:U2:P5");
		matrix.add("I3:U3:P5");
		matrix.add("I3:U4:P5");
		matrix.add("I3:U5:P5");
	}
	private List<String> possib_neighs = new ArrayList<String>();
	private double d = 1.0;
	private int k = 20;
	private int iteracao, numSimilares = 0;

	private Pearson correlacao = new Pearson();
	
	
	public 	List<Integer> getUserItens(int user){
		List<Integer> itensUser = new ArrayList<Integer>();	
		for (String inter : matrix){
			if (inter.contains("U"+user)){
				itensUser.add(Integer.parseInt(inter.substring(1, 2)));
			}
		}
		System.out.println("Itens do usuário: "+itensUser);
		return itensUser;
	}
	
	public List<Long> getItensValue(int item){
		List<Long> itensValue = new ArrayList<Long>();	
		for (String inter : matrix){
			if (inter.contains("I"+item)){
				itensValue.add((long)Integer.parseInt(inter.substring(7, inter.length())));
			}
		}
		//System.out.println("Valores dos item "+item+": "+itensValue);
		return itensValue;
	}
	
	public List<Long> getFinalList(int item, int value){
		List<Long> itensValue = getItensValue(item);
		List<Long> finalList = new ArrayList<Long>();
		for (int i=0; i<itensValue.size(); i++){
			if (i==item){
				finalList.add((long)value);
				finalList.add(itensValue.get(i));
			}else{
				finalList.add(itensValue.get(i));
			}
		}
		return finalList;
	}
	
	public int[] getSomaItem(int item){
		int soma = 0, q = 0;
		for (String inter : matrix ){
			if (inter.contains("I"+item)){
				soma = soma + Integer.parseInt(inter.substring(7, inter.length()));
				q = q + 1;
			}
		}
		int[] somaItem = {soma, q};
		System.out.println("Soma do item "+item+" e quantidade: "+Arrays.toString(somaItem));
		return somaItem;
	}
	
	public double getMediaItem(int item){
		int[] soma;
		double mediaItem = 0.0;
		soma = getSomaItem(item);
		mediaItem = (double)soma[0]/(double)soma[1];
		System.out.println("Média do item "+item+": "+mediaItem);
		return mediaItem;
	}
	
	public double getVarianciaItem(int item){
		int[] soma = getSomaItem(item); 
		double sumMinusAverage = (double)soma[0] - getMediaItem(item);
		double varianciaItem = sumMinusAverage*sumMinusAverage/((soma[0]*soma[1])-1);
		System.out.println("Variância do item "+item+": "+varianciaItem);
		return varianciaItem;
	}
	
	
	
	
	public void CalculaSimilares(int itemA, int userA){
		System.out.println("Cálculos para o item alvo "+itemA+" e usuário alvo "+userA+":");
		for (int item : getUserItens(userA)){
			System.out.println("Difirença da média do item alvo "+itemA+" e da média do item "+item+": "+ Math.abs(getMediaItem(itemA)-getMediaItem(item)));
			//System.out.println("Valores de "+itemA+" :"+getMediaItem(itemA));
			//System.out.println("Valores de "+item+" :"+getMediaItem(item));
			//double s = correlacao.correlacao(getFinalList(itemA,0), getItensValue(item));
			//System.out.println("Correlação dos itens "+itemA+" e "+item+": "+s);
			//System.out.println("Lista do item alvo "+itemA+": "+getFinalList(itemA,0));
			//System.out.println("Lista do item "+item+": "+getItensValue(item));
			System.out.println("Diferença das médias: "+Math.abs(getMediaItem(itemA)-getMediaItem(item)));
			if (Math.abs(getMediaItem(itemA)-getMediaItem(item)) <= getD()){
				double s = correlacao.correlacao(getFinalList(itemA,0), getItensValue(item));
				System.out.println("Correlação dos itens: "+s);
				if (s >= 0.0){
					//p = r*sqrt((n-2)/(1-r^2))
					double p = s * Math.sqrt((getItensValue(item).size() - 2) / (1 - s*s));
					getPossib_neighs().add(p+":"+getNumSimilares()+":0");
					getPossib_neighs().add(s+":"+getNumSimilares()+":1");
					setNumSimilares(getNumSimilares()+ 1);
				}
				
			}
			System.out.println("Possib Neighs: "+ getPossib_neighs());
		}
		ordenaPossib_Neighs(getPossib_neighs());
		double limite = getNumSimilares()/2.0;
		int j = 0;
		while (j < limite && getNumSimilares() < getK()){
			//getPossib_neighs().add(p+":"+getNumSimilares()+":0");
			for (String item : getPossib_neighs()){
				if (item.contains(":"+j+":")){
					String[] parts = item.split(":");
					if (parts[2] == "0"){
						getPossib_neighs().add(parts[0]+getNumSimilares()+"0");
					}else{
						getPossib_neighs().add(parts[0]+getNumSimilares()+"1");
					}					
				}
			}
			setNumSimilares(getNumSimilares()+ 1);
		}
		
		/*AQUI double acres = 1 -*/ 
		
		
	}
	
	public void ordenaPossib_Neighs(List<String> possib_neighs){
		Collections.sort(possib_neighs);
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public List<String> getPossib_neighs() {
		return possib_neighs;
	}

	public void setPossib_neighs(List<String> possib_neighs) {
		this.possib_neighs = possib_neighs;
	}
	
	public int getNumSimilares() {
		return numSimilares;
	}

	public void setNumSimilares(int numSimilares) {
		this.numSimilares = numSimilares;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

}
