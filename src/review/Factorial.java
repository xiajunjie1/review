package review;

/**
 * 实现阶乘
 * 算法思想：递归
 *
 */

public class Factorial {

	
	public static int factorial(int para){
		
		if(para==1)return 1;
		if(para<1){
			System.out.println("请输入大于0的正整数");
			return 0;
		}
		
		return para*factorial(para-1);
	}
	
	public static void main(String[] args){
		
		int result=factorial(4);
		System.out.println("结果为："+result);
	}
}
