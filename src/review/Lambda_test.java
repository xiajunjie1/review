package review;

/*
 * Lambda表达式学习
 * 
 * 	java8的新特性
 * 	从本质上来讲，lambda表达式是一个匿名函数，可以使用这个匿名函数，实现接口中的方法。对接口进行非常简单的实现
 * 	通常来说，lambda表达式，是为了简化接口而实现的
 * 	实现接口的方法：定义实现类、匿名内部类，而lambda表达式比这两种方法都简单
 * 
 * 	并不是所有的接口都能用lambda表达式实现的
 * 		接口当中必须要实现的方法有且只有一个的接口，才可以用lambda表达式实现，即只能lambda表达式只能实现函数式接口
 * 		
 * 	函数式接口
 * 		一个接口中要求必须实现的抽象方法有且只有一个，这样的接口称之为函数式接口
 * 		可以用@FunctionalInterface注解，来确保接口是一个函数式接口
 * 
 * lambda表达式的语法：
 * 		由于lambda表示的本质是一个匿名方法，所以我们不需要关注接口中需要实现的方法的名字，我们只需要关注参数列表和方法体
 * 		(参数列表)->{	方法体...	};
 * 		参数列表要保持和接口中定义的方法形参列表一致
 * 		如果有返回值需要在方法体中return
 * 		->用来分隔参数列表和方法体
 * 
 * lambda表达式的语法进阶：
 * 		可以简化lambda表达式的基础语法
 * 		参数部分的精简：
 * 			在写lambda表达式的时候，参数列表中的参数类型可以不写(a,b)->{....}
 * 			参数列表中的参数，有且只有一个的时候，小括号可以省略
 * 		方法体部分的精简：
 * 			方法体中只有一句话，大括号可以省略，若这一句话是return语句，那么省略大括号的同时，return也必须省略
 * 
 * lambda表达式函数的引用：
 * 		在lambda表达式中不宜出现较为复杂的逻辑，如果有这种情况，可以把方法体单独写一个方法，lambda表达式直接引用该方法即可
 * 		引用一个存在的方法，代替lambda表达式需要完成的实现
 * 
 * 对象方法的特殊引用
 * 		
 * 
 * 	
 * */

public class Lambda_test {
public static void main(String[] args){
	
	Noparanoret noparanoret = ()->{
		System.out.println("无参数，无返回值!");
	};
	noparanoret.noreply();
	
	//lambda表达式简化写法，参数列表也可以写成(a)或者(int a)
	Oneparanoret oneparanoret = a->{
		System.out.println("一个参数，无返回值！参数为："+a);
	};
	oneparanoret.oneparanoreply(10);
	
	//方法体简写
	Multparanoret multparanoret = (a,b)-> System.out.println("多参数，无返回值！参数a: "+a+"参数b: "+b);
	multparanoret.multparanoreply(10, 12);
	
	//带return的方法体简写
	Noparahasret noparahasret = ()->10;
	int res1=noparahasret.reply();
	System.out.println(res1);
	
	Oneparahasret oneparahasret = (a)->{
		
		System.out.println("一个参数，有返回值！");
		return a;
	};
	int res2=oneparahasret.oneparareply(10);
	System.out.println(res2);
	
	Multparahasret multparahasret = (a,b)->{
		System.out.println("多参数，有返回值");
		return a+b;
	};
	int res3=multparahasret.multparahasret(10, 12);
	System.out.println(res3);
	
	//lambda表达式静态函数的引用
	Caculator caculator = LambdaCall::lambda1;//等价于(x,y)->LambdaCall.lambda1(x,y)
	System.out.println(caculator.caculat(10, 12));
	
	//实例方法的引用
	Caculator caculator2=new LambdaCall()::lambda2;
	System.out.println(caculator2.caculat(22, 24));
	
	//lambda调用无参构造
	GetPerson getperson=myPerson::new;
	GetPerson2 getperson2=myPerson::new;
	GetPerson3 getperson3=myPerson::new;
	getperson.getPerson();
	getperson2.getPerson("xia");
	getperson3.getPerson("jun", 12);
	
	
	//lambda表达式对象方法的特殊调用
	//myinter myint1=(mct)->mct.getName();与下面的写法等价
	myinter myint1 = myclsTest::getName;
	
	//myinter2 myint2= (mct,a)->mct.setName(a);与下面写法等价
	myinter2 myint2= myclsTest::setName;
	
	myclsTest mct=new myclsTest();
	myint2.set(mct, "jie");
	System.out.println(myint1.get(mct));
	
	
}

	
//函数式接口
@FunctionalInterface
interface funcint{
	
	public abstract void absfunc();
}

interface funcint2 extends funcint{
	//虽然该接口中没有方法，但是它继承的父接口中有一个方法，也是需要实现的，所以它也属于是函数式接口
}

interface funcint3{
	//该接口中的test2加了default修饰，可以有默认的实现方法体，实现类当中可以实现该方法，也可以不实现该方法
	//所以必须实现的方法有且只有一个test
	void test();
	default void test2(){}
}

interface funcint4{
	//该接口中的toString方法是Object当中实现了的方法，实现类可以从Object当中继承，不是必须实现
	//所以该接口当中必须实现的方法有且只有一个test3
	void test3();
	String toString();
}
//无参无返回值的函数式接口
@FunctionalInterface
interface Noparanoret{
	void noreply();
}

//一个参数无返回值的接口
@FunctionalInterface
interface Oneparanoret{
	void oneparanoreply(int a);
}

//多个参数，无返回值
interface Multparanoret{
	void multparanoreply(int a,int b);
}

//无参数，有返回值
interface Noparahasret{
	int reply();
}
//一个参数，有返回值
interface Oneparahasret{
	int oneparareply(int a);
}

interface Multparahasret{
	int multparahasret(int a,int b);
}
@FunctionalInterface
interface Caculator{
	public int caculat(int a,int b);
}
@FunctionalInterface
interface GetPerson{
	myPerson getPerson();
}

@FunctionalInterface
interface GetPerson2{
	myPerson getPerson(String a);
}
@FunctionalInterface
interface GetPerson3{
	myPerson getPerson(String a,int b);
}

@FunctionalInterface
interface myinter{
	String get(myclsTest mct);
}
@FunctionalInterface
interface myinter2{
	void set(myclsTest mct,String a);
}


//非函数式接口
//@FunctionalInterface---会报错
interface unfuncint{
	public abstract void absfunc();
	public abstract void absfunc2();
}

interface unfuncint2{
	
}


}


/**
 * 用于存放被调用的方法
 * */
class LambdaCall{
	
	public static int lambda1(int x,int y){
		if(x>y)return x-y;
		else if(x<y)return y-x;
		return x+y;
	}
	
	
	public int lambda2(int x,int y){
		if(x>y)return x-y;
		else if(x<y)return y-x;
		return x+y;
	}
	
}

class myPerson{
	String name;
	int age;
	
	public myPerson(){
		System.out.println("无参构造");
	}
	
	public myPerson(String name){
		this.name=name;
		System.out.println("一参构造"+name);
	}
	
	public myPerson(String name,int age){
		this.name=name;
		this.age=age;
		System.out.println("多参构造"+name+","+age);
		
	}
}

class myclsTest{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
