package review;
/**
 * ������ʵ�ֵĿɱ䳤�ȵķ�������
 * 
 * */

public class MutipleParameter {
	
	public static void main(String[] args){
		int max=getMax(1,2,3,4,5,6);
		System.out.println("The max number is : "+max);
	}

	//��������...��ʽ�Ĳ�������������β��б�����һλ
	public static int getMax(int... arry){//...����һ������
		int max=arry[0];
		for(int ele : arry){
			if(max<ele){
				max=ele;
			}
		}
		return max;
	}
}
