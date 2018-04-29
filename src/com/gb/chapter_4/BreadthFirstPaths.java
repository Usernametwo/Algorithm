package com.gb.chapter_4;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//广度优先搜索，适合寻找最短路径
//寻找边数最少的路径
//深度优先搜索和广度优先搜索的不同之处在于从数据结构中获取下一个顶点的规则（牵着是最晚加入的顶点，后者是最先加入的顶点）
public class BreadthFirstPaths {
	private boolean[] markred;
	private int[] edgeTo;
	private final int s;
	public BreadthFirstPaths(Graph g, int s) {
		// TODO Auto-generated constructor stub
		markred = new boolean[s];
		edgeTo = new int[g.V()];
		this.s = s;
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s) {
		Queue<Integer> queue = new PriorityQueue<>();
		//标记起点
		markred[s] = true;
		//将起点加入队列
		queue.add(s);
		while(!queue.isEmpty()) {
			//从队列中删去下一顶点
			int v = queue.remove();
			for(int w : g.adj(v)) {
				if(!markred[w]) {
					//保存最短路径的最后一条边
					edgeTo[w] = v;
					markred[w] = true;
					//加入队列
					queue.add(v);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return markred[v];
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
