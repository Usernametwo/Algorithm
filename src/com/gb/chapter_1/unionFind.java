package com.gb.chapter_1;

import java.util.Scanner;

public class unionFind {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		unionFind uf = new unionFind(N);
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
	public unionFind(int N)
	{
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
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
		return id[p];
	}
	public void union(int p, int q)
	{
		int pID = find(p);
		int qID = find(q);
		if(pID == qID)
		{
			return;
		}
		else 
		{
			for(int i = 0; i<this.id.length; i++)
			{
				if(this.id[i] == pID)
				{
					this.id[i] = qID;
				}
			}
			count--;
		}
	}
	
}
