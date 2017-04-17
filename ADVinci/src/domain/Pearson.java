package domain;
import java.util.ArrayList;
import java.util.List;


public class Pearson {
	
	private List<Long> array1 = new ArrayList<Long>();
	private List<Long> array2 = new ArrayList<Long>();
	private long array1Sum, array2Sum = 0;
	private double sum1, sum2, a, r1, r2, b, b1, b2;
	
	public double correlacao(List<Long> array1, List<Long> array2){
		
		setArray1(array1);
		for (int i = 0; i < getArray1().size(); i++) {
            if (getArray1().get(i) == null) {
            	getArray1().set(i, (long) 0);
            }
        }
		//System.out.print("Array1: "+getArray1()+"\n");
		setArray2(array2);
		//System.out.print("Array2: "+getArray2()+"\n");
		for (int i = 0; i < getArray2().size(); i++) {
            if (getArray2().get(i) == null) {
            	getArray2().set(i, (long) 0);
            }
        }
	
		if (getArray1().size() != getArray2().size()){return -1;}
		for (int i=0; i < getArray1().size(); i++) {
			setArray1Sum(getArray1Sum()+getArray1().get(i));
			//System.out.print("Array1Sum: "+getArray1Sum()+"\n");
		}
		for (int i=0; i < getArray2().size(); i++) {
			setArray2Sum(getArray2Sum()+getArray2().get(i));
			//System.out.print("Array2Sum: "+getArray2Sum()+"\n");
		}
		
		setSum1((double)getArray1Sum()/(double)getArray1().size());
		//System.out.print("Sum1/n: "+getSum1()+"\n");
		setSum2((double)getArray2Sum()/(double)getArray2().size());
		//System.out.print("Sum2/n: "+getSum2()+"\n");
		
		for (int i=0; i < getArray1().size(); i++) {
			setR1((getArray1().get(i)-getSum1()));
			//System.out.print("R1: "+getR1()+"\n");
			setR2((getArray2().get(i)-getSum2()));
			//System.out.print("R2: "+getR2()+"\n");
			setA(getA()+getR1()*getR2());
			//System.out.print("A: "+getA()+"\n");
			setB1(getB1()+Math.pow(getR1(),2));
			//System.out.print("B1: "+getB1()+"\n");
			setB2(getB2()+Math.pow(getR2(),2));
			//System.out.print("B2: "+getB2()+"\n");
		}
		
		setB(Math.sqrt(getB1()*getB2()));
		//System.out.print("B: "+getB()+"\n");
		
		if (getB()==0) { return 0; } else { return getA()/getB(); }

	}
	
	
	public List<Long> getArray1() {
		return array1;
	}


	public void setArray1(List<Long> array1) {
		this.array1 = array1;
	}


	public List<Long> getArray2() {
		return array2;
	}


	public void setArray2(List<Long> array2) {
		this.array2 = array2;
	}
	
	
	public long getArray1Sum() {
		return array1Sum;
	}


	public void setArray1Sum(long array1Sum) {
		this.array1Sum = array1Sum;
	}


	public long getArray2Sum() {
		return array2Sum;
	}


	public void setArray2Sum(long array2Sum) {
		this.array2Sum = array2Sum;
	}
	
	public double getSum2() {
		return sum2;
	}


	public void setSum2(double sum2) {
		this.sum2 = sum2;
	}


	public double getSum1() {
		return sum1;
	}


	public void setSum1(double sum1) {
		this.sum1 = sum1;
	}
	
	
	public double getA() {
		return a;
	}
	
	
	public void setA(double d) {
		this.a = d;
	}


	public double getR1() {
		return r1;
	}


	public void setR1(double d) {
		this.r1 = d;
	}


	public double getR2() {
		return r2;
	}


	public void setR2(double d) {
		this.r2 = d;
	}
	
	public double getB() {
		return b;
	}


	public void setB(double b) {
		this.b = b;
	}


	public double getB1() {
		return b1;
	}


	public void setB1(double b1) {
		this.b1 = b1;
	}


	public double getB2() {
		return b2;
	}


	public void setB2(double b2) {
		this.b2 = b2;
	}


	
}
