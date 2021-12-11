package review;
/*
 * 程序中的继承，是类与类之间的特征和行为的一种赠与或获取
 * 两个类之间的继承，必须满足 is a的关系
 * 
 * 使用场景：
 * 	1.对类进行功能扩展
 * 	2.可根据程序中需要使用到的多个具体类，进行共性提取
 * 
 * 不可被继承：
 * 	1.私有对象、方法不可以被继承
 * 	2.构造方法
 * 	3.默认修饰符的变量、方法不能继承给跨包子类
 * 
 * 继承特点：
 * 	Java是单继承的，有且只能有一个父类
 * 
 * 修饰符权限排序：
 * 	public>protected>默认>private
 * 	protected比默认的权限大在跨包子类的访问
 * 
 * 
 * 方法重写
 * 		父类提供的方法无法完成所需要的需求，可以在子类中定义一个相同的方法
 * 		重写要求：
 * 			方法名字一致
 * 			方法参数一致
 * 			重写方法访问权限要>=父类方法
 * 			重写方法返回值类型<=父类方法的返回值类型
 * 				即返回值类型可以和父类方法返回类型一致，也可以是它的子类型
 * 
 * 		对比重载
 * 			重载在同一个类中的同名方法，方法参数不同，返回值也可以不同。
 * 			重载是编译时多态，而重写是运行时多态
 * 
 * Super关键字
 * 	对父类方法的引用
 * 	使用场景
 * 		对父类方法的功能进行补充，即重写的时候，保留父类方法的功能，然后在其基础上进行延伸
 * 		这种情况可在重写方法中使用super.父类方法，后面在添加自己拓展的功能
 * 
 * 继承当中的构造方法
 * 	子类实例化的时候，优先实例化父类部分，这个过程中默认调用父类的无参构造方法
 * 	若父类没有无参构造或者父类无参构造访问权限不足，会造成子类无法实例化
 * 	解决：
 * 		1.访问权限不足：修改无参构造的访问权限
 * 		2.没有无参构造方法：
 * 			增加无参构造方法
 * 			在子类的构造方法中，用super()调用存在的构造方法
 * 
 * 
 * final 变量：
 * 		1.修饰变量，表示该变量的值不可变（常量）
 * 		2.修饰类，表示这个类是一个最终类，无法被继承
 * 		3.修饰方法，表示是最终方法，无法被重写
 * 			
 * */
public class Extends_review {
	public static void main(String[] args){
		Son1 son=new Son1();
		//先执行父类构造方法，再执行子类构造方法
		son.eat();
		son.sleep();
		
	}
	
}


class Father{
	public String name;
	public int age;
	
	public  Father(){
		this.name="爹";
		this.age=50;
		System.out.println(this.name+"我已经"+this.age+"了");
	}
	public void eat(){
		System.out.println("老子吃了！");
	}
	protected void sleep(){
		System.out.println("老子睡了");
	}
	
}
//继承父类
class Son1 extends Father{
	public Son1(){
		this.name="仔1";
		this.age=25;
		System.out.println(this.name+"我已经"+this.age+"了");
	}
	public void play(){}
	
	//重写
	//方法的返回值和方法名，以及参数都要和父类的方法维持一致
	//重写注解：对重写的方法做验证，添加了重写注解的方法，如果不是重写，则会报错
	
	@Override
	public void eat(){
		System.out.println("儿子吃了");
		
	}
	@Override
	//void sleep(){}//报语法错误,访问权限被缩小
	public void sleep(){
		System.out.println("儿子睡了");
	}

	
	
}