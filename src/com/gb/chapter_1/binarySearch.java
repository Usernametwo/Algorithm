package com.gb.chapter_1;

public class binarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,3,5,7,9};
		System.out.print(binarySearch.rank(3, a));
	}

	
	//二分查找的递归实现            时间复杂度为lgN
	public static int rank(int key, int[] a)
	{
		return rank(key, a, 0, a.length - 1);
	}
	public static int rank(int key, int[] a, int lo, int hi)
	{
		if(lo > hi)
		{
			return -1;
		}
		int mid = lo + (hi - lo) / 2;
		if(key < a[mid])
		{
			return rank(key, a, lo, mid);
		}
		else if(key > a[mid])
		{
			return rank(key, a, mid, hi);
		}
		else
		{
			return mid;
		}
	}
}

