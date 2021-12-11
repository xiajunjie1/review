package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * IO编程-File类
 * 	File类实现了Comparable接口，即File类对象可以使用Arrays.sort的方式来进行大小关系的排序
 * 	File的构造：
 * 		public File(String path)---设置一个要操作文件的完整路径
 * 		public File(String parent,String child)---设置有文件的父目录和子文件路径
 * 	文件的创建：
 * 		public boolean createNewFile()throws IOException
 *
 *	文件的删除
 *		public boolean delete()
 *
 *	判断文件是否存在
 *		public boolean exists()
 *
 *	问题：
 *		1.在java编程的过程当中，实际上是通过JVM虚拟机实现了磁盘的操作，所以在操作文件时，会有一定的延时
 *		2.在程序开发过程中，若使用的操作系统不同（Windows、macOS、Linux...），会出现一个路径分隔符的问题
 *			Windows默认的路径分隔符：\\，现在也支持/
 *			Linux系统中(类Unix)路径分隔符:/
 *		设计IO程序的时候，为了适应更多的操作系统，解决分隔符的问题，File类中提供了一个常量separator
 *		
 *	创建单级目录
 *		public boolean mkdir()
 *	创建多级目录
 *		public boolean mkdirs()
 *	获取父路径
 *		public String getParent()
 *	获取父路径的文件对象
 *		public File getParentFile()
 *
 *	判断当前路径是否有可读、可写、可执行的权限
 *		public boolean canRead()
 *		public boolean canWrite()
 *		public boolean canExcute()
 *
 *	获取文件绝对路径实例
 *		public File getAbsoluteFile()
 *	获取文件绝对路径
 *		public String getAbsolutePath()
 *
 *	获取路径的名称
 *		public String getName()
 *	是否为绝对路径
 *		public boolean isAbsolute()
 *	是否为目录
 *		public boolean isDirectory()
 *	是否为文件
 *		public boolean isFile()
 *	是否为隐藏文件或目录
 *		public boolean isHidden()
 *	最后修改时间
 *		public long lastModified()
 *	获取文件长度（字节单位）
 *		public long length()
 *
 *
 *	目录操作
 *	列出所有子路径的名称
 *	public String[] list()
 *	
 *	列出所有子路径文件对象
 *	public File[] listFiles([Filter filter])
 *
 *
 *	文件更名
 *	public boolean renameTo(File dest)
 *	如果要进行更名，则一定要配置有一个完善的路径才可以实现这样的更名处理
 *	更名的操作除了能够更改文件的名字以外，还具备文件的磁盘移动
 * */
