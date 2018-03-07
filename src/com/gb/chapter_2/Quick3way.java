package com.gb.chapter_2;
//三向切分的快速排序：对于只含有若干主键的随机数组，三向切分的时间复杂度是线性的
public class Quick3way {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4,5,4,5,5,3,3,2,1,1};
		Quick3way.sort(a);
		for(int temp : a)
		{
			System.out.println(temp);
		}
	}
	public static void sort(Comparable[] a)
	{
		//消除对输入的依赖
		shuffle(a);
		sort(a, 0, a.length-1);
	}
	public static void sort(Comparable[] a, int lo, int hi)
	{
		if(hi <= lo)
		{
			return;
		}
		int lt = lo, i = lo + 1, gt = hi; 
		Comparable v = a[lo];
		while(i <= gt)
		{
			//将数组分为a[lo...lt-1] < v=a[lt...gt] < a[gt+1...hi]
			int cmp = a[i].compareTo(v);
			if(cmp < 0) 
				exch(a, lt++, i++);
			if(cmp > 0)
				exch(a, i, gt--);
			else 
				i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
	public static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}
	public static void exch(Comparable[] a, int i, int j)
	{
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	public static void shuffle(Comparable[] a)
	{
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			//将a[i]和a[i...N-1]中的任意一个元素交换
			int r = i + (int)(Math.random()*(N-i)); 
			Comparable tmp = a[i];
			a[i] = a[r];
			a[r] = tmp;
		}
	}
}
