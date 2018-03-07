package com.gb.chapter_2;
//选择排序：数据移动最少，选择排序用了N次交换  运行时间和输入无关， 时间复杂度为O(N^2)
public class Selection {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4};
		Selection.sort(a);
		for(int temp : a)
		{
			System.out.println(temp);
		}
	}
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			int min = i;
			for(int j = i+1; j<N; j++)
			{
				if(less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
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
