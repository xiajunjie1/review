package review;
/*
 * Arrays工具类
 * 该类中包含了若干个对数组进行操作的工具方法
 * 
 * 
 * 	
 * */

import java.util.Arrays;
public class ArrayTools {

	public static void main(String[] args){
		 
		int[] arrtest={3,6,10,2,22,7,99,0,12};
		
		//工具方法-拷贝:从原数组中拷贝一定数量的元素，并返回新的数组
		//从arrtest中拷贝前五个元素，将其作为新的数组进行返回
		//如果拷贝的个数超过了原数组的长度，依然可以拷贝，只是在最后的结果中补了若干默认值，如int则补0，String则补null
		int[] arrtest2=Arrays.copyOf(arrtest, 5);
		
		//工具方法-数组转字符串Arrays.toString(数组)：将数组的内容拼接成字符串并返回
		System.out.println(Arrays.toString(arrtest2));
		
		//工具方法-拷贝
		//Arrays.copyOfRange(original, from, to)
		//将原数组中从下标from到下标to-1的元素拷贝生成一个新的数组
		//to可以越界，越界后在后面填充上默认值，而from是不能越界的，from越界会抛出数组越界异常
		
		//工具方法-equals：比较两个数组内容是否相同
		int[] arrtestbak={3,6,10,2,22,7,99,0,12};
		System.out.println(Arrays.equals(arrtest, arrtestbak));
		
		//工具方法-填充：用值填充数组
		int[] testarr=new int[4];
		Arrays.fill(testarr, 10);
		System.out.println(Arrays.toString(testarr));
		
		//工具方法-排序
		Arrays.sort(arrtestbak);
		System.out.println(Arrays.toString(arrtestbak));
		
		//工具方法-用二分查找，查找相应元素在数组中的位置，并且返回下标
		//二分查找的前提条件，数组已经排好了顺序
		int index = Arrays.binarySearch(arrtestbak, 99);
		System.out.println("下标为："+index);
		
		
		//System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
		//将一个数组(src)从指定位（srcPos)开始，拷贝到目标数组(dest)的指定位置(destPos)，拷贝length个元素
		//注意事项：不要发生数组越界的情况
		
		int[] src={1,2,3,4,5,6};
		src=remove(src,5);
		System.out.println("删除后结果: "+Arrays.toString(src));
	
	}
	
	public static int[] remove(int[] src,int index){
		if(index<0||index>=src.length){
		System.out.println("参数不合法！");	
		return src;
		}
		if(index==src.length-1){
			src=Arrays.copyOf(src, src.length-1);
			return src;
		}
		for(int i=index;i<src.length-1;i++){
			src[i]=src[i+1];
		}
		src=Arrays.copyOf(src, src.length-1);
		
		return src;
	}
}
