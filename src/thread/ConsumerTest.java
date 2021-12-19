package thread;
/*
 * 生产者消费者问题
 * 	生产者往公共区间放入内容，消费者从公共区间中取走数据
 * 	在生产者和消费者的模型之间，需要如下的协同处理
 * 		1.生产者在生产的过程中有很多工序，如果其中有一道工序未完成，那么消费者无法取走商品
 * 		2.如果生产者生产能力很强，并且消费者消费能力弱，则会出现下面的情况：当商品生产完成，却未被取走。那么生产者应该等待消费者取走之后再继续生产
 * 		3.如果消费者能力很强，没有新的产品取走，那么它应该等待生产者生产出新的商品，然后再进行取走。
 * 
 * 在实际项目开发中，消息组件就是一种生产者和消费者的模型
 * 
 * 
 * 线程同步相关处理方法
 * 	public final void wait()throws InterruptedException:线程等待，一直到被唤醒为止
 * 	public final void wait(long timeoutMillis)throws InterruptedException:线程等待，如果达到了超时时间则停止等待
 * 	public final void wait(long timeoutMillis)throws InterruptedException:线程等待，可以设置纳秒
 * 	
 * 	public final void notify():唤醒等待线程，所有的线程按照等待顺序依次恢复
 * 	public final void notifyAll():唤醒全部等待线程，优先级高的线程可能优先恢复
 * 	
 * 以上的方法都是在synchronized方法中才去使用，同时这些方法属于最原始的多线程协作控制，如果真的用这些方法来进行开发，那么项目上面对于死锁的情况就会频发
 * 
 * 
 * 守护线程
 * 	一个线程随行的保镖，本质上它也是一个线程，只是这种线程随着启动它的线程而存在
 * 	在java程序里面，所有的线程都是通过Thread来实现控制的，那么如果想将某一个线程设置为守护线程，可以使用如下的方法：
 * 		public final void setDaemon(boolean on):将当前线程设为守护线程
 * 		public final boolean isDaemon():判断当前线程是否为守护线程
 * 
 * volatile
 * 	该关键字主要是定义在属性的声明上
 * 	
 * 	传统程序变量操作的流程：
 * 		1.如果要进行变量的修改，一定要先将主内存中的变量，拷贝一份到线程内存中，此时线程内存中的变量为变量副本
 * 		2.随后在线程内存中针对于变量的副本数据进行操作（赋值，修改等）
 * 		3.当所有线程中的变量修改完成后，则需要与主内存中的变量进行同步操作
 * 	所以线程内存中的变量与主内存中的变量会存在同步延迟
 * 
 * 	volatile关键字的作用：不再进行副本定义，直接操作主内存中的变量
 * 	volatile避免了各种拷贝变量及重新同步所带来的延迟损耗（这之中就会存在有同步问题），而可以直接进行更加快速的
 * 	主内存变量访问（如果要是直接访问主内存中的变量，就可以减少这种延迟，那么看起来就会有点立刻“同步”的意思）
 * 
 * */
public class ConsumerTest {
	public static void main(String[] args){
		//ProdCosm();
		DaemonTest();
	}
	
	
	/**
	 * 生产者消费者模型测试
	 * 分析执行结果，可得出以下问题：
	 * 	1.生产者在没有完成信息生产的时候，消费者已经获取到数据了(null值)，获取的数据就属于一个半成品数据
	 * 	2.如果说现在消费者的性能高于生产者，那么会出现同样的信息即重复消费问题
	 * 	3.如果生产者的性能高于消费者的性能，那么会出现重复生产的问题
	 * */
	private static void ProdCosm(){
		//message msg=new message();
		Newmessage nmsg=new Newmessage();
		//给生产者、消费者类传入的是同一个message实例对象，所以他们里面的数据是共有的
		Thread productor=new Thread(new Productor(nmsg));
		Thread comsumer=new Thread(new Comsumer(nmsg));
		productor.start();
		comsumer.start();
	}
	
