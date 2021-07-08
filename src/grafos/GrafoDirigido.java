package grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GrafoDirigido<T> {

	private double[][] m;
	private List<T> vertices;
	private List<Tupla<T>> aristas;

	public GrafoDirigido(List<T> vertices, List<Tupla<T>> aristas) {
		this.vertices = vertices;
		this.aristas = aristas;
		this.m = new double[vertices.size()][vertices.size()];

		// Llena la matriz de INFINITOS
		for (int i = 0; i < this.m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (this.m[i][j] == 0)
					this.m[i][j] = Double.POSITIVE_INFINITY;
			}
		}

		// Llena matriz con costos
		for (Tupla<T> tupla : aristas) {
			T vertice1 = tupla.getNodo1();
			T vertice2 = tupla.getNodo2();

			this.m[this.vertices.indexOf(vertice1)][this.vertices.indexOf(vertice2)] = tupla.getPeso();
		}

	}

	public GrafoDirigido(List<T> vertices) {
		this.vertices = vertices;
		this.aristas = new ArrayList<Tupla<T>>();
		this.m = new double[vertices.size()][vertices.size()];

		// Llena la matriz de INFINITOS
		for (int i = 0; i < this.m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (this.m[i][j] == 0)
					this.m[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}

	public T getVertice(int pos) {
		return this.vertices.get(pos);
	}

	public int getPosVertice(T vertice) {
		return this.vertices.indexOf(vertice);
	}

	public double[][] getM() {
		return m;
	}

	public int size() {
		return this.vertices.size();
	}

	public double getCosto(T vertice1, T vertice2) {
		return this.m[vertices.indexOf(vertice1)][vertices.indexOf(vertice2)];
	}

	public boolean contiene(T vertice) {
		return this.vertices.contains(vertice);
	}

	public boolean contiene(Tupla<T> tupla) {
		return contiene(tupla.getNodo1()) && contiene(tupla.getNodo2());
	}

	public List<T> getVertices() {
		return vertices;
	}

	public List<T> getAdyacentes(T nodo) {
		List<T> adyacentes = new ArrayList<T>();

		for (int i = 0; i < m.length; i++) {
			if (m[this.vertices.indexOf(nodo)][i] != Double.POSITIVE_INFINITY)
				adyacentes.add(this.vertices.get(i));
		}

		return adyacentes;
	}

	public boolean addCosto(Tupla<T> tupla) {
		boolean sal = true;

		T vertice1 = tupla.getNodo1();
		T vertice2 = tupla.getNodo2();

		sal = this.vertices.contains(vertice1) && this.vertices.contains(vertice2);

		if (sal) {
			this.m[this.vertices.indexOf(vertice1)][this.vertices.indexOf(vertice2)] = tupla.getPeso();
			this.aristas.add(tupla);
		}

		return sal;
	}

	// Dijkstra
	public double[] dijkstra(T verticeIni) {
		List<T> s = new ArrayList<T>();
		List<T> vS = new ArrayList<T>();
		double[] d = this.m[this.vertices.indexOf(verticeIni)];
		List<T> p = new ArrayList<T>();
		for (int i = 0; i < this.size() - 1; i++) {
			p.add(verticeIni);
		}
		
		double min = Double.MAX_VALUE;
		double minAnterior = Double.MIN_VALUE;
		int w = 0;

		// Caso inicial
		vS.addAll(this.vertices);
		s.add(verticeIni);
		vS.remove(verticeIni);

		while (vS.size() > 0) {
			// Encuentra W
			int cont = 0;
			for (double elem : d) {
				if (elem < min && elem > minAnterior) {
					w = cont;
					min = elem;
				}
				cont++;
			}

			// remplaza en vector d
			for (int i = 0; i < d.length; i++) {
				if (i != w && i != this.vertices.indexOf(verticeIni)) {
					if (d[w] + this.m[w][i] < d[i])
						p.set(i - 1, this.vertices.get(w));
					
					d[i] = Math.min(d[i], d[w] + this.m[w][i]);
				}
			}

			minAnterior = min;
			min = Double.MAX_VALUE;

			// Actualizo conjuntos
			s.add(this.vertices.get(w));
			vS.remove(this.vertices.get(w));
		}

		System.out.println("p: " + p);
		return d;
	}

	// Prim
	public Mst<T> prim() {
		List<T> vG = new ArrayList<T>();
		List<T> vMst = new ArrayList<T>();
		double cT = 0;
		T verticeParaAgregar2 = null;
		GrafoDirigido<T> grafoSal = new GrafoDirigido<T>(this.vertices);

		// Caso inicial
		vG.addAll(this.vertices);
		vMst.add(this.vertices.get(0));
		vG.remove(0);

		while (vG.size() > 0) {
			// encuentra costo minimo
			double minActual = Double.POSITIVE_INFINITY;
			Tupla<T> tuplaParaAgregar = new Tupla<T>(null, null);
			for (T vertice : vMst) {
				for (int i = 0; i < this.m.length; i++) {
					if (this.m[this.vertices.indexOf(vertice)][i] < minActual && !vMst.contains(this.vertices.get(i))) {
						minActual = this.m[this.vertices.indexOf(vertice)][i];
						verticeParaAgregar2 = this.vertices.get(i);
						tuplaParaAgregar = new Tupla<T>(vertice, verticeParaAgregar2, minActual);
					}
				}
			}

			// Actualizo
			vMst.add(verticeParaAgregar2);
			vG.remove(verticeParaAgregar2);
			cT += minActual;
			grafoSal.addCosto(tuplaParaAgregar);
		}

		return new Mst<T>(grafoSal, cT);
	}

	// Kruskal
	public Mst<T> kruskal() {
		List<T> vMst = new ArrayList<T>();
		PriorityQueue<Tupla<T>> aristas = new PriorityQueue<Tupla<T>>(this.aristas);
		double cT = 0;
		GrafoDirigido<T> grafoSal = new GrafoDirigido<T>(this.vertices);

//		for (Tupla<T> arista : this.aristas) {
//			aristas.add(arista);
//		}

		UnionFind unionFind = new UnionFind(this.size());

		int i = 0;

		while (i < this.size() - 1) {

			Tupla<T> arista = aristas.remove();

			int representanteOrigen = unionFind.find(this.getPosVertice(arista.getNodo1()));
			int representanteDestino = unionFind.find(this.getPosVertice(arista.getNodo2()));

			if (representanteOrigen != representanteDestino) {
				i++;
				unionFind.union(representanteOrigen, representanteDestino);
//				Tupla<T> tupla = new Tupla<T>(null, null, representanteDestino);
				grafoSal.addCosto(arista);
				cT += arista.getPeso();
			}
		}

//		matrizResultado.setCostoMinimo(costoMinimo);

		return new Mst<T>(grafoSal, cT);
	}

	// DFS - Depth First Search
	public GrafoDirigido<T> DFS(T nodoInicial) {
		GrafoDirigido<T> grafoSal = new GrafoDirigido<T>(this.vertices);
		boolean[] nodosVisitados = new boolean[this.vertices.size()];
		Stack<T> pilaNodos = new Stack<T>();

		pilaNodos.push(nodoInicial);
		nodosVisitados[getPosVertice(nodoInicial)] = true;

		while (!pilaNodos.isEmpty()) {
			T nodo = pilaNodos.pop();
			System.out.print(nodo + " --> ");
			for (T ady : this.getAdyacentes(nodo)) {
				if (!nodosVisitados[getPosVertice(ady)]) {
					pilaNodos.push(ady);
					nodosVisitados[getPosVertice(ady)] = true;
				}
			}
		}

		return grafoSal;
	}

	// BFS - Breadth First Search
	public int[] BFS(T nodoInicial) {
		boolean[] visitado = new boolean[this.vertices.size()];
		int[] distancias = new int[this.vertices.size()];
		Queue<T> cola = new LinkedList<T>();
		int dist = 0;

		visitado[getPosVertice(nodoInicial)] = true;
		distancias[getPosVertice(nodoInicial)] = dist;
		dist++;
		cola.add(nodoInicial);
		System.out.print(nodoInicial + " --> ");

		while (!cola.isEmpty()) {
			T nodo = cola.poll();
			for (T ady : getAdyacentes(nodo)) {
				if (!visitado[getPosVertice(ady)]) {
					cola.add(ady);
					visitado[getPosVertice(ady)] = true;
					distancias[getPosVertice(ady)] = dist;

					System.out.print(ady + " --> ");

				}
			}
			dist++;
		}
		
		return distancias;
	}

	// Floyd
	public double[][] floyd() {

		double[][] mCostosMin = this.m;
		int i, j, k;

		// Diagonales en cero
		for (i = 0; i < this.size(); i++)
			mCostosMin[i][i] = 0;

		for (k = 0; k < this.size(); k++) {
			for (i = 0; i < this.size(); i++) {
				for (j = 0; j < this.size(); j++) {

					if (k != i && k != j && mCostosMin[i][k] != Double.POSITIVE_INFINITY
							&& mCostosMin[k][j] != Double.POSITIVE_INFINITY) {
						double dist = mCostosMin[i][k] + mCostosMin[k][j];
						if (dist < mCostosMin[i][j])
							mCostosMin[i][j] = dist;
					}

				}
			}
		}

		return mCostosMin;
	}

	// Warshall
	public boolean[][] warshall() {

		boolean[][] mCaminos = new boolean[this.size()][this.size()];
		int i, j, k;

		// Lleno matriz
		for (int s = 0; s < m.length; s++) {
			for (int g = 0; g < m.length; g++) {
				if (m[s][g] != Double.POSITIVE_INFINITY)
					mCaminos[s][g] = true;
			}
		}

		for (k = 0; k < this.size(); k++) {
			for (i = 0; i < this.size(); i++) {
				for (j = 0; j < this.size(); j++) {

					if (k != i && k != j && i != j)
						mCaminos[i][j] = mCaminos[i][j] || (mCaminos[i][k] && mCaminos[k][j]);
				}
			}
		}

		return mCaminos;
	}

}
