package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
 * Thread和Runnable的联系
 * 	Thread类它实现了Runnable接口，也就是说Thread中的run方法是对Runnable接口中的run方法的重写
 * 	同时如果想要启动线程，还需要将Runnable接口子类的对象实例传到Thread类之中，这种处理结构类似代理设计模式
 * 	但是当前的程序又和传统的代理设计模式有所区别，如果在真是的代理设计模式中，代理主题类的对象所能够调用的方法
 * 	应该是接口自定义的方法名称，此时如果通过Thread类的run()方法进行启动，那么这样的代理就更合理了
 * 	
 * 	使用Runnable实现的多线程可以更加清晰的描述处数据数据共享的概念（Thread也可以描述处这种概念，但是相比较而言还是Runnable更适合）
 * 
 * 
 * Callable接口的使用
 * 	使用Runnable接口实现的接口会有一个问题，它无法进行执行完毕后的返回。
 * 	即，如果在run方法中执行一个循环进行字符串的拼接，然后将拼接的结果保存为一个属性。我们在主方法中启动线程
 * 	然后在打印这个结果属性，执行后没法得到拼接完成的字符串，因为这个时候线程中run方法的内容还没执行，主方法
 * 	就已经对该结果属性进行了输出。在JDK1.5之后提供了一个“J.U.C（java.util.concurrent）”的新的线程开发包，这个开发包里面针对多线程
 * 	的实现提供了另一个新型处理接口----Callable
 * 
 *	Callable接口结构：
 *		public interface Callable<V>{
 *			public V call() throws Exception;
 *		}
 *	该接口中的call()类似于Runnable中的run()方法，而且它可以返回一个值
 *	由于call方法存在返回值，所以Callable采用了异步的处理架构，虽然所有的线程都需要通过Thread来启动，
 *	但是对于异步数据的返回就必须使用一个最为重要的接口：Future，它当中有一个get()方法，就是通过该方法得到call方法的返回值
 *	Callable接口与Thread并无直接联系，但是Future接口有一个子类RunnableFuture<V>接口，该接口同时继承了Future和Runnable两个接口
 *	在RunnableFuture接口下，又有一个FutureTask（异步任务）的实现类，即RunnableFuture实例对象可以由FutureTask实现
 *	又由于RunnableFuture是Runnable的子类，所以Runnable接口也可以接收FutureTask对象
 *	而FutureTask类中有如下两个构造方法：
 *		public FutureTask(Runnable runnable,V result)
 *		public FutureTask(Callable<v> call)
 *	
 * 
 * 
 * 	
 * 	
 * 
 * 
 * 		
 * 
 * */
public class Thread_review {
	public static void main(String[] args){
		//ThreadTest();
		//RunnableTest();
		//sharedata();
		CallableTest();
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
	
	/**
	 * 通过Runnable接口子对象，实现数据共享
	 * 分析可知，虽然这里获取了三个线程，但这三个线程接收的是同一个
	 * Runnable对象，即他们三个中的target变量是同一个对象，即它们三个调用同一个对象的run方法
	 * 由此实现了数据共享
	 * */
	private static void sharedata(){
		TicketSell seller=new TicketSell();//定义Runnable接口子对象
		//开启多个卖票线程
		Thread sellerA=new Thread(seller);
		Thread sellerB=new Thread(seller);
		Thread sellerC=new Thread(seller);
		//开始卖票
		sellerA.start();
		sellerB.start();
		sellerC.start();
	}
	
	/**
	 * Callable线程调用
	 * */
	private static void CallableTest(){
		myThreadCall callable=new myThreadCall();
		//Runnable runable=new FutureTask(callable);
		FutureTask<String> future=new FutureTask<String>(callable);
		new Thread(future).start();
		try {
			//异步获取线程执行完毕后的返回值
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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


/**
 * 卖票模型，每个线程都可以进行卖票的操作
 * */
class TicketSell implements Runnable{
	private int ticket=5;
	@Override
	public void run() {
		while(this.ticket>0){
			if(this.ticket>0){
				System.out.println("售票："+this.ticket--);
			}else{
				break;
			}
		}
		
	}
	
	
}

/**
 * 利用Callable接口实现多线程，并取得线程运行后的返回值
 * */
class myThreadCall implements Callable<String>{
	private StringBuilder result=new StringBuilder();
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			this.result.append(i);
		}
		return result.toString();
	}
	
}
