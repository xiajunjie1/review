package review;
/*
 * 包装类复习
 * 在基本数据类型上添加上一层包裹，使其具有引用类型的功能
 * byte---Byte
 * short--Short
 * int----Integer
 * long---Long
 * float--Float
 * double-Double
 * char---Character
 * boolean-Boolean
 * 
 * 装箱
 * 拆箱
 * 自动装箱和自动拆箱：自JDK1.5之后，java支持自动装箱和自动拆箱
 * 	自动装箱：由一个基本变量向一个对应的包装类进行赋值
 * 	自动拆箱：由一个包装类变量向一个对应的基本变量进行赋值
 * 
 * 在某些情况下，是无法进行自动的装箱和自动的拆箱的
 * */
public class WrapperCls_review {
	public static void main(String[] args){
		Integer int1=Integer.valueOf(10);
		test(int1);
		test(10);
	}
	
	/**
	 * 手动的装箱：由基本类型完成对包装类型的转换
	 * 1.通过包装类的构造方法
	 * 2.通过包装类的静态方法valueOf
	 * 推荐使用方法2,因为符合享元原则
	 * 当new 一个包装类对象的时候，会开辟一个新的内存空间，如果用valueOf的话，所以引用对象都是指向的同一块儿空间
	 * */
	private static void boxing(){
		//1.通过包装类的构造方法完成
		Byte b1=new Byte((byte)10);//将byte类型的10装箱成为Byte对象
		Integer inte1=new Integer(20);
		
		//2.通过包装类的静态方法valueOf
		Byte b2=Byte.valueOf((byte)30);
		Integer inte2=Integer.valueOf(35);
		
		
	}
	
	/**
	 * 手动拆箱：由包装类型完成对应基本类型的转换
	 * 1.使用每一个包装类的xxxValue方法可以实现
	 * 
	 * */
	private static void unboxing(){
		Integer int1=Integer.valueOf(100);
		int baseint1=int1.intValue();
		System.out.println(baseint1);
	}
	
	
	/**
	 * 手动装箱和手动拆箱使用场景
	 * 在方法重载的场景下，是无法进行自动的装箱和自动的拆箱的
	 * */
	private static void test(int i){
		System.out.println("基本类型："+i);
	}
	
	private static void test(Integer i){
		System.out.println("引用类型："+i);
	}
}
