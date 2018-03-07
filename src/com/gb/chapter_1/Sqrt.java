package com.gb.chapter_1;

public class Sqrt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(Sqrt.sqrt(9));
	}
	//牛顿迭代法求平方根       
	public static double sqrt(double c)
	{
		if(c < 0)
		{
			return Double.NaN;
		}
		double err = 1e-5;
		double t = c;
		//  如果     |t*t - c| > err   执行迭代     t = - f(t) / f(t)' + t    直到f(t) = t*t -c ---> 0
		while(Math.abs(t - c/t) > err * t)
		{
			t = (c/t + t) / 2.0;
		}
		return t;
	}
}
