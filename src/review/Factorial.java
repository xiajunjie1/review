package review;

/**
 * ʵ�ֽ׳�
 * �㷨˼�룺�ݹ�
 *
 */

public class Factorial {

	
	public static int factorial(int para){
		
		if(para==1)return 1;
		if(para<1){
			System.out.println("���������0��������");
			return 0;
		}
		
		return para*factorial(para-1);
	}
	
	public static void main(String[] args){
		
		int result=factorial(4);
		System.out.println("���Ϊ��"+result);
	}
}
