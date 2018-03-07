package com.gb.chapter_2;
//插入排序:对于接近有序的数组排序会快很多,时间复杂度为O(N^2),插入排序大概会比选择排序快一倍
public class Insertion {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4};
		Insertion.sort(a, 0, a.length - 1);
		for(int temp : a)
		{
			System.out.println(temp);
		}
	}
	public static void sort(Comparable[] a, int lo, int hi)
	{
		for(int i = 1 + lo; i < hi; i++)
		{
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
			{
				exch(a, j, j-1);
			}
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
