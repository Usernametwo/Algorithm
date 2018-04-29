package com.gb.chapter_4;

import java.util.ArrayList;

//基于邻接表数组实现的图（没有加权）
public class Graph {
	//顶点数目
	private final int V;
	//边的数目
	private int E;
	//邻接表
	private ArrayList<Integer>[] adj;
	public Graph(int V) {
		// TODO Auto-generated constructor stub
		this.V = V;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for(int v = 0; v < V;v++) {
			adj[v] = new ArrayList<>();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
}
