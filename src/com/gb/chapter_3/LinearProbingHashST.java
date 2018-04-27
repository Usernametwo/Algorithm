package com.gb.chapter_3;

//基于线性探测的符号表,依靠数组中的空位来解决碰撞冲突
public class LinearProbingHashST<Key, Value> {
	public static void main(String[] args) {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>();
		st.put(1, "a");
		st.put(2, "b");
		st.put(3, "c");
		String s1 = st.get(2);
		System.out.println(s1);
		st.delete(2);
		String s2 = st.get(2);
		System.out.println(s2);
	}
	
	//符号表中键值对的总数
	private int N;
	//线性探测表的大小
	private int M = 16;
	//键
	private Key[] keys;
	//值
	private Value[] vals;
	
	public LinearProbingHashST() {
		// TODO Auto-generated constructor stub
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	//调整数组大小
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>();
		for(int i = 0; i < M ; i++) {
			if(keys[i] != null) {
				t.put(keys[i], vals[i]);
			}
		}
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
	public void put(Key key, Value val) {
		if(N > M/2) {
			resize(2 * M);
		}
		int i;
		for(i = hash(key); keys[i] != null; i=(i + 1) % M) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i=(i + 1) % M) {
			if(keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}
	
	private boolean contains(Key key) {
		return get(key) != null;
	}
	//基于线性探测的散列表中删除一个键需要将其后的键重新插入
	public void delete(Key key) {
		if(!contains(key)) {
			return;
		}
		int i = hash(key);
		while(!key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N -- ;
		if(N > 0 && N == M/8) {
			resize(M/2);
		}
		
	}
}
