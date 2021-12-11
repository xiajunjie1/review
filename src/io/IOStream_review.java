package io;

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
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

/*
 * IO流
 * 	在程序中的流指的是数据的处理方向，例如File类可以实现文件的创建和删除，但是File类没办法
 * 	实现文件内容的操作
 * 
 * 	在java编程中，如果想实现输入和输出操作，提供了两种不同的流
 * 		字节操作流：OutputStream、InputStream
 * 		字符操作流：Writer、Reader
 * 
 * 
 * 	OutputStream实现了两个接口，Closeable、Flushable
 * 		public void write(byte[] byte)throws IOException：将字节数组进行输出
 * 		public void write(byte[] b,int off,int len)throws IOException：实现指定范围的字节数组的输出
 * 		public abstract void write(int b)throws IOException：输出单个字节
 * 
 * 		OutputStream是一个抽象类，若要实现OutputStream的功能，必须依赖子类，若此时操作的是文件内容的输出，那么可以
 * 		使用FileOutputStream类
 * 
 * FileOutputSream
 * 		public FileOutputStream(File file)throws FileNotFoundException
 * 			使用此构造方法创建的文件输出流，每次输出的时候会采用覆盖的处理方式
 * 		public FileOutputStream(File file,boolean append)throws FileNotFoundException
 * 			使用此构造方法创建的文件输出流，每次输出的时候会采用内容追加的处理方式
 * 
 * 
 * InputStream：字节输入流
 * 		public int available()：获取全部的可用字节数
 * 		public abstract int read()throws IOException：读取单个的字节数据
 * 		public  int read(byte[] b)throws IOException:将内容读取到字节数组当中，并返回读取字节的长度
 * 		public  int read(byte[] b，int off,int len)throws IOException:读取部分内容到字节数组当中，并返回读取的字节长度
 * 
 * 
 * Writer：字符输出流
 * 	虽然OutputStream可以实现数据的输出，但是每次都需要将数据转换成字节数组。
 * 	而Writer类则可以解决这一问题
 * 	Writer实现了Appendable，所以它可以实现内容增加
 * 		public void write(char[] cbuf)throws IOException:输出字符数组
 * 		public void write(String str)throws IOException:输出字符串
 * 		public int write(int c)throws IOException:输出单个字符
 * 字符和字节数据都可以自动向int类型转换的，所以不管使用何种数据流，都会有输出或读取单个内容的支持方法，这种参数的方法或返回值都是int类型
 * 相比字节流而言，字符流可以直接实现字符串的输出，避免了字符串转换字节数组的操作
 * 
 * Reader：字符输入流	
 * 	Reader实现了Readable接口，该接口与NIO有关
 * public  int read(char[] cbuf)throws IOException:将读取到的数据读到字符数组中
 * public  int read(char[] cbuf，int off,int len)throws IOException:将数据读取到指定字符数组的部分内容
 * public  int read()throws IOException：读取单个的字符数据
 * public long skip(long n)throws IOException：跳过若干个字节后，在进行读取（定位读取位置）
 * 
 * 
 * 字节流和字符流的区别：
 * 	字节流是直接与IO的介质交互的，而字符流需要经过内存的处理，所以数据会经过内存的缓冲区
 * 	如果输出流程序中，输出流不关闭，那么字节流的数据是能输出到介质中的，而字符流不会输出到介质中
 * 	因为字符流的数据在内存缓冲区中，close方法被调用时会强制刷新缓冲区，如果流不能关闭，可以考虑用flush方法刷新
 * 	在日常的编程中，大多数情况用字节流，若是处理中文数据的话，首选字符流
 * 
 * 
 * 转换流
 * 	InputStreamReader
 * 		Reader的子类
 * 		
 * 	OutputStreamWriter
 * 		Writer的子类
 * 	
 * 	转换流的功能是将字节流转换成字符流
 * 	之前使用的文件字符流其实都是转换流的子类，也就是说，这中间就存在字符与字节的转换定义关系
 * 
 * 	
 * 
 * */

public class IOStream_review {

	public static void main(String[] args){
		//FileOutputStreamUsage();
		//InputStreamUsage();
		//WriterUsage();
		//ReaderUsage();
		//TransformStream();
		//File xfile=new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"test.txt");
		//System.out.println(xfile.getAbsolutePath());
		//System.out.println(xfile.getPath().replace(xfile.getParent(), "D:/ff"));
		//System.out.println(xfile.getParent());
		//File in=new File("F:"+File.separator+"xia"+File.separator);
		//File out=new File("F:"+File.separator+"cs111"+File.separator);
		File in =new File("F:"+File.separator+"xia"+File.separator+"pic.jpg");
		File out=new File("F:"+File.separator+"picbak.jpg");
		System.out.println(in);
		Icopy copy=new CopyUtil(in,out);
		long start=System.currentTimeMillis();
		copy.copy();
		long end=System.currentTimeMillis();
		System.out.printf("复制用时：%s ms",(end-start));
		
		
	}
	
