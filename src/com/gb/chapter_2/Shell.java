package com.gb.chapter_2;
//希尔排序：对插入排序的改进，交换不相邻的元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组排序
//比选择排序和插入排序快，数组越大，优势越大。
public class Shell {
	public static void main(String[] args)
	{
		Integer[] a = {2,1,5,3,4};
		Shell.sort(a);
		for(int temp : a)
		{
			System.out.println(temp);
		}
	}
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		int h = 1;
		while(h < N/3)
			h = 3*h +1;
		while(h >= 1)
		{//将数组变为h有序
			for(int i = h; i < N; i++)
			{
				for(int j = i; j >= h && less(a[j], a[j-h]); j -= h)
				{
					exch(a, j, j-h);
				}
			}
			h = h/3;
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
