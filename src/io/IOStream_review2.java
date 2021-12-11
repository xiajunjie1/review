package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * 内存操作流
 * 	在输入和输出操作时，不会产生文件
 * 	字节内存操作流
 * 		ByteArrayInputStream:字节内存输入流
 * 		ByteArrayOutputStream:字节内存输出流
 * 		
 * 	字符内存操作流
 * 		CharArrayReader:字符内存输入流
 * 		CharArrayWriter:字符内存输出流
 * 	
 * 	内存流的意义：
 * 		在实际项目过程中，有些输出的过程仅仅是能够在内存中处理的，例如登录验证码这样的图片都是在内存中处理的
 * 		在JDK1.9之前，可以结合内存输出流以及文件输入流实现整个文件数据的全部读取
 * 
 * 
 * 
 * 管道流
 * 		管道最早指的是进程之间的数据通讯模式，为了解决线程间数据的操作，引用了管道流
 * 
 * 		PipedOutputStream/PipedInputStream
 * 		PipedWriter/PipedReader
 * 
 * RandomAccessFile：随机读写文件
 * 	利用保存数据的固定长度，实现文件读取指针的跳跃
 * 	所谓的随机读写实际上就相当于在整个文件中设置一个有一个文件读取的指针，每次读取的时候都会依据指针所在
 * 	的位置进行指定长度数据的读取，如果要想读取某一条数据，那么只需要修改指针的位置即可，这样的读取操作被称为
 * 	随机文件读取，而随机文件读取所能够使用的类就是java.io.RandomAccessFile
 * 
 * 	它实现了DataOutput、DataInput两个接口，即它既可以读也可以写
 * 	
 * 构造方法：
 * 		public RandomAccessFile(File file,String mode)throws FileNotFoundException
 * 		mode主要有两种,只读(r)、读写(rw)
 * 
 * 		public int read(byte[] b)throws IOException:读取指定的字节数据
 * 		public final int readInt()throws IOException:读取整数
 * 		public final double readDouble()throws IOException：读取浮点数
 * 		public void seek(long pos)throws IOException:设置读取指针的位置
 * 		public int skipBytes(int n)throws IOException:跳过指定长度的字节
 * 		public void write(byte[] b]throws IOException:写入字节数据
 * 		public final void writeInt(int v)throws IOException:写入整形数据
 * 		public final void writeDouble(double v)throws IOException:写入浮点型数据
 * 
 * 
 * 打印流：
 * 	无论是字节输出流还是字符输出流，在面对java中各种类型数据的输出的时候，都存在一个类型转换的问题，使用起来比较不方便
 * 	在java中，打印流就是用来解决这个问题的。打印流用到了装饰设计者模式的思想
 * 		PrintWriter（字符打印流）
 * 		构造方法
 * 			public PrintWriter(File file)
 * 			public PrintWriter(OutputStream out)
 * 			public PrintWriter(Writer out)
 * 
 * 		PrintStream（字节打印流）
 * 		构造方法
 * 		public PrintStream(File file)
 * 		public PrintStream(OutputStream out)
 * 
 * 
 * 	使用打印流更多的是关注具体的数据输出，也就是说具体的字符串或者是数字，通过完整的对比我们可以发现PrintWriter
 * 	实际上所支持的输出的装饰操作要比PrintStream多很多，意味着PrintWriter在开发中更加的方便，所以后续的很多程序
 * 	进行输出的时候，推荐使用PrintWriter
 * 
 * 	打印流中，输出支持常规的print、println、printf
 * 	通过比较可知，在以后的程序中，如果要输出具体的数字（非二进制），通过打印流是最方便的
 * 
 * SystemIO
 * 	System.out:常量，用于向屏幕输出-------OutputStream的子类
 * 	System.in:常量，用于读取键盘输入数据----InputStream的子类
 * 
 * BufferedReader
 * 	缓冲流
 * 	在实际的开发中，如果使用InputStream或者Reader进行数据读取的时候，都需要将数据保存在数组中，
 * 	但是这样的操作过于繁琐了，所以此时可以考虑将读取到的内容暂时放到一个缓冲区当中，这样需要的时候，
 * 	一次性读取全部内容，避免繁琐的操作，所以就有了一个专属的类：BufferedReader
 * 
 * 	构造方法
 * 	public BufferedReader(Reader in)
 * 	读取一行数据
 * 	public String readLine()throws IOException
 * 
 * 	InputStream及其子类可以通过InputStreamReader转换成Reader，作为参数传递到BufferedReader里面
 * 
 * 
 * 
 *	Scanner
 *		JDK1.5之后，为了简化数据输入的操作，提供了Scanner类
 *		它属于java.util包中
 *		它实现了Closeable接口，它与输入输出流没有明确的继承关系存在
 *		它实现了Iterator接口
 *
 *	构造器：
 *、		public Scanner(File file)throws FileNotFoundException:获取一个文件的扫描流
 *		public Scanner(File file,String charsetName)throws FileNotFoundException:采用特定的编码方式打开文件
 *		public Scanner(InputStream in):明确定义一个字节输入流
 *		public Scanner(InputStream in,String charsetName):明确采用特定编码打开一个字节输入流
 *		public Scanner(String source):直接将一个字符串作为一个输入流
 *
 *	通过Scanner可以实现InputStream的简化操作
 *
 *	public boolean hasNext()：判断是否有数据
 *	public boolean hasNextXxx():判断是否有一个指定数据的存在
 *	
 *	public String next():获取当前的输入数据
 *	public Xxx nextXxx():获取指定类型的数据
 *	public Scanner useDelimiter(String pattern):设置读取分隔符
 *
 *	在使用Scanner的时候，如果发现没有数据或者数据内容不匹配，则就有可能造成数据输入繁琐，所以在不方便
 *	用Scanner类进行数据输入的时候强烈推荐使用BufferedReader类实现处理，由此可得出结论：
 *		1.如果要通过程序实现数据的输出，那么一定要选择使用PrintWriter/PrintStream
 *		2.如果要实现数据内容的输入，最新方法是使用Scanner，而Scanner类不好用的时候，则建议使用BufferedReader进行读取
 *
 *
 *	
 *
 *		
 *
 *  	
 * 
 * */
