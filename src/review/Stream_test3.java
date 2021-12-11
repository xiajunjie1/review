package review;

import java.util.Arrays;
import java.util.stream.*;
import java.util.*;
/*
 * Collectors工具类
 * 	里面封装了很多方法，可以方便的获取到一个Collector接口对象，从而可以使用collect方法，对流中的数据进行
 * 	各式各样的处理、整合
 * 
 * 该工具类的方法主要是同Stream.collect()方法混用
 * */
public class Stream_test3 {
	public static void main(String[] args){
		CollectorUsage();
	}
	
	private static void CollectorUsage(){
		String[] datasource={"I","will","f**k","your","big","Daddy"};
		Stream<String> stream=Arrays.stream(datasource);
		//stream.collect(Collectors.toList());
		//stream.collect(Collectors.toSet());
		//Map<String,String> map=stream.collect(Collectors.toMap(e1->e1, e1->e1));
		//map.forEach((k,v)->System.out.println("key: "+k+" value: "+v));
		//String result=stream.collect(Collectors.joining());//将流中的String元素都连接起来
		//System.out.println(result);
		//String result2=stream.collect(Collectors.joining(","));//将流中的String元素都连接起来，并用，分隔
		//System.out.println(result2);
		String result3=stream.collect(Collectors.joining(",", "[", "]"));//将流中的String元素连接起来，并添加前置字符和后置字符
		System.out.println(result3);
		
	}
}
