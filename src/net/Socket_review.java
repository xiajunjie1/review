package net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 *网络编程
 *	网络编程，更多是指针对于"TCP/UDP"两种协议进行网络开发
 *	但是由于这两个协议本身所涉及到的细节性的组成是非常繁琐的，所以为了解决网络程序的开发问题，才针对于
 *	各种网络协议有了一种抽象性的管理，而这种抽象性程序管理逻辑：Socket编程
 *	而在Socket的内部，针对于网络编程的引用模式也分为两种：
 *		CS模式
 *		BS模式
 *
 *	TCP程序：使用三次握手和四次挥手的方式保证所有的数据可靠的进行传输
 *	UDP程序：发送数据报，而接收数据报的一方不一定能够接收到信息
 *
 *
 *	如果想进行网络程序的开发，最简单的应用实现就可以通过java.net包来完成，在这个包中提供有两个类
 *		java.net.ServerSocket:工作在服务端的类，主要定义监听端口，接收客户端的请求
 *		java.net.Socket:
 * 
 * 
 * BIO通信模型（Block IO，阻塞IO)
 * 	Echo模型当中，是一个单线程的编程，BIO模型则是在Echo模型的基础上引用了多线程
 * 	而这种模型存在一个问题，就是该模型，由服务器分配线程对象，可是如果此时的客户端即便没有与服务器产生任何的交互
 * 	这个线程的连接也要继续维护着，如果在高并发访问的程序中，会存在严重的性能浪费
 * 	要解决这个问题，需要用到NIO
 * 		
 * 
 * */
public class Socket_review {
	public static void main(String[] args){
		try {
			//ServerSockTest();
			//EchoServer();
			//BioServer();
			UdpServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void ServerSockTest() throws Exception{
		//创建一个监听9999端口的ServerSocket
		ServerSocket Serversock=new ServerSocket(9999);
		System.out.println("服务器开始监听...");
		//获得一个Socket对象,也可以理解成是客户端
		Socket cilent=Serversock.accept();
		//向客户端输出内容
		PrintStream ps=new PrintStream(cilent.getOutputStream());
		//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(cilent.getOutputStream(),"UTF-8"));
		//PrintWriter ps=new PrintWriter(cilent.getOutputStream());
		//ps.println("服务器测试数据...");
		ps.println("this is Server test data");
		//bw.write("服务器测试数据...");
		cilent.shutdownOutput();//输出完毕后才关闭
		Serversock.close();
		System.out.println("服务器关闭");
		
	}
	
	/**
	 * Echo模型服务器端
	 * @throws IOException 
	 * */
	private static void EchoServer() throws IOException{
		ServerSocket server=new ServerSocket(9999);
		System.out.println("服务器开始运行....");
		Socket client=server.accept();//阻塞等待请求
		boolean flag=true;
		PrintStream out=new PrintStream(client.getOutputStream());
		Scanner in=new Scanner(client.getInputStream());
		in.useDelimiter("\n");
		while(flag){
			if(in.hasNext()){
			
				String value=in.next().trim();
				System.out.println("ttttt："+value);
				if(value.equalsIgnoreCase("exit")){
					out.println("交互结束，服务器断开连接...");
					flag=false;
					break;
				}
				
				out.println("【服务器数据】："+value);
			}
			
		}
		
		in.close();
		out.close();
		server.close();
		
		
	}
	
	
	private static void BioServer()throws Exception{
		ServerSocket server=new ServerSocket(9999);
		System.out.println("服务器运行！");
		while(true)
		{
		BIOClient bclient=new BIOClient(server.accept());
		new Thread(bclient).start();
	
		}
	}
	
	
	private static void UdpServer() throws IOException{
		DatagramSocket server=new DatagramSocket(8000);//设置服务器的监听端口
		String message="测试报文";//要发送的数据
		//创建数据报，包含要发送的信息，即发送的长度，还有发送到哪个客户端的哪个端口
		DatagramPacket packet=new DatagramPacket(message.getBytes(),0,message.getBytes().length,InetAddress.getByName("localhost"),9999);
		server.send(packet);//发送数据
		System.out.println("【UDP】服务器发送数据完毕");
		server.close();
		
	}
	
	
}


class BIOClient implements Runnable{
	private Socket client;
	public BIOClient(Socket client){
		this.client=client;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		PrintStream out=new PrintStream(this.client.getOutputStream());
		Scanner in=new Scanner(this.client.getInputStream());
		in.useDelimiter("\n");
		boolean flag=true;
		while(flag){
			if(in.hasNext()){
				
				String value=in.next().trim();
				System.out.println("收到【"+this.client+"】的信息");
				if(value.equalsIgnoreCase("exit")){
					out.println("交互结束，服务器断开连接...");
					flag=false;
					break;
				}
				
				out.println("【服务器数据】："+value);
		}
			}
		out.close();
		in.close();
		this.client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}