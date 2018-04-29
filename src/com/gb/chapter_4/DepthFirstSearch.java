package com.gb.chapter_4;

import java.util.Stack;

//基于深度优先搜索来搜索一幅图，查看其顶点是否连通
//深度优先搜索每条边都会被访问两次，且在第二次时总会发现这个顶点已经被标记过
//适合单点路径问题,不适合寻找最短路径
public class DepthFirstSearch {
	//这个点是否调用过dfs()
	private boolean[] marked;
	//从起点到一个顶点的已知路径上的最后一个顶点
	private int[] edgeTo;
	//起点
	private final int s;       
	private int count;
	
	public DepthFirstSearch(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		dfs(g, s);
	}
	
	private void dfs(Graph g, int v) {
		marked[v] = true;
		count++;
		for(int w : g.adj(v)) {    //遍历与v相连接的点
			if(!marked[w]) {           //如果未被遍历则进入递归
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}
	
	public boolean[] marked() {
		return marked;
	}
	
	public int count() {
		return count;
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	//返回路径
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) 
			return null;
		Stack<Integer> stack = new Stack<>();
		for(int i = v; i != s ; i = edgeTo[i]) {
			stack.push(i);
		}
		stack.push(s);
		return stack;
	}
}
