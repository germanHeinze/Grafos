package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {

	private int[] id;

	public UnionFind(int size) {
		id = new int[size];
		for (int i = 0; i < size; i++) {
			id[i] = i;
		}
	}

	public boolean connected(int i, int j) {
		return root(i) == root(j);
	}

	private int root(int i) {
		int root;
		while ((root = id[i]) != i)
			i = id[i];
		return root;
	}

	public int find(int i) {
		return root(i);
	}

	public void union(int i, int j) {
		int rootI, rootJ;
		if ((rootI = root(i)) == (rootJ = root(j)))
			return;
		id[rootI] = rootJ;// Mover el árbol donde se encuentra el nodo i al árbol donde se encuentra el
							// nodo j
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Map<Integer, List<Integer>> group = new HashMap<>();
		for (int i = 0; i < this.id.length; i++) {
			int root = root(i);
			if (group.get(root) == null) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(i);
				group.put(root, tmp);
			} else
				group.get(root).add(i);
		}
		return group.toString();
	}
}