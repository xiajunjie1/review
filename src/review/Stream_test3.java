package review;

import java.util.Arrays;
import java.util.stream.*;
import java.util.*;
/*
 * Collectors������
 * 	�����װ�˺ܶ෽�������Է���Ļ�ȡ��һ��Collector�ӿڶ��󣬴Ӷ�����ʹ��collect�����������е����ݽ���
 * 	��ʽ�����Ĵ�������
 * 
 * �ù�����ķ�����Ҫ��ͬStream.collect()��������
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
		//String result=stream.collect(Collectors.joining());//�����е�StringԪ�ض���������
		//System.out.println(result);
		//String result2=stream.collect(Collectors.joining(","));//�����е�StringԪ�ض��������������ã��ָ�
		//System.out.println(result2);
		String result3=stream.collect(Collectors.joining(",", "[", "]"));//�����е�StringԪ�����������������ǰ���ַ��ͺ����ַ�
		System.out.println(result3);
		
	}
}
