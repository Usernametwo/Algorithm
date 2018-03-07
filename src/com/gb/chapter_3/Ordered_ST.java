package com.gb.chapter_3;

import java.util.Iterator;

//基于有序数组实现的符号表
public class Ordered_ST<Key extends Comparable<Key>, Value> implements Iterable<Key>
{
	public static void main(String[] args)
	{
		Integer[] a = new Integer[10];
		String[] strings = new String[10];
		Ordered_ST<Integer, String> st = new Ordered_ST<Integer, String>(a, strings);
		st.put(1, "a");
		st.put(2, "b");
		st.put(3, "c");
		String s1 = st.get(2);
		System.out.println(s1);
		st.delete(2);
		String s2 = st.get(2);
		System.out.println(s2);
		Iterator<Integer> iterator = st.iterator();
		for(;iterator.hasNext();)
		{
			Integer tmp = iterator.next();
			System.out.println("key:" + tmp.toString() + " value:" + st.get(tmp));
		}
	}
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	public Ordered_ST(Key[] keys, Value[] vals) 
	{
		// TODO Auto-generated constructor stub
		this.keys = keys;
		this.vals = vals;
	}
	
	//基于有序数组的二分查找（迭代）
	public int rank(Key key)
	{
		int lo = 0, hi = this.N - 1;
		while(lo <= hi)
		{
			int mid = lo+ (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1; 
			else 
				return mid;
		}
		return lo;
	}
	
	public void put(Key key, Value val)
	{
		if(val == null)
		{
			this.delete(key);
			return;
		}
		//查找键，找到则更新，否则创建新的元素
		int i = this.rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
		{
			vals[i] = val;
			return;
		}
		for(int j = N; j > i; j--)
		{
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Value get(Key key)
	{
		if(this.isEmpty())
			return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	public void delete(Key key)
	{
		int i = this.rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
		{
			for(int j = i; j < N-1; j++)
			{
				keys[j] = keys[j+1];
				vals[j] = vals[j+1];
			}
			N--;
		}
	}
	public boolean isEmpty()
	{
		return this.size() == 0;
	}
	public int size()
	{
		return this.N;
	}
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Key>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return i < N;
			}

			@Override
			public Key next() {
				// TODO Auto-generated method stub
				i++;
				return keys[i-1];
			}
		};
	}
}
