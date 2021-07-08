package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import grafos.GrafoDirigido;
import grafos.Mst;
import grafos.Tupla;

public class GrafoTests {

	@Test
	public void ComportamientoNormal() {
		List<String> ciudades = new ArrayList<String>();
		ciudades.add("BsAs");
		ciudades.add("Moron");
		ciudades.add("Tesei");
		ciudades.add("Ituzaingo");

		List<Tupla<String>> tuplaList = new ArrayList<Tupla<String>>();
		Tupla<String> tupla1 = new Tupla<String>("BsAs", "Moron", 2);
		Tupla<String> tupla2 = new Tupla<String>("Tesei", "Moron", 1);
		Tupla<String> tupla3 = new Tupla<String>("Tesei", "Ituzaingo", 5);

		tuplaList.add(tupla1);
		tuplaList.add(tupla2);
		tuplaList.add(tupla3);

		GrafoDirigido<String> grafo = new GrafoDirigido<String>(ciudades, tuplaList);
		assertEquals(grafo.getPosVertice("BsAs"), 0);
		assertEquals(grafo.getPosVertice("Moron"), 1);
		assertEquals(grafo.getPosVertice("Tesei"), 2);
		assertEquals(grafo.getPosVertice("Ituzaingo"), 3);

		double[][] m = grafo.getM();
		System.out.println("Matriz de adyacencia: ");
		for (double[] fila : m) {
			for (double elem : fila) {
				System.out.printf("%8.2f", elem);
				System.out.print("  ");
			}
			System.out.println();
		}
	}

	@Test
	public void Dijkstra() {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);

		List<Tupla<Integer>> tuplaList = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(1, 2, 10);
		Tupla<Integer> tupla2 = new Tupla<Integer>(1, 4, 30);
		Tupla<Integer> tupla3 = new Tupla<Integer>(1, 5, 100);
		Tupla<Integer> tupla4 = new Tupla<Integer>(2, 3, 50);
		Tupla<Integer> tupla5 = new Tupla<Integer>(3, 5, 10);
		Tupla<Integer> tupla6 = new Tupla<Integer>(4, 3, 20);
		Tupla<Integer> tupla7 = new Tupla<Integer>(4, 5, 60);

