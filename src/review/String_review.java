package review;

import java.util.Arrays;

/*
 * 字符串复习
 * 	1.字符串与基本数据类型的转换
 * 		基本数据类型转字符串：
 * 			利用+号将基本数据类型转为String
 * 			利用String.valueOf()
 * 
 * 	2.字符串内存分析
 * 		字符串是在常量池中开辟空间的，字符串变量存的是常量池中的某一块地址
 * 		字符串变量重新赋值的话，其实并不是修改了常量池中字符串的值
 * 
 * 	3.字符串实例内存分析
 * 		当用new String("xxx")的初始化字符串的时候，会在堆上开辟一块内存，
 * 		然后堆上的内存中存储一个地址，该地址指向常量池中对应的字符串，然后在将堆上开辟的内存返回给字符串变量
 * 		所以用new String的方式创建String对象时，地址是不同的。
 * 
 * 	4.字符串拼接内存分析
 * 		当拼接的字符串都是字符串常量时，如"hello"+"world"它们是在常量池中进行拼接的
 * 		所以结果就是"helloworld"常量，所以"hello"+"world"=="helloworld"
 * 		如果是含有字符串变量的拼接，那么会现在堆内存上开辟一块空间，然后该空间存在一块地址，该地址指向常量池中拼接好后的字符串
 * 		然后在将该堆内存的地址返回给String对象，所以只要是含有字符串变量的字符拼接，都会生成新的对象
 * 
 * 	5.String的构造方法
 * 		无参构造：String()
 * 		字符串构造：String("xxx")
 * 		字符数组构造 String(char[] c)  String(char[] c,offset,length):从第offset位开始，去length字符进行字符串实例化
 * 		字节数组狗仔String(byte[] b)
 * 
 * 
 * 	6.String的非静态方法
 * 
 * 	7.String的静态方法
 * 		
 * 				
 * */
public class String_review {

	public static void main(String[] args){
		//basicTostring();
		//stringTobasic();
		
		/*Person p=new Person();
		p.name="test";
		Person p2=p;
		p2.name="测试";
		System.out.println(p.name);
		System.out.println(p2.name);
		String a="aaa";
		String b=a;
		b="bbb";
		System.out.println(a);
		System.out.println(b);*/
		
		String str1="hello";
		String str2=new String("hello");
		System.out.println(str1==str2);//false
		
		String str3="hello"+"world";
		String str4=str2+"world";
		String str5="helloworld";
		String str6=str2+"world";
		System.out.println(str3==str4);//false
		System.out.println(str3==str5);//true
		System.out.println(str4==str6);//false
		String str7=str1+str2;
		String str8=str1+str2;
		System.out.println(str7==str8);//false
		
		//Constractor();
		//Strmethod();
		Strstaticmethod();
	}
	
