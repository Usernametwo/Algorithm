package com.gb.chapter_3;

import java.util.LinkedList;
import java.util.Queue;

//基于二叉查找树实现的符号表
public class BST<Key extends Comparable<Key>, Value> 
{
	public static void main(String[] args) 
	{
		BST<Integer, String> bst = new BST<>();
		bst.put(1, "a");
		bst.put(2, "b");
		bst.put(3, "c");
		bst.put(1, "d");
		bst.put(5, "d");
		System.out.println(bst.size());
		System.out.println("向上取整:" + bst.ceiling(4) );
		System.out.println("向下取整:" + bst.floor(4) );
		//排名为0-index
		System.out.println("排名第2的key为：" + bst.select(2));
		System.out.println("2的排名为:" + bst.rank(2));
		//删除操作
		bst.delete(2);
		System.out.println("删除2之后查询key为2的value:" + bst.get(2));
		//范围查找
		Iterable<Integer> iterable = bst.keys();
		for(Integer item : iterable)
		{
			System.out.println(item.toString() + " : " + bst.get(item));
		}
	}
	private Node root;
	private class Node
	{
		private Key key;
		private Value value;
		private Node left, right;
		private int N;            //以该节点为根的子树中的节点总数(包括根节点)
		public Node(Key key, Value value, int N)
		{
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	
	public int size()
	{
		return root.N;
	}
	public int size(Node x)
	{
		if(x == null)
		{
			return 0;
		}
		else 
		{
			return x.N;
		}
	}
	
	private Value get(Node x, Key key)
	{
		if(x == null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
		{
			return this.get(x.left, key);
		}
		else if(cmp > 0)
		{
			return this.get(x.right, key);
		}
		else 
		{
			return x.value;
		}
	}
	public Value get(Key key)
	{
		return this.get(root, key);
	}
	
	
	private Node put(Node x, Key key, Value value)
	{
		if(x == null)
		{
			return new Node(key, value, 1);
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
		{
			x.left = this.put(x.left, key, value);
		}
		else if(cmp > 0)
		{
			x.right = this.put(x.right, key, value);
		}
		else
		{
			x.value = value;
		}
		//这里的+1是加上根节点本身
		x.N = this.size(x.left) + this.size(x.right) + 1;
		return x;
	}
	public void put(Key key, Value value)
	{
		if(value == null)
		{
			this.delete(key);
		}
		this.root = this.put(root, key, value);
	}
	
	private Node max(Node x)
	{
		if(x.right == null)
		{
			return x;
		}
		return this.max(x.right);
	}
	
	private Node min(Node x)
	{
		if(x.left == null)
		{
			return x;
		}
		return this.min(x.left);
	}
	public Key min()
	{
		return this.min(this.root).key;
	}
	public Key max()
	{
		return this.max(this.root).key;
	}
	
	//向下取整
	private Node floor(Node x, Key key)
	{
		if(x == null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
		{
			return x;
		}
		if(cmp < 0)
		{
			return this.floor(x.left, key);
		}
		Node t = this.floor(x.right, key);
		if(t != null)
		{
			return t;
		}
		else
		{
			return x;
		}
	}
	public Key floor(Key key)
	{
		Node x = this.floor(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
	//向上取整
	private Node ceiling(Node x, Key key)
	{
		if(x == null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
		{
			return x;
		}
		if(cmp > 0)
		{
			return this.ceiling(x.right, key);
		}
		Node t = this.ceiling(x.left, key);
		if(t != null)
		{
			return t;
		}
		else
		{
			return x;
		}	
	}
	public Key ceiling(Key key)
	{
		Node x = this.ceiling(root, key);
		if(x == null)
			return null;
		return x.key;	
	}
	 
	//返回排名为k的key     排名为0-index   
	private Node select(Node x, int k)
	{
		if(x == null)
		{
			return null;
		}
		int t = this.size(x.left);
		if(t > k)
		{
			return this.select(x.left, k);
		}
		else if(t < k)
		{
			return this.select(x.right, k - t - 1);
		}
		else
		{
			return x;
		}
	}
	public Key select(int k)
	{
		return this.select(root, k).key;
	}

	//返回该key对应的排名   排名为  0-index
	private int rank(Key key, Node x)
	{
		if(x == null)
		{
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
		{
			return rank(key, x.left);
		}
		else if(cmp > 0)
		{
			return 1 + size(x.left) + rank(key, x.right); 
		}
		else 
		{
			return size(x.left);
		}
	}
	public int rank(Key key)
	{
		return this.rank(key, root);
	}
	
	//删除操作
	private Node deleteMin(Node x)
	{
		if(x.left == null)
		{
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = this.size(x.left) + this.size(x.right) + 1;
		return x;
	}
	public void deleteMin()
	{
		root = this.deleteMin(root);
	}
	
	private Node delete(Node x, Key key)
	{
		if(x == null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
		{
			x.left = this.delete(x.left, key);
		}
		else if(cmp > 0)
		{
			x.right = this.delete(x.right, key);
		}
		else 
		{
			if(x.right == null)
			{
				return x.left;
			}
			if(x.left == null)
			{
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	public void delete(Key key)
	{
		root = this.delete(root, key);
	}
	
	//范围查找操作
	public Iterable<Key> keys()
	{
		return this.keys(this.min(), this.max());
	}
	public Iterable<Key> keys(Key lo, Key hi)
	{
		Queue<Key> queue = new LinkedList<Key>();
		this.keys(root, queue, lo, hi);
		return queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
	{
		if(x == null)
		{
			return;
		}
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0)
		{
			this.keys(x.left, queue, lo, hi);
		}
		if(cmplo <= 0 && cmphi >= 0)
		{
			queue.offer(x.key);
		}
		if(cmphi > 0)
		{
			this.keys(x.right, queue, lo, hi);
		}
	}
	
}