		tuplaList.add(tupla1);
		tuplaList.add(tupla2);
		tuplaList.add(tupla3);
		tuplaList.add(tupla4);
		tuplaList.add(tupla5);
		tuplaList.add(tupla6);
		tuplaList.add(tupla7);

		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nums, tuplaList);
		double[] result = grafo.dijkstra(1);

		System.out.println("Dijkstra, costos minimos: ");
		for (double d : result) {
			System.out.printf("%8.2f ", d);
		}
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');

		assertEquals(Double.POSITIVE_INFINITY, result[0], 0.1);
		assertEquals(10, result[1], 0.1);
		assertEquals(50, result[2], 0.1);
		assertEquals(30, result[3], 0.1);
		assertEquals(60, result[4], 0.1);
	}

	@Test
	public void Prim() {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(1);
		nums.add(2);
		nums.add(3);
		
		List<Tupla<Integer>> tuplaList = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(0, 1, 1);
		Tupla<Integer> tupla2 = new Tupla<Integer>(0, 3, 5);
		Tupla<Integer> tupla3 = new Tupla<Integer>(1, 2, 4);
		Tupla<Integer> tupla4 = new Tupla<Integer>(1, 3, 5);
		Tupla<Integer> tupla5 = new Tupla<Integer>(2, 3, 2);
		
		tuplaList.add(tupla1);
		tuplaList.add(tupla2);
		tuplaList.add(tupla3);
		tuplaList.add(tupla4);
		tuplaList.add(tupla5);
		
		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nums, tuplaList);
		Mst<Integer> salidaMst = grafo.prim();
		
		System.out.println("Prim, costo minimo: ");
		System.out.println(salidaMst.getCosto());
		
		for (double[] fila : salidaMst.getGrafo().getM()) {
			for (double elem : fila) {
				System.out.printf("%8.2f ", elem);	
				System.out.print("  ");
			}
			System.out.println();
		}
		
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');
		
		assertEquals(7, salidaMst.getCosto(), 0.1);
	}
	
	@Test
	public void Kruskal() {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(1);
		nums.add(2);
		nums.add(3);
		
		List<Tupla<Integer>> tuplaList = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(0, 1, 1);
		Tupla<Integer> tupla2 = new Tupla<Integer>(0, 3, 5);
		Tupla<Integer> tupla3 = new Tupla<Integer>(1, 2, 4);
		Tupla<Integer> tupla4 = new Tupla<Integer>(1, 3, 5);
		Tupla<Integer> tupla5 = new Tupla<Integer>(2, 3, 2);
		
		tuplaList.add(tupla1);
		tuplaList.add(tupla2);
		tuplaList.add(tupla3);
		tuplaList.add(tupla4);
		tuplaList.add(tupla5);
		
		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nums, tuplaList);
		Mst<Integer> salidaMst = grafo.kruskal();
		
		System.out.println("Kruskal, costo minimo: ");
		System.out.println(salidaMst.getCosto());
		
		for (double[] fila : salidaMst.getGrafo().getM()) {
			for (double elem : fila) {
				System.out.printf("%8.2f ", elem);	
				System.out.print("  ");
			}
			System.out.println();
		}
		
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');
		
		assertEquals(7, salidaMst.getCosto(), 0.1);
	}
	
	
	@Test
	public void DFS_BFS() {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(1);
		nums.add(2);
		nums.add(3);
		
		List<Tupla<Integer>> tuplaList = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(0, 1, 1);
		Tupla<Integer> tupla2 = new Tupla<Integer>(0, 3, 5);
		Tupla<Integer> tupla3 = new Tupla<Integer>(1, 2, 4);
		Tupla<Integer> tupla4 = new Tupla<Integer>(1, 3, 5);
		Tupla<Integer> tupla5 = new Tupla<Integer>(2, 3, 2);
		
		tuplaList.add(tupla1);
		tuplaList.add(tupla2);
		tuplaList.add(tupla3);
		tuplaList.add(tupla4);
		tuplaList.add(tupla5);
		
		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nums, tuplaList);
		System.out.println("DFS: ");
		grafo.DFS(0);
		System.out.println();
		System.out.println("BFS: ");
		grafo.BFS(0);
		
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');
	}
	
	
	@Test
	public void floyd() {
		
		// Nodos de clase
		List<Integer> nodos = new ArrayList<Integer>();
		nodos.add(1);
		nodos.add(2);
		nodos.add(3);
		
		// Pesos
		List<Tupla<Integer>> tuplas = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(1, 2, 8);
		Tupla<Integer> tupla2 = new Tupla<Integer>(1, 3, 5);
		Tupla<Integer> tupla3 = new Tupla<Integer>(2, 1, 3);
		Tupla<Integer> tupla4 = new Tupla<Integer>(3, 2, 2);
		
		tuplas.add(tupla1);
		tuplas.add(tupla2);
		tuplas.add(tupla3);
		tuplas.add(tupla4);
		
		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nodos, tuplas);
		
		double[][] mCostosMin = grafo.floyd();
		
		assertEquals(0, mCostosMin[0][0], 0.1);
		assertEquals(7, mCostosMin[0][1], 0.1);
		assertEquals(5, mCostosMin[0][2], 0.1);
		assertEquals(3, mCostosMin[1][0], 0.1);
		assertEquals(0, mCostosMin[1][1], 0.1);
		assertEquals(8, mCostosMin[1][2], 0.1);
		assertEquals(5, mCostosMin[2][0], 0.1);
		assertEquals(2, mCostosMin[2][1], 0.1);
		assertEquals(0, mCostosMin[2][2], 0.1);
		
		// Imprimo en pantalla
		System.out.println("Matriz de Floyd: ");
		
		for (double[] fila : mCostosMin) {
			for (double elem : fila) {
				System.out.printf("%8.2f ", elem);	
				System.out.print("  ");
			}
			System.out.println();
		}
		
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');
		
	}
	
	@Test
	public void warshal() {
		
		// Nodos de clase
		List<Integer> nodos = new ArrayList<Integer>();
		nodos.add(1);
		nodos.add(2);
		nodos.add(3);
		
		// Pesos
		List<Tupla<Integer>> tuplas = new ArrayList<Tupla<Integer>>();
		Tupla<Integer> tupla1 = new Tupla<Integer>(1, 2, 8);
		Tupla<Integer> tupla2 = new Tupla<Integer>(1, 3, 5);
		Tupla<Integer> tupla3 = new Tupla<Integer>(2, 1, 3);
		Tupla<Integer> tupla4 = new Tupla<Integer>(3, 2, 2);
		
		tuplas.add(tupla1);
		tuplas.add(tupla2);
		tuplas.add(tupla3);
		tuplas.add(tupla4);
		
		GrafoDirigido<Integer> grafo = new GrafoDirigido<Integer>(nodos, tuplas);
		
		boolean[][] mCaminos = grafo.warshall();
		
		// Imprimo en pantalla
		System.out.println("Matriz de Warshall: ");
		
		for (boolean[] fila : mCaminos) {
			for (boolean elem : fila) {
				System.out.print(elem);	
				System.out.print("  ");
			}
			System.out.println();
		}
		
		System.out.printf("%s%s%s%s", '\n', '\n', '\n', '\n');
		
		// Test
		assertEquals(false, mCaminos[0][0]);
		assertEquals(true, mCaminos[0][1]);
		assertEquals(true, mCaminos[0][2]);
		assertEquals(true, mCaminos[1][0]);
		assertEquals(false, mCaminos[1][1]);
		assertEquals(true, mCaminos[1][2]);
		assertEquals(true, mCaminos[2][0]);
		assertEquals(true, mCaminos[2][1]);
		assertEquals(false, mCaminos[2][2]);
	}


}
