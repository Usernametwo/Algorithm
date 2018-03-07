package com.gb.chapter_1;

import java.util.Scanner;
import java.util.Stack;

//Dijkstra 的双栈算术表达式求值算法, 对于每个操作都需要打括号  Ctrl+Z结束
public class Evaluate {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		while(sc.hasNext())
		{ 
			String s = sc.next();
			switch(s)
			{
				case "(": break;
				case "+": ops.push(s); break;
				case "-": ops.push(s); break;
				case "*": ops.push(s); break;
				case "/": ops.push(s); break;
				case "sqrt": ops.push(s); break;
				case ")": 
					String op = ops.pop();
					double v = vals.pop();
					switch(op)
					{
						case "+": v = vals.pop() + v; break;
						case "-": v = vals.pop() - v; break;
						case "*": v = vals.pop() * v; break;
						case "/": v = vals.pop() / v; break;
						case "sqrt": v = Math.sqrt(v); break;
					}
					vals.push(v);
					break;
				default: vals.push(Double.parseDouble(s)); break;
			}
		}
		System.out.println(vals.pop());
		sc.close();
	}
}
