package com.gb.chapter_3;

//平衡查找树（红黑二叉查找树）
//和标准的二叉查找树由上向下生长不同，2-3树的生长是由下向上的
//在一棵大小为N的2-3树中，查找和插入操作访问的结点必然不超过lgN个
public class RedBlackBST<Key extends Comparable<Key>, Value> {

	public static void main(String[] args) {

	}
	private Node root;
	
	//红链接将两个2-结点连接起来构成一个3-结点，黑链接则是普通链接
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node {
		//键
		Key key;
		//相关联的值
		Value val;
		//左右子树
		Node left, right;
		//这棵子树中的节点数量
		int N;
		//由其父节点指向它链接的颜色
		boolean color;
		Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	private boolean isRed(Node x) {
		//空链接为黑色
		if(x == null) return false;
		return x.color == RED;
	}
	
	private int size(Node x) {
		return x.N;
	}
	
	//旋转操作
	//左旋
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	//右旋
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	//如果一个结点的两个子节点都是RED的，则两个子结点需要转换成BLACK，将父结点转换成RED
	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	//插入操作
	public void put(Key key, Value val) {
		//查找key，找到则更新其值，否则为它新创建一个结点并且插入
		root = put(root, key, val);
		root.color = BLACK;
	}
	private Node put(Node h, Key key, Value val) {
		if(h == null) {
			return new Node(key, val, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		if(cmp < 0) h.left = put(h.left, key, val);
		if(cmp > 0) h.left = put(h.right, key, val);
		else h.val = val;
		
		//除了这三句之外，其他的和二叉查找树中的实现完全相同
		//将任意含有红色右链接的3-结点向左旋转
		if(isRed(h.right) && !(isRed(h.left))) h = rotateLeft(h);
		//将4-结点中两条连续红链接中的上层链接向右旋转
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		//进行颜色转换并将红链接在树中向上传递
		if(isRed(h.left) &&isRed(h.right)) flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	
}
