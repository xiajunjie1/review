package review;

/*
 * 接口
 * 	宏观概念：接口是一种标准
 * 	微观概念：接口是一种能力和约定
 * 		接口的定义：代表某种能力
 * 		方法的定义：能力的具体要求
 * 
 * 接口的定义
 * 	接口的定义与类相似，只是关键字class变成了interface
 * 	接口中可以定义：
 * 		方法
 * 			接口中的方法默认修饰符是public abstract,即默认的都是抽象方法
 * 			
 * 		属性
 * 			接口中的属性，默认修饰符是public static final----常量
 * 
 * 接口与抽象类比较：
 * 	相同：
 * 		接口和抽象类编译后都会生产.class字节码文件
 * 		都不能创建对象
 * 		都可以声明引用
 * 		都具备Object类型所定义的方法
 * 		都可以写抽象方法
 * 
 * 不同点：
 * 	接口中所有的属性，都是公开静态常量，默认为public static final
 * 	接口中所有的方法，都是公开抽象方法，缺省用public abstract
 * 	接口中没有构造方法、构造代码段、静态代码段
 * 
 * 
 * 实现多个接口
 * 	每个接口之间用逗号隔开，接口之间的先后顺序无影响
 * 	一个类实现了多个接口，必须重写所有接口中的抽象类
 * 如果两个接口中，有同名，但是返回值不同的方法，则这两个接口是互斥接口，无法同时实现的
 * 
 * 
 * 接口之间的继承
 * 	接口之间的继承是多继承，即一个接口可以有多个父接口。
 * 	接口多继承的时候，也不能同时继承两个互斥接口
 * 
 * 接口的多态
 * 	向上转型：实现类对象转型成接口类型。隐式转换
 * 	向下转型：接口转型为实现类---显示转换，在向下转型之前，也用instanceof进行一个判断
 * 		
 * */
public class Interface_review {
	public static void main(String[] args){
		myfirstInt mfi=new Impcls();//向上转型
		mysecondInt msi=new Impcls2();
		Impcls2 imp2;
		if(msi instanceof Impcls2){
			System.out.println("向下转型");
			imp2=(Impcls2)msi;
		}
		
		
		
	}
}

//
interface myfirstInt{
	  int a=10;//必须初始化，因为接口中的属性是静态常量
	 
	 void basefunc();//不能有方法体，默认是抽象方法
	
}

interface mysecondInt{
	public  abstract void basefunc2();//可以与其他接口中的该方法同名，但是实现类只能实现一次
	
}

//定义接口实现类
//实现类必须重写接口中所有的抽象方法，或者定义为抽象类
class Impcls implements myfirstInt
{

	@Override
	public void basefunc() {
		System.out.println("实现类实现接口方法！");
		
	}
		
		}

//实现多个接口，接口之间用逗号分隔
class Impcls2 implements myfirstInt,mysecondInt{

	@Override
	public void basefunc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void basefunc2() {
		// TODO Auto-generated method stub
		
	}
	
}

