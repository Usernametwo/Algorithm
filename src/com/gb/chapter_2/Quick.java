package com.gb.chapter_2;

//快速排序（分治法）：原地排序（只需要一个很小的辅助栈），将长度为N的数组排序的时间复杂度为O(NlgN)
//但是在某些情况下可能是平方级别，在快速排序之前将数组随机排序会降低这种情况的出现。
//归并排序和希尔排序一般都比快速排序慢,因为他们都需要在内循环里面移动数据
public class Quick {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4};
		Quick.sort(a);
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
		//快速排序的改进：在数组较小的时候快速排序比插入排序慢(因为递归，快速排的sort()方法在小数组中也会调用自己)
		//所以我们可以在此处将其切换到插入排序,M设置为5-15较为合适
		/*
		if(hi <= lo)
		{
			return;
		}
		*/
		int M = 5;
		if(hi <= lo + M)
		{
			Insertion.sort(a, lo, hi);
			return;
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	public static int partition(Comparable[] a, int lo, int hi)
	{
		//将数组切分成a[lo...i-1],a[i],a[i+1...hi]
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while(true)
		{
			while(less(a[++i], v))
				if(i == hi)
					break;
			while(less(v, a[--j]))
				if(j == lo)
					break;
			if(i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
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



