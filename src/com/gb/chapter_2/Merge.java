package com.gb.chapter_2;
//归并排序：能够保证任意长度为N的数组排序所需的时间复杂度为O(NlgN)；缺点：所需要的额外空间和N成正比
public class Merge {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4};
		Merge.sort(a);
		for(int temp : a)
		{
			System.out.println(temp);
		}
	}
	private static Comparable[] aux;            //归并时用到的辅助数组
	public static void sort(Comparable[] a)
	{

		int N = a.length;
		aux = new Comparable[N];
		//自顶向下的归并排序   分治法
		//sort(a, 0, N-1);
		//自底向上的归并排序
		for(int sz = 1; sz < N ; sz = sz+sz)
		{
			for(int lo = 0; lo < N-sz; lo += sz+sz)
			{
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	public static void sort(Comparable[] a, int lo, int hi)
	{
		if(hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		merge(a, lo, mid, hi);	
	}
	public static void merge(Comparable[] a, int lo, int mid, int hi)
	{
		//将a[lo...mid]和a[mid+1...hi]归并
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++)
		{
			aux[k] = a[k];
		}
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if(less(aux[j], aux[i]))
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		}
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
}
