package com.gb.chapter_2;

//基于堆的优先队列：插入元素和删除最大元素的时间复杂度均为O(lgN)
public class MaxPQ<Key extends Comparable<Key>> {
	//1-index  基于堆的完全二叉树
	private Key[] pq;
	private int N = 0;
	public MaxPQ()
	{
	}
	public MaxPQ(int max)
	{
		this.pq = (Key[]) new Comparable[max + 1];
		this.N = max;
	}
	public MaxPQ(Key[] a)
	{
		this.N = a.length - 1;
		this.pq = a;
		for(int k = N/2; k >= 1; k--)
		{
			this.sink(k);
		}
	}
	public void insert(Key v)
	{
		pq[++this.N] = v;
		this.swim(this.N);
	}
	public Key Max()
	{
		return this.pq[1];
	}
	public Key delMax()
	{
		Key t = this.pq[1];
		//与其最后一个结点交换
		this.exch(1, N--);
		//设置为null，防止对象游离
		this.pq[N+1] = null;
		//恢复堆的有序性
		sink(1);
		//为了避免此处的类型转换，我们采用了泛型而不是使用Comparable
		return t;
	}
	public boolean isEmpty()
	{
		return this.N == 0;
	}
	public int size()
	{
		return this.N;
	}
	private boolean less(int i, int j)
	{
		return this.pq[i].compareTo(this.pq[j]) < 0;
	}
	private void exch(int i, int j)
	{
		Key t = this.pq[i];
		this.pq[i] = this.pq[j];
		this.pq[j] = t;
	}
	private void swim(int k)
	{
		while(k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k / 2;
		}
	}
	private void sink(int k)
	{
		while(2*k <= this.N)
		{
			int j = 2*k;
			if(j < this.N && less(j, j+1))
				j++;
			if(!this.less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	//堆排序：时间复杂度为O(NlgN)
	public static void sort(Comparable[] a)
	{
		MaxPQ pq = new MaxPQ(a);
		while(pq.size() > 1)
		{
			a[pq.size()] = pq.delMax();
		}
	}
	public static void main(String[] args)
	{
		// 1-index
		Integer[] a = {null,2,1,5,3,4};
		MaxPQ.sort(a);
		for(int i = 1; i < a.length; i++)
		{
			System.out.println(a[i]);
		}
	}
}