	/**
	 * 基本类型转String
	 * 
	 * */
	private static void basicTostring(){
		//利用String的valueOf方法
		String str1=String.valueOf(10);
		System.out.println(str1);
		System.out.println(str1.getClass().getName());
		
		//利用包装类的toString方法
		String str2=Integer.toString(10);
		System.out.println(str2);
		System.out.println(str2.getClass().getName());
	}
	
	
	private static void stringTobasic(){
		//1.利用valueOf
		int n1=Integer.valueOf("123");
		System.out.println(n1);
		
		//2.利用parseXXX
		int n2=Integer.parseInt("456");
		System.out.println(n2);
		
		/*推荐使用方法2，方法1底层会调用parseInt将字符串转成基本类型，然后再进行装箱返回Integer
		由于赋值的时候进行了自动拆箱，所以又将Integer对象拆箱成了int
		*/
		
		
		//特殊：字符串转字符。因为字符串是多个字符的集合，所以不能直接用上述方法将字符串转成字符
		char c="hello world".charAt(0);
		System.out.println(c);
	}
	
	
	private static void Constractor(){
		String str1=new String(new char[]{'a','b','c','d','e','f'});
		String str2=new String(new char[]{'a','b','c','d','e','f','g','h'},3,3);
		String str3=new String(new byte[]{49,89,90,91,92});
		String str4=new String(new byte[]{49,89,90,91,92},2,2);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);//1YZ[\字符数组会自动转成ASCII码来显示
		System.out.println(str4);
		
	}
	
	/**
	 * 字符串的非静态方法
	 * */
	private static void Strmethod(){
		//字符串的拼接
		String str1="adadad".concat("ooo");
		System.out.println(str1);
		//字符串的截取
		String str2="ffedddd".substring(2);
		System.out.println(str2);
		String str3="ffedddd".substring(2,4);//左闭右开
		System.out.println(str3);
		//字符序列截取，返回值是一个接口
		CharSequence s4="ffedddd".subSequence(2, 4);
		System.out.println(s4);
		//字符串的替换
		String str5 ="hello world".replace('l', 'L');//参数是两个字符
		System.out.println(str5);
		String str6="HELLO WORLD".replace("HELLO", "CHANGE");//参数是CharSequence接口对象，可以传入String
		System.out.println(str6);
		//获取特定位置的字符
		char c1="HAHAHHA----FFF".charAt(10);
		System.out.println(c1);
		//将字符串转成字符数组
		char[] chs="Go To Hell".toCharArray();
		System.out.println(Arrays.toString(chs));
		//将字符串转字节数组
		byte[] bytes="Test Test".getBytes();//里面可以指定字符集，不同的字符集，字符对应的数字可能不同
		System.out.println(Arrays.toString(bytes));
		
		//查询某一个字符第一次在字符串中出现的下标
		int index="Hello,World".indexOf('l');
		System.out.println(index);
		//查询某一个字符最后一次在字符串中出现的下标
		int lindex="Hello,World".lastIndexOf('l');
		System.out.println(lindex);
		//从字符串指定位置开始，第一个字符出现的下标
		int index2="Hello,World".indexOf('l',5);
		System.out.println(index2);
		//从字符串指定位置开始，最后一个字符出现的下标
		//lastIndexOf原理，从后往前查询，搜索到的第一个字符即是
		//加上的数字后，代表从5开始往前查询
		int index3="Hello,World".lastIndexOf('l', 5);
		System.out.println(index3);
		//小写转大写
		String str7="hello world".toUpperCase();
		System.out.println(str7);
		//大写转小写
		String str8=str7.toLowerCase();
		System.out.println(str8);
		//获取字符串的长度
		int len="Hello World!".length();
		System.out.println(len);
		
		//包含
		System.out.println("Hello World".contains("ll"));
		//以...开头
		System.out.println("Hello World".startsWith("He"));
		//以...结尾
		System.out.println("Hello World".endsWith("ld"));
		//去除一个字符串的收尾空格
		System.out.println("    Hello World    ".trim());
		
		//字符串比较大小
		//依次比较每一位字符的大小，如果某一次字符大小比较可以分出大小，就以该次大小返回
		//返回值为数字，大于0代表前面的字符串大于后面的字符串，小于0代表前面的字符串小于后面的字符串，等于0则两者相等
		System.out.println("Hello".compareTo("World"));
		
	
	}
	
	private static void Strstaticmethod(){
		//第一个参数为分隔符，后面是所有要拼接的字符串，每个字符串之前用分隔符分隔
		String join=String.join("-", "wo","cao","ni","ma","!");
		System.out.println(join);
		String[] param={"abc","bef","ghl"};
		String join2=String.join(",", param);
		System.out.println(join2);
		//字符串格式化 %s--字符串  %d---整数  %f---浮点数   %c---字符  
		//%ns：凑齐n位字符串，如果不够补空格	%nd：凑齐n位数字，不够补空格	%.nf：保留n位小数
		String name="xiaoming";
		int age=18;
		String gender="male";
		float weight=50.1f;
		String format=String.format("name:%s,age:%d,gender:%s,weight:%.2f", name,age,gender,weight);
		System.out.println(format);
	}

}