	/**
	 * OutputStream的使用
	 * */
	private static void FileOutputStreamUsage(){
		File file = new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"test.txt");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();//创建父目录
		}
		OutputStream output=null;
		//获取OutputStream对象
		try {
			 //output=new FileOutputStream(file);//覆盖输入
			 output=new FileOutputStream(file,true);//追加输入
			String data="这是要写入的数据\r\n";
			//由于是字节流，所以输出的数据必须是字节数组
			//当OutputStream在输出的时候，如果文件对象中的文件不存在，那么会先创建文件，在直接将数据输出到文件中
			output.write(data.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(output!=null)
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	/**
	 * InputStream的使用
	 * 由于每次读取到数据是直接覆盖传入的字节数组，若最后一次读取的数据没有填满字节数组，
	 * 那么未填满的部分还存放的是上一次读取到的数据
	 * */
	private static void InputStreamUsage(){
		File file = new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"test.txt");
		try(InputStream input=new FileInputStream(file)){//实现了Closeable接口的类，可以这么写，在try执行完毕后，该资源会自动关闭
			byte[] b=new byte[4];//一次读取4个字节
			System.out.println("可读字节数："+input.available());
			//System.out.println(input.read(b));
		
			//System.out.println(new String(b));
			int len;
			StringBuilder data=new StringBuilder();
			while((len=input.read(b))!=-1){
				//System.out.println("读取到的长度"+len);
				//System.out.println(Arrays.toString(b));
				//System.out.println(new String(b));
				
				//data.append(new String(b));
				//为了使读到的数据精确，采用一下方式进行赋值
				data.append(new String(b,0,len));
			}
			System.out.println(data.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符输出流Writer的使用
	 * */
	private static void WriterUsage(){
		File file=new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"Chartest.txt");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		try(Writer writer=new FileWriter(file,true)){//指定要将数据输出到哪个文件当中,允许追加输出
			
			//writer.write("这是Writer输出的数据\r\n");//覆盖式输出
			
			writer.append("这是Writer输出的数据\r\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符输入流的使用
	 * */
	private static void ReaderUsage(){
		File file=new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"Chartest.txt");
		try(Reader reader=new FileReader(file)){
			int len=0;
			char[] chs=new char[8];
			StringBuilder data=new StringBuilder();
			while((len=reader.read(chs))!=-1){
				data.append(new String(chs,0,len));
			}
			System.out.printf("读取到的数据是：\n%S",data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换流的使用
	 * */
	private static void TransformStream(){
		File file=new File("F:"+File.separator+"xia"+File.separator+"cs"+File.separator+"Transformtest.txt");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		
		try(Writer out= new OutputStreamWriter(new FileOutputStream(file,true));
			Reader in=new InputStreamReader(new FileInputStream(file))
				)
		{
			out.write("转换输出流测试\r\n");
			out.flush();
			char[] chs=new char[8];
			int len;
			StringBuilder data=new StringBuilder();
			while((len=in.read(chs))!=-1){
				data.append(new String(chs,0,len));
			}
			System.out.println(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}


/**
 * 定义文件拷贝的接口
 * */
interface Icopy{
	public void copy();
}

/**
 * 文件拷贝工具类
 * */
class CopyUtil implements Icopy{
	private File inFile;
	private File outFile;
	
	public CopyUtil(File inFile,File outFile){
		this.inFile=inFile;
		this.outFile=outFile;
	}
	
	/**
	 * 提供对外的拷贝调用方法
	 * 也是接口的实现方法
	 * */
	@Override
	public void copy() {
		if(!this.inFile.exists()){
			System.out.println("需要复制的文件不存在！");
		}
		if(!this.outFile.exists()){
			if(!this.outFile.getName().contains(".")){
				//是文件夹
				this.outFile.mkdirs();
			}
			else{
				try {
					this.outFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(this.outFile.isFile()){
			if(this.inFile.isDirectory()){
				System.out.println("不允许将目录复制到文件当中！");
				return;
			}
			String sufix1=this.inFile.getName().substring(this.inFile.getName().indexOf("."));
			String sufix2=this.outFile.getName().substring(this.outFile.getName().indexOf("."));
			if(!sufix1.equals(sufix2)){
				System.out.println("不允许将文件转换成不同类型的文件！");
				return;
			}
		}
		
		try{
			copyFile(this.inFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于实际拷贝文件的IO操作
	 * in：需要拷贝的文件路径
	 * out：拷贝到的文件路径
	 * */
	private void handler(InputStream in,OutputStream out)throws Exception{
		byte[] b=new byte[128];//若复制的文件较大，可以增加容量
		int len;
		while((len=in.read(b))!=-1){
			out.write(b, 0, len);
		}
		if(out!=null)out.close();
		if(in!=null)in.close();
	}
	
	
	/**
	 * 拷贝操作的逻辑代码
	 * 由于涉及到文件夹内容的复制，所以使用了递归的方法，该方法是被递归调用的方法
	 * */
	private void copyFile(File inputFile)throws Exception{
		if(!inputFile.exists()){
			System.out.println("需要复制的文件不存在！");
			return ;
		}
		if(inputFile.isDirectory()){
			//需要复制的是文件夹
			File[] files=inputFile.listFiles();
			for(File temp:files){
			this.copyFile(temp);
			}
		}
		else{
			//是文件，进行复制处理
			if(this.outFile.isDirectory()){
				//需要将文件复制到该目录
				InputStream in=new FileInputStream(inputFile);
				String targetpath=this.outFile.getPath()+inputFile.getPath().substring(inputFile.getPath().indexOf(File.separator));
				File targetFile=new File(targetpath);
				if(!targetFile.getParentFile().exists()){
					targetFile.getParentFile().mkdirs();
				}
				OutputStream out=new FileOutputStream(targetFile);
				this.handler(in,out);
				
				
			}else{
				//输出对象是文件，直接覆盖输出
				InputStream in=new FileInputStream(inputFile);
				OutputStream out=new FileOutputStream(this.outFile);
				this.handler(in, out);
				
			}
		}
	}



}
