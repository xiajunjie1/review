package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 对象序列化
 * 	可以将内存中的实例化对象的内容取出，并直接转换为二进制的数据流，在转换完成之后，其他的程序就可以通过
 * 	这个二进制的数据内容直接进行该对象的还原处理，但是在java中，不是所有类的对象都拥有这种二进制转化功能
 * 	，所以为了明确的对可以实现的二进制转换的类进行标注，在java.io中提供有一个Serializable接口
 * 	该接口中没有方法，它仅是一个标识性接口，表示该类的对象可以序列化处理
 * 
 * 	在序列化和反序列化处理定义之中，为了保证反序列化操作的正确性，一般会在类中去定义一个序列化版本编号，这个
 * 	编号的常量名称是固定好的"serialVersionUID",并且需要手工为它分配一个不会重复的内容，但是随着技术的发展
 * 	对于程序中是否要编写"serialVersionUID"版本编号已经没有太多的要求了，因为会自动的为其分配一个指定的序列化
 * 	版本编号。
 * 
 * 	虽然现在Book类实现了序列化和反序列化的标记接口，但是对于具体的序列化操作形式是由JDK决定的，因为序列化后的数据属于二进制数据
 * 	而对于二进制数据必须采用正确的方式才可以实现数据的内容的读取（反序列化），为了正确的实现序列化与反序列化的操作，在java.io中
 * 	就提供了两个专属的类：ObjectOutputStream、ObjectInputStream
 * 	
 * 构造方法：
 * 		public ObjectOutputStream(OutputStream out)throws IOException
 * 		public ObjectInputStream(InputStream in)throws IOException
 * 
 * 操作方法
 * 		public final void writeObject(Object obj) throws IOException
 * 		public final Object readObject() throws IOException,ClassNotFoundException
 * 
 * transient关键字
 * 		在对对象进行序列化的时候，如果对象中某些属性不希望被序列化，那么就可以考虑在这个属性上使用transient关键字
 * 
 * 
 * */
public class Serializable_review {
	public static void main(String[] args){
		try {
			//SerialHandler();
			System.out.println(DeSerial());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 对对象进行序列化
	 * */
	private static void SerialHandler() throws Exception{
		File file=new File("F:"+File.separator+"xia"+File.separator+"serialtest");
		Book book = new Book("这是只是一个测试","小夏",39.99);
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(book);
		oos.close();
	}
	
	/**
	 * 反序列化
	 * */
	private static Object DeSerial() throws Exception{
		Object obj=null;
		File file=new File("F:"+File.separator+"xia"+File.separator+"serialtest");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		obj=ois.readObject();
		return obj;
	}
}

class Book implements Serializable{

	private String title;
	private String author;
	private double price;
	public Book(String title, String author, double price) {
		
		this.title = title;
		this.author = author;
		this.price = price;
	}
	@Override
	public String toString() {
		return "标题："+this.title+"、作者："+this.author+"、价格："+this.price;
	}
	
	
}
