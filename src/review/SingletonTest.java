package review;

/*
 * 单例设计模式
 * 一个类有且只有一个对象。在程序的任意模块，获取到这个类的对象，是相同的对象。
 * 饿汉单例模式
 * 懒汉单例模式
 * 
 * 相比较而言，饿汉单例模式有一定的控件浪费
 * */
public class SingletonTest {
	public static void main(String[] args){
		for(int i=0;i<20;i++){
			Boss b=Boss.getInstance();
			System.out.println(i+":"+b);
			BigBoss bb=BigBoss.getInstance();
			System.out.println(i+":"+bb);
		}
	}
}
/*
 * 单例类，无论在任何地方，该类的对象有且只有一个
 * 	1.私有化构造方法，杜绝从外面实例化对象的可能性
 * 	2.提供一个私有的、静态的、当前类的对象，并实例化
 * 	3.提供一个public权限的静态方法，用来返回一个当前类的对象
 * 	饿汉式单利模式，无论是否用到该实例，首先初始化该对象
 * */
class Boss{
	private static Boss boss=new Boss();//该实例化，只有在类第一次加载的时候才会运行。之后是不会在运行的
	
	private Boss(){
		System.out.println("实例化了一个对象");//调用多次 Boss.getInstance(),该语句只会打印一次，说明只进行了一次初始化
	}
	
	public static Boss getInstance(){

		return boss;
	}
}
/*
 * 懒汉式单例
 * 当向类申请对象的时候，判断类变量是否为空，若为空在进行实例化
 * */
class BigBoss{
	private static BigBoss bigboss;
	private BigBoss(){
		System.out.println("实例化一个对象");
	}
	
	public static BigBoss getInstance(){
		if(bigboss==null){
			bigboss=new BigBoss();
		}
		
		return bigboss;
	}
}
