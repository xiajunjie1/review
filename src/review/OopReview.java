package review;
/*
 * 面向对象复习
 * 1.对象内存分析
 * 	首先在栈内存中开辟一个空间，叫作mycls，用来存放实例对象的映射地址
 * 	其次，系统看到new关键字后，会在堆内存中开辟一个空间，该空间里面存放类中的成员变量
 * 	然后将成员变量赋值，如name默认值为null，age默认值为0，然后再将该地址引用存放到mycls中
 * 
 * 2.成员访问
 * 	非静态对象：需要使用对象来访问（访问之前需要实例化对象）
 * 	静态：使用static修饰的属性或方法，静态属性，在类第一次加载到内存的时候，空间就会被分配。它是属于类的，所有对象可以共享该变量
 * 		静态属性的空间在静态区开辟空间，静态的空间是唯一的，所有的对象是共享这个空间的
 * 		静态成员利用类本身就可以访问了，对象也可以访问
 * 
 * 3.this关键字的省略
 * 	当需要访问的属性与局部变量没有重名的时候，this关键字可以写，也可以不写
 * 	当需要访问的属性与局部变量重名的时候，this关键字必须写，不可以省略
 * 
 * 4.构造方法
 * 	用于实例化对象，在实例化对象的时候调用
 * 	构造方法名必须与类名保持一致
 * 	无返回值（返回值类型部分不写）
 * 	构造方法根据有无参数，分为：无参构造、有参构造
 * 	构造方法使用：属性值初始化设置
 * 	构造方法中调用其它构造方法---this(参数）
 * 		构造方法中调用其它构造方法，必须将调用语句写在第一句
 * 		不能循环调用构造方法---如构造方法1中调用构造方法2，构造方法2中又调用构造方法1
 * 
 * 		
 * 
 * */
public class OopReview {
	public static void main(String[] args){
		//实例化对象
		Mycls mycls = new Mycls();
		//实例方法的调用
		mycls.show();
		//静态方法的调用
		Mycls.display();//1
		Mycls.display();//2
		Mycls.display();//3
		
	}
}

class Mycls{
	public String name;
	public int age;
	public static int count;
	public void show(){
		System.out.println("My name is "+this.name+" "+this.age+" years old");
		System.out.println("静态变量："+count);//0
		//非静态方法，可以访问本类中的静态成员和非静态成员
	}
	//静态方法
	public static void display(){
		count++;
		//this.count++;//语法报错，静态方法中无法使用this
		//age=100;//语法报错，静态方法中无法使用非静态属性
		System.out.println("静态变量："+count);
		
	}
	
}


class StudentCls{
	public String name;
	public int age;
	public boolean gender;//true=male,false=female
	
	//无参构造方法---默认是存在无参构造，如果重载了一个有参构造，则无参改造必须得写
	public StudentCls(){}
	//有参构造，可用来初始化成员变量
	public StudentCls(String name,int age,boolean gender){
		//由于局部变量和成员变量同名，所以必须得加上this关键字
		//this.name=name;
		//his.age=age;
		this(name,age);
		this.gender=gender;
		
	}
	
	public StudentCls(String name,int age){
		//由于局部变量和成员变量同名，所以必须得加上this关键字
		this.name=name;
		this.age=age;
	
		
	}
	
}