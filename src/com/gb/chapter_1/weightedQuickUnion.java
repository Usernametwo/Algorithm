package com.gb.chapter_1;

import java.util.Scanner;

public class weightedQuickUnion {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		weightedQuickUnion uf = new weightedQuickUnion(N);
		while(sc.hasNext())
		{
			int p = sc.nextInt();
			int q = sc.nextInt();
			if(uf.connected(p, q))
				continue;
			else
			{
				uf.union(p, q);
				System.out.println(p + " " + q);
			}
		}
		sc.close();
	}
	//分量id
	private int[] id;
	//分量个数
	private int count;
	//各个点所在树的大小
	private int[] sz;
	public weightedQuickUnion(int N)
	{
		count = N;
		id = new int[N];
		sz = new int[N];
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
			sz[i] = 1;
		}
	}
	public int count()
	{
		return this.count;
	}
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}
	public int find(int p)
	{
		//找到根节点10
		while(p != id[p])
			p = id[p];
		return id[p];
	}
	public void union(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		if(i == j)
		{
			return;
		}
		else 
		{
			//将小树的根节点连接到大树的根节点
			if(sz[i] < sz[j])
			{
				id[i] = id[j];
				sz[j] += sz[i];
			}
			else
			{
				id[j] = id[i];
				sz[i] += sz[j];
			}
			count--;
		}
	}
}
