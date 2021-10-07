package review;
/**
 * 用数组实现的可变长度的方法参数
 * 
 * */

public class MutipleParameter {
	
	public static void main(String[] args){
		int max=getMax(1,2,3,4,5,6);
		System.out.println("The max number is : "+max);
	}

	//数据类型...形式的参数，必须放在形参列表的最后一位
	public static int getMax(int... arry){//...代表一个数组
		int max=arry[0];
		for(int ele : arry){
			if(max<ele){
				max=ele;
			}
		}
		return max;
	}
}
