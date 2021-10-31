package review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 异常复习
 * 	异常是对程序在运行过程中种种不正常的描述，如果程序遇到了未经处理的异常，会导致这个程序无法进行编译或运行
 * 
 * 	在java中，用Throwable（基类）类来描述所有不正常的情况，它有两个子类
 * 		Error（错误）：发生在jvm虚拟机级别的错误，这些错误无法被处理
 * 		Exception（异常）：描述程序中遇到的异常，是可以被捕获处理的
 * 			IOException
 * 			RuntimeException
 * 
 * 		非RuntimeException异常，是无法通过编译的。
 * 
 * 	异常的捕获
 * 		try{...}catch(异常类型 e){...}
 * 
 * 	finally字句，可以跟在try-catch后面，无论是否出现、捕获异常，都会执行finally中的代码
 * 	基于finally的特定，一般会在finally中进行资源的释放，如关闭IO流等
 * 
 * 
 * 	throw
 * 		一个异常对象，被实例化完成后，没有任何意义。不会影响到程序的编译或运行
 * 		如果需要一个异常生效，需要使用关键字throw进行异常抛出
 * 
 * 	throws
 * 		写在方法声明中的如public void func()throws{...}
 * 		用途1：告诉调用方，调用本方法的时候会出现哪些异常
 * 		用途2：本方法在使用的时候，不会处理当前异常，它会抛出给调用方，由调用方来处理
 * 
 * 	自定义异常
 * 		为什么要自定义异常：
 * 			使用异常，是为了处理一些重大的逻辑bug，这些逻辑bug可能会导致程序崩溃
 * 			当我们需要的异常，系统没有提供的时候，我们就可以进行自定义日常了
 * 
 * 		定义编译时异常------------继承Exception
 * 		定义运行时异常------------继承RuntimeException
 * */
public class Exception_review {

	public static void main(String[] args){
		int[] arr={1,2,3,4};
		String str=null;
		try{
		//将可能出错的代表放到try...catch中，不会影响程序执行	
		//int ele = arr[4];
		//发生异常的位置之后的语句不会再执行
		//System.out.println(ele);
		
		str.equals("test");
		
	}catch(ArrayIndexOutOfBoundsException e){
		//此处捕捉的异常类型要和实际抛出的异常类型匹配，否则依然无法处理异常，会影响程序执行
		System.err.println("数组下标越界！");
	}catch(NullPointerException ne){
		//如果try代码段中可能存在多个异常，那么后面可以跟上多个Catch
		System.err.println("空指针！");
	}
		//多个catch之间的异常，没有继承关系的话，先后顺序无所谓
		//如果有继承关系，得保证父类异常在后，子类异常在前
		//若多种异常的处理方式都是相同的，可以直接Catch一个父类异常，进行统一处理
		//ExceptionTest(2);
		//System.out.println(finallyTest());
		//System.out.println(finallyTest2());
		//throwTest();
		System.out.println("end");
		
		List<String> list=new ArrayList<String>();
		list.add("abc");
		list.add("fff");
		list.add("defg");
		list.add("www");
		list.add("ooo");
		try {
			loop(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("错误抛出");
		}
		
		try {
			throw new MyException("自定义错误信息！！！");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("自定义异常："+e.getMessage());//获取异常信息
		}
	}
	
	/*
	 * Exception 测试
	 * */
	public static void ExceptionTest(int index){
		int[] arr={1,2,3,4};
		String str=null;
		try{
			
		int ele = arr[index];
		System.out.println(ele);
		str.equals("test");
		//一个Catch处理多个异常，即可直接catch父类，也可以将若干个无继承关系的异常用|隔开
	}catch(ArrayIndexOutOfBoundsException |NullPointerException e){
		//此处捕捉的异常类型要和实际抛出的异常类型匹配，否则依然无法处理异常，会影响程序执行
		System.err.println("数组下标越界或空指针异常！");
	}catch(RuntimeException re){
		System.err.println("其他异常");
	}
	}
	
	/*
	 * finally 测试
	 * */
	public static int finallyTest(){
		/*
		 * 执行过程：
		 * 	当try中的代码，执行到return的时候，系统会将要返回的值，拷贝一份到临时空间中
		 * 	然后执行finally中的代码，这个时候，finally中的代码修改的是原局部的变量的内容
		 * 	finally中的代码执行完成后，会将临时空间中的数据返回，并释放临时空间
		 * 	所以返回值为20
		 * */
		int x=10;
		try{
			x=20;
		return x;
	}finally{
		x=100;
		System.out.println("finally代码执行");
	}
	}
	/*
	 * finally 测试
	 * */
	public static String finallyTest2(){
		String a="a";
		try{
			a="b";
			return a;
		}finally{
			a="c";
			System.out.println("finally代码2执行");
		}
	}
	
	
	/*
	 * throw 测试
	 * */
	private static void throwTest(){
		
		RuntimeException ex=new RuntimeException();
		int i=1;
		while(true){
			if(i>100)throw ex;//抛出异常
			System.out.println(i++);
			
		}
	}
	
	/*
	 * thorws 测试
	 * */
	private static void loop(List<String> list)throws Exception{
		Exception e =new Exception();
		int i=0;
		Iterator<String> it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
			if(i==list.size()/2+1){
				throw e;//如果没有throws Exception的话，对于编译型异常无法通过编译
			}
			i++;
			System.out.println("---------"+i);
		}
	}
}

//编译时异常
class MyException extends Exception{
	public MyException(){
		
	}
	
	public MyException(String message){
		super(message);
	}
}
