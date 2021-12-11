package thread;

/*
 * Java多线程编程
 * 	java本身不支持有多进程的程序开发，但是java支持多线程的程序开发，那么就需要清楚的区分出进程和线程之间有哪些联系
 * 	所谓的多线程指的是在一个进程基础上的划分
 * 
 * 	在java的程序中，所有的线程的主类必有有严格的继承或实现要求
 * 
 * 	父类：Thread
 * 	接口：Runnable、Callable（JDK1.5)
 * 
 * 	Thread类：直接实现多线程的主类设置
 * 		由于java中的单继承的局限，所以强制继承Thread父类的结构并不提倡
 * 		按照之前的分析来讲如果要想进行多线程的开发，那么首先就需要定义一个线程的主体类，而这个主体类
 * 		现在就可以直接继承Thread父类，同时要求覆写Thread中的run()方法
 * 		
 * 		错误的调用：线程的主体类直接调用run方法
 * 		所有线程当中，可以用start()启动线程，该方法的作用是JVM调用run()方法
 * 
 * Runnable接口：
 * 		在JDK1.8之后，使用函数式接口来进行Runnable的定义
 * 		它里面有一个public void run(),作为线程的主方法
 * 		用户只需要实现该接口，实现run方法，即可完成线程主类的定义，在JDK1.8后，可以配合lambda表达式使用
 * 
 * 
 * 
 * 		
 * 
 * */
public class Thread_review {
	public static void main(String[] args){
		//ThreadTest();
		RunnableTest();
	}
	
	private static void ThreadTest(){
		new myThread("线程A").start();
		new myThread("线程B").start();
		new myThread("线程C").start();
	}
	
	private static void RunnableTest(){
		new Thread(new myThreadRun("线程1")).start();
		new Thread(new myThreadRun("线程2")).start();
		//使用lambda表达式
		new Thread(Thread_review::run).start();
		new Thread(new myThreadRun("线程3")).start();
		
		
	}
	
	public static void run(){
		for(int i=0;i<10;i++){
			System.out.println("【测试线程】:"+i);
			
		}
	}
}


class myThread extends Thread{
	private String name;
	
	public myThread(String name){
		this.name=name;
	}

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("【"+this.name+"】:"+i);
		}
	}
	
}

/**
 * 此时虽然定义了一个线程主体类，同时这个类本身也避免了Thread继承时单继承的局限，但会存在另外一个问题
 * 按照多线程实现机制来讲，所以多线程一定要通过Thread类的start()方法，而如果实现Runnable，则这个接口
 * 没有start()方法
 * 由Thread的源代码可知道，Thread类可以接收Runnable对象，并有一个属性target接收该对象
 * Thread的原生run方法中，判断如果target不为空，那么就调用target中的run方法。
 * */
class myThreadRun implements Runnable{

private String name;
	
	public myThreadRun(String name){
		this.name=name;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("【"+this.name+"】:"+i);
		}
		
	}
	
}
