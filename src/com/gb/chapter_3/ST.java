package com.gb.chapter_3;

import java.util.Iterator;

//基于无序链表实现的符号表
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key>
{
	public static void main(String[] args)
	{
		ST<Integer, String> st = new ST<>();
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
	//链表首结点
	private Node first;
	private class Node
	{
		//链表结点的定义
		Key key;
		Value val;
		Node next;
		//结点的构造方法
		public Node(Key key, Value val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	ST()
	{
		
	}
	public void put(Key key, Value val)
	{
		if(val == null)
		{
			this.delete(key);
			return;
		}
		//查找给定的键，找到则更新其值，否则创建结点
		for(Node node = first; node != null; node = node.next)
		{
			if(node.key == key)
			{
				node.val = val;       //找到该键，更新
				return;
			}
		}
		first = new Node(key, val, first);  //未找到该键，创建结点并且插入到链表头
	}
	public Value get(Key key)
	{
		//查找给定的键，返回对应的值
		for(Node node = first; node != null; node = node.next)
		{
			if(node.key == key)
			{
				return node.val;
			}
		}
		return null;
	}
	public void delete(Key key)
	{
		//保存上一步的结点
		Node tmp = first;
		//查找给定的键，并且删除
		for(Node node = first; node != null; node = node.next)
		{
			if(node.key == key)
			{
				tmp.next = node.next;
				node.key = null;
				node.val = null;
				node.next = null;
				return;
			}
			tmp = node;
		}
		
	}
	public boolean Contains(Key key)
	{
		return this.get(key) != null;
	}
	public boolean isEmpty()
	{
		return this.size() == 0;
	}
	public int size()
	{
		int size = 0;
		for(Node node = first; node != null; node = node.next)
		{
			size++;
		}
		return size;
	}
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Key>() {
			Node tmp = first;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return tmp != null;
			}

			@Override
			public Key next() {
				// TODO Auto-generated method stub
				Node temp = tmp;
				tmp = tmp.next;
				return temp.key;
			}
		};
	}
	
}
