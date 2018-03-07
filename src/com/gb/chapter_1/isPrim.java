package com.gb.chapter_1;

public class isPrim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(isPrim.is_prim(109));
	}
	
	//判断一个数是否为素数，一个数若可以进行因数分解，那么分解时得到的两个数一定是一个小于等于sqrt(n)，一个大于等于sqrt(n)
	//据此，代码中并不需要遍历到n-1，遍历到sqrt(n)即可，因为若sqrt(n)左侧找不到约数，那么右侧也一定找不到约数
	public static boolean is_prim(int N)
	{
		if(N < 2)
		{
			return false;
		}
		for(int i = 2; i * i <= N; i++)
		{
			if(N % i ==0)
				return false;
		}
		return true;
	}
}