public class IOStream_review2 {
public static void main(String[] args){
	try {
		//memStreamUsage();
		//PrintUsage();
		//BuffereReaderUsage();
		ScannerUsage();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 用文件输入流和内存输出流完成文件内容的读取
 * */
private static void memStreamUsage() throws IOException{
	//读取文件的数据
	
	InputStream in=new FileInputStream(new File("F:"+File.separator+"xia"+File.separator+"test.txt"));
	//接收文件的数据
	ByteArrayOutputStream out=new ByteArrayOutputStream();
	int len;
	
	byte[] b=new byte[8];
	while((len=in.read(b))!=-1){
		
		//System.out.println(new String(b,0,len));
		out.write(b,0,len);
		
	}
	//输出流转成字符串，并输出
	String content=new String(out.toByteArray());
	System.out.println(content);
	in.close();
	out.close();
}

/**
 * 打印流的使用
 * */
private static void PrintUsage()throws IOException{
	try(
			//PrintWriter pw=new PrintWriter(new File("F:"+File.separator+"xia"+File.separator+"ptest.txt"))
			PrintWriter pw =new PrintWriter(new FileOutputStream(new File("F:"+File.separator+"xia"+File.separator+"ptest.txt"),true));
			){
		pw.println("这是打印流的测试");
		pw.println(100);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * BufferedReader的使用，通过BufferedReader实现读取键盘的输入
 * 一般如果通过字符数组来读取System.in的数据可能会因为数组大小的问题，导致读取的数据不全
 * 而用BufferedReader中的readLine()就可以解决这个问题
 * */
private static void BuffereReaderUsage()throws IOException{
	BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	System.out.print("请输入内容：");
	System.out.println("内容回显："+buf.readLine());//以回车为分隔符
	buf.close();
	
	//通过缓冲输入流，读取文件的数据
	File file=new File("F:"+File.separator+"xia"+File.separator+"ptest.txt");
	//BufferedWriter bout=new BufferedWriter(new FileWriter(file,true));
	BufferedReader buf2=new BufferedReader(new FileReader(file));
	String data;
	while((data=buf2.readLine())!=null){
		System.out.println(data);
	}
	buf2.close();
	
}
	
	/**
	 * Scanner的使用
	 * 
	 * */
	private static void ScannerUsage()throws IOException{
		System.out.print("请输入数据：");
		Scanner sc=new Scanner(System.in);
		if(sc.hasNext()){
			System.out.println("数据回显："+sc.next());
		}
		
		//通过Scanner控制输入的数字为整型
		System.out.print("请输入整数：");
		if(sc.hasNextInt()){
			System.out.println("输入的整数为："+sc.nextInt());
		}else{
			System.err.println("输入的内容不为整数");
		}
		
		//Scanner可以结合正则表达式，来对输入的数据进行验证
		
		
		//Scanner读取文件数据，注意，在读取文件的时候要指定好分隔符（默认只要是空格或者换行都描述分隔符）
		File file=new File("F:"+File.separator+"xia"+File.separator+"ptest.txt");
		//Scanner sc2=new Scanner(new FileInputStream(file));
		Scanner sc2=new Scanner(file);
		sc2.useDelimiter("\n");//指定换行为分隔符
		/*
		while(sc2.hasNext()){
			//默认遇到空格或者换行，当前读到的数据都会被输出一次
			System.out.println(sc2.next());
		}*/
		while(sc2.hasNext()){
			//System.out.println("cssss");
			System.out.print(sc2.next());
		}
	}


}
