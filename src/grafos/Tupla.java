package grafos;


public class Tupla<T> implements Comparable<Tupla<T>>{
	private double peso;
	private T nodo1;
	private T nodo2;

	public Tupla(T nodo1, T nodo2) {
		this.peso = 1;
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}

	public Tupla(T nodo1, T nodo2, double peso) {
		this.peso = peso;
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public T getNodo1() {
		return nodo1;
	}
	
	public T getNodo2() {
		return nodo2;
	}

	@Override
	public int compareTo(Tupla<T> otraTupla) {
		return Double.compare(this.peso, otraTupla.peso);
	}
	
	@Override
	public String toString() {
		return "" + nodo1 + " -> " + nodo2 + " : " + peso;
	}
}
