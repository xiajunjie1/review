package thread;

/*
 * 多线程运行状态
 * 	线程的生命周期
 * 		创建状态：在java程序中如果要进行多线程对象的创建则一定要有线程的主体类，同时要通过关键字new进行Thread类的实例化对象的创建
 * 			与该代码对应：Thread thread=new Thread(myThread);
 * 
 * 		就绪状态：当线程对象创建完成之后需要进行线程的启动，而线程的启动肯定需要通过Thread中的start方法进行启动,但是调用start方法
 * 				的时候，并不是立刻就执行多线程，它本身需要等待CPU的调度
 * 
 * 		执行状态：当我们的系统为我们线程分配了相关硬件资源后，所有线程将按照其定义的核心业务功能进行执行，但是这个执行并不是一直执行的
 * 				而是需要进行资源的抢占，即运行一段时间后，当前线程就会让出当前的资源
 * 
 * 		阻塞状态：当某一个线程让出了当前的资源后，那么该线程对象将进入到一种阻塞状态（此时线程之中未完成的代码暂时不执行了），其他的线程
 * 				继续完成自己先前未完成的任务，除了这种自动的系统级控制之外，也可以利用Thread类之中的一些线程的控制方法来进行线程阻塞操作
 * 
 * 		终止状态：当线程的方法全部执行完毕后，该线程就将释放掉所占用的全部资源，并且结束全部的执行
 * 
 * 多线程的操作方法
 * 		在多线程开发之中，所有的线程对象如果在不加控制的情况下，一旦创建则会一直执行，如果需要进行线程的延缓执行，
 * 		或是线程资源的让出等等，都可以通过Thread类提供的方法进行控制
 * 
 * 	线程命名和取得
 * 		在操作系统中，所有线程的执行是随机的，如果要对线程进行控制，那么最好的方法是利用线程名称进行标记
 * 		在实际开发的过程当中，不建议命名重名的线程名称同时对已命名的线程进行命名的修改
 * 		相关操作方法：
 * 		public Thread(Runnable runnable,String name),在初始化对象的时候，对该线程命名
 * 		public final String getName(),获取线程名称
 * 		public final void  setName(),设置或修改线程名称
 * 		
 * 		在获取线程名称的时候有一点注意：所有的线程都是不固定的执行状态，可以获得的线程名称一般都属于当前正在运行的线程对象
 * 		而要想获取当前正在运行的线程名称，则需要依靠Thread类中的一个重要方法：public static Thread currentThread()
 * 
 * 	每当使用java命令执行一个类的时候就会在操作系统的内部为其分配一个进程，而现在所有的程序执行的时候都是基于线程的方式运行的，
 * 	其中会由JVM帮助用户手工的创建一个主线程(main),而后在由主线程创建若干个子线程
 * 
 * 	线程休眠
 * 		public static void sleep(long millis)throws InterruptedException:设置休眠时间，单位为毫秒
 * 		public static void sleep(long millis,int nanos)throws InterruptedException:设置休眠时间，单位为纳秒
 * 		不管是哪种休眠的方法，都有一个InterruptedException异常，该异常会在休眠非正常结束的情况下抛出
 * 
 * 		对于当前的线程执行过程之中，每一次休眠的时候可以发现整个的休眠操作好像是所有的线程都一起进入到了休眠，到了休眠的时间之后会一起进行自动唤醒
 * 		但是这其中实际上是会存在有先后顺序。在多线程编程的实现机制中进入到run()方法的线程可能非常多，但是所有的线程一旦遇到休眠，就会按自己的时钟
 * 		进行休眠，由于线程执行的速度非常快，所以从整个的执行上来讲就会像所有线程同时休眠
 * 
 * 	线程中断
 * 		在多线程编程中，一个线程是无法自己中断线程的，需要依靠其他的线程来中断该线程
 * 		public void interrupt():线程中断
 * 		public boolean isInterrupted()：判断线程中断状态
 * 
 * 	线程的强制执行
 * 		如果某一资源非常重要，需要强制性霸占资源执行处理，这个时候就需要使用join()方法进行控制
 * 		public final void join()throws InterruptedException:此方法一旦调用，此线程将持续执行到结束
 * 		public final void join(long millis)throws InterruptedException:设置一个线程的强制占用时间,如果超过了这个时间，也要释放资源
 * 		public final void join(long millis，int nanos)throws InterruptedException:设置一个线程强制占用时间，单位可以到纳秒
 * 		该方法都会抛出一个InterruptedException,说明该方法是可以被强制中断的，即可以调用interrupt()方法进行强制中断
 * 
 * 	线程的礼让
 * 		在多线程的执行过程当中，我们所有的线程要轮流进行CPU资源的抢占，而当有一个线程抢占到了资源以后，可以通过一种礼让的形式，让出当前的资源。
 * 		如果想要执行这样的礼让操作，可以使用yield()方法
 * 		public static void yield()
 * 
 * 
 * 	线程优先级
 * 		在多线程编程里面，也包含有线程的优先级的概念，从理论上来讲：线程的优先级越高，越有可能先执行
 * 		相关常量、方法：
 * 			public static final int MIN_PRIORITY:最低优先级，代表数值为1
 * 			public static final int NORM_PRIORITY:中等优先级，代表数值为5
 * 			public static final int MAX_PRIORITY:最高优先级，代表数值为10
 * 
 * 			public final void setPriority(int newPriority):修改线程的优先级
 * 			public final int getPriority():获取线程的优先级
 * 		
 * 		创建的线程都有一个默认的优先级，默认优先级为5（中等优先级） 
 * 
 * 
 * 	线程同步问题
 * 		共同参与模型：多个线程更新公共资源
 * 			锁处理机制：同步代码块和同步方法，这两种实现的方式都依赖于关键字“synchronized”
 * 
 * 			同步代码块：使用synchronized定义的一个代码块，在使用同步代码块的时候，一般都需要进行一个同步对象的锁定
 * 			synchronized(同步对象){...}
 * 			同步对象往往可以使用当前对象this,一般而言只要保证这个同步对象是同一个对象即可，例如Runnable对象，虽然传入多个线程中
 * 			进行启动，但是传入的Runnable对象是同一个，也就是说在Runnable对象的run方法中，调用的this对象是同一个Runnable对象
 * 			如果是直接继承Thread的话，那就不能直接用this，因为每个Thread线程都是不同的对象，在里面直接调用this也是不同的对象
 * 			
 * 
 * 		生产者与消费者模型：多个线程使用公共通道
 * 
 * 	线程死锁
 * 		多线程，资源互相占用且出现资源互相等待
 * 		多个线程更新同一资源的时候必须要考虑到同步，而同步所带来的问题就是线程死锁
 * 
 * 
 * 	
 * 		
 * 		
 * 
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
public class Thread_review2 {
	public static void main(String[] args){
		//getName();
		//sleepTest();
		//InterrputTest();
		//JoinTest();
		//yiledTest();
		//priorityTest();
		//synTest1();
		synTest2();
	}
	
	/**
	 * 获取线程名称
	 * */
	private static void getName(){
		for(int i=0;i<5;i++){
			//初始化线程，并未线程命名
			new Thread(()->{
				//获取当前运行的线程的名字，并打印出来
				System.out.println("线程名称为："+Thread.currentThread().getName());
			},"【线程jayj】-"+i).start();
			//如果在新建线程的时候，没有定义线程名称，它默认会以Thread-xx的形式命名，这个xx是自动增长的数字
		}
	}
	
	/**
	 * 线程休眠测试
	 * */
	private static void sleepTest(){
		for(int i=0;i<5;i++){
			new Thread(()->{
				for(int j=0;j<30;j++){
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName()+"-"+j);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//休眠1s
				}
			},"【线程jayj】-"+i).start();
		}
	}
	
	private static void InterrputTest(){
	
			Thread thread=new Thread(()->{
			System.out.println("线程执行，等待...");
			try {
				Thread.sleep(20000);
				System.out.println(Thread.currentThread().getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("线程休眠异常!!!");
				//e.printStackTrace();
			}//休眠20s
		},"【线程jayj】");
		thread.start();
		System.out.println(thread.isInterrupted());//打印线程是否中断
		try {
			Thread.sleep(2000);
			thread.interrupt();
			System.out.println(thread.isInterrupted());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("线程中断异常");
			e.printStackTrace();
		}//让线程在执行一下
		
		
	
		}
	
	/**
	 * 线程强制执行测试
	 * 	该程序中，主线程和子线程分别进行输出自己当前的线程名，和虚幻次数，在前20次时，主线程和子线程交替执行，超过20次后
	 * 	由于主线程调用了join()方法，所以都由主线程执行，直到主线程执行完毕后，子线程才重新开始执行
	 * 
	 * */
	private static void JoinTest(){
		Thread mainThread=Thread.currentThread();//获取主线程
		Thread subThread=new Thread(()->{
			for(int i=0;i<100;i++){
				try {
					Thread.sleep(100);//设置休眠100ms
					if(i>20){
						//当i大于20后，设置子线程将资源全部交给主线程
						mainThread.join();
					}
					System.out.println("【"+Thread.currentThread().getName()+"】子线程执行-"+i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"sub");//获取子线程
		subThread.start();//启动子线程
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(100);
				System.out.println("【"+mainThread.getName()+"】主线程运行-"+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 线程礼让测试
	 * 	所谓礼让处理就是把到手的资源直接让给其他的线程进行执行处理，如果真的是在实际的多线程开发过程之中，要想实现这样的礼让
	 * 	由于会有若干个不同的线程，那么就需要为每一个线程设置一些重要的名称标记
	 * */
	private static void yiledTest(){
		Thread mainThread=Thread.currentThread();//获取主线程
		Thread subThread=new Thread(()->{
			for(int i=0;i<100;i++){
				try {
					//Thread.sleep(100);//设置休眠100ms
					if(i%2==0){
						//当为偶数次
						Thread.yield();//礼让线程
						System.out.println("子线程礼让");
					}
					System.out.println("【"+Thread.currentThread().getName()+"】子线程执行-"+i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"sub");//获取子线程
		subThread.start();//启动子线程
		for(int i=0;i<100;i++){
			try {
				//Thread.sleep(100);
				System.out.println("【"+mainThread.getName()+"】主线程运行-"+i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 优先级测试
	 * 实际设置了优先级后，优先级高的线程也只是有可能被提前调用，也就是说也可能出现优先级高的线程一直不被调用
	 * */
	private static void priorityTest(){
		Thread[] threads=new Thread[3];
		for(int i=0;i<threads.length;i++){
			threads[i]=new Thread(()->{
				for(int j=0;j<100;j++){
				try {
					Thread.sleep(200);
					System.out.println("【"+Thread.currentThread().getName()+"】执行："+j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				},"Thread"+i);
		}
		
			//设置线程优先级,一般设置优先级都在线程执行之前
			threads[0].setPriority(Thread.MIN_PRIORITY);
			threads[2].setPriority(Thread.MAX_PRIORITY);
		
			for(int k=0;k<threads.length;k++){
				threads[k].start();
			}
	}
	
	/**
	 * 线程同步方式1：同步代码块实现
	 * */
	private static void synTest1(){
		Runnable ticket=new Tseller();
		Thread t1=new Thread(ticket,"票贩1");
		Thread t2=new Thread(ticket,"票贩2");
		Thread t3=new Thread(ticket,"票贩3");
		
		t2.start();
		t1.start();
		t3.start();
	}
	
	private static void synTest2(){
		Runnable ticket=()->{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tseller.trun();
		};
		Thread t1=new Thread(ticket,"票贩1");
		Thread t2=new Thread(ticket,"票贩2");
		Thread t3=new Thread(ticket,"票贩3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
}

/**
 * 卖票Runnable类，若不加上同步代码块，则多个线程同时卖票，会出现重复卖票的情况，尤其是
 * 在判断票数是否大于0和卖票操作之间加了一个线程休眠后，这个问题愈发严重
 * 在此处可以加上同步代码块，解决数据不准确的问题
 * */
class Tseller implements Runnable{

	private static int ticket=5;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(this.ticket>0){
			synchronized(this){
			if(this.ticket>0){
				try {
					Thread.sleep(200);
					System.out.println("【"+Thread.currentThread().getName()+"】卖票，剩余票数"+--this.ticket);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				break;
			}
		}
			}
		
		
	}
	
	 /**
	  * 提供同步方法，供Runnable对象调用
	  * */
public static synchronized void trun(){
		while(ticket>0){
			
			if(ticket>0){
				try {
					Thread.sleep(200);
					System.out.println("【"+Thread.currentThread().getName()+"】卖票,剩余张数："+--ticket);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				break;
			}
		}
	
}
	
	
}