public class File_review {
	public static void main(String[] args){
		
		//FileHandler();
		//FileDirHandler();
		//FileDirHandler2();
		//getFileInfo();
		//DirHandler(new File("F:"+File.separator+"xia"+File.separator));
		//CreateFiles();
		RenameHandler();
		
		
	}
	
	
	/**
	 * 多File对象的简单操作，实现文件的创建和删除
	 * */
	private static void FileHandler(){
		//获取文件对象
				//File file =new File("F:\\first.txt");//定义要操作的文件路径
				File file=new File("F:"+File.separator+"first.txt");
				System.out.println("文件对象路径信息："+file);
				
				//如果该文件不存在，那么创建文件。文件存在，删除文件
				
					try {
						if(!file.exists()){
						System.out.println("开始创建文件...");
						System.out.println(file.createNewFile());
						} else{
							System.out.println("开始删除文件...");
							System.out.println(file.delete());
							
						}
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
	
	
	/**
	 * 通过File类，实现文件夹的操作
	 * 该段程序，如果在多线程的编程环境下，会存在效率问题，因为每次判断父目录是否存在，需要花费一定的时间
	 * 可以通过将该功能封装到一个类当中，通过静态代码块的方式，实现只判断一次父目录是否存在的操作
	 * 第一次加载类的时候，会判断父目录是否存在，如果不存在则创建，之后的操作中，由于静态代码块中的内容
	 * 不会在加载，所以不会多次判断父目录是否存在
	 * */
	private static void FileDirHandler(){
		
		File file=new File("F:"+File.separator+"xia"+File.separator+"jun"+File.separator+"jie"+File.separator+"first.txt");
		if(!file.getParentFile().exists()){
			//判断父目录，如果父目录不存在，则创建目录
			System.out.println("创建父目录");
			System.out.println(file.getParentFile().mkdirs());
		}
		try{
		if(!file.exists()){
			//文件不存在，创建文件
			System.out.println("创建文件...");
			System.out.println(file.createNewFile());
		}else{
			System.out.println("删除文件...");
			System.out.println(file.delete());
		}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void FileDirHandler2(){
		try {
			
			FileDirhandler.handler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取文件的信息
	 * */
	private static void getFileInfo(){
		
		File file =new File("F:"+File.separator+"xia"+File.separator+"pic.jpg");
		if(!file.exists()){
			System.out.println("文件不存在！");
			return;
		}
		System.out.printf("大小：%s byte,%s Kb\n", file.length(),file.length()>>10);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		System.out.printf("最后修改时间：%s\n", sdf.format(new Date(file.lastModified())));
		System.out.printf("可读：%s,可写：%s,可执行：%s\n", file.canRead(),file.canWrite(),file.canExecute());
		System.out.printf("文件绝对路径：%s,文件名：%s\n", file.getAbsolutePath(),file.getName());
		System.out.printf("是否为目录：%s,是否为文件：%s,是否为隐藏文件：%s\n", file.isDirectory(),file.isFile(),file.isHidden());
	}
	
	/**
	 * 文件夹操作
	 * 列出一个文件夹下所有以.txt结尾的文件
	 * */
	public static void DirHandler(File file){
		//File file=new File("F:"+File.separator+"xia"+File.separator);
		
		File[] files;
		//System.out.println(file.exists()&&file.isDirectory());
		if(file.exists()&&file.isDirectory()){
			//调用含有过滤器的构造方法
			 files=file.listFiles((f)->{
				if(f.isDirectory()){
					//如果是目录，就加到File数组中
					return true;
					}
				else if(f.getName().endsWith(".txt")){
					//是txt文件，加入到File数组中
					return true;
				}else{
					return false;
				}
				
			});
			
			//遍历File数组，如果是文件夹，则传入DirHandler()方法中，作为递归
			for(File f : files){
				if(f.isDirectory()){
					DirHandler(f);
				}else{
					System.out.println(f);
				}
			}
			
		}
		}
	
	
	private static void CreateFiles(){
		String parent="F:"+File.separator+"xia"+File.separator;
		
		
		for(int i=1;i<120;i++){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		File file=new File(parent+"jay-"+sdf.format(new Date())+"-"+i+".log");	
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
		
	private static void RenameHandler(){
		File parent=new File("F:"+File.separator+"xia"+File.separator);
		RenameUtil ru=new RenameUtil(parent);
		ru.renameHandler();
	}
		
	}
	
	


/*
 * 文件夹操作改进类
 * 将父目录的判断放在静态代码块中
 * */
class FileDirhandler{
	private static File file=new File("F:"+File.separator+"xia"+File.separator+"jun"+File.separator+"jie"+File.separator);
	static {
		if(!file.getParentFile().exists()){
			//判断父目录，如果父目录不存在，则创建目录
			System.out.println("创建父目录");
			System.out.println(file.getParentFile().mkdirs());
		}
		
		
	}
	public static void handler() throws IOException{
		if(!file.exists()){
			//文件不存在，创建文件
			System.out.println("创建文件...");
			System.out.println(file.createNewFile());
		}else{
			System.out.println("删除文件...");
			System.out.println(file.delete());
		}
	}
	
	

}


/*
 * 重命名工具类，实现将某文件夹下的文件，全部都命名成jay-时间戳-编号.log的形式
 * 编号的为数，要根据文件的数量来进行确定，例如有1000个文件，编号位数为4位
 * */
class RenameUtil{
	private File parent;
	private int filecount;
	
	public RenameUtil(File parent){
		this.parent=parent;
	}
	
	/**
	 * 提供给外部调用的改名方法
	 * */
	public void renameHandler(){
		this.counfFiles(this.parent);
		//System.out.println(this.filecount);
		int len=(this.filecount+"").length();
		//System.out.println(len);
		this.handler(this.parent,len);
		
	}
	
	/**
	 * 由renameHandler方法调用，进行改名。
	 * */
	private void handler(File parent,int len){
		File[] files=parent.listFiles((f)->{
			if(f.getName().endsWith("log"))return true;
			else if(f.isDirectory())return true;
			else return false;
		});
		
		for(File f : files){
			if(f.isDirectory()){
				this.handler(f,len);
			}
			else{
				StringBuilder oldname=new StringBuilder(f.getName());
				int start=oldname.lastIndexOf("-")+1;
				int end=oldname.indexOf(".");
				StringBuilder number=new StringBuilder(oldname.subSequence(start, end));
				//System.out.println(number);
				while(number.length()<len){
					number.insert(0, 0);
				}
				//System.out.println(number);
				StringBuilder newname=new StringBuilder(oldname);
				newname.replace(start, end,number.toString());
				System.out.println("旧名字："+oldname+"  新名字："+newname);
				newname.insert(0, File.separator);
				newname.insert(0, parent.getAbsolutePath());
				//System.out.println(newname);
				f.renameTo(new File(newname.toString()));
			}
		}
	}
	
	/**
	 * 由renameHandler方法调用，计算出.log文件的数量
	 * */
	private void counfFiles(File parent){
		//计算文件夹中所有的.log文件，并记录在filecount上
		if(!parent.exists())
		{
			System.out.println("目录不存在！");
			return;
			
		}
		File[] files=parent.listFiles((f)->{
			if(f.getName().endsWith(".log"))return true;
			else if(f.isDirectory())return true;
			return false;
		});
		
		for(File f : files){
			if(f.isDirectory()){
				this.counfFiles(f);
			}
			else{
				this.filecount++;
			}
		}
		}
		
	}

