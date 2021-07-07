package grafos;


public class Mst<T> {
	private GrafoDirigido<T> grafo;
	private double costo;
	
	public Mst(GrafoDirigido<T> grafo, double costo) {
		this.grafo = grafo;
		this.costo = costo;
	}

	public GrafoDirigido<T> getGrafo() {
		return grafo;
	}

	public double getCosto() {
		return costo;
	}
	
	
	
}