	/**
	 * 守护线程测试
	 * 只要有一个通过同一个Runnable对象，main创建出来的线程，在运行那么守护线程就会一直执行
	 * 只要当所有的线程执行完成后，守护线程也会结束
	 * */
	private static void DaemonTest(){
		mainThread main=new mainThread();
		Thread main1=new Thread(main);
		Thread main2=new Thread(main);
		main1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		main2.start();
	}
}


/**
 * 消息类，作为生产者，消费者模型的公共区间
 * 
 * */
class message{
	private String title;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

/**
 * 通过修改message类，解决生产者消费者中存在的问题
 * message类，会同时被生产者和消费者两个线程进行操作，所以所有的同步等待和唤醒操作，就只能字啊message类中完成
 * 利用synchronized方法解决数据混乱的问题，即title更新了，但是content未更新，消费者就已经读取数据了
 * 通过设置一个标记，来控制线程的等待和唤醒，从而解决重复生产或者重复消费的问题
 * 
 * 将生产和消费的操作，全部都放到公共区间类中，加以控制，这样即可解决之前模型中存在的三个问题
 * */
class Newmessage{
	private String title;
	private String content;
	//true代表允许生产，消费者等待，false代表允许消费，生产者等待
	private boolean flag=true;
	
	/**
	 * 为了同步操作，将生产的过程都放入到一个同步方法中
	 * */
	public synchronized void set(String title,String content){
		if(flag==false){
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
			this.title=title;
			this.content=content;
			this.flag=false;//设置不可生产，消费者可以消费
			super.notify();//唤醒其他等待线程
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized String get(){
		if(this.flag==true){
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.flag=true;
		super.notify();
		return "消费者获取到数据：title："+this.title+"、content："+this.content;
	}
}

/**
 * 生产者类
 * 往message中交替填充数据
 * */
class Productor implements Runnable{

	private message msg;//接收公共区间对象，方便往里面填入数据
	private Newmessage nmsg;
	public Productor(message msg){
		this.msg=msg;
	}
	public Productor(Newmessage nmsg){
		this.nmsg=nmsg;
	}
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			try{
			if(i%2==0){
				//Thread.sleep(100);//模拟操作延迟
				//this.msg.setTitle("百度");
				//this.msg.setContent("www.baidu.com");
				this.nmsg.set("百度", "www.baidu.com");
				
			}else{
				//Thread.sleep(100);
				//this.msg.setTitle("谷歌");
				//this.msg.setContent("www.google.com");
				this.nmsg.set("谷歌", "www.google.com");
				
			}
			}catch(Exception e){
				System.out.println("线程休眠异常!");
			}
		}
	}
	
}

/**
 * 消费者类
 * 	方便从里面取走对象
 * */
class Comsumer implements Runnable{
	private message msg;//接收公共区间对象，方便从里面取走数据
	private Newmessage nmsg;
	public Comsumer(message msg){
		this.msg=msg;
	}
	
	public Comsumer(Newmessage nmsg){
		this.nmsg=nmsg;
	}
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			try {
				//Thread.sleep(50);//模拟操作延迟,且消费者性能高于生产者
				//System.out.println("消费者获取到数据：title："+this.msg.getTitle()+"、content："+this.msg.getContent());
				System.out.println(this.nmsg.get());
			} catch (Exception e) {
				System.out.println("线程休眠异常");
			}
		}
	}
	
}

/**
 * 线程类，在里面定义一个守护线程
 * */
class mainThread implements Runnable{

	public mainThread(){
		Thread DeamonThread =new Thread(()->{
			for(int i=0;i<Integer.MAX_VALUE;i++){
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("【守护线程输出：】"+i);
			}
		});
		//设置该线程为守护线程
		DeamonThread.setDaemon(true);
		DeamonThread.start();
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("【"+Thread.currentThread().getName()+"】线程输出--"+i);
			
		}
		
	}
	
}
